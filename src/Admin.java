import java.util.ArrayList;
import java.util.List;

public class Admin implements User {
	private static final long serialVersionUID = 1L;
	
	private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String password;
    private boolean isActive;
    private List<String> orderedItems;
    private static final int MAX_ORDER_LIMIT = 10;

    // Constructor to initialize a Admin object 
    public Admin(String firstName, String lastName, String email, String userName, String password, boolean isActive) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.isActive = isActive;
        this.orderedItems = new ArrayList<>();
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
        return "Admin";
    }

    @Override
    public void orderItems(MenuItem item) throws CustomExceptions.ItemNotAvailableException {
        if (orderedItems.size() >= MAX_ORDER_LIMIT) {
            throw new CustomExceptions.ItemNotAvailableException("Order limit reached");
        }
        // orderedItems.add(item.getName());
    }

    @Override
    public void setActive(boolean active) {
        this.isActive = active;
    }

    @Override
    public void setOrderedItems(List<String> orderedItems) {
        this.orderedItems = orderedItems;
    }

    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public void cancelItem(MenuItem item) {
        System.out.println("cancelling");
    }


    @Override
    public boolean canPlace() {
        return orderedItems.size() < MAX_ORDER_LIMIT;
    }

    @Override
    public String getDetails() {
        return String.format("Admin;%s;%s;%s;%s;%s;%s;", firstName, lastName, email, userName, password,isActive);
    }

    @Override
    public String toDataString() {
        return String.format("%s;%s;%s;%s;%s;%s;%s;", getRole(), firstName, lastName, email, userName, password, isActive);
    }


    public static Admin fromDataString(String data) {
        String[] parts = data.split(";");
        String firstName = parts[1];
        String lastName = parts[2];
        String email = parts[3];
        String userName = parts[4];
        String password = parts[5];
        boolean isActive = Boolean.parseBoolean(parts[6]);
        return new Admin(firstName, lastName, email, userName, password, isActive);
    }

    @Override
    public int compareTo(User other) {
        return this.email.compareTo(other.getEmail());
    }


    
   // xxx your codes
}
