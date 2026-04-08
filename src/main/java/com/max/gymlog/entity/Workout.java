package com.max.gymlog.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String workoutName;
    @Column(nullable = false)
    private int durationMinutes;
    @Column(nullable = false)
    private LocalDate workoutDate;
    private String notes;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Workout(String workoutName, int durationMinutes, LocalDate workoutDate, String notes, User user) {
        this.workoutName = workoutName;
        this.durationMinutes = durationMinutes;
        this.workoutDate = workoutDate;
        this.notes = notes;
        this.user = user;
    }

    protected Workout() {
    }
}
