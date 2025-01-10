package com.example.firstnutritionproject.FragmentAndActivity.Update

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.firstnutritionproject.Data.DataUserTable.DataInputUser
import com.example.firstnutritionproject.Data.ViewModel.UserViewModel
import com.example.mainlanguagesupport.R
import com.example.mainlanguagesupport.databinding.FragmentUpdateBinding

class UpdateFragment : Fragment() {

    //creating a private writable property called listBinding
    private var updateBinding: FragmentUpdateBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = updateBinding!!

    private lateinit var myUserViewModel : UserViewModel

    // to get the Argument from SafeArgs
    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View? {

        //creates an instance of the binding class for the fragment to use.
        updateBinding = FragmentUpdateBinding.inflate(inflater, container, false)
        val view = binding.root

        // ViewModelProvider is a utility class that provides ViewModel instances.
        myUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        //Defining the ActionBar

        //Accessing String items for Spinner & Creating too
        val typeFood = resources.getStringArray(R.array.type)
        val adapter = ArrayAdapter(
            requireActivity(),
            android.R.layout.simple_spinner_item,
            typeFood)
        binding.typeUpdate.adapter = adapter

        binding.foodUpdate.setText(args.argument.nameFood)
        //to set our spinner item selected.
        val a = args.argument.typeFood
        //getting the position of the item by the item name in our adapter.
        val spinnerPosition: Int = adapter.getPosition(a)
        //setting selection for our spinner to spinner position.
        binding.typeUpdate.setSelection(spinnerPosition)
        binding.caloriesUpdate.setText(args.argument.amountFood.toString())

        binding.confrimUpdate.setOnClickListener{
            updateData()
        }

        binding.deleteButton.setOnClickListener{
            deleteData()
        }

        return view


    }

    override fun onCreateOptionsMenu(menus: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar, menus)
        super.onCreateOptionsMenu(menus, inflater)
    }


    private fun deleteData() {
        val idFood = args.argument.id

        val alertBuilder = AlertDialog.Builder(requireContext())
        alertBuilder.setTitle("Delete ${args.argument.nameFood}")
        alertBuilder.setMessage("Are you sre you want to delete?")
        alertBuilder.setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, which ->
        myUserViewModel.deleteUser(idFood)
        //Return to List Fragment
        findNavController().navigate(R.id.action_updateFragment_to_listFragment)})
        alertBuilder.setNegativeButton("No",DialogInterface.OnClickListener { dialogInterface, which ->})
        alertBuilder.create().show()

    }

    private fun updateData(){
        val updateFood = binding.foodUpdate.text.toString()
        val updateType = binding.typeUpdate.selectedItem.toString()
        val updateCalories = binding.caloriesUpdate.text

        if (checkNull(updateFood, updateType, updateCalories)){
            val currentId = args.argument.id
            val data = DataInputUser(updateFood, updateType, updateCalories.toString().toInt(), currentId)
            myUserViewModel.updateUser(data)
            Toast.makeText(requireContext(), "You have updated to the list", Toast.LENGTH_SHORT).show()

            // Return to List Fragment
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        else {
            Toast.makeText(requireContext(), "Please fill in the update", Toast.LENGTH_SHORT).show()

        }


    }
    private fun checkNull(name:String, type: String, calories: Editable) : Boolean{
        //return true if it is NOT EMPTY
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(type) && calories.isEmpty())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        updateBinding = null
    }

}