package com.ll.groupware_renewal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Professor")
@Getter
@Setter
@NoArgsConstructor
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long professorId;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    private String professorRoom;
    private String professorRoomNum;
    private String professorColleges;
    private String professorMajor;
} 