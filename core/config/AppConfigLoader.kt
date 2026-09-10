package core.config

/**
 * مسئولیت:
 * بارگذاری تنظیمات مرکزی برنامه از فایل AppConfig.
 * این لایه باعث می‌شود اطلاعات ثابت برنامه داخل کدهای مختلف تکرار نشود.
 */
class AppConfigLoader {

    fun load(): AppConfig {
        // در این مرحله قرارداد Loader ایجاد شده است.
        // اتصال نهایی به منبع JSON در مرحله Data Source اضافه می‌شود.
        return AppConfig()
    }
}
