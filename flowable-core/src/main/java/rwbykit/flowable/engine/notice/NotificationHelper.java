package rwbykit.flowable.engine.notice;

import rwbykit.flowable.engine.Notification;
import rwbykit.flowable.model.Listener;
import rwbykit.flowableTemp.core.util.Beans;
import rwbykit.flowableTemp.core.util.CollectionHelper;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class NotificationHelper {

    private final static Map<String, Notification<?>> NOTIFICATION_MAP = new ConcurrentHashMap<>(8);

    public final static Notification<?> getNotification(String className) {
        return NOTIFICATION_MAP.computeIfAbsent(className, (type) -> Beans.newInstance(className));
    }

    public final static List<Notification<?>> getNotifications(List<String> classNames) {
        return CollectionHelper.nonEmpty(classNames) ?
                classNames.stream().map(NotificationHelper::getNotification).collect(Collectors.toList()) :
                Collections.emptyList();
    }

    public final static <T> List<Notification<T>> getNotificationsByType(List<Listener> listeners) {
        return CollectionHelper.nonEmpty(listeners) ?
                listeners.stream()
                        .map(Listener::getClassType)
                        .map(NotificationHelper::getNotification)
                        .map(notification -> (Notification<T>) notification)
                        .collect(Collectors.toList()) :
                Collections.emptyList();
    }

}
