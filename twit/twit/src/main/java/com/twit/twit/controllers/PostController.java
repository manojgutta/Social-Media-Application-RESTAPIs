package com.twit.twit.controllers;

import com.twit.twit.model.*;
import com.twit.twit.service.PostService;
import com.twit.twit.service.UserService;
import com.twit.twit.model.PostDTO;
import com.twit.twit.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @GetMapping
    public ResponseEntity<Object> getPostById(@RequestParam("postID") int postID) {
        // Retrieve the post by ID
        Post post = postService.findPostBypostID(postID);
        if (post == null) {
            ReturnsError e = new ReturnsError("Post does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        }
        List<CommentDTO> comments = new ArrayList<>();
        List<Comment> c = commentService.findByPostID(postID);
        List<Comment> sortedComments = c.stream()
                .sorted(Comparator.comparing(Comment::getDate))
                .toList();
        for (Comment c1 : sortedComments) {
            comments.add(commentService.getCommentByID(c1.getCommentID()));
        }
        PostDTO post1 = new PostDTO(post.getPostID(), post.getPostBody(), post.getDate(), comments);
        return ResponseEntity.ok(post1);
    }

    @DeleteMapping
    public ResponseEntity<?> deletePost(@RequestParam("postID") int postID) {
        boolean success = postService.deletePost(postID);
        if (!success) {
            ReturnsError e = new ReturnsError("Post does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        }
        return ResponseEntity.ok("Post deleted");
    }

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody Post post) {
        // Check if the user exists
        int userID = post.getUserID();
        UserClass user = userService.findByuserID(userID);
        if (user == null) {
            ReturnsError e = new ReturnsError("User does not exist");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }

        // Create the post
        postService.createPost(post.getPostBody(), user);

        return ResponseEntity.status(HttpStatus.CREATED).body("Post created successfully");
    }

    @PatchMapping
    public ResponseEntity<?> editPost(@RequestBody PostController.PostEditPayload payload) {
        boolean success = postService.editPost(payload.getPostID(), payload.getPostBody());
        if (success) {
            return ResponseEntity.ok("Post edited successfully");
        } else {
            ReturnsError e = new ReturnsError("Post does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        }
    }

    public static class PostEditPayload {
        private String postBody;
        private int postID;

        public int getPostID() {
            return this.postID;
        }

        public void setPostID(int postID) {
            this.postID = postID;
        }
        public String getPostBody() {
            return this.postBody;
        }

        public void setPostBody(String postBody) {
            this.postBody = postBody;
        }
    }

}
