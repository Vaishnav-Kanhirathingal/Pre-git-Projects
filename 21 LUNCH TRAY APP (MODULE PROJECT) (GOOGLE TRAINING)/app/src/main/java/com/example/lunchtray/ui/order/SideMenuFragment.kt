/*
 * Copyright (C) 2021 The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.lunchtray.ui.order

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.lunchtray.R
import com.example.lunchtray.data.DataSource
import com.example.lunchtray.databinding.FragmentSideMenuBinding
import com.example.lunchtray.model.OrderViewModel

/**
 * [SideMenuFragment] allows people to add a side to the order or cancel the order.
 */
class SideMenuFragment : Fragment() {
    private val TAG = "SideMenu_VKP"

    // Binding object instance corresponding to the fragment_start_order.xml layout
    // This property is non-null between the onCreateView() and onDestroyView() lifecycle callbacks,
    // when the view hierarchy is attached to the fragment.
    private var _binding: FragmentSideMenuBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    // Use the 'by activityViewModels()' Kotlin property delegate from the fragment-ktx artifact
    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSideMenuBinding.inflate(inflater, container, false)
        val root = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            // TODO: initialize the SideMenuFragment variables
            sideMenuFragment = this@SideMenuFragment
        }
    }

    fun goToNextScreen() {
        // TODO: Navigate to the AccompanimentMenuFragment
        findNavController().navigate(R.id.action_sideMenuFragment_to_accompanimentMenuFragment)
    }

    fun cancelOrder() {
        sharedViewModel.resetOrder()
        findNavController().navigate(R.id.action_sideMenuFragment_to_startOrderFragment)
    }

    fun setOption(option: Int) {
        Log.d(TAG, "option number selected = ${option}")
        when (option) {
            1 -> sharedViewModel.setSide(getString(R.string.salad))
            2 -> sharedViewModel.setSide(getString(R.string.soup))
            3 -> sharedViewModel.setSide(getString(R.string.potatoes))
            else -> sharedViewModel.setSide(getString(R.string.rice))
        }
    }

    fun optionChecked(choice: Int): Boolean {
        Log.d(TAG, "set checked for option ${choice}")
        if (sharedViewModel.side.value != null) {
            return when (choice) {
                1 -> (sharedViewModel.side.value!!.name == DataSource.menuItems[getString(R.string.salad)]?.name)
                2 -> (sharedViewModel.side.value!!.name == DataSource.menuItems[getString(R.string.soup)]?.name)
                3 -> (sharedViewModel.side.value!!.name == DataSource.menuItems[getString(R.string.potatoes)]?.name)
                4 -> (sharedViewModel.side.value!!.name == DataSource.menuItems[getString(R.string.rice)]?.name)
                else -> false
            }
        } else {
            return false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.d(TAG, "fragment destroyed")
    }
}
