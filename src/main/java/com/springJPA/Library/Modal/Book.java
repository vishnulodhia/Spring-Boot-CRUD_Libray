package com.springJPA.Library.Modal;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name="Book")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long book_id;

    @Column(nullable = false)
    private String bookname;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String status;

    @Column
    @CreationTimestamp
    private Timestamp created_at;

    @Column
    @CreationTimestamp
    private Timestamp updated_at;

    @Column
    private String created_by;

    @Column
    private String update_by;

    @Column
    private long issue_id;

}