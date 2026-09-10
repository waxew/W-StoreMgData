package business_profile.ui

/**
 * قرارداد پایه برای Renderer صفحات وابسته به Business Profile.
 */
interface DynamicScreenRendererContract {
    fun render(screenId: String): String
}
