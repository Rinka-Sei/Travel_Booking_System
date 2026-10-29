package com.mycompany.travel_booking_system.model;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Booking extends JFrame{
    private JLabel lblWelcome, lblFlightId, lblHotelId;
    private JButton btnLogOut, btnBookPackage, btnViewItinerary, btnInvoice, btnCancel;
    private JTextField txtFlightId, txtHotelId;
    private JTabbedPane tabbedPane;
    private JPanel bookingsPanel;
    private JScrollPane scrollPane;
    private DefaultTableModel tableModel;
    private JTable table;
    
    Booking(){
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
       
        bookingsPanel = new JPanel();
        bookingsPanel.setLayout(null);
        
        lblFlightId = new JLabel("Flight ID:");
        lblFlightId.setBounds(10, 20, 60, 25);
        bookingsPanel.add(lblFlightId);
        
        tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(15, 50, 805, 490);
        
        txtFlightId = new JTextField();
        txtFlightId.setBounds(70, 20, 100, 25);
        bookingsPanel.add(txtFlightId);
        
        lblHotelId = new JLabel("Hotel ID:");
        lblHotelId.setBounds(185, 20, 60, 25);
        bookingsPanel.add(lblHotelId);

        txtHotelId = new JTextField();
        txtHotelId.setBounds(245, 20, 100, 25);
        bookingsPanel.add(txtHotelId);

        btnBookPackage = new JButton("Book Package");
        btnBookPackage.setBounds(365, 20, 120, 25);
        bookingsPanel.add(btnBookPackage);

        btnViewItinerary = new JButton("View Itinerary");
        btnViewItinerary.setBounds(495, 20, 120, 25);
        bookingsPanel.add(btnViewItinerary);

        btnInvoice = new JButton("Invoice");
        btnInvoice.setBounds(630, 20, 75, 25);
        bookingsPanel.add(btnInvoice);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(710, 20, 75, 25);
        bookingsPanel.add(btnCancel);
        
        String [] columnNames = new String []{"Booking ID","Customer","Flight ID","Hotel ID","Total Price"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(15, 60, 770, 330);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        bookingsPanel.add(scrollPane);

        
        tabbedPane.addTab("Flights", new JPanel());
        tabbedPane.addTab("Hotels", new JPanel());
        tabbedPane.addTab("Package Deals (Quick Sort)", new JPanel());
        tabbedPane.addTab("Bookings & Itinerary", bookingsPanel);
        add(tabbedPane);
        //fix user contribution
        tabbedPane.setSelectedIndex(3);
        }
}
