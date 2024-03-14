package com.example.guestincsync.ui.home

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.TabHost
import android.widget.TabWidget
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.guestincsync.R
import com.example.guestincsync.databinding.FragmentHomeBinding
import com.example.guestincsync.ui.adapter.RoomAdapter
import com.example.guestincsync.ui.dataModelClass.Room
import com.google.android.material.floatingactionbutton.FloatingActionButton


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var roomAdapter: RoomAdapter
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel: HomeViewModel by activityViewModels()
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val currencyDaily:TextView = binding.textViewCurrencyDaily
        val currencyMonthly:TextView = binding.textViewCurrencyMonthly
        val sharedPreferences = requireActivity().getSharedPreferences("MySettings", Context.MODE_PRIVATE)
        val myCurrency = sharedPreferences.getString("MyCurrency", "Rs.") ?: ""
        currencyDaily.text=myCurrency
        currencyMonthly.text=myCurrency
        homeViewModel.text.observe(viewLifecycleOwner){newCurrency->
            currencyDaily.text = newCurrency
            currencyMonthly.text = newCurrency
        }

        val fabButton: FloatingActionButton = binding.fab
        fabButton.setOnClickListener { view ->
            val popUpMenu = layoutInflater.inflate(com.example.guestincsync.R.layout.fab_menu, null)

            // Create a PopupWindow
            val popupWindow = PopupWindow(
                popUpMenu,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true
            )

            // Calculate the position of the fabButton on the screen
            val location = IntArray(2)
            view.getLocationOnScreen(location)

            // Get the status bar height
            val statusBarHeight = getStatusBarHeight()

            // Calculate x and y coordinates to position the PopupWindow above the FloatingActionButton
            val xOff = location[0] + view.width - popupWindow.width -480// Align to the right
            val yOff = location[1] - popupWindow.height - statusBarHeight - 760 // 760dp margin from the bottom

            // Show the PopupWindow at the specified location
            popupWindow.showAtLocation(view, Gravity.NO_GRAVITY, xOff, yOff)

// Find views in the inflated layout
            val textViewCheckIn: TextView = popUpMenu.findViewById(R.id.textViewCheckIn)
            val textViewCheckOut: TextView = popUpMenu.findViewById(R.id.textViewCheckOut)
            val textViewRoomService: TextView = popUpMenu.findViewById(R.id.textViewRoomService)
            val textViewAddRoom: TextView = popUpMenu.findViewById(R.id.textViewAddRoom)
            val textViewAddStaff: TextView = popUpMenu.findViewById(R.id.textViewAddStaff)
            val textViewAddMenuItem: TextView = popUpMenu.findViewById(R.id.textViewAddMenuItem)

            // Set OnClickListener for each TextView
            textViewCheckIn.setOnClickListener {
                Toast.makeText(requireContext(), "Guest Inc Sync", Toast.LENGTH_SHORT).show()
            }

            textViewCheckOut.setOnClickListener {
                Toast.makeText(requireContext(), "Guest Inc Sync", Toast.LENGTH_SHORT).show()
            }

            textViewRoomService.setOnClickListener {
                Toast.makeText(requireContext(), "Guest Inc Sync", Toast.LENGTH_SHORT).show()
            }

            textViewAddRoom.setOnClickListener {
                Toast.makeText(requireContext(), "Guest Inc Sync", Toast.LENGTH_SHORT).show()
            }

            textViewAddStaff.setOnClickListener {
                Toast.makeText(requireContext(), "Guest Inc Sync", Toast.LENGTH_SHORT).show()
            }

            textViewAddMenuItem.setOnClickListener {
                Toast.makeText(requireContext(), "Guest Inc Sync", Toast.LENGTH_SHORT).show()
            }

        }

     val tabHost: TabHost = binding.tabHost
        tabHost.setup()

// Add Available Rooms tab indicator
        val availableRoomTabSpec: TabHost.TabSpec = tabHost.newTabSpec("Available Rooms")
        availableRoomTabSpec.setIndicator("Available Rooms")
        availableRoomTabSpec.setContent(R.id.availableRoom)
        tabHost.addTab(availableRoomTabSpec)

// Add Booked Rooms tab indicator
        val bookedRoomTabSpec: TabHost.TabSpec = tabHost.newTabSpec("Booked Rooms")
        bookedRoomTabSpec.setIndicator("Booked Rooms")
        bookedRoomTabSpec.setContent(R.id.bookedRoom)
        tabHost.addTab(bookedRoomTabSpec)

        // Add Customer today tab indicator
        val customerTodayTabSpec: TabHost.TabSpec = tabHost.newTabSpec("Customer Today")
        customerTodayTabSpec.setIndicator("Customer Today")
        customerTodayTabSpec.setContent(R.id.customerToday)
        tabHost.addTab(customerTodayTabSpec)


        // Initialize RecyclerView with GridLayoutManager
        val recyclerView: RecyclerView = binding.availableRoomRecyclerView
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        roomAdapter = RoomAdapter(getSampleRoomData())
        recyclerView.adapter = roomAdapter


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    // Helper method to get the status bar height
    private fun getStatusBarHeight(): Int {
        var result = 0
        val resourceId: Int = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId)
        }
        return result
    }
    private fun getSampleRoomData(): List<Room> {
        // Replace this with your actual room data retrieval logic
        val roomList = mutableListOf<Room>()
        roomList.add(Room("100", "Normal", "1000/hr"))
        roomList.add(Room("101", "Deluxe", "1500/hr"))
        roomList.add(Room("10100", "Attached", "4500/hr"))
        roomList.add(Room("100", "Normal", "1000/hr"))
        roomList.add(Room("101", "Deluxe", "1500/hr"))
        roomList.add(Room("10100", "Attached", "4500/hr"))
        roomList.add(Room("100", "Normal", "1000/hr"))
        roomList.add(Room("101", "Deluxe", "1500/hr"))
        roomList.add(Room("10100", "Attached", "4500/hr"))
        roomList.add(Room("100", "Normal", "1000/hr"))
        roomList.add(Room("101", "Deluxe", "1500/hr"))
        roomList.add(Room("10100", "Attached", "4500/hr"))
        roomList.add(Room("100", "Normal", "1000/hr"))
        roomList.add(Room("101", "Deluxe", "1500/hr"))
        roomList.add(Room("10100", "Attached", "4500/hr"))
        roomList.add(Room("100", "Normal", "1000/hr"))
        roomList.add(Room("101", "Deluxe", "1500/hr"))
        roomList.add(Room("10100", "Attached", "4500/hr"))
        roomList.add(Room("100", "Normal", "1000/hr"))
        roomList.add(Room("101", "Deluxe", "1500/hr"))
        roomList.add(Room("10100", "Attached", "4500/hr"))
        roomList.add(Room("100", "Normal", "1000/hr"))
        roomList.add(Room("101", "Deluxe", "1500/hr"))
        roomList.add(Room("10100", "Attached", "4500/hr"))

        // Add more rooms as needed
        return roomList
    }
}