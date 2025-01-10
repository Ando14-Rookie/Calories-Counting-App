package com.example.firstnutritionproject.FragmentAndActivity.List

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstnutritionproject.Data.ViewModel.UserViewModel
import com.example.firstnutritionproject.FragmentAndActivity.Update.UpdateFragmentArgs
import com.example.firstnutritionproject.RecyclerView.adapter
import com.example.mainlanguagesupport.R
import com.example.mainlanguagesupport.databinding.FragmentListBinding

class ListFragment : Fragment() {

    //creating a private writable property called listBinding
    private var listBinding: FragmentListBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = listBinding!!
    private lateinit var myUserViewModel : UserViewModel

    // to get the Argument from SafeArg
    private val args by navArgs<UpdateFragmentArgs>()

    // Will be used to get the calories
    // Array list is used so we can sum it
    //private lateinit var amountFoodData : ArrayList<DataInputUser>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //creates an instance of the binding class for the fragment to use.
        listBinding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root

        //Making the Recycler View
        val myAdapter = adapter()
        val recyclerView = binding.recyclerView
        recyclerView.adapter = myAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Showing the number
        //updateNumber()

        // ViewModelProvider is a utility class that provides ViewModel instances.
        myUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        // To observe Live Data and Changes from DAtaBase
        // Passing data to Adapter
        myUserViewModel.readAll.observe(viewLifecycleOwner, Observer { DataInputUser->
            myAdapter.updateRecyclerView(DataInputUser)
        })

        // Track the number calories for carbs by LiveData
        myUserViewModel.totalCarbs.observe(viewLifecycleOwner,Observer{sum ->
            // To prevent crashing the program due to amount being null
            if (sum != null){
                binding.numberCarbs.text = sum.toString()
            }
            else{
                binding.numberCarbs.text = "0"
            }
        })

        // Track the number calories for carbs by LiveData
        myUserViewModel.totalProtein.observe(viewLifecycleOwner,Observer{sum1 ->
            // To prevent crashing the program due to amount being null
            if (sum1 != null){
                binding.numberProtein.text = sum1.toString()
            }
            else{
                binding.numberProtein.text = "0"
            }
        })

        // Change the number calories for carbs by LiveData
        myUserViewModel.totalFat.observe(viewLifecycleOwner,Observer{sum2 ->
            // To prevent crashing the program due to amount being null
            if (sum2 != null){
                binding.numberFats.text = sum2.toString()
            }
            else{
                binding.numberFats.text = "0"
            }
        })

        // Basically FindViewById
        binding.actionButton.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        // Return the view that will pop up
        return view
    }

    override fun onDestroy(){
        // To destory binding and prevent memory leak
        listBinding = null
        super.onDestroy()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        listBinding = null
    }
}
