package `in`.tourism.pilgrimagetour

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

object ResourceFactory {

    fun packageCollection(db: FirebaseFirestore): CollectionReference {

        return db.collection("package")
    }
    fun contactCollection(db: FirebaseFirestore): CollectionReference {

        return db.collection("contactInfo")
    }
    fun destCollection(db: FirebaseFirestore): CollectionReference {

        return db.collection("destination")
    }

    fun destinationArrayList(): ArrayList<Destination> {
        return arrayListOf<Destination>().apply {
            add(
                Destination(
                    "Kedarnath,Char,Uttarakhand",
                    R.drawable.ked,
                    "Kedarnath",
                    textNumOfPac = "20+ packages"
                )
            )
            add(
                Destination(
                    actualName = "Dwarka,12,Gujarat",
                    R.drawable.dwarka1,
                    textName = "Dwarka",
                    "20+ packages"
                )
            )
            add(
                Destination(
                    "Somnath,Gujarat,12",
                    R.drawable.somnath1,
                    "Somnath",
                    textNumOfPac = "20+ packages"
                )
            )
            add(
                Destination(
                    "Haridwar,Uttarakhand,Winter",
                    R.drawable.haridwar,
                    "Haridwar",
                    textNumOfPac = "20+ packages"
                )
            )
            add(
                Destination(
                    "12,Varanasi,Kashi",
                    R.drawable.varanasi,
                    "Varanasi",
                    textNumOfPac = "20+ packages"
                )
            )


            add(
                Destination(
                    actualName = "Puri,12,Puri",
                    image = R.drawable.puri,
                    textName = "Puri",
                    "20+ packages"
                )
            )
            add(
                Destination(
                    actualName = "Mahakal,Ujjain,12",
                    R.drawable.mah,
                    textName = "Mahakaleshwar",
                    textNumOfPac = "10+ packages"
                )
            )
            add(
                Destination(
                    actualName = "Rameswaram,12,Triangle",
                    R.drawable.ram,
                    textName = "Rameswaram",
                    "20+ packages"
                )
            )
            add(
                Destination(
                    actualName = "12,Mathura,Brij",
                    R.drawable.mathura1,
                    textName = "Mathura",
                    "20+ packages"
                )
            )
            add(
                Destination(
                    actualName = "Gaya,Gaya,Gaya",
                    image = R.drawable.bodh_gaya,
                    textName = "Bodh Gaya",
                    textNumOfPac = "20+ packages"
                )
            )
            add(
                Destination(
                    actualName = "Shikhar,Shikhar, Jain ",
                    image = R.drawable.parsnath,
                    textNumOfPac = "10+ packages",
                    textName = "Shikharji"
                )
            )
            add(
                Destination(
                    actualName = "Nashik,Nashik,12",
                    R.drawable.nas,
                    textName = "Nashik",
                    "10+ packages"
                )
            )
            add(
                Destination(
                    "Amritsar,Amritsar,Takht",
                    R.drawable.amr,
                    textName = "Amritsar",
                    textNumOfPac = "20+ packages"
                )
            )
            add(
                Destination(
                    actualName = "Shirdi,Shirdi,12",
                    R.drawable.shirdi,
                    textName = "Shirdi",
                    textNumOfPac = "20+ packages"
                )
            )

            add(
                Destination(
                    "Tirupati,Tiru,Tirupati",
                    R.drawable.tirup,
                    "Tirupati",
                    textNumOfPac = "20+ packages"
                )
            )
        }
    }

    private fun getFAQsList(): ArrayList<FAQ2> {

        return arrayListOf<FAQ2>().apply {

            add(
                FAQ2(
                    textQ = "Q1.",
                    textQuestion = "When is the best time to go for pilgrimage tours in India?",
                    textAnswer = "One can go on any pilgrimage tour in India at any time of the year. " + "Except for a few remote pilgrimages that depend on weather conditions, one can travel " + "for pilgrimage anywhere in India at any time of the year."
                )
            )
            add(
                FAQ2(
                    "Q2.",
                    textQuestion = "Does the Pilgrimage Tour offer all types of pilgrimage packages?",
                    textAnswer = "Yes. Pilgrimage tour offers all types of pilgrimage packages for people of all faiths."
                )
            )
            add(
                FAQ2(
                    "Q3.",
                    textQuestion = "Does the Pilgrimage Tour help with the Darshan experience in various temples?",
                    textAnswer = "Yes. Pilgrimage tour offers assistance with the Darshan experience in various temples."
                )
            )
            add(
                FAQ2(
                    "Q4.",
                    textQuestion = "Does the Pilgrimage Tour take extra precautions for older people?",
                    textAnswer = "Yes, the Pilgrimage tour ensures special arrangements like a palanquin and wheelchair or as required for the older people during their pilgrimage."
                )
            )
            add(
                FAQ2(
                    "Q5.",
                    textQuestion = "Are Pilgrimage Tour packages flexible to make changes?",
                    textAnswer = "Yes. It is simple to customize Pilgrimage tour packages according to requirements and needs."
                )
            )
            add(
                FAQ2(
                    "Q6.",
                    textQuestion = "Will I get customer support when needed?",
                    textAnswer = "Yes, throughout the pilgrimage tour, 24/7."
                )
            )
            add(
                FAQ2(
                    "Q7.",
                    textQuestion = "Do I have to make full payment to book?",
                    textAnswer = "No. One can get in touch with our travel experts and talk to them about it."
                )
            )
            add(
                FAQ2(
                    "Q8.",
                    textQuestion = "Can I book my pilgrimage packages before the temples open?",
                    textAnswer = "Yes, we offer the service of advanced booking for pilgrimages throughout the year."
                )
            )
            add(
                FAQ2(
                    "Q9.",
                    textQuestion = "Will I be able to design the package myself?",
                    textAnswer = "Yes, you can contact our travel experts and design the entire package with their assistance."
                )
            )
            add(
                FAQ2(
                    "Q10.",
                    textQuestion = "What are the best pilgrimage destinations in India?",
                    textAnswer = "Some of the best pilgrimage destinations in India are Shirdi, Tirupati, Rameswaram, Somnath, Dwarka, " + "Vaishno Devi, Puri, Amritsar, Mathura, and Varanasi."
                )
            )
        }
    }

    fun getHomeFragmentBestsellingList(): ArrayList<TravelPackage> {
        return arrayListOf<TravelPackage>().apply {

            add(twelveJyotirlingaTour24days)
            add(amarnathHelYat4days)
            add(besShirTour2days)
            add(brijVihAgr3days)
            add(budSpirTrav9days)
            add(budTourSar5days)
            add(chardhamYatPil10days)
            add(chardhamHelYat5days)
            add(deogharJyotirlingaTour3days)
            add(fulDayAll1day)
            add(fulDayVarSig1day)
            add(harRisTour3days)
            add(hemSahYat7days)
            add(himPradesh5deviDar6days)
            add(jainPilShik6days)
            add(kasVisPrayag4days)
            add(kasPrayagVin4days)
            add(matVrindavanTour1day)
            add(matVrindavanAgr4days)
            add(norIndPil9days)

            add(odishaTour4days)
            add(odishaTour3days)
            add(panKedYat16days)
            add(risFulDaySig1day)
            add(ramJyotirlingaPil4days)
            add(southIndTem12days)
            add(srisailamMalJyotirlingaPil4days)
            add(southernTriangleTour5days)
            add(spiritualGanTour8days)
            add(tirBalDar4days)
            add(ujjainOmkTour3days)
            add(ujjainOmkTour4days)
            add(varTemPil3days)
            add(varPinDaanAstVis3days)
            add(varSarSig3days)
            add(vinPilTour1day)
        }
    }

    fun getHomFragPilgrimages(): ArrayList<TravelPackage> {
        return arrayListOf<TravelPackage>().apply {


            add(chardhamYatPil10days)
            add(jainPilShik6days)
            add(norIndPil9days)
            add(ramJyotirlingaPil4days)
            add(srisailamMalJyotirlingaPil4days)
            add(varTemPil3days)
            add(vinPilTour1day)

            add(sevenJyotirlingaPil12days)
            add(budPilTaj11days)
            add(budPilTour10days)
            add(chardhamTourPil15days)
        }
    }

    fun getHomFragChardhamPac(): ArrayList<TravelPackage> {
        return arrayListOf<TravelPackage>().apply {

            add(chardhamHelYat5days)
            add(panKedYat16days)
            add(twelveJyotirlingaTour24days)
            add(chardhamYatPil10days)

            add(badHelYat1day)
            add(badKedDo5days)
            add(charDhamHel3days)
            add(chardhamTourPil15days)
            add(charDehMus5days)
            add(charDhamDar11days)
            add(divCharDham10days)
        }
    }

    private fun getInclusionsList(): ArrayList<String> {
        return arrayListOf<String>().apply {
            add("Arrival assistance at the airport/railway station by our representative")
            add("Accommodation on double sharing basis at hotels")
            add("All transfers/tour services are by air-conditioned vehicles. A/c will not work on hill drive.")
            add("Meals (daily breakfast at all places)")
            add("One bottle of mineral water per person per day basis")
            add("Sightseeing included as per the above itinerary.")
            add("All-inclusive (fuel, driver allowance, parking toll tax, state tax.)")
        }
    }

    private fun getExclusionsList(): ArrayList<String> {
        return arrayListOf<String>().apply {
            add("Entrance fees and guide charges at any point.")
            add("Expenses of personal nature, such as tipping, porters, laundry, telephones, camera fees, etc., are exclusions.")
            add("Any kind of insurance.")
            add("Any charges for flights, train, helicopter, auto, ropeway tickets, Doli & pony, ferry, boating, etc., are exclusions.")
            add("Any claim or delay charges due to natural calamities, landslides, road blockage, etc., are exclusions.")
            add("Any other things not mentioned in the cost included column.")
            add("Government service tax – 5% applicable on total billing")
        }
    }

    private fun getGuidelinesList(): ArrayList<String> {
        return arrayListOf<String>().apply {
            add("Not everything goes as planned. You should be ready if any change arises.")
            add("Be aware of cultural differences.")
            add("Be aware of the geography and climate of the place.")
            add("Be up to speed on the law and order of the place.")
            add("Be updated with the politics and surroundings.")
            add("Secure your belongings and children.")
            add("It is safe to register with your embassy.")
            add("Book tickets and passes in advance to avoid any mess.")
            add("Keep all your travel documents safe.")
            add("Carry all your medicines.")
            add("Carry light and manageable luggage.")
            add("Keep your eating habits healthy.")
        }
    }

    val ujjainOmkTour3days = TravelPackage(
        ism = "Hinduism, Buddism, Jainism",
        viewType = 1,
        image = R.drawable.ujj_omk3day1,
        textName = "Ujjain Omkareshwar Tour",
        textUrl = "",
        textMrp = "₹ 11,000",
        textDiscounted = "₹8,400",
        textDays = "3 Days",
        textNights = "2 Nights",
        textButton = "View Details",
        details = PackageDetails(
            "It covers tourist destinations around Indore city which includes heritage, " + "cultural & pilgrimage destinations of Madhya Pradesh. " + "It also covers Jyotirlinga Temples, handloom art, boating, forts, and palaces.",
            daysList = arrayListOf<ItineraryDay>().apply {
                add(
                    ItineraryDay(
                        textDay = "Day 1",
                        textDeparture = "Arrival Indore- Ujjain (77 km - 2 hrs)",
                        placesList = listOf("Indore", "Ujjain"),
                        textExplanation = "Arrive at Indore. Proceed to Ujjain. On arrival, " + "check into the hotel. Later proceed for half day sightseeing at Ujjain. " + "One of the holiest cities in the Hindu religion and one of the sites that host the Kumbha " + "Mela. Sightseeing starts from Mahakaleshwar temple, one of the 12 Jyotirlingas of Lord " + "Shiva. Later visit Iskon temple, Shakti Peeth, Harsiddhi temple, " + "Sandipani Ashram, Bade Ganesh Ka Mandir, Ram Ghat, etc. " + "Evening Aarti at Mahakaleshwar temple is the main attraction. Overnight stay at hotel."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 2",
                        textDeparture = "Departure Ujjain - Omkareshwar - Maheshwar (200 km - 4 hrs)",
                        placesList = listOf("Ujjain", "Omkareshwar", "Maheshwar"),
                        textExplanation = "After breakfast, proceed to Omkareshwar – " + "one of the 12 Jyotirlingas of Lord Shiva. It is located on the bank of the Narmada river. " + "Take a holy dip in Maa Narmada and do Darshan and Puja at Omkareshwar temple. " + "The shape of the town is said to be very similar to the Hindu symbol of 'Om' ॐ. " + "Moreover, the name also originates from 'Omkara' " + "which happens to be one of the names of Lord Shiva. " + "The Hanging bridge over the Narmada and the scenic landscape add-on to the exquisiteness of " + "Omkareshwar. The city has 68 Teerth (places of spiritual importance) and a group of 108 " + "Shivlingas, making it an utterly important Shaiva site. River Kaveri confluences with " + "Narmada here. Later, visit ancient Maheshwar."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 3",
                        textDeparture = "Departure Maheshwar - Indore (90 kms - 2 hrs)",
                        placesList = listOf("Maheshwar", "Indore"),
                        textExplanation = "In the morning, visit Maheshwar, a city which impacted the prominent ruling " + "dyansty Holkar's. Queen Ahilya Bai Holkar who ruled during the Holkar dynasty, " + "was a religious lady. Under her rule, temples and holy Ghats were developed in the " + "Malwa region of Central India, and Maheshwar was the capital. Visit famous AhilyaBai Fort " + "and Palace, Akhileshwar Temple (inside fort complex), Narmada Ghat. If time permits, " + "one can go boating at Narmada Ghat on direct payment basis. Later, drive back to Indore. " + "If time permits, visit Rajwada, Lal Bagh Palace; Indore is also famous for sweet dishes. " + "Later transfer to Indore Airport /Railway Station for onward journey."
                    )
                )
            },
            inclusionsList = arrayListOf<String>().apply {

                add("Included accommodation on a double-sharing basis at hotels.")
                add("All transfers/tours services by air-conditioned vehicle")
                add("Sightseeing as per the above itinerary.")
                add("Meals (daily breakfast at all places)")
                add("All-inclusive (fuel, driver allowance, parking toll tax, state tax.)")
                add("All taxes except 5% govt. service tax")
            },
            exclusionsList = arrayListOf<String>().apply {
                add("Expenses of personal nature, such as tipping, porters, laundry, telephones, and camera fees. etc")
                add("Any kind of insurance.")
                add("Entrance fee at any point")
                add("Any cable car charges, boating charges, guide charges, etc")
                add(
                    "Any claim or delay charges due to natural calamities, landslides, road blockage, etc., " + "or anything not mentioned in the cost included column."
                )
            },
            guidelinesList = getGuidelinesList(),
            fAQsList = getFAQsList()
        ),
        oneDay = false
    )
    val ujjainOmkTour4days = TravelPackage(
        ism = "Hinduism, Buddism, Jainism",
        viewType = 1,
        image = R.drawable.ujj_omk4day1,
        textName = "Ujjain Omkareshwar Tour ",
        textUrl = "",
        textMrp = "₹ 14,000",
        textDiscounted = "₹11,900",
        textDays = "4 Days",
        textNights = "3 Nights",
        textButton = "View Details",
        details = PackageDetails(
            textOverview = "It covers tourist destinations around Indore city which includes heritage, " + "cultural & pilgrimage destinations of Madhya Pradesh. " + "It also covers Jyotirlinga Temples, handloom art, boating, forts, and palaces.",
            daysList = arrayListOf<ItineraryDay>().apply {
                add(
                    ItineraryDay(
                        textDay = "Day 1",
                        textDeparture = "Arrival Indore- Ujjain (77 km - 2 hrs)",
                        placesList = listOf("Indore", "Ujjain"),
                        textExplanation = "Arrive at Indore. Proceed to Ujjain. On arrival, " + "check into the hotel. Later proceed for half day sightseeing at Ujjain. " + "One of the holiest cities in the Hindu religion and one of the sites that host the Kumbha " + "Mela. Sightseeing starts from Mahakaleshwar temple, one of the 12 Jyotirlingas of Lord " + "Shiva. Later visit Iskon temple, Shakti Peeth, Harsiddhi temple, " + "Sandipani Ashram, Bade Ganesh Ka Mandir, Ram Ghat, etc. " + "Evening Aarti at Mahakaleshwar temple is the main attraction. Overnight stay at hotel."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 2",
                        textDeparture = "Departure Ujjain - Omkareshwar - Maheshwar (200 km - 4 hrs)",
                        placesList = listOf("Ujjain", "Omkareshwar", "Maheshwar"),
                        textExplanation = "After breakfast, proceed to Omkareshwar – " + "one of the 12 Jyotirlingas of Lord Shiva. It is located on the bank of the Narmada river. " + "Take a holy dip in Maa Narmada and do Darshan and Puja at Omkareshwar temple. " + "The shape of the town is said to be very similar to the Hindu symbol of 'Om' ॐ. " + "Moreover, the name also originates from 'Omkara' " + "which happens to be one of the names of Lord Shiva. " + "The Hanging bridge over the Narmada and the scenic landscape add-on to the exquisiteness of " + "Omkareshwar. The city has 68 Teerth (places of spiritual importance) and a group of 108 " + "Shivlingas, making it an utterly important Shaiva site. River Kaveri confluences with " + "Narmada here. Later, visit ancient Maheshwar."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 3",
                        textDeparture = "Departure Maheshwar – Mandu (50 km - 2 hrs)",
                        placesList = listOf("Maheshwar", "Mandu"),
                        textExplanation = "In the morning, visit Maheshwar, a city which impacted the prominent ruling " + "dyansty Holkar's. Queen Ahilya Bai Holkar who ruled during the Holkar dynasty, " + "was a religious lady. Under her rule, temples and holy Ghats were developed in the " + "Malwa region of Central India, and Maheshwar was the capital. Visit famous AhilyaBai Fort " + "and Palace, Akhileshwar Temple (inside fort complex), Narmada Ghat. If time permits, " + "one can go boating at Narmada Ghat on direct payment basis. Later, drive back to Indore. " + "If time permits, visit Rajwada, Lal Bagh Palace; Indore is also famous for sweet dishes. " + "Later transfer to Indore Airport /Railway Station for onward journey."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 4",
                        textDeparture = "Departure Mandu - Indore (80 km - 2 hrs)",
                        placesList = listOf("Mandu", "Indore"),
                        textExplanation = "After breakfast in the morning, visit The Royal Enclave Jahaz Mahal, " + "Hindola Mahal, Darwazas of Mandu, and Hoshang Shah's tomb. Later drive to Indore, " + "and if time permits, one can visit Rajwada, Lal Bagh Palace. Indore is also famous for " + "its sweet dishes. Later transfer to Indore airport /railway station for onward journey."
                    )
                )
            },
            inclusionsList = arrayListOf<String>().apply {

                add("Included accommodation on a double-sharing basis at hotels.")
                add("All transfers/tours services by air-conditioned vehicle")
                add("Sightseeing as per the above itinerary.")
                add("Meals (daily breakfast at all places)")
                add("All-inclusive (fuel, driver allowance, parking toll tax, state tax.)")
                add("All taxes except 5% govt. service tax")
            },
            exclusionsList = arrayListOf<String>().apply {
                add("Expenses of personal nature, such as tipping, porters, laundry, telephones, and camera fees. etc")
                add("Any kind of insurance.")
                add("Entrance fee at any point")
                add("Any cable car charges, boating charges, guide charges, etc")
                add(
                    "Any claim or delay charges due to natural calamities, landslides, road blockage, etc., " + "or anything not mentioned in the cost included column."
                )
            },
            guidelinesList = getGuidelinesList(),
            fAQsList = getFAQsList()
        ),
        oneDay = false
    )
    val odishaTour3days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.oris_tour3day1,
        textName = "Odisha Tour ",
        textUrl = "",
        textMrp = "₹ 11,000",
        textDiscounted = "₹8,850",
        textDays = "3 Days",
        textNights = "2 Nights",
        textButton = "View Details",
        details = PackageDetails(
            textOverview = "Known as the Soul of India, Odisha has a distinctive culture and historical land " + "with priceless natural wealth buried under its surface. Its virgin beauty of endless emerald green " + "fields, meandering rivers dotted with picturesque villages, exquisite temples, monuments, " + "and long beaches, the architecturally incredible Sun temple & Jagannath temple, " + "and its spectacular Rath Yatra dot its coastal plains.",
            daysList = arrayListOf<ItineraryDay>().apply {
                add(
                    ItineraryDay(
                        textDay = "Day 1",
                        textDeparture = "Arrival in Bhubaneswar",
                        placesList = listOf("Bhubaneswar"),
                        textExplanation = "Welcome to Bhubaneshwar, the capital of Odisha. " + "Our representative greets you on arrival at Bhubaneswar airport/railway station, " + "and you are transferred to the hotel, later visiting the twin hills of Khandagiri & " + "Udayagiri. Here, find the Jain temple & Elephanta caves, Lion cave, " + "and Queen cave of the first century BC. Bhubaneswar is famous for its beautiful temples " + "constructed between the 7th to 13th centuries. In this temple city, " + "you can visit Lingaraj temple, Mukteswar temple, Parsuram temple, Rajarani temple, " + "Kedargouri, and Bindusagar. Overnight stay at the hotel."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 2",
                        textDeparture = "Departure Bhubaneswar – Konark - Puri",
                        placesList = listOf("Bhubaneswar", "Konark", "Puri"),
                        textExplanation = "In the morning, after breakfast, drive to Puri en route to visit Dhauli " + "(Buddhist Stupa/peace pagoda), Pipli (the appliqué work village), and Raghurajpur " + "(the artisan village). The most famous attractions here are the Pattachitra (miniature paintings), " + "palm leaf engravings, stone carvings, wooden toys, Tusser paintings, etc., " + "and the famous Konark Sun temple (famously known as Black pagoda). The Sun temple is one of the " + "UNESCO heritage sites built by King Narasimha of the Ganga dynasty in the 13th century. En route, " + "visit Ramchandi temple & Chandrabhaga beach (one of the finest beaches with a long stretch " + "of clean sands & the beach has its lovely serenity). Then proceed to Puri and check in to the hotel. " + "Later visit to Shri Jagannath temple, one of the oldest temples of the Hindus. " + "As a vibrant and living temple, it occupies a special place in religious and cultural history. " + "Over the centuries, it has attracted many kings, conquerors, Maharishis, devotees, and pilgrims. " + "Well known all over the world, it is situated on the sacred Mahodadhi " + "(presently known as the Bay of Bengal). Overnight stay in a hotel."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 3",
                        textDeparture = "Depart Puri",
                        placesList = listOf("Puri"),
                        textExplanation = "Return home with happy memories\nToday bid farewell to your Puri Konark tour" + " as we transfer you to Bhubaneswar railway station for your onward journey."
                    )
                )
            },
            inclusionsList = arrayListOf<String>().apply {

                add("Welcome drink on arrival (non–alcoholic)")
                add("Included accommodation on a double-sharing basis at hotels.")
                add("Meals (daily breakfast at all places)")
                add("Included sightseeing as per the above itinerary by separate vehicles.")
                add("Airport/railway station transfers to and fro")
                add("All-inclusive (fuel, driver allowance, parking toll tax, state tax.)")
            },
            exclusionsList = arrayListOf<String>().apply {
                add("Expenses of personal nature, such as tipping, porters, laundry, telephones, and camera fees. etc")
                add("Any kind of insurance.")
                add("Airfares, train fare.")
                add("Boating at Chilika lake")
                add("Entrance fee at any point")
                add(
                    "Any claim or delay charges due to natural calamities, landslides, road blockage, etc., " + "or anything not mentioned in the cost included column."
                )
                add("All taxes except 5% govt. service tax")
                add(
                    "Rates are not valid during peak season and festival holidays (peak season: During Christmas, New year, Diwali, " + "Durga Pooja, Dussehra, Ratha Yatra, and long weekends)"
                )
            },
            guidelinesList = getGuidelinesList(),
            fAQsList = getFAQsList()
        ),
        oneDay = false
    )
    val odishaTour4days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.oris_tour4day,
        textName = "Odisha Tour",
        textUrl = "",
        textMrp = "₹ 14,000",
        textDiscounted = "₹11,400",
        textDays = "4 Days",
        textNights = "3 Nights",
        textButton = "View Details",
        details = PackageDetails(
            textOverview = "Known as the Soul of India, Odisha has a distinctive culture and historical land " + "with priceless natural wealth buried under its surface. Its virgin beauty of endless emerald green " + "fields, meandering rivers dotted with picturesque villages, exquisite temples, monuments, " + "and long beaches, the architecturally incredible Sun temple & Jagannath temple, " + "and its spectacular Rath Yatra dot its coastal plains.",
            daysList = arrayListOf<ItineraryDay>().apply {
                add(
                    ItineraryDay(
                        textDay = "Day 1",
                        textDeparture = "Arrival in Bhubaneswar",
                        placesList = listOf("Bhubaneswar"),
                        textExplanation = "Welcome to Bhubaneshwar, the capital of Odisha. " + "Our representative greets you on arrival at Bhubaneswar airport/railway station, " + "and you are transferred to the hotel, later visiting the twin hills of Khandagiri & " + "Udayagiri. Here, find the Jain temple & Elephanta caves, Lion cave, " + "and Queen cave of the first century BC. Bhubaneswar is famous for its beautiful temples " + "constructed between the 7th to 13th centuries. In this temple city, " + "you can visit Lingaraj temple, Mukteswar temple, Parsuram temple, Rajarani temple, " + "Kedargouri, and Bindusagar. Overnight stay at the hotel."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 2",
                        textDeparture = "Departure Bhubaneswar – Konark - Puri",
                        placesList = listOf("Bhubaneswar", "Konark", "Puri"),
                        textExplanation = "In the morning, after breakfast, drive to Puri en route to visit Dhauli " + "(Buddhist Stupa/peace pagoda), Pipli (the appliqué work village), and Raghurajpur " + "(the artisan village). The most famous attractions here are the Pattachitra (miniature paintings), " + "palm leaf engravings, stone carvings, wooden toys, Tusser paintings, etc., " + "and the famous Konark Sun temple (famously known as Black pagoda). The Sun temple is one of the " + "UNESCO heritage sites built by King Narasimha of the Ganga dynasty in the 13th century. En route, " + "visit Ramchandi temple & Chandrabhaga beach (one of the finest beaches with a long stretch " + "of clean sands & the beach has its lovely serenity). Then proceed to Puri and check in to the hotel. " + "Later visit to Shri Jagannath temple, one of the oldest temples of the Hindus. " + "As a vibrant and living temple, it occupies a special place in religious and cultural history. " + "Over the centuries, it has attracted many kings, conquerors, Maharishis, devotees, and pilgrims. " + "Well known all over the world, it is situated on the sacred Mahodadhi " + "(presently known as the Bay of Bengal). Overnight stay in a hotel."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 3",
                        textDeparture = "In Puri (Excursion of Chilika lake)",
                        placesList = listOf("Puri"),
                        textExplanation = "The morning after breakfast, proceed to Chilika lake, the largest brackish water body in Asia " + "which is the home of numerous migratory birds & sea life. Visit the island by boat for bird watching & " + "also Kalijai temple located at Kalijai island (on direct payment). Return to the hotel, and the evening " + "relax on the golden beach of Puri. Overnight at the hotel."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 4",
                        textDeparture = "Depart Bhubaneswar",
                        placesList = listOf("Bhubaneswar"),
                        textExplanation = "Return home with happy memories\nToday bid farewell to your Puri Konark tour" + " as we transfer you to Bhubaneswar railway station for your onward journey."
                    )
                )
            },
            inclusionsList = arrayListOf<String>().apply {

                add("Welcome drink on arrival (non–alcoholic)")
                add("Included accommodation on a double-sharing basis at hotels.")
                add("Meals (daily breakfast at all places)")
                add("Included sightseeing as per the above itinerary by separate vehicles.")
                add("Airport/railway station transfers to and fro")
                add("All-inclusive (fuel, driver allowance, parking toll tax, state tax.)")
            },
            exclusionsList = arrayListOf<String>().apply {
                add("Expenses of personal nature, such as tipping, porters, laundry, telephones, and camera fees. etc")
                add("Any kind of insurance.")
                add("Airfares, train fare.")
                add("Boating at Chilika lake")
                add("Entrance fee at any point")
                add(
                    "Any claim or delay charges due to natural calamities, landslides, road blockage, etc., " + "or anything not mentioned in the cost included column."
                )
                add("All taxes except 5% govt. service tax")
                add(
                    "Rates are not valid during peak season and festival holidays (peak season: During Christmas, New year, Diwali, " + "Durga Pooja, Dussehra, Ratha Yatra, and long weekends)"
                )
            },
            guidelinesList = getGuidelinesList(),
            fAQsList = getFAQsList()
        ),
        oneDay = false
    )
    val kasVisPrayag4days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.kas_vis4day,
        textName = "Kashi Vishwanath Prayag Darshan",
        textUrl = "",
        textMrp = "₹ 10,000",
        textDiscounted = "₹7,500",
        textDays = "4 Days",
        textNights = "3 Nights",
        textButton = "View Details",
        details = PackageDetails(
            textOverview = "Varanasi/Kashi, an ancient city in Uttar Pradesh, has the Kashi Vishwanath temple as its chief attraction. " + "The revered temple, the narrow winding lanes of Varanasi, the many auspicious Ghats of Varanasi, " + "and the famed Benarasi Sarees and Paans are some of the fascinating gifts that the city has to offer to a new " + "visitor.\nThus since time immemorial, the city has lured pilgrims and tourists from every nook and corner of the " + "country. Take a dip into the holy river Ganga at Triveni Sangam in Prayag (Allahabad). To soak in the religious " + "fervor and old-world charm of Varanasi & Prayag, you must embark on this unforgettable Kashi Darshan & Prayag tour.",
            daysList = arrayListOf<ItineraryDay>().apply {
                add(
                    ItineraryDay(
                        textDay = "Day 1",
                        textDeparture = "Arrival at Delhi – Varanasi",
                        placesList = listOf("Delhi", "Varanasi"),
                        textExplanation = "Upon your arrival at Delhi railway station/airport, you’ll be greeted by " + "our tour representative, who then escorts you to Varanasi by rail route or air. On reaching Varanasi, " + "you’ll check into the hotel and rest overnight at the hotel."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 2",
                        textDeparture = "At Varanasi",
                        placesList = listOf("Varanasi"),
                        textExplanation = "This morning, we depart for the top attraction of Varanasi, the Kashi Vishwanath temple. " + "This lord Shiva shrine is among the 12 Shiva Jyotirlingams and is flocked " + "by thousands of devotees daily. After Darshan at the temple, we return to the hotel for breakfast. " + "Later, during the day, proceed to visit the famed historical site, the Ramnagar fort, " + "which beautifully exhibits a fusion of Islamic and Indian architectural styles. " + "Back to hotel for dinner and night stay."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 3",
                        textDeparture = "At Varanasi",
                        placesList = listOf("Varanasi"),
                        textExplanation = "Day 3 of the tour goes to Varanasi sightseeing, where you would get to visit " + "some of the other notable attractions of Varanasi, such as the New Vishwanath temple, Durga temple, " + "Nepali museum, and Sarnath museum. Also, the Varanasi Ghats occupy most of our day three " + "sightseeing agenda. So, you would visit some of the most famous Ghats of Varanasi, such as Assi Ghat, " + "Manikarnika Ghat, Kedar Ghat, Hanuman Ghat, and Dasaswamedh Ghat. A visit to these Ghats " + "will let you catch sight of devotees taking their dips in the holy Ganges or chanting mantras during " + "the various religious rituals held at the Ghats of Varanasi. In the evening, we will partake in the " + "Ganga Aarti at Dasaswamedh Ghat and then return to the hotel for dinner and a night's stay."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 4",
                        textDeparture = "At Allahabad",
                        placesList = listOf("Allahabad"),
                        textExplanation = "The next day we leave for Allahabad, another historical and religious city of UP. " + "Upon reaching, check into the hotel and later proceed for Allahabad sightseeing which covers sites " + "like Triveni Sangam, Ashoka pillars, and Allahabad fort. Back to hotel for dinner and night stay."
                    )
                )
            },
            inclusionsList = getInclusionsList(),
            exclusionsList = getExclusionsList(),
            guidelinesList = getGuidelinesList(),
            fAQsList = getFAQsList()
        ),
        oneDay = false
    )
    val varTemPil3days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.varanasi_tem,
        textName = "Varanasi Temples Pilgrimage",
        textUrl = "",
        textMrp = "₹ 11,000",
        textDiscounted = "₹8,000",
        textDays = "3 Days",
        textNights = "2 Nights",
        textButton = "View Details",
        details = PackageDetails(
            textOverview = "The holy city of Varanasi has always been the ‘Moksha Dwar’ for pilgrims who visit the city in the hope of " + "receiving salvation from the cycle of birth and rebirth.\nThe city is well known for its Kashi Vishwanath temple, " + "the Lord Shiva Jyotirlingam. Also, the Ghats of Varanasi, the Banarasi Paan, and the famous Banarasi silk " + "are other items that make Varanasi occupy a definite place on the country’s map.",
            daysList = arrayListOf<ItineraryDay>().apply {
                add(
                    ItineraryDay(
                        textDay = "Day 1",
                        textDeparture = "Arrival at Varanasi",
                        placesList = listOf("Varanasi"),
                        textExplanation = "Upon your arrival at the nearest railway station/airport, you’ll be greeted by our tour " + "representative, who then escorts you to the hotel. Check into the hotel and proceed to the spell " + "bounding Ganga Aarti at the Dasaswamedh Ghat. You could also take a boat ride on the Ganges while " + "visualizing the Ganga Aarti. Back to hotel for dinner and night stay."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 2",
                        textDeparture = "Varanasi – Sarnath – Varanasi",
                        placesList = listOf("Varanasi", "Sarnath"),
                        textExplanation = "Early morning, we embark on a spiritual boat ride on the Ganges river. " + "It is an out-of-the-world experience as you sail past the Ghats and catch glimpses of " + "people taking their holy dip, offering Surya Namaskar, chanting mantras, and so on. " + "Later, visit the famous Kashi Vishwanath temple, followed by other temples such as " + "Sankat Mochan temple, Annapurna temple, Manas Mandir, Bharat Mata Mandir, and BHU. " + "Back to the hotel for breakfast.\nLater during the day, we make an excursion to Sarnath, " + "the historical place where Lord Buddha is believed to have delivered his first sermon. " + "The places we visit at Sarnath temple include Chaukhandi Stupa, Dhamekh Stupa, Buddha temples, " + "and archaeological museum.\nWe will be back in Varanasi by evening and later some shopping times. " + "Back to hotel for dinner and night stay."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 3",
                        textDeparture = "Departure from Varanasi",
                        placesList = listOf("Varanasi"),
                        textExplanation = "After breakfast, you are transferred to the railway station/airport, " + "where our representative sees you off, and you depart for your onward journey back home."
                    )
                )
            },
            hotelsList = arrayListOf<DestinationHotel>().apply {
                add(
                    DestinationHotel(
                        textDestination = "Bhubaneswar",
                        textHotels = "Hotel Golden Orchid",
                        textMeals = "Breakfast only"
                    )
                )

                add(
                    DestinationHotel(
                        textDestination = "Puri",
                        textHotels = "Hotel Naren Palace",
                        textMeals = "Breakfast only"
                    )
                )
            },
            inclusionsList = getInclusionsList(),
            exclusionsList = getExclusionsList(),
            guidelinesList = getGuidelinesList(),
            fAQsList = getFAQsList()
        ),
        oneDay = false
    )
    val chardhamYatPil10days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.char_yat10day1,
        textName = "Chardham Yatra Pilgrimage",
        textUrl = "",
        textMrp = "₹ 44,000",
        textDiscounted = "₹41,000",
        textDays = "10 Days",
        textNights = "9 Nights",
        textButton = "View Details",
        details = PackageDetails(
            textOverview = "One of the most pious pilgrimages in India, Char Dham Yatra in Uttarakhand, is a cherished dream of every " + "Hindu devout that they want to embark on at least once in their life. The pilgrimage holds a belief " + "that covering these holy abodes in Uttarakhand helps you attain salvation and liberation from the cycle " + "of birth and death. Our beautifully crafted Char Dham Yatra tour packages make your life so easy and comfortable " + "by arranging top-notch accommodations, transport, and VIP Darshan Pass (covered under some packages only). " + "Book the Char Dham Yatra tour package with the Pilgrimage Tour and make this challenging and arduous trekking " + "pilgrimage easy and successful.\nChhota Chardham Yatra is a Yatra to four holy pilgrimages located in small " + "beautiful towns of Uttarakhand state in the northern part of India. These four temples are dedicated to " + "four different Gods and Goddesses. This Yatra consists of visiting the four holy temples Badrinath, Kedarnath, " + "Gangotri, and Yamunotri. The actual Chardham Yatra consists of four temples situated in East, West, " + "North and South direction each. That is why the ‘Chardhams’ of Uttarakhand are called Chhota Chardham Yatra." + "\nChardham Yatra is considered as a must-visit destination by Hindus. They believe one must go for " + "Chardham Yatra at least for once in their entire life. Pilgrims and devotees from all over India, " + "and people from all over the world, go for Chhota Chardham Yatra.\nThe four Dhams of this Yatra are Badrinath, " + "Kedarnath, Gangotri, and Yamunotri.",
            daysList = arrayListOf<ItineraryDay>().apply {
                add(
                    ItineraryDay(
                        textDay = "Day 1",
                        textDeparture = "Departure from Dehradun – Barkot (215 km drive)",
                        placesList = listOf("Dehradun", "Barkot"),
                        textExplanation = "It is the first day of your Chhota Chardham Yatra. All the passengers in the group will " + "queue at the Dehradun bus stand to go towards Barkot via Mussoorie, which takes a minimum of " + "7 hours to cover this road distance. After arriving at Barkot, check in to hotels or guest houses " + "as you will spend the night in Barkot."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 2",
                        textDeparture = "Barkot – Yamunotri – Barkot (42 km drive/6 km trek)",
                        placesList = listOf("Barkot", "Yamunotri"),
                        textExplanation = "Get up early as you leave for your first destination Yamunotri. Take a ride to Janki Chatti, " + "a 42 km drive via Hanuman Chatti. After reaching Janki Chatti, you will be trekking for " + "6 km to reach Yamunotri. After doing Darshan in Yamunotri, start hiking back to Janki Chatti. " + "And from there, take a ride back to Barkot. After arriving at Barkot, spend the night there again."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 3",
                        textDeparture = "Barkot – Uttarkashi (82 km drive)",
                        placesList = listOf("Barkot", "Uttarkashi"),
                        textExplanation = "You will leave for Uttarkashi from Barkot, an 82 km drive. It will take around " + "4 hours to reach Uttarkashi. Check in at the hotel for a night's stay, as you will leave " + "for your next destination in the morning."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 4",
                        textDeparture = "Uttarkashi – Gangotri – Uttarkashi (100 km drive)",
                        placesList = listOf("Uttarkashi", "Gangotri"),
                        textExplanation = "Get up early and ride to Gangotri, a 100 km drive. After reaching your second destination " + "Gangotri you must take a dip in the holy river of Ganga. And after Darshan, " + "leave for Uttarkashi again. Take a ride back to Uttarkashi, and stay for the night there in hotels."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 5",
                        textDeparture = "Uttarkashi – Guptkashi (228 km drive)",
                        placesList = listOf("Uttarkashi", "Guptkashi"),
                        textExplanation = "Get up early, as you will drive a long way to Gupkashi. You will be driving for 228 km, " + "will take over 8-9 hours to reach there. After reaching Guptkashi, check in to hotels, " + "or you can also enjoy camping. Spend the rest of the night there."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 6",
                        textDeparture = "Guptkashi – Kedarnath (32 km drive/14 km trek)",
                        placesList = listOf("Guptkashi", "Kedarnath"),
                        textExplanation = "This day you will be heading towards your third destination Kedarnath. " + "Drive from Guptakashi and cover a distance of 32 km to reach Gaurikund. Right from the " + "moment you arrive at Gaurikund, start the trekking trail of almost 14 km to reach " + "the holy shrine of Lord Kedar. After completing philosophy and the Puja rituals, return to your " + "accommodation for an overnight halt."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 7",
                        textDeparture = "Kedarnath – Guptkashi (14 km trek/32 km drive)",
                        placesList = listOf("Kedarnath", "Guptkashi"),
                        textExplanation = "Post gorging upon the mouth-watering Garhwali cuisine, trek back towards the Gaurikund. " + "After arriving at Gaurikund, take a ride back to Guptkashi, which is a 32 km drive. " + "Check into hotels in Guptkashi and spend the rest of the night there."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 8",
                        textDeparture = "Guptkashi – Badrinath (173 km drive)",
                        placesList = listOf("Guptkashi", "Badrinath"),
                        textExplanation = "It is the day you will visit your fourth and last destination of the Chhota Chardham Yatra. " + "You will be driving to Kedarnath, a 173 km drive from Guptkashi. It will take about " + "7-8 hours to reach Badrinath. After Darshan and performing Pooja, you will stay there " + "for the night in hotels."
                    )
                )
                add(
                    ItineraryDay(
                        textExplanation = "You will be leaving for Srinagar on this day. You will be driving to Srinagar, " + "a 156 km drive from Badrinath. And it will take around 7-8 hours to get there. " + "After arriving at Srinagar, check in to the hotel or a guest house to spend the night there.",
                        textDeparture = "Badrinath – Srinagar (156 km drive)",
                        placesList = listOf("Badrinath", "Srinagar"),
                        textDay = "Day 9"
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 10",
                        textExplanation = "It is the last day of your tour. You will be driving for 162 km, " + "which will take around 5 hours to reach Dehradun. After reaching Dehradun, " + "take a ride back to your home. And your Chhota Chardham Yatra ends here.",
                        textDeparture = "Srinagar – Dehradun (162 km drive)",
                        placesList = listOf("Srinagar", "Dehradun")
                    )
                )
            },
            inclusionsList = getInclusionsList(),
            exclusionsList = getExclusionsList(),
            guidelinesList = getGuidelinesList(),
            fAQsList = getFAQsList()
        ),
        oneDay = false
    )
    val fulDayAll1day = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.ful_day_all1day,
        textName = "Full-Day Allahabad Sightseeing",
        textUrl = "",
        textMrp = "₹ 8,000",
        textDiscounted = "₹5,500",
        textDays = "1 Day",
        textNights = "0 Nights",
        textButton = "View Details",
        details = PackageDetails(
            textOverview = "The city of Allahabad also referred to as Prayag, is one of the holiest pilgrimage spots for Hindus. " + "It falls within the north Indian state of Uttar Pradesh and is the site of the Triveni Sangam, " + "or sacred confluence of three mighty rivers; Ganga, Yamuna, and mythical Saraswati. Allahabad, " + "per historical records, is the country's second most ancient city, with its origin dating back to the Vedic times." + "\nEmperor Akbar gave the name 'Illahabad' which stands for \"Garden of Allah\" in Urdu. " + "Our ancient Vedic texts mention Allahabad as a locale where Prajapati Brahma had carried out a sacrificial ritual. " + "Our full-day tour of Allahabad will take us to some of the most exotic places this city graces.",
            daysList = arrayListOf<ItineraryDay>().apply {
                add(
                    ItineraryDay(
                        textDay = "Day 1",
                        textDeparture = "Arrival at Allahabad station",
                        placesList = listOf("Allahabad"),
                        textExplanation = "Upon reaching Allahabad station, you will be greeted by our tour representative, " + "who then escorts you for a city tour of Allahabad. The first in our sightseeing agenda comes the " + "Triveni Sangam. It is the confluence point of three rivers, Ganga, Yamuna and Saraswati, " + "each maintaining its distinct identity in terms of the different color of the waters. " + "Triveni Sangam makes the venue for Kumbh Mela once in every 12 years. Take a holy dip at the " + "Triveni Sangam to rid your mortal sins and feel blessed.\nAfter Triveni Sangam, " + "we visit the holy Shivkoti Mahadev temple or Someshwar Mahadev temple, a lord Shiva temple along " + "Yamuna banks. The temple enshrines the Lord in the shape of Ekadasa Rudra.\nNext, " + "we visit the famous Allahabad fort. Emperor Ashoka initially constructed this historical fort, " + "but later, Emperor Akbar renovated it. Zanana Palace, Saraswati Koop, lofty watch towers, " + "and the Ashokan Pillar, which date back to the 3rd century BCE, are some of the major highlights " + "of this gorgeous fort.\nAfter Allahabad fort, we visit the Khusro Bagh. This tranquil and beautiful " + "walled garden is home to the mausoleums of 3 famous personalities Khusrau Mirza, " + "Nithar Begum (his daughter), and Shah Begum (his wife). The three tombs beautifully exhibit the " + "excellence of Mughal architecture.\nNext, we visit the Anand Bhavan, which had the Nehru family " + "residence during their times. It has a Jawahar Planetarium within its campus, the main highlight of " + "this site.\nAfter this, we visit the All Saints Cathedral. This cathedral was built by Britishers " + "and consecrated in the year 1887. Brilliant gothic revival architecture of the 13th century is " + "exhibited at this cathedral.\nNext, we visit the picturesque Alfred Park. This beautiful park is " + "studded with scented flower plants and various other shrubs and herbs, offering a complete " + "green setting. It is believed that this gorgeous park has been named after Chandrashekhar Azad, " + "one of the great freedom fighters who sacrificed his life at this spot only while fighting " + "for the independence of our country. It is ideal place to sit, relax and stretch your sore, " + "tired muscles.\nSoon after, you are transferred to the railway station, where our representative " + "sees you off, and you depart for your onward journey back. Here ends your one day city tour of Allahabad."
                    )
                )
            },
            inclusionsList = getInclusionsList(),
            exclusionsList = getExclusionsList(),
            guidelinesList = getGuidelinesList(),
            fAQsList = getFAQsList()
        ),
        oneDay = false
    )
    val matVrindavanTour1day = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.mat_vrin1day,
        textName = "Mathura & Vrindavan",
        textUrl = "",
        textMrp = "₹ 7,000",
        textDiscounted = "₹4,000",
        textDays = "1 Day",
        textNights = "0 Nights",
        textButton = "View Details",
        details = PackageDetails(
            textOverview = "Mathura and Vrindavan are two holy cities of Uttar Pradesh closely associated with the life " + "of Lord Krishna. Mathura was the Lord’s birthplace, while Vrindaban was the locale for his childhood " + "days and youthful years.\nThere are many big and small temples scattered across Mathura and Vrindaban " + "which would take you back to Dwapara Yuga, i.e., the era of Lord Krishna. Pilgrims from far and " + "wide gather at Mathura and Vrindaban to catch glimpses of the various relics of the life of " + "Lord Krishna. Our Mathura & Vrindavan Car Tour Package will take you to these fascinating destinations.",
            daysList = arrayListOf<ItineraryDay>().apply {
                add(
                    ItineraryDay(
                        textDay = "Day 1",
                        textDeparture = "Arrival at Mathura station",
                        placesList = listOf("Mathura"),
                        textExplanation = "Upon reaching Mathura station, you’ll be greeted by our tour representative, " + "who then escorts you to your car/coach for a sightseeing tour of Mathura. The places we " + "visit at Mathura are Krishna Janmabhoomi temple, Dwarkadheesh temple, Mathura museum, " + "and Kans Qila.\nKrishna Janmabhoomi temple is said to have been constructed over the prison " + "cell where Krishna was born. So, this is said to be the exact birthplace. The Dwarkadheesh temple " + "is our site of visit at Mathura. At the temple, the Lord is presented in his royal avatar, " + "i.e., as a king and not as a ‘Bal Gopal’ or rustic young cowboy. This temple was set " + "up at Mathura some 150 years back by a Lord Krishna devotee. After this we proceed " + "to visit the Mathura museum. The museum was set up in 1874, has a splendid architecture and exhibits " + "some of the rarest forms of archaeological specimens belonging to the Gupta and Kushana periods. " + "Kans Qila was set up by Jaipur king Raja Man Singh and was named Kans, the maternal uncle " + "of Lord Krishna. The fort exhibits unique fusion of Mughal and Hindu architecture." + "\nAfter completing our sightseeing of Mathura, we drive to Vrindavan, where we visit " + "places like Banke Bihari temple, ISKON temple, Prem Mandir, Radha Vallabh temple, " + "and Nidhivan.\nWe first visit the Banke Bihari temple. This temple came into being in 1864, " + "set up by Swami Haridas. The temple houses the image of Tribhanga Murari/Lord Krishna standing " + "in Tribhanga pose. After this, we visit the ISKON temple. The International Society for Krishna " + "Consciousness set up the ISKON temple. In 1966, this temple came into being, " + "also referred to as Sri Krishna Balaram Temple. Next, we visit the Shama Sham Dham or Prem Mandir. " + "It is the main Ashram of the Jagadguru Kripalu Parishat. Next, we proceed to the Radha " + "Vallabh temple. This sacred temple symbolizes the eternal love between Radha-Krishna. " + "Sundardas Bhatnagar established this temple in the year 1585. Next, we visit Nidhivan, " + "the picturesque garden where lord Krishna is said to have carried his Raas-Leela with Radha " + "and the Gopis. The serene ambiance of Nidhivan captivates your heart indeed. " + "After completing the Vrindavan sightseeing, you go to Vrindavan station/bus depot, " + "where our representative sees you off, and you depart for your onward journey back home " + "at the end of this one-day tour of Mathura and Vrindavan."
                    )
                )
            },
            inclusionsList = getInclusionsList(),
            exclusionsList = getExclusionsList(),
            guidelinesList = getGuidelinesList(),
            fAQsList = getFAQsList()
        ),
        oneDay = false
    )
    val norIndPil9days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.nor_ind9day,
        textName = "North India Pilgrimage",
        textUrl = "",
        textMrp = "₹ 30,000",
        textDiscounted = "₹27,500",
        textDays = "9 Days",
        textNights = "8 Nights",
        textButton = "View Details",
        details = PackageDetails(
            textOverview = "Northern India is dotted with some of the most famed and magnificent Hindu temples and other " + "religious sites. For a devout God-lover, a temple tour of north India offers one of the rarest " + "and loftiest spiritual experiences. The folklore behind these temples is too astounding and is colorful " + "enough to keep your interest glued to them all the time.",
            daysList = arrayListOf<ItineraryDay>().apply {
                add(
                    ItineraryDay(
                        textDay = "Day 1",
                        textDeparture = "Arrive in Delhi",
                        placesList = listOf("Delhi"),
                        textExplanation = "Upon your arrival at Delhi railway station/airport, you’ll be greeted by " + "our tour representative, who then escorts you to the hotel. Dinner and night stay at hotel."
                    )
                )
                add(
                    ItineraryDay(
                        textDeparture = "Delhi sightseeing",
                        textDay = "Day 2",
                        placesList = listOf("Delhi"),
                        textExplanation = "Today we’ll explore the capital city of India, Delhi. The places we cover " + "during this Delhi sightseeing tour are Parliament house, India gate, Swaminarayan Akshardham temple, " + "Lakshmi Narayan temple, Chhatarpur temple, Lotus temple, and Qutub Minar. " + "Back to hotel for dinner and night stay."
                    )
                )
                add(
                    ItineraryDay(
                        textExplanation = "Afterward, we board the train from Delhi railway station to reach Haridwar. " + "On reaching Haridwar, we directly visit some of the major attractions of Haridwar, " + "like Har-ki-Pauri Ghat, Mansa Devi temple, Maya Devi temple, Chandi Devi temple, " + "and Daksha Mahadev temple. After Haridwar sightseeing, we drive to Rishikesh, " + "another holy and serene place. Reach Rishikesh, check into the hotel, and later in the evening, " + "take a look at the Ganga Aarti at Rishikesh Triveni Ghat. Back to hotel for dinner and night stay.",
                        textDay = "Day 3",
                        textDeparture = "Delhi-Haridwar [by train]- Rishikesh",
                        placesList = listOf("Delhi", "Haridwar", "Rishikesh")
                    )
                )
                add(
                    ItineraryDay(
                        textDeparture = "Rishikesh- Haridwar- Varanasi [by train]",
                        textDay = "Day 4",
                        placesList = listOf("Rishikesh", "Haridwar", "Varanasi"),
                        textExplanation = "The next day we drive back to Haridwar and reach the Haridwar railway station to " + "board the overnight train to Varanasi."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 5",
                        textDeparture = "Arrival in Varanasi",
                        placesList = listOf("Varanasi"),
                        textExplanation = "Afterward, we arrive at Varanasi. You are transferred to the hotel. " + "Check in and enjoy the entire day at leisure. Dinner and night stay at hotel."
                    )
                )
                add(
                    ItineraryDay(
                        textExplanation = "The next day is given entirely to Varanasi sightseeing. Early in the morning, " + "we embark on a rejuvenating boat cruise on the Ganges, after which we visit the famous " + "Kashi Vishwanath Lord Shiva Jyotirlingam temple. Post Darshan, we come back to the " + "hotel for breakfast. Later we set out to see the other attractions of Varanasi, " + "such as Banaras Hindu University, Sankat Mochan temple, Durga temple, and Archaeological museum. " + "Later during the day, we will go on an excursion to the famous Sarnath, where Lord Buddha " + "delivered his first sermon. At Sarnath, we visit places like the Sarnath museum and Dhamek Stupa. " + "After the Sarnath visit, we drive back to Varanasi. Dinner and night stay at hotel.",
                        textDay = "Day 6",
                        textDeparture = "At Varanasi",
                        placesList = listOf("Varanasi")
                    )
                )
                add(
                    ItineraryDay(
                        textDeparture = "Varanasi- Agra [by train]",
                        textDay = "Day 7",
                        placesList = listOf("Varanasi", "Agra"),
                        textExplanation = "This morning is at your complete leisure. In the evening, you’ll be driven " + "to Varanasi railway station to board the overnight train to Agra."
                    )
                )
                add(
                    ItineraryDay(
                        textExplanation = "This morning we reach Agra. On arriving, you are escorted to the hotel. " + "Check-in, freshen up, and later proceed for a short city tour of Agra. The places covered " + "during this tour are Agra fort, the Taj Mahal, counted among the seven wonders of the globe, " + "and Itmad-Ud-Daulah’s tomb. Back to hotel for dinner and night stay.",
                        textDay = "Day 8",
                        textDeparture = "Arrival in Agra and sightseeing",
                        placesList = listOf("Agra")
                    )
                )
                add(
                    ItineraryDay(
                        textDeparture = "Agra- Mathura- Delhi, and departure",
                        textDay = "Day 9",
                        placesList = listOf("Agra", "Mathura", "Delhi"),
                        textExplanation = "This morning we leave for Delhi. En route, we visit Mathura, and the places " + "covered therein are Krishna Janmabhoomi temple, Dwarkadheesh temple, and Mathura museum. " + "After the Mathura visit, we drive down straight to Delhi. If there’s time, " + "you could go ahead and explore the shops and bazaars at Connaught Place and Chandni Chowk, " + "post which you’ll be dropped at the airport/railway station. Our representative will see you " + "off then you can depart for your onward journey back home with lots of happy memories " + "of the North Indian temples tour."
                    )
                )
            },
            inclusionsList = getInclusionsList(),
            exclusionsList = getExclusionsList(),
            guidelinesList = getGuidelinesList(),
            fAQsList = getFAQsList()
        ),
        oneDay = false
    )
    val varPinDaanAstVis3days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.var_pin3day,
        textName = "Varanasi Pind Daan & Asthi Visarjan",
        textUrl = "",
        textMrp = "₹ 12,000",
        textDiscounted = "₹9,500",
        textDays = "3 Days",
        textNights = "2 Nights",
        textButton = "View Details",
        details = PackageDetails(
            textOverview = "Varanasi, a holy town of Uttar Pradesh, set along the banks of river Ganges is known for " + "its many significant temples and Ghats. The Ghats of Varanasi are often crowded with pilgrims, " + "visitors and priests engaged in performing various important Hindu rituals, with one such ritual " + "being Pind Daan.\nIt is believed that if Pind Daan of deceased ancestors is done at the Ghats of Varanasi, " + "their bodiless soul gets freedom from all delusions and inclinations towards the earthly life and " + "can then make the ultimate departure to the last haven of endless peace. Thus Pind Daan tour of " + "Varanasi is one of the commonest tours that pilgrims from every nook and corner of the country avail.",
            daysList = arrayListOf<ItineraryDay>().apply {
                add(
                    ItineraryDay(
                        textDay = "Day 1",
                        textDeparture = "Arrival in Varanasi",
                        placesList = listOf("Varanasi"),
                        textExplanation = "Upon your arrival at Varanasi airport/railway station, you are greeted by " + "our tour representative, who then escorts you to the hotel. Check-in, freshen up, " + "and towards the evening, proceed to enjoy the sights of the most mesmerizing Ganga Aarti at " + "the famous Ghats of Varanasi. Back to hotel for dinner and night stay."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 2",
                        textDeparture = "Pind Daan and temples tour of Varanasi",
                        placesList = listOf("Varanasi"),
                        textExplanation = "The next day early, we will leave for the Pind Daan procedures to be performed " + "on the Varanasi Ghats. We will pre-arrange priests as well as Pind Daan Pooja Samagri. " + "The entire Pind Daan process takes nearly four hours to complete. After the Pind Daan ceremonies, " + "we visit the famous Shiva Jyotirlingam, the Kashi Vishwanath temple. After Darshan and " + "Puja at Kashi Vishwanath temple, we return to the hotel for breakfast and later proceed " + "on a city tour of Varanasi. The places covered during this city tour are Sankat Mochan temple, " + "Manas Mandir, Durga temple, Bharat Mata temple, and the famous Banaras Hindu University (BHU). \n" + "The evening is free for you to enjoy some leisure and shopping time. Get back to the hotel for dinner and a night's stay."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 3",
                        textDeparture = "Varanasi boat ride and departure",
                        placesList = listOf("Varanasi"),
                        textExplanation = "Next morning we will go for a fascinating and unforgettable boat ride on " + "the shimmering Ganges. The boat ride offers you a unique view of the quintessential Varanasi. " + "After the boat ride, we return to the hotel for our breakfast. The rest of the " + "day is free for you to enjoy leisure hours. Later, you’ll be transferred to Varanasi airport/railway " + "station, where our representative will see you off, and you will depart for your onward journey back home."
                    )
                )
            },
            inclusionsList = getInclusionsList(),
            exclusionsList = getExclusionsList(),
            guidelinesList = getGuidelinesList(),
            fAQsList = getFAQsList()
        ),
        oneDay = false
    )
    val brijVihAgr3days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.brij_vih3day,
        textName = "Brij Vihar & Agra Tour",
        textUrl = "",
        textMrp = "₹ 9,000",
        textDiscounted = "₹6,500",
        textDays = "3 Days",
        textNights = "2 Nights",
        textButton = "View Details",
        details = PackageDetails(
            textOverview = "This Brij Vihar Agra Tour package takes you through some of the holiest places of Brij Dham " + "and the historic city of Agra, giving you a wonderful experience and unforgettable memories.",
            daysList = arrayListOf<ItineraryDay>().apply {
                add(
                    ItineraryDay(
                        textDay = "Day 1",
                        textDeparture = "Arrive in Mathura- Vrindavan- Gokul- Vrindavan",
                        placesList = listOf("Mathura", "Vrindavan", "Gokul"),
                        textExplanation = "Upon arrival at Mathura station, you are greeted by our tour representatives, " + "who will escort you sightseeing in Mathura, the holy birthplace of Lord Krishna. " + "We visit Dwarkadheesh temple, Krishna Janmabhoomi temple, Mathura museum, and Kans Qila. " + "After sightseeing in Mathura, we drive to the neighboring city of Vrindavan, " + "where lord Krishna is said to have spent his childhood. Reach Vrindavan, check into the hotel, " + "and later explore the city of Vrindavan. We visit ISKON Mandir, Banke Bihari temple, " + "and Prem Mandir. After Vrindavan sightseeing, we drive to Gokul, where Lord Krishna’s father " + "Vasudeva had left the newborn to protect him from the fury of Kans. At Gokul, " + "we visit places like Brahmand Ghat and Ramanreti. After Gokul sightseeing, we return to " + "the hotel at Vrindavan for dinner and a night stay."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 2",
                        textDeparture = "Excursion to Nandgaon, Goverdhan, Barsana & back to Vrindavan",
                        placesList = listOf("Nandgaon", "Goverdhan", "Barsana", "Vrindavan"),
                        textExplanation = "The next day we go on an excursion to Nandgaon, Goverdhan, Barsana & Nandagaon, " + "per mythical tales, had been the residential village of Lord Krishna where he resided " + "along with his foster parents Nanda Baba and Ma Yashoda. At Nandgaon, we visit the Nandbhavan, " + "after which we move toward Govardhan. It is well known for the famed Govardhan Parvat, " + "which lord Krishna is said to have lifted on one little finger to protect the people of " + "the village from the torrential downpours sent forth by wrathful Lord Indra. At Govardhan, " + "we visit places like Jatipura temple and Danghati temple. Next, we go to Barsana, " + "the residential village of Lord Krishna’s childhood beloved, Radha. At Barsana, " + "we visit places like the Rangili Mahal and Radha Rani temple. Post excursion, " + "we drive back to Vrindavan. Dinner and night stay at Vrindavan hotel."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 3",
                        textDeparture = "Vrindavan- Agra- Fatehpur Sikri and departure from Agra",
                        placesList = listOf("Vrindavan", "Agra", "Fatehpur Sikri"),
                        textExplanation = "This morning, post breakfast, we leave for Agra, the historic city with many " + "relics from the Mughal era. At Agra, we visit the famous Taj Mahal, Agra fort, and Sikandra. " + "Next, we go to Fatehpur Sikri, a city created by Emperor Akbar to commemorate his victory over " + "Ranthambore and Chittoor. At Fatehpur Sikri, we visit places like Buland Darwaza and the " + "Tomb of Sheik Salim Chisti. Post-visit, you are driven back to Agra and transferred directly " + "to the station, where our representative sees you off, and you depart for your onward journey back home."
                    )
                )
            },
            inclusionsList = getInclusionsList(),
            exclusionsList = getExclusionsList(),
            guidelinesList = getGuidelinesList(),
            fAQsList = getFAQsList()
        ),
        oneDay = false
    )
    val fulDayVarSig1day = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.ful_day_var1day,
        textName = "Full-Day Varanasi Sightseeing",
        textUrl = "",
        textMrp = "₹ 9,000",
        textDiscounted = "₹6,500",
        textDays = "1 Day",
        textNights = "0 Nights",
        textButton = "View Details",
        details = PackageDetails(
            textOverview = "Varanasi, an ancient town of Uttar Pradesh, is one of the holiest pilgrimage destinations for the " + "Hindus. It is considered one of the Saptapuris, i.e., seven sacred places of Hinduism, and is famed " + "enough for its many auspicious Ghats along the Ganges, where several rituals are held day in and out. " + "They say these ceremonies pave the way towards their spiritual goals most successfully. Thus, ceremonies like " + "Pind Daan, Shraddh, etc.",
            daysList = arrayListOf<ItineraryDay>().apply {
                add(
                    ItineraryDay(
                        textDay = "Day 1",
                        textDeparture = "At Varanasi",
                        placesList = listOf("Varanasi"),
                        textExplanation = "We first head for the Kashi Vishwanath temple. This temple is often referred " + "to as the ‘Golden temple’ and is a revered lord Shiva Jyotirlinga. This shrine is " + "held to be some 3500 years old. After Darshan and Puja at Kashi Vishwanath temple, " + "we proceed towards other places of Varanasi such as Sankat Mochan temple, Tulsi Manas temple, " + "Kathwala temple, Bharath Mata temple, Alamgir mosque. \nSankat Mochan temple falls next " + "on our sightseeing agenda. This temple belongs to Lord Hanuman and stands along the Assi river. " + "Freedom fighter and famed educationist Pandit Madan Mohan Malaviya took the initiative " + "of setting up this temple. Prasad at the temple consists in delicious Besan ki Laddoo " + "which you can enjoy here. After Sankat Mochan temple, we go to the Tulsi Manas temple, " + "a beautiful temple made of white marble belonging to Lord Rama. The temple walls bear " + "inscriptions from Tulsidas’ epic Ramcharitmanas. After this, we visit the Kathwala temple. " + "The brilliant woodwork and architecture of the temple will surely captivate your attention. " + "Next, we go to the Bharat Mata Mandir, which belongs to Mother India. The country’s relief " + "map engraved on marble is the chief highlight of this temple. After this, we proceed to " + "visit the Alamgir mosque. This mosque is believed to have been set up by Aurangzeb " + "and shows a beautiful fusion of Mughal and north Indian temple architectures.\nAfter this, " + "we visit some famous Ghats of Varanasi, such as Manikarnika Ghat, Assi Ghat, and Dasaswamedh Ghat. " + "Manikarnika Ghat is considered where one receives Moksha, and Assi Ghat is said " + "to be where all sins are washed away through a holy dip. Dasaswamedh Ghat is famed " + "enough for the evening Ganga Aarti which we will not miss. If there’s time, we might even " + "go for a serene boat ride on the Ganges. After visiting the Ghats, our representative transfers " + "you to the station/airport, sees you off, and you proceed for your onward journey back home. " + "Here ends your one-day tour of Varanasi."
                    )
                )
            },
            inclusionsList = getInclusionsList(),
            exclusionsList = getExclusionsList(),
            guidelinesList = getGuidelinesList(),
            fAQsList = getFAQsList()
        ),
        oneDay = false
    )
    val harRisTour3days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.har_ris3day,
        textName = "Haridwar Rishikesh Tour",
        textUrl = "",
        textMrp = "₹ 15,000",
        textDiscounted = "₹12,250",
        textDays = "3 Days",
        textNights = "2 Nights",
        textButton = "View Details",
        details = PackageDetails(
            textOverview = "Haridwar and Rishikesh are the two most popularly visited pilgrimage destinations in Uttarakhand. " + "These two places are dotted with numerous holy temples and revered Ghats along the banks of the river Ganges. " + "Undertake this amazing Haridwar-Rishikesh tour to delve into the depths of religiosity and tranquility.",
            daysList = arrayListOf<ItineraryDay>().apply {
                add(
                    ItineraryDay(
                        textDay = "Day 1",
                        textDeparture = "Arrive in Delhi – Haridwar (230 km, 5 hrs drive approx.)",
                        placesList = listOf("Delhi", "Haridwar"),
                        textExplanation = "Upon arrival at Delhi airport/railway station, you’ll be greeted by our tour representative, " + "who then drives you down to Haridwar, the holy city also often referred to as Gateway to God’s World. " + "Upon reaching, check into the hotel and later visit the famous ‘Har-ki-Pauri’ bathing Ghat of " + "Haridwar. Har-ki-Pauri is an auspicious bathing Ghat that is believed to have been " + "visited by Lord Vishnu and Lord Shiva in the Vedic era. In the evening, we attend the " + "auspicious Ganga Aarti at Har-ki-Pauri Brahmakund. The sight of thousands of gleaming golden " + "Diyas embellishing the stream of the Ganges would give you a lovely experience. " + "After participating in the Ganga Aarti, we visit the local markets for souvenirs. " + "Back to hotel for dinner and night stay."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 2",
                        textDeparture = "Haridwar – Rishikesh (27 km, 43 mins drive approx.)",
                        placesList = listOf("Haridwar", "Rishikesh"),
                        textExplanation = "The next day after breakfast, we set out for a short sightseeing of Haridwar during " + "the first half of the day. The places covered are Mansa Devi temple, Chandi Devi temple, " + "Daksha Mahadev temple, Pawan Dham temple, Bilkeshwar Mahadev temple, Bharat Mata Mandir, " + "and Dudhadhari temple.\nAfter completing Haridwar sightseeing, in the latter half of the day, " + "we leave for Rishikesh, another serene city along the banks of the holy Ganges. " + "Rishikesh is well known for its many renowned yoga therapy centers and Ashrams and " + "thus often referred to as the country’s ‘yoga capital’. On reaching, check into the " + "hotel and later proceed to view the splendid Ganga Aarti at Rishikesh Triveni Ghat. " + "Back to hotel for dinner and night stay."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 3",
                        textDeparture = "Rishikesh – Delhi (254 km, 6 hrs drive approx.)",
                        placesList = listOf("Rishikesh", "Delhi"),
                        textExplanation = "The next day after breakfast, we will go for a short sightseeing trip to Rishikesh. " + "The places covered during this trip are Laxman Mandir, Neelkanth Mahadev temple, Ram Jhula, " + "Laxman Jhula, Tera Manzil temple, Geeta Bhavan, and Swami Dayananda Ashram." + "\nAfter completing Rishikesh sightseeing, we drive back to Delhi. Upon reaching, " + "you are transferred to the airport/railway station, where our representative sees you off, " + "and you proceed with the journey back home."
                    )
                )
            },
            inclusionsList = getInclusionsList(),
            exclusionsList = getExclusionsList(),
            guidelinesList = getGuidelinesList(),
            fAQsList = getFAQsList()
        ),
        oneDay = false
    )
    val matVrindavanAgr4days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.mat_vrin4day,
        textName = "Mathura Vrindavan Agra Tour",
        textUrl = "",
        textMrp = "₹ 18,000",
        textDiscounted = "₹15,500",
        textDays = "4 Days",
        textNights = "3 Nights",
        textButton = "View Details",
        details = PackageDetails(
            textOverview = "Northern India is dotted with numerous religious destinations. Mathura and Vrindavan are two " + "such very holy destinations in the North Indian state of Uttar Pradesh which pilgrims make it a " + "point to visit at least once in their lifetime. The nearby city of Agra is often clubbed with the " + "Mathura-Vrindavan tour package to make the tour a fulfilling experience.",
            daysList = arrayListOf<ItineraryDay>().apply {
                add(
                    ItineraryDay(
                        textDay = "Day 1",
                        textDeparture = "Arrive in Delhi – Mathura",
                        placesList = listOf("Delhi", "Mathura"),
                        textExplanation = "Upon your arrival at the Delhi airport/railway station, you are greeted by " + "our tour representative, who then drives you down to Mathura. On reaching, we check into " + "the hotel and later explore Mathura, Lord Krishna’s idyllic birthplace. The places covered " + "during this sightseeing tour are Krishna Janmabhumi temple, Dwarkadheesh temple, Radha Kund, " + "Vishram Ghat, and Mathura Government museum. Back to hotel for dinner and the night stay."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 2",
                        textDeparture = "Mathura – Vrindavan (11 km, 26 minutes approx.)",
                        placesList = listOf("Mathura", "Vrindavan"),
                        textExplanation = "The next day after breakfast, we leave for Vrindavan, where lord Krishna is " + "said to have spent much of his childhood and youthful years when he was in a " + "divine love affair with Sri Radhika. On reaching, check into the hotel, " + "and later proceed for a sightseeing of Vrindavan. The places covered during the Vrindavan " + "tour are Banke Bihari temple, Sri Radha Raman temple, Madan Mohan temple, Prem Mandir, " + "Vrindavan Chandrodaya, and ISKCON Mandir. Back to hotel for dinner and night stay."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 3",
                        textDeparture = "Vrindavan – Agra (69 km, 1.5 hrs approx.)",
                        placesList = listOf("Vrindavan", "Agra"),
                        textExplanation = "The next day we hit the road for Agra, the gorgeous city of the Poetry " + "of Love in stone- the Taj Mahal. On reaching, check into the hotel and later visit " + "the mesmerizing Taj and the historical Agra fort. The tour takes you back in time " + "to the royal era of the Mughals. Get back to the hotel for dinner and the night stay."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 4",
                        textDeparture = "Agra – Delhi (231 km, approx. 3 hrs)",
                        placesList = listOf("Agra", "Delhi"),
                        textExplanation = "The next day after breakfast, we travel back to Delhi. Upon reaching, " + "you are transferred directly to the airport/railway station, where our representative sees you off, " + "and you depart for your onward journey back home."
                    )
                )
            },
            inclusionsList = getInclusionsList(),
            exclusionsList = getExclusionsList(),
            guidelinesList = getGuidelinesList(),
            fAQsList = getFAQsList()
        ),
        oneDay = false
    )
    val varSarSig3days = TravelPackage(
        ism = "Hinduism, Buddhism, Buddhism",
        viewType = 1,
        image = R.drawable.var_sar3day,
        textName = "Varanasi & Sarnath Sightseeing",
        textUrl = "",
        textMrp = "₹ 9,000",
        textDiscounted = "₹6,000",
        textDays = "3 Days",
        textNights = "2 Nights",
        textButton = "View Details",
        details = PackageDetails(
            textOverview = "Varanasi Tour Package is one of the most demanded pilgrimages, visited by millions yearly." + "\nThe holy city of Varanasi has always been the ‘Moksha Dwar’ for pilgrims who visit the city " + "with the hope of receiving salvation from the cycle of birth and rebirth. The city is well known " + "for its Kashi Vishwanath temple, Lord Shiva Jyotirlingam. Also, the Ghats of Varanasi, the Banarasi Paan, " + "and the famous Banarasi silk are other items that make Varanasi occupy a definite place on the country’s map." + "\nAs one of the most prominent cities in Uttar Pradesh, Varanasi is pretty different from the other " + "71 districts in the state. At the time of the 2011 census, eight blocks and 1329 villages in this district." + "\nVaranasi grew as an important industrial center, famous for its muslin and silk fabrics, perfumes, " + "ivory works, and sculpture. It is believed that Lord Buddha gave his first sermon on “the setting " + "in motion of the wheel of Dharma” on the magical land of Varanasi only around 528 BCE.",
            daysList = arrayListOf<ItineraryDay>().apply {
                add(
                    ItineraryDay(
                        textDay = "Day 1",
                        textDeparture = "Arrival at Varanasi",
                        placesList = listOf("Varanasi"),
                        textExplanation = "Upon arrival at the nearest railway station/airport, you’ll be greeted by " + "our tour representative, who then escorts you to the hotel. Check into the hotel and " + "later watch the spell bounding Ganga Aarti at the Dasaswamedh Ghat. You could also " + "take a boat ride on the Ganges while watching the Ganga Aarti. Back to hotel for dinner and night stay."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 2",
                        textDeparture = "Varanasi – Sarnath – Varanasi",
                        placesList = listOf("Varanasi", "Sarnath"),
                        textExplanation = "Early morning, we embark on a spiritual boat ride on the Ganges river. " + "It is an out-of-the-world experience as you sail past the Ghats and catch glimpses of " + "people taking their holy dip, offering Surya Namaskar, chanting mantras, and so on. " + "Later, we will visit the famous Kashi Vishwanath temple, followed by other temples such " + "as Sankat Mochan temple, Annapurna temple, Manas Mandir, Bharat Mata Mandir, and BHU. " + "Back to the hotel for breakfast. \nLater in the day, we make an excursion to Sarnath, " + "the historical place where Lord Buddha is believed to have delivered his first sermon. " + "The places we visit at Sarnath temple include Chaukhandi Stupa, Dhamekh Stupa, Buddha temples, " + "and the archaeological museum.\nWe will be back in Varanasi by evening and can use " + "the evening for some shopping. Back to hotel for dinner and night stay."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 3",
                        textDeparture = "Departure from Varanasi",
                        placesList = listOf("Varanasi"),
                        textExplanation = "After breakfast, you will be transferred to the railway station/airport, " + "where our representative will see you off, and you will depart for your onward journey back home."
                    )
                )
            },
            inclusionsList = getInclusionsList(),
            exclusionsList = getExclusionsList(),
            guidelinesList = getGuidelinesList(),
            fAQsList = getFAQsList()
        ),
        oneDay = false
    )
    val panKedYat16days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.pan_ked16day,
        textName = "Panch Kedar Yatra",
        textButton = "View Details",
        textNights = "15 Nights",
        textDays = "16 Days",
        textDiscounted = "₹45,000",
        textMrp = "₹ 48,000",
        textUrl = "",
        details = PackageDetails(
            textOverview = "Panch Kedar Pilgrimage Tour includes a Yatra to the five holiest Lord " + "Shiva shrines scattered across a route of length 170 km in the Garhwal Himalayan region.\nThe five Panch " + "Kedar shrines include the shrines of Kedarnath, Tungnath, Rudranath, Madhyamaheshwar, and Kalpeshwar. The " + "valley between Alaknanda and Bhagirathi rivers has all these 5 Kedar. Panch Kedar Yatra is one of " + "the most strenuous pilgrimage tours, as most of the journey needs to be through trekking. Embark on this " + "auspicious Panch Kedar tour to get spiritual solace.",
            daysList = arrayListOf<ItineraryDay>().apply {
                add(
                    ItineraryDay(
                        textExplanation = "Upon arrival at Delhi airport/railway station, you’ll be received by our " + "tour representative, who then drives you to the sacred city of Haridwar. Haridwar is often " + "called the ‘Gateway to God’s world’ as all prime pilgrimage Yatra to the high Himalayas " + "start from Haridwar. On reaching, check into the hotel and later a short sightseeing of " + "Haridwar. The places we visit during this sightseeing tour are Mansa Devi temple, Chandi " + "Devi temple, Maya Devi temple, and Daksheswara Mahadev temple. In the evening, we go " + "to the famous ‘Har-ki-Pauri’ Ghat of Haridwar to watch the enchanting Ganga Aarti. Back " + "to hotel for dinner and night stay.",
                        textDay = "Day 1",
                        textDeparture = "Arrive in Delhi - Haridwar",
                        placesList = listOf("Delhi", "Haridwar")
                    )
                )
                add(
                    ItineraryDay(
                        textDeparture = "Rudraprayag- Devprayag",
                        textDay = "Day 2",
                        placesList = listOf("Rudraprayag", "Devprayag"),
                        textExplanation = "This morning we drive to Rudraprayag en route to Devprayag. As Bhagirathi and " + "Alaknanda rivers meet at Devprayag, we see the formation of the Ganga river. From Devprayag, " + "we head straight to Rudraprayag and check into the hotel. In the evening, we do a " + "Darshan at the Koteshwar Mahadev temple. Back to hotel for dinner and night stay."
                    )
                )
                add(
                    ItineraryDay(
                        textExplanation = "This morning we leave for Gaurikund post breakfast. Gaurikund is the " + "base camp for Kedarnath Yatra trek. On reaching Gaurikund, we start our 14 km-long trek towards " + "Kedarnath Dham. Apart from being one of the Uttarakhand Chardham, the Kedarnath shrine is also " + "one of the Panch Kedar. As per myths, as lord Shiva dived into the ground in the guise of " + "a bull to evade the Pandavas, his hump was left behind at Kedarnath. Thus the Jyotirlingam of Kedarnath " + "Ji enshrined here is shaped like a bull’s hump. On reaching Kedarnath, check into the hotel " + "and attend the evening Aarti at Kedarnath Dham. Back to hotel for dinner and night stay.",
                        textDay = "Day 3",
                        textDeparture = "Rudraprayag- Gaurikund- Kedarnath",
                        placesList = listOf("Rudraprayag", "Gaurikund", "Kedarnath")
                    )
                )
                add(
                    ItineraryDay(
                        textDeparture = "Kedarnath- Guptakashi",
                        textDay = "Day 4",
                        placesList = listOf("Kedarnath", "Guptakashi"),
                        textExplanation = "Early in the morning, go to the Kedarnath shrine again and attend the morning " + "Aarti therein. After Darshan, trek your way back to Gaurikund and then drive towards Guptakashi. " + "Guptakashi is where lord Shiva is said to have concealed himself for some time from " + "the notice of the Pandavas. On reaching Guptakashi, check into the hotel for dinner and the night stay."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 5",
                        textDeparture = "Guptakashi- Gaundhar",
                        placesList = listOf("Guptakashi", "Gaundhar"),
                        textExplanation = "This morning after a light, energy-packed breakfast, we start our 17 km long " + "trek towards Gaundhar. On reaching, we put up for the night in the camps."
                    )
                )
                add(
                    ItineraryDay(
                        textDeparture = "Gaundhar- Madhyamaheshwar",
                        textDay = "Day 6",
                        placesList = listOf("Gaundhar", "Madhyamaheshwar"),
                        textExplanation = "This morning we will resume our trek from Gaundhar toward Madhyamaheshwar. " + "After covering a distance of 17 km, we reach Madhyamaheshwar. On reaching, check into camps " + "for resting and freshening up. Later proceed to visit the Madhyamaheshwar shrine of lord " + "Shiva. According to legends, the lord’s belly or middle part fell on this spot, and " + "thus the name Madhyamaheshwar. Back to camp for dinner and night stay."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 7",
                        textDeparture = "Madhyamaheshwar- Gaundhar",
                        placesList = listOf("Madhyamaheshwar", "Gaundhar"),
                        textExplanation = "The next day we trek our way back to Gaundhar and check into camps for dinner and the night stay."
                    )
                )
                add(
                    ItineraryDay(
                        textDeparture = "Gaundhar- Ukhimath",
                        textDay = "Day 8",
                        placesList = listOf("Gaundhar", "Ukhimath"),
                        textExplanation = "This morning we first trek a distance of 12 km to Jagasu, and from there, " + "we drive at once to Ukhimath. According to legends, this is where Usha, Vanasura’s daughter, " + "got wedded to Aniruddha, Lord Krishna’s grandson. On reaching Ukhimath, check into camps " + "for dinner and the night stay."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 9",
                        textDeparture = "Ukhimath- Chopta- Tungnath- Chopta",
                        placesList = listOf("Ukhimath", "Chopta", "Tungnath"),
                        textExplanation = "This morning we first reach Chopta, and from Chopta, we start our 4 km trek " + "towards Tungnath. On arriving at Tungnath, we have a Darshan at the lord Shiva shrine " + "of Tungnath. According to legends, Tungnath is where the ‘Baahu’ or hands of the " + "lord appeared after he disintegrated his body. After Darshan, we trek our way back " + "to Chopta and retire to camps for dinner and the night stay."
                    )
                )
                add(
                    ItineraryDay(
                        textDeparture = "Chopta- Panargufa",
                        textDay = "Day 10",
                        placesList = listOf("Chopta", "Panargufa"),
                        textExplanation = "After breakfast, we leave for Sagar. On reaching, we start our ten km-long " + "trek towards Panargufa. On reaching, check into camps for dinner and the night stay."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 11",
                        textDeparture = "Panargufa- Rudranath",
                        placesList = listOf("Panargufa", "Rudranath"),
                        textExplanation = "After breakfast, we start our 12 km trek towards Rudranath. On reaching, we " + "pay a Darshan at the Rudranath shrine of Lord Shiva. According to legends, the face of " + "Rudra, or the lord, had fallen at this spot. Post Darshan, we retire to camps for " + "dinner and the night stay."
                    )
                )
                add(
                    ItineraryDay(
                        textDeparture = "Rudranath- Dumuk",
                        textDay = "Day 12",
                        placesList = listOf("Rudranath", "Dumuk"),
                        textExplanation = "After breakfast, we start our 18 km-long trek towards Dumuk. On reaching, we " + "retire to camps for dinner and the night stay."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 13",
                        textDeparture = "Dumuk- Kalpeshwar",
                        placesList = listOf("Dumuk", "Kalpeshwar"),
                        textExplanation = "After breakfast, we start our 14 km trek towards the Kalpeshwar Mahadev shrine of " + "Lord Shiva. According to legends, the Jata or matted locks of the lord had fallen at " + "this place. Post Darshan, we retire to camps for dinner and the night stay."
                    )
                )
                add(
                    ItineraryDay(
                        textDeparture = "Kalpeshwar- Rudraprayag",
                        textDay = "Day 14",
                        placesList = listOf("Kalpeshwar", "Rudraprayag"),
                        textExplanation = "After breakfast, we trek 9 km to Helang and proceed towards Rudraprayag by road. " + "Upon reaching, check into the hotel for dinner and the night stay."
                    )
                )
                add(
                    ItineraryDay(
                        textExplanation = "This morning we hit the road for Rishikesh, the country’s ‘Yoga Capital’ and " + "a serene, idyllic place along the banks of the Ganges. On reaching, check into the " + "hotel and later proceed to visit some of the major attractions of Rishikesh, such as Ram " + "Jhula, Laxman Jhula, Neelkanth Mahadev temple, Parmarth Niketan, Vashistha Gufa. We enjoy " + "the holy Ganga Aarti during the evening at the Triveni Sangam Ghat of Rishikesh. Back " + "to hotel for dinner and night stay.",
                        textDay = "Day 15",
                        textDeparture = "Rudraprayag- Rishikesh",
                        placesList = listOf("Rudraprayag", "Rishikesh")
                    )
                )
                add(
                    ItineraryDay(
                        textDeparture = "Rishikesh- Delhi",
                        textDay = "Day 16",
                        placesList = listOf("Rishikesh", "Delhi"),
                        textExplanation = "This morning you are driven to Delhi and dropped at the station/airport, where our " + "representative sees you off, and you depart for your onward journey back home with " + "lots of purity and piousness in your heart."
                    )
                )
            },
            inclusionsList = getInclusionsList(),
            exclusionsList = getExclusionsList(),
            guidelinesList = getGuidelinesList(),
            fAQsList = getFAQsList()
        ),
        oneDay = false
    )
    val risFulDaySig1day = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.ris_ful_day1day,
        textName = "Rishikesh Full-Day Sightseeing",
        textUrl = "",
        textMrp = "₹ 6,000",
        textDiscounted = "₹3,500",
        textDays = "1 Day",
        textNights = "0 Nights",
        textButton = "View Details",
        details = PackageDetails(
            textOverview = "Rishikesh, another holy and serene town of Uttarakhand set along the Ganges, is a major " + "attraction for all spiritual seekers from different corners of the nation and globe.\nThe town has " + "many ashrams, yoga therapy centers, temples, and sacred Ghats. Rishikesh has many metaphors in its " + "honor, such as Gateway to the Himalayas and the Yoga capital of India. The ambiance of Rishikesh is " + "ideal for meditation, yoga, and spiritual learning, and thus many aspiring scholars come here to " + "study Hindu theology, philosophy, religion, and practices. Browse our one-day-long Rishikesh sightseeing tour " + "package and witness the charm and glory of this sacred city.",
            daysList = arrayListOf<ItineraryDay>().apply {
                add(
                    ItineraryDay(
                        textDay = "Day 1",
                        textDeparture = "Arrive in Rishikesh",
                        placesList = listOf("Rishikesh"),
                        textExplanation = "Upon arrival at Rishikesh railway station, you’ll be greeted by our tour representative, " + "who then escorts you to the hotel for luggage keeping and freshening. After that, " + "we proceed to visit the various sightseeing places of Rishikesh. The locations included in " + "our sightseeing agenda of Rishikesh are Triveni Sangam Ghat, Raghunath Temple, Ram Jhula, " + "Lakshman Jhula, Laxman Mandir, Rishikund, Tera Manzil temple, Bharat Mandir, " + "Neel Kanth Mahadev.\nFirst of all, we visit the famous Triveni Sangam Ghat of Rishikesh. " + "It is where the three sacred rivers, Ganga, Yamuna, and Saraswati, meet each other. " + "After visiting Triveni Sangam Ghat, you could take a holy dip into the river and " + "then go for a short boat ride.\nAfter this, we pay a Darshan at the Geeta temple and " + "Laxmi Narayan temple near the Ghat and then move towards Raghunath temple, " + "another temple close to the Ghat. This temple belongs to lord Rama and is well " + "known for the many Hindu rituals performed there.\nRishikund, a water reservoir with great " + "mythical significance, lies in the vicinity of Raghunath temple. This Kund is said " + "to have been created by Saint Kubz and then got blessed by Yamuna, who poured her " + "water into it to make it into a pond.\nNext, we visit the Laxman temple, followed by the " + "Tera Manzil temple along the Ganges. Laxman temple is the abode of lord Laxman, " + "brother of Lord Rama. The splendid carvings on the temple captivate all attention. " + "After Laxman temple, we visit the 13-storied Tera Manzil temple. It is an enormous " + "structure belonging to various gods and goddesses of the Hindu pantheon.\nNext, " + "we visit the two most significant landmark attractions of Rishikesh; the Ram Jhula and Laxman Jhula. " + "These are two suspension bridges made of iron. These bridges come with great mythological significance. " + "There are many small shrines and Ashrams around these two Jhulas, which we could consider visiting" + " if time permits. Some of those Ashrams and temples are Geeta Bhavan, Parmarth Niketan, " + "Yog Niketan, Swargashram, Beatles Ashram, Shiva Nanda Ashram, and Sachcha Akhileshwar Mahadev temple."
                    )
                )
            },
            inclusionsList = getInclusionsList(),
            exclusionsList = getExclusionsList(),
            guidelinesList = getGuidelinesList(),
            fAQsList = getFAQsList()
        ),
        oneDay = false
    )
    val vinPilTour1day = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.vin_pil1day,
        textName = "Vindhyachal Pilgrimage Tour",
        textUrl = "",
        textMrp = "₹ 9,000",
        textDiscounted = "₹6,000",
        textDays = "1 Day",
        textNights = "0 Nights",
        textButton = "View Details",
        details = PackageDetails(
            textOverview = "Vindhyachal is one of the most sacred places in the country, known for its many Shakti Peeth " + "and Bhairav temples, plus other pilgrimage sites associated with the life of Lord Rama and Lord Krishna." + "\nA tour of Vindhyachal is one of the loftiest spiritual experiences pilgrims from far and wide " + "wish to grab. Take this 1-day Vindhyachal tour to feel the spiritual powers of Shakti and Shiva, " + "the two cardinal forces that control the entire universe.",
            daysList = arrayListOf<ItineraryDay>().apply {
                add(
                    ItineraryDay(
                        textDay = "Day 1",
                        textDeparture = "Arrive at Varanasi Airport/Railway Station",
                        placesList = listOf("Varanasi"),
                        textExplanation = "You will get greeted by our tour representative, who then drives you down " + "toward Vindhyachal, a municipality located in UP’s Mirzapur district and amid the " + "holy towns of Varanasi and Allahabad. To reach Vindhyachal, one has to drive for " + "about 15hrs over a distance of about 70 km.\nUpon reaching, we directly proceed sightseeing in " + "this sacred town. Places covered during this Vindhyachal sightseeing tour are the " + "temple of Ma Vindhyavasini, Kali Khoh temple, Kankali Devi temple, Ashtabhuja temple, " + "Sita Kund, and Ram Gaya Ghat.\nAfter Darshan and Puja at these temples, you are driven " + "back to Varanasi, where our representative sees you off, and that ends this memorable 1-day " + "Vindhyachal tour."
                    )
                )
            },
            inclusionsList = getInclusionsList(),
            exclusionsList = getExclusionsList(),
            guidelinesList = getGuidelinesList(),
            fAQsList = getFAQsList()
        ),
        oneDay = false
    )
    val kasPrayagVin4days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.kas_pray4day,
        textName = "Kashi Prayag Vindhyachal Tour",
        textUrl = "",
        textMrp = "₹ 10,000",
        textDiscounted = "₹7,500",
        textDays = "4 Days",
        textNights = "3 Nights",
        textButton = "View Details",
        details = PackageDetails(
            textOverview = "The Kashi-Prayag-Vindhyachal tour takes you through the most sacred places in our country. " + "Setting out for this short spiritual tour will impart you with some of the loftiest spiritual experiences. " + "So, set out today!",
            daysList = arrayListOf<ItineraryDay>().apply {
                add(
                    ItineraryDay(
                        textDay = "Day 1",
                        textDeparture = "Arrive at Kashi & Kashi Darshan",
                        placesList = listOf("Kashi"),
                        textExplanation = "On reaching Kashi station, you’ll be greeted by our tour representative, who will " + "escort you to the hotel. Check in, freshen up, and later visit the main attractions of " + "Kashi, such as Kashi Vishwanath temple; Lord Shiva Jyotirlingam, Sankat Mochan Hanuman temple, " + "Tulsi Manas temple, kathawala Temple, Bharat Mata Mandir. In the evening, attend the Ganga " + "Aarti of Kashi’s famous Ghat, Dasaswamedh Ghat. Back to hotel for dinner and night stay."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 2",
                        textDeparture = "Kashi – Prayag (Allahabad)",
                        placesList = listOf("Kashi", "Prayag", "Allahabad"),
                        textExplanation = "The next day post breakfast, we hit the road for Prayag [Allahabad]. On reaching, check " + "into the hotel and later proceed for a city tour visiting places like Triveni Sangam (Confluence " + "of River Ganga, Yamuna & Saraswati), Anand Bhawan, Allahabad fort, Khusro Bagh, Someshwar " + "Mahadev temple. Back to hotel for dinner and night stay."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 3",
                        textDeparture = "Prayag – Vindhyachal",
                        placesList = listOf("Prayag", "Vindhyachal"),
                        textExplanation = "This morning, we move towards Vindhyachal. On reaching Vindhyachal, check into the " + "hotel and later proceed to visit the most important places of Vindhyachal, like Vindhyavasini temple " + "and Ashtabhuja temple. Back to hotel for dinner and night stay."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 4",
                        textDeparture = "Vindhyachal – Varanasi",
                        placesList = listOf("Vindhyachal", "Varanasi"),
                        textExplanation = "This morning, we move towards Vindhyachal. On reaching Vindhyachal, check into the " + "hotel and later proceed to visit the most important places of Vindhyachal, like Vindhyavasini temple " + "and Ashtabhuja temple. Back to hotel for dinner and night stay."
                    )
                )
            },
            inclusionsList = getInclusionsList(),
            exclusionsList = getExclusionsList(),
            guidelinesList = getGuidelinesList(),
            fAQsList = getFAQsList()
        ),
        oneDay = false
    )
    val southIndTem12days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.sout_ind12day,
        textUrl = "",
        textMrp = "₹ 51,000",
        textDays = "12 Days",
        textDiscounted = "₹48,000",
        textNights = "11 Nights",
        textButton = "View Details",
        textName = "South India Temples Tour",
        details = PackageDetails(
            textOverview = "By grabbing our South India temple tour package, you get a broad " + "opportunity to explore the majestic temples of South India. Apart from being a pilgrimage journey, this temples " + "tour is also a way to gain insight into the architectural excellence and engineering prowess attained many " + "centuries back and still survives withstanding all the ravages of time. Embark on this comprehensive South India " + "temple tour for a fulfilling travel experience",
            daysList = arrayListOf<ItineraryDay>().apply {
                add(
                    ItineraryDay(
                        textDay = "Day 1",
                        textExplanation = "On arrival at Chennai airport/railway station, our tour representative will receive you, " + "then escort you to the hotel for dinner and the night's stay.",
                        textDeparture = "Arrive in Chennai",
                        placesList = listOf("Chennai")
                    )
                )
                add(
                    ItineraryDay(
                        textDeparture = "Excursion to Tirupati and back to Chennai",
                        textDay = "Day 2",
                        placesList = listOf("Tirupati", "Chennai"),
                        textExplanation = "After breakfast, we make an excursion to the famous pilgrimage destination of Tirupati. " + "On reaching, we travel to the Tirumala hilltop to visit the famous shrine of Venkateswara " + "Balaji. Post Darshan, we go to other important temples of Tirupati such as Sri " + "Padmavati Samovar temple, Sri Kalyana Venkateswara Swamy temple, Sri Kapileshwara temple, Sri " + "Govindaraja Swamy temple, Sri Kodandaramaswamy temple, Srinivasa Mangapuram and Tiruchanoor. After " + "the Tirupati temple tour, we return to our hotel in Chennai for dinner and the night stay."
                    )
                )
                add(
                    ItineraryDay(
                        textExplanation = "After breakfast, we visit the ancient temple town of Kanchipuram en route to " + "Mahabalipuram. Kanchipuram is dotted with numerous temples and is famous worldwide for its " + "exceptional Kanchipuram silks. At Kanchipuram, we visit the temples like Kamakshi temple, " + "Ekambareswarar temple, Vaikunta Perumal temple, and Kailasanathar temple. Most of these " + "temples date back to as far as the 7th century. After the Kanchipuram temple tour, we " + "head straight for Mamallapuram. On reaching, we check into the hotel and later will " + "proceed for a short Mamallapuram tour. Mamallapuram is an idyllic temple town set along " + "the coastal belt. The places covered during this tour include Thirukadalmallai, Pancha Pandava Rathas, " + "a pagoda-style shore temple, and the Arjuna cave of penance. Temple photography on the " + "shore at twilight is the main tourist attraction. Back to hotel for dinner and night stay.",
                        textDay = "Day 3",
                        textDeparture = "Chennai- Kanchipuram- Mamallapuram",
                        placesList = listOf("Chennai", "Kanchipuram", "Mamallapuram")
                    )
                )
                add(
                    ItineraryDay(
                        textDeparture = "Mamallapuram- Tiruvannamalai- Pondicherry",
                        textDay = "Day 4",
                        placesList = listOf("Mamallapuram", "Tiruvannamalai", "Pondicherry"),
                        textExplanation = "The next day we visit the ancient Heritage site of Tiruvannamalai en route to " + "Pondicherry. We go to the 9th-10th century-old Arunachala temple atop Arunachala hill at " + "Tiruvannamalai. It is a lord Shiva temple and is notable for its mammoth Gopurams. After Darshan, " + "we drive straight to Pondicherry/Puducherry and check into the hotel for dinner and the night stay."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 5",
                        textDeparture = "At Pondicherry",
                        placesList = listOf("Pondicherry"),
                        textExplanation = "The next day we explore the neat and calm city of Pondicherry. This once-upon-a-time French " + "colony still retains vestiges of French influence. The sites we visit at Pondicherry include " + "Varadaraja Perumal temple, Manakula Vinayagar temple, Vedapureeswarar temple, Sri Aurobindo " + "Asharam, Sacre Coeur Church, Pondicherry museum. Back to hotel for dinner and night stay."
                    )
                )
                add(
                    ItineraryDay(
                        textExplanation = "The next day we visit Chidambaram, another holy land en route to Thanjavur/Tanjore. " + "At Chidambaram, we go to the Thillai Nataraja temple that enshrines lord Shiva not " + "as a lingam but as Nataraja, the cosmic dancer of Tandava nritya. After Darshan at the " + "Chidambaram temple, we drive straight to Thanjavur and check into the hotel for dinner " + "and the night stay.",
                        textDay = "Day 6",
                        textDeparture = "Pondicherry- Chidambaram- Thanjavur",
                        placesList = listOf("Pondicherry", "Chidambaram", "Thanjavur")
                    )
                )
                add(
                    ItineraryDay(
                        textDeparture = "Thanjavur- Tiruchirappalli",
                        textDay = "Day 7",
                        placesList = listOf("Thanjavur", "Tiruchirappalli"),
                        textExplanation = "This morning, we explore the beautiful and culturally enriched city of Thanjavur. " + "Thanjavur is the seat of a unique painting form referred to as the Tanjore paintings. It " + "is also the place where traditional Carnatic music took its birth. At Thanjavur, we " + "visit the famous Brihadeeswara temple. UNESCO has recognized this temple as a world " + "heritage site. Brihadeeswara is one of the most ancient Lord Shiva temples. Later during " + "the day, we leave for Tiruchirappalli or Trichy. Upon reaching, check into the hotel " + "for dinner and the night stay."
                    )
                )
                add(
                    ItineraryDay(
                        textExplanation = "Next morning post breakfast, we set out to explore Trichy or Tiruchirapalli. First, " + "we visit the island of Srirangam, where the famous temple of Sri Ranganathaswamy stands. This temple " + "has one of the tallest gopurams in the country. This Gopuram dominates the skyline for " + "many miles around. Apart from the Ramanathaswamy temple, we also visit the attention-grabbing historic " + "landmark of Trichy, the rock fort. Later during the day, we hit the road for Madurai. " + "Upon reaching, check into the hotel for dinner and the night stay.",
                        textDay = "Day 8",
                        textDeparture = "Tiruchirappalli- Madurai",
                        placesList = listOf("Tiruchirappalli", "Madurai")
                    )
                )
                add(
                    ItineraryDay(
                        textDeparture = "At Madurai",
                        textDay = "Day 9",
                        placesList = listOf("Madurai"),
                        textExplanation = "Day 9 is for the exploration of Madurai. Madurai is often called the Lotus " + "city and has a history that dates back nearly 2500 years. We first visit the famous " + "Meenakshi Sundareswarar temple of Madurai. This temple has the goddess Parvati as its " + "presiding deity. However, Sundareswarar, i.e., Lord Shiva, is worshipped here with immense " + "pomp and grandeur. According to myths, Lord Sundareswarar got wedded to Goddess Meenakshi " + "at this place. After Darshan at the Meenakshi temple, we also visit the other attractions " + "of Madurai, such as Vandiyur Mariamman Tank and Thirumalai Nayak palace. Back to hotel " + "for dinner and night stay."
                    )
                )
                add(
                    ItineraryDay(
                        textExplanation = "This morning we leave for the shore destination of Kanyakumari, located on the " + "southernmost tip of the Indian peninsula. On reaching, check into the hotel and later " + "explore the enchanting Kanyakumari. The places you visit include Kumari Amman temple, Swami " + "Vivekananda memorial, and the Thiruvalluvar statue. Legends tell that at Kanyakumari, Devi " + "Parvati had undertaken strict penance to win lord Shiva as her husband, and she did " + "that in the guise of a young virgin girl. Back to hotel for dinner and night stay.",
                        textDay = "Day 10",
                        textDeparture = "Madurai- Kanyakumari",
                        placesList = listOf("Madurai", "Kanyakumari")
                    )
                )
                add(
                    ItineraryDay(
                        textDeparture = "Kanyakumari- Trivandrum",
                        textDay = "Day 11",
                        placesList = listOf("Kanyakumari", "Trivandrum"),
                        textExplanation = "The next day we head for Trivandrum. On reaching, check into the hotel and " + "later explore the city of Trivandrum. The sites we visit are Padmanabhaswamy temple and Napier " + "museum. Padmanabhaswamy Temple is a lord Vishnu shrine. The shrine grabs all attention " + "due to its exquisite murals and ornate carvings. Napier museum dates back to the " + "19th century and exhibits some rare collections. Back to hotel for dinner and night stay."
                    )
                )
                add(
                    ItineraryDay(
                        textExplanation = "This morning you are dropped at Trivandrum railway station/airport, where our " + "representative sees you off, and you depart for your journey back home with lots of " + "happy memories of the tour.",
                        textDay = "Day 12",
                        textDeparture = "Departure from Trivandrum",
                        placesList = listOf("Trivandrum")
                    )
                )
            },
            inclusionsList = getInclusionsList(),
            exclusionsList = getExclusionsList(),
            guidelinesList = getGuidelinesList(),
            fAQsList = getFAQsList()
        ),
        oneDay = false
    )
    val ramJyotirlingaPil4days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.ram_jyot4day,
        textName = "Rameswaram Jyotirlinga Pilgrimage",
        textUrl = "",
        textMrp = "₹ 12,000",
        textDiscounted = "₹9,500",
        textDays = "4 Days",
        textNights = "3 Nights",
        textButton = "View Details",
        details = PackageDetails(
            textOverview = "Rameswaram Jyotirlingam also called the Ramanathaswamy shrine of Lord Shiva, is located off " + "the coast of the South Indian state of Tamil Nadu upon the Rameswaram island.\nThe shrine belongs " + "to the Jyotirlingam of Lord Shiva, one of the 12 auspicious Jyotirlinga that our country has. Embark " + "on this short Rameswaram Jyotirlingam tour to discover the beauty and spirituality of Rameswaram.",
            daysList = arrayListOf<ItineraryDay>().apply {
                add(
                    ItineraryDay(
                        textDay = "Day 1",
                        textDeparture = "Arrive in Madurai and Madurai to Rameswaram",
                        placesList = listOf("Madurai", "Rameswaram"),
                        textExplanation = "Upon arrival at Madurai airport/railway station, you’ll be greeted by our " + "tour representative, who then drives you to Rameswaram. It takes you 3 hrs approx to " + "reach Rameswaram by road. On reaching, check into the hotel and enjoy rest and leisure " + "during the evening. Dinner and night stay in hotel."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 2",
                        textDeparture = "Rameswaram Darshan",
                        placesList = listOf("Rameswaram"),
                        textExplanation = "Next morning we set out for our Rameswaram Tirth Yatra. Rameswaram is a " + "small picturesque island situated in the Gulf of Mannar off Tamil Nadu’s coast. The island " + "is well known for the Ramanathaswamy temple, a Jyotirlinga shrine of Lord Shiva. The " + "common belief is that if one offers Puja at this shrine, one gets respite from their mortal " + "sins. Legends hold that at this spot, Lord Rama had worshipped Lord Shiva after returning " + "victorious from Lanka. He wanted to get blessed by Lord Shiva so that the sin he " + "had committed by killing Ravana could be absolved. After offering Puja at the temple, " + "you could take a holy bath in the sea of Rameswaram. We’ll have lunch outside today and " + "then stroll on the other scenic beaches, such as Dhanushkodi beach, Olaikuda beach, and " + "Pamban beach. Back to hotel for dinner and night stay."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 3",
                        textDeparture = "Rameswaram- Madurai",
                        placesList = listOf("Rameswaram", "Madurai"),
                        textExplanation = "This morning we drive to Madurai. On reaching, check into the hotel and " + "later visit the renowned Meenakshi temple of Madurai. The temple dates back to the " + "7th century and has Goddess Parvati, or Meenakshi, as its presiding deity. The Meenakshi " + "temple of Madurai is among the Seven wonders of our country. Post Darshan, we return to " + "the hotel for dinner and the night stay."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 4",
                        textDeparture = "Departure from Madurai",
                        placesList = listOf("Madurai"),
                        textExplanation = "After breakfast, our representative will drop you at Madurai airport/railway station, " + "see you off, and you will depart for your onward journey back home carrying some real " + "lofty spiritual experiences."
                    )
                )
            },
            inclusionsList = getInclusionsList(),
            exclusionsList = getExclusionsList(),
            guidelinesList = getGuidelinesList(),
            fAQsList = getFAQsList()
        ),
        oneDay = false
    )
    val srisailamMalJyotirlingaPil4days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.sris_mal4day,
        textName = "Srisailam Mallikarjuna Jyotirlinga Pilgrimage",
        textUrl = "",
        textMrp = "₹ 23,000",
        textDiscounted = "₹20,000",
        textDays = "4 Days",
        textNights = "3 Nights",
        textButton = "View Details",
        details = PackageDetails(
            textOverview = "The ancient historic city of Hyderabad is dotted with many ancient monuments and minarets which " + "spell loud of the affluent bygone eras. Tourist attractions abound and pull tourists from far and " + "wide each year.\nHyderabad draws even devout pilgrims, especially to Srisailam of Hyderabad, where " + "the much-venerated shrine of Lord Shiva, the shrine of Mallikarjuna, stands. Out of the 12 " + "Jyotirlinga shrines, Mallikarjuna Jyotirling is a divine shrine of Lord Shiva, located on the " + "Nallamalai Hills, 215 kilometers from Hyderabad in Andhra Pradesh. It has been a popular site " + "among Saivites for centuries. Srisailam is a Kshetra considered to be very old. Kashi of the " + "south is how they refer to it. The temple is an enormous construction that houses Mallikarjuna and Bharmaramba " + "Devi as the presiding deities. Monsoon is the best season to visit this place as the view is " + "spectacular, with lush green flora and fauna scattered all over.\nThe deity is believed to be " + "self-manifested, and the Goddess is considered one of the 18 Mahashaktis. The place is both Jyotirlinga " + "and Shakti Peeth, which attracts thousands of devotees from all over the country to atone for " + "their sins and seek blessings. Darshan by a photometric system has helped the devotees to prevent languishing " + "in queues for a long time.\nThe tour includes a divine Darshan of Mallikarjuna and historical sites " + "in Hyderabad that can amaze you. We can tailor the itinerary to your taste and comfort. This Kshetra is " + "well-connected with all the major cities of the country. The closest airport, railway station, and bus " + "stand are in Hyderabad. Embark on this short pilgrimage tour of Mallikarjun to get a unique, " + "soul-elevating experience.",
            daysList = arrayListOf<ItineraryDay>().apply {
                add(
                    ItineraryDay(
                        textDay = "Day 1",
                        textDeparture = "Arrive in Hyderabad and Sightseeing",
                        placesList = listOf("Hyderabad"),
                        textExplanation = "Upon arrival at Hyderabad railway station/airport, our tour representative will greet " + "you, then escort you to your hotel. Check-in, freshen up, and later make a city " + "tour of Hyderabad. Hyderabad has a royal splendor about it as this had been a " + "princely state in the yesteryears when the typical Nawabi culture prevailed across the city. " + "The places we visit today include the Salarjung museum, Charminar, Lumbini Park, Chowmahalla " + "palace, and Mecca Masjid. In the evening you could also enjoy the boating laser show. Then, " + "back to the hotel for dinner and the night stay."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 2",
                        textDeparture = "Excursion to Srisailam",
                        placesList = listOf("Srisailam"),
                        textExplanation = "This morning we make an excursion to Srisailam and visit the Mallikarjun temple " + "of Lord Shiva. The temple is among the 12 holy Lord Shiva Jyotirlingam that our country " + "has and is also one among the 275 holy Paadal Petra Sthalams. The temple is east-facing, and " + "many fascinating legends support its history. You stay for the whole day, take a " + "walk around the holy site to feel the positive vibration scattered all around, and " + "get drenched in the divinity. Paid Darshan is less exhausting than free Darshan. You " + "can touch the Shivlinga and perform Abhisheka before booking. All these experiences are overwhelming. " + "We return to Hyderabad after a full-day temple Darshan tour and retire to the hotel " + "for dinner and the night stay."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 3",
                        textDeparture = "Ramoji Film City Tour",
                        placesList = listOf("Ramoji Film City"),
                        textExplanation = "The next day is for a tour of the Ramoji Film City. This film city came " + "into being in 1996 and covers a sprawling area of about 2000 acres. The Ramoji Group " + "took the initiative to find this film city that takes you on a magical celluloid journey daily. " + "It has been certified by Guinness World Records as the world’s largest film studio complex. " + "It’s tough to step out of the city without visiting this studio. You get overcharged as " + "you enter the studio. It is a feast for the eyes of people with a passion for films. It " + "is a full-day excursion, and it is stunning to see the enormous sets, gardens, lakes, hills, " + "hospitals, railway stations, airports, churches, temples, artificial waterfalls, European and " + "US settings, Urban and rural dwellings, shopping plazas, and many more. It is spread " + "over an area of 2000 acres and is squeaky clean. You will get tired physically but desire " + "to remain for some more time. After completing the film city tour, we return to " + "the hotel for dinner and the night stay."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 4",
                        textDeparture = "Short Hyderabad Sightseeing and Departure",
                        placesList = listOf("Hyderabad"),
                        textExplanation = "This morning after a toothsome breakfast in the hotel, we set out for " + "a short city tour again during the first half. The places we visit include Snow world " + "and Golkonda palace. Later you are dropped at Hyderabad airport/railway station, where our " + "representative sees you off, and you proceed the journey back home with lots of " + "happy memories of the tour."
                    )
                )
            },
            inclusionsList = getInclusionsList(),
            exclusionsList = getExclusionsList(),
            guidelinesList = getGuidelinesList(),
            fAQsList = getFAQsList()
        ),
        oneDay = false
    )
    val southernTriangleTour5days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.sout_trian5day,
        textButton = "View Details",
        textNights = "4 Nights",
        textDays = "5 Days",
        textDiscounted = "₹30,000",
        textMrp = "₹ 33,000",
        textUrl = "",
        textName = "Southern Triangle Tour",
        details = PackageDetails(
            textOverview = "With our ‘Southern Triangle Tour’ package, travel to the southern part of India, places like " + "Madurai, Rameswaram, and Kanyakumari.\nIt will be a crazy driving experience, as you will be " + "dwelling deeper into spirituality in Madurai. You will be cruising wildly on the Pamban Bridge, watching " + "the most talked about sunset view at India’s end, and admiring the architectural intelligence of Rameswaram " + "temple during this journey of five days.\nSo, book yourself for this blissful journey of 4 Nights and " + "5 Days to the Southern Triangle of India. And live one magical and inexplicable experience.\nPlease note " + "that this tour itinerary is only a suggestive form, so if you need any readjustment or modifications, then " + "you can contact us.",
            daysList = arrayListOf<ItineraryDay>().apply {
                add(
                    ItineraryDay(
                        textDay = "Day 1",
                        textDeparture = "Arrival in Madurai – Enjoy a Sightseeing tour to Madurai.",
                        placesList = listOf("Madurai"),
                        textExplanation = "Upon arrival at the Madurai Airport/ Railway station, the tour representative will " + "transfer to your hotel room and direct the check-in process. And with your successful check-in " + "process, relax in your hotel room.\nAfter some time, please proceed towards a sightseeing " + "tour to Madurai. You will visit three landmarks in this city, i.e., ThirumalaiNayakkar Mahal, Gandhi " + "Memorial, and Meenakshi temple.\nThirumalaiNayakkar Mahal is a 17th-century old palace briefly " + "presenting a classical fusion of Dravidian and Rajput architectural design. You can " + "revel in its beauty, and you will also be able to capture some of your royal moments at " + "this palace. The palace also hosts a light and sound show every day in the evening " + "around 6:45 pm, so after your day tour, you can enjoy that show.\nAfter that, you will " + "visit the Gandhi Museum, which is only 3 km away from this place. You will find yourself " + "walking along a tree-lined road during this short trip. On reaching the museum, you " + "can check out the sections accounting for the Indian struggle for freedom and a separate " + "section exhibiting some rare objects related to the life of Mahatma Gandhi.\nIn the " + "afternoon, you will have a spiritual tour of Meenakshi Temple. It is a 15-acre temple " + "complex guarded by gateway towers made of multi-colored images of gods and demons. You " + "will enter the temple complex through the eastern entrance, then see a 1000 pillars hall " + "intricately designed by life-size figures. Then, head towards paying homage to the " + "goddess Meenakshi, and after that, you are free to spend the day in whatever way you " + "like. You can stay here and enjoy the Aarti rituals at night.\nOtherwise, you can do " + "a little shopping outside the Meenakshi temple. You can buy some cotton fabrics and hand-woven " + "work from here.\nFinally, get back to your hotel room for an overnight stay.\nMeals Included– Breakfast"
                    )
                )
                add(
                    ItineraryDay(
                        textExplanation = "After having a delicious breakfast, please proceed towards your next destination " + "Rameswaram, which is only 170 km away from Madurai. For Rameswaram, you will enjoy " + "driving alongside the lush green beauty of Tamil Nadu.\nWhen you safely reach Rameswaram, the " + "tour representative will assist you in your check-in process, then relax.\nAfter some " + "time, proceed towards your sightseeing tour to Rameswaram. You will make your first " + "stop at RamanathaswamyTemple, which is one of the 12 holy Jyotirlingas in India. The " + "temple is dedicated to the sacred mound (a sculpture believed to be carved by Goddess " + "Sita so that Lord Rama can worship Lord Shiva.) and is expected to witness thousands of " + "visitors every day. It also said that the temple has 22 wells, and their water has " + "some medicinal properties.\nOther local sites you will visit are Ram Padam, Agnitheeram, and " + "Hanuman Temple (visit to these sites is optional). Rama Padam, believed to be housing " + "a chakra, has Lord Rama's feet engraved inside. Next will be Agnitheeram, a temple where " + "Lord Rama once worshipped Lord Shiva, and Hanuman Temple, which exhibits floating stones.\nOtherwise, " + "you can go to the lost land of Dhanushkodi, which is famous for housing various sports activities " + "(Not Inclusive in the package.).\nAfter enjoying your day tour, get to your hotel " + "room for an overnight stay.\nMeals Included– Breakfast",
                        textDay = "Day 2",
                        textDeparture = "En route to Rameswaram – A City Tour",
                        placesList = listOf("Rameswaram")
                    )
                )
                add(
                    ItineraryDay(
                        textDeparture = "En route to Kanyakumari – a Sightseeing tour",
                        textDay = "Day 3",
                        placesList = listOf("Kanyakumari"),
                        textExplanation = "After having a delicious breakfast, please proceed towards your next destination, " + "Kanyakumari, which is 325 Km away from Rameswaram. Today, you will be driving alongside " + "green paddy fields and the coastal belt of the Lands’ End of India.\nBut if you " + "go for a morning Darshan at Ramanathaswamy temple, you may have to Kanyakumari a little " + "late. While driving toward Kanyakumari, you will take a short break at Tiruchendur, a " + "seashore temple of Subrahmanyam.\nWhen you reach safely in Kanyakumari, the tour representative " + "will assist you in your check-in process, and then relax for a bit.\nAfter that, head " + "towards a half-day tour to Vivekananda Memorial. You will have to take a ferry to " + "the two-Mandapa rock memorial. You will see an enormous statue of a famous Tamil poet " + "‘Tiruvallur’ here, believed to be carved by around 5,000 sculptors.\nThen in the " + "evening, watch the setting sun at the Kanyakumari shore. You will witness a very mesmerizing " + "view of the sun submerging into the sea, and the sight will stay with you forever as " + "a memory.\nFinally, get back to your hotel room for an overnight stay.\nMeals Included– Breakfast"
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 4",
                        textExplanation = "After watching a beautiful sunrise scene, please proceed toward your next " + "destination Trivandrum. While driving towards Trivandrum, you can make a short break " + "at Thanumalayan temple at Suchindram, housing a 22ft highHanumann statue (but this visit " + "is optional).\nWhen you reach safely in Kanyakumari, the tour representative will assist you " + "in your check-in process, and then relax for a bit.\nAfter that, head towards a half-day " + "tour to Trivandrum. You will make your first stop at Padmanabhaswamy temple, considered the " + "richest temple in India. After having Darshan, you will check out the Puthenmalika, a famous " + "horse place exhibiting some ancient wooden sculpture work. Later, you can check out " + "some famous oil paintings and antique collections at Napier Museum.\nFinally, get back " + "to your hotel room for an overnight stay.\nMeals Included– Breakfast",
                        textDeparture = "En route to Trivandrum – A Sightseeing Tour",
                        placesList = listOf("Trivandrum")
                    )
                )
                add(
                    ItineraryDay(
                        textDeparture = "Departure Day",
                        textDay = "Day 5",
                        placesList = listOf("Trivandrum"),
                        textExplanation = "After having a delicious breakfast in your resort, the representative will assist you " + "in checking-out process and then transfer you to Trivandrum Airport/ Railway Station, where " + "you can follow your onward journey. And with this, the tour ends on a memorable note!" + "\nMeals Included– Breakfast"
                    )
                )
            },
            inclusionsList = getInclusionsList(),
            exclusionsList = getExclusionsList(),
            guidelinesList = getGuidelinesList(),
            fAQsList = getFAQsList()
        ),
        oneDay = false
    )
    val tirBalDar4days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.tir_bal4day,
        textName = "Tirupati Balaji Darshan",
        textUrl = "",
        textMrp = "₹ 17,000",
        textDiscounted = "₹14,500",
        textDays = "4 Days",
        textNights = "3 Nights",
        textButton = "View Details",
        details = PackageDetails(
            textOverview = "The Sri Venkateswara Swami Vaari Temple is a Hindu temple dedicated to the Venkateswara form of " + "Lord Vishnu. Venkateswara was the form of Vishnu that appeared on earth to save humankind during the " + "Kali Yug. It is situated on the hill town of Tirumala at Tirupati in Andhra Pradesh. There are numerous " + "legends associated with the manifestation of the lord in Tirumala. The Tirumala Hills is a " + "part of the holy Seshachalam hills range located at an elevation of 2,799 feet above sea level. " + "It is also a major pilgrimage destination in India. Check out the Tirupati Balaji Darshan Package.",
            daysList = arrayListOf<ItineraryDay>().apply {
                add(
                    ItineraryDay(
                        textDay = "Day 1",
                        textDeparture = "Arrival in Chennai",
                        placesList = listOf("Chennai"),
                        textExplanation = "Upon arriving in Chennai, our tour representative will greet you and transfer " + "you to your hotel in Chennai. If you want, a brief sightseeing tour in Chennai can " + "take place. It will include places like the marine beach, Snake Park, etc. If you " + "don’t want, stay at the hotel and relax. Dinner and night stay will be " + "at the hotel in Chennai."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 2",
                        textDeparture = "Chennai - Tirupati",
                        placesList = listOf("Chennai", "Tirupati"),
                        textExplanation = "Early in the morning, you will be checked out of your hotel in Chennai and " + "transferred to Tirupati. It will be a road trip of 4 hours in a private car, and the " + "route will cover 134 km. On arrival, you will immediately check into your accommodation in " + "Tirupati, where you will freshen up before going for Darshan in Tirupati Temple. After the " + "Darshan, overnight stay and dinner will be at the hotel in Tirupati."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 3",
                        textDeparture = "Tirupati - Chennai",
                        placesList = listOf("Tirupati", "Chennai"),
                        textExplanation = "After checking out of your hotel in Tirupati early in the morning, you will " + "transfer back to Chennai. Upon reaching, you check into your hotel here. The remainder " + "of the day will be free for you to spend in leisure and relaxation. Night stay and " + "dinner will be at the hotel in Chennai."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 4",
                        textDeparture = "Departure from Chennai",
                        placesList = listOf("Chennai"),
                        textExplanation = "After having breakfast, you will check out of your hotel in Chennai. The " + "drop off at your place of departure will mark an end to your holy Tirupati Balaji Darshan Package with us."
                    )
                )
            },
            inclusionsList = getInclusionsList(),
            exclusionsList = getExclusionsList(),
            guidelinesList = getGuidelinesList(),
            fAQsList = getFAQsList()
        ),
        oneDay = false
    )
    val deogharJyotirlingaTour3days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.bab_baid4day,
        textName = "Baba Baidyanath Dham - Deoghar Tour",
        textUrl = "",
        textMrp = "₹ 11,000",
        textDays = "3 Days",
        textDiscounted = "₹8,000",
        textNights = "2 Nights",
        textButton = "View Details",
        details = PackageDetails(
            textOverview = "Deoghar makes a picturesque destination for a rejuvenating weekend gateway. Deoghar adorned with " + "forested belts, gorgeous hillocks and pleasing weather, offering a peaceful and charming ambiance.\nOne " + "can witness a plethora of shrines in Deogark, which makes it a popular pilgrimage destination in India. " + "The most important among these destinations is the Baidyanath Dham Shiv Jyotirlingam, thronged by thousands " + "of pilgrims yearly during the Yatra season. Attain some fascinating spiritual experiences by embarking upon " + "the pious Deogarh tour.",
            daysList = arrayListOf<ItineraryDay>().apply {
                add(
                    ItineraryDay(
                        textDay = "Day 1",
                        textExplanation = "Upon arrival at Deoghar station, our tour representative will greet and " + "escort you to the hotel. The entire day you have the leisure to rest and relax. In " + "the evening, you can stroll through the states of Deoghar and explore the bustling local " + "bazaars. Back to hotel for dinner and night stay.",
                        textDeparture = "Arrive in Deoghar",
                        placesList = listOf("Deoghar")
                    )
                )
                add(
                    ItineraryDay(
                        textDeparture = "Deoghar Sightseeing",
                        textDay = "Day 2",
                        placesList = listOf("Deoghar"),
                        textExplanation = "The next day, early morning, we will visit the auspicious Baidyanath Dham Shiv " + "Jyotirlingam. Attend Aarti and offer Puja at the temple. Post Darshan, we come to " + "the hotel for breakfast, after which we set out to visit the other places of Deoghar, " + "such as Naulakha temple, Trikuta Parvat, Nandan Pahar, and Shiv Ganga. Back to hotel " + "for dinner and night stay."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 3",
                        textExplanation = "After breakfast, we again set out for a short sightseeing trip to Deoghar, " + "covering the other remaining places like Satsang Nagar, Nav Durga temple, Pagal Baba temple, " + "Ram Krishna ashram, and Dev Sangh. Soon after, you transferred to Deoghar station, where our " + "representative will see you off, and you depart for your onward journey back home.",
                        textDeparture = "First Half Deoghar Sightseeing and Departure",
                        placesList = listOf("Deoghar")
                    )
                )
            },
            inclusionsList = getInclusionsList(),
            exclusionsList = getExclusionsList(),
            guidelinesList = getGuidelinesList(),
            fAQsList = getFAQsList()
        ),
        oneDay = false
    )
    val bhubaneswarPuriTour3days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.bhub_puri3day,
        textName = "Bhubaneswar Puri Tour",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Bhubaneswar%20Puri%20Tour&c=m17",
        textMrp = "₹ 12,000",
        textDays = "3 Days",
        textDiscounted = "₹9,000",
        textNights = "2 Nights",
        textButton = "Enquire Now"
    )
    val gayaSigTour1day = TravelPackage(
        ism = "Hinduism, Hinduism, Buddhism",
        viewType = 1,
        image = R.drawable.gaya_sig1day,
        textName = "Gaya Sightseeing Tour",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Gaya%20Sightseeing%20Tour&c=m72",
        textMrp = "₹ 11,000",
        textDiscounted = "₹8,000",
        textDays = "1 Day",
        textNights = "0 Nights",
        textButton = "Enquire Now"
    )
    val allAyodhyaVar5days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.all_ayod5day1,
        textButton = "Enquire Now",
        textNights = "4 Nights",
        textDays = "5 Days",
        textDiscounted = "₹14,000",
        textMrp = "₹ 17,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Allahabad,%20Ayodhya,%20Varanasi,%20Gaya%20Tour&c=m3",
        textName = "Allahabad, Varanasi, Gaya Tour"
    )
    val puriRathYat6days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.puri_rath6day,
        textButton = "Enquire Now",
        textNights = "5 Nights",
        textDays = "6 Days",
        textDiscounted = "₹13,500",
        textMrp = "₹ 16,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Puri%20Rath%20Yatra&c=m128",
        textName = "Puri Rath Yatra"
    )
    val sevenJyotirlingaPil12days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.sev_jyot12day,
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=7%20Jyotirlinga%20Pilgrimage&c=m1",
        textMrp = "₹ 25,000",
        textDays = "12 Days",
        textDiscounted = "₹22,000",
        textNights = "11 Nights",
        textButton = "Enquire Now",
        textName = "7 Jyotirlinga Pilgrimage"
    )
    val mah5jyotirlinga5days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.mah5jyot5day,
        textButton = "Enquire Now",
        textNights = "4 Nights",
        textDays = "5 Days",
        textDiscounted = "₹17,000",
        textMrp = "₹ 20,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Maharashtra%205%20Jyotirlinga%20Tour&c=m107",
        textName = "Maharashtra 5 Jyotirlinga Tour"
    )
    val shirdi9jyotirlinga11days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.shir9jyot11day,
        textButton = "Enquire Now",
        textNights = "10 Nights",
        textDays = "11 Days",
        textDiscounted = "₹45,000",
        textMrp = "₹ 48,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Shirdi%20&%209%20Jyotirlinga%20Darshan&c=m147",
        textName = "Shirdi & 9 Jyotirlinga"
    )
    val mahOmkJyotirlinga3days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.mah_omk3day,
        textName = "Mahakaleshwar & Omkareshwar Jyotirlinga",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Mahakaleshwar%20&%20Omkareshwar%20Jyotirlinga%20Tour&c=m106",
        textMrp = "₹ 15,000",
        textDays = "3 Days",
        textDiscounted = "₹12,000",
        textNights = "2 Nights",
        textButton = "Enquire Now"
    )
    val somNagDwarka7days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.som_nag7day,
        textName = "Somnath, Nageshwar & Dwarka",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Somnath,%20Nageshwar%20&%20Dwarka%20Pilgrimage&c=m143",
        textMrp = "₹ 18,000",
        textDays = "7 Days",
        textDiscounted = "₹15,000",
        textNights = "6 Nights",
        textButton = "Enquire Now"
    )
    val himPradesh5deviDar6days = TravelPackage(
        ism = "Hinduism, Buddism, Sikhism",
        viewType = 1,
        image = R.drawable.him_prad5devi6day1,
        textNights = "5 Nights",
        textDays = "6 Days",
        textDiscounted = "₹14,000",
        textMrp = "₹ 17,000",
        textUrl = "",
        textName = "Himachal Pradesh 5 Devi Darshan",
        textButton = "View Details",
        details = PackageDetails(
            textOverview = "Our country India is blessed with numerous abodes of God; among those, the Shakti Peeths or " + "shrines dedicated to Goddess Shakti or Ma Durga hold special significance.\nThis tour of 5 Mata " + "Darshan takes you on a trip of the five distinct Shaktipeeths of the Northern India state of Himachal " + "Pradesh. A visit to these shrines will introduce you to the charm and potency of Ma Shakti and " + "will uplift your soul.",
            daysList = arrayListOf<ItineraryDay>().apply {
                add(
                    ItineraryDay(
                        textDay = "Day 1",
                        textExplanation = "Upon arrival at Amritsar railway station, our tour representative will greet you " + "then escort you to your hotel. Check-in, freshen up, and later proceed to visit the " + "Indo-Pak Wagah border, just 30 km away from the city of Amritsar. The retreat ceremony at " + "the Wagah border is a real spectacle to watch. Back to hotel for dinner and night stay.",
                        textDeparture = "Arrive in Amritsar",
                        placesList = listOf("Amritsar")
                    )
                )
                add(
                    ItineraryDay(
                        textDeparture = "Amritsar- Katra",
                        textDay = "Day 2",
                        placesList = listOf("Katra"),
                        textExplanation = "After breakfast, we tour the city of Amritsar, places like the Golden Temple " + "or Harmandir Sahib Gurudwara, the historical garden Jallianwala Bagh, where the dark " + "tragedy of Jallianwala Bagh took place in the year 1919, and the Maharaja Ranjit Singh summer " + "palace. Post sightseeing, we drive our way to Katra via Jammu and Pathankot. Upon reaching, check " + "into the hotel for dinner and the night stay."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 3",
                        textExplanation = "After breakfast, we would start our trek to the holy shrine of Mata Vaishno " + "Devi from the Katra base camp. The one-way trek distance to this cave shrine of Mata " + "Rani is about 13.5 km. The shrine lies atop Trikuta Parvat ranges and is one of " + "the most important Shaktipeeths in the country. After Darshan, trek back to Katra " + "and then retire to the hotel for dinner and the night stay.",
                        textDeparture = "At Katra",
                        placesList = listOf("Katra")
                    )
                )
                add(
                    ItineraryDay(
                        textDeparture = "Katra- Dharamshala",
                        textDay = "Day 4",
                        placesList = listOf("Katra", "Dharamshala"),
                        textExplanation = "This morning post breakfast, we drive to Mcleodganj in Dharamshala. Upon reaching, check " + "into the hotel and later visit the Dalai Lama temple and handicrafts center. Back to " + "hotel for dinner and night stay."
                    )
                )
                add(
                    ItineraryDay(
                        textExplanation = "The next day after breakfast, we head for Jwalamukhi, and en route, we " + "visit the Chamunda Devi temple and Brajeshwari temple, located in the Kangra valley. Chamunda " + "Devi is the fierce, fearsome form of Goddess Shakti as enshrined in the temple of " + "Chamunda Devi. Next, we visit Kangra Brajeshwari temple belonging to the presiding deity " + "of the region, Sati Mata. After Darshan, we drive straight to Jwalamukhi and visit the " + "temple of Jwalaji, or flaming goddess, i.e., goddess Shakti who emits flames from her " + "mouth. Post Darshan, we check into the hotel and rest for the night.",
                        textDay = "Day 5",
                        textDeparture = "Dharamshala- Chamunda Devi- Kangra Ji- Jwala Ji",
                        placesList = listOf("Dharamshala", "Chamunda Devi", "Kangra Ji", "Jwala Ji")
                    )
                )
                add(
                    ItineraryDay(
                        textDeparture = "Jwalaji- Chintpurni Ji- Amritsar and departure",
                        textDay = "Day 6",
                        placesList = listOf("Jwalaji", "Chintpurni Ji", "Amritsar"),
                        textExplanation = "The next day we drive to Chintpurni Devi temple. Ma Chintpurni is another manifestation " + "of Goddess Shakti, also known as Chinnamasta Devi. Post Darshan, you are driven to " + "Amritsar and dropped at the airport/railway station, where our representative sees you off, " + "and you depart for your onward journey back home."
                    )
                )
            },
            inclusionsList = getInclusionsList(),
            exclusionsList = getExclusionsList(),
            guidelinesList = getGuidelinesList(),
            fAQsList = getFAQsList()
        ),
        oneDay = false
    )
    val himPradesh9deviDar8days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.him_prad9nau8day,
        textNights = "7 Nights",
        textDays = "8 Days",
        textDiscounted = "₹18,000",
        textMrp = "₹ 21,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Himachal%20Pradesh%209%20(Nau)%20Devi%20Darshan&c=m79",
        textName = "Nau (9) Devi Darshan",
        textButton = "Enquire Now"
    )
    val twelveJyotirlingaTour24days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.twel_jyot24day1,
        textNights = "23 Nights",
        textDays = "24 Days",
        textDiscounted = "₹57,000",
        textMrp = "₹ 60,000",
        textUrl = "",
        textName = "12 Jyotirlinga Tour",
        textButton = "View Details",
        details = PackageDetails(
            textOverview = "Our country is blessed with the presence of the 12 most revered Jyotirlingam of Lord Shiva. " + "These Jyotirlingam are regarded as powerful columns of cosmic light symbolizing the lord’s presence " + "among us.\nDevotees crave to visit all the 12 Jyotirlingams to purge themselves of their mortal " + "sins. It is a common belief that one who visits all the 12 or Dwadash Jyotirlinga successfully attains the " + "much sought-after ‘Siddhis’ or divine powers. This 24 day long Dwadash (12) Jyotirlinga Darshan can be " + "customized to enable your immersion in the spiritual aura inspired by each of the Jyotirlinga in accordance " + "with your individualistic requirements.",
            daysList = arrayListOf<ItineraryDay>().apply {
                add(
                    ItineraryDay(
                        textDay = "Day 1",
                        textExplanation = "Upon arrival in Mumbai, our tour representatives will greet you and escort " + "you to the station to board the overnight train to Rajkot in Gujarat. Overnight in train.",
                        placesList = listOf("Mumbai", "Rajkot"),
                        textDeparture = "Arrive in Mumbai and Mumbai- Rajkot"
                    )
                )
                add(
                    ItineraryDay(
                        textDeparture = "Rajkot- Dwarka",
                        textDay = "Day 2",
                        placesList = listOf("Rajkot", "Dwarka"),
                        textExplanation = "Reach Rajkot in the early morning and then drive towards Dwarka, one of " + "the sacred Chardham and ‘Sapta Puri’ that any devout Hindu craves to visit at least " + "once in their lifetime. Check into the hotel at Dwarka and later proceed to have a " + "Darshan at the renowned Dwarkadhish temple of Lord Krishna and the revered Nageshwar Jyotirlingam " + "of Lord Shiva. Dwarkadhish temple enshrines the idol of lord Krishna in his royal " + "avatar. Nageshwar Jyotirlingam presents Lord Shiva as the omnipotent Nagnath, i.e., one " + "who can destroy the ill effects of all types of poisons. Back to the hotel at Dwarka " + "for dinner and the night stay."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 3",
                        textExplanation = "This morning we leave for Somnath near Veraval. On reaching, check into the " + "hotel and later proceed to have a Darshan at the famous Somnath Jyotirlingam temple of Lord " + "Shiva. Myths tell that the moon god built this beautiful Shiva shrine to please lord " + "Shiva. After partaking in the evening Aarti at the temple, we return to the hotel " + "for dinner and the night stay.",
                        textDeparture = "Dwarka- Somnath",
                        placesList = listOf("Dwarka", "Somnath")
                    )
                )
                add(
                    ItineraryDay(
                        textDeparture = "Somnath- Ujjain",
                        textDay = "Day 4",
                        placesList = listOf("Somnath", "Ujjain"),
                        textExplanation = "After attending the morning Aarti at Somnath temple, we will move to the " + "station to board the overnight train to the city of Ujjain in Madhya Pradesh. Overnight on the train."
                    )
                )
                add(
                    ItineraryDay(
                        textExplanation = "This morning on reaching Ujjain, you’ll be escorted to the hotel first. Check-in " + "and relax for some time post which we proceed to visit the famed Omkareshwar Jyotirlingam temple of " + "Lord Shiva that stands with all majesty on a uniquely shaped island on the Narmada " + "river. The island resembles the holy symbol of ‘Om’ in shape. After Darshan in Omkareshwar, " + "we drive back to the hotel in Ujjain for dinner and a night stay.",
                        textDay = "Day 5",
                        textDeparture = "Ujjain- Omkareshwar- Ujjain",
                        placesList = listOf("Ujjain", "Omkareshwar")
                    )
                )
                add(
                    ItineraryDay(
                        textDeparture = "Ujjain-Haridwar",
                        textDay = "Day 6",
                        placesList = listOf("Ujjain", "Haridwar"),
                        textExplanation = "This morning we pay Darshan at another important Jyotirlingam at Ujjain, the " + "temple of Mahakaleshwar. This form depicts lord Shiva as the ‘destroyer of evils. Next, " + "we visit Shakti Mata and Bhairav Baba, after which we return to the hotel for rest. " + "In the evening, we move to the station to board the overnight train to Haridwar. Overnight on the train."
                    )
                )
                add(
                    ItineraryDay(
                        textDeparture = "At Haridwar",
                        placesList = listOf("Haridwar"),
                        textDay = "Day 7",
                        textExplanation = "This morning, we will reach Haridwar. Haridwar is a holy city of Uttarakhand " + "dotted with numerous ancient temples. We check into the hotel, freshen up and rest " + "for some time. Later proceed to visualize the famed Ganga Aarti at Har-ki-Pauri Ghat during " + "the evening. Back to hotel for dinner and night stay."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 8",
                        textExplanation = "This morning we leave for Guptakashi. On reaching, we will check into the " + "hotel and visit the sacred Ardhanarishvara temple of Guptakashi. Back to hotel for dinner and night stay.",
                        textDeparture = "Arrive Haridwar-Guptakashi",
                        placesList = listOf("Haridwar", "Guptakashi")
                    )
                )
                add(
                    ItineraryDay(
                        textDeparture = "Guptakashi-Sonprayag-Kedarnath",
                        placesList = listOf("Guptakashi", "Sonprayag", "Kedarnath"),
                        textDay = "Day 9",
                        textExplanation = "The next day we leave for Sonprayag. On reaching, we start our arduous 14 km " + "trek towards Kedarnath Dham via Gaurikund. On reaching, check into the hotel and " + "later proceed to have a Darshan of Kedarnath ji at the much revered 8th century-old Kedarnath " + "shrine. After attending Aarti at the shrine, we return to the hotel for dinner and the night stay."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 10",
                        textExplanation = "This morning we trek our way back to Sonaprayag after attending the morning Aarti " + "at Kedarnath temple. From Sonaprayag, we head for Rudraprayag, and on reaching, check " + "into the hotel for dinner and the night stay.",
                        textDeparture = "Kedarnath-Sonprayag-Rudraprayag",
                        placesList = listOf("Kedarnath", "Sonprayag", "Rudraprayag")
                    )
                )
                add(
                    ItineraryDay(
                        textDeparture = "Rudraprayag-Haridwar-Varanasi",
                        placesList = listOf("Rudraprayag", "Haridwar", "Varanasi"),
                        textDay = "Day 11",
                        textExplanation = "This morning we drive back to Haridwar, and on reaching, you are transferred " + "to the station to board the overnight train to Varanasi. Lunch and dinner are served " + "en route. Overnight in train."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 12",
                        textExplanation = "This morning on reaching Varanasi, one of the holiest cities of North India, " + "we escort you to the hotel. Check-in, rest for some time, and later in the evening, " + "proceed to view the mystical Ganga Aarti at Varanasi’s famed Dasaswamedh Ghat. Back to " + "hotel for dinner and night stay.",
                        textDeparture = "At Varanasi",
                        placesList = listOf("Varanasi")
                    )
                )
                add(
                    ItineraryDay(
                        textDeparture = "Varanasi-Jasidih-Deoghar",
                        placesList = listOf("Varanasi", "Jasidih", "Deoghar"),
                        textDay = "Day 13",
                        textExplanation = "After Darshan and Aarti at the Kashi Vishwanath Jyotirlingam shrine of Varanasi, " + "plus Darshan at Varanasi’s Sankat Mochan temple, we move to the station to catch a " + "train to Jasidih. On reaching Jasidih, we make a by-road journey to Jharkhand’s Deoghar city. " + "Upon reaching, check into the hotel for dinner and the night stay."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 14",
                        textExplanation = "The next day early morning, we will visit the Baba Baijnath Jyotirlingam shrine of " + "Lord Shiva. At this shrine, the lord is extolled as a Baidya, i.e., a doctor who " + "can heal the ailing. After partaking in the Abhishek Puja and morning Aarti, we move " + "to the station to board the train for Howrah [Kolkata]. On reaching Kolkata, we switch " + "to another train that takes us to Chennai. Overnight in train.",
                        textDeparture = "Deoghar-Kolkata-Chennai",
                        placesList = listOf("Deoghar", "Kolkata", "Chennai")
                    )
                )
                add(
                    ItineraryDay(
                        textDeparture = "Chennai-Rameswaram",
                        placesList = listOf("Chennai", "Rameswaram"),
                        textDay = "Day 15",
                        textExplanation = "This morning we reach Chennai. You can freshen up and take some rest in " + "the station waiting room. Later in the day, we catch a train to the holy town of " + "Rameshwaram. Overnight in train."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 16",
                        textExplanation = "On reaching Rameswaram, you are escorted to the hotel. Check-in and live the " + "rest of the day amid leisure and rest, which is badly needed to soothe your sore, aching " + "muscles. Dinner and night stay in hotel.",
                        textDeparture = "At Rameswaram",
                        placesList = listOf("Rameswaram")
                    )
                )
                add(
                    ItineraryDay(
                        textDeparture = "Rameswaram-Madurai",
                        placesList = listOf("Rameswaram", "Madurai"),
                        textDay = "Day 17",
                        textExplanation = "This morning we proceed for Darshan at the Ramanatha Swamy temple standing atop " + "the Rameswaram island into the sea. Ramanatha swami Jyotirlingam is said to have " + "been established by lord Rama to get blessed by lord Shiva and free himself of the " + "sin of Brahma Hatya, i.e., the one he had committed by killing Ravana. Post Darshan, we " + "hit the road for Madurai. On reaching, check into the hotel, and later in the evening, " + "proceed to visit the famous Meenakshi temple of Madurai, an architectural marvel belonging to " + "Goddess Parvati. Back to hotel for dinner and night stay."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 18",
                        textExplanation = "This morning after breakfast, we will move to Madurai station to board a " + "train for Pune. Overnight in train.",
                        textDeparture = "Madurai-Pune",
                        placesList = listOf("Madurai", "Pune")
                    )
                )
                add(
                    ItineraryDay(
                        textDeparture = "At Pune",
                        placesList = listOf("Pune"),
                        textDay = "Day 19",
                        textExplanation = "This morning we will reach Pune and check into the hotel. The entire day " + "is left to you to spend at leisure and rest. The evening can be made use of for " + "some exciting shopping experiences. Get back to the hotel for dinner and the night stay."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 20",
                        textExplanation = "This morning we drive to Trimbakeshwar Jyotirlingam of lord Shiva at Nashik " + "in Maharashtra, and en route visit another Jyotirlinga shrine; the Bhimashankar cradled amid " + "the picturesque locale of Sahyadris. On reaching Trimbakeshwar, check into the hotel " + "for dinner and the night stay.",
                        textDeparture = "Pune-Bhimashankar-Trimbakeshwar",
                        placesList = listOf("Pune", "Bhimashankar", "Trimbakeshwar")
                    )
                )
                add(
                    ItineraryDay(
                        textDeparture = "Trimbakeshwar-Nashik-Shirdi",
                        placesList = listOf("Trimbakeshwar", "Nashik", "Shirdi"),
                        textDay = "Day 21",
                        textExplanation = "This morning, we proceed for Darshan and Puja at the Trimbakeshwar shrine of Lord " + "Shiva, followed by a Nashik Darshan tour. The places covered during this tour are " + "Sita Gufa, Kapileshwara temple, Kalaram temple, and Panchvati. Post Nashik Darshan, we " + "head straight to Sai Baba Leela Sthal Shirdi. Upon reaching, check into the hotel " + "for dinner and the night stay."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 22",
                        textExplanation = "This morning after partaking in the Kakad Aarti at the temple of Sai Baba, " + "we leave for Aurangabad en route to Shani Shingnapur, where we visit a temple dedicated " + "to Lord Saturn or Shani dev. After Shani Shingnapur Darshan, we proceed towards Aurangabad and " + "visit the lord Shiva Jyotirlingam shrines of Aundha Nagnath and Grishneshwar. Post Darshan, we " + "move to the station to board the overnight train to Hyderabad. Overnight in train.",
                        textDeparture = "Shirdi-Shani Shingnapur-Aurangabad-Hyderabad",
                        placesList = listOf("Shirdi", "Shani Shingnapur", "Aurangabad", "Hyderabad")
                    )
                )
                add(
                    ItineraryDay(
                        textDeparture = "Hyderabad-Srisailam",
                        placesList = listOf("Hyderabad", "Srisailam"),
                        textDay = "Day 23",
                        textExplanation = "On reaching Hyderabad this morning, we leave for Srisailam. On reaching, check " + "into the hotel and later Darshan at Srisailam Lord Shiva shrine, the Mallikarjun Jyotirlinga, " + "enshrined in an architecturally ornate east-facing temple. Back to hotel for dinner and night stay."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 24",
                        textExplanation = "Next morning we drive back to Hyderabad, and you are dropped at the airport/station " + "where our representative sees you off, and you journey back home carrying a deeply satiated " + "heart and purified soul.",
                        textDeparture = "Srisailam-Hyderabad and Departure",
                        placesList = listOf("Srisailam", "Hyderabad")
                    )
                )
            },
            inclusionsList = getInclusionsList(),
            exclusionsList = getExclusionsList(),
            guidelinesList = getGuidelinesList(),
            fAQsList = getFAQsList()
        ),
        oneDay = false
    )
    val baidyanathJyotirlingaYat4days = TravelPackage(
        ism = "Hinduism, Hinduism, Buddhism",
        viewType = 1,
        image = R.drawable.baid_jyot4day,
        textName = "Baidyanath Jyotirlinga with Gaya",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Baidyanath%20Jyotirlinga%20Yatra%20with%20Gaya&c=m18",
        textMrp = "₹ 15,000",
        textDiscounted = "₹12,000",
        textDays = "4 Days",
        textNights = "3 Nights",
        textButton = "Enquire Now"
    )
    val kedJyotirlingaTour6days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.ked_jyot6day,
        textButton = "Enquire Now",
        textNights = "5 Nights",
        textDays = "6 Days",
        textDiscounted = "₹11,000",
        textMrp = "₹ 14,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Kedarnath%20Jyotirlinga%20Tour&c=m96",
        textName = "Kedarnath Jyotirlinga Tour"
    )
    val spiritualGanTour8days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.spir_gan8day,
        textNights = "7 Nights",
        textDays = "8 Days",
        textDiscounted = "₹28,000",
        textMrp = "₹ 31,000",
        textUrl = "",
        textName = "Spiritual Ganges Tour",
        textButton = "View Details",
        details = PackageDetails(
            textOverview = "River Ganga is considered the life source for teeming millions of the country. Many religious places " + "have emerged along the course of the Ganges, and pilgrims from time to time visit these places to " + "soak in the spiritual aura of the River Goddess Ma Ganga.\nIf you wish to feel the true essence " + "of the Ganges, you need to embark on a spiritual tour of the Ganges.",
            daysList = arrayListOf<ItineraryDay>().apply {
                add(
                    ItineraryDay(
                        textDay = "Day 1",
                        textExplanation = "Upon your arrival at Delhi airport/railway station, you are greeted by our " + "tour representative, who then escorts you to the hotel. Check into the hotel for " + "dinner and the night stay.",
                        placesList = listOf("Delhi"),
                        textDeparture = "Delhi Arrival"
                    )
                )
                add(
                    ItineraryDay(
                        textDeparture = "At Delhi",
                        textDay = "Day 2",
                        placesList = listOf("Delhi"),
                        textExplanation = "Next day post breakfast, we depart for a city tour of Delhi. Delhi had been " + "the capital of the Mughals in historic times. Old Delhi and New Delhi have many visitable " + "places that make a busy city tour. During this sightseeing tour, we visit places like " + "Birla temple, Qutub Minar, Red fort, Rajghat, Humayun’s tomb, Jama Masjid, India gate, " + "and Jantar Mantar. The tour winds up with a visit to Chandni Chowk, one of the " + "bustling shopping places in Delhi. Back to hotel for dinner and night stay."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 3",
                        textExplanation = "Next day, post breakfast, we leave for Mathura, a significant pilgrimage destination for " + "Lord Krishna devotees. Lord Krishna is said to have taken his birth in Mathura. Upon " + "reaching Mathura, check into the hotel and later proceed for a sightseeing tour of Mathura. " + "Places covered during the tour are Krishna Janmabhoomi Mandir, Dwarkadhish Temple, Kusum Sarovar, " + "Vishram Ghat, Kans Qila, Mathura museum. Back to hotel for dinner and night stay.",
                        textDeparture = "Delhi – Mathura",
                        placesList = listOf("Delhi", "Mathura")
                    )
                )
                add(
                    ItineraryDay(
                        textDeparture = "Mathura – Agra – Allahabad",
                        textDay = "Day 4",
                        placesList = listOf("Mathura", "Agra", "Allahabad"),
                        textExplanation = "The next day early in the morning, we leave for Agra. On reaching, we directly " + "proceed for the city tour of Agra, covering places like the Taj Mahal, Agra Fort, Akbar’s " + "tomb, Sikandra fort, Jodha Bai ka Rauza, and Itmad-ud-Daulah’s tomb. Post sightseeing, we " + "head for the station directly to board an express train to Allahabad across an overnight journey."
                    )
                )
                add(
                    ItineraryDay(
                        textExplanation = "On reaching Allahabad in the morning, we check into the hotel, freshen up and " + "later proceed for a short sightseeing trip of the city, during which we visit the " + "famous Triveni Sangam, Ashoka pillar, and Allahabad fort. Post sightseeing, we head for " + "Varanasi. Upon reaching, check into the hotel for dinner and the night stay.",
                        textDay = "Day 5",
                        textDeparture = "Allahabad – Varanasi",
                        placesList = listOf("Allahabad", "Varanasi")
                    )
                )
                add(
                    ItineraryDay(
                        textDeparture = "At Varanasi",
                        textDay = "Day 6",
                        placesList = listOf("Varanasi"),
                        textExplanation = "The next day early morning, we set out for a memorable boat ride on the " + "holy Ganges. The serene ambiance of the early morning will touch your soul, indeed. After " + "the boat ride, we will tour Varanasi; we will visit places like Kashi Vishwanath temple, Sankat " + "Mochan temple, Bharat Mata temple, and BHU. Back to hotel for dinner and night stay."
                    )
                )
                add(
                    ItineraryDay(
                        textDeparture = "Varanasi – Sarnath – Delhi",
                        placesList = listOf("Varanasi", "Sarnath", "Delhi"),
                        textDay = "Day 7",
                        textExplanation = "The next day early morning, we hit the road for Sarnath, where Lord Buddha " + "is said to have delivered his first sermon. We visit places like Chaukhandi Stupa, Dhamek Stupa, " + "and the Archaeological museum. Towards evening we board a train for Delhi across an " + "overnight train journey."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 8",
                        textExplanation = "On reaching Delhi in the morning, check into the hotel. Rest of the day, " + "you have complete leisure to rest and relax. In the evening, we will transfer you to " + "Delhi airport/railway station, where our representative will see you off, and you " + "will depart for your onward journey back home.",
                        textDeparture = "Delhi Departure",
                        placesList = listOf("Delhi")
                    )
                )
            },
            inclusionsList = getInclusionsList(),
            exclusionsList = getExclusionsList(),
            guidelinesList = getGuidelinesList(),
            fAQsList = getFAQsList()
        ),
        oneDay = false
    )
    val allChitrakootAyodhya8days = TravelPackage(
        ism = "Hinduism, Hinduism, Buddhism",
        viewType = 1,
        image = R.drawable.all_chit8day,
        textNights = "7 Nights",
        textDays = "8 Days",
        textDiscounted = "₹15,000",
        textMrp = "₹ 18,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Allahabad,%20Chitrakoot,%20Ayodhya,%20Naimisaranya,%20Lucknow,%20Varanasi,%20Bodh%20Gaya%20Tour&c=m4",
        textName = "Varanasi, Bodh Gaya Tour",
        textButton = "Enquire Now"
    )
    val chardhamTourPil15days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.char_tour15day1,
        textNights = "14 Nights",
        textDays = "15 Days",
        textDiscounted = "₹45,000",
        textMrp = "₹ 48,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Chardham%20Tour%20Pilgrimage&c=m42",
        textName = "Chardham Tour Pilgrimage",
        textButton = "Enquire Now"
    )
    val chardhamHelYat5days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 2,
        image = R.drawable.char_hel5day,
        textNights = "4 Nights",
        textDays = "5 Days",
        textDiscounted = "₹1,70,000",
        textMrp = "₹ 1,73,000",
        textUrl = "",
        textName = "Chardham Helicopter Yatra",
        textButton = "View Details",
        details = PackageDetails(
            textOverview = "Char Dham Yatra is one of the very sacred journeys through which one is subjected to attain " + "salvation after death. For the spiritual connection with the Almighty and the chance of getting free " + "from the cycle of birth, millions of devotees embark on this journey every year. \nSo, book yourself " + "for our Char Dham Yatra Helicopter package and awaken your spiritual side with this journey.\nWith " + "the 4 Nights and 5 Days Char Dham Yatra Helicopter package, the pilgrims will get a chance to " + "cover all four spiritual abodes, i.e., Yamunotri, Gangotri, Kedarnath, and Badrinath. The package will " + "give all five-star hotel stays, all three meals, a ride above the Himalayan ranges, and VIP passes " + "to the temple.",
            daysList = arrayListOf<ItineraryDay>().apply {
                add(
                    ItineraryDay(
                        textDay = "Day 1",
                        textDeparture = "Dehradun To Yamunotri departure",
                        placesList = listOf("Dehradun", "Yamunotri"),
                        textExplanation = "You will be asked to assemble at the Sahastradhara helipad 15 minutes before departure. " + "The staff will give you a brief introduction of all the do’s and don’ts during the journey. " + "Then, you will depart from Sahastradhara Helipad (i.e., Dehradun) at 7 am, and after " + "a 45 minutes journey, you will reach Kharsali Helipad, which is in Yamunotri.\nYou will " + "be transferred to your hotel rooms by our representative. You will have about one " + "hour to freshen up and resume your journey to the first Dham of Char Dham Yatra. At " + "Yamunotri temple, you will get VIP darshan passes, and with that, you can follow all " + "your worship rituals to the presiding deity of Yamunotri temple, i.e., Goddess Yamuna.\nThere " + "is another main attraction you can visit at your own expense, like JankiChatti, which is " + "only 7 km away from Yamunotri temple."
                    )
                )
                add(
                    ItineraryDay(
                        textExplanation = "On your day 2 of the Char Dham Yatra Helicopter package, you will be " + "asked to assemble at Kharsali Helipad at 7:45 AM. From there, you will depart for Harshil " + "Helipad of Gangotri, and after 45 minutes, you will reach there.\nThen, you will be " + "transferred to your hotel rooms and given one hour to freshen yourself from all the " + "traveling. You will get VIP passes to Gangotri temple to ignore the long Darshan line. " + "When you complete the Darshan, you will be transferred to your hotel room, where you " + "get lunch.\nAnd after lunch, the whole evening is at your disposal to spend the " + "way you like. But, come back to your hotel room for an overnight stay.",
                        textDay = "Day 2",
                        textDeparture = "Yamunotri to Gangotri departure",
                        placesList = listOf("Yamunotri", "Gangotri")
                    )
                )
                add(
                    ItineraryDay(
                        textDeparture = "Gangotri to Kedarnath departure",
                        textDay = "Day 3",
                        placesList = listOf("Gangotri", "Kedarnath"),
                        textExplanation = "You will resume day three by departing early from Harshil helipad at 9 am. " + "As per Government rules and regulations, you will deboard another helicopter at Guptkashi and " + "arrive at the Kedarnath by 10:00 am approx.\nFor Kedarnath, you fly very high along " + "the snow-capped mountain peaks. When you arrive safely in Kedarnath, you will be " + "first transferred to your hotel room to freshen yourself up from the early morning trip. " + "After one hour, you will be given VIP passes for Kedarnath temple, and you will " + "have a whole hour to perform all the worship rituals for the deity of Kedarnath, i.e., " + "Lord Shiva.\nIn the evening, you will follow a little tour to Triyuginarayan Temple, which is " + "believed to be the site where Lord Shiva and Goddess Parvati got married. After that, you " + "will stay overnight at this place."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 4",
                        textExplanation = "On day 4, you will be transferred to Kedarnath helipad till 10:00 am, and from " + "there, you will depart for Badrinath at 10:15 am. After 45 minutes, you will arrive " + "safely at the Badrinath helipad and be transferred to your hotel room as a refreshment." + "\nAbout 1 hour later, you will given VIP darshan passes to Badrinath temple, where you " + "can whole-heartedly worship Lord Narayan’s idol. It is one of the most sacred temples, which " + "attracts millions of tourists every year.\nAfter Darshan, you will have the whole " + "evening to spend the way you like.",
                        placesList = listOf("Kedarnath", "Badrinath"),
                        textDeparture = "Kedarnath to Badrinath departure"
                    )
                )
                add(
                    ItineraryDay(
                        textDeparture = "Back to Dehradun",
                        textDay = "Day 5",
                        placesList = listOf("Dehradun"),
                        textExplanation = "On day 5, you will be asked to wake up early at 4 am. At 4 am, you will " + "witness the MahaAbhishekAarti at Badrinath, and the whole ritual takes about two hours. " + "After that, you will be transferred to Badrinath Helipad, and from there, you will " + "depart for Dehradun (where the whole spiritual journey of Char Dham started).\nWith this, " + "you will be handed a memorable spiritual journey."
                    )
                )
            },
            inclusionsList = getInclusionsList(),
            exclusionsList = getExclusionsList(),
            guidelinesList = getGuidelinesList(),
            fAQsList = getFAQsList()
        ),
        oneDay = false
    )
    val charDhamHel3days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 2,
        image = R.drawable.char_dham3day,
        textName = "Char Dham Helicopter Yatra",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Char%20Dham%20Helicopter%20Yatra&c=m44",
        textMrp = "₹ 1,63,000",
        textDiscounted = "₹1,60,000",
        textDays = "3 Days",
        textNights = "2 Nights",
        textButton = "Enquire Now"
    )
    val doDhamHel1day = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 2,
        image = R.drawable.do_dham1day,
        textName = "Do Dham Helicopter Yatra",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Do%20Dham%20Helicopter%20Yatra&c=m50",
        textMrp = "₹ 90,000",
        textDiscounted = "₹87,000",
        textDays = "1 Day",
        textNights = "0 Nights",
        textButton = "Enquire Now"
    )
    val kedHelYat1day = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 2,
        image = R.drawable.ked_hel1day,
        textName = "Kedarnath Helicopter Yatra",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Kedarnath%20Helicopter%20Yatra&c=m94",
        textMrp = "₹ 73,000",
        textDiscounted = "₹70,000",
        textDays = "1 Day",
        textNights = "0 Nights",
        textButton = "Enquire Now"
    )
    val badHelYat1day = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 2,
        image = R.drawable.bad_hel1day,
        textName = "Badrinath Helicopter Yatra",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Badrinath%20Helicopter%20Yatra&c=m19",
        textMrp = "₹ 72,000",
        textDiscounted = "₹69,000",
        textDays = "1 Day",
        textNights = "0 Nights",
        textButton = "Enquire Now"
    )
    val ganHelYat1day = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 2,
        image = R.drawable.gan_hel1day,
        textName = "Gangotri Helicopter Yatra",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Gangotri%20Helicopter%20Yatra&c=m71",
        textMrp = "₹ 68,000",
        textDiscounted = "₹65,000",
        textDays = "1 Day",
        textNights = "0 Nights",
        textButton = "Enquire Now"
    )
    val amarnathHelYat4days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 2,
        image = R.drawable.amar_hel4day,
        textName = "Amarnath Helicopter Yatra",
        textUrl = "",
        textMrp = "₹ 83,000",
        textDiscounted = "₹80,000",
        textDays = "4 Days",
        textNights = "3 Nights",
        textButton = "View Details",
        details = PackageDetails(
            textOverview = "With our 3 Nights and 4 Days Amarnath Yatra Helicopter package, follow the path of spirituality " + "to the sacred caves of Amarnath temple and get lost in the elixir of devotion. \nThe venerated Lord " + "Shiva temple is at 3,888 meters in the pristine mountains of Jammu and Kashmir. The helicopter package " + "will take you from the small town of Baltal and helps you fulfill the sacred journey with ease.\nSo " + "book yourself for this hassle-free journey of Amarnath and enjoy the warmth of spirituality at this " + "beautiful abode of Lord Shiva.",
            daysList = arrayListOf<ItineraryDay>().apply {
                add(
                    ItineraryDay(
                        textDay = "Day 1",
                        textDeparture = "Arrival in Srinagar",
                        placesList = listOf("Srinagar"),
                        textExplanation = "Upon your arrival in Srinagar, the tour representative will greet you with " + "your welcome drinks and assist you in your transfer to Sonmarg. On your way to " + "Sonmarg, you will find yourself driving along the picturesque mountain of Jammu and " + "Kashmir. The journey alone will excite the traveling fervor in you, and you will " + "automatically get relieved from the tiredness.\nOnce you reach Sonmarg safely, the representative " + "will direct you toward the check-in formalities, and after that, the whole day is at your disposal."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 2",
                        textDeparture = "Baltal-Amarnath temple-Sonmarg",
                        placesList = listOf("Baltal", "Amarnath temple", "Sonmarg"),
                        textExplanation = "After having a delicious breakfast, you will assemble in the reception area, " + "and from there, the representative will help with your guided transfer to Baltal. Once " + "you reach Baltal, the ground staff will introduce you to all the rules and regulations to " + "abide by during this journey. Then, you will board the helicopter for a ride to " + "Panjtarni, the closest helipad to Amarnath temple.\nOn reaching Panjtarni, you will walk " + "forward, about 6 km. You can also take the help of palkis and horseback to Amarnath temple. " + "Inside the caves of Amarnath, follow all your worship rituals to get the precious blessings " + "of Lord Shiva, and if you are lucky, you may also spot the immortal pigeon inside the " + "cave.\nOnce you have worshipped Lord Shiva to your heart's content, proceed back to " + "Panjtarni, where a helicopter ride will be waiting for you.\nEnjoy your overnight stay at Sonmarg hotel."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 3",
                        textDeparture = "Sightseeing tour of Srinagar",
                        placesList = listOf("Srinagar"),
                        textExplanation = "Start your day third with a delicious breakfast, and then, the representative will " + "assist you to your transfer to Srinagar. On reaching Srinagar, perform all the check-in " + "formalities and freshen up yourself from the previous road journey. Then, the representative " + "will ask you to assemble for a sightseeing tour of Srinagar.\nThe Sightseeing tour of Srinagar " + "will start with a brief nature visit to the Mughal Gardens. Enjoy the silence of nature " + "at this scenic garden; then, towards the joyful Shikara ride at the very famous Dal " + "Lake of Srinagar.\nIn the evening, head back towards your hotel room and enjoy your overnight stay."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 4",
                        textDeparture = "Departure from Srinagar",
                        placesList = listOf("Srinagar"),
                        textExplanation = "After breakfast, the representative will help with your airport transfer, and from " + "there, you will fly towards your respective destinations. With this, you will leave " + "this memorable journey!"
                    )
                )
            },
            inclusionsList = getInclusionsList(),
            exclusionsList = getExclusionsList(),
            guidelinesList = getGuidelinesList(),
            fAQsList = getFAQsList()
        ),
        oneDay = false
    )
    val amarnathHelYat3days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 2,
        image = R.drawable.amar_hel3day,
        textName = "Amarnath Helicopter Yatra",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Amarnath%20Helicopter%20Yatra%20from%20Baltal&c=m6",
        textMrp = "₹ 78,000",
        textDiscounted = "₹75,000",
        textDays = "3 Days",
        textNights = "2 Nights",
        textButton = "Enquire Now"
    )
    val budPilTaj11days = TravelPackage(
        ism = "Buddhism, Buddhism, Buddhism",
        viewType = 1,
        image = R.drawable.bud_pil11day,
        textButton = "Enquire Now",
        textNights = "10 Nights",
        textDays = "11 Days",
        textDiscounted = "₹25,000",
        textMrp = "₹ 28,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Buddhist%20Pilgrimage%20with%20Taj%20Mahal&c=m14",
        textName = "Buddhist Pilgrimage"
    )
    val budPilTour10days = TravelPackage(
        ism = "Buddhism, Buddhism, Buddhism",
        viewType = 1,
        image = R.drawable.bud_pil10day,
        textName = "Buddhist Pilgrimage Tour",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Buddhist%20Pilgrimage%20Tour&c=m15",
        textMrp = "₹ 26,000",
        textDiscounted = "₹23,000",
        textDays = "10 Days",
        textNights = "9 Nights",
        textButton = "Enquire Now"
    )
    val indNepBud12days = TravelPackage(
        ism = "Buddhism, Buddhism, Buddhism",
        viewType = 1,
        image = R.drawable.bud_tour12day,
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Buddhist%20Tour%20with%20Nepal&c=m84",
        textMrp = "₹ 88,000",
        textDays = "12 Days",
        textDiscounted = "₹85,000",
        textNights = "11 Nights",
        textButton = "Enquire Now",
        textName = "Buddhist Tour with Nepal"
    )
    val budTourSar5days = TravelPackage(
        ism = "Buddhism, Buddhism, Buddhism",
        viewType = 1,
        image = R.drawable.bud_tour5day1,
        textButton = "View Details",
        textNights = "4 Nights",
        textDays = "5 Days",
        textDiscounted = "₹24,000",
        textMrp = "₹ 27,000",
        textUrl = "",
        textName = "Buddhist Tour with Sarnath and Bodh Gaya",
        details = PackageDetails(
            textOverview = "Buddhists from across the globe visit the sacred Buddhist sites in India as a part " + "of their pilgrimage journey. This Quick Buddhist tour package promises a spiritually evoking journey through " + "the most sacred and pious sites revered highly by Buddhism followers, including Sarnath and Bodhgaya. " + "Cleanse your soul and visit these significant Buddhist centers of worship. The places covered in " + "this package will give you a complete idea of the life and times of Lord Buddha and how he " + "changed his life from a royal prince to a simple saint who gave the world one of the most popular " + "religions in the world.",
            daysList = arrayListOf<ItineraryDay>().apply {
                add(
                    ItineraryDay(
                        textDay = "Day 1",
                        textDeparture = "Arrival in Delhi",
                        placesList = listOf("Delhi"),
                        textExplanation = "Upon arriving in the capital city of Delhi, you will be guided and transferred to " + "your hotel. Here, you will freshen up and relax before heading for a small city " + "excursion.\nYou will visit the iconic landmark, India Gate, lined with green gardens where " + "you can take your loved ones for picnics. The India gate complex also contains a small " + "water body where people go for paddle boat rides.\nOn your way back, you will also " + "see the majestic-looking and glorious parliament house along with Rashtrapati Bhawan, which is " + "the residence of the president of India and a huge tourist attraction for its magnificent " + "structures.\nFinally, you will head back to your hotel, where you will spend your night " + "after having a delicious meal."
                    )
                )
                add(
                    ItineraryDay(
                        textExplanation = "Early in the morning, after breakfast, you will make way for a short " + "local excursion of Delhi for half a day. It will start with a visit to the memorial " + "of Mahatma Gandhi located at Rajghat. As the name suggests, this place is where the " + "cremation ceremony of Mahatma Gandhi took place. People visit here to offer their " + "respects to him. Situated close to the bank of River Yamuna, flanked on both sides " + "by green gardens and the continuous burning of the eternal flame, gives this place a " + "calming effect.\nAfter the short excursion of the capital, you will finally head towards " + "Varanasi via flight; on arrival, our tour representative will get you checked in to " + "your allotted hotel for dinner and the night stay.",
                        textDay = "Day 2",
                        textDeparture = "Delhi – Varanasi (2 Hours Flight Journey)",
                        placesList = listOf("Delhi", "Varanasi")
                    )
                )
                add(
                    ItineraryDay(
                        textDeparture = "Varanasi – Sarnath – Bodhgaya (247 km / 6 Hours)",
                        textDay = "Day 3",
                        placesList = listOf("Varanasi", "Sarnath", "Bodhgaya"),
                        textExplanation = "Start your morning on a spiritual note with a peaceful boat ride on the " + "mighty river Ganga. You will witness a glorious sunrise in its full splendor through this " + "boat ride. After the boat ride, on your way back, you will be taken for a stroll " + "through the colorful and vibrant Ghats of Varanasi, like Manikarnika Ghat, Assi Ghat, " + "and Tulsi ghat.\nAfter breakfast, you will head out for a complete excursion to one " + "of the Buddhist pilgrimage destination of Sarnath. It is the exact spot where Lord " + "Buddha gave his very first sermon after achieving enlightenment at the deer park " + "known as Rishipattana. After offering your prayers, go to the next location of the " + "holy Dhamekh stupa.\nIt says that after Parinirvana, Lord Buddha's mortal remains were " + "cremated, and the ashes were divided and buried into eight different mounds. It is " + "a highly religious pilgrimage center for Buddhists from across the globe.\nYour next " + "stop will be the Chaukhandi stupa, which has now evolved into a shrine for Lord Buddha's " + "relic from burial mounds. Govardhan constructed the unique octagonal shape of the " + "stupa to commemorate the visit of Humayun. Originally this stupa marks the exact place " + "where Lord Buddha met his disciples from the first time to travel from Bodhgaya to Sarnath." + "\nYou will then go to the famous archaeological museum of Sarnath, which contains the " + "findings retained from the excavation site of Sarnath, which includes 6,832 items, sculptures, and " + "artifacts.\nBy the end of the day, you will head to the holy city of Bodhgaya via road. " + "Once you finally arrive in Bodhgaya, you will check into your hotel for a comfortable night's stay."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 4",
                        textExplanation = "This day will be reserved for you to go on a local sightseeing excursion to the " + "holy city. After having a meal, your day will start with a visit to the famous Mahabodhi " + "temple. A UNESCO world heritage site, this ancient temple is one of the religiously significant " + "temples in India for Buddhists. This major pilgrimage site houses the descendant of the " + "famous Bodhi tree under which Lord Buddha had attained enlightenment, making it a " + "huge magnet for Buddhist pilgrims.\nAfter offering your prayers to the spiritual lord " + "and post lunch, you will go to your next stop at Gaya. Upon reaching Gaya, you will " + "go for a local sightseeing tour to the Dungeshwari cave temple, also known as Mahakala " + "caves.\nEnshrined inside the cave is a golden sculpture of Lord Buddha, along with a " + "sculpture of Goddess Dungeshwari. This cave temple was constructed to commemorate when " + "Lord Buddha was frail and starving and was fed Kheer by a tribal woman named Sujata. Lord " + "Buddha spent a large chunk of his time in this cave in intense penance and meditation.\nBy " + "the end of the day, you will be guided back to your hotel in Bodhgaya to stay overnight.",
                        textDeparture = "Bodhgaya (Sightseeing)",
                        placesList = listOf("Bodhgaya")
                    )
                )
                add(
                    ItineraryDay(
                        textDeparture = "Bodhgaya Departure",
                        textDay = "Day 5",
                        placesList = listOf("Bodhgaya"),
                        textExplanation = "On the final day, you will be taken back to your preferred place of departure, " + "either the railway station or airport, from where your journey to your desired location will continue."
                    )
                )
            },
            inclusionsList = getInclusionsList(),
            exclusionsList = getExclusionsList(),
            guidelinesList = getGuidelinesList(),
            fAQsList = getFAQsList()
        ),
        oneDay = false
    )
    val dalaiLamaTour16days = TravelPackage(
        ism = "Buddhism, Buddhism, Buddhism",
        viewType = 1,
        image = R.drawable.dal_lama16day,
        textName = "Dalai Lama Tour",
        textButton = "Enquire Now",
        textNights = "15 Nights",
        textDays = "16 Days",
        textDiscounted = "₹Request",
        textMrp = "Price on",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Dalai%20Lama%20Tour&c=m51"
    )
    val budPilNor16days = TravelPackage(
        ism = "Buddhism, Buddhism, Buddhism",
        viewType = 1,
        image = R.drawable.bud_pil16day,
        textName = "Buddhist Pilgrimage Tour",
        textButton = "Enquire Now",
        textNights = "15 Nights",
        textDays = "16 Days",
        textDiscounted = "₹Request",
        textMrp = "Price on",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Buddhist%20Pilgrimage%20with%20North%20East%20India%20Tour&c=m13"
    )
    val budTrailArt10days = TravelPackage(
        ism = "Buddhism, Buddhism, Buddhism",
        viewType = 1,
        image = R.drawable.bud_trail10day,
        textName = "Buddha's Trail Tour",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Buddha's%20Trail%20with%20Buddhist%20Art%20Tour&c=m21",
        textMrp = "₹ 26,000",
        textDiscounted = "₹23,000",
        textDays = "10 Days",
        textNights = "9 Nights",
        textButton = "Enquire Now"
    )
    val budSitNep13days = TravelPackage(
        ism = "Buddhism, Buddhism, Buddhism",
        viewType = 1,
        image = R.drawable.bud_sit13day,
        textName = "Buddhist Sites in Nepal",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Buddhist%20Sites%20in%20India%20&%20Nepal&c=m22",
        textMrp = "Price on",
        textDiscounted = "₹Request",
        textDays = "13 Days",
        textNights = "12 Nights",
        textButton = "Enquire Now"
    )
    val budSpirTrav9days = TravelPackage(
        ism = "Buddhism, Buddhism, Buddhism",
        viewType = 1,
        image = R.drawable.bud_spir9day,
        textName = "Buddhist Spiritual Travel",
        textUrl = "",
        textMrp = "₹ 30,000",
        textDiscounted = "₹27,500",
        textDays = "9 Days",
        textNights = "8 Nights",
        textButton = "View Details",
        details = PackageDetails(
            textOverview = "Being one of the oldest religions in the world, Buddhism has garnered a huge base of " + "Buddhist individuals who take up the journey that Lord Buddha took to achieve enlightenment. India is home " + "to several Buddhist sites since this is where Lord Buddha had attained enlightenment and created one " + "of the biggest religions in the world. This Buddhist Spiritual Tour Package will take you on " + "a peaceful and thoughtful journey across some of the most highly revered sites for Buddhism. In " + "9 days and eight nights you will experience the true wonder Buddhism. The powerful divinity is one " + "of its kind, enriching your soul.",
            daysList = arrayListOf<ItineraryDay>().apply {
                add(
                    ItineraryDay(
                        textDay = "Day 1",
                        textDeparture = "Delhi – Lucknow – Shravasti",
                        placesList = listOf("Delhi", "Lucknow", "Shravasti"),
                        textExplanation = "Once you arrive in Delhi, our tour representative will guide and assist you " + "with an early morning train to Lucknow. After covering a train journey of 5 hours to " + "Lucknow, you will be driven to Shravasti. Here, you will check into your hotel to " + "spend the night along with a complete dinner."
                    )
                )
                add(
                    ItineraryDay(
                        textDeparture = "Shravasti Local Sightseeing – Lumbini (203 km / 5 Hours 30 Mins)",
                        textDay = "Day 2",
                        placesList = listOf("Shravasti", "Lumbini"),
                        textExplanation = "It is the day when your local sightseeing tour of Shravasti, post breakfast, will " + "commence. Starting with a visit to one of the most visited sites of Sahet Mahet.\nThe " + "famous Sahet site houses the ancient remains of Buddhist temples, monasteries, and Stupas, which " + "hold great significance to the Buddhist culture. Thousands of years back, this place " + "was a hub of spirituality, and Lord Buddha used to stay and deliver his sermons to " + "his followers here. The remainder of the temples and monasteries include Jetavana Garden, Kosamba " + "Kuti, and Gandha Kuti. Buddhists from all over the world make sure to visit this " + "place and pay their respects.\nOn the other hand, Mahet, an ancient city on the " + "northern end of Sahet, was a completely fortified city and a comfortable home for Buddhist " + "stupas. Ruins of Stupas like Angulimala, Pakki Kuti, and Saudara Stupa only remain due " + "to the excavations. However, it still attracts Buddhists far and wide, offering them a " + "soulful experience.\nAfter lunch, you will head toward Jetavana Vihara, one significant and " + "religiously relevant Buddhist monasteries. The reason is that Lord Buddha gave his " + "teachings and discourses from this, giving it an aura of magnificent spirituality.\nYou will " + "then go towards the famous Ananda Bodhi tree, in front of the entrance of Jetavana, on " + "request of Anasthapitaka, which is symbolic of Lord Buddha when he traveled away from " + "Shravasti. This tree is important since it grew from the sapling of the original Bodhi " + "tree in Bodh Gaya, sitting beneath whose cool shade Lord Buddha had achieved his enlightenment." + "\nWith the evening approaching, you will be driven to your next holy destination, Lumbini. On " + "India – Nepal border, you will be checked in and granted your entry visa, after which " + "you will head to your hotel for an overnight stay, where you will also be served some dinner."
                    )
                )
                add(
                    ItineraryDay(
                        textExplanation = "Post breakfast, your local sightseeing day of the birthplace of Lord Buddha, " + "Lumbini, will kick start.\nLord Buddha took his first breath in the magnificent garden of " + "Lumbini, now a huge pilgrimage site for Buddhists from across the globe. The Lumbini complex " + "houses several UNESCO world heritage sites like Maya Devi temple. This Buddhist temple is " + "adjacent to the sacred garden to commemorate the birth of his one true spiritual leader. This " + "temple was originally commissioned by the Mauryan emperor Ashoka the great in the " + "3rd century BCE, and discovered in 2013.\nNext in stop will be the eternal peace flame, " + "continuously burning for an indefinite period. This flame, present within the premise " + "of the Maya devi temple, was ignited to commemorate the international year of peace, " + "promoting peace and harmony among nations and other global communities. The tranquility and " + "quietness of the place are something that you will remember.\nYour next stop will " + "be the Lumbini Museum, dedicated to the life and time of Lord Buddha. It has numerous " + "collections of Lord Buddha's artifacts, pictures, and sculptures of Lord Buddha from " + "all around the world.\nYour final stop for the whole day of sightseeing will be the " + "Royal Thai Monastery. This Thai style monastery, a meditation centre, stands tall in " + "white gleaming marble, one of the finest architectural wonders. Immerse yourself in deep " + "divinity and unparalleled peace before heading to your next sacred destination of Kushinagar, " + "the place of death of Lord Buddha.\nOnce you arrive at Kushinagar, you will be " + "directed by our tour representative to the hotel here for the night stay and dinner.",
                        textDay = "Day 3",
                        textDeparture = "Lumbini Local Sightseeing – Kushinagar (143 km / 4 Hours 15 Mins)",
                        placesList = listOf("Lumbini", "Kushinagar")
                    )
                )
                add(
                    ItineraryDay(
                        textDeparture = "Kushinagar Sightseeing – Vaishali via Kesaria (122 km / 2.5 Hours) – Patna (50 km / 1 Hour)",
                        textDay = "Day 4",
                        placesList = listOf("Kushinagar", "Vaishali", "Kesaria", "Patna"),
                        textExplanation = "It is the day when you will spend some time sightseeing at the local holy " + "sites. This sightseeing will begin with a visit to Mahaparinirvana temple, which is " + "the exact spot where Lord Buddha took his last breath after ordaining his disciples and " + "saying the last words to his Sangha. This temple houses a 6.1 meters long statue of " + "Lord Buddha in a lying down position facing towards his right.\nYour next stop will " + "be the famous Ramabhar Stupa, which is the cremation site of Lord Buddha. One of the " + "most significant pilgrimage spots for Buddhists, it houses some important relics, sculptures, photos, " + "and statues related to Lord Buddha. This structure attracts tourists from far and " + "wide, making this historical site a must-visit. If you want to look deep into the " + "life and death of Lord Buddha, then this place will be the right choice for you to " + "visit.\nLater in the afternoon, your journey toward Patna will commence, but first you " + "will stop at Kesariya to visit the largest Stupa in the world, followed by a visit " + "to Vaishali.\nThis Stupa, standing at a whopping height of 104 feet, is of world " + "heritage importance, and it is said that Lord Buddha spent a night here just before " + "the day of his attaining enlightenment. As per an ancient travelogue this Stupa used " + "to tower 123 feet before some parts succumbed to an earthquake.\nVaishali is especially relevant " + "in Buddhism since, after attaining his enlightenment, Lord Buddha gave up his alms " + "begging bowl to the people of Vaishali and continued visiting here to establish the " + "Bhikshuni Sangha.\nFinally, after having a soulful time in these serene sites, your journey " + "towards Patna will continue. Once you arrive in Patna, you will go to your hotel " + "for dinner, followed by a night stay."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 5",
                        textDeparture = "Patna– Nalanda(100 km)– Nalanda University– Rajgir(40 km)– Bodhgaya(80 km)",
                        placesList = listOf("Patna", "Nalanda", "Rajgir", "Bodhgaya"),
                        textExplanation = "After breakfast, you will drive to visit the oldest university and learning center, " + "Nalanda university. It was built as a large Buddhist monastery, where individuals and learners " + "from India and abroad came to learn about Buddhism and formalized Vedic traditions. This UNESCO " + "world heritage site laid its foundations in the 5th century CE. Apart from being a " + "tourist attraction, Nalanda is also a crucial part of the Buddhist pilgrimage route. The " + "ruins here offer an insight into the bygone era.\nYour next stop will be the huge " + "Xuanzang memorial hall, built by the Chinese government in India, honouring the world famous " + "Chinese pilgrim traveler Xuan Zang. He was well known for documenting his travels; he " + "also studied and taught in the University of Nalanda. It houses Zang's writings, travelogues and " + "other materials.\nLater, after the completion of your sightseeing tour, your journey toward " + "Rajgir will begin. This place is known for being an important Buddhist pilgrimage site because " + "it was Lord Buddha's favorite place where the Atanatiya conference was held. You " + "will visit the famous World Peace stupa here, which contains statues of the lord " + "going through his four stages of life – birth, enlightenment, teaching, and death.\nThe " + "next stop on your list will be the Griddhakuta or Vulture Peak, which is the spot " + "where Lord Buddha had halted to preach and spread the word of his teachings.\nFinally, your drive " + "to Bodhgaya will commence. Upon arrival, you will check into your hotel for a night's stay and dinner."
                    )
                )
                add(
                    ItineraryDay(
                        textExplanation = "After breakfast, you will visit one of the most Buddhist-relevant sites in the " + "world, The Mahabodhi Temple and the Bodhi tree.\nLiterally meaning the Great awakening " + "temple, this Mahabodhi temple is where Lord Buddha said to have achieved his enlightenment after " + "extreme penance and meditation under the Bodhi tree, which is also present within the " + "temple's complex. It is a major pilgrimage site for Buddhists seeking spirituality and peace " + "in their souls. Monks can be seen meditating under the cool shade of the massive and " + "holy Bodhi tree, which will calm your mind.\nDungeshwari cave temple is the next " + "stop, also known as Mahakala caves. It is believed to be where Lord Buddha had intensely " + "meditated for six long years before leaving for Bodhgaya for the final enlightenment. You will " + "be able to pay your respect to the Gold structure of Lord Buddha residing inside the cave, " + "which is a way of commemorating his penance time.\nNext in your visiting line will be " + "the Sujata Village temple, dedicated to the tribal woman who offered a sweet meal " + "of Kheer to Lord Buddha when she saw him lying down, starved and weak. It gave him " + "immense power and strength to carry on his holy journey.\nAt day's end, you will " + "be driven back to Bodhgaya, where your night stay will be preceded by dinner.",
                        textDay = "Day 6",
                        textDeparture = "Bodhgaya – Gaya (12 km / 45 mins)",
                        placesList = listOf("Bodhgaya", "Gaya")
                    )
                )
                add(
                    ItineraryDay(
                        textDeparture = "Bodhgaya – Varanasi (300 km / 6 – 7 Hours)",
                        textDay = "Day 7",
                        placesList = listOf("Bodhgaya", "Varanasi"),
                        textExplanation = "Your day will begin with a breakfast meal followed by a journey to the holy " + "town of Varanasi, where upon your arrival, you will be checked into your hotel smoothly " + "by our tour representative.\nEvening time will be to visit the splendid Ganga Aarti at " + "the Dashashwamedh Ghat. Here you will experience one of the holiest and most spiritual " + "times, filled with rhythmic chantings and a massive aarti along the banks of the " + "mighty River Ganga.\nAfter finishing Aarti, you will be taken back to your hotel " + "for an overnight stay along with a scrumptious dinner."
                    )
                )
                add(
                    ItineraryDay(
                        textExplanation = "Your morning will be for a glorious boat ride on the holy river of Ganga. " + "You will go to breakfast, after which your day will be filled with pure spiritualism in " + "one of the holiest Buddhism towns of Sarnath.\nYour Sarnath excursion will begin with " + "a visit to the deer park, where Lord Buddha gave his very first sermon to his followers " + "and disciples after attaining enlightenment. An important Buddhist pilgrimage site, this " + "location sees visitors from far and wide who come here to experience the reverence of " + "the divine Lord Buddha.\nNext in the local sightseeing excursion will be the Dhamek " + "stupa, where Lord Buddha delivered his first sermon. The stupa commemorates this historical moment " + "in the history of Buddhism.\nAfter this, you will be guided to Chaukhandi stupa, a shrine " + "of Lord Buddha that houses several relics of Lord Buddha. The shape of this stupa " + "is unorthodox, octagonal. Your final visit will be at the archeological museum, home to " + "6,832 artifacts and sculptures related to the holy site of Sarnath and the spiritual " + "leader, Lord Buddha.\nAs the Sarnath excursion draws to an end, you will be taken " + "back to your hotel, in Varanasi, for dinner and relaxation.",
                        textDay = "Day 8",
                        textDeparture = "Varanasi – Sarnath (15 km / 45 Mins)",
                        placesList = listOf("Varanasi", "Sarnath")
                    )
                )
                add(
                    ItineraryDay(
                        textDeparture = "Varanasi Departure for Delhi",
                        textDay = "Day 9",
                        placesList = listOf("Varanasi", "Delhi"),
                        textExplanation = "On the final day of your trip, you will be dropped off at the railway station " + "or airport for your return to Delhi."
                    )
                )
            },
            inclusionsList = getInclusionsList(),
            exclusionsList = getExclusionsList(),
            guidelinesList = getGuidelinesList(),
            fAQsList = getFAQsList()
        ),
        oneDay = false
    )
    val jainPilTour16days = TravelPackage(
        ism = "Jainism, Jainism, Jainism",
        viewType = 1,
        image = R.drawable.jain_pil16day,
        textName = "Jain Pilgrimage Tour",
        textButton = "Enquire Now",
        textNights = "15 Nights",
        textDays = "16 Days",
        textDiscounted = "₹Request",
        textMrp = "Price on",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Jain%20Pilgrimage%20Tour&c=m86"
    )
    val jainPilShik6days = TravelPackage(
        ism = "Jainism, Jainism, Jainism",
        viewType = 1,
        image = R.drawable.jain_pil6day,
        textNights = "5 Nights",
        textDays = "6 Days",
        textDiscounted = "₹14,000",
        textMrp = "₹ 17,000",
        textUrl = "",
        textName = "Jain Pilgrimage Shikhar Ji Tour",
        textButton = "View Details",
        details = PackageDetails(
            textOverview = "Seek the blessings at one of the highest hills of Parasnath Hill Range. In our 5N/6D " + "Jain Pilgrimage Shikhar Ji Tour, we take you on a journey that guarantees utter peace of mind " + "and many blessings of the Supreme. Get atop the Parasnath Hill at Shikharji to witness the mighty " + "Jain Temples showering blessings on the region. Take a plunge in the spiritual bliss by taking " + "the Pahad Yatra. We will then take you the holy site of Rujiwalika, where Tirthankara Mahaveer attained " + "enlightenment. Feel the divinity of this place and then seek the blessings at Rajgruhi, where we shall " + "walk from temple to temple, paying homage at each. Take a holy bath at Lachhwad and perform Puja " + "and rituals before visiting Guniyaji for the darshan of Shree Gautam Swamiji Kevalgynan Bhumi. We will " + "also visit Kundalpur, the birthplace of Shree Gautam Swami and seize the opportunity to visit the " + "closely situated Nalanda University before concluding this religious tour at Gavmandir of Shree Mahavir " + "Swami at Pavapuri.",
            daysList = arrayListOf<ItineraryDay>().apply {
                add(
                    ItineraryDay(
                        textDay = "Day 1",
                        textExplanation = "Arrive at Parasnath Railway Station and then head to Shikharji. On arrival at " + "Shikharji, check in at Dharamshala. Post lunch, we will leave for Taleti and visit Kanch " + "Mandir, Museum, 20 Jinalay, Samvasharan, Digambar Mandir, Jahaj Mandir, Bhaktamar Mandir, New / Old " + "Bhomiyaji Mandir, Dharam Mangal University (on your own) Chouviar Bhaktibhavna. Overnight stay at " + "Dharamshala.",
                        textDeparture = "Arrive at Parasnath",
                        placesList = listOf("Parasnath")
                    )
                )
                add(
                    ItineraryDay(
                        textDeparture = "At Shikharji",
                        textDay = "Day 2",
                        placesList = listOf("Shikharji"),
                        textExplanation = "Early morning, we shall leave for Pahad Yatra (where 20 Jain Tirthankars attained Nirvana). " + "We shall take the Holy Bath, go for Seva -Puja, Darshan at Mountain (Navkarsi near Jal " + "Mandir), and later return to Dharamshala. Overnight stay at Dharamshala."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 3",
                        textExplanation = "After performing Navkarsi, we shall leave for Rujuwalika by Bus. At " + "Rujuwalika is the Shree Mahavir Swami Temple (Where Mahavir attained Enlightenment). We shall " + "go for Darshan followed by lunch here. Later, we shall visit Rajgruhi by bus. Overnight stay at Rajgruhi.",
                        textDeparture = "Shikharji - Rujuwalika - Rajgruhi",
                        placesList = listOf("Shikharji", "Rujuwalika", "Rajgruhi")
                    )
                )
                add(
                    ItineraryDay(
                        textDeparture = "At Rajgruhi",
                        textDay = "Day 4",
                        placesList = listOf("Rajgruhi"),
                        textExplanation = "Post Navkarsi, we will visit Shree Mahavir Swami Golden Temple and Navlakha Temple. " + "After Seva, Pooja, and Darshan, we will proceed for Pahad Yatra (Shree Munisuvarath Swamijis four " + "Kalyanaks) Ropeway, Virayatan (Bhramin Kala Mandir), Japanese Temple, Virayatan and Darshan " + "of different places. Overnight stay at Rajgruhi."
                    )
                )
                add(
                    ItineraryDay(
                        textExplanation = "Early morning, we will head out to Lachhwad by road. On arrival at Lachhwad, we " + "shall visit Shree Mahavir Swami Temple. After Navkarsi, we will proceed to Shatriyakund Pahad, " + "the birthplace of Shree Mahavir Swamiji. Here we will take the holy bath and then " + "perform rituals and Seva. After performing Pooja and Darshan at mountain Taletima Chhavan, Diksha " + "Kalyank Darshan, we will return for lunch. Later, we will head to proceed to Gunniyaji by " + "bus. On arrival, we will visit Shree Gautam Swamiji Kevalgynan Bhumi. After the Darshan, " + "we shall proceed to Rajgruhi by road. Overnight stay Rajgruhi.",
                        textDay = "Day 5",
                        textDeparture = "Rajgruhi - Lachhwad - Guniyaji - Rajgruhi",
                        placesList = listOf("Rajgruhi", "Lachhwad", "Guniyaji")
                    )
                )
                add(
                    ItineraryDay(
                        textDeparture = "Rajgruhi - Kundalpur - Nalanda - Rajendra Nagar depart",
                        textDay = "Day 6",
                        placesList = listOf("Rajgruhi", "Kundalpur", "Nalanda", "Rajendra Nagar"),
                        textExplanation = "After Navkarsi, we will leave for Kundalpur by road. On arrival at Kundalpur, we " + "shall visit Shree Gautam Swami's birthplace. Later, we will visit the world-famous Nalanda University " + "and proceed to Pavapuri. On arrival at Pavapuri, we will visit Gavmandir of Shree Mahavir " + "Swami. After Chaouviar, we will proceed to Rajendra Nagara for your drop at the " + "railway station to board the train for your onward destination."
                    )
                )
            },
            inclusionsList = getInclusionsList(),
            exclusionsList = getExclusionsList(),
            guidelinesList = getGuidelinesList(),
            fAQsList = getFAQsList()
        ),
        oneDay = false
    )
    val jainPilKer4days = TravelPackage(
        ism = "Jainism, Jainism, Jainism",
        viewType = 1,
        image = R.drawable.ker_jain4day,
        textName = "Kerala Jain Pilgrimage",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Kerala%20Jain%20Pilgrimage&c=m88",
        textMrp = "₹ 15,000",
        textDiscounted = "₹12,000",
        textDays = "4 Days",
        textNights = "3 Nights",
        textButton = "Enquire Now"
    )
    val jainPilNor6days = TravelPackage(
        ism = "Jainism, Jainism, Jainism",
        viewType = 1,
        image = R.drawable.nor_ind6day,
        textButton = "Enquire Now",
        textNights = "5 Nights",
        textDays = "6 Days",
        textDiscounted = "₹13,500",
        textMrp = "₹ 16,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=North%20India%20Jain%20Pilgrimage&c=m89",
        textName = "North India Jain Pilgrimage"
    )
    val jainPilUtt6days = TravelPackage(
        ism = "Jainism, Jainism, Jainism",
        viewType = 1,
        image = R.drawable.utt_prad6day,
        textButton = "Enquire Now",
        textNights = "5 Nights",
        textDays = "6 Days",
        textDiscounted = "₹13,500",
        textMrp = "₹ 16,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Uttar%20Pradesh%20Jain%20Pilgrimage&c=m90",
        textName = "Uttar Pradesh Jain Pilgrimage"
    )
    val jainTemRaj13days = TravelPackage(
        ism = "Jainism, Jainism, Jainism",
        viewType = 1,
        image = R.drawable.raj_jain13day,
        textName = "Rajasthan Jain Temples Tour",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Rajasthan%20Jain%20Temples%20Tour&c=m85",
        textMrp = "Price on",
        textDiscounted = "₹Request",
        textDays = "13 Days",
        textNights = "12 Nights",
        textButton = "Enquire Now"
    )
    val jainPilKar5days = TravelPackage(
        ism = "Jainism, Jainism, Jainism",
        viewType = 1,
        image = R.drawable.kar_jain5day,
        textButton = "Enquire Now",
        textNights = "4 Nights",
        textDays = "5 Days",
        textDiscounted = "₹14,000",
        textMrp = "₹ 17,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Karnataka%20Jain%20Pilgrimage&c=m91",
        textName = "Karnataka Jain Pilgrimage"
    )
    val blisAmrPac2days = TravelPackage(
        ism = "Sikhism, Sikhism, Sikhism",
        viewType = 1,
        image = R.drawable.blis_amr2day,
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Blissful%20Amritsar%20Package&c=m24",
        textMrp = "Price on",
        textDays = "2 Days",
        textDiscounted = "₹Request",
        textNights = "1 Night",
        textButton = "Enquire Now",
        textName = "Blissful Amritsar Package"
    )
    val hemSahYat7days = TravelPackage(
        ism = "Sikhism, Sikhism, Sikhism",
        viewType = 1,
        image = R.drawable.hem_sah7day,
        textName = "Hemkund Sahib Yatra",
        textUrl = "",
        textMrp = "₹ 18,000",
        textDays = "7 Days",
        textDiscounted = "₹15,000",
        textNights = "6 Nights",
        textButton = "View Details",
        details = PackageDetails(
            textOverview = "Positioned near the beautiful Valley of Flowers, Hemkund Lake is a prominent pilgrimage destination for " + "Hindus and Sikhs. Along the shore of this meandering lake is the sacred Sikh shrine Hemkund Sahib fortified. " + "It is where Guru Gobind Singh, the tenth Guru in Sikhism, unified with God after protracted meditation in " + "his previous birth. Nearby this shrine is located Lakshmana Temple, where Lakshmana, the younger brother " + "of Rama, took penance. Thus this shrine is religiously and historically significant for both Hinduism " + "and Sikhism.\nThis 06 Nights and 07 Days of itinerary take the tourists on a tour of Hemkund Sahib " + "Ji. Major destinations covered in the itinerary are Haridwar, Joshimath, and Govind Ghat. Ghangaria, Hemkund " + "Sahib, Ghangaria, and Valley of flowers. This tour is ideal for those spiritual tourists who want " + "to get soaked in spirituality and engrossing natural beauty.",
            daysList = arrayListOf<ItineraryDay>().apply {
                add(
                    ItineraryDay(
                        textDay = "Day 1",
                        textExplanation = "On arrival at Delhi airport/railway station, meet our representative, who will be " + "there for your welcome and afterward drive you to Haridwar, which is about a 5 " + "to 6 hours long journey. On reaching, check in at the already booked hotel in Haridwar. " + "Later in the evening, we will visit Mansa Devi temple to offer prayers. Also, we will " + "attend Ganga aarti at Har Ki Peri. Afterward, we will return to the hotel for a " + "relaxing overnight stay.\nHaridwar, one of the sacred places of Hindu devotes in India, " + "lies in the Haridwar district of Uttarakhand at the foothill of Shivaliks. It is the " + "place where people go to take a dip in the holy Ganga river for the salvation of their " + "deceased elders or close ones. According to the Hindu legend, Bhagirath, a Suryavanshi prince, " + "carried penance here to rescue the souls of his ancestors who had decomposed because of the " + "nuisance of sage Kapila. The plentiful water of the Ganga river was dropped on earth " + "from the locks of Lord Shiva to revive the sixty thousand sons of king Sagara. Haridwar is " + "the entranceway to the sources of the Ganga and the Yamuna.",
                        textDeparture = "Arrival in Delhi – Haridwar (205 km 06 Hrs)",
                        placesList = listOf("Delhi", "Haridwar")
                    )
                )
                add(
                    ItineraryDay(
                        textDeparture = "Haridwar – Pipalkoti (245 Km 10 Hrs)",
                        textDay = "Day 2",
                        placesList = listOf("Haridwar", "Pipalkoti"),
                        textExplanation = "After breakfast, we will check out from the hotel in Haridwar and drive to " + "Pipalkoti via Rishikesh Devprayag, Rudrapryag, and Karanparyag. Later in the evening, " + "on reaching Pipalkoti, we will check in at the already-booked hotel and enjoy a soothing overnight stay."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 3",
                        textExplanation = "Today after breakfast in the morning, we will leave by road for Govindghat. We " + "will stop at Joshimath to visit Adi Shankaracharya Math and Narsingh Mandir. Afterward, we " + "will continue our drive to Govindghat. On reaching, we will start trekking from here to " + "Ghangaria. Check-in at the pre-booked Guest House in Ghangaria for a comfortable overnight stay.",
                        textDeparture = "Pipalkoti – Govindghat – Ghangaria (50 km. Drive + 13 Kms Trek)",
                        placesList = listOf("Pipalkoti", "Govindghat", "Ghangaria")
                    )
                )
                add(
                    ItineraryDay(
                        textDeparture = "Ghangaria – Hemkund Sahib – Ghangaria (5 km Trek per way)",
                        textDay = "Day 4",
                        placesList = listOf("Ghangaria", "Hemkund Sahib"),
                        textExplanation = "After breakfast in the morning, we will start trekking to Hemkund Sahib. On " + "reaching, we will take a holy dip in Hemkund and proceed for the Darshan of Shri Hemkund " + "Sahib Ji. Later, we will trek back to Ghangaria and overnight in the hotel."
                    )
                )
                add(
                    ItineraryDay(
                        textExplanation = "We will start our day early in the morning and trek down to Govindghat. Here, " + "we will meet our driver and leave by road for Rudrapryag via Joshimath, Chamoli, and Karanprayag. " + "On reaching, check in at the already booked hotel for an overnight stay.\nRudraprayag is " + "the meeting point of two significant rivers, i.e., the Alaknanda and Mandakini Rivers.",
                        textDay = "Day 5",
                        textDeparture = "Ghangaria - Govindghat - Rudraprayag (13 km. Trek + 132 Kms Drive)",
                        placesList = listOf("Ghangaria", "Govindghat", "Rudraprayag")
                    )
                )
                add(
                    ItineraryDay(
                        textDeparture = "Rudraprayag - Rishikesh (135 km 7 Hrs)",
                        textDay = "Day 6",
                        placesList = listOf("Rudraprayag", "Rishikesh"),
                        textExplanation = "After breakfast, we will drive to Rishikesh via Srinagar Garhwal. We will " + "stop at Devprayag, the Confluence of the Alaknanda and Bhagirathi Rivers, to visit the " + "meeting point. Afterward, we will continue our drive to Rishikesh. On reaching, check " + "in at the pre-booked hotel for a relaxing overnight stay.\nRishikesh is a famous " + "holy place located just 24 km away from Haridwar. Enclosed by the beautiful mountains from three " + "sides and the Ganga river flowing through, it lies right in the lap of the lower " + "Himalayas. This pilgrimage center is associated with the Ramayana and houses many ancient " + "and newly constructed temples. It is an ideal place for practicing Yoga and meditation. " + "It has many Yoga centers and Ashrams. Rishikesh is the gateway to start the journey to " + "religious places in North India, including Yamunotri, Gangotri, Kedarnath, and Badrinath."
                    )
                )
                add(
                    ItineraryDay(
                        textDeparture = "Rishikesh – Delhi (234 km 07 Hrs)",
                        textDay = "Day 7",
                        placesList = listOf("Rishikesh", "Delhi"),
                        textExplanation = "On the last day of our tour, we will have breakfast in the morning and afterward " + "get ready to drive back to Delhi. Upon reaching, get a direct transfer to Delhi airport/railway " + "station to board a flight or train for your onward destination."
                    )
                )
            },
            inclusionsList = getInclusionsList(),
            exclusionsList = getExclusionsList(),
            guidelinesList = getGuidelinesList(),
            fAQsList = getFAQsList()
        ),
        oneDay = false
    )
    val gurTourHim6days = TravelPackage(
        ism = "Sikhism, Sikhism, Sikhism",
        viewType = 1,
        image = R.drawable.him_gur6day,
        textButton = "Enquire Now",
        textNights = "5 Nights",
        textDays = "6 Days",
        textDiscounted = "₹13,500",
        textMrp = "₹ 16,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Himachal%20Gurudwaras%20Tour&c=m69",
        textName = "Himachal Gurudwaras Tour"
    )
    val golTemTour5days = TravelPackage(
        ism = "Sikhism, Sikhism Sikhism",
        viewType = 1,
        image = R.drawable.gol_tem5day,
        textButton = "Enquire Now",
        textNights = "4 Nights",
        textDays = "5 Days",
        textDiscounted = "₹14,000",
        textMrp = "₹ 17,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Golden%20Temple%20Tour&c=m70",
        textName = "Golden Temple Tour"
    )
    val amrDharRel7days = TravelPackage(
        ism = "Buddhism, Sikhism, Sikhism",
        viewType = 1,
        image = R.drawable.amr_dhar7day,
        textName = "Amritsar Religious Tour",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Amritsar%20&%20Dharmshala%20Religious%20Tour&c=m7",
        textMrp = "₹ 18,000",
        textDays = "7 Days",
        textDiscounted = "₹15,000",
        textNights = "6 Nights",
        textButton = "Enquire Now"
    )
    val famGurTour14days = TravelPackage(
        ism = "Sikhism, Sikhism, Sikhism",
        viewType = 1,
        image = R.drawable.fam_gur14day,
        textName = "Famous Gurudwaras Tour",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Famous%20Gurudwaras%20Tour%20in%20India&c=m65",
        textMrp = "Price on",
        textDays = "14 Days",
        textDiscounted = "₹Request",
        textNights = "13 Nights",
        textButton = "Enquire Now"
    )
    val dwarSomDar4days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.dwar_som4day,
        textName = "Dwarka Somnath Darshan",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Dwarka%20Somnath%20Darshan&c=m52",
        textMrp = "₹ 11,000",
        textDiscounted = "₹8,500",
        textDays = "4 Days",
        textNights = "3 Nights",
        textButton = "Enquire Now"
    )
    val gujTemTour5days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.guj_tem5day,
        textButton = "Enquire Now",
        textNights = "4 Nights",
        textDays = "5 Days",
        textDiscounted = "₹12,500",
        textMrp = "₹ 15,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Gujarat%20Temples%20Tour:%20Dwarka,%20Somnath%20&%20Ahmedabad&c=m73",
        textName = "Gujarat Temples Tour"
    )
    val bestGujFam8days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.best_guj8day,
        textNights = "7 Nights",
        textDays = "8 Days",
        textDiscounted = "₹25,000",
        textMrp = "₹ 28,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Best%20of%20Gujarat%20Family%20Package:%20Kutch,%20Bhuj%20&%20Dwarka&c=m25",
        textName = "Best of Gujarat",
        textButton = "Enquire Now"
    )
    val aWeekGuj7days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.a_week7day,
        textName = "A Week in Gujarat",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=A%20Week%20in%20Gujarat:%20Dwarka,%20Somnath%20&%20Gir&c=m8",
        textMrp = "₹ 14,000",
        textDays = "7 Days",
        textDiscounted = "₹11,400",
        textNights = "6 Nights",
        textButton = "Enquire Now"
    )
    val dwarSomTour6days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.dwar_som6day,
        textButton = "Enquire Now",
        textNights = "5 Nights",
        textDays = "6 Days",
        textDiscounted = "₹18,500",
        textMrp = "₹ 21,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Dwarka%20Somnath%20Tour%20with%20Diu%20Island&c=m53",
        textName = "Dwarka Somnath Tour"
    )
    val beautGujDwar5days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.beaut_guj5day,
        textButton = "Enquire Now",
        textNights = "4 Nights",
        textDays = "5 Days",
        textDiscounted = "₹15,000",
        textMrp = "₹ 18,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Beautiful%20Gujarat%20with%20Dwarka%20&%20Somnath&c=m26",
        textName = "Beautiful Gujarat with Dwarka"
    )
    val somDwarTour4days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.som_dwar4day,
        textName = "Somnath Dwarka Tour",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Somnath%20Dwarka%20Tour%20with%20Porbandar&c=m149",
        textMrp = "₹ 17,000",
        textDiscounted = "₹14,350",
        textDays = "4 Days",
        textNights = "3 Nights",
        textButton = "Enquire Now"
    )
    val somDwarJun5days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.som_dwar5day,
        textButton = "Enquire Now",
        textNights = "4 Nights",
        textDays = "5 Days",
        textDiscounted = "₹8,999",
        textMrp = "₹ 11,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Somnath%20Dwarka%20with%20Junagadh%20Tour&c=m150",
        textName = "Somnath Dwarka with Junagadh"
    )
    val dwarSomDiu6days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.dwar_som_diu6day,
        textButton = "Enquire Now",
        textNights = "5 Nights",
        textDays = "6 Days",
        textDiscounted = "₹17,650",
        textMrp = "₹ 20,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Dwarka%20Somnath%20Diu%20&%20Junagadh%20Package&c=m54",
        textName = "Dwarka Somnath Package"
    )
    val splGujTem5days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.spl_guj5day,
        textButton = "Enquire Now",
        textNights = "4 Nights",
        textDays = "5 Days",
        textDiscounted = "₹8,500",
        textMrp = "₹ 11,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Splendid%20Gujarat%20Temples%20&%20Wildlife%20Tour%20including%20Dwarka%20&%20Somnath&c=m151",
        textName = "Splendid Gujarat Temples"
    )
    val kedBadYat6days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.ked_bad6day,
        textButton = "Enquire Now",
        textNights = "5 Nights",
        textDays = "6 Days",
        textDiscounted = "₹13,500",
        textMrp = "₹ 16,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Kedarnath%20Badrinath%20Yatra%20from%20Haridwar&c=m98",
        textName = "Kedarnath Badrinath from Haridwar"
    )
    val kedYatDel5days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.ked_yat5day,
        textButton = "Enquire Now",
        textNights = "4 Nights",
        textDays = "5 Days",
        textDiscounted = "₹10,500",
        textMrp = "₹ 13,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Kedarnath%20Yatra%20from%20Delhi&c=m99",
        textName = "Kedarnath Yatra from Delhi"
    )
    val holyKedBad2days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.holy_ked2day,
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Holy%20Kedarnath%20&%20Badrinath%20Tour&c=m80",
        textMrp = "₹ 13,000",
        textDays = "2 Days",
        textDiscounted = "₹10,500",
        textNights = "1 Night",
        textButton = "Enquire Now",
        textName = "Holy Kedarnath & Badrinath"
    )
    val bestGujTem7days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.best_guj7day,
        textName = "Best of Gujarat",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Best%20of%20Gujarat:%20Temples,%20Statues%20&%20Wildlife%20including%20Dwarka%20&%20Somnath&c=m27",
        textMrp = "₹ 25,000",
        textDays = "7 Days",
        textDiscounted = "₹22,500",
        textNights = "6 Nights",
        textButton = "Enquire Now"
    )
    val divCharDham10days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.div_char10day,
        textName = "Divine Char Dham Yatra",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Divine%20Char%20Dham%20Yatra%20by%20Road&c=m55",
        textMrp = "₹ 21,000",
        textDiscounted = "₹18,000",
        textDays = "10 Days",
        textNights = "9 Nights",
        textButton = "Enquire Now"
    )
    val fasAhmDiu8days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.fas_ahm8day,
        textNights = "7 Nights",
        textDays = "8 Days",
        textDiscounted = "₹13,000",
        textMrp = "₹ 16,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Fascinating%20Ahmedabad,%20Diu%20&%20Somnath%20including%20Dwarka&c=m68",
        textName = "Fascinating Somnath including Dwarka",
        textButton = "Enquire Now"
    )
    val dwarSomGuj5days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.dwar_som5day,
        textButton = "Enquire Now",
        textNights = "4 Nights",
        textDays = "5 Days",
        textDiscounted = "₹15,999",
        textMrp = "₹ 18,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Dwarka-%20Somnath:%20Gujarat%20Pilgrims%20Package&c=m56",
        textName = "Gujarat Pilgrims Package"
    )
    val holyKedYat5days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.holy_ked5day,
        textButton = "Enquire Now",
        textNights = "4 Nights",
        textDays = "5 Days",
        textDiscounted = "₹8,500",
        textMrp = "₹ 11,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Holy%20Kedarnath%20Yatra&c=m81",
        textName = "Holy Kedarnath Yatra"
    )
    val kedBadFam6days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.ked_bad_fam6day,
        textButton = "Enquire Now",
        textNights = "5 Nights",
        textDays = "6 Days",
        textDiscounted = "₹13,200",
        textMrp = "₹ 16,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Kedarnath%20Badrinath%20Family%20Tour%20with%20Srinagar&c=m100",
        textName = "Kedarnath Badrinath Family Tour"
    )
    val ganKedBad10days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.gan_ked10day,
        textName = "Gangotri, Kedarnath & Badrinath",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Gangotri,%20Kedarnath%20&%20Badrinath%20Yatra%20from%20Delhi&c=m74",
        textMrp = "₹ 31,000",
        textDiscounted = "₹28,140",
        textDays = "10 Days",
        textNights = "9 Nights",
        textButton = "Enquire Now"
    )
    val kedBadrDar6days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.ked_bad_dar6day,
        textButton = "Enquire Now",
        textNights = "5 Nights",
        textDays = "6 Days",
        textDiscounted = "₹45,000",
        textMrp = "₹ 48,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Kedarnath%20&%20Badrinath%20Darshan%20via%20Helicopter&c=m101",
        textName = "Kedarnath Darshan via Helicopter"
    )
    val relPacRis7days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.rel_pac7day,
        textName = "Religious Package- Rishikesh, Kedarnath",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Religious%20Package-%20Rishikesh,%20Rudraprayag,%20Joshimath%20&%20Kedarnath&c=m139",
        textMrp = "₹ 17,000",
        textDays = "7 Days",
        textDiscounted = "₹14,500",
        textNights = "6 Nights",
        textButton = "Enquire Now"
    )
    val badKedDo5days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.bad_ked5day,
        textButton = "Enquire Now",
        textNights = "4 Nights",
        textDays = "5 Days",
        textDiscounted = "₹22,160",
        textMrp = "₹ 25,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Badrinath%20Kedarnath%20Do%20Dham%20Tour%20with%20Srinagar&c=m28",
        textName = "Badrinath Kedarnath Tour"
    )
    val haridRisDeh6days = TravelPackage(
        ism = "Hinduism, Buddism, Sikhism",
        viewType = 1,
        image = R.drawable.har_ris6day,
        textButton = "Enquire Now",
        textNights = "5 Nights",
        textDays = "6 Days",
        textDiscounted = "₹14,999",
        textMrp = "₹ 17,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Haridwar%20Rishikesh%20Dehradun%20Tour&c=m82",
        textName = "Haridwar Rishikesh Dehradun Tour"
    )
    val allOnePac7days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.all_one7day,
        textName = "All in One: Haridwar",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=All%20in%20One%20Package:%20Haridwar,%20Mussoorie%20&%20Corbett&c=m9",
        textMrp = "₹ 30,000",
        textDays = "7 Days",
        textDiscounted = "₹27,160",
        textNights = "6 Nights",
        textButton = "Enquire Now"
    )
    val therHaridRis4days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.ther_har4day,
        textName = "Therapeutic Haridwar & Rishikesh",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Therapeutic%20Haridwar%20&%20Rishikesh%20Ganges%20Tour&c=m167",
        textMrp = "₹ 14,000",
        textDiscounted = "₹11,900",
        textDays = "4 Days",
        textNights = "3 Nights",
        textButton = "Enquire Now"
    )
    val charDehMus5days = TravelPackage(
        ism = "Hinduism, Buddhism, Sikhism",
        viewType = 1,
        image = R.drawable.char_deh5day,
        textButton = "Enquire Now",
        textNights = "4 Nights",
        textDays = "5 Days",
        textDiscounted = "₹11,720",
        textMrp = "₹ 14,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Delightful%20Dehradun%20Mussoorie%20Tour%20with%20Dhanaulti%20&%20Haridwar&c=m45",
        textName = "Dehradun Tour with Haridwar"
    )
    val tripFunHar5days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.trip_fun5day,
        textButton = "Enquire Now",
        textNights = "4 Nights",
        textDays = "5 Days",
        textDiscounted = "₹35,000",
        textMrp = "₹ 38,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Triple%20Fun:%20Haridwar,%20Rishikesh,%20Mussoorie%20Tour&c=m168",
        textName = "Haridwar, Rishikesh, Mussoorie Tour"
    )
    val stunNainHar6days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.stun_nain6day,
        textButton = "Enquire Now",
        textNights = "5 Nights",
        textDays = "6 Days",
        textDiscounted = "₹22,500",
        textMrp = "₹ 25,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Stunning%20Nainital%20Haridwar%20Mussoorie%20Tour&c=m152",
        textName = "Stunning Nainital Haridwar Tour"
    )
    val remHaridRis5days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.rem_har5day,
        textButton = "Enquire Now",
        textNights = "4 Nights",
        textDays = "5 Days",
        textDiscounted = "₹14,500",
        textMrp = "₹ 17,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Remarkable%20Haridwar%20Rishikesh%20Auli%20Tour&c=m140",
        textName = "Remarkable Haridwar Rishikesh Auli"
    )
    val auliWinBon6days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.auli_win6day,
        textButton = "Enquire Now",
        textNights = "5 Nights",
        textDays = "6 Days",
        textDiscounted = "₹20,000",
        textMrp = "₹ 23,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Auli%20Winter%20Bonanza%20Package%20with%20Haridwar%20&%20Joshimath%20from%20Delhi&c=m10",
        textName = "Auli Winter Bonanza"
    )
    val bestShimDeh7days = TravelPackage(
        ism = "Hinduism, Buddhism, Sikhism",
        viewType = 1,
        image = R.drawable.best_shim7day,
        textName = "Best of Haridwar",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Best%20of%20Shimla,%20Dehradun,%20Rishikesh%20&%20Haridwar&c=m29",
        textMrp = "₹ 45,000",
        textDays = "7 Days",
        textDiscounted = "₹42,600",
        textNights = "6 Nights",
        textButton = "Enquire Now"
    )
    val besShirTour2days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.bes_shir2day,
        textUrl = "",
        textMrp = "₹ 8,000",
        textDays = "2 Days",
        textDiscounted = "₹5,720",
        textNights = "1 Night",
        textButton = "View Details",
        details = PackageDetails(
            "Located in Kopargaon taluka in Ahmednagar district, Shirdi is a small town famed as the village " + "of revered spiritual leader Sai Baba. Being one of the main pilgrimage sites in Maharashtra, it is " + "where many cultural practices are observed that attract nature lovers and religious tourists alike. Book " + "our specially crafted Shirdi 2 days package with a detailed itinerary covering all the major " + "temples and destinations in and around Shirdi. And gear up for a sacred tour that imprints on your soul for life.",
            daysList = arrayListOf<ItineraryDay>().apply {
                add(
                    ItineraryDay(
                        textDay = "Day 1",
                        textDeparture = "Reach Shirdi",
                        placesList = listOf("Shirdi"),
                        textExplanation = "Arrive at the Kopargaon Railway Station, and let our representatives drive you to " + "Shirdi. Post a long car ride, check in to a hotel and freshen up. Then, proceed for local " + "sightseeing to places like Sai Baba Temple, Dwarkamai, Chavdi, Khandoba Temple, and Nanda " + "Deep for Darshan. Later, get back to the hotel, have a tasty dinner, and enjoy an " + "overnight stay at your hotel in Shirdi."
                    )
                )
                add(
                    ItineraryDay(
                        textDay = "Day 2",
                        textDeparture = "Shirdi: Shani Shingnapur and Departure",
                        placesList = listOf("Shirdi", "Shani Shingnapur"),
                        textExplanation = "Start the beautiful day with a delicious breakfast and complete the check-out formalities. " + "Next, you will visit the sacred Shani Shingnapur temple; after seeking blessings, you will " + "get a transfer to Shirdi Airport/Railway station for your onward journey."
                    )
                )
            },
            inclusionsList = getInclusionsList(),
            exclusionsList = getExclusionsList(),
            guidelinesList = getGuidelinesList(),
            fAQsList = getFAQsList()
        ),
        oneDay = false,
        textName = "Bestselling Shirdi Tour for a Spiritual Getaway"
    )
    val shirTrimShan3days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.shir_trim3day,
        textName = "Shirdi Trimbakeshwar Tour",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Shirdi%20Trimbakeshwar%20Shani%20Shingnapur%20Tour&c=m153",
        textMrp = "₹ 13,000",
        textDays = "3 Days",
        textDiscounted = "₹10,500",
        textNights = "2 Nights",
        textButton = "Enquire Now"
    )
    val shirTourMum4days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.bes_shir2day,
        textName = "Shirdi Tour from Mumbai",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Shirdi%20Tour%20from%20Mumbai&c=m154",
        textMrp = "₹ 14,000",
        textDiscounted = "₹11,999",
        textDays = "4 Days",
        textNights = "3 Nights",
        textButton = "Enquire Now"
    )
    val shirSeekDiv2days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.shir_seek2day,
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Shirdi:%20Seek%20Divine%20Blessings&c=m155",
        textMrp = "₹ 10,000",
        textDays = "2 Days",
        textDiscounted = "₹7,499",
        textNights = "1 Night",
        textButton = "Enquire Now",
        textName = "Shirdi: Seek Divine Blessings"
    )
    val shirShaniShin3days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.shir_shani3day,
        textName = "Shirdi Shani Shingnapur Tour",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Shirdi%20Shani%20Shingnapur%20Tour%20from%20Aurangabad&c=m156",
        textMrp = "₹ 13,000",
        textDays = "3 Days",
        textDiscounted = "₹10,500",
        textNights = "2 Nights",
        textButton = "Enquire Now"
    )
    val shirAjanEll5days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.shir_ajan5day,
        textButton = "Enquire Now",
        textNights = "4 Nights",
        textDays = "5 Days",
        textDiscounted = "₹18,000",
        textMrp = "₹ 21,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Shirdi%20Ajanta%20Ellora%20Tour%20from%20Bangalore&c=m157",
        textName = "Shirdi Ajanta Ellora Tour"
    )
    val excSpirHol3days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.exc_spir3day,
        textName = "Spiritual Holiday to Shirdi",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Divine%20Tirumala%20Tirupati%20Tour&c=m61",
        textMrp = "₹ 13,000",
        textDays = "3 Days",
        textDiscounted = "₹10,000",
        textNights = "2 Nights",
        textButton = "Enquire Now"
    )
    val bestShirPac4days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.bes_shir4day,
        textName = "Bestselling Shirdi Package",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Bestselling%20Shirdi%20Package%20from%20Bangalore&c=m31",
        textMrp = "₹ 17,000",
        textDiscounted = "₹14,300",
        textDays = "4 Days",
        textNights = "3 Nights",
        textButton = "Enquire Now"
    )
    val shirAjanEll4days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.shir_ajan4day,
        textName = "Shirdi Refreshing Vacation",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Shirdi%20Ajanta%20Ellora%20Tour%20for%20a%20Refreshing%20Vacation&c=m158",
        textMrp = "₹ 24,000",
        textDiscounted = "₹21,500",
        textDays = "4 Days",
        textNights = "3 Nights",
        textButton = "Enquire Now"
    )
    val mostPopShir4days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.most_pop4day,
        textName = "Most Popular Shirdi Tour",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Most%20Popular%20Shirdi%20Tour%20from%20Hyderabad&c=m108",
        textMrp = "₹ 21,000",
        textDiscounted = "₹18,499",
        textDays = "4 Days",
        textNights = "3 Nights",
        textButton = "Enquire Now"
    )
    val madRamKan3days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.mad_ram3day,
        textName = "Madurai Rameswaram & Kanyakumari",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Madurai%20Rameswaram%20&%20Kanyakumari&c=m109",
        textMrp = "₹ 9,000",
        textDays = "3 Days",
        textDiscounted = "₹6,000",
        textNights = "2 Nights",
        textButton = "Enquire Now"
    )
    val madRamKan4days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.mad_ram4day,
        textName = "Madurai, Rameswaram & Kanyakumari",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Madurai,%20Rameswaram%20&%20Kanyakumari%20Religious%20Tour&c=m110",
        textMrp = "₹ 13,000",
        textDiscounted = "₹10,000",
        textDays = "4 Days",
        textNights = "3 Nights",
        textButton = "Enquire Now"
    )
    val kanRamDar5days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.kan_ram5day,
        textButton = "Enquire Now",
        textNights = "4 Nights",
        textDays = "5 Days",
        textDiscounted = "₹17,650",
        textMrp = "₹ 20,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Kanyakumari%20Rameswaram%20Darshan&c=m102",
        textName = "Kanyakumari Rameswaram Darshan"
    )
    val southPilRam5days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.south_pil5day,
        textButton = "Enquire Now",
        textNights = "4 Nights",
        textDays = "5 Days",
        textDiscounted = "₹13,000",
        textMrp = "₹ 16,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=South%20Pilgrimage%20Rameswaram%20Kanyakumari%20Madurai%20Tour&c=m159",
        textName = "South Pilgrimage Rameswaram Tour"
    )
    val kerMadRam11days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.ker_mad11day,
        textButton = "Enquire Now",
        textNights = "10 Nights",
        textDays = "11 Days",
        textDiscounted = "₹32,500",
        textMrp = "₹ 35,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Kerala%20with%20Madurai,%20Rameswaram%20&%20Kanyakumari&c=m103",
        textName = "Kerala with Madurai, Rameswaram"
    )
    val encRamKod5days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.enc_ram5day,
        textButton = "Enquire Now",
        textNights = "4 Nights",
        textDays = "5 Days",
        textDiscounted = "₹11,700",
        textMrp = "₹ 14,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Enchanting%20Rameswaram,%20Kodaikanal%20&%20Madurai&c=m62",
        textName = "Enchanting Rameswaram"
    )
    val royMadRam6days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.roy_mad6day,
        textButton = "Enquire Now",
        textNights = "5 Nights",
        textDays = "6 Days",
        textDiscounted = "₹15,420",
        textMrp = "₹ 18,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Royal%20Madurai,%20Rameswaram,%20Kanyakumari%20&%20Kovalam&c=m141",
        textName = "Royal Madurai, Rameswaram, Kanyakumari"
    )
    val specKanRam7days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.spec_kan7day,
        textName = "Spectacular Kanyakumari, Rameswaram",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Spectacular%20Kanyakumari,%20Rameswaram%20&%20Pondicherry&c=m160",
        textMrp = "₹ 27,000",
        textDays = "7 Days",
        textDiscounted = "₹24,250",
        textNights = "6 Nights",
        textButton = "Enquire Now"
    )
    val panKanRam8days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.pan_kan8day,
        textNights = "7 Nights",
        textDays = "8 Days",
        textDiscounted = "₹23,350",
        textMrp = "₹ 26,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Panoramic%20Kanyakumari,%20Rameswaram,%20Madurai%20&%20Kodaikanal&c=m129",
        textName = "Panoramic Kanyakumari, Rameswaram, Madurai",
        textButton = "Enquire Now"
    )
    val panTourSouth10days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.pan_tour10day,
        textName = "Panoramic Tour to Tirupati",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Panoramic%20Tour%20of%20South%20from%20Trivandrum%20to%20Tirupati&c=m130",
        textMrp = "₹ 35,000",
        textDiscounted = "₹32,000",
        textDays = "10 Days",
        textNights = "9 Nights",
        textButton = "Enquire Now"
    )
    val encThanRam5days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.enc_than5day,
        textButton = "Enquire Now",
        textNights = "4 Nights",
        textDays = "5 Days",
        textDiscounted = "₹13,000",
        textMrp = "₹ 16,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Enchanting%20Thanjavur,%20Rameswaram%20&%20Madurai&c=m63",
        textName = "Enchanting Thanjavur, Rameswaram"
    )
    val matVrinTour1day = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 2,
        image = R.drawable.mat_vrin_del1day,
        textName = "Mathura Vrindavan Tour",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Mathura%20Vrindavan%20Tour%20from%20Delhi&c=m111",
        textMrp = "₹ 9,000",
        textDiscounted = "₹6,500",
        textDays = "1 Day",
        textNights = "0 Nights",
        textButton = "Enquire Now"
    )
    val pilSpecMat3days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.pil_spec3day,
        textName = "Pilgrims Special Mathura Vrindavan",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Pilgrims%20Special%20Mathura%20Vrindavan%20Tour%20by%20Car&c=m131",
        textMrp = "₹ 8,000",
        textDays = "3 Days",
        textDiscounted = "₹5,999",
        textNights = "2 Nights",
        textButton = "Enquire Now"
    )
    val matVrinTour4days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.mat_vrin_tour4day,
        textName = "Mathura Vrindavan Tour with Taj Mahal",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Mathura%20Vrindavan%20Tour%20with%20Taj%20Mahal&c=m112",
        textMrp = "₹ 13,000",
        textDiscounted = "₹10,999",
        textDays = "4 Days",
        textNights = "3 Nights",
        textButton = "Enquire Now"
    )
    val matVrinAgra3days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.mat_vrin3day,
        textName = "Mathura Heritage Tour",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Mathura%20Vrindavan%20&%20Agra%20Heritage%20Tour&c=m113",
        textMrp = "₹ 12,000",
        textDays = "3 Days",
        textDiscounted = "₹9,500",
        textNights = "2 Nights",
        textButton = "Enquire Now"
    )
    val norIndTem9days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.nor_ind_tem9day,
        textName = "North India with Mathura",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=North%20India%20Temples%20Tour%20with%20Mathura%20Vrindavan%20Trip&c=m120",
        textMrp = "₹ 27,000",
        textDiscounted = "₹24,500",
        textDays = "9 Days",
        textNights = "8 Nights",
        textButton = "Enquire Now"
    )
    val matVrinTour11days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.mat_vrin11day,
        textButton = "Enquire Now",
        textNights = "10 Nights",
        textDays = "11 Days",
        textDiscounted = "₹25,880",
        textMrp = "₹ 28,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Mathura%20Vrindavan%20Tour%20with%20UP%20Pilgrimage&c=m114",
        textName = "Mathura Vrindavan Tour"
    )
    val budTrailTour8days = TravelPackage(
        ism = "Buddhism, Buddhism, Buddhism",
        viewType = 1,
        image = R.drawable.bud_trail8day,
        textNights = "7 Nights",
        textDays = "8 Days",
        textDiscounted = "₹21,000",
        textMrp = "₹ 24,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Buddha%20Trail%20Tour&c=m32",
        textName = "Buddha Trail Tour",
        textButton = "Enquire Now"
    )
    val comVarBodh5days = TravelPackage(
        ism = "Hinduism, Buddhism, Buddhism",
        viewType = 1,
        image = R.drawable.com_var5day,
        textButton = "Enquire Now",
        textNights = "4 Nights",
        textDays = "5 Days",
        textDiscounted = "₹14,000",
        textMrp = "₹ 17,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Complete%20Varanasi%20&%20Bodh%20Gaya%20Tour&c=m46",
        textName = "Varanasi & Bodh Gaya"
    )
    val pilSpecPac5days = TravelPackage(
        ism = "Hinduism, Buddhism, Buddhism",
        viewType = 1,
        image = R.drawable.pil_spec5day,
        textButton = "Enquire Now",
        textNights = "4 Nights",
        textDays = "5 Days",
        textDiscounted = "₹12,800",
        textMrp = "₹ 15,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Pilgrims%20Special%20Package:%20Varanasi,%20Bodh%20Gaya,%20Allahabad&c=m132",
        textName = "Pilgrimage: Varanasi, Bodh Gaya"
    )
    val budPilTour5days = TravelPackage(
        ism = "Hinduism, Buddhism, Buddhism",
        viewType = 1,
        image = R.drawable.bud_pil5day,
        textButton = "Enquire Now",
        textNights = "4 Nights",
        textDays = "5 Days",
        textDiscounted = "₹18,500",
        textMrp = "₹ 21,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Buddhist%20Pilgrimage%20Tour:%20Varanasi%20&%20Bodh%20Gaya&c=m33",
        textName = "Varanasi & Bodh Gaya"
    )
    val serVarBodh5days = TravelPackage(
        ism = "Hinduism, Buddhism, Buddhism",
        viewType = 1,
        image = R.drawable.ser_var5day,
        textButton = "Enquire Now",
        textNights = "4 Nights",
        textDays = "5 Days",
        textDiscounted = "₹10,450",
        textMrp = "₹ 13,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Serenity%20of%20Varanasi%20&%20Bodh%20Gaya&c=m161",
        textName = "Varanasi & Bodh Gaya"
    )
    val entVarBodh4days = TravelPackage(
        ism = "Hinduism, Buddhism, Buddhism",
        viewType = 1,
        image = R.drawable.ent_var4day,
        textName = "Enthralling Varanasi, Bodh Gaya",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Enthralling%20Varanasi,%20Bodh%20Gaya%20&%20Patna&c=m64",
        textMrp = "₹ 18,000",
        textDiscounted = "₹15,000",
        textDays = "4 Days",
        textNights = "3 Nights",
        textButton = "Enquire Now"
    )
    val ayodVarBodh5days = TravelPackage(
        ism = "Hinduism, Buddhism, Buddhism",
        viewType = 1,
        image = R.drawable.ayod_var5day,
        textButton = "Enquire Now",
        textNights = "4 Nights",
        textDays = "5 Days",
        textDiscounted = "₹13,950",
        textMrp = "₹ 16,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Ayodhya,%20Varanasi%20&%20Bodh%20Gaya%20Tour&c=m11",
        textName = "Varanasi & Bodh Gaya Tour"
    )
    val marDelHar6days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.mar_del6day,
        textButton = "Enquire Now",
        textNights = "5 Nights",
        textDays = "6 Days",
        textDiscounted = "₹21,950",
        textMrp = "₹ 24,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Marvellous%20Delhi,%20Haridwar%20&%20Varanasi&c=m115",
        textName = "Marvellous Haridwar & Varanasi"
    )
    val varBodhGaya6days = TravelPackage(
        ism = "Hinduism, Buddhism, Buddhism",
        viewType = 1,
        image = R.drawable.var_bodh6day,
        textButton = "Enquire Now",
        textNights = "5 Nights",
        textDays = "6 Days",
        textDiscounted = "₹22,000",
        textMrp = "₹ 25,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Varanasi,%20Bodh%20Gaya%20&%20Allahabad%20Tour&c=m180",
        textName = "Varanasi, Bodh Gaya Tour"
    )
    val holTourPac7days = TravelPackage(
        ism = "Hinduism, Buddhism, Sikhism",
        viewType = 1,
        image = R.drawable.hol_tour7day,
        textName = "Tour Package of Varanasi",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Holiday%20Tour%20Package%20of%20Varanasi%20&%20Bihar&c=m83",
        textMrp = "₹ 22,000",
        textDays = "7 Days",
        textDiscounted = "₹19,450",
        textNights = "6 Nights",
        textButton = "Enquire Now"
    )
    val budTrailBodh6days = TravelPackage(
        ism = "Buddhism, Buddhism, Buddhism",
        viewType = 1,
        image = R.drawable.bud_trail6day,
        textButton = "Enquire Now",
        textNights = "5 Nights",
        textDays = "6 Days",
        textDiscounted = "₹15,450",
        textMrp = "₹ 18,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Buddha%20Trail-%20Bodh%20Gaya%20&%20Rajgir&c=m34",
        textName = "Buddha Trail- Bodh Gaya"
    )
    val shirShaniShin5days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.jyot_tour3day,
        textButton = "Enquire Now",
        textNights = "4 Nights",
        textDays = "5 Days",
        textDiscounted = "₹11,999",
        textMrp = "₹ 14,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Shirdi,%20Shani%20Shingnapur%20&%20Jyotirlinga%20Tour&c=m162",
        textName = "Shirdi, Shani Shingnapur Tour"
    )
    val jyotTourNas3days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.jyot_tour3day,
        textName = "Jyotirlinga's Tour with Nashik",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Jyotirlinga's%20Tour%20with%20Nashik%20&%20Aurangabad&c=m92",
        textMrp = "₹ 22,000",
        textDays = "3 Days",
        textDiscounted = "₹19,500",
        textNights = "2 Nights",
        textButton = "Enquire Now"
    )
    val dharDalFam5days = TravelPackage(
        ism = "Hinduism, Buddhism, Sikhism",
        viewType = 1,
        image = R.drawable.dhar_dal5day,
        textButton = "Enquire Now",
        textNights = "4 Nights",
        textDays = "5 Days",
        textDiscounted = "₹15,000",
        textMrp = "₹ 18,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Dharamshala%20Dalhousie%20Family%20Package%20with%20Amritsar&c=m57",
        textName = "Family Package with Amritsar"
    )
    val dalAmrFam5days = TravelPackage(
        ism = "Hinduism, Sikhism, Sikhism",
        viewType = 1,
        image = R.drawable.dal_amr5day,
        textButton = "Enquire Now",
        textNights = "4 Nights",
        textDays = "5 Days",
        textDiscounted = "₹9,500",
        textMrp = "₹ 12,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Dalhousie%20&%20Amritsar%20Family%20Tour&c=m58",
        textName = "Dalhousie Amritsar Family Tour"
    )
    val beautShimMan8days = TravelPackage(
        ism = "Hinduism, Sikhism, Sikhism",
        viewType = 1,
        image = R.drawable.beaut_shim8day,
        textNights = "7 Nights",
        textDays = "8 Days",
        textDiscounted = "₹18,999",
        textMrp = "₹ 21,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Beautiful%20Shimla%20Manali%20with%20Amritsar%20Tour&c=m35",
        textName = "Beautiful Amritsar Tour",
        textButton = "Enquire Now"
    )
    val supSavTour10days = TravelPackage(
        ism = "Buddhism, Sikhism, Sikhism",
        viewType = 1,
        image = R.drawable.sup_sav10day,
        textName = "Super Saver Tour Amritsar",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Super%20Saver%20Tour%20Package%20of%20Himachal%20&%20Amritsar&c=m163",
        textMrp = "₹ 32,000",
        textDiscounted = "₹29,000",
        textDays = "10 Days",
        textNights = "9 Nights",
        textButton = "Enquire Now"
    )
    val dharDalAmr6days = TravelPackage(
        ism = "Hinduism, Buddhism, Sikhism",
        viewType = 1,
        image = R.drawable.dhar_dal6day,
        textButton = "Enquire Now",
        textNights = "5 Nights",
        textDays = "6 Days",
        textDiscounted = "₹18,199",
        textMrp = "₹ 21,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Dharamshala,%20Dalhousie%20&%20Amritsar%20Tour&c=m59",
        textName = "Dalhousie & Amritsar Tour"
    )
    val amrChanGroup4days = TravelPackage(
        ism = "Sikhism, Sikhism, Sikhism",
        viewType = 1,
        image = R.drawable.amr_chan4day,
        textName = "Amritsar Group Tour",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Amritsar%20&%20Chandigarh%20Group%20Tour&c=m12",
        textMrp = "₹ 10,000",
        textDiscounted = "₹7,000",
        textDays = "4 Days",
        textNights = "3 Nights",
        textButton = "Enquire Now"
    )
    val golTemAmr2days = TravelPackage(
        ism = "Sikhism, Sikhism, Sikhism",
        viewType = 4,
        image = R.drawable.gol_tem2day,
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Golden%20Temple%20Amritsar%20Package%20(with%20Train%20Tickets)&c=m75",
        textMrp = "₹ 8,000",
        textDays = "2 Days",
        textDiscounted = "₹5,500",
        textNights = "1 Night",
        textButton = "Enquire Now",
        textName = "Golden Temple Amritsar Package"
    )
    val mclDalFam7days = TravelPackage(
        ism = "Hinduism, Buddhism, Buddhism",
        viewType = 1,
        image = R.drawable.mcl_dal7day,
        textName = "McLeodganj Tour from Amritsar",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Mcleodganj%20&%20Dalhousie%20Family%20Tour%20from%20Amritsar&c=m116",
        textMrp = "₹ 22,000",
        textDays = "7 Days",
        textDiscounted = "₹19,500",
        textNights = "6 Nights",
        textButton = "Enquire Now"
    )
    val scenHimAmr8days = TravelPackage(
        ism = "Buddhism, Sikhism, Sikhism",
        viewType = 1,
        image = R.drawable.scen_him8day,
        textNights = "7 Nights",
        textDays = "8 Days",
        textDiscounted = "₹24,750",
        textMrp = "₹ 27,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Scenic%20Himachal%20Amritsar%20Tour&c=m164",
        textName = "Scenic Himachal Amritsar Tour",
        textButton = "Enquire Now"
    )
    val pilBanTir3days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.pil_ban3day,
        textName = "Pilgrimage to Tirupati",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Pilgrimage%20from%20Bangalore%20to%20Tirupati&c=m133",
        textMrp = "₹ 10,000",
        textDays = "3 Days",
        textDiscounted = "₹7,000",
        textNights = "2 Nights",
        textButton = "Enquire Now"
    )
    val chenTirTour3days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.chen_tir3day,
        textName = "Chennai to Tirupati Tour",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Chennai%20to%20Tirupati%20Tour%20Package&c=m47",
        textMrp = "₹ 11,000",
        textDays = "3 Days",
        textDiscounted = "₹8,999",
        textNights = "2 Nights",
        textButton = "Enquire Now"
    )
    val divTirTir2days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.div_tir2day,
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Divine%20Tirumala%20Tirupati%20Tour&c=m60",
        textMrp = "₹ 7,000",
        textDays = "2 Days",
        textDiscounted = "₹4,800",
        textNights = "1 Night",
        textButton = "Enquire Now",
        textName = "Divine Tirumala Tirupati Tour"
    )
    val banTirTour2days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.ban_tir2day,
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Bangalore%20to%20Tirupati%20Tour&c=m40",
        textMrp = "₹ 9,000",
        textDays = "2 Days",
        textDiscounted = "₹6,500",
        textNights = "1 Night",
        textButton = "Enquire Now",
        textName = "Bangalore to Tirupati Tour"
    )
    val tirBalDar3days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.tir_bal3day,
        textName = "Tirupati Balaji Darshan",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Tirupati%20Balaji%20Darshan%20&%20Chennai%20Sightseeing%20Package&c=m169",
        textMrp = "₹ 11,000",
        textDays = "3 Days",
        textDiscounted = "₹8,000",
        textNights = "2 Nights",
        textButton = "Enquire Now"
    )
    val tirDarPac3days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.tir_dar3day,
        textName = "Tirupati Darshan Package",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Tirupati%20Darshan%20Package&c=m170",
        textMrp = "₹ 15,000",
        textDays = "3 Days",
        textDiscounted = "₹12,000",
        textNights = "2 Nights",
        textButton = "Enquire Now"
    )
    val pocFrienTir4days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.poc_frien4day,
        textName = "Pocket Friendly Tirupati",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Pocket%20Friendly%20Tirupati-%20Darshan%20&%20Sightseeing&c=m134",
        textMrp = "₹ 6,000",
        textDiscounted = "₹3,820",
        textDays = "4 Days",
        textNights = "3 Nights",
        textButton = "Enquire Now"
    )
    val relPacTir6days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.rel_pac6day,
        textButton = "Enquire Now",
        textNights = "5 Nights",
        textDays = "6 Days",
        textDiscounted = "₹6,780",
        textMrp = "₹ 9,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Religious%20Package-%20Tirupati,%20Rameswaram%20&%20Madurai&c=m142",
        textName = "Religious Package- Tirupati, Rameswaram"
    )
    val quickDarTir3days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.quick_dar3day,
        textName = "Quick Darshan at Tirupati",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Quick%20Darshan%20at%20Tirupati&c=m135",
        textMrp = "₹ 15,000",
        textDays = "3 Days",
        textDiscounted = "₹12,899",
        textNights = "2 Nights",
        textButton = "Enquire Now"
    )
    val mahDarPac3days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.mah_dar3day,
        textName = "Mahakal Darshan Package",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Mahakal%20Darshan%20Package&c=m117",
        textMrp = "₹ 11,000",
        textDays = "3 Days",
        textDiscounted = "₹8,200",
        textNights = "2 Nights",
        textButton = "Enquire Now"
    )
    val ujjIndSig3days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.ujj_ind3day,
        textName = "Ujjain Indore Sightseeing Package",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Ujjain%20Indore%20Sightseeing%20Package%20with%20Omkareshwar%20Darshan&c=m174",
        textMrp = "₹ 12,000",
        textDays = "3 Days",
        textDiscounted = "₹9,490",
        textNights = "2 Nights",
        textButton = "Enquire Now"
    )
    val jyotirTour3days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.jyotir_tour3day,
        textName = "Jyotirlinga Tour",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Jyotirlinga%20Tour&c=m93",
        textMrp = "₹ 7,000",
        textDays = "3 Days",
        textDiscounted = "₹4,999",
        textNights = "2 Nights",
        textButton = "Enquire Now"
    )
    val madPradHer8days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.mad_prad8day,
        textNights = "7 Nights",
        textDays = "8 Days",
        textDiscounted = "₹8,500",
        textMrp = "₹ 11,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Madhya%20Pradesh%20Heritage%20with%20Ujjain%20Simhastha%20&%20Jyotirlinga%20Darshan&c=m118",
        textName = "Madhya Pradesh with Ujjain",
        textButton = "Enquire Now"
    )
    val nasIgatTour2days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.nas_igat2day,
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Nashik%20Igatpuri%20Tour%20from%20Pune&c=m121",
        textMrp = "₹ 10,000",
        textDays = "2 Days",
        textDiscounted = "₹7,100",
        textNights = "1 Night",
        textButton = "Enquire Now",
        textName = "Nashik Igatpuri Tour"
    )
    val nasBhanIgat3days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.nas_bhan3day,
        textName = "Nashik Bhandardara Igatpuri Tour",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Nashik%20Bhandardara%20Igatpuri%20Tour%20from%20Mumbai&c=m122",
        textMrp = "₹ 13,000",
        textDays = "3 Days",
        textDiscounted = "₹10,100",
        textNights = "2 Nights",
        textButton = "Enquire Now"
    )
    val nasIgatMum2days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.nas_bhan3day,
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Nashik%20Igatpuri%20Tour%20from%20Mumbai&c=m123",
        textMrp = "₹ 9,000",
        textDays = "2 Days",
        textDiscounted = "₹6,900",
        textNights = "1 Night",
        textButton = "Enquire Now",
        textName = "Nashik Igatpuri Tour"
    )
    val nasShirBhan4days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.nas_igat2day,
        textName = "Nashik Shirdi Bhandardara Igatpuri",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Nashik%20Shirdi%20Bhandardara%20Igatpuri%20Tour%20from%20Mumbai&c=m124",
        textMrp = "₹ 15,000",
        textDiscounted = "₹12,850",
        textDays = "4 Days",
        textNights = "3 Nights",
        textButton = "Enquire Now"
    )
    val quickTourPuri2days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.quick_tour2day,
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Quick%20Tour%20of%20Puri%20&%20Konark%20from%20Bhubaneswar&c=m136",
        textMrp = "₹ 8,000",
        textDays = "2 Days",
        textDiscounted = "₹5,750",
        textNights = "1 Night",
        textButton = "Enquire Now",
        textName = "Quick Tour of Puri"
    )
    val bestPuriTour2days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.best_puri2day,
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Best%20of%20Puri%20from%20Bhubaneswar&c=m36",
        textMrp = "₹ 9,000",
        textDays = "2 Days",
        textDiscounted = "₹6,250",
        textNights = "1 Night",
        textButton = "Enquire Now",
        textName = "Best of Puri"
    )
    val bestBhubPuri3days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.best_bhub3day,
        textName = "Bhubaneswar & Puri",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Best%20of%20Bhubaneswar%20&%20Puri%20from%20Bhubaneswar&c=m37",
        textMrp = "₹ 12,000",
        textDays = "3 Days",
        textDiscounted = "₹9,450",
        textNights = "2 Nights",
        textButton = "Enquire Now"
    )
    val bestPuriPuri2days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.quick_tour2day,
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Best%20of%20Puri%20from%20Puri&c=m38",
        textMrp = "₹ 8,000",
        textDays = "2 Days",
        textDiscounted = "₹5,500",
        textNights = "1 Night",
        textButton = "Enquire Now",
        textName = "Best of Puri"
    )
    val bhubPuriKon4days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.bhub_puri4day,
        textName = "Bhubaneswar Puri Konark Tour",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Bhubaneswar%20Puri%20Konark%20Tour%20from%20Bhubaneswar&c=m39",
        textMrp = "₹ 15,000",
        textDiscounted = "₹12,950",
        textDays = "4 Days",
        textNights = "3 Nights",
        textButton = "Enquire Now"
    )
    val spirTourUtt8days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.spir_tour8day,
        textNights = "7 Nights",
        textDays = "8 Days",
        textDiscounted = "₹23,340",
        textMrp = "₹ 26,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Spiritual%20Tour%20of%20Uttarakhand%20including%20Haridwar%20&%20Kedarnath&c=m165",
        textName = "Spiritual Tour of Uttarakhand",
        textButton = "Enquire Now"
    )
    val tourPacInd3days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.tour_pac3day,
        textName = "Char Dham including Haridwar",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Tour%20Package%20in%20India:%20Char%20Dham%20Yatra%20by%20Helicopter%20including%20Haridwar&c=m171",
        textMrp = "₹ 1,68,000",
        textDays = "3 Days",
        textDiscounted = "₹1,65,000",
        textNights = "2 Nights",
        textButton = "Enquire Now"
    )
    val uttRelTour11days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.utt_rel11day,
        textButton = "Enquire Now",
        textNights = "10 Nights",
        textDays = "11 Days",
        textDiscounted = "₹28,250",
        textMrp = "₹ 31,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Uttarakhand%20Religious%20Tour%20including%20Haridwar%20&%20Kedarnath&c=m175",
        textName = "Uttarakhand Religious Tour"
    )
    val charDhamDar11days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.char_dham11day,
        textButton = "Enquire Now",
        textNights = "10 Nights",
        textDays = "11 Days",
        textDiscounted = "₹29,000",
        textMrp = "₹ 32,000",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Char%20Dham%20Darshan&c=m48",
        textName = "Char Dham Darshan"
    )
    val charYatHel6days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 2,
        image = R.drawable.char_yat6day,
        textButton = "Enquire Now",
        textNights = "5 Nights",
        textDays = "6 Days",
        textDiscounted = "₹Request",
        textMrp = "₹ Price on",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Chardham%20Yatra%20by%20Helicopter&c=m181",
        textName = "Chardham Yatra by Helicopter"
    )
    val gujTemTour3days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.guj_tem3day,
        textName = "Gujarat Temples Tour",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Gujarat%20Temples%20Tour&c=m182",
        textMrp = "₹ Price on",
        textDays = "3 Days",
        textDiscounted = "₹Request",
        textNights = "2 Nights",
        textButton = "Enquire Now"
    )
    val sevenJyotDar8days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.sev_jyot8day,
        textNights = "7 Nights",
        textDays = "8 Days",
        textDiscounted = "₹Request",
        textMrp = "₹ Price on",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=7%20Jyotirlinga%20Darshan&c=m183",
        textName = "7 Jyotirlinga Darshan",
        textButton = "Enquire Now"
    )
    val doDhamYat5days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 2,
        image = R.drawable.do_dham5day,
        textButton = "Enquire Now",
        textNights = "4 Nights",
        textDays = "5 Days",
        textDiscounted = "₹Request",
        textMrp = "₹ Price on",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Do%20Dham%20Yatra%20by%20Helicopter%20from%20Haridwar&c=m184",
        textName = "Do Dham from Haridwar"
    )
    val mataVaisDevi3days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 2,
        image = R.drawable.mata_vais3day,
        textName = "Vaishno Devi by Helicopter",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Mata%20Vaishno%20Devi%20Yatra%20by%20Helicopter&c=m185",
        textMrp = "₹ Price on",
        textDays = "3 Days",
        textDiscounted = "₹Request",
        textNights = "2 Nights",
        textButton = "Enquire Now"
    )
    val charYat12days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.char_yat12day,
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Chardham%20Yatra&c=m186",
        textMrp = "₹ Price on",
        textDays = "12 Days",
        textDiscounted = "₹Request",
        textNights = "11 Nights",
        textButton = "Enquire Now",
        textName = "Chardham Yatra"
    )
    val twelveJyotTour19days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.twel_jyot19day,
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=12%20Jyotirlinga%20Tour&c=m187",
        textMrp = "₹ Price on",
        textDays = "19 Days",
        textDiscounted = "₹Request",
        textNights = "18 Nights",
        textButton = "Enquire Now",
        textName = "12 Jyotirlinga Tour"
    )
    val kailManYat14days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.kail_man14day,
        textName = "Kailash Mansarovar Yatra",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Kailash%20Mansarovar%20Yatra&c=m188",
        textMrp = "₹ Price on",
        textDays = "14 Days",
        textDiscounted = "₹Request",
        textNights = "13 Nights",
        textButton = "Enquire Now"
    )
    val tamNaduTem7days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.tam_nadu7day,
        textName = "Tamil Nadu Temples Tour",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Tamil%20Nadu%20Temples%20Tour&c=m189",
        textMrp = "₹ Price on",
        textDays = "7 Days",
        textDiscounted = "₹Request",
        textNights = "6 Nights",
        textButton = "Enquire Now"
    )
    val ekDhamYat5days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.ek_dham5day,
        textButton = "Enquire Now",
        textNights = "4 Nights",
        textDays = "5 Days",
        textDiscounted = "₹Request",
        textMrp = "₹ Price on",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Ek%20Dham%20Yatra-%20Gangotri&c=m190",
        textName = "Ek Dham Yatra- Gangotri"
    )
    val kailManYat9days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 2,
        image = R.drawable.kail_man9day,
        textName = "Kailash Mansarovar by Helicopter",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Kailash%20Mansarovar%20Yatra%20by%20Helicopter&c=m191",
        textMrp = "₹ Price on",
        textDiscounted = "₹Request",
        textDays = "9 Days",
        textNights = "8 Nights",
        textButton = "Enquire Now"
    )
    val maaVaisDevi3days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.maa_vais3day,
        textName = "Maa Vaishno Devi Yatra",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Maa%20Vaishno%20Devi%20Yatra%20by%20Road&c=m192",
        textMrp = "₹ Price on",
        textDays = "3 Days",
        textDiscounted = "₹Request",
        textNights = "2 Nights",
        textButton = "Enquire Now"
    )
    val damTourHel6days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 2,
        image = R.drawable.dam_tour6day,
        textButton = "Enquire Now",
        textNights = "5 Nights",
        textDays = "6 Days",
        textDiscounted = "₹Request",
        textMrp = "₹ Price on",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Damodarkund%20Tour%20by%20Helicopter&c=m193",
        textName = "Damodarkund Tour by Helicopter"
    )
    val harKiDun13days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.har_ki13day,
        textName = "Har Ki Dun Trek",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Har%20Ki%20Dun%20Trek&c=m194",
        textMrp = "Price on",
        textDiscounted = "₹Request",
        textDays = "13 Days",
        textNights = "12 Nights",
        textButton = "Enquire Now"
    )
    val spirVarTour4days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.spir_var4day,
        textName = "Spiritual Varanasi Tour",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Spiritual%20Varanasi%20Tour&c=m195",
        textMrp = "Price on",
        textDiscounted = "₹Request",
        textDays = "4 Days",
        textNights = "3 Nights",
        textButton = "Enquire Now"
    )
    val ganGaumTap11days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.gan_gaum11day,
        textButton = "Enquire Now",
        textNights = "10 Nights",
        textDays = "11 Days",
        textDiscounted = "₹Request",
        textMrp = "Price on",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Gangotri%20Gaumukh%20Tapovan%20Trek&c=m196",
        textName = "Gangotri Gaumukh Tapovan Trek"
    )
    val revLordKris6days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.rev_lord6day,
        textButton = "Enquire Now",
        textNights = "5 Nights",
        textDays = "6 Days",
        textDiscounted = "₹Request",
        textMrp = "₹ Price on",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Revisiting%20Lord%20Krishna's%20Childhood&c=m197",
        textName = "Revisiting Lord Krishna's Childhood"
    )
    val tibOverTour8days = TravelPackage(
        ism = "Buddhism, Buddhism, Buddhism",
        viewType = 1,
        image = R.drawable.tib_over8day,
        textNights = "7 Nights",
        textDays = "8 Days",
        textDiscounted = "₹Request",
        textMrp = "₹ Price on",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Tibet%20Overland%20Tour&c=m198",
        textName = "Tibet Overland Tour",
        textButton = "Enquire Now"
    )
    val panjTakExp7days = TravelPackage(
        ism = "Sikhism, Sikhism, Sikhism",
        viewType = 1,
        image = R.drawable.panj_tak7day,
        textName = "Panj Takht Express",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Panj%20Takht%20Express&c=m199",
        textMrp = "₹ Price on",
        textDays = "7 Days",
        textDiscounted = "₹Request",
        textNights = "6 Nights",
        textButton = "Enquire Now"
    )
    val ekDhamYam5days = TravelPackage(
        ism = "Hinduism, Hinduism, Hinduism",
        viewType = 1,
        image = R.drawable.ek_dham_yam5day,
        textButton = "Enquire Now",
        textNights = "4 Nights",
        textDays = "5 Days",
        textDiscounted = "₹Request",
        textMrp = "₹ Price on",
        textUrl = "https://www.pilgrimagetour.in/packages/mob/m-query.php?n=Ek%20Dham%20Yatra-%20Yamunotri&c=m200",
        textName = "Ek Dham Yatra- Yamunotri"
    )

    fun ismArrayList(): ArrayList<Destination> {

        return arrayListOf<Destination>().apply {

            add(
                Destination(
                    actualName = "Hinduism",
                    image = R.drawable.hinduism_om,
                    textName = "Hinduism",
                    ""
                )
            )
            add(
                Destination(
                    actualName = "Buddhism",
                    image = R.drawable.buddhism_dharmachakra,
                    textName = "Buddhism",
                    ""
                )
            )
            add(
                Destination(
                    actualName = "Jainism", image = R.drawable.jainism, textName = "Jainism", ""
                )
            )
            add(
                Destination(
                    actualName = "Sikhism", image = R.drawable.sikhism, textName = "Sikhism", ""
                )
            )
        }
    }
}
