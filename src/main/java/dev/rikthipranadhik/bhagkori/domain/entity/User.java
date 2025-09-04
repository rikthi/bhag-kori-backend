package dev.rikthipranadhik.bhagkori.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "user_id")
    private Long id;

    @Column(name= "user_email", nullable = false, unique = true)
    private String email;

    @Column(name="user_phone_number")
    private String phoneNumber;

    @Column(name= "user_name", nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="users_rooms",
    joinColumns =
            @JoinColumn(name="user_id"),
    inverseJoinColumns =
            @JoinColumn(name="room_id")
    )
    private Set<Room> rooms = new HashSet<>();

}
