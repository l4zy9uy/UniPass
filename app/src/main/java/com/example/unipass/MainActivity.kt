package com.example.unipass

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.ComponentActivity
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.unipass.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    //private lateinit var logout: Button
    private lateinit var fragmentManager: FragmentManager
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        var toogle = ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar, R.string.nav_open, R.string.nav_close)
        binding.drawerLayout.addDrawerListener(toogle)
        toogle.syncState()

        binding.bottomNavigation.background = null
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.bottom_home -> openFrament(HomeFragment())
                R.id.bottom_profile -> openFrament(ProfileFragment())
                R.id.bottom_generator -> openFrament(PasswordGeneratorFragment())
                R.id.bottom_settings -> openFrament(SettingsFragment())
            }
            true
        }
        fragmentManager = supportFragmentManager
        openFrament(HomeFragment())

        binding.fab.setOnClickListener {
            Toast.makeText(this, "Add Vault", Toast.LENGTH_SHORT).show()
        }
        /*database = Firebase.database.reference
        //logout = findViewById(R.id.log_out)
        fragment1 = findViewById(R.id.fragment1)
        fragment2 = findViewById(R.id.fragment2)

        val homeFragment = HomeFragment()
        val passwordGeneratorFragment = PasswordGeneratorFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, homeFragment)
            commit()
        }

        fragment1.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, homeFragment)
                addToBackStack(null)
                commit()
            }
        }

        fragment2.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, passwordGeneratorFragment)
                addToBackStack(null)
                commit()
            }
        }*/
        /*logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            Toast.makeText(this, "Logged out!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, StartActivity::class.java)
            startActivity(intent)
            finish()
        }*/

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {

        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        if(binding.drawerLayout.isDrawerOpen(GravityCompat.START))
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        else{
            super.onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun openFrament(fragment: Fragment) {
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }
}