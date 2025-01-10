package com.example.firstnutritionproject.RecyclerView

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Path.Direction
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
//import com.example.firstnutritionproject.databinding.CustomRecyclerBinding
import com.example.firstnutritionproject.Data.DataUserTable.DataInputUser
import com.example.firstnutritionproject.Data.ViewModel.UserViewModel
import com.example.firstnutritionproject.FragmentAndActivity.List.ListFragment
import com.example.firstnutritionproject.FragmentAndActivity.List.ListFragmentDirections
import com.example.firstnutritionproject.FragmentAndActivity.Main.MainActivity
import com.example.firstnutritionproject.FragmentAndActivity.Update.UpdateFragmentArgs
import com.example.mainlanguagesupport.databinding.CustomRecyclerBinding
import com.example.nutritiontracker.Data.DataInputUserDAO
import com.example.nutritiontracker.Data.DataInputUserDatabase

// https://stackoverflow.com/questions/60423596/how-to-use-viewbinding-in-a-recyclerview-adapter


class adapter : RecyclerView.Adapter<adapter.MyViewHolder>() {

    //returns an empty read-only list
    private var list = emptyList<DataInputUser>()
    // to get the DAO
    private lateinit var myUserViewModel : UserViewModel

    class MyViewHolder(itemBinding : CustomRecyclerBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        // // Holds the views/id for adding it to the text
        val name = itemBinding.textName
        val type   = itemBinding.typeName
        val calories   =itemBinding.calories
        val id =itemBinding.idText
        var layout = itemBinding.backgroundRecyclerView

    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val viewBinding = CustomRecyclerBinding.inflate(LayoutInflater.
        from(parent.context), parent, false)

        return MyViewHolder(viewBinding)
    }

    //binds the list items to a view
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = list[position]
        val context = holder.itemView.context

        holder.name.text = currentItem.nameFood
        holder.type.text = currentItem.typeFood
        holder.calories.text = currentItem.amountFood.toString()
        holder.id.text = currentItem.id.toString()

            holder.layout.setOnClickListener{
                // to send the current object from the list to UpdateFragment
                val pass = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
                holder.itemView.findNavController().navigate(pass)
                Toast.makeText(context, "You just click ${currentItem.nameFood}", Toast.LENGTH_SHORT).show()
            }

        }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return list.size}

    fun updateRecyclerView(userInput:List<DataInputUser>){
        this.list = userInput
        //Notify any registered observers that the data set has changed.
        notifyDataSetChanged()}

    }











