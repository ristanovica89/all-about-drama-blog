package com.artist.blog_app.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    private String imgPath;

    @Column(name = "added_date", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime addedDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private BlogUser blogUser;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
