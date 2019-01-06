package cz.pivkavmeste.aamte2018.householdchores

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Member")
data class Member(
        @PrimaryKey(autoGenerate = true)
        val id: Int = 0,
        val name: String,
        val description: String,
        val photo: Int = 0
)
