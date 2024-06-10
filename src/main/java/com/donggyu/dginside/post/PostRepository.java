package com.donggyu.dginside.post;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Modifying
    @Transactional
    @Query("Update posts p SET p.title = :title, p.category = :category, p.contents = :contents, p.attachedFile = :attachedFile WHERE p.id = :id")
    int updatePostById(Long id, String title, String Category, String contents, List<String> attachedFile);
}
