package sedion.jeffli.activity;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import sedion.jeffli.buildin.BaseFragment;
import sedion.jeffli.itp.R;
import sedion.jeffli.pullreash.ui.PullToRefreshBase;
import sedion.jeffli.pullreash.ui.PullToRefreshBase.OnRefreshListener;
import sedion.jeffli.pullreash.ui.PullToRefreshWebView;
import sedion.jeffli.util.UIHelper;
import sedion.jeffli.util.WarnUtils;
import sedion.wq.itp.webservice.WebServiceHelper;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import com.slidingmenu.lib.SlidingMenu;

@SuppressLint("ValidFragment")
public class MainFragment extends BaseFragment {
	
	
	//滑动菜单
	private 	SlidingMenu menu;
	//左滑动菜单按钮
	private 	ImageView 	ivMore;
	//webView控件
	private     WebView  	mainWebView;
	
	//下拉刷新webView控件
	private PullToRefreshWebView mPullWebView;
	//刷新时间
    private SimpleDateFormat mDateFormat = new SimpleDateFormat("MM-dd HH:mm");
    
    public MainFragment(){}
	public MainFragment(SlidingMenu menu) {
		this.menu = menu;
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View v =  inflater.inflate(R.layout.main_activity, null);
		return	v;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		
		super.onActivityCreated(savedInstanceState);

		this.initViews();
		//this.initWebView();//初始化相关WEBVIEW 
		
	}

	private void initViews()
	{
		
		View parent = this.getView();

		//this.ivMore		 = (ImageView) parent.findViewById(R.id.btn_more);
		//this.mainWebView = (WebView) parent.findViewById(R.id.wv_main);
		this.mPullWebView= (PullToRefreshWebView) parent.findViewById(R.id.pull_webview);
		
		mPullWebView.setOnRefreshListener(new OnRefreshListener<WebView>() {

            @Override
            public void onPullDownToRefresh(PullToRefreshBase<WebView> refreshView) {
                loadUrl(null);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<WebView> refreshView) {
            }
        });
		
		mainWebView = mPullWebView.getRefreshableView();
		WebSettings webSettings = mainWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);//js
        mainWebView.setWebChromeClient(new WebChromeClient()
        {
           @Override
		    public boolean onJsAlert(WebView view, String url, String message,
		    		JsResult result)
		    {
		    	// TODO Auto-generated method stub
		    	return super.onJsAlert(view, url, message, result);
		    }

        });
		mainWebView.setWebViewClient(new WebViewClient() {
	            public boolean shouldOverrideUrlLoading(WebView view, String url) {
	                return false;
	            }
	            public void onPageFinished(WebView view, String url) {
	                mPullWebView.onPullDownRefreshComplete();
	                setLastUpdateTime();
	            }
	        });

        loadUrl(null);
        setLastUpdateTime();
		//this.ivMore.setOnClickListener(this);
	}

	private void setLastUpdateTime() {
        String text = formatDateTime(System.currentTimeMillis());
        mPullWebView.setLastUpdatedLabel(text);
    }
	
	private String formatDateTime(long time) {
	     if (0 == time) {
	         return "";
	     }
	     
	     return mDateFormat.format(new Date(time));
	}
 
/*	@Override
	public void onClick(View v) 
	{
		switch (v.getId()) 
		{
			case R.id.btn_more: 
			{
				this.menu.showMenu();
				break;
			}
			case KeyEvent.KEYCODE_MENU:
			{
				this.menu.showMenu();
				break;
			}
		}
	}

	 @Override
	 public boolean onKeyDown(int keyCode, KeyEvent event) {
	  
	  if (keyCode == KeyEvent.KEYCODE_MENU) { // 在这里做你想做的事情
		  this.menu.showMenu();
	  }   
	  return true; // 最后，一定要做完以后返回 true，或者在弹出菜单后返回true，其他键返回super，让其他键默认
	 }*/
	
	/*//webView Configurations
	@SuppressLint("SetJavaScriptEnabled")
	private void initWebView()
	{
        WebSettings webSettings = mainWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);//js
        
        mainWebView.setWebViewClient(new WebViewClient());
        mainWebView.loadUrl(WebServiceHelper.IP+WebServiceHelper.PROJECT+"studentCourseRelation/getStudentCourseRelationsByUserLoginApp?ulName="+getUserName()+"&password="+getUserPass());
	}*/
	
	/*打开主页*/
	private void loadUrl(String urlStr) 
	{
        if (StringUtils.isNotEmpty(urlStr))
            mainWebView.loadUrl(urlStr);
        else 
            mainWebView.loadUrl(WebServiceHelper.IP+WebServiceHelper.PROJECT+"studentCourseRelation/getStudentCourseRelationsByUserLoginApp?ulName="+getUserName()+"&ulPassword="+pswReturn(getUserPass()));

	}
	
	/*打开修改密码页面*/
	public void openChagePwdWeb()
	{
		/*js调用退出弹出框*/
        mainWebView.addJavascriptInterface(this, "jsi");
        mainWebView.loadUrl(WebServiceHelper.IP+WebServiceHelper.PROJECT+"admin/showChangeUserpwdByUserLoginApp?ulName="+getUserName()+"&ulPassword="+pswReturn(getUserPass()));
	}
			
	/**
     *  退出程序 提示对话框 
     *  webView中调用toast原生组件
     * @param message
     */
    public void exitToast(){
        new AlertDialog.Builder(this.context)  
        .setTitle("温馨提示")
        .setMessage("修改密码成功。请重启软件，按确定键重启。")
        .setPositiveButton("确定", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which) {
            	/*退出程序*/
				Intent intent = new Intent(Intent.ACTION_MAIN);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.addCategory(Intent.CATEGORY_HOME);
				startActivity(intent);
				System.exit(0);
            }    
        }).show();
    }
    
  //MD5
  	public static String pswReturn(String psdString)
  	{
  		//设置密码用MD5加密**********
   		try 
   		{ 
   			MessageDigest md = MessageDigest.getInstance("MD5"); 
   			md.update(psdString.getBytes()); 
   			byte b[] = md.digest(); 
   	
   			int buffNum; 
   			StringBuffer buf = new StringBuffer(""); 
   			for (int offset = 0; offset < b.length; offset++)
   			{ 
   				buffNum = b[offset]; 
   				if(buffNum<0) buffNum+= 256; 
   				if(buffNum<16) 
   				buf.append("0"); 
   				buf.append(Integer.toHexString(buffNum)); 
   			} 
   			psdString=buf.toString();//32位的加密 
   			System.out.println("密码="+buf);
   			return psdString;
   		} 
   		catch (NoSuchAlgorithmException e) 
   		{ 
   			e.printStackTrace(); 
   		}
  		return psdString; 
  	}
}
 