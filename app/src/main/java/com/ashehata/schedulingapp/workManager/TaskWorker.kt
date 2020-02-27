package com.ashehata.schedulingapp.workManager

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.ashehata.schedulingapp.activity.Main2Activity
import com.ashehata.schedulingapp.activity.Main3Activity
import com.ashehata.schedulingapp.activity.MainActivity
import com.ashehata.schedulingapp.R

class TaskWorker(context: Context, workPara: WorkerParameters) : Worker(context, workPara) {

    override fun doWork(): Result {

        sendNotification(context = applicationContext, id = 0)

        return Result.success()
    }

    private fun sendNotification(context: Context, id: Int) {
        val builder = NotificationCompat.Builder(context, "0")
            .setSmallIcon(R.drawable.ic_launcher_background) //set icon for notification
            .setContentTitle("Welcome again!") //set title of notification
            .setContentText("Press here to open a random activity.") //this is notification message
            .setAutoCancel(true) // makes auto cancel of notification
            .setPriority(NotificationCompat.PRIORITY_HIGH) //set priority of notification

        val notificationIntent = Intent(context, randomActivity())
        notificationIntent.apply {
            // Set your own flags
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }

        val pendingIntent = PendingIntent.getActivity(
            context, 0, notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        builder.setContentIntent(pendingIntent)

        // Add as notification
        val manager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(id, builder.build())
    }

    private fun randomActivity() = arrayOf(
        MainActivity::class.java,
        Main2Activity::class.java,
        Main3Activity::class.java).random()

}