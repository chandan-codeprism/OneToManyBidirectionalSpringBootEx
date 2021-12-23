package in.name.chandan.service.impl;

import in.name.chandan.entity.Comment;
import in.name.chandan.entity.User;
import in.name.chandan.repo.UserRepository;
import in.name.chandan.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public String saveUser(User user) {
        User userSave = userRepository.save(user);
        return "User is Saved with userId: " + userSave.getId();
    }

    @Override
    public String updateUser(User user) {
        String resp = "";
        if (userRepository.existsById(user.getId())) {

            List<Comment> com = userRepository.findById(user.getId()).get().getComments();
            user.setComments(com);
            Integer id = userRepository.save(user).getId();
            resp = "User updated with Id: " + id + " with comment Id: " + user.getComments();
            userRepository.save(user);
            resp = "user with id: " + user.getId() + " Updated";
        } else {
            resp = "User with id: " + user.getId() + " Does not exist";
        }
        return resp;
    }

    @Override
    public User getOneUser(Integer id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);

    }
}
