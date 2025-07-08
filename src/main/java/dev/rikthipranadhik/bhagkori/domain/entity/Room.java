package dev.rikthipranadhik.bhagkori.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
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

    @Column(name= "room_description")
    private String description;

    @Column(name="room_create_time", nullable=false)
    private LocalDateTime createTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "room_creator_id")
    private User creatorId;

    @ManyToMany(mappedBy="rooms", fetch=FetchType.LAZY)
    private Set<User> members;
}
