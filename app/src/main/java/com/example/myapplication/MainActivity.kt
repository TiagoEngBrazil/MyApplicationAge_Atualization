package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    @SuppressLint("SimpleDateFormat")
    private val dateFormat: SimpleDateFormat = SimpleDateFormat("dd/MM")

    @SuppressLint("SimpleDateFormat")
    private val dateFormat2: SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy")

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val calendar = Calendar.getInstance()
        val todayDate = dateFormat.format(calendar.time)
        val todayDate2 = dateFormat2.format(calendar.time)

        binding.textObservation.text = getString(R.string.observation, todayDate2.toString())
        binding.buttonRadio1.text = getString(R.string.before_of, todayDate.toString())
        binding.buttonRadio2.text = getString(R.string.in_, todayDate.toString())
        binding.buttonRadio3.text = getString(R.string.after_, todayDate.toString())

        binding.buttonCalculate.setOnClickListener(this)

        supportActionBar?.hide()
    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_calculate) {
            calculate()
        }
    }

    private fun validation1(): Boolean {
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        return binding.textYearOfBirth.text.toString()
            .toInt() in (getString(R.string.oldest_Year).toInt()..currentYear)
    }

    private fun validation2(): Boolean {
        return binding.buttonRadio1.isChecked || binding.buttonRadio2.isChecked || binding.buttonRadio3.isChecked || binding.buttonRadio4.isChecked
    }

    private fun validation3(): Boolean {
        val yearOfBirth = binding.textYearOfBirth.text.toString().toIntOrNull()
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        return yearOfBirth == currentYear && binding.buttonRadio3.isChecked
    }

    @SuppressLint("ShowToast")
    private fun calculate() {

        val yearOfBirth = binding.textYearOfBirth.text.toString().toIntOrNull()
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        val todayDate = dateFormat.format(calendar.time)
        val todayDate2 = dateFormat2.format(calendar.time)


        if (yearOfBirth != null && validation1() && validation2() && !validation3()) {
            val age1 = currentYear - yearOfBirth
            val age2 = currentYear - yearOfBirth - getString(R.string.number_one).toInt()

            if (currentYear == yearOfBirth) {
                when {
                    binding.buttonRadio1.isChecked -> binding.textAnswer1.text = getString(
                        R.string.type_answer_1,
                        yearOfBirth.toString(),
                        todayDate.toString()
                    )
                    binding.buttonRadio2.isChecked -> binding.textAnswer1.text =
                        getString(
                            R.string.type_answer_1_2,
                            todayDate.toString(),
                            yearOfBirth.toString()
                        )

                    binding.buttonRadio4.isChecked -> binding.textAnswer1.text =
                        getString(
                            R.string.type_answer_1_3,
                            currentYear.toString()
                        )
                }

            } else if (yearOfBirth == currentYear - getString(R.string.number_one).toInt()) {
                if (binding.buttonRadio1.isChecked) {
                    binding.textAnswer1.text =
                        getString(
                            R.string.type_answer_2, yearOfBirth.toString(),
                            todayDate.toString()
                        )
                } else if (binding.buttonRadio2.isChecked) {
                    binding.textAnswer1.text =
                        getString(
                            R.string.type_answer_2_2, todayDate.toString(), yearOfBirth.toString()
                        )
                } else if (binding.buttonRadio3.isChecked) {
                    binding.textAnswer1.text =
                        getString(
                            R.string.type_answer_2_4,
                            yearOfBirth.toString(),
                            todayDate.toString(),
                            todayDate.toString()
                        )
                } else if (binding.buttonRadio4.isChecked) {
                    binding.textAnswer1.text =
                        getString(
                            R.string.type_answer_2_3,
                            yearOfBirth.toString(), todayDate.toString(), todayDate
                        )
                    binding.textAnswer2.text =
                        getString(
                            R.string.type_answer_2_4,
                            yearOfBirth.toString(),
                            todayDate.toString()
                        )
                }
            } else {
                if (binding.buttonRadio1.isChecked) {
                    binding.textAnswer1.text =
                        getString(
                            R.string.type_answer_4,
                            yearOfBirth.toString(),
                            todayDate.toString(), age1.toString()
                        )
                    binding.textAnswer2.text =
                        getString(
                            R.string.empity_string
                        )
                } else if (binding.buttonRadio2.isChecked) {
                    binding.textAnswer1.text =
                        getString(
                            R.string.type_answer_4_2, todayDate.toString(),
                            yearOfBirth.toString(), age1.toString()
                        )
                    binding.textAnswer2.text =
                        getString(
                            R.string.empity_string
                        )
                } else if (binding.buttonRadio3.isChecked) {
                    binding.textAnswer1.text =
                        getString(
                            R.string.type_answer_5_1,
                            yearOfBirth.toString(), todayDate.toString(), age2.toString()
                        )
                    binding.textAnswer2.text =
                        getString(
                            R.string.empity_string
                        )
                } else if (binding.buttonRadio4.isChecked) {
                    binding.textAnswer1.text =
                        getString(
                            R.string.type_answer_5_2,
                            yearOfBirth.toString(),
                            todayDate.toString(),
                            todayDate.toString(),
                            age1.toString()
                        )
                    binding.textAnswer2.text = getString(
                        R.string.type_answer_5_1,
                        yearOfBirth.toString(), todayDate.toString(), age2.toString()
                    )
                }
            }
            val intent = Intent(this, AnswerActivity::class.java)
            intent.putExtra(getString(R.string.recover_text1), binding.textAnswer1.text.toString())
            intent.putExtra(getString(R.string.recover_text2), binding.textAnswer2.text.toString())
            startActivity(intent)

        } else if (yearOfBirth == null) {
            Toast.makeText(this, R.string.exception_year_of_birth, Toast.LENGTH_SHORT)
                .show()
            return
        } else if (!validation1()) {
            Toast.makeText(
                this, getString(R.string.exception_gap_year, currentYear.toString()),
                Toast.LENGTH_LONG
            ).show()
        } else if (!validation2()) {
            Toast.makeText(
                this, R.string.validation_type_radioButton,
                Toast.LENGTH_LONG
            ).show()
        } else if (validation3()) {
            Toast.makeText(
                this,
                getString(
                    R.string.exception_birth_after_today_date,
                    todayDate2.toString()
                ),
                Toast.LENGTH_SHORT
            ).show()
        }

    }

}
