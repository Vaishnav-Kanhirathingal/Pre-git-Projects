package com.example.cupcake

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.cupcake.databinding.FragmentSummaryBinding
import com.example.cupcake.model.OrderViewModel

class SummaryFragment : Fragment() {
    private var binding: FragmentSummaryBinding? = null
    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentSummaryBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            //here lifecycle owner isn't necessary because no changes
            //are being made during this fragment's lifecycle
            viewModel = sharedViewModel //todo - important
            //assigning the activity's shared view model as the fragments view model
            summaryFragment = this@SummaryFragment //todo - important
            //'this' used as is refers to the binding instance.
            //so, use @ and clarify the fragment class name
            binding!!.apply {
                if (sharedViewModel.isUserNameANumber) {
                    yourNameLabel.text = resources.getString(R.string.user_code)
                    referralInfo.text = resources.getString(R.string.generator_message)
                    warningImage.setImageResource(R.drawable.ic_outline_warning)
                } else {
                    yourNameLabel.text = resources.getString(R.string.user_name)
                    referralInfo.text = ""
                    warningImage.setImageResource(android.R.color.transparent)
                }
            }
        }
    }

    fun cancelOrder() {
        sharedViewModel.resetOrder()
        findNavController().navigate(R.id.action_summaryFragment_to_startFragment)
    }

    fun sendOrder() {
        val noOfCupcakes:Int = sharedViewModel.quantity.value?:0
        val userNameToSend =
        if (sharedViewModel.isUserNameANumber){ "referral code: ${sharedViewModel.userName}" }
        else{ "referral name: ${sharedViewModel.userName}" }
        val orderSummary = getString(
            R.string.order_details,
            resources.getQuantityString(R.plurals.cupcakes,noOfCupcakes,noOfCupcakes),
            //noOfCupcakes will be used as a vararg and applied to the string
            //only if the string has a '%d'
            sharedViewModel.flavor.value.toString(),
            sharedViewModel.date.value.toString(),
            userNameToSend,
            sharedViewModel.price.value.toString()
        )
        Toast.makeText(activity, orderSummary, Toast.LENGTH_SHORT).show()
        val intent:Intent = Intent(Intent.ACTION_SEND).setType("text/plain")
            .putExtra(Intent.EXTRA_SUBJECT,getString(R.string.new_cupcake_order))
            .putExtra(Intent.EXTRA_TEXT,orderSummary)
        if (activity?.packageManager?.resolveActivity(intent,0)!=null){
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}