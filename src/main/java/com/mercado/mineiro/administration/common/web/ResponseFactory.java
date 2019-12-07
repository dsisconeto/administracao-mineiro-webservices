package com.mercado.mineiro.administration.common.web;

import com.mercado.mineiro.administration.common.DomainException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class ResponseFactory {


    public ResponseEntity<Object> unprocessableEntity(DomainException e) {
        return unprocessableEntity(e.getMessage(), new LinkedHashMap<String, String>());
    }


    public ResponseEntity<Object> unprocessableEntity(String message, Map<String, String> errors) {

        var body = new LinkedHashMap<String, Object>();
        body.put("message", message);
        body.put("errors", errors);
        return ResponseEntity.unprocessableEntity()
                .body(body);
    }


    public <T> ResponseEntity<T> created(String path, Long id, T body) {
        var builder = UriComponentsBuilder.newInstance();
        var uri = builder.path(path).buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).body(body);
    }

    public ResponseEntity notContent() {
        return ResponseEntity.noContent().build();
    }

    public <T> ResponseEntity<T> notFound() {
        return ResponseEntity.notFound().build();
    }

    public <T> ResponseEntity<T> ok(T body) {
        return ResponseEntity.ok(body);
    }
}
