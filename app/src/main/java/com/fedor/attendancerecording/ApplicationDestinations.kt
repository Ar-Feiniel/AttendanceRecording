package com.fedor.attendancerecording

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer

val applicationBottomBarScreens = listOf(MainCalendarDestination, StudentsDestination, MarkersDestination, ScheduleDestination)

interface ApplicationDestination {
    val route: String
    val iconResId: Int
    val screenNameResId: Int
}

object MainCalendarDestination : ApplicationDestination{
    override val route: String
        get() = TODO("Not yet implemented")
    override val iconResId: Int
        get() = TODO("Not yet implemented")
    override val screenNameResId: Int
        get() = TODO("Not yet implemented")

}
object RecordingDestination : ApplicationDestination{
    override val route: String
        get() = TODO("Not yet implemented")
    override val iconResId: Int
        get() = TODO("Not yet implemented")
    override val screenNameResId: Int
        get() = TODO("Not yet implemented")

}
object StudentsDestination : ApplicationDestination{
    override val route: String
        get() = TODO("Not yet implemented")
    override val iconResId: Int
        get() = TODO("Not yet implemented")
    override val screenNameResId: Int
        get() = TODO("Not yet implemented")

}
object MarkersDestination : ApplicationDestination{
    override val route: String
        get() = TODO("Not yet implemented")
    override val iconResId: Int
        get() = TODO("Not yet implemented")
    override val screenNameResId: Int
        get() = TODO("Not yet implemented")

}
object ScheduleDestination : ApplicationDestination{
    override val route: String
        get() = TODO("Not yet implemented")
    override val iconResId: Int
        get() = TODO("Not yet implemented")
    override val screenNameResId: Int
        get() = TODO("Not yet implemented")

}
object ExportDestination : ApplicationDestination{
    override val route: String
        get() = TODO("Not yet implemented")
    override val iconResId: Int
        get() = TODO("Not yet implemented")
    override val screenNameResId: Int
        get() = TODO("Not yet implemented")

}
object SettingsDestination : ApplicationDestination{
    override val route: String
        get() = TODO("Not yet implemented")
    override val iconResId: Int
        get() = TODO("Not yet implemented")
    override val screenNameResId: Int
        get() = TODO("Not yet implemented")

}
