package dev.rikthipranadhik.bhagkori.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="expenses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Expense {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="expense_id")
    private Long id;

    @Column(name="expense_name", nullable=false)
    private String name;

    @Column(name="expense_create_time")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name="expense_payer_id")
    private User payer;

    @ManyToOne
    @JoinColumn(name="expense_room_id")
    private Room room;


}
