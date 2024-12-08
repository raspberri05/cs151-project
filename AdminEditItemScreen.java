import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminEditItemScreen extends JFrame {
    public interface ItemListener {
        void itemEdited();
    }


    public AdminEditItemScreen(JFrame parent, MenuItem item, ItemListener listener) {
        super("Edit Menu Item");
        MenuManager manageMenu = new MenuManager();
        setLayout(new BorderLayout());
        setSize(600, 700);
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.WEST;

        JLabel titleLabel = new JLabel("Title");
        JLabel descriptionLabel = new JLabel("Description");
        JLabel priceLabel = new JLabel("Price");
        JLabel countLabel = new JLabel("Count");

        JTextField titleField = new JTextField(20);
        JTextField descriptionField = new JTextField(20);
        JTextField priceField = new JTextField(20);
        JTextField countField = new JTextField(20);

        String[] types = {"Pancake", "Diner"};
        JComboBox<String> typeComboBox = new JComboBox<>(types);
        typeComboBox.setPreferredSize(new Dimension(titleField.getPreferredSize()));

        JCheckBox activeCheckBox = new JCheckBox("Active");

        
        JButton submitButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");

        titleField.setText(item.getTitle());
        descriptionField.setText(item.getDescription());
        priceField.setText(Float.toString(item.getPrice()));
        countField.setText(Integer.toString(item.getCount()));
        typeComboBox.setSelectedItem(item.getMenuType());
        activeCheckBox.setSelected(item.isActive());

        
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String type = typeComboBox.getSelectedItem().toString();
                String title = titleField.getText();
                String description = descriptionField.getText();
                float price = Float.parseFloat(priceField.getText());
                int count = Integer.parseInt(countField.getText());
                String itemID = item.getItemID();
                boolean isActive = activeCheckBox.isSelected();
                if (type.equals("Diner")) {
                    DinerMenuItem diner = new DinerMenuItem(title, itemID, description, price, count, isActive);
                    manageMenu.deleteMenuItem(itemID);
                    manageMenu.addMenuItem(diner);
                }
                else {
                    PancakeMenuItem pancake = new PancakeMenuItem(title, itemID, description, price, count, isActive);
                    manageMenu.deleteMenuItem(itemID);
                    manageMenu.addMenuItem(pancake);
                }
                listener.itemEdited();
                dispose();
            }
        });

        panel.add(titleLabel, gbc);
        panel.add(titleField, gbc);
        panel.add(descriptionLabel, gbc);
        panel.add(descriptionField, gbc);
        panel.add(priceLabel, gbc);
        panel.add(priceField, gbc);
        panel.add(countLabel, gbc);
        panel.add(countField, gbc);
        panel.add(typeComboBox, gbc);
        panel.add(activeCheckBox, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
        buttonPanel.add(submitButton);
        buttonPanel.add(cancelButton);

        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(buttonPanel, gbc);

        add(panel, BorderLayout.CENTER);
    }
}
