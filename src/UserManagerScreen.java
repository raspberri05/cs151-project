import java.awt.*;
import java.util.Map;
import javax.swing.*;

public class UserManagerScreen extends JFrame {

    private Map<String, User> customers;

    public UserManagerScreen(JFrame parent) {
        super("Manage Users");
        setLayout(new BorderLayout());
        setSize(735, 446);
        setLocationRelativeTo(parent);

        UserManager userManager = new UserManager();
        customers = userManager.getUsers();

        JPanel panel = new JPanel(new BorderLayout());

        DefaultListModel<String> listModel = new DefaultListModel<>();
        updateListModel(userManager, listModel);

        JList<String> userList = new JList<>(listModel);
        userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(userList);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton addButton = new JButton("Add");
        JButton editButton = new JButton("Edit");
        JButton toggleButton = new JButton("Toggle Activation");
        JButton deleteButton = new JButton("Delete");

        deleteButton.addActionListener(e -> {
            String selectedUser = userList.getSelectedValue();
            if (selectedUser != null) {
                String[] userParts = selectedUser.split(":");
                String userName = userParts[0].split(",")[0].trim();
                userManager.deleteUser(userName);
                listModel.removeElement(selectedUser);
            }
        });

        toggleButton.addActionListener(e -> {
            String selectedUser = userList.getSelectedValue();
            if (selectedUser != null) {
                String[] userParts = selectedUser.split(":");
                String userName = userParts[0].split(",")[0].trim();
                User user = customers.get(userName);
                userManager.toggleActivation(user);
                updateListModel(userManager, listModel);
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(toggleButton);
        buttonPanel.add(deleteButton);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel, BorderLayout.CENTER);
    }

    private void updateListModel(UserManager userManager, DefaultListModel<String> listModel) {
        customers = userManager.getUsers();
        listModel.clear();
        for (User u : customers.values()) {
            if (u instanceof Customer) {
                listModel.addElement(u.getUserName() + ", " + u.getLastName() + ", " + u.getFirstName() + ": " + (u.isActive() ? "Active" : "Inactive"));
            }
        }
    }
}