package com.exercise.dealersolution.controller;

import com.exercise.dealersolution.model.Request.ProductRequest;
import com.exercise.dealersolution.model.Product;
import com.exercise.dealersolution.usecase.ProductUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/dealer")
public class ProductController {

    @Autowired
    private ProductUseCase productUseCase;

    @GetMapping("/models")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<List<Product>> getAll() {
        return new ResponseEntity<>(productUseCase.getAll(), HttpStatus.OK);
    }

    @PostMapping("/models")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Product> create(@RequestBody final ProductRequest product) {
        return new ResponseEntity<>(productUseCase.createProduct(product), HttpStatus.OK);
    }

    @GetMapping("/models/avaliable")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<List<Product>> getAllAvaliable() {
        return new ResponseEntity<>(productUseCase.getAllAvaliable(), HttpStatus.OK);
    }

    @GetMapping("/models/retrieve/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public LocalDate retrieveDeadline(@PathVariable final long id) {
        return productUseCase.retrieveDeadline(id);
    }

    @DeleteMapping("/models")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteItems(@RequestBody final List<ProductRequest> products) {
        productUseCase.deleteItems(products);
    }

    @DeleteMapping("/models/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable final long id) {
        productUseCase.delete(id);
    }

    @PutMapping("/model/{id}")
    public ResponseEntity<Product> update(@PathVariable final long id, @RequestBody final ProductRequest product) {
        product.setId(id);
        return new ResponseEntity<>(productUseCase.update(product), HttpStatus.OK);
    }

    @PutMapping("/model/{id}/{price}")
    public ResponseEntity<Product> updateProductPrice(@PathVariable final long id, @PathVariable final Double price) {
        return new ResponseEntity<>(productUseCase.updateProductPrice(id, price), HttpStatus.OK);
    }

    @GetMapping("/models/avaliable/order")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<List<Product>> getAllAvaliableOrder() {
        return new ResponseEntity<>(productUseCase.getAllAvaliableOrder(), HttpStatus.OK);
    }
}
