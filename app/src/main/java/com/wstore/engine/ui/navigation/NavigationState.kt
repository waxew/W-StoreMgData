package com.wstore.engine.ui.navigation

// وضعیت فعلی Navigation برنامه.
// این لایه فقط وضعیت حرکت بین صفحات را نگهداری می‌کند.
// منطق کسب و کار در این بخش قرار نمی‌گیرد.

class NavigationState {
    var currentRoute: String = ScreenRoutes.DASHBOARD
}
