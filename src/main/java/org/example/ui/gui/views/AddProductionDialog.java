package org.example.ui.gui.views;

import org.example.production.Production;

import javax.swing.*;

public class AddProductionDialog extends JPanel {
    public AddProductionDialog() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
        JLabel titleLabel = new JLabel("Title: ");
        JTextField titleField = new JTextField();
        titlePanel.add(titleLabel);
        titlePanel.add(titleField);

        JPanel typePanel = new JPanel();
        typePanel.setLayout(new BoxLayout(typePanel, BoxLayout.X_AXIS));
        JLabel typeLabel = new JLabel("Type: ");
        JComboBox<String> typeBox = new JComboBox<>(new String[]{"Movie", "Series"});
        typePanel.add(typeLabel);
        typePanel.add(typeBox);

        JPanel directorsPanel = new JPanel();
        directorsPanel.setLayout(new BoxLayout(directorsPanel, BoxLayout.X_AXIS));
        JLabel directorsLabel = new JLabel("Directors: ");
        JTextField directorsField = new JTextField();
        directorsPanel.add(directorsLabel);
        directorsPanel.add(directorsField);

        JPanel actorsPanel = new JPanel();
        actorsPanel.setLayout(new BoxLayout(actorsPanel, BoxLayout.X_AXIS));
        JLabel actorsLabel = new JLabel("Actors: ");
        JTextField actorsField = new JTextField();
        actorsPanel.add(actorsLabel);
        actorsPanel.add(actorsField);

        JPanel genresPanel = new JPanel();
        genresPanel.setLayout(new BoxLayout(genresPanel, BoxLayout.X_AXIS));
        JLabel genresLabel = new JLabel("Genres: ");
        JTextField genresField = new JTextField();
        genresPanel.add(genresLabel);
        genresPanel.add(genresField);

        JPanel ratingsPanel = new JPanel();
        ratingsPanel.setLayout(new BoxLayout(ratingsPanel, BoxLayout.X_AXIS));
        JLabel ratingsLabel = new JLabel("Ratings: ");
        JTextField ratingsField = new JTextField();
        ratingsPanel.add(ratingsLabel);
        ratingsPanel.add(ratingsField);

        JPanel plotPanel = new JPanel();
        plotPanel.setLayout(new BoxLayout(plotPanel, BoxLayout.X_AXIS));
        JLabel plotLabel = new JLabel("Plot: ");
        JTextField plotField = new JTextField();
        plotPanel.add(plotLabel);
        plotPanel.add(plotField);

        JPanel durationPanel = new JPanel();
        durationPanel.setLayout(new BoxLayout(durationPanel, BoxLayout.X_AXIS));
        JLabel durationLabel = new JLabel("Duration: ");
        JTextField durationField = new JTextField();
        durationPanel.add(durationLabel);
        durationPanel.add(durationField);

        JPanel releaseYearPanel = new JPanel();
        releaseYearPanel.setLayout(new BoxLayout(releaseYearPanel, BoxLayout.X_AXIS));
        JLabel releaseYearLabel = new JLabel("Release year: ");
        JTextField releaseYearField = new JTextField();
        releaseYearPanel.add(releaseYearLabel);
        releaseYearPanel.add(releaseYearField);

        JPanel numSeasonsPanel = new JPanel();
        numSeasonsPanel.setLayout(new BoxLayout(numSeasonsPanel, BoxLayout.X_AXIS));
        JLabel numSeasonsLabel = new JLabel("Number of seasons: ");
        JTextField numSeasonsField = new JTextField();
        numSeasonsPanel.add(numSeasonsLabel);
        numSeasonsPanel.add(numSeasonsField);

        add(titlePanel);
        add(typePanel);
        add(directorsPanel);
        add(actorsPanel);
        add(genresPanel);
        add(ratingsPanel);
        add(plotPanel);
        add(durationPanel);
        add(releaseYearPanel);
        add(numSeasonsPanel);
    }
}
