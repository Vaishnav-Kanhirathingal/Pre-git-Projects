package com.example.cupcake

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.cupcake.databinding.FragmentPickupBinding
import com.example.cupcake.model.OrderViewModel

class PickupFragment : Fragment() {

    private var binding: FragmentPickupBinding? = null
    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentPickupBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner //todo - important
            //extremely important for the fragment view model to be lifecycle aware.
            viewModel = sharedViewModel //todo - important
            //assigning the activity's shared view model as the fragments view model
            //nextButton.setOnClickListener { goToNextScreen() } todo -remove later
            pickUpFragment = this@PickupFragment //todo - important
            //'this' used as is refers to the binding instance.
            //so, use @ and clarify the fragment class name
        }
    }

    fun goToNextScreen() {
        if (binding?.userName?.text.toString().length > 2) {
            sharedViewModel.userName = binding!!.userName.text.toString()
            sharedViewModel.isUserNameANumber = false
        } else {
            Toast.makeText(
                this.requireContext(),
                resources.getString(R.string.toast_too_short),
                Toast.LENGTH_LONG
            ).show()
            sharedViewModel.apply {
                userName = "${(100000..999999).random()}"
                isUserNameANumber = true
            }
        }
        findNavController().navigate(R.id.action_pickupFragment_to_summaryFragment)
    }

    fun cancelOrder() {
        sharedViewModel.resetOrder()
        findNavController().navigate(R.id.action_pickupFragment_to_startFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}

