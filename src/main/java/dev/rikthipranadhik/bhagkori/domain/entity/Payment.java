package dev.rikthipranadhik.bhagkori.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="payment_id")
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    private Room room;

    @Column(name="payment_time")
    private LocalDateTime paymentTime;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="payment_payer_id", nullable=false)
    private User payer;

    @ManyToOne
    @JoinColumn(name="payment_payee_id", nullable=false)
    private User payee;

    @Column(name="payment_amount", nullable=false)
    private BigDecimal amount;
}
