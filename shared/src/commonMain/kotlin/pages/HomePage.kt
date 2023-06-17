package pages

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import components.CalendarView
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ui.theme.AppTheme


@OptIn(ExperimentalResourceApi::class)
@Composable
fun HomePage() {

    var greetingText by remember { mutableStateOf("Hello, World!") }
    var showImage by remember { mutableStateOf(false) }
    // 获取当前日期
    val currentDate: Instant = Clock.System.now()

    val localDateTime = currentDate.toLocalDateTime(TimeZone.currentSystemDefault())
    // 获取当前月份的第一天
    val firstDayOfMonth = LocalDate(localDateTime.year, localDateTime.month, 1)

    // 获取当前月份的总天数
    val totalDaysInMonth = firstDayOfMonth.daysUntil(
        LocalDate(
            localDateTime.year,
            localDateTime.monthNumber + 1,
            1
        )
    )
    println(totalDaysInMonth)
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        AnimatedVisibility(showImage) {
            Image(
                painterResource("compose-multiplatform.xml"),
                null
            )
        }
        CalendarView()
        Box(
            modifier = Modifier.height(3.dp)
        ) {

        }
        Divider(
        )
    }
}