package cz.pivkavmeste.aamte2018.householdchores

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_room.*
import org.jetbrains.anko.doAsync
import java.util.*


class RoomActivity : AppCompatActivity() {

    private lateinit var db: HouseholdDB
    private lateinit var memberDao: MemberDao
    private lateinit var roomDao: RoomDao


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        db = HouseholdDB.getAppDataBase(applicationContext)!!
        memberDao = db.memberDao()
        roomDao = db.roomDao()

        val roomsAdapter = RoomsAdapter()
        val membersAdapter = MembersAdapter()
        doAsync {
            roomsAdapter.setData(roomDao.getAll())
            membersAdapter.setData(memberDao.getAll())
        }

        roomsListView.layoutManager = LinearLayoutManager(this)
        roomsListView.adapter = roomsAdapter

        addRoomButton2.setOnClickListener {
            val newRoomName = roomsEditText.text.toString()
            val newRoom = Room(name = newRoomName, memberName = getRandomMembername(), icon = R.drawable.icon)
            storeToDb(newRoom)
            roomsAdapter.addRoom(newRoom)
        }

        showMembersButton2.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        showTipOfTheDayButton2.setOnClickListener {
            val intent = Intent(this, FactActivity::class.java)
            startActivity(intent)
        }


    }

    private fun storeToDb(room: Room) {
        doAsync {
            roomDao.insert(room)
        }
    }

    private fun getRandomMembername(): String {
        val size = memberDao.getAll().size
        return memberDao.getAll()[rand(0, size)].name
    }

    private fun rand(from: Int, to: Int) : Int {
        val random = Random()
        return random.nextInt(to - from) + from
    }

}
