package com.shouzhong.base.request

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shouzhong.base.util.getApp

class RequestActivity : AppCompatActivity() {
    private var uniqueId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        uniqueId = intent.getIntExtra("unique_id", 0)
        startActivityForResult(
            intent.getParcelableExtra("data")!!,
            uniqueId and 0x0000ffff
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == (uniqueId and 0x0000ffff)) {
            getApp()!!.sendBroadcast(
                Intent("${getApp()!!.packageName}.shouzhong.receiver.action.START_ACTIVITY_FOR_RESULT_$uniqueId").apply {
                    putExtra("result_code", resultCode)
                    if (data != null) putExtra("data", data)
                }
            )
            finish()
            return
        }
    }
}