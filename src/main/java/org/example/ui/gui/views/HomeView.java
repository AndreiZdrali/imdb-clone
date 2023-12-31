package org.example.ui.gui.views;

import org.example.IMDB;
import org.example.production.Production;
import org.example.services.ProductionService;
import org.example.user.User;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HomeView extends JPanel {
    JLabel welcomeLabel;
    JPanel recommendationsPanel;
    JLabel recommendationsLabel;
    JList<String> recommendationsList;

    public HomeView() {
        User<?> user = IMDB.getInstance().getUserInterface().getCurrentUser();

        setLayout(new BorderLayout());

        //welcome label with username
        welcomeLabel = new JLabel("Welcome back, " + user.getUsername() + "!");
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomeLabel.setVerticalAlignment(JLabel.CENTER);
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        //recommendations panel
        recommendationsPanel = new JPanel(new GridLayout(2, 1));
        recommendationsPanel.setPreferredSize(new Dimension(0, 200));
        recommendationsPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));

        //recommendations label
        recommendationsLabel = new JLabel("Today's Recommendations");
        recommendationsLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        recommendationsLabel.setHorizontalAlignment(JLabel.CENTER);
        recommendationsLabel.setVerticalAlignment(JLabel.CENTER);

        //recommendations list
        recommendationsList = new JList<>();
        recommendationsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        recommendationsList.setLayoutOrientation(JList.VERTICAL);
        recommendationsList.setVisibleRowCount(5);
        recommendationsList.setFixedCellHeight(30);
        recommendationsList.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        recommendationsList.setFont(new Font("Arial", Font.PLAIN, 14));

        //sort productions by rating
        List<Production> productions = ProductionService.getProductions().stream().sorted((p1, p2) -> {
            if (p1.getAverageRating() > p2.getAverageRating()) {
                return -1;
            } else if (p1.getAverageRating() < p2.getAverageRating()) {
                return 1;
            } else {
                return 0;
            }
        }).toList().subList(0, 5);
        recommendationsList.setListData(productions.stream().map(Production::getTitle).toArray(String[]::new));

        JScrollPane recommendationsScrollPane = new JScrollPane(recommendationsList);

        //add components to recommendations panel
        recommendationsPanel.add(recommendationsLabel);
        recommendationsPanel.add(recommendationsScrollPane);

        add(welcomeLabel, BorderLayout.NORTH);
        add(recommendationsPanel, BorderLayout.CENTER);

    }
}
