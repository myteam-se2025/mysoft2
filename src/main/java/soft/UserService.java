package soft;

import java.sql.SQLException;

public class UserService {

 
    
    private UserDAO userdao ;
    public UserService() throws SQLException {
        userdao = new UserDAO();
    }
    public void registerUser(User user) {
        userdao.addUser(user);
    }



    
}
