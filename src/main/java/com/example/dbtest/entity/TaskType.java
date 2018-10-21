package com.example.dbtest.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "task_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
public class TaskType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "type")
    private String type;

    @Column(name = "comment")
    private String comment;

    @OneToMany(mappedBy = "taskType",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<Task> task;
}






