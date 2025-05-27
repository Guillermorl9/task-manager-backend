package com.example.taskmanagerbackend.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskApp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    //private String date;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private String time;
    private String description;
    private boolean completed;

    @ManyToOne
    @JsonBackReference
    private TaskListApp taskListApp;
}
