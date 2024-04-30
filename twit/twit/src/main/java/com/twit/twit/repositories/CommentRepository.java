package com.twit.twit.repositories;

import com.twit.twit.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
//    List<Comment> findByPostId(int postId);
}
