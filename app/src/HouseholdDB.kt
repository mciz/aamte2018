package cz.pivkavmeste.aamte2018.householdchores

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room as Roomx

@Database(entities = [Room::class, Member::class], version = 1)
abstract class HouseholdDB: RoomDatabase() {

    abstract fun memberDao(): MemberDao
    abstract fun roomDao(): RoomDao

    companion object {
        var INSTANCE: HouseholdDB? = null
        fun getAppDataBase(context: Context): HouseholdDB? {
            if (INSTANCE == null){
                synchronized(HouseholdDB::class){
                    INSTANCE = Roomx.databaseBuilder(context.applicationContext, HouseholdDB::class.java, "myDB").allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }
        fun destroyDataBase(){
            INSTANCE = null
        }
    }
}