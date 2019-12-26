package com.mercado.mineiro.administration.common.web;

import com.mercado.mineiro.administration.common.exception.DomainException;
import com.mercado.mineiro.administration.common.base.EntityBase;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class Responses {

    @Setter
    @Getter
    protected String path;

    public static ResponseEntity<Object> unprocessableEntity(DomainException e) {
        return unprocessableEntity(e.getMessage(), new LinkedHashMap<>());
    }


    public static ResponseEntity<Object> unprocessableEntity(String message, Map<String, String> errors) {

        var body = new LinkedHashMap<String, Object>();
        body.put("message", message);
        body.put("errors", errors);
        return ResponseEntity.unprocessableEntity()
                .body(body);
    }


    public static <T extends EntityBase> ResponseEntity<T> created(String path, T entity) {
        var builder = UriComponentsBuilder.newInstance();
        var uri = builder.path(path + "/{id}").buildAndExpand(entity.getId()).toUri();
        return ResponseEntity.created(uri).body(entity);
    }


    public static <T> ResponseEntity<T> created(String path, Long id, T body) {
        var builder = UriComponentsBuilder.newInstance();
        var uri = builder.path(path).buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).body(body);
    }

    public static ResponseEntity notContent() {
        return ResponseEntity.noContent().build();
    }

    public static <T> ResponseEntity<T> notFound() {
        return ResponseEntity.notFound().build();
    }

    public static <T> ResponseEntity<T> ok(T body) {
        return ResponseEntity.ok(body);
    }
}
