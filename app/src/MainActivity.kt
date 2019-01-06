package cz.pivkavmeste.aamte2018.householdchores

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync


class MainActivity : AppCompatActivity() {

    private lateinit var db: HouseholdDB
    private lateinit var memberDao: MemberDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = HouseholdDB.getAppDataBase(applicationContext)!!
        memberDao = db.memberDao()
        val membersAdapter = MembersAdapter()
        doAsync {
            membersAdapter.setData(memberDao.getAll())
        }
        membersListView.layoutManager = LinearLayoutManager(this)
        //membersListView.layoutManager = RecyclerView.LayoutManager(this)?
        membersListView.adapter = membersAdapter

        addMemberbutton1.setOnClickListener {
            val desc = "Spolubydlici"
            val newMemberName = membersEditText.text.toString()
            val newMember = Member(name = newMemberName, description = desc, photo = R.drawable.icon)
            storeToDb(newMember)
            membersAdapter.addMember(newMember)
        }

        showRoomsButton1.setOnClickListener {

            val intent = Intent(applicationContext, RoomActivity::class.java)
            startActivity(intent)
        }

        showTipOfTheDayButton1.setOnClickListener {
            val intent = Intent(applicationContext, FactActivity::class.java)
            startActivity(intent)
        }
    }

    private fun storeToDb(newMember: Member) {
        doAsync {
            newMember.copy(id = memberDao.insert(newMember).toInt())
        }
    }
}
