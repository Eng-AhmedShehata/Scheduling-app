package com.ashehata.schedulingapp.workManager

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.ashehata.schedulingapp.Main3Activity
import com.ashehata.schedulingapp.R

class TaskWorker(context: Context, workPara: WorkerParameters) : Worker(context, workPara) {

    override fun doWork(): Result {

        sendNotification(context = applicationContext, id = 0)

        return Result.success()
    }

    private fun sendNotification(context: Context, id: Int) {
        val builder = NotificationCompat.Builder(context, "0")
            .setSmallIcon(R.drawable.ic_launcher_background) //set icon for notification
            .setContentTitle("Notifications Example") //set title of notification
            .setContentText("This is a notification message") //this is notification message
            .setAutoCancel(true) // makes auto cancel of notification
            .setPriority(NotificationCompat.PRIORITY_DEFAULT) //set priority of notification


        val notificationIntent = Intent(context, Main3Activity::class.java)
        notificationIntent.apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }
        //notification message will get at NotificationView
        //notification message will get at NotificationView
        //notificationIntent.putExtra("message", "This is a notification message")

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

    private fun randomActivity() {

    }
}