import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class MenuManagerScreen extends JFrame {

	private  ArrayList<MenuItem> menu;

    public MenuManagerScreen(JFrame parent) {
        super("Manage Menu Items");
        setLayout(new BorderLayout());
        setSize(735, 446);
        setLocationRelativeTo(parent);

        MenuManager menuManager = new MenuManager();
        menu = menuManager.getMenu();

        DefaultListModel<String> listModel = new DefaultListModel<>();
        updateListModel(menuManager, listModel);

        JList<String> menuList = new JList<>(listModel);
        menuList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(menuList);

        JPanel panel = new JPanel(new BorderLayout());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton addButton = new JButton("Add");
        JButton editButton = new JButton("Edit");
        JButton toggleButton = new JButton("Toggle Activation");
        JButton deleteButton = new JButton("Delete");

        addButton.addActionListener(e -> {
            AdminNewItemScreen newItemScreen = new AdminNewItemScreen(this);
            newItemScreen.setVisible(true);
            updateListModel(menuManager, listModel);
        });

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(toggleButton);
        buttonPanel.add(deleteButton);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel, BorderLayout.CENTER);
    }

    private void updateListModel(MenuManager menuManager, DefaultListModel<String> listModel) {
        listModel.clear();
        menu = menuManager.getMenu();
        for (MenuItem item : menu) {
            listModel.addElement(item.getTitle() + ": " + item.getPrice() + ", " + (item.isActive() ? "Active" : "Inactive"));
        }
    }
	
}
