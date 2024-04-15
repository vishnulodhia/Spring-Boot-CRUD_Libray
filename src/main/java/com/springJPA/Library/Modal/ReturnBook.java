package com.springJPA.Library.Modal;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Timestamp;
import java.util.Calendar;

@Entity
@Table(name="ReturnBook")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class ReturnBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long returnBook_id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false,referencedColumnName = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserDetail user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false,referencedColumnName = "book_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Book book;

    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp return_date ;


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


}
