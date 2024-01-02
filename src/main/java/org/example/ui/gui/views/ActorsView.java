package org.example.ui.gui.views;

import kotlin.NotImplementedError;
import org.example.IMDB;
import org.example.production.Actor;
import org.example.services.ActorService;
import org.example.user.Contributor;
import org.example.user.Staff;
import org.example.user.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ActorsView extends JPanel {
    JPanel leftPanel;
    JPanel rightPanel;

    JTextField searchField;
    JButton searchButton;
    JTable actorsTable;
    DefaultTableModel tableModel;
    JTextArea infoArea;
    JButton editActorButton;
    JButton addActorButton;
    JButton deleteActorButton;

    public ActorsView() {
        setLayout(new BorderLayout());

        leftPanel = createLeftPanel();
        rightPanel = createRightPanel();

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
    }

    private JPanel createLeftPanel() {
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setPreferredSize(new Dimension(320, 0));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(260, 30));

        searchButton = new JButton("Search");
        searchButton.setPreferredSize(new Dimension(60, 30));

        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);

        actorsTable = new JTable();
        actorsTable.setDefaultEditor(Object.class, null);
        actorsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("No");
        tableModel.addColumn("Name");

        actorsTable.setModel(tableModel);

        actorsTable.getColumnModel().getColumn(0).setPreferredWidth(20);
        actorsTable.getColumnModel().getColumn(1).setPreferredWidth(260);

        for (Actor actor : ActorService.getActors()) {
            tableModel.addRow(new Object[] {
                    actor.getId(),
                    actor.getName()
            });
        }

        actorsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent evt) {
                tableOnClick(evt);
            }
        });

        leftPanel.add(searchPanel, BorderLayout.NORTH);
        leftPanel.add(new JScrollPane(actorsTable), BorderLayout.CENTER);

        return leftPanel;
    }

    private JPanel createRightPanel() {
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setPreferredSize(new Dimension(650, 0));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        infoArea = new JTextArea("Select an actor to view more information");
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);
        infoArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(infoArea);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        editActorButton = new JButton("Edit");
        addActorButton = new JButton("Add");
        deleteActorButton = new JButton("Delete");

        editActorButton.setEnabled(false);
        deleteActorButton.setEnabled(false);

        User<?> user = IMDB.getInstance().getUserInterface().getCurrentUser();
        switch (user.getUserType()) {
            case Regular -> { }
            case Contributor, Admin -> {
                buttonsPanel.add(editActorButton);
                buttonsPanel.add(addActorButton);
                buttonsPanel.add(deleteActorButton);
            }
            default -> throw new IllegalStateException("Unexpected value: " + user.getUserType());
        }

        deleteActorButton.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to delete this actor?",
                    "Delete actor",
                    JOptionPane.YES_NO_OPTION
            );

            if (result == JOptionPane.YES_OPTION) {
                int row = actorsTable.getSelectedRow();
                String name = (String) actorsTable.getValueAt(row, 1);

                if (!(user instanceof Staff<?> staff))
                    throw new RuntimeException("Dc nu-i staff?");

                staff.removeActorSystem(ActorService.getActorByName(name));
                tableModel.removeRow(actorsTable.getSelectedRow());
            }
        });

        rightPanel.add(scrollPane, BorderLayout.CENTER);
        rightPanel.add(buttonsPanel, BorderLayout.SOUTH);

        return rightPanel;
    }

    private void tableOnClick(MouseEvent evt) {
        int row = actorsTable.rowAtPoint(evt.getPoint());
        int col = actorsTable.columnAtPoint(evt.getPoint());
        if (row < 0 || col < 0)
            return;

        editActorButton.setEnabled(false);
        editActorButton.setEnabled(false);
        deleteActorButton.setEnabled(false);

        Actor actor = ActorService.getActorByName((String) actorsTable.getValueAt(row, 1));
        assert actor != null;
        infoArea.setText(actor.getBiography());

        User<?> user = IMDB.getInstance().getUserInterface().getCurrentUser();
        switch (user.getUserType()) {
            case Regular -> { }
            case Contributor, Admin -> {
                Staff<?> staff = (Staff<?>) user;
                if (staff.getActorsContribution().contains(actor)) {
                    editActorButton.setEnabled(true);
                    deleteActorButton.setEnabled(true);
                }
            }
            default -> throw new IllegalStateException("Unexpected value: " + user.getUserType());
        }
    }
}
