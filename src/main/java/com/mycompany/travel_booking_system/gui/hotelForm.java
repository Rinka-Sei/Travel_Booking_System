package com.mycompany.travel_booking_system.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class hotelForm extends JPanel implements ActionListener {

    // Hotel Management
    private JLabel lblTitle, lblSearch, lblSearchHint;
    private JLabel lblId, lblName, lblLoc, lblRat, lblPrice;
    private JButton btnAdd;
    private JTextField txtHotelId, txtHotelName, txtLocation;
    private JTextField txtRating, txtHotelPrice, txtSearchHotel;
    private JTable tblHotels;
    private DefaultTableModel hotelModel;
    private TableRowSorter<DefaultTableModel> sorter;
    private JPanel pnlInput;
    private JScrollPane sp;

    public hotelForm() {

        // GUI
        setLayout(null);

        // Title
        lblTitle = new JLabel("Hotel Management");
        lblTitle.setBounds(20, 15, 300, 30);
        add(lblTitle);

        // para magkakasama yung hotel inputs
        pnlInput = new JPanel();
        pnlInput.setLayout(null);
        pnlInput.setBorder(
            BorderFactory.createTitledBorder("Hotel Information")
        );
        pnlInput.setBounds(20, 55, 735, 125);
        add(pnlInput);

        // Hotel ID
        lblId = new JLabel("Hotel ID:");
        lblId.setBounds(15, 25, 70, 25);
        pnlInput.add(lblId);

        txtHotelId = new JTextField();
        txtHotelId.setBounds(85, 25, 110, 28);
        pnlInput.add(txtHotelId);

        // Hotel Name
        lblName = new JLabel("Name:");
        lblName.setBounds(215, 25, 50, 25);
        pnlInput.add(lblName);

        txtHotelName = new JTextField();
        txtHotelName.setBounds(265, 25, 150, 28);
        pnlInput.add(txtHotelName);

        // Location
        lblLoc = new JLabel("Location:");
        lblLoc.setBounds(435, 25, 65, 25);
        pnlInput.add(lblLoc);

        txtLocation = new JTextField();
        txtLocation.setBounds(500, 25, 200, 28);
        pnlInput.add(txtLocation);

        // Rating
        lblRat = new JLabel("Rating:");
        lblRat.setBounds(15, 70, 50, 25);
        pnlInput.add(lblRat);

        txtRating = new JTextField();
        txtRating.setBounds(70, 70, 80, 28);
        pnlInput.add(txtRating);

        // Price per Night
        lblPrice = new JLabel("Price/Night:");
        lblPrice.setBounds(170, 70, 80, 25);
        pnlInput.add(lblPrice);

        txtHotelPrice = new JTextField();
        txtHotelPrice.setBounds(250, 70, 120, 28);
        pnlInput.add(txtHotelPrice);

        // Add Hotel Button
        btnAdd = new JButton("Add Hotel");
        btnAdd.setBounds(400, 70, 130, 30);
        pnlInput.add(btnAdd);

        // Search Section
        lblSearch = new JLabel("Search Hotels");
        lblSearch.setBounds(20, 195, 200, 25);
        add(lblSearch);

        lblSearchHint = new JLabel("Search by name or location:");
        lblSearchHint.setBounds(20, 225, 190, 25);
        add(lblSearchHint);

        txtSearchHotel = new JTextField();
        txtSearchHotel.setBounds(210, 225, 300, 30);
        add(txtSearchHotel);

        // Hotel Table
        hotelModel = new DefaultTableModel(
            new String[]{
                "ID", "Name", "Location", "Rating", "Price/Night"
            }, 0
        );

        tblHotels = new JTable(hotelModel);
        tblHotels.setRowHeight(28);

        sorter = new TableRowSorter<>(hotelModel);
        tblHotels.setRowSorter(sorter);

        sp = new JScrollPane(tblHotels);
        sp.setBounds(20, 265, 735, 245);
        add(sp);

        // button actionlistener
        btnAdd.addActionListener(this);

        // Search Listener
        txtSearchHotel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String search = txtSearchHotel.getText().trim();

                sorter.setRowFilter(
                    RowFilter.regexFilter(
                        "(?i)" + java.util.regex.Pattern.quote(search),
                        1, 2
                    )
                );
            }
        });
    }

    // for action listener
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnAdd) {

            String id = txtHotelId.getText().trim();
            String name = txtHotelName.getText().trim();
            String location = txtLocation.getText().trim();
            String rating = txtRating.getText().trim();
            String price = txtHotelPrice.getText().trim();

            if (!id.isEmpty() && !name.isEmpty()
                    && !location.isEmpty()
                    && !rating.isEmpty() && !price.isEmpty()) {

                try {
                    double hotelRating = Double.parseDouble(rating);
                    double hotelPrice = Double.parseDouble(price);

                    hotelModel.addRow(new Object[]{
                        id, name, location, hotelRating, hotelPrice
                    });

                    // para maclear yung input
                    txtHotelId.setText("");
                    txtHotelName.setText("");
                    txtLocation.setText("");
                    txtRating.setText("");
                    txtHotelPrice.setText("");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                        this,
                        "Please enter valid numbers for Rating and Price."
                    );
                }

            } else {
                JOptionPane.showMessageDialog(
                    this,
                    "Please fill in all hotel information."
                );
            }
        }
    }
}
