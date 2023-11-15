package com.example.unipass

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.SeekBar
import com.google.android.material.textfield.TextInputLayout

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PasswordGeneratorFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PasswordGeneratorFragment : Fragment() {
    private lateinit var generatedPasswordLayout: TextInputLayout
    private lateinit var lengthBar: SeekBar
    private lateinit var uppercaseCheckBox: CheckBox
    private lateinit var lowercaseCheckBox: CheckBox
    private lateinit var numbersCheckBox: CheckBox
    private lateinit var symbolsCheckBox: CheckBox
    private lateinit var generateButton: Button
    private val defaultPasswordLength: Int = 8
    private var passwordLength = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_password_generator, container, false)
        var uppercase = true
        var lowercase = true
        var symbols = true
        var numbers = true
        // Initialize UI elements
        generatedPasswordLayout = view.findViewById(R.id.generatedPasswordLayout)
        lengthBar = view.findViewById(R.id.lengthBar)
        uppercaseCheckBox = view.findViewById(R.id.uppercaseCheckBox)
        lowercaseCheckBox = view.findViewById(R.id.lowercaseCheckBox)
        numbersCheckBox = view.findViewById(R.id.numbersCheckBox)
        symbolsCheckBox = view.findViewById(R.id.symbolsCheckBox)
        generateButton = view.findViewById(R.id.generateButton)

        lengthBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                passwordLength = defaultPasswordLength + progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                TODO("Not yet implemented")
            }
        })

        // Set up listener for CheckBox clicks
        uppercaseCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (!isChecked) {
                uppercase = false
            }
        }

        lowercaseCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (!isChecked) {
                lowercase = false
            }
        }

        symbolsCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                symbols = false
            }
        }

        numbersCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                numbers = false
            }
        }

        val passwordGenerator = PasswordGenerator(passwordLength, uppercase, lowercase, symbols, numbers)

        generateButton.setOnClickListener {
            generatedPasswordLayout.editText?.setText(passwordGenerator.generatePassword())
        }
        return view
    }


}