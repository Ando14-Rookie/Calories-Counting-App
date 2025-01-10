package com.example.firstnutritionproject.FragmentAndActivity.Add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.firstnutritionproject.Data.DataUserTable.DataInputUser
import com.example.firstnutritionproject.Data.ViewModel.UserViewModel
import com.example.mainlanguagesupport.R
import com.example.mainlanguagesupport.databinding.FragmentAddBinding

class AddFragment : Fragment(), OnItemSelectedListener {

    //creating a private writable property called listBinding
    private var addBinding: FragmentAddBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = addBinding!!

    private lateinit var myUserViewModel : UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?) : View?{


        //creates an instance of the binding class for the fragment to use.
        addBinding = FragmentAddBinding.inflate(inflater, container, false)
        val view = binding.root

        // ViewModelProvider is a utility class that provides ViewModel instances.
        myUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        // Finding id of Confirm Button
        binding.confrimUpdate.setOnClickListener{
            insertDatatoDatabase()
        }

        //Accessing String items for Spinner & Creating too
        val typeFood = resources.getStringArray(R.array.type)
        val adapter = ArrayAdapter(
            requireActivity(),
            android.R.layout.simple_spinner_item,
            typeFood)
        binding.typeName.adapter = adapter

        return view

    }

    private fun insertDatatoDatabase() {
        val foodName = binding.foodName.text.toString()
        val typeName = binding.typeName.selectedItem.toString()
        val calories = binding.calories.text

        if (checkNull(foodName, typeName, calories)){
            // If true and isn't empty, then execute this

            // Create User Object
            val user = DataInputUser(foodName, typeName, calories.toString().toInt(), 0)
            //Adding data to Database
            myUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "You have added to the list", Toast.LENGTH_SHORT).show()

            // Return to List Fragment
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }
        else{
            //If is empty, then
            Toast.makeText(requireContext(), "Please fill all part", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, long: Long) {
        var item = parent?.getItemAtPosition(position).toString()
        Toast.makeText(requireContext(), "You just choose $item", Toast.LENGTH_SHORT).show()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    // Check
    fun checkNull(name:String, type: String, calories:Editable) : Boolean{
        //return true if it is NOT EMPTY
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(type) && calories.isEmpty())
    }
    override fun onDestroyView() {
        super.onDestroyView()
        addBinding = null
    }
}

