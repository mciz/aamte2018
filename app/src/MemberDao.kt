package cz.pivkavmeste.aamte2018.householdchores

import androidx.room.*

@Dao
interface MemberDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(member: Member): Long

    @Update
    fun update(member: Member)

    @Delete
    fun delete(member: Member)

    @Query("SELECT * FROM Member")
    fun getAll(): List<Member>
}