package com.ashehata.schedulingapp.app

import android.app.Application
import android.util.Log
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.ashehata.schedulingapp.workManager.TaskWorker
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
            15, TimeUnit.MINUTES)
                .setConstraints(mConstraints)
                .build()

        WorkManager.getInstance(this)
            .enqueueUniquePeriodicWork("work", ExistingPeriodicWorkPolicy.KEEP, periodicRequest)

    }
}