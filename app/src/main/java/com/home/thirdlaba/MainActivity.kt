package com.home.thirdlaba

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.home.thirdlaba.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private val adapter=UsersAdapter()
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofitService=RetrofitService.getInstance()
        val mainRepository=MainRepository(retrofitService)


        viewModel = ViewModelProvider(this, MyViewModelFactory(mainRepository)).get(MainViewModel::class.java)

        viewModel.usersList.observe(this, {
            adapter.setUsers(it)
        })

        viewModel.errorMessage.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })


        viewModel.loading.observe(this, Observer {
            if (it) {
                binding.progressDialog.visibility = View.VISIBLE
            } else {
                binding.progressDialog.visibility = View.GONE
            }
        })
        viewModel.getAllUsers()
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        binding.recyclerview.adapter=adapter
        return binding.root
    }
}