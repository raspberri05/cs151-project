import java.awt.*;
import java.util.Map;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class UserManagerScreen extends JFrame
        implements AdminUserSignupScreen.UserListener, AdminUserEditScreen.UserListener {

    private Map<String, User> customers;
    private DefaultListModel<String> listModel; // Declare listModel as an instance variable

    public UserManagerScreen(JFrame parent) {
        super("Manage Users");
        setLayout(new BorderLayout());
        setSize(735, 446);
        setLocationRelativeTo(parent);

        UserManager userManager = new UserManager();
        customers = userManager.getUsers();

        JPanel panel = new JPanel(new BorderLayout());

        listModel = new DefaultListModel<>();
        updateListModel(userManager, listModel);

        JList<String> userList = new JList<>(listModel);
        userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(userList);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton addButton = new JButton("Add");
        JButton editButton = new JButton("Edit");
        JButton toggleButton = new JButton("Toggle Activation");
        JButton deleteButton = new JButton("Delete");

        MouseListener mouseListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String selectedUser = userList.getSelectedValue();
                    if (selectedUser != null) {
                        String username = getUsername(selectedUser);
                        JOptionPane.showMessageDialog(null, userManager.getUserInfo(username), "User Info",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        };

        userList.addMouseListener(mouseListener);

        editButton.addActionListener(e -> {
            String selectedUser = userList.getSelectedValue();
            if (selectedUser != null) {
                String userName = getUsername(selectedUser);
                User user = customers.get(userName);
                AdminUserEditScreen editScreen = new AdminUserEditScreen(this, user, this);
                editScreen.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "No User Selected", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        deleteButton.addActionListener(e -> {
            String selectedUser = userList.getSelectedValue();
            if (selectedUser != null) {
                JDialog confirmDialog = new JDialog(this, "Confirm Deletion", true);
                confirmDialog.setLayout(new BorderLayout());
                confirmDialog.setLocationRelativeTo(this);

                JLabel confirmLabel = new JLabel("Do you want to delete this user?");
                confirmLabel.setHorizontalAlignment(SwingConstants.CENTER);
                confirmDialog.add(confirmLabel, BorderLayout.CENTER);

                JPanel dialogButtonPanel = new JPanel();
                dialogButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

                JButton confirmButton = new JButton("Yes");
                JButton cancelButton = new JButton("No");

                dialogButtonPanel.add(confirmButton);
                dialogButtonPanel.add(cancelButton);
                confirmDialog.add(dialogButtonPanel, BorderLayout.SOUTH);

                confirmButton.addActionListener(event -> {
                    String userName = getUsername(selectedUser);
                    userManager.deleteUser(userName);
                    listModel.removeElement(selectedUser);
                    confirmDialog.dispose();
                });

                cancelButton.addActionListener(event -> confirmDialog.dispose());

                confirmDialog.setSize(400, 200);
                confirmDialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                confirmDialog.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "No User Selected", "Error", JOptionPane.ERROR_MESSAGE);
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
            } else {
                JOptionPane.showMessageDialog(null, "No User Selected", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        addButton.addActionListener(e -> {
            AdminUserSignupScreen signupScreen = new AdminUserSignupScreen(this, this);
            signupScreen.setVisible(true);
            updateListModel(userManager, listModel);
        });

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(toggleButton);
        buttonPanel.add(deleteButton);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel, BorderLayout.CENTER);
    }

    @Override
    public void userAdded() {
        UserManager userManager = new UserManager();
        updateListModel(userManager, listModel);
    }

    @Override
    public void userEdited() {
        UserManager userManager = new UserManager();
        updateListModel(userManager, listModel);
    }

    private String getUsername(String selectedUser) {
        String[] userParts = selectedUser.split(":");
        String userName = userParts[0].split(",")[0].trim();
        return userName;
    }

    private void updateListModel(UserManager userManager, DefaultListModel<String> listModel) {

        customers = userManager.getUsers();
        listModel.clear();
        for (User u : customers.values()) {
            if (u instanceof Customer) {
                listModel.addElement(u.getUserName() + ", " + u.getLastName() + ", " + u.getFirstName() + ": "
                        + (u.isActive() ? "Active" : "Inactive"));
            }
        }
    }
}