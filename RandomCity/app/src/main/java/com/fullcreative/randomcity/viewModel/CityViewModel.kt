import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.fullcreative.randomcity.dataBase.CityDatabase
import com.fullcreative.randomcity.dataBase.CityEntity
import com.fullcreative.randomcity.models.CityItem
import com.fullcreative.randomcity.ui.navigation.Screen
import com.fullcreative.randomcity.worker.CityToastWorker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
class CityViewModel(context: Context) : ViewModel() {
    private val cities =
        listOf("Gdańsk", "Warszawa", "Poznań", "Białystok", "Wrocław", "Katowice", "Kraków")
    private val colors = listOf("Yellow", "Green", "Blue", "Red", "Black", "Gray")

    private val _items = mutableStateListOf<CityItem>()
    val items: List<CityItem> get() = _items

    private val cityDao = CityDatabase.getInstance(context).cityDao()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val savedCities = cityDao.getAllCities()
            _items.addAll(savedCities.map {
                CityItem(it.city, it.color, LocalDateTime.parse(it.dateTime))
            })
        }

        viewModelScope.launch(Dispatchers.Default) {
            while (true) {
                delay(5000)
                val city = cities.random()
                val color = colors.random()
                val dateTime = LocalDateTime.now()
                val cityItem = CityItem(city, color, dateTime)
                _items.add(cityItem)
                _items.sortBy { it.city }

                viewModelScope.launch(Dispatchers.IO) {
                    cityDao.insertCity(
                        CityEntity(
                            city = city,
                            color = color,
                            dateTime = dateTime.format(DateTimeFormatter.ISO_DATE_TIME)
                        )
                    )
                }
            }
        }
    }

    fun onCityClicked(cityName: String, navController: NavController) {
        navController.navigate(Screen.Details.createRoute(cityName, "colorHex"))
    }
}
