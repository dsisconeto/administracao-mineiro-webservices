package com.mercado.mineiro.administration;

import com.github.javafaker.Faker;
import com.mercado.mineiro.administration.common.factory.FakerFactory;
import com.mercado.mineiro.administration.common.factory.ModelMapperFactory;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ConfigurationBeans {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapperFactory().make();
    }

    @Bean
    public Faker faker() {
        return new FakerFactory().make();
    }

}
