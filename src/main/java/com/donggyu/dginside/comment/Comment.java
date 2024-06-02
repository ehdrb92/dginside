package com.donggyu.dginside.comment;

import com.donggyu.dginside.post.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity(name = "comments")
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "contents", nullable = false, columnDefinition = "TEXT")
    private String contents;

    @Column(name = "likes", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer likes;

    @Column(name = "dislikes", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer dislikes;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "posts_id", nullable = false)
    private Post posts;
}
