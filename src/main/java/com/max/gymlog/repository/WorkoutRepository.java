package com.max.gymlog.repository;

import com.max.gymlog.entity.User;
import com.max.gymlog.entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    List<Workout> findAllByUser(User user);
    List<Workout> findByUserAndWorkoutDate(User user, LocalDate workoutDate);
    List<Workout> findByUserAndWorkoutName(User user, String workoutName);
}
