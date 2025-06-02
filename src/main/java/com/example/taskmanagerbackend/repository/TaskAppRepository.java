package com.example.taskmanagerbackend.repository;

import com.example.taskmanagerbackend.model.TaskApp;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TaskAppRepository extends JpaRepository<TaskApp, Long> {
    List<TaskApp> findByTaskListAppId(Long taskListAppId);

    @Query("SELECT t FROM TaskApp t WHERE t.taskListApp.category.userApp.email = :email")
    List<TaskApp> findByUserEmail(@Param("email") String email);

    @Query("SELECT t FROM TaskApp t WHERE t.taskListApp.category.userApp.email = :email AND t.date = :date")
    List<TaskApp> findByUserEmailAndDate(@Param("email") String email, @Param("date") LocalDate date);

    @Query("""
    SELECT t FROM TaskApp t 
    WHERE t.taskListApp.category.userApp.email = :email 
    AND t.completed = false 
    AND t.date >= :today 
    ORDER BY t.date ASC
""")
    List<TaskApp> findNextTop4PendingTasksByEmailAfterToday(
            @Param("email") String email,
            @Param("today") LocalDate today,
            Pageable pageable
    );


    @Query("SELECT t FROM TaskApp t " +
            "WHERE t.taskListApp.category.userApp.email = :email " +
            "AND t.date BETWEEN :startDate AND :endDate")
    List<TaskApp> findUpcomingTasksByUserEmail(
            @Param("email") String email,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

}
