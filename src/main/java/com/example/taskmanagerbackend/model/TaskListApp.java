package com.example.taskmanagerbackend.model;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskListApp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    private CategoryApp category;

    @OneToMany(mappedBy = "taskListApp", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskApp> tasks = new ArrayList<>();

}
