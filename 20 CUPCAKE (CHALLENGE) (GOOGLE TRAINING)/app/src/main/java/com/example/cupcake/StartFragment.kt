package com.example.cupcake

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.cupcake.databinding.FragmentStartBinding
import com.example.cupcake.model.OrderViewModel

class StartFragment : Fragment() {
    private var binding: FragmentStartBinding? = null
    private val sharedViewModel: OrderViewModel by activityViewModels()
    //shared view model declared here will be used through out the activity.
    //this will be declared the same way under different fragments of the same activity.

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentStartBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.startFragment = this  //todo - important
        //'this' is used to pass the class instance to the xml file
        //so that it can use that instance to access the class functions used in the onclick
        //listeners in the xml file
    }

    fun orderCupcake(quantity: Int) {
        sharedViewModel.setQuantity(quantity)
        if (sharedViewModel.hasNoFlavor()) {
            sharedViewModel.setFlavor(getString(R.string.vanilla),false)
        }
        findNavController().navigate(R.id.action_startFragment_to_flavorFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}

