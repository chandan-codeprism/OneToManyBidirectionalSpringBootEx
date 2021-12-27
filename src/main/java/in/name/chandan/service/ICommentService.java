package in.name.chandan.service;

import in.name.chandan.entity.Comment;

import java.util.List;


public interface ICommentService {

    String saveComment(Comment comment);

    String updateComment(Comment comment);

    Comment getOneComment(Integer id);

    List<Comment> getAllComment();

    void deleteComment(Integer id);

    String addMultipleComment(List<Comment> comments);

}
