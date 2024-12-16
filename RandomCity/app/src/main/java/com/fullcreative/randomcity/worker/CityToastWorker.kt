package com.fullcreative.randomcity.worker

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters

class CityToastWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        val cityName = inputData.getString("cityName") ?: return Result.failure()
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(applicationContext, "Welcome to $cityName", Toast.LENGTH_LONG).show()
        }
        return Result.success()
    }
}