package com.example.firstnutritionproject.FragmentAndActivity.Main

//import com.example.firstnutritionproject.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.mainlanguagesupport.R
import com.example.mainlanguagesupport.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Making the Binding for Main Activity
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        // Return the outer most view
        val view = binding.root
        // make the ROOT view active on the screen.
        setContentView(view)


        //Setting the back button
        setupActionBarWithNavController(findNavController(R.id.fragmentContainerView))

        // showing the back button in action bar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    // this event will enable the back
    // function to the button on press
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}