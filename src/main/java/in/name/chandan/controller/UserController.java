package in.name.chandan.controller;

import in.name.chandan.entity.User;
import in.name.chandan.repo.UserRepository;
import in.name.chandan.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private UserRepository userRepository;

    //1. To Save user Data
    @PostMapping("/save")
    public ResponseEntity<String> saveUser(@RequestBody User user) {
        ResponseEntity<String> resp = null;
        try {
            String message = userService.saveUser(user);
            resp = new ResponseEntity<String>(message, HttpStatus.OK);

        } catch (Exception e) {
            resp = new ResponseEntity<String>("Unable to save data", HttpStatus.OK);
            e.printStackTrace();
        }

        return resp;
    }

    //2. to update user data
    @PutMapping("/update")
    public ResponseEntity<String> editUser(@RequestBody User user) {
        ResponseEntity<String> resp = null;
        try {

            String message = userService.updateUser(user);
            resp = new ResponseEntity<String>(message, HttpStatus.OK);
        } catch (Exception e) {
            resp = new ResponseEntity<String>("unable to update", HttpStatus.INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
        return resp;
    }

    // 3. get one user
    @GetMapping("/one/{id}")
    public ResponseEntity<?> getOneUser(@PathVariable Integer id) {
        ResponseEntity<?> resp = null;
        try {
            User usr = userService.getOneUser(id);
            resp = new ResponseEntity<User>(usr, HttpStatus.OK);
        } catch (Exception e) {
            resp = new ResponseEntity<String>("Id not present", HttpStatus.NOT_FOUND);
            e.printStackTrace();
        }
        return resp;
    }

    // 4.get all User
    @GetMapping("/all")
    public ResponseEntity<?> getAllUser() {
        ResponseEntity<?> resp = null;
        try {
            List<User> list = userService.getAllUser();
            if (list != null && !list.isEmpty()) {
                resp = new ResponseEntity<List<User>>(list, HttpStatus.OK);
            } else {
                resp = new ResponseEntity<String>("No data found", HttpStatus.OK);
            }
        } catch (Exception e) {
            resp = new ResponseEntity<String>("Unable to fetch data", HttpStatus.INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
        return resp;
    }

    //5. Delete one User
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        ResponseEntity<String> resp = null;
        try {
            boolean exist = userRepository.existsById(id);
            if (exist) {
                userService.deleteUser(id);
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

    @GetMapping("/{name}")
    public ResponseEntity<?> getUserByName(@PathVariable String name) {
        ResponseEntity<?> resp = null;
        try {
            List<User> list = userService.getUserByName(name);
            if (list != null && !list.isEmpty()) {
                resp = new ResponseEntity<List<User>>(list, HttpStatus.OK);
            } else {
                resp = new ResponseEntity<String>("No data found", HttpStatus.OK);
            }
        } catch (Exception e) {
            resp = new ResponseEntity<String>("Unable to fetch data", HttpStatus.INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
        return resp;
    }
}
