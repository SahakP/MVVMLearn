package com.example.mvvmlearn.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvvmlearn.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

   private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}


/*

"https://restcountries.com/v2/"


 * 1. create empty activity
 * 2. create packages
 * 3. multidex
 * 4. Create two fragments with basic views
 * 5. Install hilt
 * 5. install view binding (gradle, fragment, activity)
 * 6. Navigation (activity loads firs view, on click opens second one)
 * 7. Install retrofit (for network calls)

 * 8. Create models (using postman to understand models structure)

 * 9. Create repositories

 * 10. Create viewModel

 * 11. RecyclerView (fragment 1)

 * 12. Compile all together (click in country opens fragment 2 with country details)

 * 13. Check internet connection before actions
 * 14. Create room DB with local countries info
 * 15. If there is no internet, just show local countries
 * 16. Else, call network, and UPDATE your local
 */