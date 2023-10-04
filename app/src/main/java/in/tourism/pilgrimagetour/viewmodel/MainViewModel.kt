package `in`.tourism.pilgrimagetour.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import `in`.tourism.pilgrimagetour.Destination2
import `in`.tourism.pilgrimagetour.SliderBanner
import `in`.tourism.pilgrimagetour.TravelPac

class MainViewModel : ViewModel() {

    var packagesList: ArrayList<TravelPac?> = ArrayList()

    fun setPackageList(travelPacList: ArrayList<TravelPac?>?) {
        if (travelPacList != null) {

            packagesList = travelPacList
        }
    }

    private val _hotelDestination = MutableLiveData("")

    val hotelDestination: LiveData<String> = _hotelDestination

    fun setHotelDestination(hotelDestination: String) {
        _hotelDestination.value = hotelDestination
    }

    var homeTopSliderBannersList: ArrayList<SliderBanner?> = ArrayList()

    fun setSliderBanners(list: ArrayList<SliderBanner?>?) {

        if (list != null) {

            homeTopSliderBannersList = list
        }
    }

    var packagesDestinationsList: ArrayList<Destination2> = ArrayList()

    fun setPackageDestinationsList(destinationList: ArrayList<Destination2>) {

        packagesDestinationsList = destinationList
    }

    private val _carRental = MutableLiveData("")

    val carRental: LiveData<String> = _carRental

    fun setCarRental(carRental: String) {

        _carRental.value = carRental
    }

}