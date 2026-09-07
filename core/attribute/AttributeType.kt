/*
================================================
نام فایل:
AttributeType.kt

بخش:
Dynamic Attribute Engine

وظیفه:
تعریف انواع داده برای ویژگی های متغیر کسب و کار.

این ویژگی ها داخل Core Entity ذخیره نمی شوند.
از طریق Business Profile تعریف و فعال می شوند.

================================================
*/

package com.wstoremgdata.core.attribute

/**
 * انواع داده قابل استفاده برای ویژگی های پویا
 */
enum class AttributeType {
    TEXT,
    NUMBER,
    BOOLEAN,
    DATE,
    SELECT
}
