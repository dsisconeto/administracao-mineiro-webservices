package com.mercado.mineiro.administration.bill.payment;

import com.mercado.mineiro.administration.bill.Bill;
import com.mercado.mineiro.administration.bill.category.Category;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "bill_payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private BigDecimal amount;
    private LocalDate paidAt;
    @OneToOne(mappedBy = "payment")
    private Bill bill;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
