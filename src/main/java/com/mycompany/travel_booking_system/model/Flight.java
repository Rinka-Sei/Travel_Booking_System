package com.mycompany.travel_booking_system.model;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Flight extends JFrame implements ActionListener{
    private JLabel lblWelcome, lblTime, lblPrice, lblFlightId, lblDeparture, lblArrival, lblSearch;
    private JButton btnLogOut, btnAddFlight, btnLoad;
    private JTextField txtFlightId, txtDeparture, txtArrival, txtTime, txtPrice, txtSearch;
    private JTabbedPane tabbedPane;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;
    private JTable table;
    private JPanel flightsPanel;
    
    
    Flight(){
        //basic gui
        setTitle("Travel Booking System");
        setSize(850,600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //welcome greeting
        lblWelcome = new JLabel("Welcome! ");
        lblWelcome.setBounds(25, 15, 200, 25);
        add(lblWelcome);
        //button for logout
        btnLogOut = new JButton("Logout");
        btnLogOut.setBounds(720, 15, 90, 25);
        add(btnLogOut);
        //for the tabbed pane
        tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(15, 50, 805, 490);
        //for the flight panel
        flightsPanel = new JPanel();
        flightsPanel.setLayout(null);
        //label for flight id
        lblFlightId = new JLabel("Flight ID:");
        lblFlightId.setBounds(15, 20, 60, 25);
        flightsPanel.add(lblFlightId);
        //user input for the flight id
        txtFlightId = new JTextField();
        txtFlightId.setBounds(75, 20, 100, 25);
        flightsPanel.add(txtFlightId);
        //label for departure
        lblDeparture = new JLabel("Departure:");
        lblDeparture.setBounds(195, 20, 70, 25);
        flightsPanel.add(lblDeparture);
        //user input for departure
        txtDeparture = new JTextField();
        txtDeparture.setBounds(270, 20, 120, 25);
        flightsPanel.add(txtDeparture);
        //label for arrival
        lblArrival = new JLabel("Arrival:");
        lblArrival.setBounds(415, 20, 60, 25);
        flightsPanel.add(lblArrival);
        //user input for arrival
        txtArrival = new JTextField();
        txtArrival.setBounds(465, 20, 120, 25);
        flightsPanel.add(txtArrival);
        //label for time
        lblTime = new JLabel("Time (HHMM):");
        lblTime.setBounds(15, 55, 90, 25);
        flightsPanel.add(lblTime);
        //user input for time
        txtTime = new JTextField();
        txtTime.setBounds(105, 55, 100, 25);
        flightsPanel.add(txtTime);
        //label for price
        lblPrice = new JLabel("Price:");
        lblPrice.setBounds(215, 55, 50, 25);
        flightsPanel.add(lblPrice);
        //user input for price
        txtPrice = new JTextField();
        txtPrice.setBounds(270, 55, 120, 25);
        flightsPanel.add(txtPrice);
        //button for adding flight
        btnAddFlight = new JButton("Add Flight");
        btnAddFlight.setBounds(405, 55, 110, 25);
        flightsPanel.add(btnAddFlight);
        //button to load 
        btnLoad = new JButton("Load (BST Sorted)");
        btnLoad.setBounds(335, 95, 150, 25);
        flightsPanel.add(btnLoad);
        //label for search destination
        lblSearch = new JLabel("Search Destination:");
        lblSearch.setBounds(15, 95, 130, 25);
        flightsPanel.add(lblSearch);
        //user input for searching destination
        txtSearch = new JTextField();
        txtSearch.setBounds(145, 95, 180, 25);
        flightsPanel.add(txtSearch);
        //table and its initial values
        String[] columnNames = {"ID", "Departure", "Arrival", "Time", "Price"};
        Object[][] data = {
            {"FL-203", "Manila (MNL)", "Biringan (BRN)", "30", "600.0"},
            {"FL-101", "Manila (MNL)", "Tokyo (NRT)", "630", "280.0"},
            {"FL-102", "Manila (MNL)", "Singapore (SIN)", "815", "150.0"},
            {"FL-103", "Cebu (CEB)", "Seoul (ICN)", "1100", "210.0"},
            {"FL-104", "Manila (MNL)", "Bangkok (BKK)", "1430", "175.5"},
            {"FL-105", "Davao (DVO)", "Manila (MNL)", "1845", "85.0"},
            {"FL-106", "Manila (MNL)", "Sydney (SYD)", "2215", "520.0"}
        };

        tableModel = new DefaultTableModel(data, columnNames);
        table = new JTable(tableModel);

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(15, 135, 770, 310);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        flightsPanel.add(scrollPane);
        //tabbed pane for each category
        tabbedPane.addTab("Flights", flightsPanel);
        tabbedPane.addTab("Hotels", new JPanel());
        tabbedPane.addTab("Package Deals (QuickSort)", new JPanel());
        tabbedPane.addTab("Bookings & Itinerary", new JPanel());
        add(tabbedPane);
        //button action listener for all the buttons used
        btnLogOut.addActionListener(this);
        btnAddFlight.addActionListener(this);
        btnLoad.addActionListener(this);

        
    }
    //basic logic to get user input and actions it needs to execute
    @Override
    public void actionPerformed(ActionEvent e) {
        //if napindot ang add flight
         if (e.getSource()== btnAddFlight) {
             //get user input
            String id = txtFlightId.getText().trim();
            String dep = txtDeparture.getText().trim();
            String arr = txtArrival.getText().trim();
            String time = txtTime.getText().trim();
            String price = txtPrice.getText().trim();
              
            if (!id.isEmpty() && !dep.isEmpty() && !arr.isEmpty() && !time.isEmpty() && !price.isEmpty()) {
                //if all text field is not empty add input values to new table and display 
                tableModel.addRow(new Object[]{id, dep, arr, time, price});
                //set all text field to clear/remove previously inputted text
                txtFlightId.setText(""); 
                txtDeparture.setText(""); 
                txtArrival.setText("");
                txtTime.setText(""); 
                txtPrice.setText("");
            } else {
                //show message if one or more text field is empty when add flight button is clicked
                JOptionPane.showMessageDialog(this, "Please fill in Flight ID, Departure, Arrival, Time, and Price.");
            }
            
    

        };

    }
    

}
