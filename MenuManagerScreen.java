import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuManagerScreen extends JFrame
        implements AdminNewItemScreen.ItemListener, AdminEditItemScreen.ItemListener {

    private ArrayList<MenuItem> menu;
    private DefaultListModel<String> listModel; // Declare listModel as an instance variable

    public MenuManagerScreen(JFrame parent) {
        super("Manage Menu Items");
        setLayout(new BorderLayout());
        setSize(735, 446);
        setLocationRelativeTo(parent);

        MenuManager menuManager = new MenuManager();
        menu = menuManager.getMenu();

        listModel = new DefaultListModel<>();
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

        MouseListener mouseListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String selectedItem = menuList.getSelectedValue();
                    if (selectedItem != null) {
                        String itemID = getItemID(selectedItem);
                        JOptionPane.showMessageDialog(null, menuManager.getMenuInfo(itemID), "Item Info",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        };

        menuList.addMouseListener(mouseListener);

        editButton.addActionListener(e -> {
            String selectedItem = menuList.getSelectedValue();
            if (selectedItem != null) {
                String itemID = getItemID(selectedItem);
                MenuItem item = menuManager.getMenuObject(itemID);
                AdminEditItemScreen editItemScreen = new AdminEditItemScreen(this, item, this);
                editItemScreen.setVisible(true);
                updateListModel(menuManager, listModel);
            } else {
                JOptionPane.showMessageDialog(null, "No Item Selected", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        deleteButton.addActionListener(e -> {
            String selectedItem = menuList.getSelectedValue();
            if (selectedItem != null) {
                JDialog confirmDialog = new JDialog(this, "Confirm Deletion", true);
                confirmDialog.setLayout(new BorderLayout());
                confirmDialog.setLocationRelativeTo(this);

                JLabel confirmLabel = new JLabel("Do you want to delete this Menu Item?");
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
                    String itemID = getItemID(selectedItem);
                    menuManager.deleteMenuItem(itemID);
                    listModel.removeElement(selectedItem);
                    confirmDialog.dispose();
                });

                cancelButton.addActionListener(event -> confirmDialog.dispose());

                confirmDialog.setSize(400, 200);
                confirmDialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                confirmDialog.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "No Item Selected", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        addButton.addActionListener(e -> {
            AdminNewItemScreen newItemScreen = new AdminNewItemScreen(this, this);
            newItemScreen.setVisible(true);
            updateListModel(menuManager, listModel);
        });

        toggleButton.addActionListener(e -> {
            String selectedItem = menuList.getSelectedValue();
            if (selectedItem != null) {
                String itemID = getItemID(selectedItem);
                menuManager.toggleActivation(itemID);
                updateListModel(menuManager, listModel);
            } else {
                JOptionPane.showMessageDialog(null, "No Item Selected", "Error", JOptionPane.ERROR_MESSAGE);
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

    private String getItemID(String item) {
        return item.split(":")[0].trim();
    }

    @Override
    public void itemAdded() {
        MenuManager menuManager = new MenuManager();
        updateListModel(menuManager, listModel);
    }

    @Override
    public void itemEdited() {
        MenuManager menuManager = new MenuManager();
        updateListModel(menuManager, listModel);
    }

    private void updateListModel(MenuManager menuManager, DefaultListModel<String> listModel) {
        menu = menuManager.getMenu();
        listModel.clear();
        for (MenuItem item : menu) {
            listModel.addElement(item.getItemID() + ": " + item.getTitle() + ", " + item.getPrice() + ", "
                    + (item.isActive() ? "Active" : "Inactive"));
        }
    }

}
