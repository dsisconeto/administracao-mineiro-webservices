package com.mercado.mineiro.administration.bill;

import com.mercado.mineiro.administration.bill.category.Category;
import com.mercado.mineiro.administration.bill.document.Document;
import com.mercado.mineiro.administration.bill.payment.Payment;
import com.mercado.mineiro.administration.common.DomainException;
import com.mercado.mineiro.administration.supplier.Supplier;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Table(name = "bills")
public class Bill {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @NonNull
    @Getter
    @Setter
    private String description;
    @NonNull
    @Getter
    private BigDecimal amount;
    @NonNull
    @Getter
    private LocalDate payIn;
    @NonNull
    @Enumerated(EnumType.STRING)
    @Getter
    private Status status = Status.PAYABLE;
    @ManyToOne(optional = false)
    @NonNull
    @Getter
    @Setter
    private Category category;
    @ManyToOne
    @Getter
    @Setter
    private Supplier supplier;

    @OneToOne
    @JoinColumn(unique = true)
    @Getter
    private Payment payment;
    @Getter
    private BigDecimal interest;
    @OneToOne
    @Getter
    @Setter
    private Document document;
    @Getter
    private LocalDateTime createdAt = LocalDateTime.now();
    @Getter
    @Setter
    private LocalDateTime updatedAt;


    Bill(@NonNull String description,
         @NonNull BigDecimal amount,
         @NonNull Category category,
         @NonNull LocalDate payIn
    ) {

        this.description = description;
        this.amount = amount;
        this.category = category;
        this.payIn = payIn;

        this.verifyPayIn();
    }


    void pay(@NonNull Payment payment) throws DomainException {

        var isPaymentAmountLessThanBillAmount = payment.getAmount().compareTo(this.getAmount()) < 0;

        if (isPaymentAmountLessThanBillAmount) {
            throw new DomainException("Valor pago não pode ser menor que valor da conta");
        }


        this.payment = payment;
        this.interest = payment.getAmount().subtract(this.getAmount());
        this.status = Status.PAID;
    }

    void cancel() {
        this.status = Status.CANCELLED;
    }

    private void verifyPayIn() {

        if (isStatusPaid() || isStatusCanceled()) return;

        var now = LocalDate.now();

        if (payIn.isBefore(now)) {
            this.status = Status.OVERDUE;
            return;
        }

        if (payIn.isEqual(now)) {
            this.status = Status.TO_PAY_TODAY;
        }
    }

    void setAmount(BigDecimal amount) throws DomainException {

        if (this.isStatusPaid()) {
            throw new DomainException("Amount não pode ser alterado se o status for paid");
        }

        this.amount = amount;
    }

    void setPayIn(@NonNull LocalDate payIn) throws DomainException {

        if (isStatusPaid()) {
            throw new DomainException("PayIn não pode ser alterado se o status for paid");
        }

        this.payIn = payIn;
    }


    public boolean isStatusCanceled() {
        return status == Status.CANCELLED;
    }

    public boolean isStatusPaid() {
        return status == Status.PAID;
    }

    public boolean isStatusToPayToday() {
        return status == Status.TO_PAY_TODAY;
    }

    public boolean isStatusToOverDue() {
        return status == Status.OVERDUE;
    }

    public boolean isStatusPayable() {
        return status == Status.PAYABLE;
    }

}
