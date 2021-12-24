/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
    val TAG = "Summary Fragment CustomTAG"
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
        }
    }

    fun cancelOrder() {
        sharedViewModel.resetOrder()
        findNavController().navigate(R.id.action_summaryFragment_to_startFragment)
    }

    fun sendOrder() {
        val noOfCupcakes:Int = sharedViewModel.quantity.value?:0
        Log.d(TAG," value for string = ${noOfCupcakes}")

        val orderSummary = getString(
            R.string.order_details,
            resources.getQuantityString(R.plurals.cupcakes,noOfCupcakes,noOfCupcakes),
            //noOfCupcakes will be used as a vararg and applied to the string
            //only if the string has a '%d'
            sharedViewModel.flavor.value.toString(),
            sharedViewModel.date.value.toString(),
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