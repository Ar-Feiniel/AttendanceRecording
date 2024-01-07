package com.fedor.attendancerecording.viewmodel.screens

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.fedor.attendancerecording.R
import com.fedor.attendancerecording.data.repositories.interfaces.RecordRepository
import com.fedor.attendancerecording.viewmodel.CalendarViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate

class ExportViewModel(
    private val recordsRepository: RecordRepository
) : CalendarViewModel() {

    var recordsCount by mutableStateOf(0)

    var dateLabelText by mutableStateOf(getGracefulSelectedMonthYearText())

    val exportFormats: Map<String, Int> = mapOf(
        "xlsx" to R.drawable.export_excel,
        "csv" to R.drawable.export_csv
    )

    fun updateRecordsCount(){
        viewModelScope.launch {
            recordsCount = recordsRepository.getRecordsCountByDate(dateLabelText)
        }
    }

    fun goToPreviousMonth(){
        stepByDate("previous")
        updateRecordsCount()
    }

    fun goToNextMonth(){
        stepByDate("next")
        updateRecordsCount()
    }

    private fun stepByDate(action: String){
        selectedDate = computeDateByOffset(action)
        dateLabelText = getGracefulSelectedMonthYearText()
    }

    private fun computeDateByOffset(action: String): LocalDate {
        val day: Int = selectedDate.dayOfMonth
        var month: Int = selectedDate.monthValue
        var year: Int = selectedDate.year
        when(action){
            "previous" -> {
                if(month -1 <=0){
                    year--
                    month = 12
                }
                else{
                    month--
                }
            }
            "next" -> {
                if(month +1 > 12){
                    year++
                    month = 1
                }
                else{
                    month++
                }
            }
        }
        return LocalDate.of(year, month, day)
    }
}
class FileManager(context: Context){
    val appDataDirName = context.applicationContext.filesDir
}
internal class xlsxGenerator(){

}
internal class csvGenerator(){

}