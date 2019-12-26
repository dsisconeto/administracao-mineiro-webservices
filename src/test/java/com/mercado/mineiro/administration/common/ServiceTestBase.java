package com.mercado.mineiro.administration.common;

import com.github.javafaker.Faker;
import com.mercado.mineiro.administration.common.factory.FakerFactory;
import com.mercado.mineiro.administration.common.factory.ModelMapperFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

public class ServiceTestBase {

    protected ModelMapper modelMapper;
    protected Faker faker;


    @BeforeAll
    protected void beforeAll() {

        MockitoAnnotations.initMocks(this);
        faker = new FakerFactory().make();
        modelMapper = new ModelMapperFactory().make();
    }
}
