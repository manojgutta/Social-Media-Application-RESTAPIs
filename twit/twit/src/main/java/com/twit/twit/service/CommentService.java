package com.twit.twit.service;

import com.twit.twit.model.*;
import com.twit.twit.repositories.CommentRepository;
import com.twit.twit.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;

    public void createComment(String commentBody, int postID, UserClass user) {
        Comment comment = new Comment();
        comment.setCommentBody(commentBody);
        // Set the post for which the comment is created
        comment.setPostID(postID);
        // Set the user who created the comment
        comment.setUserID(user.getUserID());
        commentRepository.save(comment);
    }

    public CommentDTO getCommentByID(int commentId) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if (comment != null) {
            return convertToDTO(comment);
        } else {
            return null;
        }
    }

    private CommentDTO convertToDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setCommentID(comment.getCommentID());
        commentDTO.setCommentBody(comment.getCommentBody());
        commentDTO.setCommentCreator(getCreator(comment));
        return commentDTO;
    }

    public boolean editComment(int commentID, String newCommentBody) {
        Comment comment = commentRepository.findById(commentID).orElse(null);
        if (comment == null) {
            return false;
        }
        comment.setCommentBody(newCommentBody);
        commentRepository.save(comment);
        return true;
    }

    public boolean deleteComment(int commentID) {
        if (!commentRepository.existsById(commentID)) {
            return false;
        }
        commentRepository.deleteById(commentID);
        return true;
    }

    public CommentCreator getCreator(Comment comment)
    {
        UserClass user = userRepository.findByuserID(comment.getUserID());
        if (user != null) {
            CommentCreator commentCreator = new CommentCreator();
            commentCreator.setName(user.getName());
            commentCreator.setUserID(user.getUserID());
            return commentCreator;
        } else {
            return null;
        }
    }

    public List<Comment> findByPostID(int postID) {
        List <Comment> c = commentRepository.findAll();
        List <Comment> comments = new ArrayList<Comment>();
        for (Comment comment : c) {
            if (comment.getPostID() == postID) {
                comments.add(comment);
            }
        }
        return comments;
    }
}
