package com.mercado.mineiro.administration.bill.category;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NonNull
    private String Name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Category(@NonNull Long id, @NonNull String name) {
        this.id = id;
        Name = name;
    }
}
