package com.mercado.mineiro.administration.bill.category;


import com.mercado.mineiro.administration.common.base.EntityBase;
import lombok.*;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)

public class Category extends EntityBase {

    @NonNull
    private String name;

    public Category(Long id, String name) {
        setId(id);
        this.name = name;
    }
}
