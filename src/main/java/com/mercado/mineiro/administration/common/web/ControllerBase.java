package com.mercado.mineiro.administration.common.web;

public abstract class ControllerBase {

    private ResponseFactory responseFactory;

    protected ControllerBase(ResponseFactory responseFactory) {

        this.responseFactory = responseFactory;
    }

    protected ResponseFactory response() {
        return responseFactory;
    }


}
