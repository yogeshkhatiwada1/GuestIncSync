package com.example.guestincsync.ui.home

import BookedRoomAdapter
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.TabHost
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.guestincsync.R
import com.example.guestincsync.databinding.FragmentHomeBinding
import com.example.guestincsync.nepalidate.ConverterUtil
import com.example.guestincsync.ui.adapter.AvailableRoomAdapter
import com.example.guestincsync.ui.dataModelClass.BookedRoom
import com.example.guestincsync.ui.dataModelClass.Room
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var availableRoomAdapter: AvailableRoomAdapter
    private lateinit var bookedRoomAdapter: BookedRoomAdapter
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
        homeViewModel.currencyData.observe(viewLifecycleOwner){newCurrency->
            currencyDaily.text = newCurrency
            currencyMonthly.text = newCurrency
        }
        val currencyFormatDaily:TextView = binding.textViewDailyIncome
        val currencyFormatMonthly:TextView = binding.textViewMonthlyIncome
        val currencyFormatDailyIncomeUp:TextView = binding.textViewDailyIncomeUp
        val currencyFormatDailyIncomeDown:TextView = binding.textViewDailyIncomeDown
        val currencyFormatMonthlyIncomeUp:TextView = binding.textViewMonthlyIncomeUp
        val currencyFormatMonthlyIncomeDown:TextView = binding.textViewMonthlyIncomeDown
        val currencyFormat = sharedPreferences.getString("CurrencyFormat", "Numeric/Alphabetical Format") ?: ""
        if(currencyFormat=="Numeric/Alphabetical Format"){
            currencyFormatDaily.text = "5k"
            currencyFormatMonthly.text = "10k"
             currencyFormatDailyIncomeUp.text ="2k"
             currencyFormatDailyIncomeDown.text ="2k"
             currencyFormatMonthlyIncomeUp.text ="5k"
             currencyFormatMonthlyIncomeDown.text ="5k"
        }
        else{
            currencyFormatDaily.text ="5000"
            currencyFormatMonthly.text = "10000"
            currencyFormatDailyIncomeUp.text ="2000"
            currencyFormatDailyIncomeDown.text ="2000"
            currencyFormatMonthlyIncomeUp.text ="5000"
            currencyFormatMonthlyIncomeDown.text ="5000"
        }
        homeViewModel.currencyFormat.observe(viewLifecycleOwner){newCurrencyFormat->
            if(newCurrencyFormat=="A"){
                currencyFormatDaily.text = "5k"
                currencyFormatMonthly.text = "10k"
                currencyFormatDailyIncomeUp.text ="2k"
                currencyFormatDailyIncomeDown.text ="2k"
                currencyFormatMonthlyIncomeUp.text ="5k"
                currencyFormatMonthlyIncomeDown.text ="5k"
            }
            if(newCurrencyFormat=="N"){
                currencyFormatDaily.text = "5000"
                currencyFormatMonthly.text = "10000"
                currencyFormatDailyIncomeUp.text ="2000"
                currencyFormatDailyIncomeDown.text ="2000"
                currencyFormatMonthlyIncomeUp.text ="5000"
                currencyFormatMonthlyIncomeDown.text ="5000"
            }
        }
        val dateFormat = sharedPreferences.getString("DateFormat", "AD") ?: ""
        val date: TextView = binding.textViewDate
        if(dateFormat=="AD") {
            date.text = adDate()
        }
        else{
                val currentDate = Calendar.getInstance().time
                // Define the desired date format
                val dateFormat = SimpleDateFormat("dd MM yyyy", Locale.ENGLISH)
                // Format the date
                val formattedDate = dateFormat.format(currentDate)
                val dateNepali = ConverterUtil.convertADDatetoBS(formattedDate)

                val dayFormat = SimpleDateFormat("EEE", Locale.ENGLISH)
                val day = dayFormat.format(currentDate)
                date.text = "$day, $dateNepali"
        }
        homeViewModel.dateFormat.observe(viewLifecycleOwner){newDate->
            if(newDate=="AD") {
                date.text = adDate()
            }
            else{
                val currentDate = Calendar.getInstance().time
                // Define the desired date format
                val dateFormat = SimpleDateFormat("dd MM yyyy", Locale.ENGLISH)
                // Format the date
                val formattedDate = dateFormat.format(currentDate)
                val dateNepali = ConverterUtil.convertADDatetoBS(formattedDate)

                val dayFormat = SimpleDateFormat("EEE", Locale.ENGLISH)
                val day = dayFormat.format(currentDate)
                date.text = "$day, $dateNepali"
            }
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
            val xOff = location[0] + view.width - popupWindow.width -460// Align to the right
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
        val availableRoomRecyclerView: RecyclerView = binding.availableRoomRecyclerView

        val bookedRoomRecyclerView: RecyclerView = binding.bookedRoomRecyclerView

        availableRoomAdapter = AvailableRoomAdapter(getSampleRoomData())
        availableRoomRecyclerView.adapter = availableRoomAdapter
        bookedRoomAdapter = BookedRoomAdapter(getSampleBookedRoomData())
        bookedRoomRecyclerView.adapter = bookedRoomAdapter

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
    private fun getSampleBookedRoomData(): List<BookedRoom> {
        val bookedRoomList = mutableListOf<BookedRoom>()
        bookedRoomList.add(BookedRoom("100", "Normal", "1000/hr", "Mr. Example", "Wed-2024, 11:00 AM", "Thu-2024, 12:30 AM", "2.5 hrs"))
        bookedRoomList.add(BookedRoom("101", "Deluxe", "1500/hr", "Mr. Example", "Wed-2024, 11:00 AM", "Thu-2024, 12:00 AM", "2.5 hrs"))
        bookedRoomList.add(BookedRoom("10100", "Attached", "4500/hr", "Mr. Example", "Wed-2024, 11:00 AM", "Thu-2024, 12:00 AM", "2.5 hrs"))
        bookedRoomList.add(BookedRoom("100", "Normal", "1000/hr", "Mr. Example", "Wed-2024, 11:00 AM", "Thu-2024, 12:00 AM", "2.5 hrs"))
        bookedRoomList.add(BookedRoom("101", "Deluxe", "1500/hr", "Mr. Example", "Wed-2024, 11:00 AM", "Thu-2024, 12:00 AM", "2.5 hrs"))
        bookedRoomList.add(BookedRoom("10100", "Attached", "4500/hr", "Mr. Example", "Wed-2024, 11:00 AM", "Thu-2024, 12:00 AM", "2.5 hrs"))
        bookedRoomList.add(BookedRoom("100", "Normal", "1000/hr", "Mr. Example", "Wed-2024, 11:00 AM", "Thu-2024, 12:00 AM", "2.5 hrs"))
        bookedRoomList.add(BookedRoom("101", "Deluxe", "1500/hr", "Mr. Example", "Wed-2024, 11:00 AM", "Thu-2024, 12:00 AM", "2.5 hrs"))
        bookedRoomList.add(BookedRoom("10100", "Attached", "4500/hr", "Mr. Example", "Wed-2024, 11:00 AM", "Thu-2024, 12:00 AM", "2.5 hrs"))
        bookedRoomList.add(BookedRoom("100", "Normal", "1000/hr", "Mr. Example", "Wed-2024, 11:00 AM", "Thu-2024, 12:00 AM", "2.5 hrs"))
        bookedRoomList.add(BookedRoom("101", "Deluxe", "1500/hr", "Mr. Example", "Wed-2024, 11:00 AM", "Thu-2024, 12:00 AM", "2.5 hrs"))
        bookedRoomList.add(BookedRoom("10100", "Attached", "4500/hr", "Mr. Example", "Wed-2024, 11:00 AM", "Thu-2024, 12:00 AM", "2.5 hrs"))

        return bookedRoomList
    }
    private fun adDate():String{
        // Get the current date
        val currentDate = Calendar.getInstance().time
        // Define the desired date format
        val dateFormat = SimpleDateFormat("EEE, dd MMMM yyyy", Locale.ENGLISH)
        // Format the date
        val formattedDate = dateFormat.format(currentDate)
        return formattedDate
    }
}