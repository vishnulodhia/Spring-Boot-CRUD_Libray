package com.springJPA.Library.Modal;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name="UserDetail")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString


public class UserDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column
    private Long fine;

    @Column
    private  Long totalFine;

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

    @ElementCollection
    private List<Book> bookdata;

}
