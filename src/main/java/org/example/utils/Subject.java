package org.example.utils;

import org.example.management.NotificationWrapper;

public interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);

    /** Notifica toti observerii, face null check in caz ca a fost sters utilizatorul */
    void notifyObservers(NotificationWrapper notificationWrapper);
}
