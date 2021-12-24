package com.example.cupcake

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.cupcake.databinding.FragmentFlavorBinding
import com.example.cupcake.model.OrderViewModel

class FlavorFragment : Fragment() {
    private var binding: FragmentFlavorBinding? = null
    private val sharedViewModel: OrderViewModel by activityViewModels()
    // this will be used as the view model for the
    //activity in which the fragment is placed

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentFlavorBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner //todo - important
            //extremely important for the fragment view model to be lifecycle aware.
            viewModel = sharedViewModel //todo - important
            //assigning the activity's shared view model as the fragments view model.
            //nextButton.setOnClickListener { goToNextScreen() }
            flavorFragment = this@FlavorFragment //todo - important
            //'this' used as is refers to the binding instance.
            //so, use @ and clarify the fragment class name
        }
    }

    fun setFlavor(flavorGiven: String) {

        if (flavorGiven == getString(R.string.special_flavor)) {
            sharedViewModel.setFlavor(flavorGiven, true)
            binding!!.warningTextView.text = getString(R.string.warning_text)
        } else {
            sharedViewModel.setFlavor(flavorGiven, false)
            binding!!.warningTextView.text = ""
        }
    }

    fun goToNextScreen() {
        findNavController().navigate(R.id.action_flavorFragment_to_pickupFragment)
    }

    fun cancelOrder() {
        sharedViewModel.resetOrder()
        findNavController().navigate(R.id.action_flavorFragment_to_startFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}

