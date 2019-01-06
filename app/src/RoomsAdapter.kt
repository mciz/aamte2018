package cz.pivkavmeste.aamte2018.householdchores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.room_row.view.*

class RoomsAdapter: RecyclerView.Adapter<RoomsAdapter.RoomViewHolder>() {

    private val rooms = mutableListOf<Room>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder
            = RoomViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.room_row, parent, false))


    override fun getItemCount(): Int = rooms.size


    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        holder.bind(rooms[holder.adapterPosition])
    }

    fun setData(items: List<Room>) {
        rooms.clear()
        rooms.addAll(items)
        notifyDataSetChanged()
    }

    fun addRoom(room: Room) {
        rooms.add(room)
        notifyItemInserted(rooms.lastIndex)
    }

    inner class RoomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(room: Room) {
            with(room) {
                itemView.roomIconImageView.setImageDrawable(ContextCompat.getDrawable(itemView.context, icon))
                itemView.roomNameTextView.text = name
                itemView.memberTextView.text = memberName
            }
        }
    }
}