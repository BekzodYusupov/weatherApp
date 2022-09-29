package uz.gita.weatherapp.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.weatherapp.R
import uz.gita.weatherapp.data.model.forecast.ForecastResponseData
import uz.gita.weatherapp.databinding.ScreenGraphBinding
import uz.gita.weatherapp.viewModel.GraphViewModel
import uz.gita.weatherapp.viewModel.impl.GraphViewModelImpl
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class GraphScreen : Fragment(R.layout.screen_graph) {
    private val viewBinding: ScreenGraphBinding by viewBinding(ScreenGraphBinding::bind)
    private val viewModel: GraphViewModel by viewModels<GraphViewModelImpl>()
//    private lateinit var graphXYvalues: ArrayList<Entry>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("oooo", "GraphScreen:.....${viewModel.chartLiveData.value}")
        viewBinding.btnSearch.setOnClickListener {
            val country = viewBinding.countryInput.text.toString()
            viewModel.getData(country.lowercase(Locale.getDefault()))
        }
        viewModel.chartLiveData.observe(viewLifecycleOwner) {

            val lineDataSet = LineDataSet(loadGraphXYvalues(it), "Weather data 1")
/*      this is to draw multiple lines since we want only one, we ain't need it
        val dataSets:ArrayList<ILineDataSet> = arrayListOf(lineDataSet)*/
            val lineData = LineData(lineDataSet)
            viewBinding.lineChart.data = lineData
            //to update graph
            viewBinding.lineChart.invalidate()
        }


    }

    private fun loadGraphXYvalues(forecast: ForecastResponseData): ArrayList<Entry> {
        val arraylistXY: ArrayList<Entry> = ArrayList()
        val sdf = SimpleDateFormat("dd")
        val currentTimeInLong = Calendar.getInstance().time.time
        val currentDateInFloat = sdf.format(currentTimeInLong).toFloat()

        for (i in 0 .. 5) {
            val time = forecast.list[i].dt * 1000L
            val date = sdf.format(Date(time)).toFloat()
            val temp = (forecast.list[i].main.temp - 273.15).toInt().toFloat()
                arraylistXY.add(Entry((i+12).toFloat(), temp))
        }

        return arraylistXY
    }
}