package com.example.shanbei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.R.integer;
import android.R.string;
import android.app.Application;
import android.util.Log;
/**

* MyApplication包含一些公用方法和公用变量

* @author Shenyang

* @Time 2016-02-27

*/
public class MyApplication extends Application {
	/**

	* 文章文本预处理

	* @param essayName assets文件夹中保存的文章名称

	* @return 返回文章每个单词与等级的List集合，如果该单词不在生词列表中，等级为-1

	* @author Shenyang
	 * @throws IOException 

	* @Time 2016-02-27


	   */
	public List<String> essayMap(String essayName) throws IOException
	{
		List<String> list=new ArrayList<String>();
		Map<String, String> map=new HashMap<String, String>();
		//获取文章txt的输入流Reader_essay
	    InputStreamReader reader_essay=new InputStreamReader(getResources().getAssets().open(essayName));
	    //获取单词列表txt的输入流bufReader_list
	    InputStreamReader reader_list=new InputStreamReader(getResources().getAssets().open("list.txt"));
		BufferedReader bufReader_list=new BufferedReader(reader_list);
		
		String s=null;
		//将单词和级别用键值对的形式保存到map中
		while((s=bufReader_list.readLine())!=null)
		{
			String[] ss=s.split(",");
			map.put(ss[0], ss[1]);
		}
		
		int c=-1;
		String word="";
		while((c=reader_essay.read())!=-1)
		{
			if((c>=(int)'a'&&c<=(int)'z')||(c>=(int)'A'&&c<=(int)'Z'))
			{
				word=word+(char)c;
				
			}
			else {
				
				String valueOfWord=map.get(word);
				if(valueOfWord!=null)
				{
					list.add(word);
					list.add(valueOfWord);
				}
				word="";
			}
		}
		
		bufReader_list.close();
		reader_essay.close();
		return list;
		
	}
	

}
