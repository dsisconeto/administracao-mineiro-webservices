package com.mercado.mineiro.administration.common.factory;

import com.github.javafaker.Faker;

import java.util.Locale;

public class FakerFactory {

    public Faker make() {
        return new Faker(new Locale("pt-BR"));
    }
}
