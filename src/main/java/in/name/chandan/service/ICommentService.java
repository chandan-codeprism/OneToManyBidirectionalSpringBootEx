package in.name.chandan.service;

import in.name.chandan.entity.Comment;

import java.util.List;


public interface ICommentService {

    public String saveComment(Comment comment);

    public String updateComment(Comment comment);

    public Comment getOneComment(Integer id);

    public List<Comment> getAllComment();

    public void deleteComment(Integer id);

}
