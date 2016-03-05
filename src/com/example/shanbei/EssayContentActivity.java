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
* ��    ����EssayContentActivity
* ��    ������������ʾ�������ݵ�Activity
* ��    �ߣ�����
* ʱ    �䣺2016-2-27
*/
public class EssayContentActivity extends Activity {
	private List<String> mWordList=null;//��ǰ�����а��������ʼ��ȼ�
	private int mEssayId=-1;//��ǰ���µ����
	private MyTextView mTextViewEssay;//��ʾ�������ݵ�TextView
	private TextView mTextViewTitle;//��ʾ���±����TextView
	private TextView mTextViewShowNewWords;//�����TextView�ؼ���ѡ���Ƿ���ʾ�����е�����
	private SeekBar mSeekBar;//�϶���seekbar�ؼ���ѡ����ʾ���ʵĵȼ�
	private MyApplication mApp;
	private String mTextContent;//Ҫ��ʾ����������
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		init();
		//ΪmTextViewShowNewWords��ӵ���¼��������TextView�ؼ���ѡ���Ƿ���ʾ�����е�����
		mTextViewShowNewWords.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(mTextViewShowNewWords.getText().toString().equals("��ʾ����"))
				{
					mSeekBar.setProgress(mSeekBar.getMax());
					mTextViewShowNewWords.setText("�ر���ʾ");
					mTextViewEssay.setmLevel(6);
					
					mTextViewEssay.invalidate();
					
					
					Toast.makeText(EssayContentActivity.this,"��ʾ��������",Toast.LENGTH_SHORT).show();
					
				}
				else 
				{
					mSeekBar.setProgress(0);
					mTextViewShowNewWords.setText("��ʾ����");
					mTextViewEssay.setmLevel(-1);
					mTextViewEssay.invalidate();
					Toast.makeText(EssayContentActivity.this,"����ʾ����",Toast.LENGTH_SHORT).show();
					
					
					
				}
				
			}
		});
		//ΪmSeekBar����϶��¼����϶���seekbar�ؼ���ѡ����ʾ���ʵĵȼ�
		mSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				int level=seekBar.getProgress()-1;
				mTextViewEssay.setmLevel(level);
				mTextViewEssay.invalidate();
				if(level<0)
				{
					Toast.makeText(EssayContentActivity.this,"����ʾ����",Toast.LENGTH_SHORT).show();
				}
				else {
					Toast.makeText(EssayContentActivity.this, (level)+"������������",Toast.LENGTH_SHORT).show();
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
		//mTextViewEssay��ӵ���¼���ʵ�ֵ�����ʸ�����ʾ
		mTextViewEssay.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		
	}
	/*
	 * �� �� ����formatString()
	 * ��      �ܣ���ȡҪ��ʾ����������
	 * ��      ������
	 * �� �� ֵ��String������������
	 * ��      �ߣ�����
	 * ʱ      �䣺2016-2-27
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
     * �� �� ����init()
     * ��      �ܣ��Ե�ǰActivity���г�ʼ��
     * ��      ������
     * �� �� ֵ����
     * ��      �ߣ�����
     * ʱ      �䣺2016-2-27
     */
	public void init()
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.essay_layout);
		//��ȡ�ؼ�
		mTextViewTitle=(TextView)findViewById(R.id.textview_title);
		mTextViewShowNewWords=(TextView)findViewById(R.id.textview_showNewWords);
		mTextViewEssay=(MyTextView)findViewById(R.id.textview_essay);
		mSeekBar=(SeekBar)findViewById(R.id.seekBar1);
		//�õ�����id
		mEssayId=getIntent().getIntExtra("id", -1)+1;
		//���ñ���
		mTextViewTitle.setText("Lesson "+mEssayId);
		//��ȡapplication
		mApp=(MyApplication)getApplication();
		try {
			mWordList=mApp.getNewWords(mEssayId+".txt");//��ȡ��ǰ�����а��������ʼ��ȼ�
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			//��ȡ��ǰ��������
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
