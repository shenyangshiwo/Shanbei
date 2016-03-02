package com.example.shanbei;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
/*
* 类    名：SplashAcitvity
* 描    述：开场动画Activity
* 作    者：沈阳
* 时    间：2016-2-27
*/
public class SplashActivity extends Activity {
    private long mLogTime;//登录时间
    private long mWaitTime=1000;//等待时间
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);
        mLogTime=System.currentTimeMillis();//获取登录时间
        new LoadThread().start();
       
	}
	/*
	* 类    名：LoadThread
	* 描    述：数据加载线程，数据加载最长时间为mWaitTime，加载完成进入新的Acitivity
	* 作    者：沈阳
	* 时    间：2016-2-27
	*/
	public class LoadThread extends Thread
	{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			while((System.currentTimeMillis()-mLogTime)<mWaitTime)
			{
				//
			}
			startActivity(new Intent(getApplication(),EssayListActivity.class));
			SplashActivity.this.finish();
			
		}
		
	}
}
