import java.util.ArrayList;
import java.util.List;

public class Admin implements User {	
	private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String password;
    private boolean isActive;

    public Admin(String firstName, String lastName, String email, String userName, String password, boolean isActive) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.isActive = isActive;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public boolean isActive() {
        return isActive;
    }

    public String getRole() {
        return "Admin";
    }

    public List<String> getOrderedItems() {
        return new ArrayList<String>();
    }

    @Override
    public String toDataString() {
        return String.format("%s;%s;%s;%s;%s;%s;%s;", getRole(), firstName, lastName, email, userName, password, isActive);
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }
}
