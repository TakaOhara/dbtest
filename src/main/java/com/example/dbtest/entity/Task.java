package com.example.dbtest.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Data
//@NoArgsConstructor
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "type_id")//tasksテーブルの中の外部キーになっているカラムを指定　相手先は外部結合から自動的に探しに行く
    private int typeId;

    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "type_id",
            insertable = false, updatable = false)//このnameはTask内のフィールドと重複してはならない
    private TaskType taskType;


    @Column(name = "title")
    private String title;

    @Column(name = "detail")
    //@NotEmpty//アノテーションをつけるだけで空の場合例外発生
    private String detail;

    @JoinColumn(name = "deadline")
    private LocalDateTime deadline;

    public Task(int typeId, String title, String detail, LocalDateTime deadline) {
        this.typeId = typeId;
        this.title = title;
        this.detail = detail;
        this.deadline = deadline;
    }

    public Task(int id, int typeId, String title, String detail, LocalDateTime deadline) {
        this.id = id;
        this.typeId = typeId;
        this.title = title;
        this.detail = detail;
        this.deadline = deadline;
    }
}






