package com.example.shanbei;


import java.util.List;

import android.R.integer;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View.MeasureSpec;
import android.widget.TextView;
/*
* ��    ����MyTextView
* ��    �������Զ����ı���ʾTextView,ʵ�������˶��뼰����ָ���ȼ�������ʾ����
* ��    �ߣ�����
* ʱ    �䣺2016-2-28
*/
public class MyTextView extends TextView {
	public List<String> mWordList=null;//��ǰ�����а��������ʼ��ȼ�
	public float mTextSize;//�����С
	public int mTextColor;//������ɫ
	public Paint mPaint;//����
	public String mTextContent="shen yang nanjing university";//��ʾ����
	public float mTextViewWidth;//�ؼ����
	public float mWordDistance=20;//������ʾ��С���
	public float mLineSpace=10;//������ʾ�о�
	public int mLineCount=0;//��ʾ����
	public float mPadding=20;//��ʾ�߾�
	String[] mTextWords=null;//��������Ӣ�ĵ�������
	public int mLevel=-1;//Ҫ��ʾ�����ʼ���-1������ʾ����
	/*
	 * �� �� ����MyTextView(Context context, AttributeSet attrs) 
	 * ��      �ܣ���д���๹�캯�������û�ˢ�Ĵ�С����ɫ
	 * ��      ����(Context context, AttributeSet attrs)
	 * �� �� ֵ����
	 * ��      �ߣ�����
	 * ʱ      �䣺2016-2-29
	 */
	public MyTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		
		
		//TypedArray a=context.obtainStyledAttributes(attrs,R.styleable.MyTextView);
		mTextColor=Color.rgb(0x00, 0x00, 0x00);  
		mTextSize=40;
		mPaint = new Paint();
		mPaint.setTextSize(mTextSize);  
        mPaint.setColor(mTextColor);
        //a.recycle(); 
	}
	/* @���Ǹ���onMeasure(int widthMeasureSpec, int heightMeasureSpec)����
	 * �� �� ����onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	 * ��      �ܣ����Ǹ���onMeasure�������Զ���ؼ��Ĵ�С
	 * ��      ����(Context context, AttributeSet attrs)
	 * �� �� ֵ����
	 * ��      �ߣ�����
	 * ʱ      �䣺2016-2-29
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		//super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
		mTextViewWidth=MeasureSpec.getSize(widthMeasureSpec)-mPadding*2;
		mTextWords=mTextContent.split(" ");
		int start=0;
		int end=start;
		mLineCount=0;
		
		while(start<mTextWords.length)
		{
			float drawedWidth=mPadding;
			for(end=start;end<mTextWords.length;end++)
			{
				if(end==start)
				{
					
					
					drawedWidth=drawedWidth+mPaint.measureText(mTextWords[start]);
				}
				else
				{
					float willDrawWidth=drawedWidth+mWordDistance+mPaint.measureText(mTextWords[end]);
					if(willDrawWidth<=mTextViewWidth)
					{
						
						drawedWidth=willDrawWidth;
						if(end==mTextWords.length-1)
						{
							mLineCount++;
							break;
						}
					}
					else
					{

						mLineCount++;
						break;
					}
				}
			}
			start=end;
			
		}
		setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), (int) ((mLineCount+1)*(mTextSize+mLineSpace))+50);
	    
	}
	/* @���Ǹ���onDraw(Canvas canvas)����
	 * �� �� ����onDraw(Canvas canvas)
	 * ��      �ܣ����Ǹ���onDraw(Canvas canvas)�������Զ���ؼ�����ʾ������
	 * ��      ����Canvas canvas��������
	 * �� �� ֵ����
	 * ��      �ߣ�����
	 * ʱ      �䣺2016-2-29
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		//super.onDraw(canvas);
		//mTextWords=mTextContent.split(" ");
		reDraw(canvas, mLevel);
	
	}
	/* 
	 * �� �� ����reDraw(Canvas canvas, int i)
	 * ��      �ܣ��Զ���ؼ�����ʾ������
	 * ��      ����Canvas canvas����������int i����Ҫ��ʾ�����ʵȼ�
	 * �� �� ֵ����
	 * ��      �ߣ�����
	 * ʱ      �䣺2016-2-29
	 */
	public void reDraw(Canvas canvas, int i)
	{
		int start=0;
		int end=start;
		mLineCount=0;
		mPaint = new Paint();
		mPaint.setTextSize(mTextSize);  
        mPaint.setColor(mTextColor);
		while(start<(mTextWords.length))
		{
			float drawedWidth=mPadding;
			if(start==(mTextWords.length-1))
			{
				
				break;
			}
			for(end=start;end<mTextWords.length;end++)
			{
				if(end==start)
				{
					
					
					drawedWidth=drawedWidth+mPaint.measureText(mTextWords[start]);
					
				}
				else
				{
					float willDrawWidth=drawedWidth+mWordDistance+mPaint.measureText(mTextWords[end]);
					if(willDrawWidth<=mTextViewWidth)
					{
						
						drawedWidth=willDrawWidth;
						if(end==(mTextWords.length-1))
						{
							drawedWidth=mPadding;
							for(int k=start;k<=end;k++)
							{
								if(k==start)
								{
									if(choosePaint(k))
									{
										float startX=drawedWidth;
										float startY=(mLineCount+1)*(mTextSize+mLineSpace);
										float stopX=startX+mPaint.measureText(mTextWords[start]);
										float stopY=startY;
										
										canvas.drawLine(startX, startY, stopX, stopY, mPaint);
									}
							
									canvas.drawText(mTextWords[k], drawedWidth, (mLineCount+1)*(mTextSize+mLineSpace), mPaint);
									drawedWidth=drawedWidth+mWordDistance+mPaint.measureText(mTextWords[start]);	
									
								}
								else
								{
									if(choosePaint(k))
									{
										float startX=drawedWidth;
										float startY=(mLineCount+1)*(mTextSize+mLineSpace);
										float stopX=startX+mPaint.measureText(mTextWords[start]);
										float stopY=startY;
										
										canvas.drawLine(startX, startY, stopX, stopY, mPaint);
									}
									canvas.drawText(mTextWords[k], drawedWidth, (mLineCount+1)*(mTextSize+mLineSpace), mPaint);
									drawedWidth=drawedWidth+mWordDistance+mPaint.measureText(mTextWords[k]);
								}
							}
							mLineCount++;
							break;
						}
					}
					else
					{
						float additionaWidth=mTextViewWidth-drawedWidth;
						float avrAdditonWidth=additionaWidth/(end-start-1)+mWordDistance;
						drawedWidth=mPadding;
						for(int k=start;k<end;k++)
						{
							if(k==start)
							{
								if(choosePaint(k))
								{
									float startX=drawedWidth;
									float startY=(mLineCount+1)*(mTextSize+mLineSpace);
									float stopX=startX+mPaint.measureText(mTextWords[k]);
									float stopY=startY;
									
									canvas.drawLine(startX, startY, stopX, stopY, mPaint);
								}
								canvas.drawText(mTextWords[k], drawedWidth, (mLineCount+1)*(mTextSize+mLineSpace), mPaint);
								drawedWidth=drawedWidth+avrAdditonWidth+mPaint.measureText(mTextWords[start]);
							}
							else
							{
								if(choosePaint(k))
								{
									float startX=drawedWidth;
									float startY=(mLineCount+1)*(mTextSize+mLineSpace);
									float stopX=startX+mPaint.measureText(mTextWords[k]);
									float stopY=startY;
									
									canvas.drawLine(startX, startY, stopX, stopY, mPaint);
								}
								canvas.drawText(mTextWords[k], drawedWidth, (mLineCount+1)*(mTextSize+mLineSpace), mPaint);
								drawedWidth=drawedWidth+avrAdditonWidth+mPaint.measureText(mTextWords[k]);
							}
						}
						mLineCount++;
						break;
					}
				}
			}
			start=end;
			
			
		}
		
	}
	/* 
	 * �� �� ����choosePaint(int k)
	 * ��      �ܣ��ж�mTextWords[k]�Ƿ��������ʣ��ǵĻ��������ú�ɫ����true�����ǵĻ��������ú�ɫ����false
	 * ��      ����int k��������mTextWords�����k���ַ���
	 * �� �� ֵ��boolean����mTextWords[k]�Ƿ���������
	 * ��      �ߣ�����
	 * ʱ      �䣺2016-2-29
	 */
	public boolean choosePaint(int k)
	{
		int i=0;
		boolean IsNewWord=false;
		for(;i<mWordList.size();i=i+2)
		{
			if(mTextWords[k].contains(mWordList.get(i)))
			{
				if(Integer.parseInt(mWordList.get(i+1))<=mLevel)
				{
					IsNewWord=true;
			        break;
					
				}
				else
				{
					
			        IsNewWord=false;
			        break;
				}
				
			}
			
		}
		if(IsNewWord==true)
		{
			mPaint=new Paint();
			mTextColor=Color.RED;
			mTextSize=40;
			mPaint.setTextSize(mTextSize);  
	        mPaint.setColor(mTextColor);
	        
		}
		else {
			mTextColor=Color.BLACK;  
			mTextSize=40;
			mPaint = new Paint();
			mPaint.setTextSize(mTextSize);  
	        mPaint.setColor(mTextColor);
		}
		return IsNewWord;
		
		
	}

}
