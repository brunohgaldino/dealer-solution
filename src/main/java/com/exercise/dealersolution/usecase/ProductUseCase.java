package com.exercise.dealersolution.usecase;

import com.exercise.dealersolution.exception.ProductAvaliableException;
import com.exercise.dealersolution.exception.ProductNotFoundException;
import com.exercise.dealersolution.mapper.ProductMapper;
import com.exercise.dealersolution.model.Product;
import com.exercise.dealersolution.model.Request.ProductRequest;
import com.exercise.dealersolution.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProductUseCase {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Optional<Product> getById(long id) {
        return productRepository.findById(id);
    }

    public Product createProduct(ProductRequest product) {

        Product ae = productMapper.toEntity(product);
        return productRepository.save(ae);
    }

    public List<Product> getAllAvaliable() {
        List<Product> products = getAll();

        return products
                .stream()
                .filter(prd -> prd.getStatus().equals("2"))
                .collect(Collectors.toList());

    }


    public LocalDate retrieveDeadline(final long id) {

        Optional<Product> product = getById(id);

        if (product.isPresent()) {
            if (product.get().getQuantity() == 0) {
                return formatDate(product.get().getDeadline());
            }
            throw new ProductAvaliableException();
        }
        throw new ProductNotFoundException();

    }

    private LocalDate formatDate(final String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }


    //TODO: informar o produto nao encontrado e remover os existentes
    public void deleteItems(final List<ProductRequest> products) {
        for (ProductRequest product : products) {
            delete(productMapper.toEntity(product).getId());
        }
    }

    public void delete(final long id) {
        Optional<Product> product = getById(id);
        if (product.isPresent()) {
            productRepository.delete(product.get());
        } else {
            throw new ProductNotFoundException();
        }
    }

    public Product update(final ProductRequest product) {
        final Optional<Product> productExists = getById(product.getId());
        if (productExists.isPresent()) {

            Product updatedProduct = productExists.get();
            updatedProduct.setDescription(product.getDescription());
            updatedProduct.setDeadline(product.getDeadline());
            updatedProduct.setPrice(product.getPrice());
            updatedProduct.setQuantity(product.getQuantity());
            updatedProduct.setStatus(product.getStatus());

            return productRepository.save(updatedProduct);

        } else {
            throw new ProductNotFoundException();
        }
    }

    public Product updateProductPrice(final long id, final Double price) {

        final Optional<Product> productExists = getById(id);
        if (productExists.isPresent()) {
            Product updatedProduct = productExists.get();
            updatedProduct.setPrice(price);

            return productRepository.save(updatedProduct);

        } else {
            throw new ProductNotFoundException();
        }
    }

    public List<Product> getAllAvaliableOrder() {
        List<Product> products = getAllAvaliable();

        products.sort(Comparator.comparing(Product::getDeadline)
                .thenComparing(Product::getQuantity)
                .thenComparing(Product::getPrice).reversed());

        return products;
    }
}
