import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public enum cafe {
	DB; // singleton design

	private List<MenuItem> menu;
	private Map<String, User> users;

	private cafe() { // must be private
		menu = new ArrayList<>();
		users = new HashMap<>();
	}

	public Map<String, User> getUsers() {
		return users;
	}

   		// xxx your codes
}
