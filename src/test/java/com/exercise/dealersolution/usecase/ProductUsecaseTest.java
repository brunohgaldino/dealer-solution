package com.exercise.dealersolution.usecase;

import com.exercise.dealersolution.mapper.ProductMapper;
import com.exercise.dealersolution.model.Product;
import com.exercise.dealersolution.model.Request.ProductRequest;
import com.exercise.dealersolution.repository.ProductRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductUsecaseTest {

    @InjectMocks
    private ProductUseCase productUseCase;

    @Mock
    private ProductRepository productRepository;

    @Spy
    private ProductMapper mapper = Mappers.getMapper(ProductMapper.class);

    @Before("test")
    public void setup() {
        MockitoAnnotations.initMocks(this);
        //ProductMapper mapper = Mappers.getMapper(ProductMapper.class); // Initialization of the mapper
        ReflectionTestUtils.setField(productUseCase, "ProductControllerTest", mapper);
    }

    @Test
    public void shouldGetAllProducts() {
        when(productRepository.findAll()).thenReturn(getProducts());
        List<Product> products = productUseCase.getAll();
        assertEquals(products.get(0).getId(), 1);
        assertEquals(products.size(), 2);
    }

    @Test
    public void shouldGetByIdProduct() {
        when(productRepository.findById(anyLong())).thenReturn(getProductOptional());
        Optional<Product> product = productUseCase.getById(anyLong());
        assertEquals(product.get().getId(), 1);
    }

    //TODO: ajustar assert
    @Test
    public void shouldCreateProduct() {
        when(productRepository.save(getProduct2())).thenReturn(getProduct());
        Product product = productUseCase.createProduct(getProductRequest());
    }

    @Test
    public void shouldGetAvaliable() {
        when(productRepository.findAll()).thenReturn(getProducts());
        List<Product> product = productUseCase.getAllAvaliable();
        assertEquals(product.size(), 1);
    }

    @Test
    public void shouldRetrieveDeadline() {
        when(productRepository.findById(anyLong())).thenReturn(getProductRetrieve());
        LocalDate date = productUseCase.retrieveDeadline(anyLong());
        assertEquals(date, LocalDate.of(2099, 12, 31));
    }

    //TODO: testar exceptions
    @Test
    public void shouldDeleteListProduct() {
        when(productRepository.findById(anyLong())).thenReturn(getProductRetrieve());
        productUseCase.deleteItems(getProductsRequest());
    }

    @Test
    public void shouldDeleteProduct() {
        when(productRepository.findById(anyLong())).thenReturn(getProductRetrieve());
        productUseCase.delete(anyLong());
    }

    //TODO: ajustar assert
    @Test
    public void shouldUpdateProduct() {
        when(productRepository.findById(anyLong())).thenReturn(getProductRetrieve());
        Product product = productUseCase.update(getProductRequest());
    }

    //TODO: ajustar assert
    @Test
    public void shouldUpdateProductPrice() {
        when(productRepository.findById(anyLong())).thenReturn(getProductRetrieve());
        when(productRepository.save(getProduct2())).thenReturn(getProduct());
        Product product = productUseCase.updateProductPrice(anyLong(), 400.0);
    }

    //TODO: ajustar assert order
    @Test
    public void shouldgetAllAvaliableOrder() {
        when(productRepository.findAll()).thenReturn(getProducts());
        List<Product> product = productUseCase.getAllAvaliableOrder();
        assertEquals(product.size(), 1);
    }

    public static List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "SUV", "1", 120.0, 1, "31/12/2099"));
        products.add(new Product(2L, "Sedan", "2", 150.0, 1, "31/12/2100"));
        return products;
    }

    public static List<ProductRequest> getProductsRequest() {
        List<ProductRequest> products = new ArrayList<>();
        products.add(new ProductRequest(1L, "SUV", "1", 120.0, 1, "31/12/2099"));
        products.add(new ProductRequest(2L, "Sedan", "2", 150.0, 1, "31/12/2100"));
        return products;
    }

    public static Optional<Product> getProductRetrieve() {
        return Optional.of(new Product(1L, "SUV", "1", 120.0, 0, "31/12/2099"));
    }

    public static Optional<Product> getProductOptional() {

        return Optional.of(new Product(1L, "SUV", "1", 120.0, 1, "31/12/2099"));
    }

    public static Product getProduct() {

        return new Product(1L, "SUV", "1", 120.0, 1, "31/12/2099");
    }

    public static Product getProduct2() {

        return new Product(1L, "SUV", "1", 120.0, 1, "31/12/2099");
    }

    public static ProductRequest getProductRequest() {

        return new ProductRequest(1L, "HATCH", "1", 120.0, 1, "31/12/2099");
    }

}
