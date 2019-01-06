package cz.pivkavmeste.aamte2018.householdchores


import retrofit2.Call
import retrofit2.http.GET

interface FactApi {

    companion object {
        val BASE_URL = "http://www.boredapi.com/api/"
    }

    @GET("activity/")
    fun getFact(): Call<Fact>
}
