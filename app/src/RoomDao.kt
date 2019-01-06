package cz.pivkavmeste.aamte2018.householdchores

import androidx.room.*
import cz.pivkavmeste.aamte2018.householdchores.Room

@Dao
interface RoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(room: Room)

    @Update
    fun update(room: Room)

    @Delete
    fun delete(room: Room)

    @Query("SELECT * FROM Room")
    fun getAll(): List<Room>
}