package dev.rikthipranadhik.bhagkori.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="rooms")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name= "room_id")
    private Long id;

    @Column(name="room_name", nullable = false)
    private String name;

    @ManyToMany(mappedBy="rooms", fetch=FetchType.LAZY)
    private Set<User> members;
}
