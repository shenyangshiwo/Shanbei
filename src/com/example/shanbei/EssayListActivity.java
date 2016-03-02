package com.example.shanbei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
/*
* ��    ����EssayListActivity
* ��    ���������б���ʾActivity
* ��    �ߣ�����
* ʱ    �䣺2016-2-27
*/
public class EssayListActivity extends Activity {
	private ListView mListView;//��ʾȫ���������Ƶ�ListView
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        //mListView��ӵ���¼��������Ӧ�����±���,�򿪶�Ӧ���������ݡ�
        mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(EssayListActivity.this, EssayContentActivity.class);
				intent.putExtra("id", position);
				startActivity(intent);
			}
		});
        //mListView��ӻ�����������������ײ�ʱͨ��Toast��ʾ�û���
        mListView.setOnScrollListener(new OnScrollListener() {
			
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				switch (scrollState) 
				{	
				case SCROLL_STATE_IDLE:
					if(view.getLastVisiblePosition()==view.getCount()-1)
					{
						Toast.makeText(EssayListActivity.this, "�Ѿ������ˣ�", Toast.LENGTH_SHORT).show();
					}
					break;
				default:
					break;
				}
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub
				
			}
			
			
		});
        
    }
    /*
    * �� �� ����getData()
    * ��      �ܣ���ȡmListView��Ҫ��ʾ������
    * ��      ������
    * �� �� ֵ��List<Map<String, Object>>����mListView��Ҫ��ʾ������
    * ��      �ߣ�����
    * ʱ      �䣺2016-2-27
    */
    private List<Map<String, Object>> getData()
    {
    	List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
    	for(int i=1;i<=48;i++)
    	{
    		Map<String, Object> map=new HashMap<String, Object>();
    		map.put("name","Lesson "+i);
    		String s="";
    		
			try {
				InputStreamReader reader;
				reader = new InputStreamReader(getResources().getAssets().open(i+".txt"));
				BufferedReader bufReader=new BufferedReader(reader);
				s=bufReader.readLine();
				s=bufReader.readLine();
				bufReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		map.put("title", s);
    		list.add(map);
    	}
    	return list;
    	
    }
    /*
     * �� �� ����init()
     * ��      �ܣ��Ե�ǰActivity���г�ʼ������Ҫ������mListView��Ҫ��ʾ������
     * ��      ������
     * �� �� ֵ����
     * ��      �ߣ�����
     * ʱ      �䣺2016-2-27
     */
    private void init()
    {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        mListView=(ListView)findViewById(R.id.listView_catalog);
        SimpleAdapter adapter=new SimpleAdapter(this, getData(), R.layout.catalog_listview, new String[]{"name","title"}, new int[]{R.id.name,R.id.title});
        mListView.setAdapter(adapter);
    }
}
