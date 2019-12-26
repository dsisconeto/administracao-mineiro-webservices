package com.mercado.mineiro.administration.common.factory;

import org.modelmapper.ModelMapper;

public class ModelMapperFactory {

    public ModelMapper make() {

        var mapper = new ModelMapper();
        mapper.getConfiguration().setAmbiguityIgnored(true);
        return mapper;
    }
}
