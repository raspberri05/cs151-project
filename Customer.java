import java.util.List;

public class Customer implements User {

    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String password;
    private boolean isActive;
    private List<String> orderedItems;

    public Customer(String firstName, String lastName, String email, String userName, String password, boolean isActive, List<String> orderedItems) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.isActive = isActive;
        this.orderedItems = orderedItems;
    }
	
    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public List<String> getOrderedItems() {
        return orderedItems;
    }

    @Override
    public String getRole() {
        return "Customer";
    }

    @Override
    public String toDataString() {
        return String.format("%s;%s;%s;%s;%s;%s;%s;", getRole(), firstName, lastName, email, userName, password, isActive);
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

}
