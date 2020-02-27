package com.ashehata.schedulingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.ashehata.schedulingapp.workManager.TaskWorker
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Set constraints
        val mConstraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()


        // Set Worker request type
        val oneTimeRequest = OneTimeWorkRequest.Builder(TaskWorker::class.java)
            .setConstraints(mConstraints)
            .build()

        // Start task
        btn_start.setOnClickListener {
            WorkManager.getInstance(applicationContext).enqueue(oneTimeRequest)

        }
    }

}