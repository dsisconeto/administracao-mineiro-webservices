package com.mercado.mineiro.administration.bill;

import com.mercado.mineiro.administration.bill.category.Category;
import com.mercado.mineiro.administration.bill.document.Document;
import com.mercado.mineiro.administration.bill.payment.Payment;
import com.mercado.mineiro.administration.supplier.Supplier;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "bills")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;
    @NonNull
    private String description;
    @NonNull
    private BigDecimal amount;
    @NonNull
    private LocalDate payIn;
    @NonNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(optional = false)
    @NonNull
    private Category category;
    @ManyToOne
    private Supplier supplier;
    @OneToOne
    @JoinColumn(unique = true)
    private Payment payment;
    @OneToOne
    private Document document;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
