package cz.pivkavmeste.aamte2018.householdchores

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Room")
data class Room (
        @PrimaryKey
        val id: Int = 0,
        val name: String,
        val memberName: String,
        val icon: Int = 0
)