package in.name.chandan.service.impl;

import in.name.chandan.entity.Comment;
import in.name.chandan.entity.User;
import in.name.chandan.repo.CommentRepository;
import in.name.chandan.repo.UserRepository;
import in.name.chandan.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public String saveComment(Comment comment) {
        User usr = comment.getUser();
        comment.setUser(userRepository.findById(usr.getId()).get());
        Integer id = commentRepository.save(comment).getId();
        return "Comment created with Id: " + id + " for User: " + usr.getName();
    }

    @Override
    public String updateComment(Comment comment) {
        String resp = "";
        if (commentRepository.existsById(comment.getId())) {
            User usr = comment.getUser();
            comment.setUser(userRepository.findById(usr.getId()).get());
            Integer id = commentRepository.save(comment).getId();
            resp = "comment updated with Id: " + id + " for userId: " + comment.getUser().getId();
        } else {
            resp = "comment not present with Id: " + comment.getId();
        }
        return resp;
    }

    @Override
    public Comment getOneComment(Integer id) {
        return commentRepository.findById(id).get();
    }

    @Override
    public List<Comment> getAllComment() {
        return commentRepository.findAll();
    }

    @Override
    public void deleteComment(Integer id) {
        commentRepository.deleteById(id);

    }
}
