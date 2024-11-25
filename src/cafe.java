import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum cafe {
	DB;

	private List<MenuItem> menu;
	private Map<String, User> users;

	private cafe() {
		menu = new ArrayList<>();
		users = new HashMap<>();
		loadUsersFromFile("resources/cafeData.txt");

	}

	public static cafe getInstance() {
        return DB;
    }

    public Map<String, User> getUsers() {
        return users;
    }

	public List<MenuItem> getMenu() {
		return menu;
	}

	public void addUser(User user) {
		System.out.println(user.getRole());
		appendUserToFile(user, "resources/cafeData.txt");
	}

	private void loadUsersFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			boolean isUserSection = false;

			while ((line = br.readLine()) != null) {
			    if (line.equals("Users:")) {
			        isUserSection = true;
			    } else if (isUserSection && !line.isEmpty()) {
					parseUsers(line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	private void parseUsers(String line) {
		String[] tokens = line.split(";");
		String role = tokens[0];
		String firstName = tokens[1];
		String lastName = tokens[2];
		String email = tokens[3];
		String userName = tokens[4];
		String password = tokens[5];
		boolean isActive = Boolean.parseBoolean(tokens[6]);
		if (role.equals("Admin")) {
			Admin admin = new Admin(firstName, lastName, email, userName, password, isActive);
			users.put(userName, admin);
		}
	}

	private void appendUserToFile(User user, String filename) {
        try (FileWriter fw = new FileWriter(filename, true)) {
            fw.write(user.toDataString() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
