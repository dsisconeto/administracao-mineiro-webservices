package com.mercado.mineiro.administration.bill.recurrence;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "bill_recurrences")
public class Recurrence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;
    private int quantity;
    @Enumerated(EnumType.STRING)
    private RecurrenceType type;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
