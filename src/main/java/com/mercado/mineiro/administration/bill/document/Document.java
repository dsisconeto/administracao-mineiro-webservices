package com.mercado.mineiro.administration.bill.document;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "bill_documents")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;
    private String code;
    @ManyToOne(optional = false)
    @NonNull
    private DocumentType type;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
