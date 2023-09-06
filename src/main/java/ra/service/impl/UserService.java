package ra.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.dao.IUserDao;
import ra.model.User;
import ra.service.IUserService;

import javax.servlet.http.HttpSession;
@Service
public class UserService implements IUserService {
    @Autowired
    private IUserDao userDao;
    @Override
    public User login(String username, String password , HttpSession session) {
        User user = userDao.login(username,password);
        // lưu lên session
        if (user!=null){
            session.setAttribute("userlogin",user);
        }
        return user;
    }

    @Override
    public void logout(HttpSession session) {
        session.removeAttribute("userlogin");
    }
}
