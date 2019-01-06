package cz.pivkavmeste.aamte2018.householdchores

data class Fact( val activity: String = "",
                 val accessibility: Double = 0.0,
                 val type: String = "",
                 val participants: Int = 0,
                 val price: Double = 0.0,
                 val key: String = "")