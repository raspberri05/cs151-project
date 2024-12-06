import java.util.Map;

public class UserManager {
	private cafe DB;
	public Map<String, User> users;
	public UserManager() {
		DB = cafe.getInstance();
		users = DB.getUsers();
    }

	public void addUser(User user) {
		DB.addUser(user);
	}

	public void deleteUser(String userName) {
		DB.deleteUser(userName);
	}

	public void toggleActivation(User user) {
		User existing = users.get(user.getUserName());
		existing.setActive(!existing.isActive());
		DB.updateUser(existing);

	}

	public User login(String username, String password) {
		User user = users.get(username);
		if (user != null && user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}

	public Map<String, User> getUsers() {
		return DB.getUsers();
	}
}
