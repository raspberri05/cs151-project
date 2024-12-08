public interface MenuItem {
    String getTitle();

    String getItemID();

    String getDescription();

    float getPrice();

    int getCount();

    boolean isAvailable();

    String toDataString();

    String getMenuType();

    boolean isActive();

    void setActive(boolean active);
}
