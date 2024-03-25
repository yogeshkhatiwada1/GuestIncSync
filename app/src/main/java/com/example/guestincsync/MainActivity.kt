package com.example.guestincsync

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Switch
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.view.GravityCompat
import com.example.guestincsync.databinding.ActivityMainBinding
import com.example.guestincsync.ui.home.HomeViewModel
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private var isAdapterSet = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_profile,
                R.id.nav_rooms,
                R.id.nav_food_menu,
                R.id.nav_staff,
                R.id.nav_transactions,
                R.id.nav_business_stat,
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {
                val dialog = Dialog(this)
                dialog.setContentView(R.layout.menu_setting_layout)
                val saveButton: Button = dialog.findViewById(R.id.saveButton)
                val closeSetting: ImageView = dialog.findViewById(R.id.imageViewCloseSetting)
                loadSettings(dialog)
                saveButton.setOnClickListener {
                    saveSettings(dialog)
                }
                closeSetting.setOnClickListener {
                    dialog.dismiss()
                }
                dialog.show()

                return true
            }

            R.id.action_logout -> {
                showLogoutDialog()
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }
    private fun saveSettings(dialog: Dialog) {
        val currencies = resources.getStringArray(R.array.combined_currencies)
        val spinnerCurrency: Spinner = dialog.findViewById(R.id.spinnerCurrency)
        val selectedCurrency = spinnerCurrency.selectedItem?.toString() ?: ""

        if (!isAdapterSet) {
            val headerCurrency = "Select your Currency"
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listOf(headerCurrency) + currencies)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerCurrency.adapter = adapter

            spinnerCurrency.post {
                spinnerCurrency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        if (position > 0) {
                            val editTextMyCurrency: TextInputEditText = dialog.findViewById(R.id.editTextMyCurrency)
                            editTextMyCurrency.setText(currencies[position - 1].substringBefore(" -"))

                            val currency = editTextMyCurrency.text.toString()
                            val homeViewModel: HomeViewModel by viewModels()
                            homeViewModel.setCurrencyDataFromDialog(currency)
                        }
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        // Do nothing here
                    }
                }

                val position = currencies.indexOf(selectedCurrency)
                if (position != -1) {
                    spinnerCurrency.setSelection(position + 1)
                }
            }
            adapter.notifyDataSetChanged()

            isAdapterSet = true
        }

        val switchNotification: Switch = dialog.findViewById(R.id.switchNotification)
        val editTextMyCurrency: TextInputEditText = dialog.findViewById(R.id.editTextMyCurrency)
        val radioGroupDateFormat: RadioGroup = dialog.findViewById(R.id.radioGroupDateFormat)
        val radioGroupCurrencyFormat: RadioGroup = dialog.findViewById(R.id.radioGroupCurrencyFormat)

        val sharedPreferences = getSharedPreferences("MySettings", Context.MODE_PRIVATE)

        val isNotificationEnabled = switchNotification.isChecked
        val selectedRadioButtonIdDateFormat = radioGroupDateFormat.checkedRadioButtonId
        val selectedRadioButtonDateFormat = dialog.findViewById<RadioButton>(selectedRadioButtonIdDateFormat)
        val dateFormat = selectedRadioButtonDateFormat?.text.toString()

        val selectedRadioButtonIdCurrencyFormat = radioGroupCurrencyFormat.checkedRadioButtonId
        val selectedRadioButtonCurrencyFormat = dialog.findViewById<RadioButton>(selectedRadioButtonIdCurrencyFormat)
        val currencyFormat = selectedRadioButtonCurrencyFormat?.text.toString()

        sharedPreferences.edit {
            putBoolean("NotificationEnabled", isNotificationEnabled)
            putString("MyCurrency", editTextMyCurrency.text.toString())
            putString("DateFormat", dateFormat)
            putString("CurrencyFormat", currencyFormat)
        }

        Toast.makeText(this, "Settings saved", Toast.LENGTH_SHORT).show()

        loadSettings(dialog)
        val currency = editTextMyCurrency.text.toString()
        val homeViewModel: HomeViewModel by viewModels()
        homeViewModel.setCurrencyDataFromDialog(currency)
        if(currencyFormat=="Numeric/Alphabetical Format"){
            homeViewModel.setCurrencyFormatFromDialog("A")
        }
        else{
            homeViewModel.setCurrencyFormatFromDialog("N")
        }

        dialog.dismiss()
    }

    private fun loadSettings(dialog: Dialog) {
        val sharedPreferences = getSharedPreferences("MySettings", Context.MODE_PRIVATE)

        val isNotificationEnabled = sharedPreferences.getBoolean("NotificationEnabled", true)
        val myCurrency = sharedPreferences.getString("MyCurrency", "Rs.") ?: ""
        val dateFormat = sharedPreferences.getString("DateFormat", "AD") ?: ""
        val currencyFormat = sharedPreferences.getString("CurrencyFormat", "Numeric/Alphabetical Format") ?: ""

        val switchNotification: Switch = dialog.findViewById(R.id.switchNotification)
        switchNotification.isChecked = isNotificationEnabled

        val editTextMyCurrency: TextInputEditText = dialog.findViewById(R.id.editTextMyCurrency)
        editTextMyCurrency.setText(myCurrency)

        val radioGroupDateFormat: RadioGroup = dialog.findViewById(R.id.radioGroupDateFormat)
        radioGroupDateFormat.check(
            when (dateFormat) {
                "AD" -> R.id.radioButtonAD
                "BS" -> R.id.radioButtonBS
                else -> -1
            }
        )

        val radioGroupCurrencyFormat: RadioGroup = dialog.findViewById(R.id.radioGroupCurrencyFormat)
        radioGroupCurrencyFormat.check(
            when (currencyFormat) {
                "Numeric/Alphabetical Format" -> R.id.radioButtonNumeric_AlphabeticalFormat
                "Numeric Format" -> R.id.radioButtonNumericFormat
                else -> -1
            }
        )

        val currencies = resources.getStringArray(R.array.combined_currencies)
        val spinnerCurrency: Spinner = dialog.findViewById(R.id.spinnerCurrency)

        val headerCurrency = "Select your Currency"
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listOf(headerCurrency) + currencies)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCurrency.adapter = adapter

        spinnerCurrency.post {
            spinnerCurrency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    if (position > 0) {
                        editTextMyCurrency.setText(currencies[position - 1].substringBefore(" -"))
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Do nothing here
                }
            }
        }
        adapter.notifyDataSetChanged()
    }

    private fun showLogoutDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.logout_dialog, null)
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setView(dialogView)

        val alertDialog = alertDialogBuilder.create()

        val btnCancel = dialogView.findViewById<Button>(R.id.btnCancel)
        btnCancel.setOnClickListener {
            alertDialog.dismiss()
        }

        val btnLogout = dialogView.findViewById<Button>(R.id.btnLogout)
        btnLogout.setOnClickListener {
            Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        alertDialog.show()
    }

    fun rateApp(item: MenuItem){
        Toast.makeText(this,"Coming Soon",Toast.LENGTH_SHORT).show()
    }
    fun shareApp(item:MenuItem){
    var shareApp= Intent(Intent.ACTION_SEND)
        shareApp.type="text/plain"
        var shareMessage="Check Out Guest IncSYnc App"
        shareApp.putExtra(Intent.EXTRA_TEXT,shareMessage)
        startActivity(Intent.createChooser(shareApp,"Share app via"))
        val drawerLayout:DrawerLayout=binding.drawerLayout
        drawerLayout.closeDrawer(GravityCompat.START)
    }
    fun logout(item: MenuItem){
       showLogoutDialog()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
