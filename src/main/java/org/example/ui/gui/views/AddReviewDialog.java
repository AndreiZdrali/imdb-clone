package org.example.ui.gui.views;

import kotlin.NotImplementedError;
import org.example.IMDB;
import org.example.production.Production;
import org.example.production.Rating;
import org.example.user.Regular;
import org.example.user.User;

import javax.swing.*;
import java.awt.*;

public class AddReviewDialog extends JDialog {
    JTextArea reviewArea;
    JSpinner ratingSpinner;

    public AddReviewDialog(Production production) {
        setModalityType(ModalityType.APPLICATION_MODAL);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

        JLabel reviewLabel = new JLabel("Review:");
        reviewLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        reviewArea = new JTextArea();
        reviewArea.setPreferredSize(new Dimension(260, 50));

        JScrollPane scrollPane = new JScrollPane(reviewArea);

        topPanel.add(reviewLabel, BorderLayout.WEST);
        topPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel middlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        middlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel ratingLabel = new JLabel("Rating:");
        ratingLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        ratingSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        ratingSpinner.setSize(new Dimension(60, 30));

        middlePanel.add(ratingLabel);
        middlePanel.add(ratingSpinner);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        JButton submitButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");

        submitButton.addActionListener(e -> {
            User<?> user = IMDB.getInstance().getUserInterface().getCurrentUser();

            if (!(user instanceof Regular<?> regular)) {
                throw new RuntimeException("Only regular users can add reviews!");
            }

            String review = reviewArea.getText();
            int rating = (int) ratingSpinner.getValue();

            if (review.isBlank()) {
                JOptionPane.showMessageDialog(this, "Review cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (rating < 1 || rating > 10) {
                JOptionPane.showMessageDialog(this, "Rating must be between 1 and 10!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            regular.addRating(production, new Rating(user.getUsername(), rating, review));

            dispose();
        });

        cancelButton.addActionListener(e -> {
            dispose();
        });

        bottomPanel.add(submitButton);
        bottomPanel.add(cancelButton);

        add(topPanel, BorderLayout.NORTH);
        add(middlePanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        pack();
    }
}
