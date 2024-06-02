package com.donggyu.dginside.user;

import com.donggyu.dginside.post.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.Set;

@Entity(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, length = 20, unique = true)
    private String username;

    @Column(name = "password", nullable = false, columnDefinition = "BLOB")
    private String password;

    @Column(name = "name", nullable = false, length = 10)
    private String name;

    @Column(name= "email", nullable = false, length = 255)
    private String email;

    @Column(name = "nickname", nullable = false, length = 255)
    private String nickname;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private Date createdAt;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Post> posts;
}
