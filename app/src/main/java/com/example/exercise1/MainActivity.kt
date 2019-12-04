package com.example.exercise1


import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        findViewById<Button>(R.id.buttonCalculate).setOnClickListener { calculatePrice(it) }
        findViewById<Button>(R.id.buttonReset).setOnClickListener { resetAll(it) }


    }

    private fun resetAll(view:View){

        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);

        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    @SuppressLint("SetTextI18n")
    private fun calculatePrice(view:View) {


        val alertBox = AlertDialog.Builder(this@MainActivity)

        alertBox.setTitle("Error")
        alertBox.setMessage("Please fill in the empty space")
        alertBox.setIcon(R.mipmap.ic_launcher)

        alertBox.setNegativeButton("CLOSE") { dialog, which ->
            dialog.dismiss()
        }


        if (editTextCarPrice.text.length == 0 || editTextDownPayment.text.length == 0 || editTextLoanPeriod.text.length == 0 || editTextInterestRate.text.length == 0) {

            alertBox.show()
        } else {
            //input from users
            val carPrice = findViewById<EditText>(R.id.editTextCarPrice)
            val downPayment = findViewById<EditText>(R.id.editTextDownPayment)
            val loanPeriod = findViewById<EditText>(R.id.editTextLoanPeriod)
            val interestRate = findViewById<EditText>(R.id.editTextInterestRate)


            //Algorithm

            val carLoan = carPrice.text.toString().toInt() - downPayment.text.toString().toInt()
            val interest =
                carLoan * (interestRate.text.toString().toDouble() / 100) * loanPeriod.text.toString().toInt()
            val monthlyPay = (carLoan + interest) / loanPeriod.text.toString().toInt() / 12


            //result Text
            val resultText1 = findViewById<TextView>(R.id.textViewLoan)
            val resultText2 = findViewById<TextView>(R.id.textViewInterest)
            val resultText3 = findViewById<TextView>(R.id.textViewMonthlyRepayment)

            resultText1.text = "Loan : " + carLoan.toString()
            resultText2.text = "Interest : " + interest
            resultText3.text = "Monthly Repayment : " + monthlyPay

            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }


}
