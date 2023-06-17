package components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime


@Composable
fun CalendarView() {
    // 获取当前日期
    val currentDate: Instant = Clock.System.now()


    val localDate = currentDate.toLocalDateTime(TimeZone.currentSystemDefault()).date
    var selectedDay by remember { mutableStateOf(localDate) }

    // 获取当前月份的第一天
    val firstDayOfMonth = LocalDate(localDate.year, localDate.month, 1)

    // 获取当前月份的总天数
    val totalDaysInMonth =
        firstDayOfMonth.daysUntil(LocalDate(localDate.year, localDate.monthNumber + 1, 1))

    println("firstDayOfMonth.dayOfWeek: ${firstDayOfMonth.dayOfWeek}")
    val firstDayOfWeek = firstDayOfMonth.dayOfWeek.ordinal + 1
    val numRows = ((firstDayOfWeek - 1) + totalDaysInMonth) / 7 + 1
    println("numRows: $numRows")

    // 绘制月历
    Column {
        // 绘制月份标题
        Text(
            text = firstDayOfMonth.month.toString(),
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp)
        )

        // 绘制日期格子
        LazyVerticalGrid(columns = GridCells.Fixed(7)) {
            items(numRows * 7) { day ->
                val dayDiff = day - firstDayOfWeek
                if (dayDiff < 0 || dayDiff >= totalDaysInMonth) {
                    Box() {}
                } else {
                    val date = firstDayOfMonth.plus(dayDiff, DateTimeUnit.DAY)
                    val currentDay = date == localDate
                    Column(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(10))
                            .border(
                                1.dp,
                                //judge is current day
                                if (selectedDay == date
                                ) MaterialTheme.colorScheme.primary else Color.Transparent,
                                RoundedCornerShape(10)
                            )
                            .clickable {
                                selectedDay = date
                            }
                    ) {
                        Box(modifier = Modifier.height(1.dp)) {}
                        Box(
                            modifier = Modifier
                                .widthIn(min = 24.dp)
                                .clip(shape = RoundedCornerShape(35))
                                .background(color = if (currentDay) MaterialTheme.colorScheme.primaryContainer else Color.Transparent)
                                .align(Alignment.CenterHorizontally)
                                .padding(1.dp)
                        ) {
                            Text(
                                text = date.dayOfMonth.toString(),
                                modifier = Modifier.align(Alignment.Center),
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    color = if (currentDay) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onBackground
                                )
                            )
                        }
                        Box(modifier = Modifier.height(44.dp)) {
                        }
                    }

                }
            }
        }
    }
}
