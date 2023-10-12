package com.mongo_modules.mongo_sort.ent;

import lombok.Getter;

public enum EventType {
    USER_CREATED("Создание пользователя '%s'"),
    USER_EDITED("Редактирование пользователя '%s'"),
    USER_DELETED("Удаление пользователя '%s'"),
    USER_BLOCKED("Пользователь заблокирован '%s'"),

    GROUP_CREATED("Создание группы '%s'"),
    GROUP_EDITED("Редактирование группы '%s'"),
    GROUP_DELETED("Удаление группы '%s'"),

    EVENT_TYPE_CREATED("Тип события '%s' создан"),
    EVENT_TYPE_EDITED("Тип события '%s' изменен"),
    EVENT_TYPE_DELETED("Тип события '%s' удален"),

    CATEGORY_CREATED("Создание категории '%s'"),
    CATEGORY_EDITED("Редактирование категории '%s'"),
    CATEGORY_DELETED("Удаление категории '%s'"),

    LEAD_ORGANIZATION_CREATED("Создание руководящей организация '%s'"),
    LEAD_ORGANIZATION_EDITED("Редактирование руководящей организации '%s'"),
    LEAD_ORGANIZATION_DELETED("Удаление руководящей организации '%s'"),

    SITE_PROPERTY_CREATED("Свойство сайтов '%s' создано"),
    SITE_PROPERTY_EDITED("Свойство сайтов '%s' изменено"),

    DOMAIN_MAPPING_CREATED("Соответствие сайтов '%s' создано"),
    DOMAIN_MAPPING_EDITED("Соответствие сайтов '%s' изменено"),
    DOMAIN_MAPPING_DELETED("Соответствие сайтов '%s' удалено"),

    SITE_GROUP_CREATED("Создание группы на странице сайтов '%s'"),
    SITE_GROUP_EDITED("Редактирование группы на странице сайтов '%s'"),
    SITE_GROUP_DELETED("Удаление группы на странице сайтов '%s'"),

    SEGMENT_CREATED("Сегмент '%s' создан"),
    SEGMENT_EDITED("Сегмент '%s' изменен"),
    SEGMENT_DELETED("Сегмент '%s' удален"),

    ROBOTS_READ("Робот '%s' читал"),

    JOURNAL_DELETED("Журнал очищен %s"),
    JOURNAL_EXPORTED("Выгрузка журнала %s"),
    JOURNAL_EXPORT_ERROR("Ошибка при выгрузке журнала %s"),

    LOGIN("Вход в систему%s"),
    LOGOUT("Выход из системы%s"),

    HOST_GROUP_SETTINGS_EDITED("Хост группа изменена на '%s'"),

    ACCESS_DENIED("Доступ запрещен: %s"),

    ACCESS_RIGHTS_CHANGED("Полномочия изменены %s"),

    DIAGRAM_TYPE_CHANGED("Тип диаграммы изменен %s"),

    CACHE_EVICTED("Кэш сброшен '%s'"),

    REPORT_CREATED("Отчет %s создан"),
    REPORT_BUILT("Отчет %s построен"),
    REPORT_DELETED("Отчет %s удален"),
    REPORT_BUILT_ERROR("Ошибка %s при построении отчета"),

    EVENT_CHAIN_CREATED("Создание цепочки событий '%s'"),
    EVENT_CHAIN_EDITED("Редактирование цепочки событий '%s'"),
    EVENT_CHAIN_DELETED("Удаление цепочки событий '%s'"),

    EVENT_CHAIN_CALCULATE("Цепочка событий '%s' расчитана"),
    EVENT_CHAIN_CALCULATE_ERROR("Ошибка при расчете цепочки событий '%s'"),

    SERVICE_MODE_ENABLED("Сервисный режим включен %s"),
    SERVICE_MODE_DISABLED("Сервисный режим выключен %s"),

    SEGMENT_COMPUTE_EVENT("Поиск похожих для сегмента %s");

    @Getter
    private final String title;

    EventType(String title) {
        this.title = title;
    }
}
