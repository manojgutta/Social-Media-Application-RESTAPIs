package com.twit.twit.repositories;

import com.twit.twit.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    // No need to define createPost method here
    Post findBypostID(int postID);
    // Add additional custom methods if needed
}
