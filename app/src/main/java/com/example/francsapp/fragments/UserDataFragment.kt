package com.example.francsapp.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.francsapp.LoginActivity
import com.example.francsapp.R

class UserDataFragment : Fragment() {
    lateinit var logoutButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var v = inflater.inflate(R.layout.fragment_user_data, container, false)

        logoutButton = v.findViewById(R.id.logoutButton)
        logoutButton.setOnClickListener{
            val intent = Intent(getActivity(), LoginActivity::class.java)
            startActivity(intent)
        }

        // Inflate the layout for this fragment
        return v
    }
}
