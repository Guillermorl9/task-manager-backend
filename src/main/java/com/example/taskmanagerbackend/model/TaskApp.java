package com.example.taskmanagerbackend.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskApp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String date;
    private String time;
    private String description;
    private boolean completed;

    @ManyToOne
    private TaskListApp taskListApp;
}
