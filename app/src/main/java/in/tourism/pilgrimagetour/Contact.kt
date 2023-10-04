package `in`.tourism.pilgrimagetour

object Contact {

    var contactUrl = ""
    var phoneNumber = ""
    var email = ""
    var address = ""
    var packagesList: ArrayList<TravelPac?> = ArrayList()


    fun setUrl(url: String?) {
        this.contactUrl = url ?: ""
    }

    fun setNumber(phone: String?) {
        this.phoneNumber = phone ?: ""
    }

    fun setMail(email: String?) {
        this.email = email ?: ""
    }

    fun setOfficeAddress(officeAddress: String?) {
        this.address = officeAddress ?: ""
    }

    fun setPackageList(travelPacList: ArrayList<TravelPac?>?) {
        if (travelPacList != null) {
            packagesList = travelPacList
        }
    }


}