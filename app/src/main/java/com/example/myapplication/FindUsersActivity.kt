package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class FindUsersActivity : AppCompatActivity() {
    val constants = Constants()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.find_users)
        val findUsersButton: Button = findViewById(R.id.findButton)
        val foundUsersListView: ListView = findViewById(R.id.foundUsersListVIew)
        var database: DatabaseReference = Firebase.database.getReference(constants.USERSTAG)
        val listData = ArrayList<String>()
        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listData)
        foundUsersListView.adapter = adapter

        findUsersButton.setOnClickListener {
            database.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (listData.isNotEmpty()) listData.clear()
                    for (dataSnapshot in snapshot.children) {
                        var currentUserLogin = dataSnapshot.key.toString()
                        Log.i(constants.FIREBASETAG, currentUserLogin)
                        if (getSharedPreferences("USERDATA", Context.MODE_PRIVATE).getString(
                                constants.USERLOGIN,
                                null
                            ) != currentUserLogin
                        ) {
                            listData.add(currentUserLogin)
                        }
                    }
                    adapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.i(constants.FIREBASETAG, "loadPost:onCancelled")
                }
            })
        }
    }
}