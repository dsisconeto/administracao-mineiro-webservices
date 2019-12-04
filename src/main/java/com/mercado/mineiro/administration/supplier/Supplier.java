package com.mercado.mineiro.administration.supplier;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "suppliers")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @NonNull
    private String name;

    public Supplier(long id, @NonNull String name) {
        this.id = id;
        this.name = name;
    }

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
