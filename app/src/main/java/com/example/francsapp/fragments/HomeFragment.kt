package com.example.francsapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import com.example.francsapp.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    lateinit var v: View
    lateinit var toLoginBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_home, container, false)
        toLoginBtn = v.findViewById(R.id.HomeBtn)
        return v
    }

    override fun onStart() {
        super.onStart()

        toLoginBtn.setOnClickListener {
            Toast.makeText(activity, "hola", Toast.LENGTH_LONG).show()
            NavHostFragment.findNavController(this).navigate(R.id.action_homeFragment2_to_loginFragment2)
        }
    }
}