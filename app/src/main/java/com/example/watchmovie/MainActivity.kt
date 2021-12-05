package com.example.watchmovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.watchmovie.Services.JsonTask
import com.squareup.picasso.Picasso
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    lateinit var button : Button
    lateinit var output : TextView
    lateinit var image : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        output = findViewById(R.id.output)
        button = findViewById(R.id.button)
        image = findViewById(R.id.image)

        button.setOnClickListener {
            val url = "http://192.168.0.65:8000/"
            //getJson(url)
            postJson((url))
        }

    }

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

}