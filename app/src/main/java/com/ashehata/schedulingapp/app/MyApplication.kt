package com.ashehata.schedulingapp.app

import android.app.Application
import android.util.Log
import androidx.work.*
import com.ashehata.schedulingapp.workManager.TaskWorker
import java.lang.Exception
import java.lang.Integer.getInteger
import java.util.concurrent.TimeUnit

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

       try {
           startWorkManager()

       } catch (e: Exception) {
           Log.v("Error-WM",e.toString())
       }

    }

    private fun startWorkManager() {

        // Set constraints
        val mConstraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .build()

        // Set Worker request type
        val periodicRequest = PeriodicWorkRequest.Builder(TaskWorker::class.java,
            getInteger(R.integer.interval_time_in_hours).toLong(), TimeUnit.HOURS)
                .setConstraints(mConstraints)
                .build()

        WorkManager.getInstance(applicationContext).enqueue(periodicRequest)
    }
}