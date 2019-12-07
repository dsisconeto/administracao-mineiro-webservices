package com.mercado.mineiro.administration.bill.document;


import com.mercado.mineiro.administration.bill.document.type.DocumentType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "bill_documents")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private String code;
    @ManyToOne(optional = false)
    @NonNull
    private DocumentType type;

    public Document(String code, @NonNull DocumentType type) {
        this.code = code;
        this.type = type;
    }
}
