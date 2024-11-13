import java.io.Serializable;
import java.util.List;

public interface User extends Comparable<User>, Serializable {
	String getFirstName();
    String getLastName();
    String getEmail();
    String getUserName();
    String getPassword();
    boolean isActive();
    List<String> getOrderedItems();
    String getRole();  // e.g., "Admin" or "Customer"
    void orderItems(MenuItem item) throws CustomExceptions.ItemNotAvailableException;
    void setActive(boolean active);
    void setOrderedItems(List<String> orderedItems);
    void setUserName(String userName);
    void cancelItem(MenuItem item);   
    boolean canPlace();
    String getDetails();
    
    default boolean isAdmin() {
        return "Admin".equals(getRole());
    }
    
    String toDataString(); // Convert the object to a string for saving to a file
    static Admin fromDataString(String data) {
        // Implement a basic structure, each subclass will have its own logic
        throw new UnsupportedOperationException("fromDataString() must be implemented in the subclass.");
    }
}
