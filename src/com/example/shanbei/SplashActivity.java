package com.example.shanbei;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends Activity {
    public long logTime;//��¼ʱ��
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);
        logTime=System.currentTimeMillis();//��ȡ��¼ʱ��
        new SplashThread().start();
	}
	public class SplashThread extends Thread
	{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			while((System.currentTimeMillis()-logTime)<1000)
			{
				//�������������Ԥ���ع���
			}
			startActivity(new Intent(getApplication(),MainActivity.class));
			SplashActivity.this.finish();
			
		}
		
	}
}
