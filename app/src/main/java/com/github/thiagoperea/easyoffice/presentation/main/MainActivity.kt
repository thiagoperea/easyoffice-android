package com.github.thiagoperea.easyoffice.presentation.main

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.github.thiagoperea.easyoffice.databinding.ActivityMainBinding
import com.github.thiagoperea.easyoffice.databinding.ChipSelectableBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    // MOCK
    private val mockFloors = (1..10).map { it.toString() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupListeners()

        setMocks()
    }

    private fun setMocks() {
        // FLOORS
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mockFloors)
        binding.inputFlootText.setAdapter(adapter)

        // CHIPS
        createFloorMocks()
    }

    private fun setupViewModel() {
        //TODO: setup observers
    }

    private fun setupListeners() {
        binding.inputFlootText.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                createFloorMocks()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // nothing to do
            }
        }

        binding.confirmButton.setOnClickListener {
            val checkedChips = binding.chipGroupChairs.checkedChipIds

            Toast.makeText(this, "Cadeiras selecionadas: $checkedChips", Toast.LENGTH_SHORT).show()
        }
    }

    private fun createFloorMocks() {
        val letters = listOf("A", "B", "C", "D")
        val numbers = 1..Random.nextInt(10, 20)

        val selectedLetter = letters.random()
        numbers.forEach {
            val seat = "$selectedLetter$it"

            val seatChipBinding = ChipSelectableBinding.inflate(layoutInflater, binding.chipGroupChairs, false)
            seatChipBinding.root.text = seat

            binding.chipGroupChairs.addView(seatChipBinding.root)
        }
    }
}