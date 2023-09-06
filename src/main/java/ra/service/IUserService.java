package ra.service;

import ra.model.User;

import javax.servlet.http.HttpSession;

public interface IUserService extends IGenericService<User,Long> {
    User login(String username, String password, HttpSession session);
    void logout(HttpSession session);
}
