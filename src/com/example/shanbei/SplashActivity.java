package com.example.shanbei;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
/*
* ��    ����SplashAcitvity
* ��    ������������Activity
* ��    �ߣ�����
* ʱ    �䣺2016-2-27
*/
public class SplashActivity extends Activity {
    private long mLogTime;//��¼ʱ��
    private long mWaitTime=1000;//�ȴ�ʱ��
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);
        mLogTime=System.currentTimeMillis();//��ȡ��¼ʱ��
        new LoadThread().start();
       
	}
	/*
	* ��    ����LoadThread
	* ��    �������ݼ����̣߳����ݼ����ʱ��ΪmWaitTime��������ɽ����µ�Acitivity
	* ��    �ߣ�����
	* ʱ    �䣺2016-2-27
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
