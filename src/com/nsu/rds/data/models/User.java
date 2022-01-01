package src.com.nsu.rds.data.models;

public class User {
    private String username;
    private String password;
    private boolean isAdmin;
    private String addedBy;

    public User() {
    }

    public User(String username, String password, boolean isAdmin, String addedBy) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.addedBy = addedBy;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }
}
