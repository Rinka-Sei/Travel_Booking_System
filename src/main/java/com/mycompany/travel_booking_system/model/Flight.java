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
        setTitle("Travel Booking System");
        setSize(850,600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        lblWelcome = new JLabel("Welcome! ");
        lblWelcome.setBounds(25, 15, 200, 25);
        add(lblWelcome);
        
        btnLogOut = new JButton("Logout");
        btnLogOut.setBounds(720, 15, 90, 25);
        add(btnLogOut);
        
        tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(15, 50, 805, 490);
        
        flightsPanel = new JPanel();
        flightsPanel.setLayout(null);
        
        lblFlightId = new JLabel("Flight ID:");
        lblFlightId.setBounds(15, 20, 60, 25);
        flightsPanel.add(lblFlightId);
        
        txtFlightId = new JTextField();
        txtFlightId.setBounds(75, 20, 80, 25);
        flightsPanel.add(txtFlightId);
        
        lblDeparture = new JLabel("Departure:");
        lblDeparture.setBounds(175, 20, 70, 25);
        flightsPanel.add(lblDeparture);

        txtDeparture = new JTextField();
        txtDeparture.setBounds(245, 20, 140, 25);
        flightsPanel.add(txtDeparture);

        lblArrival = new JLabel("Arrival:");
        lblArrival.setBounds(405, 20, 60, 25);
        flightsPanel.add(lblArrival);

        txtArrival = new JTextField();
        txtArrival.setBounds(455, 20, 140, 25);
        flightsPanel.add(txtArrival);

        lblTime = new JLabel("Time (HHMM):");
        lblTime.setBounds(15, 55, 90, 25);
        flightsPanel.add(lblTime);

        txtTime = new JTextField();
        txtTime.setBounds(105, 55, 50, 25);
        flightsPanel.add(txtTime);

        lblPrice = new JLabel("Price:");
        lblPrice.setBounds(175, 55, 50, 25);
        flightsPanel.add(lblPrice);

        txtPrice = new JTextField();
        txtPrice.setBounds(245, 55, 140, 25);
        flightsPanel.add(txtPrice);
        
        btnAddFlight = new JButton("Add Flight");
        btnAddFlight.setBounds(405, 55, 110, 25);
        flightsPanel.add(btnAddFlight);

        btnLoad = new JButton("Load (BST Sorted)");
        btnLoad.setBounds(525, 55, 150, 25);
        flightsPanel.add(btnLoad);
        
        lblSearch = new JLabel("Search Destination:");
        lblSearch.setBounds(15, 95, 130, 25);
        flightsPanel.add(lblSearch);

        txtSearch = new JTextField();
        txtSearch.setBounds(145, 95, 180, 25);
        flightsPanel.add(txtSearch);

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
        
        tabbedPane.addTab("Flights", flightsPanel);
        tabbedPane.addTab("Hotels", new JPanel());
        tabbedPane.addTab("Package Deals (QuickSort)", new JPanel());
        tabbedPane.addTab("Bookings & Itinerary", new JPanel());
        add(tabbedPane);
        
        btnLogOut.addActionListener(this);
        btnAddFlight.addActionListener(this);
        btnLoad.addActionListener(this);

        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         if (e.getSource()== btnAddFlight) {
            String id = txtFlightId.getText().trim();
            String dep = txtDeparture.getText().trim();
            String arr = txtArrival.getText().trim();
            String time = txtTime.getText().trim();
            String price = txtPrice.getText().trim();
              
            if (!id.isEmpty() && !dep.isEmpty() && !arr.isEmpty()) {
                tableModel.addRow(new Object[]{id, dep, arr, time, price});
                txtFlightId.setText(""); 
                txtDeparture.setText(""); 
                txtArrival.setText("");
                txtTime.setText(""); 
                txtPrice.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Please fill in Flight ID, Departure, and Arrival.");
            }
            
    

        };

    }
    

}
