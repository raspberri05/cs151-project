import java.io.BufferedReader;
import java.io.BufferedWriter;
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
		loadMenuFromFile("resources/cafeData.txt");
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
		users.put(user.getUserName(), user);
		updateUsersFile();
	}

	public void addMenuItem(MenuItem item) {
		menu.add(item);
		updateMenuFile();
	}

	public void updateUser(User user) {
		users.put(user.getUserName(), user);
		updateUsersFile();
	}

	public void deleteUser(String userName) {
        if (users.containsKey(userName)) {
            users.remove(userName);
            updateUsersFile();
        }
    }

	private void loadUsersFromFile(String filename) {
		users.clear();
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

	private void loadMenuFromFile(String filename) {
		menu.clear();
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			boolean isMenuSection = false;

			while ((line = br.readLine()) != null) {
				if (line.equals("Menu:")) {
					isMenuSection = true;
				} else if (isMenuSection && !line.isEmpty()) {
					parseMenuItem(line);
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
		List<String> orderedItems = new ArrayList<>();
		for (int i = 7; i < tokens.length; i++) {
			orderedItems.add(tokens[i]);
		}
		if (role.equals("Admin")) {
			Admin admin = new Admin(firstName, lastName, email, userName, password, isActive);
			users.put(userName, admin);
		}
		else {
			Customer customer = new Customer(firstName, lastName, email, userName, password, isActive, orderedItems);
			users.put(userName, customer);
		}
	}

	private void parseMenuItem(String line) {
		String[] tokens = line.split(";");
		String menuType = tokens[0];
		String title = tokens[1];
		String itemID = tokens[2];
		String description = tokens[3];
		float price = Float.parseFloat(tokens[4]);
		int count = Integer.parseInt(tokens[5]);
		boolean isAvailable = Boolean.parseBoolean(tokens[6]);
		if (menuType.equals("Diner")) {
			DinerMenuItem diner = new DinerMenuItem(title, itemID, description, price, count, isAvailable, false);
			menu.add(diner);
		}
		else {
			PancakeMenuItem pancake = new PancakeMenuItem(title, itemID, description, price, count, isAvailable, false);
			menu.add(pancake);
		}

	}

	private void updateUsersFile() {
		String filename = "resources/cafeData.txt";
		List<String> fileContent = new ArrayList<>();
		boolean isUserSection = false;
	
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (line.equals("Users:")) {
					isUserSection = true;
				} else if (isUserSection && line.isEmpty()) {
					isUserSection = false;
				} else if (!isUserSection) {
					fileContent.add(line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
			for (String line : fileContent) {
				bw.write(line + "\n");
			}
			bw.write("Users:\n");
			for (User user : users.values()) {
				bw.write(user.toDataString() + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void updateMenuFile() {
		String filename = "resources/cafeData.txt";
		List<String> fileContent = new ArrayList<>();
		boolean isMenuSection = false;
	
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (line.equals("Menu:")) {
					isMenuSection = true;
				} else if (isMenuSection && line.isEmpty()) {
					isMenuSection = false;
				} else if (!isMenuSection) {
					fileContent.add(line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
			for (String line : fileContent) {
				bw.write(line + "\n");
			}
			bw.write("Menu:\n");
			for (MenuItem item : menu) {
				bw.write(item.toDataString() + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
