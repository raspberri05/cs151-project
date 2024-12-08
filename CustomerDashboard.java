import javax.swing.*;


import java.awt.*;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CustomerDashboard extends JFrame {

    private ArrayList<MenuItem> menu;
    private ArrayList<MenuItem> cart = new ArrayList<MenuItem>();
    private DefaultListModel<String> menuListModel;
    private DefaultListModel<String> cartListModel;
    private DefaultListModel<String> billListModel;
    private JSlider tipSlider = new JSlider(JSlider.HORIZONTAL, 0, 25, 0);
    private JLabel tipLabel = new JLabel("Tip: 0%");


    public CustomerDashboard(JFrame parent, User curr) {
        super("Customer Dashboard");
        setLayout(new BorderLayout());
        setSize(800, 600);
        setLocationRelativeTo(parent);

        MenuManager menuManager = new MenuManager();
        menu = (ArrayList<MenuItem>) menuManager.getMenu().stream().filter(item -> item.isActive()).collect(Collectors.toList());

        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel userInfoLabel = new JLabel(
                curr.getFirstName() + " " + curr.getLastName() + " (" + curr.getUserName() + ")");
        JButton logoutButton = new JButton("Log Out");

        logoutButton.addActionListener(e -> {
            dispose();
        });

        topPanel.add(userInfoLabel, BorderLayout.WEST);
        topPanel.add(logoutButton, BorderLayout.EAST);

        JPanel mainPanel = new JPanel(new GridLayout(1, 2));

        billListModel = new DefaultListModel<>();
        JList<String> billList = new JList<>(billListModel);
        JScrollPane billScrollPane = new JScrollPane(billList);

        JLabel billLabel = new JLabel("Bill");
        JPanel billPanel = new JPanel(new BorderLayout());

        billPanel.add(billLabel, BorderLayout.NORTH);
        billPanel.add(billScrollPane, BorderLayout.CENTER);

        cartListModel = new DefaultListModel<>();
        JList<String> cartList = new JList<>(cartListModel);
        JScrollPane cartScrollPane = new JScrollPane(cartList);

        JLabel cartLabel = new JLabel("Cart");

        JPanel cartPanel = new JPanel(new BorderLayout());

        cartPanel.add(cartLabel, BorderLayout.NORTH);
        cartPanel.add(cartScrollPane, BorderLayout.CENTER);
        cartPanel.add(billPanel, BorderLayout.SOUTH);

        menuListModel = new DefaultListModel<>();
        JList<String> menuList = new JList<>(menuListModel);
        JScrollPane menuScrollPane = new JScrollPane(menuList);
        updateMenuListModel();

        JPanel menuPanel = new JPanel(new BorderLayout());
        JLabel menuLabel = new JLabel("Menu");
        JButton addButton = new JButton("Add to Cart");

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

        addButton.addActionListener(e -> {
            String selectedItem = menuList.getSelectedValue();
            if (selectedItem != null) {
                String itemID = getItemID(selectedItem);
                MenuItem item = menuManager.getMenuObject(itemID);
                cart.add(item);
                updateCartListModel();
            } else {
                JOptionPane.showMessageDialog(null, "No Item Selected", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        menuPanel.add(menuLabel, BorderLayout.NORTH);
        menuPanel.add(menuScrollPane, BorderLayout.CENTER);
        menuPanel.add(addButton, BorderLayout.SOUTH);

        mainPanel.add(cartPanel);
        mainPanel.add(menuPanel);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        JButton orderButton = new JButton("Order");
        JButton cancelButton = new JButton("Cancel");

        tipSlider.addChangeListener(e -> {
            tipLabel.setText("Tip: " + tipSlider.getValue() + "%");
            if (cart.size() > 0) {
                double subtotal = 0;
                for (MenuItem item : cart) {
                    subtotal += item.getPrice();
                }
                updateBill(subtotal);
            }
        });

        cancelButton.addActionListener(e -> {
            cart.clear();
            updateCartListModel();
        });

        JPanel tipPanel = new JPanel(new BorderLayout());
        tipPanel.add(tipSlider, BorderLayout.CENTER);
        tipPanel.add(tipLabel, BorderLayout.NORTH);
        tipPanel.add(cancelButton, BorderLayout.EAST);

        bottomPanel.add(tipPanel, BorderLayout.WEST);
        bottomPanel.add(orderButton, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void updateMenuListModel() {
        menuListModel.clear();
        for (MenuItem item : menu) {
            menuListModel.addElement("(" + item.getItemID() + ") " + item.getTitle() + " - " + item.getPrice());
        }
    }

    public void updateCartListModel() {
        cartListModel.clear();
        double subtotal = 0;
        for (MenuItem item : cart) {
            cartListModel.addElement("(" + item.getItemID() + ") " + item.getTitle() + " - " + item.getPrice());
            subtotal += item.getPrice();
        }
        updateBill(subtotal);

    }

    private String getItemID(String item) {
        return item.split("\\)")[0].substring(1);
    }

    private void updateBill(double subtotal) {
        double tax = subtotal * 0.0925;
        double tip = (tipSlider.getValue() / 100.0) * subtotal;
        double total = subtotal + tax + tip;
        billListModel.clear();
        billListModel.addElement("SubTotal: " + String.format("%.2f", subtotal));
        billListModel.addElement("Tax: " + String.format("%.2f", tax));
        billListModel.addElement("Tip " + String.format("%.2f", tip));
        billListModel.addElement("Total: " + String.format("%.2f", total));
    }
}