package com.mercado.mineiro.administration.bill.document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;


import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "bill_documents")
public class DocumentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;
    @NonNull
    private String name;
}
