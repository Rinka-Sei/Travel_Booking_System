/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jorel_ipqm0jc
 */

package com.mycompany.travel_booking_system;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class packagedeal extends JFrame {

    public packagedeal() {
        setTitle("Travel Booking System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // --- Top Header Panel ---
        JPanel topPanel = new JPanel(new BorderLayout(5, 5));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 5, 15));

        JLabel userLabel = new JLabel("Welcome, admin [ADMIN]");
        
        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setFocusable(false);

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.add(userLabel, BorderLayout.WEST);
        headerPanel.add(logoutBtn, BorderLayout.EAST);

        // --- Navigation Tabs ---
        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Flights", new JPanel());
        tabbedPane.addTab("Hotels", new JPanel());

        JPanel packageDealsPanel = createPackageDealsPanel();
        tabbedPane.addTab("Package Deals (QuickSort)", packageDealsPanel);
        tabbedPane.setSelectedComponent(packageDealsPanel);

        tabbedPane.addTab("Bookings & Itinerary", new JPanel());

        topPanel.add(headerPanel, BorderLayout.NORTH);
        topPanel.add(tabbedPane, BorderLayout.CENTER);

        add(topPanel, BorderLayout.CENTER);
    }

    private JPanel createPackageDealsPanel() {
        JPanel mainContainer = new JPanel(new BorderLayout(10, 10));
        mainContainer.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10),
                BorderFactory.createLineBorder(new Color(130, 155, 185), 1)
        ));

        // --- Sorting Buttons Top Bar with Soft Background ---
        JPanel sortControlsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 12));
        sortControlsPanel.setBackground(new Color(240, 244, 248));
        sortControlsPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(205, 215, 225)));

        JButton sortByPriceBtn = new JButton("QuickSort by Price (Low to High)");
        JButton sortByRatingBtn = new JButton("QuickSort by Feature (Rating)");

        sortByPriceBtn.setFocusable(false);
        sortByRatingBtn.setFocusable(false);

        sortControlsPanel.add(sortByPriceBtn);
        sortControlsPanel.add(sortByRatingBtn);

        // --- Table Setup ---
        String[] columnNames = {
            "Flight ID", "Flight Price", "Hotel Name", "Hotel Rating", "Total Package Price"
        };

        // Initial 30 placeholder rows
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 30) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };

        // Zebra Striping renderer for subtle row color depth
        JTable packageTable = new JTable(tableModel) {
            @Override
            public Component prepareRenderer(javax.swing.table.TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(247, 250, 253));
                }
                return c;
            }
        };

        packageTable.setRowHeight(25);
        packageTable.getTableHeader().setReorderingAllowed(false);
        packageTable.setFillsViewportHeight(true);

        // Styled Table Header with explicitly visible White text on Dark Blue background
        JTableHeader header = packageTable.getTableHeader();
        header.setBackground(new Color(51, 102, 153));
        header.setForeground(Color.WHITE);
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(new Color(51, 102, 153));
                c.setForeground(Color.WHITE);
                setFont(getFont().deriveFont(Font.BOLD));
                setHorizontalAlignment(JLabel.CENTER);
                return c;
            }
        });

        // Grid lines styling
        packageTable.setShowGrid(true);
        packageTable.setShowHorizontalLines(true);
        packageTable.setShowVerticalLines(true);
        packageTable.setGridColor(new Color(210, 220, 230));

        // Center align table content except for Hotel Name
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < packageTable.getColumnCount(); i++) {
            if (i != 2) {
                packageTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
        }

        // Scroll pane wrapping the table
        JScrollPane tableScrollPane = new JScrollPane(packageTable);
        tableScrollPane.setBorder(BorderFactory.createLineBorder(new Color(170, 185, 200), 1));

        // Infinite Placeholder Scroll Listener
        JScrollBar verticalBar = tableScrollPane.getVerticalScrollBar();
        verticalBar.addAdjustmentListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int maximum = verticalBar.getMaximum();
                int value = verticalBar.getValue();
                int extent = verticalBar.getVisibleAmount();
                
                if (value + extent >= maximum - 30) {
                    for (int i = 0; i < 20; i++) {
                        tableModel.addRow(new Object[5]);
                    }
                }
            }
        });

        mainContainer.add(sortControlsPanel, BorderLayout.NORTH);
        mainContainer.add(tableScrollPane, BorderLayout.CENTER);

        return mainContainer;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new packagedeal().setVisible(true);
        });
    }
}