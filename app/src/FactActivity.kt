package cz.pivkavmeste.aamte2018.householdchores

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_fact.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FactActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fact)


        val retrofit = Retrofit.Builder()
                .baseUrl(FactApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()


        val api: FactApi = retrofit.create(FactApi::class.java)
        var call = api.getFact()

        showTipOfTheDayButton3.setOnClickListener {
            call = api.getFact()
            call.enqueue(object : Callback<Fact?> {
                override fun onFailure(call: Call<Fact?>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<Fact?>, response: Response<Fact?>) {
                    val fact: Fact = response.body()!!
                    activityTV.text = fact.activity
                    countTV.text = " Type: ${fact.type} \n Participants:  ${fact?.participants} \n Price: ${fact.price} $"
                }
            })
        }

        call.enqueue(object : Callback<Fact?> {
            override fun onFailure(call: Call<Fact?>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Fact?>, response: Response<Fact?>) {
                val fact: Fact = response.body()!!
                activityTV.text = fact.activity

                countTV.text = " Type: ${fact.type} \n Participants:  ${fact?.participants} \n Price: ${fact.price} $"
            }
        })

        showRoomsActivityButton3.setOnClickListener {
            val intent = Intent(applicationContext, RoomActivity::class.java)
            startActivity(intent)
        }

        showMembersActivityButton3.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
