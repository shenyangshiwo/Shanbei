package com.example.shanbei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import android.R.style;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
/*
* 类    名：EssayContentActivity
* 描    述：按需求显示文章内容的Activity
* 作    者：沈阳
* 时    间：2016-2-27
*/
public class EssayContentActivity extends Activity {
	private List<String> mWordList=null;//当前文章中包含的生词及等级
	private int mEssayId=-1;//当前文章的序号
	private MyTextView mTextViewEssay;//显示文章内容的TextView
	private TextView mTextViewTitle;//显示文章标题的TextView
	private TextView mTextViewShowNewWords;//点击该TextView控件，选择是否显示文章中的生词
	private SeekBar mSeekBar;//拖动该seekbar控件，选择显示生词的等级
	private MyApplication mApp;
	private String mTextContent;//要显示的文章内容
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		init();
		//为mTextViewShowNewWords添加点击事件，点击该TextView控件，选择是否显示文章中的生词
		mTextViewShowNewWords.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(mTextViewShowNewWords.getText().toString().equals("显示生词"))
				{
					mSeekBar.setProgress(mSeekBar.getMax());
					mTextViewShowNewWords.setText("关闭显示");
					mTextViewEssay.setmLevel(6);
					
					mTextViewEssay.invalidate();
					
					
					Toast.makeText(EssayContentActivity.this,"显示所有生词",Toast.LENGTH_SHORT).show();
					
				}
				else 
				{
					mSeekBar.setProgress(0);
					mTextViewShowNewWords.setText("显示生词");
					mTextViewEssay.setmLevel(-1);
					mTextViewEssay.invalidate();
					Toast.makeText(EssayContentActivity.this,"不显示生词",Toast.LENGTH_SHORT).show();
					
					
					
				}
				
			}
		});
		//为mSeekBar添加拖动事件，拖动该seekbar控件，选择显示生词的等级
		mSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				int level=seekBar.getProgress()-1;
				mTextViewEssay.setmLevel(level);
				mTextViewEssay.invalidate();
				if(level<0)
				{
					Toast.makeText(EssayContentActivity.this,"不显示生词",Toast.LENGTH_SHORT).show();
				}
				else {
					Toast.makeText(EssayContentActivity.this, (level)+"级及以下生词",Toast.LENGTH_SHORT).show();
				}
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				
				
			}
		});
		//mTextViewEssay添加点击事件，实现点击单词高亮显示
		mTextViewEssay.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		
	}
	/*
	 * 方 法 名：formatString()
	 * 功      能：获取要显示的文章内容
	 * 参      数：无
	 * 返 回 值：String——文章内容
	 * 作      者：沈阳
	 * 时      间：2016-2-27
	 */
	public String formatString() throws IOException
	{
		String s1="";
		InputStreamReader reader;
		reader = new InputStreamReader(getResources().getAssets().open(mEssayId+".txt"));
		BufferedReader bufReader=new BufferedReader(reader);
		String s2=bufReader.readLine();
		while((s2=bufReader.readLine())!= null)
		{
			if(s1.length()==0)
			{
				s1=s2;
			}
			else {
				s1=s1+"\n\n"+s2;
			}
			
		}
		bufReader.close();
		return s1;
	}
    /*
     * 方 法 名：init()
     * 功      能：对当前Activity进行初始化
     * 参      数：无
     * 返 回 值：无
     * 作      者：沈阳
     * 时      间：2016-2-27
     */
	public void init()
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.essay_layout);
		//获取控件
		mTextViewTitle=(TextView)findViewById(R.id.textview_title);
		mTextViewShowNewWords=(TextView)findViewById(R.id.textview_showNewWords);
		mTextViewEssay=(MyTextView)findViewById(R.id.textview_essay);
		mSeekBar=(SeekBar)findViewById(R.id.seekBar1);
		//得到文章id
		mEssayId=getIntent().getIntExtra("id", -1)+1;
		//设置标题
		mTextViewTitle.setText("Lesson "+mEssayId);
		//获取application
		mApp=(MyApplication)getApplication();
		try {
			mWordList=mApp.getNewWords(mEssayId+".txt");//获取当前文章中包含的生词及等级
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			//获取当前文章内容
			mTextContent=formatString();
			mTextViewEssay.setmLevel(-1);
			mTextViewEssay.setmTextContent(mTextContent);
			mTextViewEssay.setmWordList(mWordList);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	}
	



	

}
