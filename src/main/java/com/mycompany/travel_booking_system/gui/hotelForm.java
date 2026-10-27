/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.travel_booking_system.gui;

/**
 *
 * @author markj
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class hotelForm extends JFrame{

    // Hotel Management
    private JTextField txtHotelId, txtHotelName, txtLocation;
    private JTextField txtRating, txtHotelPrice;
    private JTextField txtSearchHotel;
    private JTable tblHotels;
    private DefaultTableModel hotelModel;

    public hotelForm() {
        setTitle("Hotel Management");
        setSize(800, 570);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        

        // eto yung title
        JLabel lblTitle = new JLabel("Hotel Management");
        lblTitle.setBounds(20, 15, 300, 30);
        add(lblTitle);

        // nakuha ko sa google, para lang nakagroup yung mga input
        JPanel pnlInput = new JPanel();
        pnlInput.setLayout(null);
        pnlInput.setBorder(
            BorderFactory.createTitledBorder("Hotel Information")
        );
        pnlInput.setBounds(20, 55, 735, 125);
        add(pnlInput);

        // para sa Hotel ID
        JLabel lblId = new JLabel("Hotel ID:");
        lblId.setBounds(15, 25, 70, 25);
        pnlInput.add(lblId);

        txtHotelId = new JTextField();
        txtHotelId.setBounds(85, 25, 110, 28);
        pnlInput.add(txtHotelId);

        // para sa Hotel Name
        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(215, 25, 50, 25);
        pnlInput.add(lblName);

        txtHotelName = new JTextField();
        txtHotelName.setBounds(265, 25, 150, 28);
        pnlInput.add(txtHotelName);

        // para sa Location
        JLabel lblLoc = new JLabel("Location:");
        lblLoc.setBounds(435, 25, 65, 25);
        pnlInput.add(lblLoc);

        txtLocation = new JTextField();
        txtLocation.setBounds(500, 25, 200, 28);
        pnlInput.add(txtLocation);

        // para sa Rating
        JLabel lblRat = new JLabel("Rating:");
        lblRat.setBounds(15, 70, 50, 25);
        pnlInput.add(lblRat);

        txtRating = new JTextField();
        txtRating.setBounds(70, 70, 80, 28);
        pnlInput.add(txtRating);

        // para sa Price per night
        JLabel lblPrice = new JLabel("Price/Night:");
        lblPrice.setBounds(170, 70, 80, 25);
        pnlInput.add(lblPrice);

        txtHotelPrice = new JTextField();
        txtHotelPrice.setBounds(250, 70, 120, 28);
        pnlInput.add(txtHotelPrice);

        // para sa Add Hotel Button
        JButton btnAdd = new JButton("Add Hotel");
        btnAdd.setBounds(400, 70, 130, 30);
        pnlInput.add(btnAdd);

        // eto naman sa Search Section
        JLabel lblSearch = new JLabel("Search Hotels");
        lblSearch.setBounds(20, 195, 200, 25);
        add(lblSearch);
        
        JLabel lblSearchHint = new JLabel("Search by name or location:");
        lblSearchHint.setBounds(20, 225, 190, 25);
        add(lblSearchHint);
        //eto na yung pag seasearchan
        txtSearchHotel = new JTextField();
        txtSearchHotel.setBounds(210, 225, 300, 30);
        add(txtSearchHotel);

        txtSearchHotel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                // Search functionality will be added later.
            }
        });

        // eto na yung table 
        hotelModel = new DefaultTableModel(
            new String[]{
                "ID", "Name", "Location", "Rating", "Price/Night"
            }, 0
        );

        tblHotels = new JTable(hotelModel);
        tblHotels.setRowHeight(28);

        JScrollPane sp = new JScrollPane(tblHotels);
        sp.setBounds(20, 265, 735, 245);
        add(sp);
    }
}