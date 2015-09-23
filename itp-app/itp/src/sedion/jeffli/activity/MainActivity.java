
package sedion.jeffli.activity;

import sedion.jeffli.entity.Results;
import sedion.jeffli.itp.R;
import sedion.jeffli.util.GlobalParameterApplication;
import sedion.jeffli.util.UIHelper;
import sedion.wq.itp.webservice.CourseWebService;
import sedion.wq.itp.webservice.WebServiceHelper;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity 
{
	/**webVie 控件**/
	private     WebView  mainWebView	   = null;
//	/**打开二维码扫描按钮**/
//	private 	Button   scanBarCodeButton = null;
//	/**返回结果的文本**/
//	private 	TextView resultTextView	   = null;			
	
	/**其他数据 **/
	private     static Results results;
	
	/**保存学生和课关系 任务**/
	private 	SaveStudentCourseRelationAsyncTask saveStudentCourseRelationAsyncTask = null;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
  //      mainWebView         = (WebView)  this.findViewById(R.id.wv_main);
//        resultTextView 		= (TextView) this.findViewById(R.id.tv_scan_result);
//        scanBarCodeButton 	= (Button) 	 this.findViewById(R.id.btn_scan_barcode);
        
        //初始化  -> WEBVIEW 
        initWebView();

        //初始化  -> Intent所携带的数据 和 全局变量
    	initDatas();
 		
       /* scanBarCodeButton.setOnClickListener(new OnClickListener() 
        {
			@Override
			public void onClick(View v) 
			{
				
				//打开扫描界面
				Intent openCameraIntent = new Intent(MainActivity.this,CaptureActivity.class);
				
				startActivityForResult(openCameraIntent, 0);
				
			}
		});*/
    }
    
    //初始化数据
	private void initDatas()
	{
		//获取启动该ResultActivity的Intent
 		Intent intent = getIntent();
 		//获取该Intent所携带的数据
 		Bundle bundle = intent.getExtras();
 		//从bundle数据包中取出数据
 		results 	  = (Results) bundle.getSerializable(Results.RESULTS_NAME);
 		
 		System.out.println("MainActivity..."+results.getUlId()+".."+results.getResults());
 		
 		//定义全局变量
 		GlobalParameterApplication gpa = (GlobalParameterApplication) getApplicationContext(); 
 		gpa.setUlId(results.getUlId());
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
		
		super.onActivityResult(requestCode, resultCode, data);
		
		//判断处理结果，并显示
		if (resultCode == RESULT_OK) 
		{
			
			Bundle bundle = data.getExtras();
			String scanResult = bundle.getString("result");
			
			//Toast.makeText(this, "result", Toast.LENGTH_LONG).show();
			//resultTextView.setText(scanResult);
			
			String[] params 	= new String[] {scanResult
					,String.valueOf(((GlobalParameterApplication) getApplicationContext()).getUlId())};
			
			saveStudentCourseRelationAsyncTask = new SaveStudentCourseRelationAsyncTask();
			saveStudentCourseRelationAsyncTask.execute(params);
			
		}
	}
	
	
	/**
	 * ==========================WEBVIEW CONFIGS==================================
	 */
	@SuppressLint("SetJavaScriptEnabled")
	private void initWebView()
	{
		//js
        WebSettings webSettings = mainWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //链接
        mainWebView.setWebViewClient(new WebViewClient());
        mainWebView.loadUrl("http://www.baidu.com/");
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    // Check if the key event was the Back button and if there's history
	    if ((keyCode == KeyEvent.KEYCODE_BACK) && mainWebView.canGoBack()) {
	    	mainWebView.goBack();
	        return true;
	    }
	    // If it wasn't the Back key or there's no web page history, bubble up to the default
	    // system behavior (probably exit the activity)
	    return super.onKeyDown(keyCode, event);
	}
	
	//==========================WEBVIEW CONFIGS==================================
	 
	
	
	
	//异步类 -> 保存课程学生关系
	class SaveStudentCourseRelationAsyncTask extends AsyncTask<String, Integer, Integer>
	{
		private Results result = null;

		@Override
		protected Integer doInBackground(String... params) 
		{
			result  = CourseWebService.saveStudentCourseRelation(params[0],params[1]);
			results 	   = result ;	//赋值
			
			if (result  != null)
			{
				Log.e("resultid", result .getResults()+"");
				return result .getResults();
			}
			else
			{
				Log.e("resultid", result .getResults()+"");
				return WebServiceHelper.FAILURE;
			}
			
		}
		
		@Override
		protected void onPostExecute(Integer result)
		{
			if ( saveStudentCourseRelationAsyncTask != null ) 
			{
				
				Log.e("result:", result+"");
				//根据不同情况处理
				if (result == -4)
				{
					UIHelper.ToastMessage(MainActivity.this, "网络不可用");
				}
				else if (result == 0 )
				{
					UIHelper.ToastMessage(MainActivity.this, "失败");
				}
				else if	(result == 1)
				{
					UIHelper.ToastMessage(MainActivity.this, "二维码扫描签到成功");
				}
				else
				{
					UIHelper.ToastMessage(MainActivity.this, "...");
				}
			}
		}
		
		@Override
		protected void onCancelled() 
		{
			saveStudentCourseRelationAsyncTask = null;
		}	
	}
	
}