package com.example.mymovie

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var edTittle: EditText
    private lateinit var tittle: String
    private lateinit var edDirector: EditText
    private lateinit var director: String
    private lateinit var edProduction: EditText
    private lateinit var production: String

    private lateinit var radioGroup: RadioGroup
    private lateinit var radioUmur1: RadioButton
    private lateinit var radioUmur2: RadioButton
    private lateinit var radioUmur3: RadioButton
    private  var umur: String = ""

    private lateinit var thriler: CheckBox
    private lateinit var comedy: CheckBox
    private lateinit var horror: CheckBox
    private lateinit var romance: CheckBox
    private lateinit var action: CheckBox
    private lateinit var fantasy: CheckBox
    private var genre: String = ""

    private lateinit var country: String

    var edDate: EditText? = null
    var cal = Calendar.getInstance()
    private lateinit var tanggal: String

    private lateinit var submit: Button

    private lateinit var review: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edTittle = findViewById(R.id.tittle)
        edDirector = findViewById(R.id.director)
        edProduction = findViewById(R.id.production)

        radioGroup = findViewById(R.id.radioGroup)
        radioUmur1 = findViewById(R.id.radio1)
        radioUmur2 = findViewById(R.id.radio2)
        radioUmur3 = findViewById(R.id.radio3)

        thriler = findViewById(R.id.thriler)
        comedy = findViewById(R.id.comedy)
        horror = findViewById(R.id.horror)
        romance = findViewById(R.id.romance)
        action = findViewById(R.id.action)
        fantasy = findViewById(R.id.fantasy)

        radioGroup.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener{
            override fun onCheckedChanged(view: RadioGroup?, index: Int) {

                if (index== R.id.radio1){
                    umur = "Under 13th"
                }else if (index== R.id.radio2){
                    umur="13th until 18th"
                }else if (index== R.id.radio3){
                    umur="18th and above"
                }
            }
        })

        val myCountry = arrayOf("Indonesia","Thailand","Korea","Hollywood","Bollywood")
        negara.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,myCountry)
        negara.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?){

            }

            override fun onItemSelected(
                p0: AdapterView<*>?,
                p1: View?,
                p2: Int,
                p3: Long
            ) {
                country = myCountry[p2]
            }
        }

        edDate = this.editDate

        val dateSetListener = object : DatePickerDialog.OnDateSetListener{
            override fun onDateSet(view: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
                cal.set(Calendar.YEAR,year)
                cal.set(Calendar.MONTH,monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH,dayOfMonth)
                updateDateInView()

            }
        }
        edDate!!.setOnClickListener(object : View.OnClickListener{
            @RequiresApi(Build.VERSION_CODES.N)
            override fun onClick(view: View) {
                DatePickerDialog(this@MainActivity,dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
            }
        })

        submit = findViewById(R.id.proses)

        submit.setOnClickListener{

            genre = ""

            if (thriler.isChecked){
                genre += "Thriler"
            }
            if (comedy.isChecked){
                genre += ", Comedy"
            }
            if (horror.isChecked){
                genre += ", Horror"
            }
            if (romance.isChecked){
                genre += ", Romance"
            }
            if (action.isChecked){
                genre += ", Action"
            }
            if (fantasy.isChecked){
                genre += ", Fantasy"
            }


            tanggal = edDate!!.text.toString()

//            review ="Tittle : "+edTittle.text.toString()+
//                    "\nDirected by : "+edDirector.text.toString()+
//                    "\nHome Production : "+edProduction.text.toString()+
//                    "\nGenre : "+genre+
//                    "\nAge Restriction : "+ umur+
//                    "\nCountry : "+country+
//                    "\nDate Release : "+tanggal
//
//            Toast.makeText(this, review, Toast.LENGTH_SHORT).show()


            tittle = edTittle.getText().toString()
            director = edDirector.getText().toString()
            production = edProduction.getText().toString()

            val intent = Intent(this, ActivityBaru::class.java)

            intent.putExtra("tittle_key", tittle)
            intent.putExtra("director_key",director)
            intent.putExtra("production_key",production)
            intent.putExtra("umur_key",umur)
            intent.putExtra("genre_key",genre)
            intent.putExtra("country_key",country)
            intent.putExtra("tanggal_key",tanggal)

            startActivity(intent)

           }

    }

    private fun updateDateInView() {
        val myFormat = "MM/dd/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)

        edDate!!.setText(sdf.format(cal.getTime()))
    }
}
