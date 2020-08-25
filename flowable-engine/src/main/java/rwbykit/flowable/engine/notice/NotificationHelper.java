package rwbykit.flowable.engine.notice;

import rwbykit.flowable.core.model.parser.Listener;
import rwbykit.flowable.core.util.Collections;
import rwbykit.flowable.core.util.Lists;
import rwbykit.flowable.core.util.Objects;
import rwbykit.flowable.core.Notification;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public final class NotificationHelper {

    private final static Map<String, Notification<?>> NOTIFICATION_MAP = new ConcurrentHashMap<>(8);

    public static Notification<?> getNotification(String className) {
        return NOTIFICATION_MAP.computeIfAbsent(className, (type) -> Objects.newInstance(className));
    }

    public static List<Notification<?>> getNotifications(List<String> classNames) {
        return Collections.nonEmpty(classNames) ?
                classNames.stream().map(NotificationHelper::getNotification).collect(Collectors.toList()) :
                Lists.emptyList();
    }

    @SuppressWarnings("unchecked")
    public static <T> List<Notification<T>> getNotificationsByType(List<Listener> listeners) {
        return Collections.nonEmpty(listeners) ?
                listeners.stream()
                        .map(Listener::getClassType)
                        .map(NotificationHelper::getNotification)
                        .map(notification -> (Notification<T>) notification)
                        .collect(Collectors.toList()) :
                Lists.emptyList();
    }

}
