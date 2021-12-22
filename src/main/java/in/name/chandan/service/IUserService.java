package in.name.chandan.service;

import in.name.chandan.entity.User;

import java.util.List;

public interface IUserService {

    public String saveUser(User user);

    public String updateUser(User user);

    public User getOneUser(Integer id);

    public List<User> getAllUser();

    public void deleteUser(Integer id);


}
