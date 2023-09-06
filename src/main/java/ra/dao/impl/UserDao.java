package ra.dao.impl;

import org.springframework.stereotype.Component;
import ra.dao.IUserDao;
import ra.model.User;
import ra.util.ConnectDB;

import javax.servlet.http.HttpSession;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
@Component

public class UserDao implements IUserDao {
    @Override
    public User login(String username, String password) {
        User  user = null;
        Connection conn = null;
        try {
            conn = ConnectDB.getConnection();
            CallableStatement callSt= conn.prepareCall("SELECT * from Users where username =? and password = ?");
            callSt.setString(1,username);
            callSt.setString(2,password);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()){
                user = new User();
                user.setId(rs.getLong("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }


}
