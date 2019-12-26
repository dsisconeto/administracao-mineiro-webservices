package com.mercado.mineiro.administration.bill.document.type;

import com.mercado.mineiro.administration.common.base.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@Table(name = "bill_document_types")
@EqualsAndHashCode(callSuper = true)
public class DocumentType extends EntityBase {

    private Long id;
    @NonNull
    private String name;

    public DocumentType(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
