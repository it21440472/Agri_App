package com.example.agro.activities

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.example.agro.R
import com.example.agro.models.cropModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CropInsertionActivity : AppCompatActivity() {

    private lateinit var addcropname: EditText
    private lateinit var addcroptype: EditText
    private lateinit var addcropsize: EditText
    private lateinit var addcropwork: EditText
    private lateinit var addcropExp: EditText
    private lateinit var btnSaveData: Button

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertion)

        addcropname = findViewById(R.id.addcropname)
        addcroptype = findViewById(R.id.addcroptype)
        addcropsize = findViewById(R.id.addcropsize)
        addcropwork = findViewById(R.id.addcropwork)
        addcropExp = findViewById(R.id.addcropExp)
        btnSaveData = findViewById(R.id.btnSave)

        dbRef = FirebaseDatabase.getInstance().getReference("Crops")

        btnSaveData.setOnClickListener {
            savecropData()




        }

        val calbutton = findViewById<Button>(R.id.calbutton);

        // Set the onClickListener for the "Start" button
        calbutton.setOnClickListener(){
            // Create an intent to start the Dashboard activity
            val intenta = Intent(this, CalActivity::class.java)
            // Start the Dashboard activity and finish the MainActivity
            startActivity(intenta)
            finish()
        }




    }

    private fun savecropData() {

        //getting values
        val cname = addcropname.text.toString()
        val ctype = addcroptype.text.toString()
        val csize = addcropsize.text.toString()
        val cwork = addcropwork.text.toString()
        val cexp = addcropExp.text.toString()



        if (cname.isEmpty() || ctype.isEmpty() || csize.isEmpty() || cwork.isEmpty() || cexp.isEmpty()) {
            Toast.makeText(this, "Fill", Toast.LENGTH_LONG).show()



            return
        }

        val cropID = dbRef.push().key!!

        val crop = cropModel(cropID, cname, ctype, csize,cwork,cexp)

        dbRef.child(cropID).setValue(crop)
            .addOnCompleteListener {
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()



            }.addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }



    }
}