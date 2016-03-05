package com.example.shanbei;


import java.util.List;

import android.R.integer;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View.MeasureSpec;
import android.widget.TextView;
/*
* 类    名：MyTextView
* 描    述：按自定义文本显示TextView,实现了两端对齐及按照指定等级高亮显示生词
* 作    者：沈阳
* 时    间：2016-2-28
*/
public class MyTextView extends TextView {
	private List<String> mWordList=null;//当前文章中包含的生词及等级
	private float mTextSize;//字体大小
	private int mTextColor;//字体颜色
	private Paint mPaint;//画笔
	private String mTextContent="shen yang nanjing university";//显示内容
	private float mTextViewWidth;//控件宽度
	private float mWordDistance=20;//单词显示最小间隔
	private float mLineSpace=10;//单词显示行距
	private int mLineCount=0;//显示行数
	private float mPadding=20;//显示边距
	private String[] mTextWords=null;//文章内容英文单词数组
	private int mLevel=-1;//要显示的生词级别，-1代表不显示生词
	
	public void setmWordList(List<String> mWordList) {
		this.mWordList = mWordList;
	}

	public void setmTextContent(String mTextContent) {
		this.mTextContent = mTextContent;
	}

	public void setmLevel(int mLevel) {
		this.mLevel = mLevel;
	}

	/*
	 * 方 法 名：MyTextView(Context context, AttributeSet attrs) 
	 * 功      能：重写父类构造函数，设置画刷的大小和颜色
	 * 参      数：(Context context, AttributeSet attrs)
	 * 返 回 值：无
	 * 作      者：沈阳
	 * 时      间：2016-2-29
	 */
	public MyTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		
		
		
		mTextColor=Color.rgb(0x00, 0x00, 0x00);  
		mTextSize=40;
		mPaint = new Paint();
		mPaint.setTextSize(mTextSize);  
        mPaint.setColor(mTextColor);

	}
	/* @覆盖父类onMeasure(int widthMeasureSpec, int heightMeasureSpec)方法
	 * 方 法 名：onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	 * 功      能：覆盖父类onMeasure方法，自定义控件的大小
	 * 参      数：(Context context, AttributeSet attrs)
	 * 返 回 值：无
	 * 作      者：沈阳
	 * 时      间：2016-2-29
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
	/* @覆盖父类onDraw(Canvas canvas)方法
	 * 方 法 名：onDraw(Canvas canvas)
	 * 功      能：覆盖父类onDraw(Canvas canvas)方法，自定义控件中显示的内容
	 * 参      数：Canvas canvas——画布
	 * 返 回 值：无
	 * 作      者：沈阳
	 * 时      间：2016-2-29
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		//super.onDraw(canvas);
		
		reDraw(canvas, mLevel);
	
	}
	/* 
	 * 方 法 名：reDraw(Canvas canvas, int i)
	 * 功      能：自定义控件中显示的内容
	 * 参      数：Canvas canvas——画布，int i——要显示的生词等级
	 * 返 回 值：无
	 * 作      者：沈阳
	 * 时      间：2016-2-29
	 */
	public void reDraw(Canvas canvas, int i)
	{
		
		int start=0;
		int end=start;
		mLineCount=0;
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
									//---
									
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
		canvas.save();
		canvas.restore();
		
		
	}
	/* 
	 * 方 法 名：choosePaint(int k)
	 * 功      能：判断mTextWords[k]是否属于生词，是的话画笔设置红色返回true，不是的话画笔设置黑色返回false
	 * 参      数：int k——代表mTextWords数组第k个字符串
	 * 返 回 值：boolean——mTextWords[k]是否属于生词
	 * 作      者：沈阳
	 * 时      间：2016-2-29
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
			
			mTextColor=Color.RED;
			mTextSize=40;
			mPaint.setTextSize(mTextSize);  
	        mPaint.setColor(mTextColor);
	        
		}
		else {
			mTextColor=Color.BLACK;  
			mTextSize=40;
			
			mPaint.setTextSize(mTextSize);  
	        mPaint.setColor(mTextColor);
		}
		return IsNewWord;
		
		
	}

}
