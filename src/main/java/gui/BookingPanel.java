package gui;

import database.DBConnection;
import model.PackageDeal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * Panel view for managing customer bookings, itineraries, and printable invoices.
 * Supports booking creation with destination matching verification, cancellation, itinerary summaries, and invoice generation.
 * <p>
 * The administrative "Report" action is restricted to accounts whose current role is
 * {@code ADMIN}; non-admin sessions never see or trigger the report control. Role gating
 * follows the same {@code boolean isAdmin} pattern used in {@code FlightPanel}.
 * </p>
 */
public class BookingPanel extends JPanel implements ActionListener {

    private JTextField txtBookFlightId;
    private JTextField txtBookHotelId;
    private JButton btnBook;
    private JButton btnUpdate;
    private JButton btnItinerary;
    private JButton btnInvoice;
    private JButton btnReport;
    private JButton btnCancel;
    private JTable tblBookings;
    private DefaultTableModel bookingModel;
    private MainFrame parent;

    /**
     * Constructs the BookingPanel view and initializes its controls and table.
     * <p>
     * The "Report" button is only created and added to the panel when the
     * logged-in account has the {@code ADMIN} role, using the same
     * {@code boolean isAdmin} gating pattern applied throughout the GUI layer.
     * For all other roles the control is omitted entirely so the administrative
     * report cannot be reached from the Bookings &amp; Itinerary tab.
     * </p>
     *
     * @param parent the parent {@link MainFrame} dashboard container
     */
    public BookingPanel(MainFrame parent) {
        this.parent = parent;
        setLayout(null);
        setBounds(0, 0, 900, 580);

        boolean isAdmin = "ADMIN".equals(parent.getCurrentRole());

        JLabel lblFlightId = new JLabel("Flight ID:");
        lblFlightId.setBounds(10, 10, 60, 25);
        add(lblFlightId);

        txtBookFlightId = new JTextField();
        txtBookFlightId.setBounds(70, 10, 80, 25);
        add(txtBookFlightId);

        JLabel lblHotelId = new JLabel("Hotel ID:");
        lblHotelId.setBounds(160, 10, 60, 25);
        add(lblHotelId);

        txtBookHotelId = new JTextField();
        txtBookHotelId.setBounds(220, 10, 80, 25);
        add(txtBookHotelId);

        btnBook = new JButton("Book Package");
        btnBook.setBounds(315, 10, 125, 25);
        btnBook.addActionListener(this);
        add(btnBook);

        btnUpdate = new JButton("Update Selected");
        btnUpdate.setBounds(445, 10, 120, 25);
        btnUpdate.addActionListener(this);
        add(btnUpdate);

        btnItinerary = new JButton("View Itinerary");
        btnItinerary.setBounds(575, 10, 120, 25);
        btnItinerary.addActionListener(this);
        add(btnItinerary);

        btnInvoice = new JButton("Invoice");
        btnInvoice.setBounds(705, 10, 75, 25);
        btnInvoice.addActionListener(this);
        add(btnInvoice);

        if (isAdmin) {
            btnReport = new JButton("Report");
            btnReport.setBounds(785, 10, 90, 25);
            btnReport.addActionListener(this);
            add(btnReport);
        }

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(10, 45, 90, 25);
        btnCancel.addActionListener(this);
        add(btnCancel);

        bookingModel = new DefaultTableModel(new String[]{"Booking ID", "Customer", "Flight ID", "Hotel ID", "Total Price"}, 0);
        tblBookings = new JTable(bookingModel);
        tblBookings.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && tblBookings.getSelectedRow() != -1) {
                int row = tblBookings.getSelectedRow();
                setFieldValue(txtBookFlightId, bookingModel.getValueAt(row, 2));
                setFieldValue(txtBookHotelId, bookingModel.getValueAt(row, 3));
            }
        });
        JScrollPane sp = new JScrollPane(tblBookings);
        sp.setBounds(10, 75, 875, 490);
        add(sp);

        loadBookings();
    }

    /**
     * Handles action events for booking creation, itinerary display, invoice generation, and cancellation.
     *
     * @param e the ActionEvent triggered
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBook) {
            performBooking();
        } else if (e.getSource() == btnUpdate) {
            updateSelectedBooking();
        } else if (e.getSource() == btnItinerary) {
            generateItinerary();
        } else if (e.getSource() == btnInvoice) {
            generateInvoice();
        } else if (btnReport != null && e.getSource() == btnReport) {
            generateSystemReport();
        } else if (e.getSource() == btnCancel) {
            cancelBooking();
        }
    }

    /**
     * Creates a new booking in the database based on the provided flight and hotel IDs.
     * Verifies that the flight arrival destination and hotel location match, displaying a warning if there is a mismatch.
     */
    private void performBooking() {
        String fId = txtBookFlightId.getText().trim();
        String hId = txtBookHotelId.getText().trim();

        if (fId.isEmpty() && hId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter at least a Flight ID or Hotel ID.");
            return;
        }

        double flightPrice = 0;
        double hotelPrice = 0;
        String flightArrival = null;
        String hotelLocation = null;

        try (Connection conn = DBConnection.getConnection()) {
            if (!fId.isEmpty()) {
                PreparedStatement ps = conn.prepareStatement("SELECT arrival, price FROM flights WHERE flight_id = ?");
                ps.setString(1, fId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    flightArrival = rs.getString("arrival");
                    flightPrice = rs.getDouble("price");
                } else {
                    JOptionPane.showMessageDialog(this, "Flight ID '" + fId + "' not found.", "Invalid Flight ID", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            if (!hId.isEmpty()) {
                PreparedStatement ps = conn.prepareStatement("SELECT location, price_per_night FROM hotels WHERE hotel_id = ?");
                ps.setString(1, hId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    hotelLocation = rs.getString("location");
                    hotelPrice = rs.getDouble("price_per_night");
                } else {
                    JOptionPane.showMessageDialog(this, "Hotel ID '" + hId + "' not found.", "Invalid Hotel ID", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            if (!fId.isEmpty() && !hId.isEmpty() && flightArrival != null && hotelLocation != null) {
                if (!PackageDeal.isDestinationMatch(flightArrival, hotelLocation)) {
                    JOptionPane.showMessageDialog(this,
                            "Destination mismatch! Flight arrival ('" + flightArrival + "') does not match hotel location ('" + hotelLocation + "').",
                            "Location Mismatch Warning",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            double total = flightPrice + hotelPrice;

            PreparedStatement psInsert = conn.prepareStatement("INSERT INTO bookings(username, flight_id, hotel_id, total_price) VALUES(?, ?, ?, ?)");
            psInsert.setString(1, parent.getCurrentUser());
            psInsert.setString(2, fId.isEmpty() ? null : fId);
            psInsert.setString(3, hId.isEmpty() ? null : hId);
            psInsert.setDouble(4, total);
            psInsert.executeUpdate();

            txtBookFlightId.setText("");
            txtBookHotelId.setText("");

            JOptionPane.showMessageDialog(this, "Booking Successful!");
            loadBookings();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Booking Failed: " + ex.getMessage());
        }
    }

    /**
     * Loads booking records from the database into the JTable.
     * Administrators see all user bookings, whereas customers see only their own bookings.
     */
    public void loadBookings() {
        bookingModel.setRowCount(0);
        try (Connection conn = DBConnection.getConnection()) {
            String role = parent.getCurrentRole();
            String user = parent.getCurrentUser();
            String query = "ADMIN".equals(role) ? "SELECT * FROM bookings" : "SELECT * FROM bookings WHERE username = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            if (!"ADMIN".equals(role)) {
                ps.setString(1, user);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                bookingModel.addRow(new Object[]{
                        rs.getInt("booking_id"),
                        rs.getString("username"),
                        rs.getString("flight_id"),
                        rs.getString("hotel_id"),
                        rs.getDouble("total_price")
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error loading bookings: " + ex.getMessage());
        }
    }

    /**
     * Updates the selected booking and recalculates its total from current
     * flight and hotel prices.
     */
    private void updateSelectedBooking() {
        int selectedRow = tblBookings.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a booking to update.");
            return;
        }

        String flightId = txtBookFlightId.getText().trim();
        String hotelId = txtBookHotelId.getText().trim();
        if (flightId.isEmpty() && hotelId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "A booking must contain a flight or hotel.");
            return;
        }

        try (Connection conn = DBConnection.getConnection()) {
            int bookingId = Integer.parseInt(bookingModel.getValueAt(selectedRow, 0).toString());
            double total = calculateBookingTotal(conn, flightId, hotelId);
            try (PreparedStatement ps = conn.prepareStatement(
                    "UPDATE bookings SET flight_id = ?, hotel_id = ?, total_price = ? WHERE booking_id = ?")) {
                ps.setString(1, flightId.isEmpty() ? null : flightId);
                ps.setString(2, hotelId.isEmpty() ? null : hotelId);
                ps.setDouble(3, total);
                ps.setInt(4, bookingId);
                ps.executeUpdate();
            }
            loadBookings();
            JOptionPane.showMessageDialog(this, "Booking updated successfully.");
        } catch (SQLException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error updating booking: " + ex.getMessage());
        }
    }

    private double calculateBookingTotal(Connection conn, String flightId, String hotelId) throws SQLException {
        double total = 0;
        String arrival = null;
        String location = null;
        if (!flightId.isEmpty()) {
            try (PreparedStatement ps = conn.prepareStatement(
                    "SELECT arrival, price FROM flights WHERE flight_id = ?")) {
                ps.setString(1, flightId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) {
                        throw new SQLException("Flight ID '" + flightId + "' was not found.");
                    }
                    arrival = rs.getString("arrival");
                    total += rs.getDouble("price");
                }
            }
        }
        if (!hotelId.isEmpty()) {
            try (PreparedStatement ps = conn.prepareStatement(
                    "SELECT location, price_per_night FROM hotels WHERE hotel_id = ?")) {
                ps.setString(1, hotelId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) {
                        throw new SQLException("Hotel ID '" + hotelId + "' was not found.");
                    }
                    location = rs.getString("location");
                    total += rs.getDouble("price_per_night");
                }
            }
        }
        if (arrival != null && location != null && !PackageDeal.isDestinationMatch(arrival, location)) {
            throw new SQLException("Flight arrival and hotel location do not match.");
        }
        return total;
    }

    private void setFieldValue(JTextField field, Object value) {
        field.setText(value == null ? "" : value.toString());
    }

    /**
     * Generates and displays a detailed travel itinerary for the selected booking entry.
     */
    private void generateItinerary() {
        int selectedRow = tblBookings.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a booking from the table to view its itinerary.");
            return;
        }

        Object bId = bookingModel.getValueAt(selectedRow, 0);
        Object fId = bookingModel.getValueAt(selectedRow, 2);
        Object hId = bookingModel.getValueAt(selectedRow, 3);

        StringBuilder itinerary = new StringBuilder("=== TRAVEL ITINERARY ===\n");
        itinerary.append("Booking ID: ").append(bId).append("\n\n");

        try (Connection conn = DBConnection.getConnection()) {
            if (fId != null) {
                PreparedStatement psF = conn.prepareStatement("SELECT * FROM flights WHERE flight_id = ?");
                psF.setString(1, fId.toString());
                ResultSet rsF = psF.executeQuery();
                if (rsF.next()) {
                    itinerary.append("[FLIGHT DETAILS]\n")
                            .append("Flight ID: ").append(rsF.getString("flight_id")).append("\n")
                            .append("From: ").append(rsF.getString("departure")).append(" (").append(rsF.getString("departure_time")).append(" HRS)\n")
                            .append("To: ").append(rsF.getString("arrival")).append(" (").append(rsF.getString("arrival_time")).append(" HRS)\n\n");
                }
            } else {
                itinerary.append("[FLIGHT DETAILS]: No flight booked.\n\n");
            }

            if (hId != null) {
                PreparedStatement psH = conn.prepareStatement("SELECT * FROM hotels WHERE hotel_id = ?");
                psH.setString(1, hId.toString());
                ResultSet rsH = psH.executeQuery();
                if (rsH.next()) {
                    itinerary.append("[HOTEL DETAILS]\n")
                            .append("Hotel: ").append(rsH.getString("name")).append("\n")
                            .append("Location: ").append(rsH.getString("location")).append("\n")
                            .append("Rating: ").append(rsH.getDouble("rating")).append(" Stars\n\n");
                }
            } else {
                itinerary.append("[HOTEL DETAILS]: No hotel booked.\n\n");
            }

            JOptionPane.showMessageDialog(this, itinerary.toString(), "Itinerary Summary", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error building itinerary: " + ex.getMessage());
        }
    }

    /**
     * Generates and displays a text invoice dialog for the selected booking entry.
     */
    private void generateInvoice() {
        int selectedRow = tblBookings.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a booking from the table.");
            return;
        }

        Object bId = bookingModel.getValueAt(selectedRow, 0);
        Object user = bookingModel.getValueAt(selectedRow, 1);
        Object fId = bookingModel.getValueAt(selectedRow, 2);
        Object hId = bookingModel.getValueAt(selectedRow, 3);
        Object total = bookingModel.getValueAt(selectedRow, 4);

        String invoice = "====================================\n"
                + "          TRAVEL INVOICE            \n"
                + "====================================\n"
                + "Booking ID: " + bId + "\n"
                + "Customer: " + user + "\n"
                + "Flight Booked: " + (fId == null ? "None" : fId) + "\n"
                + "Hotel Booked: " + (hId == null ? "None" : hId) + "\n"
                + "------------------------------------\n"
                + "TOTAL AMOUNT: $" + total + "\n"
                + "====================================";

        JOptionPane.showMessageDialog(this, invoice, "Invoice Details", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Generates and displays an executive administrative activity and revenue report.
     * <p>
     * Defensive role re-check: even if the control were somehow reached by a
     * non-admin session, the report is refused before touching the service layer.
     * </p>
     */
    private void generateSystemReport() {
        if (!"ADMIN".equals(parent.getCurrentRole())) {
            JOptionPane.showMessageDialog(this, "Access denied. Only administrators may generate system reports.",
                    "Access Denied", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            service.BookingManagementService bookingService = new service.BookingManagementService();
            String reportText = bookingService.generateSystemSummaryReport();

            JTextArea txtReport = new JTextArea(reportText);
            txtReport.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12));
            txtReport.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(txtReport);
            scrollPane.setPreferredSize(new java.awt.Dimension(500, 350));

            JOptionPane.showMessageDialog(this, scrollPane, "System Activity & Revenue Report", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error generating report: " + ex.getMessage());
        }
    }

    /**
     * Cancels the selected booking record from the database upon user confirmation.
     */
    private void cancelBooking() {
        int selectedRow = tblBookings.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a booking to cancel.");
            return;
        }

        int bookingId = (int) bookingModel.getValueAt(selectedRow, 0);

        int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to cancel booking #" + bookingId + "?", "Confirm Cancellation", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement ps = conn.prepareStatement("DELETE FROM bookings WHERE booking_id = ?")) {
                ps.setInt(1, bookingId);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Booking cancelled successfully.");
                loadBookings();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error cancelling booking: " + ex.getMessage());
            }
        }
    }
}