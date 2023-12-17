package com.fedor.attendancerecording

import androidx.navigation.NavType
import androidx.navigation.navArgument

val applicationBottomBarScreens = listOf(MainCalendarDestination, StudentsDestination, MarkersDestination, ScheduleDestination)

interface ApplicationDestination {
    val route: String
    val iconResId: Int?
    val screenNameResId: Int
}

object MainCalendarDestination : ApplicationDestination{
    override val route = "mainCalendar"
    override val iconResId = R.drawable.calendar_month
    override val screenNameResId = R.string.calendar
}
object RecordingDestination : ApplicationDestination{
    override val route = "recording"
    override val iconResId = null
    override val screenNameResId = R.string.calendar
    val navArgumentName: String = "selected_date"
    val routeWithArguments: String = "${route}/{${navArgumentName}}"
    val arguments = listOf(
        navArgument(navArgumentName) { type = NavType.StringType}
    )
}
object StudentsDestination : ApplicationDestination{
    override val route = "students"
    override val iconResId = R.drawable.group
    override val screenNameResId = R.string.students

}
object EditStudentDestination : ApplicationDestination{
    override val route = "edit_student"
    override val iconResId = null
    override val screenNameResId = R.string.edit_student
    val navArgumentName: String = "student_id"
    val routeWithArguments: String = "${route}/{${navArgumentName}}"
    val arguments = listOf(
        navArgument(navArgumentName) { type = NavType.IntType}
    )
}
object MarkersDestination : ApplicationDestination{
    override val route = "markers"
    override val iconResId = R.drawable.marker
    override val screenNameResId = R.string.markers

}
object ScheduleDestination : ApplicationDestination{
    override val route = "schedule"
    override val iconResId = R.drawable.edit_calendar
    override val screenNameResId = R.string.schedule

}
object ExportDestination : ApplicationDestination{
    override val route = "export"
    override val iconResId = R.drawable.export
    override val screenNameResId = R.string.export

}
object SettingsDestination : ApplicationDestination{
    override val route = "settings"
    override val iconResId = R.drawable.settings
    override val screenNameResId = R.string.settings

}
