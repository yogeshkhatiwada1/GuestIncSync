package com.example.guestincsync.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.guestincsync.R
import com.example.guestincsync.ui.dataModelClass.Room

class AvailableRoomAdapter(private val roomList: List<Room>) : RecyclerView.Adapter<AvailableRoomAdapter.RoomViewHolder>() {

    class RoomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Define your views here using itemView.findViewById
        val roomNumber: TextView = itemView.findViewById(R.id.textView12)
        val roomCategory: TextView = itemView.findViewById(R.id.textView14)
        val roomPrice: TextView = itemView.findViewById(R.id.textView16)
        val checkInButton: Button = itemView.findViewById(R.id.button3)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.available_room_recycler, parent, false)
        return RoomViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val room = roomList[position]
        // Bind your data to the views here
        holder.roomNumber.text = room.number
        holder.roomCategory.text = room.category
        holder.roomPrice.text = room.price
        // Set click listener for the check-in button
        holder.checkInButton.setOnClickListener {
            // Handle check-in button click
        }
    }

    override fun getItemCount(): Int {
        return roomList.size
    }
}
