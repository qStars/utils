package com.star.quick_work_sample

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.Keep
import android.util.Log
import android.view.View
import android.widget.Button
import com.star.quick_work.observer.TemplateObservable
import com.star.quick_work.observer.TemplateObserver
import com.star.quick_work.permission_tool.MPermission
import com.star.quick_work.permission_tool.annotation.OnMPermissionDenied
import com.star.quick_work.permission_tool.annotation.OnMPermissionGranted
import com.star.quick_work.permission_tool.annotation.OnMPermissionNeverAskAgain
import com.star.quick_work.slow_onclikc.SlowOnClickListener
import com.star.quick_work.timer.TimerHelper
import java.util.*


const val LIVE_PERMISSION_REQUEST_CODE: Int = 100

class MainActivity : AppCompatActivity() {
    var timer: TimerHelper? = null

    // 权限控制
    val TEST_PERMISSIONS = arrayOf(Manifest.permission.READ_PHONE_STATE)

    private var notifChange: TemplateObservable<Boolean>? = null

    var button: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.button)

        //防止暴力点击
        button?.setOnClickListener(object : SlowOnClickListener() {
            override fun onPreventQuickClick(v: View?) {

            }
        })


        //请求权限
        MPermission.with(this)
                .addRequestCode(LIVE_PERMISSION_REQUEST_CODE)
                .permissions(*TEST_PERMISSIONS)
                .request()


        //指定间隔时间重复执行任务
        timer = TimerHelper(1000 * 3)
        timer?.startTimer(object : TimerTask() {
            override fun run() {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        //注册观察者
        this.registerObserver(true)

        //发布消息
        getNotifChange().notifyChanged(false)
    }

    fun registerObserver(isRegister: Boolean) {
        getNotifChange().registerObserver(changeData, isRegister)
    }


    var changeData = TemplateObserver<Boolean> {
        Log.e("message", "receiving message")
    }


    fun getNotifChange(): TemplateObservable<Boolean> {
        if (notifChange == null)
            notifChange = TemplateObservable(this)
        return notifChange!!
    }


    override fun onDestroy() {
        //注销观察者
        this.registerObserver(false)
        super.onDestroy()
    }

    @Keep //如果使用混淆 此标记防止方法被删除
    @OnMPermissionGranted(LIVE_PERMISSION_REQUEST_CODE)
    fun onTestPermissionGranted() {
    }


    @Keep
    @OnMPermissionDenied(LIVE_PERMISSION_REQUEST_CODE)
    fun onTestPermissionDenied() {

    }

    @Keep
    @OnMPermissionNeverAskAgain(LIVE_PERMISSION_REQUEST_CODE)
    fun onTestPermissionDeniedAsNeverAskAgain() {
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        MPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults)
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}
