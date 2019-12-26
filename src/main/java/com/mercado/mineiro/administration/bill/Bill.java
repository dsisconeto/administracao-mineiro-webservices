package com.mercado.mineiro.administration.bill;

import com.mercado.mineiro.administration.bill.category.Category;
import com.mercado.mineiro.administration.bill.document.Document;
import com.mercado.mineiro.administration.bill.payment.Payment;
import com.mercado.mineiro.administration.common.base.DomainEvent;
import com.mercado.mineiro.administration.common.exception.DomainException;
import com.mercado.mineiro.administration.common.base.EntityBase;
import com.mercado.mineiro.administration.supplier.Supplier;
import lombok.*;
import org.springframework.context.ApplicationEvent;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;

import javax.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Bill extends EntityBase {


    @NonNull
    private String description;

    @NonNull
    private BigDecimal amount;

    @NonNull
    private LocalDate payIn;

    @NonNull
    @Enumerated(EnumType.STRING)
    @Setter(AccessLevel.NONE)
    private Status status = Status.PAYABLE;

    @ManyToOne(optional = false)
    @NonNull
    private Category category;

    @ManyToOne
    private Supplier supplier;

    @OneToOne
    @JoinColumn(unique = true)
    private Payment payment;

    private BigDecimal interest;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Document document;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;

    @Transient
    @Setter(AccessLevel.NONE)
    private Collection<Object> events = new ArrayList<>();


    public Bill(@NonNull String description,
                @NonNull BigDecimal amount,
                @NonNull Category category,
                @NonNull LocalDate payIn
    ) {

        this.description = description;
        this.amount = amount;
        this.category = category;
        this.setPayIn(payIn);

    }


    void pay(@NonNull Payment payment) throws DomainException {

        var isPaymentAmountLessThanBillAmount = payment.getAmount().compareTo(this.getAmount()) < 0;

        if (isPaymentAmountLessThanBillAmount) {
            throw new DomainException("Valor pago não pode ser menor que valor da conta");
        }


        this.payment = payment;
        this.interest = payment.getAmount().subtract(this.getAmount());
        changeStatus(Status.PAID);
    }

    void cancel() {
        this.status = Status.CANCELLED;
    }


    public void setAmount(BigDecimal amount) throws DomainException {

        if (this.isStatusPaid()) {
            throw new DomainException("Amount não pode ser alterado se o status for paid");
        }

        this.amount = amount;
    }

    public void setPayIn(@NonNull LocalDate payIn) throws DomainException {

        if (isStatusPaid() || isStatusCanceled()) {
            throw new DomainException(
                    "A data do pagamento não pode ser alterada se a conta já estiver paga ou cancelada"
            );
        }

        var now = LocalDate.now();

        this.payIn = payIn;

        if (payIn.isBefore(now)) {
            changeStatus(Status.OVERDUE);
            return;
        }

        if (payIn.isEqual(now)) {
            changeStatus(Status.TO_PAY_TODAY);
            return;
        }

        this.changeStatus(Status.PAYABLE);
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

    private void changeStatus(Status status) {
        events.add(new BillChangeStatusEvent(this, this.status));
        this.status = status;
    }

    @DomainEvents
    public Collection<Object> getEvents() {
        return Collections.unmodifiableCollection(events);
    }


}
