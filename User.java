import java.util.List;

public interface User {
    String getFirstName();

    String getLastName();

    String getEmail();

    String getUserName();

    String getPassword();

    boolean isActive();

    List<String> getOrderedItems();

    String getRole();

    String toDataString();

    void setActive(boolean active);
}
