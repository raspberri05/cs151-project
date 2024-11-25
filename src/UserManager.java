public class UserManager {
	public UserManager() {
    }

	public void addUser(User user) {
		cafe DB = cafe.getInstance();
		DB.addUser(user);
	}
}
