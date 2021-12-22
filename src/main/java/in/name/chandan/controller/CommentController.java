package in.name.chandan.controller;

import in.name.chandan.entity.Comment;
import in.name.chandan.repo.CommentRepository;
import in.name.chandan.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private ICommentService commentService;

    @Autowired
    private CommentRepository commentRepository;

    //1. To  add new comment
    @PostMapping("/add")
    public ResponseEntity<String> save(@RequestBody Comment comment) {
        ResponseEntity<String> resp = null;
        try {
            String message = commentService.saveComment(comment);
            resp = new ResponseEntity<String>(message, HttpStatus.OK);

        } catch (Exception e) {
            resp = new ResponseEntity<String>("Unable to save data", HttpStatus.OK);
            e.printStackTrace();
        }

        return resp;
    }

    //2. to update comment
    @PutMapping("/update")
    public ResponseEntity<String> editComment(@RequestBody Comment comment) {
        ResponseEntity<String> resp = null;
        try {

            String message = commentService.updateComment(comment);
            resp = new ResponseEntity<String>(message, HttpStatus.OK);
        } catch (Exception e) {
            resp = new ResponseEntity<String>("unable to update", HttpStatus.INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
        return resp;
    }

    // 3. get comment by id
    @GetMapping("/one/{id}")
    public ResponseEntity<?> getOneComment(@PathVariable Integer id) {
        ResponseEntity<?> resp = null;
        try {
            Comment com = commentService.getOneComment(id);
            resp = new ResponseEntity<Comment>(com, HttpStatus.OK);
        } catch (Exception e) {
            resp = new ResponseEntity<String>("Unable to fetch data", HttpStatus.INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
        return resp;
    }

    // 4.get all comments
    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        ResponseEntity<?> resp = null;
        try {
            List<Comment> list = commentService.getAllComment();
            if (list != null && !list.isEmpty()) {
                resp = new ResponseEntity<List<Comment>>(list, HttpStatus.OK);
            } else {
                resp = new ResponseEntity<String>("No data found", HttpStatus.OK);
            }
        } catch (Exception e) {
            resp = new ResponseEntity<String>("Unable to fetch data", HttpStatus.INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
        return resp;
    }

    //5. Delete one comment by id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Integer id) {
        ResponseEntity<String> resp = null;
        try {
            boolean exist = commentRepository.existsById(id);
            if (exist) {
                commentService.deleteComment(id);
                resp = new ResponseEntity<String>("Successfully " + id + " Removed", HttpStatus.OK);

            } else {
                resp = new ResponseEntity<String>("Failed...Id does not exist", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            resp = new ResponseEntity<String>("Fail...Unable to delete", HttpStatus.INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
        return resp;
    }


}
