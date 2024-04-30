package com.twit.twit.service;

import com.twit.twit.model.Comment;
import com.twit.twit.model.UserClass;
import com.twit.twit.repositories.CommentRepository;
import com.twit.twit.repositories.PostRepository;
import com.twit.twit.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;
//
    @Autowired
    private CommentService commentService;

    public void createPost(String postBody, UserClass user) {
        Post post = new Post();
        post.setPostBody(postBody);
        post.setUserID(user.getUserID());
        postRepository.save(post); // This line saves the post to the database
    }
    public boolean existsById(int postId) {
        return postRepository.existsById(postId);
    }

    public Boolean editPost(int postID, String newPostBody) {
        Post existingPost = postRepository.findBypostID(postID);
        if (existingPost != null) {
            existingPost.setPostBody(newPostBody);
            postRepository.save(existingPost);
            return true;
        } else {
            return false;
        }
    }
    // Method to delete a post
    public Boolean deletePost(int postID) {
        Post existingPost = postRepository.findBypostID(postID);
        if (existingPost != null) {
            List<Comment> commentsToDelete = commentService.findByPostID(postID);
            for(Comment comment : commentsToDelete) {
                commentRepository.deleteById(comment.getCommentID());
            }
            postRepository.delete(existingPost);
            return true;
        } else {
            return false;
        }
   }

    public Post findPostBypostID(int postID) {
        return postRepository.findBypostID(postID);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
}

