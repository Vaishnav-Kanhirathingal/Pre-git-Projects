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
import com.example.lunchtray.databinding.FragmentEntreeMenuBinding
import com.example.lunchtray.model.OrderViewModel

/**
 * [EntreeMenuFragment] allows people to add an entree to the order or cancel the order.
 */
class EntreeMenuFragment : Fragment() {
    private val TAG = "EntreeMenu_VKP"

    private var _binding: FragmentEntreeMenuBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEntreeMenuBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            entreeMenuFragment = this@EntreeMenuFragment
        }
        Log.d(TAG, "onViewCreated - model.entree.isNull = ${sharedViewModel.entree.value == null}")
    }

    fun goToNextScreen() {
        findNavController().navigate(R.id.action_entreeMenuFragment_to_sideMenuFragment)
    }

    fun cancelOrder() {
        sharedViewModel.resetOrder()
        findNavController().navigate(R.id.action_entreeMenuFragment_to_startOrderFragment)
    }

    fun setOption(option: Int) {
        Log.d(TAG, "option number selected = ${option}")
        when (option) {
            1 -> sharedViewModel.setEntree(getString(R.string.cauliflower))
            2 -> sharedViewModel.setEntree(getString(R.string.chili))
            3 -> sharedViewModel.setEntree(getString(R.string.pasta))
            else -> sharedViewModel.setEntree(getString(R.string.skillet))
        }
    }

    fun optionChecked(choice: Int): Boolean {
        Log.d(TAG, "set checked for option ${choice}")
        if (sharedViewModel.entree.value != null) {
            return when (choice) {
                1 -> (sharedViewModel.entree.value!!.name == DataSource.menuItems[getString(R.string.cauliflower)]?.name)
                2 -> (sharedViewModel.entree.value!!.name == DataSource.menuItems[getString(R.string.chili)]?.name)
                3 -> (sharedViewModel.entree.value!!.name == DataSource.menuItems[getString(R.string.pasta)]?.name)
                4 -> (sharedViewModel.entree.value!!.name == DataSource.menuItems[getString(R.string.skillet)]?.name)
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
