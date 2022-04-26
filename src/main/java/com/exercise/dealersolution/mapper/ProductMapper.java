package com.exercise.dealersolution.mapper;

import com.exercise.dealersolution.model.Request.ProductRequest;
import com.exercise.dealersolution.model.Product;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring",  injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProductMapper {

    Product toEntity(final ProductRequest post);

}
