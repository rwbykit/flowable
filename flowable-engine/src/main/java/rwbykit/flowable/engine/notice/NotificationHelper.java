package rwbykit.flowable.engine.notice;

import rwbykit.flowable.core.model.Listener;
import rwbykit.flowable.core.util.Collections;
import rwbykit.flowable.core.util.Lists;
import rwbykit.flowable.core.util.Objects;
import rwbykit.flowable.engine.Notification;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class NotificationHelper {

    private final static Map<String, Notification<?>> NOTIFICATION_MAP = new ConcurrentHashMap<>(8);

    public final static Notification<?> getNotification(String className) {
        return NOTIFICATION_MAP.computeIfAbsent(className, (type) -> Objects.newInstance(className));
    }

    public final static List<Notification<?>> getNotifications(List<String> classNames) {
        return Collections.nonEmpty(classNames) ?
                classNames.stream().map(NotificationHelper::getNotification).collect(Collectors.toList()) :
                Lists.emptyList();
    }

    public final static <T> List<Notification<T>> getNotificationsByType(List<Listener> listeners) {
        return Collections.nonEmpty(listeners) ?
                listeners.stream()
                        .map(Listener::getClassType)
                        .map(NotificationHelper::getNotification)
                        .map(notification -> (Notification<T>) notification)
                        .collect(Collectors.toList()) :
                Lists.emptyList();
    }

}
