package com.wstore.engine.profile

/**
 * شناسه‌های استاندارد Rendererهای UI که از Business Profile JSON خوانده می‌شوند.
 *
 * نگهداری این شناسه‌ها در یک نقطه از پراکندگی Stringهای ثابت در Feature Screenها جلوگیری می‌کند.
 * Core همچنان نام هیچ کسب‌وکار مشخصی را نمی‌شناسد و فقط Variant انتخاب‌شده توسط Profile را می‌خواند.
 */
object ProfileUiVariants {
    const val MOBILE_DASHBOARD = "mobile_dashboard"
    const val DEVICE_PRODUCT_CARD = "device_product_card"
    const val MOBILE_NAVIGATION = "mobile_navigation"
}
