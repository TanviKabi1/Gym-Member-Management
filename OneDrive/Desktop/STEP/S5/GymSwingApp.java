import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GymSwingApp {
    private static DefaultTableModel model;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Gym Members");
        frame.setSize(700, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        JTextField txtId = new JTextField();
        JTextField txtName = new JTextField();
        JTextField txtType = new JTextField();
        JTextField txtFees = new JTextField();
        JButton btnAdd = new JButton("Add Member");
        JButton btnUpdate = new JButton("Update Member");
        JButton btnDelete = new JButton("Delete Member");

        inputPanel.add(new JLabel("Member ID:"));
        inputPanel.add(txtId);
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(txtName);
        inputPanel.add(new JLabel("Membership Type:"));
        inputPanel.add(txtType);
        inputPanel.add(new JLabel("Fees:"));
        inputPanel.add(txtFees);
        inputPanel.add(btnAdd);
        inputPanel.add(btnUpdate);
        inputPanel.add(btnDelete);

        frame.add(inputPanel, BorderLayout.NORTH);

        model = new DefaultTableModel();
        JTable table = new JTable(model);
        model.addColumn("Member ID");
        model.addColumn("Name");
        model.addColumn("Membership Type");
        model.addColumn("Fees");

        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        String url = "jdbc:mysql://localhost:3306/test1";
        String user = "root";
        String password = "Saachi@0509";

        loadTableData(url, user, password);

        btnAdd.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                String name = txtName.getText();
                String type = txtType.getText();
                double fees = Double.parseDouble(txtFees.getText());

                Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO GymMembers (member_id, name, membership_type, fees) VALUES (?, ?, ?, ?)"
                );
                ps.setInt(1, id);
                ps.setString(2, name);
                ps.setString(3, type);
                ps.setDouble(4, fees);
                ps.executeUpdate();
                ps.close();
                conn.close();

                JOptionPane.showMessageDialog(frame, "Member added!");
                clearFields(txtId, txtName, txtType, txtFees);
                loadTableData(url, user, password);

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter valid numbers for ID and Fees.");
            }
        });

        btnUpdate.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                String name = txtName.getText();
                String type = txtType.getText();
                double fees = Double.parseDouble(txtFees.getText());

                Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement ps = conn.prepareStatement(
                    "UPDATE GymMembers SET name = ?, membership_type = ?, fees = ? WHERE member_id = ?"
                );
                ps.setString(1, name);
                ps.setString(2, type);
                ps.setDouble(3, fees);
                ps.setInt(4, id);

                int rowsUpdated = ps.executeUpdate();
                ps.close();
                conn.close();

                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(frame, "Member updated successfully!");
                    clearFields(txtId, txtName, txtType, txtFees);
                    loadTableData(url, user, password);
                } else {
                    JOptionPane.showMessageDialog(frame, "No member found with this ID.");
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter valid numbers for ID and Fees.");
            }
        });

        btnDelete.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());

                Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement ps = conn.prepareStatement(
                    "DELETE FROM GymMembers WHERE member_id = ?"
                );
                ps.setInt(1, id);

                int rowsDeleted = ps.executeUpdate();
                ps.close();
                conn.close();

                if (rowsDeleted > 0) {
                    JOptionPane.showMessageDialog(frame, "Member deleted successfully!");
                    clearFields(txtId, txtName, txtType, txtFees);
                    loadTableData(url, user, password);
                } else {
                    JOptionPane.showMessageDialog(frame, "No member found with this ID.");
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid number for Member ID.");
            }
        });

        frame.setVisible(true);
    }

    private static void loadTableData(String url, String user, String password) {
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM GymMembers");

            model.setRowCount(0);

            while (rs.next()) {
                model.addRow(new Object[] {
                    rs.getInt("member_id"),
                    rs.getString("name"),
                    rs.getString("membership_type"),
                    rs.getDouble("fees")
                });
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void clearFields(JTextField... fields) {
        for (JTextField field : fields) {
            field.setText("");
        }
    }
}
