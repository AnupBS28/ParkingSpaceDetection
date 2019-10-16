package com.example.med4u

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.ActivityCompat
import android.widget.Toast

class splashActivity : AppCompatActivity() {

    var permissionsString = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.READ_PHONE_STATE
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        if (!hasPermissions(this@splashActivity, *permissionsString)) {

            ActivityCompat.requestPermissions(this@splashActivity,permissionsString,131)

        } else {
            Handler().postDelayed({
                val startAct = Intent(this@splashActivity, MainActivity::class.java)
                startActivity(startAct)
                this.finish()
            }, 1700)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            131->{
                if(grantResults.isNotEmpty() && grantResults[0]== PackageManager.PERMISSION_GRANTED
                    && grantResults[1]==PackageManager.PERMISSION_GRANTED
                    && grantResults[2] == PackageManager.PERMISSION_GRANTED){
                    Handler().postDelayed({
                        val startAct = Intent(this@splashActivity, MainActivity::class.java)
                        startActivity(startAct)
                        this.finish()
                    }, 1400)
                }
                else{
                    Toast.makeText(this@splashActivity,"Please Grant All Permissions",Toast.LENGTH_SHORT).show()

                    Handler().postDelayed({
                        this.finish()
                        }, 1500)


                }
                return
            }
            else->{
                Toast.makeText(this@splashActivity,"Something Went Wrong", Toast.LENGTH_SHORT).show()
                this.finish()
                return
            }

        }
    }
    fun hasPermissions(context: Context, vararg permissions: String): Boolean {
        var hasAllPermissions = true
        for (permission in permissions) {
            val res = context.checkCallingOrSelfPermission(permission)
            if (res != PackageManager.PERMISSION_GRANTED)
                hasAllPermissions = false


        }
        return hasAllPermissions
    }
}
