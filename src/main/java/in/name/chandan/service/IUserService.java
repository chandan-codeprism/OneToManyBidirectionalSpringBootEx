package in.name.chandan.service;

import in.name.chandan.entity.User;

import java.util.List;

public interface IUserService {

    String saveUser(User user);

    String updateUser(User user);

    User getOneUser(Integer id);

    List<User> getAllUser();

    void deleteUser(Integer id);

    List<User> getUserByName(String name);



}
