package org.example.ui.gui.views;

import kotlin.NotImplementedError;

import javax.swing.*;
import java.awt.*;

public class ProductionsView extends JPanel {
    JPanel leftPanel;
    JPanel rightPanel;

    JTextField searchField;
    JButton searchButton;
    JTable productionsTable;

    public ProductionsView() {
        setLayout(new BorderLayout());

        leftPanel = createLeftPanel();
        rightPanel = createRightPanel();

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
    }

    private JPanel createLeftPanel() {
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setPreferredSize(new Dimension(250, 0));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(190, 30));

        searchButton = new JButton("Search");
        searchButton.setPreferredSize(new Dimension(60, 30));

        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);

        productionsTable = new JTable();
        productionsTable.setPreferredSize(new Dimension(180, 0));

        leftPanel.add(searchPanel, BorderLayout.NORTH);
        leftPanel.add(productionsTable, BorderLayout.CENTER);

        return leftPanel;
    }

    private JPanel createRightPanel() {
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setPreferredSize(new Dimension(650, 0));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        return rightPanel;
    }
}
