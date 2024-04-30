package com.twit.twit.controllers;

import com.twit.twit.model.*;
import com.twit.twit.repositories.UserRepository;
import com.twit.twit.service.CommentService;
import com.twit.twit.service.PostService;
import com.twit.twit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<List<PostDTO>> getFeed()
    {
        List<Post> allPosts = postService.getAllPosts();

        // Sort posts based on date in reverse chronological order
        List<Post> sortedPosts = allPosts.stream()
                .sorted(Comparator.comparing(Post::getDate).reversed())
                .toList();

        // Map sorted posts to PostDTO objects
        List<PostDTO> postDTOs = sortedPosts.stream()
                .map(post -> {
                    // Retrieve comments for each post
                    List<Comment> comments = commentService.findByPostID(post.getPostID());

                    // Map comments to CommentDTO objects
                    List<CommentDTO> commentDTOs = comments.stream()
                            .map(comment -> {
                                // Create CommentDTO object
                                return new CommentDTO(
                                        comment.getCommentID(),
                                        comment.getCommentBody(), commentService.getCreator(comment)
                                );
                            })
                            .collect(Collectors.toList());

                    // Create PostDTO object
                    return new PostDTO(
                            post.getPostID(),
                            post.getPostBody(),
                            post.getDate(),
                            commentDTOs
                    );
                })
                .toList();
        // Return response body
        return ResponseEntity.ok(postDTOs);
    }

    @GetMapping("/user")
    public ResponseEntity<Object> getUserDetails(@RequestParam("userID") int userId) {
        UserDTO userDTO = userService.getUserDetails(userId);
        if (userDTO != null) {
            return ResponseEntity.ok(userDTO);
        } else {
            ReturnsError E = new ReturnsError("User does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(E);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserClass user) {
        String email = user.getEmail();
        String password = user.getPassword();

        UserClass user1 = userRepository.findByEmail(email);
        if (user1 == null) {
            ReturnsError E = new ReturnsError("User does not exist");
            return ResponseEntity.badRequest().body(E);
        }

        if (!user1.getPassword().equals(password)) {
            ReturnsError E = new ReturnsError("Username/Password Incorrect");
            return ResponseEntity.badRequest().body(E);
        }

        return ResponseEntity.ok("Login Successful");
    }
}
