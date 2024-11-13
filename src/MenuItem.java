import java.io.Serializable;
import java.util.Iterator;

public interface MenuItem extends Comparable<MenuItem>, Serializable{
    String getTitle();
    String getItemID();
    String getDescription();
    float getPrice();
    int getCount();
    void setCount(int count);
    
    boolean isAvailable();
    void setAvailable(boolean available);
    
    boolean isCurrent();  // New method
    void setCurrent(boolean current);  // New method
    
    String toDataString();
    void addItem(MenuItem item);
    void removeItem(String itemID);
    Iterator<MenuItem> createIterator();
    
    String getMenuType();
    
}
	