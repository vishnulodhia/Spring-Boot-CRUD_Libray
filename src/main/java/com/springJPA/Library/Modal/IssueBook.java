package com.springJPA.Library.Modal;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

@Entity
@Table(name="IssueBook")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class IssueBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long issueBook_id;

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
    private Timestamp issue_date ;

    @Column(nullable = false)
    private  Timestamp return_date= YourEntity();

    public Timestamp YourEntity() {
        // Calculate updatedAtPlus8Days based on the current timestamp
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 8);  // Adding 8 days
        return new Timestamp(calendar.getTimeInMillis());

    }

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
