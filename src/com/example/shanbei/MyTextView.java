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
/**

* 文章文本显示自定义TextView

* @author Shenyang

* @Time 2016-02-27

*/
public class MyTextView extends TextView {
	public List<String> list_word=null;
	private float textSize;
	private int textColor;
	private Paint mPaint;  
	public String textContent="shen yang nanjing university";
	private float textViewWidth;
	private float wordSeparationDistance=20;
	private float lineSpacing=10;
	private int lineCount=0;
	private float padding=20;
	String[] textWords=null;
	public int level=-1;
	public MyTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		
		textWords=textContent.split(" ");
		//TypedArray a=context.obtainStyledAttributes(attrs,R.styleable.MyTextView);
		textColor=Color.rgb(0x00, 0x00, 0x00);  
		textSize=40;
		mPaint = new Paint();
		mPaint.setTextSize(textSize);  
        mPaint.setColor(textColor);
        //a.recycle(); 
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		//super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
		textViewWidth=MeasureSpec.getSize(widthMeasureSpec)-padding*2;
		
		int start=0;
		int end=start;
		lineCount=0;
		
		while(start<textWords.length)
		{
			float drawedWidth=padding;
			for(end=start;end<textWords.length;end++)
			{
				if(end==start)
				{
					
					
					drawedWidth=drawedWidth+mPaint.measureText(textWords[start]);
				}
				else
				{
					float willDrawWidth=drawedWidth+wordSeparationDistance+mPaint.measureText(textWords[end]);
					if(willDrawWidth<=textViewWidth)
					{
						
						drawedWidth=willDrawWidth;
						if(end==textWords.length-1)
						{
							lineCount++;
							break;
						}
					}
					else
					{

						lineCount++;
						break;
					}
				}
			}
			start=end;
			
		}
		setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), (int) ((lineCount+1)*(textSize+lineSpacing))+50);
	    
	}
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		//super.onDraw(canvas);
		textWords=textContent.split(" ");
		reDraw(canvas, level);
	
	}
	public void reDraw(Canvas canvas, int i)
	{
		int start=0;
		int end=start;
		lineCount=0;
		mPaint = new Paint();
		mPaint.setTextSize(textSize);  
        mPaint.setColor(textColor);
		while(start<(textWords.length))
		{
			float drawedWidth=padding;
			if(start==(textWords.length-1))
			{
				
				break;
			}
			for(end=start;end<textWords.length;end++)
			{
				if(end==start)
				{
					
					
					drawedWidth=drawedWidth+mPaint.measureText(textWords[start]);
					
				}
				else
				{
					float willDrawWidth=drawedWidth+wordSeparationDistance+mPaint.measureText(textWords[end]);
					if(willDrawWidth<=textViewWidth)
					{
						
						drawedWidth=willDrawWidth;
						if(end==(textWords.length-1))
						{
							drawedWidth=padding;
							for(int k=start;k<=end;k++)
							{
								if(k==start)
								{
									if(choosePaint(k))
									{
										float startX=drawedWidth;
										float startY=(lineCount+1)*(textSize+lineSpacing);
										float stopX=startX+mPaint.measureText(textWords[start]);
										float stopY=startY;
										
										canvas.drawLine(startX, startY, stopX, stopY, mPaint);
									}
							
									canvas.drawText(textWords[k], drawedWidth, (lineCount+1)*(textSize+lineSpacing), mPaint);
									drawedWidth=drawedWidth+wordSeparationDistance+mPaint.measureText(textWords[start]);	
									
								}
								else
								{
									if(choosePaint(k))
									{
										float startX=drawedWidth;
										float startY=(lineCount+1)*(textSize+lineSpacing);
										float stopX=startX+mPaint.measureText(textWords[start]);
										float stopY=startY;
										
										canvas.drawLine(startX, startY, stopX, stopY, mPaint);
									}
									canvas.drawText(textWords[k], drawedWidth, (lineCount+1)*(textSize+lineSpacing), mPaint);
									drawedWidth=drawedWidth+wordSeparationDistance+mPaint.measureText(textWords[k]);
								}
							}
							lineCount++;
							break;
						}
					}
					else
					{
						float additionaWidth=textViewWidth-drawedWidth;
						float avrAdditonWidth=additionaWidth/(end-start-1)+wordSeparationDistance;
						drawedWidth=padding;
						for(int k=start;k<end;k++)
						{
							if(k==start)
							{
								if(choosePaint(k))
								{
									float startX=drawedWidth;
									float startY=(lineCount+1)*(textSize+lineSpacing);
									float stopX=startX+mPaint.measureText(textWords[k]);
									float stopY=startY;
									
									canvas.drawLine(startX, startY, stopX, stopY, mPaint);
								}
								canvas.drawText(textWords[k], drawedWidth, (lineCount+1)*(textSize+lineSpacing), mPaint);
								drawedWidth=drawedWidth+avrAdditonWidth+mPaint.measureText(textWords[start]);
							}
							else
							{
								if(choosePaint(k))
								{
									float startX=drawedWidth;
									float startY=(lineCount+1)*(textSize+lineSpacing);
									float stopX=startX+mPaint.measureText(textWords[k]);
									float stopY=startY;
									
									canvas.drawLine(startX, startY, stopX, stopY, mPaint);
								}
								canvas.drawText(textWords[k], drawedWidth, (lineCount+1)*(textSize+lineSpacing), mPaint);
								drawedWidth=drawedWidth+avrAdditonWidth+mPaint.measureText(textWords[k]);
							}
						}
						lineCount++;
						break;
					}
				}
			}
			start=end;
			
			
		}
		
	}
	public boolean choosePaint(int k)
	{
		int i=0;
		boolean flag=false;
		for(;i<list_word.size();i=i+2)
		{
			if(textWords[k].contains(list_word.get(i)))
			{
				if(Integer.parseInt(list_word.get(i+1))<=level)
				{
					flag=true;
			        break;
					
				}
				else
				{
					
			        flag=false;
			        break;
				}
				
			}
			
		}
		if(flag==true)
		{
			mPaint=new Paint();
			textColor=Color.RED;
			textSize=40;
			mPaint.setTextSize(textSize);  
	        mPaint.setColor(textColor);
	        
		}
		else {
			textColor=Color.rgb(0x00, 0x00, 0x00);  
			textSize=40;
			mPaint = new Paint();
			mPaint.setTextSize(textSize);  
	        mPaint.setColor(textColor);
		}
		return flag;
		
		
	}

}
