package business_profile.ui.compose

/**
 * اتصال صفحه فرم پویا به Renderer.
 *
 * این کلاس فقط وظیفه هماهنگ کردن Screen و DynamicFormRenderer را دارد
 * و منطق کسب و کار داخل UI قرار نمی‌گیرد.
 */
class DynamicFormScreenBinder {
    fun bind(screenId: String, profileId: String): String {
        return "$profileId:$screenId"
    }
}
