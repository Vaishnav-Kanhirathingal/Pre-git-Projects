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
import android.util.DisplayMetrics
import android.util.Log
import android.view.*
import androidx.constraintlayout.solver.LinearSystem.getMetrics
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.lunchtray.MainActivity
import com.example.lunchtray.R
import com.example.lunchtray.databinding.FragmentStartOrderBinding
import com.example.lunchtray.model.OrderViewModel

class StartOrderFragment : Fragment() {
    private val TAG = "StartOrder_VKP"
    private var _binding: FragmentStartOrderBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartOrderBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.startOrderBtn.setOnClickListener {
            findNavController().navigate(R.id.action_startOrderFragment_to_entreeMenuFragment)
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.d(TAG, "fragment destroyed")
    }
}
