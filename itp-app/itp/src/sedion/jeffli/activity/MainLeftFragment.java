package sedion.jeffli.activity;

import org.apache.commons.lang3.StringUtils;

import com.zxing.activity.CaptureActivity;

import sedion.jeffli.activity.MainActivity.SaveStudentCourseRelationAsyncTask;
import sedion.jeffli.buildin.BaseFragment;
import sedion.jeffli.constant.LoginActivityConstant;
import sedion.jeffli.constant.MainLeftFragConstant;
import sedion.jeffli.entity.Results;
import sedion.jeffli.itp.R;
import sedion.jeffli.pullreash.ui.PullToRefreshBase;
import sedion.jeffli.pullreash.ui.PullToRefreshWebView;
import sedion.jeffli.pullreash.ui.PullToRefreshBase.OnRefreshListener;
import sedion.jeffli.util.GlobalParameterApplication;
import sedion.jeffli.util.UIHelper;
import sedion.jeffli.util.WarnUtils;
import sedion.wq.itp.webservice.CourseWebService;
import sedion.wq.itp.webservice.WebServiceHelper;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

public class MainLeftFragment extends BaseFragment {
	

	//webView控件
	private     WebView  	mainWebView;
	
	private LinearLayout layoutMortgageCalculator;
	//二维码扫描登录
	private LinearLayout layoutScanBarCode;
	private LinearLayout layoutAboutUs;
	private LinearLayout layoutCheckForUpdates;
	private LinearLayout layout_out;
	
		
	//其他数据
	private static 	Results results;
	//保存学生和课关系 任务
	private 		SaveStudentCourseRelationAsyncTask saveStudentCourseRelationAsyncTask = null;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.main_left, null);
	}
	
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		this.initViews();
	}

	private void initViews() {
		
		View parent = this.getView();
		
		//this.mainWebView = (WebView) parent.findViewById(R.id.wv_main);
		
		this.layout_out 				= (LinearLayout) parent.findViewById(R.id.layout_out);
		this.layoutAboutUs			  	= (LinearLayout) parent.findViewById(R.id.layout_about_us);
		this.layoutScanBarCode 			= (LinearLayout) parent.findViewById(R.id.layout_scanBarCode);
		this.layoutCheckForUpdates 		= (LinearLayout) parent.findViewById(R.id.layout_check_for_updates);
		this.layoutMortgageCalculator 	= (LinearLayout) parent.findViewById(R.id.layout_mortgage_calculator);

		this.layout_out.setOnClickListener(this);
		this.layoutAboutUs.setOnClickListener(this);
		this.layoutScanBarCode.setOnClickListener(this);
		this.layoutCheckForUpdates.setOnClickListener(this);
		this.layoutMortgageCalculator.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) 
	{
		switch (v.getId()) 
		{
			case R.id.layout_mortgage_calculator: 
			{
				WarnUtils.toast(this.context, "个人资料，后续开发中");
				break;
			}
			case R.id.layout_scanBarCode: 
			{
				Intent openCameraIntent = new Intent(getActivity(),CaptureActivity.class);
				startActivityForResult(openCameraIntent, 0);//打开二维码扫描界面
				
				break;
			}
			case R.id.layout_about_us: 
			{
				WarnUtils.toast(this.context, "系统设置，后续开发中");
				break;
			}
			case R.id.layout_check_for_updates: 
			{
				WarnUtils.toast(this.context, "修改用户密码");
				/*修改密码页面*/
				((MainFragment)getFragmentManager().findFragmentById(R.id.slidingmenu_content)).openChagePwdWeb();
				break;
			}
			case R.id.layout_out: 
			{
				/*退出程序*/
				showCheckDialog(this.context, "确定退出智慧教学平台？");
				break;
			}
		}
	}
	

	/**
     *  退出程序 提示对话框
     * @param message
     */
    public void showCheckDialog(Context context,String message){
        new AlertDialog.Builder(context)  
        .setTitle("温馨提示")
        .setMessage(message)
        .setPositiveButton("确定", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which) {
            	/*退出程序*/
				Intent intent = new Intent(Intent.ACTION_MAIN);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.addCategory(Intent.CATEGORY_HOME);
				startActivity(intent);
				System.exit(0);
            }    
        })
        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        })
        .show();
    }
    
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
		
		super.onActivityResult(requestCode, resultCode, data);
		
		if (resultCode == Activity.RESULT_OK)//二维码扫描 返回结果处理，并显示
		{
			
			Bundle bundle = data.getExtras();
			String scanResult = bundle.getString("result");
			
			//检查URL 的 result
			System.out.println("results:"+scanResult);
			if (scanResult.startsWith(WebServiceHelper.IP)) 
			{
				String[] params   = new String[] {scanResult,String.valueOf(getUlId())};
				
				saveStudentCourseRelationAsyncTask = new SaveStudentCourseRelationAsyncTask();
				saveStudentCourseRelationAsyncTask.execute(params);
			}
			else
			{
				UIHelper.ToastMessage(getActivity(), MainLeftFragConstant.getQrCodeIsNotGood());
			}
			
			
		}
	}
	
	class SaveStudentCourseRelationAsyncTask extends AsyncTask<String, Integer, Integer>
	{
		private Results result = null;

		@Override
		protected Integer doInBackground(String... params) 
		{
			result  = CourseWebService.saveStudentCourseRelation(params[0],params[1]);//webService保存课程学生关系
			setResults(result);
			
			if (result != null)
			{
				Log.e("resultid", result .getResults()+"");
				return result .getResults();
			}
			else
			{
				return WebServiceHelper.RESULT_NET_FAIL;
			}
			
		}
		
		@Override
		protected void onPostExecute(Integer result)
		{
			if ( saveStudentCourseRelationAsyncTask != null ) 
			{
				
				//根据不同情况处理
				if (result == WebServiceHelper.OTHER_FAILURE)
					UIHelper.ToastMessage(getActivity(), MainLeftFragConstant.getCourseIsNotRunning());
				if (result == WebServiceHelper.RESULT_NET_FAIL)
					UIHelper.ToastMessage(getActivity(), MainLeftFragConstant.getNetWorkUnavailable());
				else if (result == WebServiceHelper.FAILURE)
					UIHelper.ToastMessage(getActivity(), MainLeftFragConstant.getQrCodeFaile());
				else if	(result == WebServiceHelper.SUCCESS)
				{
					UIHelper.ToastMessage(getActivity(), MainLeftFragConstant.getQrCodeSuccess());
					//刷新
					
				}else
					UIHelper.ToastMessage(getActivity(), LoginActivityConstant.getItpGoesError());
				
			}
		}
		
		@Override
		protected void onCancelled() 
		{
			saveStudentCourseRelationAsyncTask = null;
		}	
	}
	
	public static Results getResults()
	{
		return results;
	}

	public static void setResults(Results results)
	{
		MainLeftFragment.results = results;
	}
	
}
