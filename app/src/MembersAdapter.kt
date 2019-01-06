package cz.pivkavmeste.aamte2018.householdchores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.member_row.view.*

class MembersAdapter: RecyclerView.Adapter<MembersAdapter.MemberViewHolder>() {

    private val members = mutableListOf<Member>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder
            = MemberViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.member_row, parent, false))


    override fun getItemCount(): Int = members.size


    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        holder.bind(members[holder.adapterPosition])
    }

    fun setData(items: List<Member>) {
        members.clear()
        members.addAll(items)
        notifyDataSetChanged()
    }

    fun addMember(member: Member) {
        members.add(member)
        notifyDataSetChanged()
    }

    inner class MemberViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

         fun bind(member: Member) {
            with(member) {
                itemView.iconImageView.setImageDrawable(ContextCompat.getDrawable(itemView.context, photo))
                itemView.nameTV.text = name
                itemView.descriptionTV.text = description
            }
        }
    }
}