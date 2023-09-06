package ra.dao;

import ra.model.User;

import javax.servlet.http.HttpSession;

public interface IUserDao extends IGenericDao<User,Long>{
    User login(String username, String password);

}
