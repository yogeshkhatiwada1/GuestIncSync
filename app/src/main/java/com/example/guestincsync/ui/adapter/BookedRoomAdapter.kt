import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.guestincsync.R
import com.example.guestincsync.ui.dataModelClass.BookedRoom

class BookedRoomAdapter(private val roomList: List<BookedRoom>) : RecyclerView.Adapter<BookedRoomAdapter.RoomViewHolder>() {

    class RoomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val roomNumber: TextView = itemView.findViewById(R.id.textView12)
        val roomCategory: TextView = itemView.findViewById(R.id.textView14)
        val roomPrice: TextView = itemView.findViewById(R.id.textView16)
        val checkOutButton1: Button = itemView.findViewById(R.id.button3)
        val checkOutButton2: Button = itemView.findViewById(R.id.button4)
        val imageViewDown: ImageView = itemView.findViewById(R.id.imageView4)
        val imageViewUp: ImageView = itemView.findViewById(R.id.imageView5)
        val customerName: TextView = itemView.findViewById(R.id.textView20)
        val checkInTime: TextView = itemView.findViewById(R.id.textView24)
        val checkOutTime: TextView = itemView.findViewById(R.id.textView26)
        val currentStay: TextView = itemView.findViewById(R.id.textView22)

        // Views to toggle visibility
        lateinit var linearLayout1: LinearLayout
        lateinit var linearLayout2: LinearLayout
        lateinit var textView19: TextView
        lateinit var textView20: TextView
        lateinit var textView23: TextView
        lateinit var textView24: TextView
        lateinit var textView25: TextView
        lateinit var textView26: TextView
        lateinit var textView21: TextView
        lateinit var textView22: TextView
        lateinit var button4: Button

        init {
            // Initialize the views
            linearLayout1 = itemView.findViewById(R.id.linearLayout1)
            linearLayout2 = itemView.findViewById(R.id.linearLayout2)
            textView19 = itemView.findViewById(R.id.textView19)
            textView20 = itemView.findViewById(R.id.textView20)
            textView23 = itemView.findViewById(R.id.textView23)
            textView24 = itemView.findViewById(R.id.textView24)
            textView25 = itemView.findViewById(R.id.textView25)
            textView26 = itemView.findViewById(R.id.textView26)
            textView21 = itemView.findViewById(R.id.textView21)
            textView22 = itemView.findViewById(R.id.textView22)
            button4 = itemView.findViewById(R.id.button4)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.booked_room_recycler, parent, false)
        return RoomViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val room = roomList[position]
        // Bind your data to the views here
        holder.roomNumber.text = room.number
        holder.roomCategory.text = room.category
        holder.roomPrice.text = room.price
        holder.customerName.text = room.customerName
        holder.checkInTime.text = room.checkInTime
        holder.checkOutTime.text = room.checkOutTime
        holder.currentStay.text = room.currentStay

        // Set click listener for the imageView
        holder.imageViewDown.setOnClickListener {
            // Toggle visibility of views
            holder.linearLayout1.visibility = View.VISIBLE
            holder.linearLayout2.visibility = View.VISIBLE
            holder.textView19.visibility = View.VISIBLE
            holder.textView20.visibility = View.VISIBLE
            holder.textView23.visibility = View.VISIBLE
            holder.textView24.visibility = View.VISIBLE
            holder.textView25.visibility = View.VISIBLE
            holder.textView26.visibility = View.VISIBLE
            holder.textView21.visibility = View.VISIBLE
            holder.textView22.visibility = View.VISIBLE
            holder.checkOutButton2.visibility = View.VISIBLE
            holder.checkOutButton1.visibility= View.GONE
            holder.imageViewDown.visibility=View.GONE
            holder.imageViewUp.visibility=View.VISIBLE
        }
        holder.imageViewUp.setOnClickListener {
            // Toggle visibility of views
            holder.linearLayout1.visibility = View.GONE
            holder.linearLayout2.visibility = View.GONE
            holder.textView19.visibility = View.GONE
            holder.textView20.visibility = View.GONE
            holder.textView23.visibility = View.GONE
            holder.textView24.visibility = View.GONE
            holder.textView25.visibility = View.GONE
            holder.textView26.visibility = View.GONE
            holder.textView21.visibility = View.GONE
            holder.textView22.visibility = View.GONE
            holder.checkOutButton2.visibility = View.GONE
            holder.checkOutButton1.visibility= View.VISIBLE
            holder.imageViewUp.visibility=View.GONE
            holder.imageViewDown.visibility=View.VISIBLE
        }
        // Set click listener for the check-out button1
        holder.checkOutButton1.setOnClickListener {
            // Handle check-in button click
        }

        // Set click listener for the check-out button2
        holder.checkOutButton2.setOnClickListener {
            // Handle check-out button click
        }
    }

    override fun getItemCount(): Int {
        return roomList.size
    }
}
