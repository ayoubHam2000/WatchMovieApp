package com.example.watchmovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.example.watchmovie.Services.JsonTask
import com.squareup.picasso.Picasso
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    lateinit var userNameField : EditText
    lateinit var passwordField : EditText
    lateinit var signInBtn : Button
    lateinit var loginBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

    }

    private fun initView()
    {
        userNameField = this.findViewById(R.id.userName)
        passwordField = this.findViewById(R.id.password)
        signInBtn = this.findViewById(R.id.logIn)
        loginBtn = this.findViewById(R.id.signIn)

        loginBtn.setOnClickListener{logInClick()}
    }

    private fun logInClick()
    {
        //trim is for to remove spaces from start and end ex: "  aasaad " => "aasaad"
        val userName = userNameField.text.trim()
        val password = userNameField.text.trim()
        when {
            userName.isEmpty() -> userNameField.error = "Empty Field"
            password.isEmpty() -> passwordField.error = "Empty Field"
            else -> {
                val obj = JSONObject();
                obj.put("user_name", userName)
                obj.put("password", password)
                postJson("http://10.11.2.10:8080/user", obj)
            }
        }
    }

    private fun postJson(url : String, obj : JSONObject)
    {
        JsonTask.postJsonObject(this, url, obj){success, res->
            if (success){
                println(">>$res")
            }
            else
            {
                println("Error")
            }
        }
    }

/*
    private fun getJson(url : String)
    {

        JsonTask.getJsonObject(this, url){success, res->
            if (success){
                output.text = res.toString()
                val imageurl = res["pathimage"].toString()
                Picasso.get().load(imageurl).into(image)
                println(">> complete")
            }
        }
        println(">>>request is sent")
    }

    private fun postJson(url : String)
    {
        val jsonObject = JSONObject()

        jsonObject.put("name", "Test1")
        jsonObject.put("studio", "Test2")
        JsonTask.postJsonObject(this, url, jsonObject){success, res->
            if (success){
                println(">>$res")
            }
        }
    }
*/
}