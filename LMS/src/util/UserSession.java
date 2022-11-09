package util;

public class UserSession {
    private static UserSession instance;
    private String username;
    private long userId;
    private int role_id;

    private UserSession() {
    }

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }


    public String getUsername() {
        return username;
    }

    public long getUserId() {
        return userId;
    }

    public int getRole() {
        return role_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setRole(int role_id) {
        this.role_id = role_id;
    }

    public void clearSession() {
        this.username = null;
        this.userId = 0;
        this.role_id = 0;
    }
}
