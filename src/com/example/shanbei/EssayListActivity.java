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
* 类    名：EssayListActivity
* 描    述：文章列表显示Activity
* 作    者：沈阳
* 时    间：2016-2-27
*/
public class EssayListActivity extends Activity {
	private ListView mListView;//显示全部文章名称的ListView
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        //mListView添加点击事件，点击相应的文章标题,打开对应的文章内容。
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
        //mListView添加活动监听器，当滑动到底部时通过Toast提示用户。
        mListView.setOnScrollListener(new OnScrollListener() {
			
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				switch (scrollState) 
				{	
				case SCROLL_STATE_IDLE:
					if(view.getLastVisiblePosition()==view.getCount()-1)
					{
						Toast.makeText(EssayListActivity.this, "已经到底了！", Toast.LENGTH_SHORT).show();
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
    * 方 法 名：getData()
    * 功      能：获取mListView中要显示的数据
    * 参      数：无
    * 返 回 值：List<Map<String, Object>>——mListView中要显示的数据
    * 作      者：沈阳
    * 时      间：2016-2-27
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
     * 方 法 名：init()
     * 功      能：对当前Activity进行初始化，主要是设置mListView中要显示的内容
     * 参      数：无
     * 返 回 值：无
     * 作      者：沈阳
     * 时      间：2016-2-27
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
