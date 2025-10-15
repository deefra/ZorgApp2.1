package user;

import java.util.HashMap;

public class UserManager {
    private final HashMap<String, User> users;
    private User currentUser;

    public UserManager() {
        this.users = new HashMap<>();
       addUser(1, "admin", "admin");
       addUser(2, "user1", "user");
       addUser(3, "user2", "user");
       addUser(4, "user3", "user");
       addUser(5, "user4", "user");
    }

    public User getUser(String username) {return users.get(username);}
    public User getCurrentUser() {return currentUser;}

    public void addUser(int id, String username, String password) {
        users.put(username, new User(id, username, password));
    }

    public User login(String username, String password) {
        currentUser = getUser(username);
        if(currentUser != null && currentUser.passwordAuthentication(password)) {
            return currentUser;
        }
        return null;
    }

    public void logout() {
        currentUser = null;
    }

}
