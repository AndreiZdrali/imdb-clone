package org.example.utils;

import org.example.management.NotificationWrapper;

public interface Observer {
    void update(NotificationWrapper notificationWrapper);
}
