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
/**

* 文章文本显示Activity

* @author Shenyang

* @Time 2016-02-27

*/
public class EssayActivity extends Activity {
	public List<String> list_word=null;
	public int id=-1;
	public MyTextView textView_essay;
	public TextView textView_title;
	public TextView textView_showNewWord;
	public SeekBar seekBar;
	public MyApplication app;
	public String text;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		init();
		
		textView_showNewWord.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(textView_showNewWord.getText().toString().equals("显示生词"))
				{
					seekBar.setProgress(seekBar.getMax());
					textView_showNewWord.setText("关闭显示");
					textView_essay.level=6;
					textView_essay.setEnabled(false);
					textView_essay.setEnabled(true);
					
					
					Toast.makeText(EssayActivity.this,"显示所有生词",Toast.LENGTH_SHORT).show();
					
				}
				else 
				{
					seekBar.setProgress(0);
					textView_showNewWord.setText("显示生词");
					textView_essay.level=-1;
					textView_essay.setEnabled(false);
					textView_essay.setEnabled(true);
					Toast.makeText(EssayActivity.this,"不显示生词",Toast.LENGTH_SHORT).show();
					
					
					
				}
				
			}
		});
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				int level=seekBar.getProgress()-1;
				textView_essay.level=level;
				textView_essay.setEnabled(false);
				textView_essay.setEnabled(true);
				if(level<0)
				{
					Toast.makeText(EssayActivity.this,"不显示生词",Toast.LENGTH_SHORT).show();
				}
				else {
					Toast.makeText(EssayActivity.this, (level)+"级及以下生词",Toast.LENGTH_SHORT).show();
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
		
		
		
		
	}
	/**

	* txt文本转化为string

	* @return 返回文章文本的string对象

	* @author Shenyang
	
	* @throws IOException 

	* @Time 2016-02-27

	*/
	public String formatString() throws IOException
	{
		String s1="";
		InputStreamReader reader;
		reader = new InputStreamReader(getResources().getAssets().open(id+".txt"));
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
	/**

	* activity初始化

	* @author Shenyang

	* @Time 2016-02-27

	*/
	public void init()
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.essay_layout);
		//获取控件
		textView_title=(TextView)findViewById(R.id.textview_title);
		textView_showNewWord=(TextView)findViewById(R.id.textview_showNewWords);
		textView_essay=(MyTextView)findViewById(R.id.textview_essay);
		seekBar=(SeekBar)findViewById(R.id.seekBar1);
		//得到文章id
		id=getIntent().getIntExtra("id", -1)+1;
		//设置标题
		textView_title.setText("Lesson "+id);
		//获取application
		MyApplication app=(MyApplication)getApplication();
		try {
			list_word=app.essayMap(id+".txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			text=formatString();
			textView_essay.level=-1;
			textView_essay.textContent=text;
			textView_essay.list_word=list_word;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	



	

}
