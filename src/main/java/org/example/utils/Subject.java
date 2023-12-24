package org.example.utils;

import org.example.management.NotificationWrapper;

public interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(NotificationWrapper notificationWrapper);
}
