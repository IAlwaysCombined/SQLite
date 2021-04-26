package com.example.sqlite

import android.database.Cursor
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.sqlite.database.DatabaseHelper
import com.example.sqlite.databinding.ActivityMainBinding
import com.example.sqlite.utilite.APP_ACTIVITY
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var peopleBD: DatabaseHelper // Init class for working with database
    private lateinit var binding: ActivityMainBinding // Init view binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        APP_ACTIVITY = this
        peopleBD = DatabaseHelper(this)
        pressButton()
    }

    //Check button click
    private fun pressButton(){
        binding.btnAdd.setOnClickListener {
            addUser()
        }
        binding.btnShow.setOnClickListener {
            showUsers()
        }
        binding.btnReAdd.setOnClickListener {
            reAddUser()
        }
        binding.btnDelete.setOnClickListener {
            deleteUser()
        }
    }

    //Create new entry in database
    private fun addUser(){
        val name: String = edtName.text.toString()
        val email: String = edtEmail.text.toString()
        val insertData : Boolean = peopleBD.addData(name, email)
        if(insertData){
            showToast("Entry added")
        }else{
            showToast("Something went wrong")
        }
    }

    //Output all entry of database in AlertDialog
    private fun showUsers(){
        val data: Cursor? = peopleBD.showData()
        if (data!!.count == 0){
            display("Error", "Data null")
        }else {
            val buffer = StringBuffer()
            while (data.moveToNext()) {
                buffer.append(
                    "ID: " + data.getString(0) + "\n"
                )
                buffer.append(
                    "Name: " + data.getString(1) + "\n"
                )
                buffer.append(
                    "Email: " + data.getString(2) + "\n"
                )
            }
            display("All users", buffer.toString())
        }
    }
    //AlertDialog
    private fun display(title: String, message: String){
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setCancelable(true)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.show()
    }

    //Change entry
    private fun reAddUser(){
        val id = edtReID.text.toString()
        val name = edtReName.text.toString()
        val email = edtReEmail.text.toString()
        peopleBD.reAddData(id, name, email)
        showToast("Data changed")
    }

    //Delete entry
    private fun deleteUser(){
        val id = edtDelete.text.toString()
        peopleBD.deleteData(id)
        showToast("Data deleted")
    }
}