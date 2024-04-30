package com.twit.twit.controllers;

import com.twit.twit.model.Comment;
import com.twit.twit.model.CommentDTO;
import com.twit.twit.model.ReturnsError;
import com.twit.twit.model.UserClass;
import com.twit.twit.service.CommentService;
import com.twit.twit.service.PostService;
import com.twit.twit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> createComment(@RequestBody Comment comment) {
        // Check if the post exists
        int postID = comment.getPostID();
        if (!postService.existsById(postID)) {
            ReturnsError e = new ReturnsError("Post does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        }

        // Check if the user exists
        int userID = comment.getUserID();
        UserClass user = userService.findByuserID(userID);
        if (user == null) {
            ReturnsError e = new ReturnsError("User does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        }

        // Create the comment
        commentService.createComment(comment.getCommentBody(), postID, user);

        return ResponseEntity.status(HttpStatus.CREATED).body("Comment created successfully");
    }

    @GetMapping
    public ResponseEntity<Object> getComment(@RequestParam("commentID") int commentID) {
        CommentDTO comment= commentService.getCommentByID(commentID);
        if (comment == null) {
            ReturnsError e = new ReturnsError("Comment does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        }
        return ResponseEntity.ok(comment);
    }

    @PatchMapping
    public ResponseEntity<?> editComment(@RequestBody Comment comment) {
        int commentID = comment.getCommentID();
        String commentBody = comment.getCommentBody();

        boolean success = commentService.editComment(commentID, commentBody);
        if (!success) {
            ReturnsError e = new ReturnsError("Comment does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        }
        return ResponseEntity.ok("Comment edited successfully");
    }

    @DeleteMapping
    public ResponseEntity<?> deleteComment(@RequestParam("commentID") int commentID) {
        boolean success = commentService.deleteComment(commentID);
        if (!success) {
            ReturnsError e = new ReturnsError("Comment does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        }
        return ResponseEntity.ok("Comment deleted");
    }

    public static class CommentIdPayload {
        private int commentID;

        public int getCommentID() {
            return commentID;
        }

        public void setCommentID(int commentID) {
            this.commentID = commentID;
        }
    }
}
