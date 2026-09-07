package com.wstore.engine.core.attribute

/**
 * انواع داده قابل استفاده در Attribute Engine.
 * این فایل مستقل از نوع کسب و کار است.
 * کسب و کارها فقط Schema تعریف می‌کنند.
 */
enum class AttributeType {
    TEXT,
    NUMBER,
    DATE,
    BOOLEAN,
    ENUM
}
