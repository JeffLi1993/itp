package sedion.jeffli.buildin;

import sedion.jeffli.util.ActivityUtils;
import sedion.jeffli.util.GlobalParameterApplication;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

/* 
 *Fragment作为Activity界面的一部分组成出现
 *可以在一个Activity中同时出现多个Fragment，并且，一个Fragment亦可在多个Activity中使用。
 *在Activity运行过程中，可以添加、移除或者替换Fragment（add()、remove()、replace()）
 *Fragment可以响应自己的输入事件，并且有自己的生命周期，当然，它们的生命周期直接被其所属的
 *宿主activity的生命周期影响。
 * */
public class BaseFragment extends Fragment implements View.OnClickListener{

	private int ulId;
	private String userName;
	private String userPass;
	
	public Context context;
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		this.context = this.getActivity();
	}
	
	@Override
	public void onClick(View v) {
	}

	public void exit(){
		this.getActivity().finish();
	}
	public void exitToHome(){
		ActivityUtils.finishAll();
	}
	
	public static final void setTextStyle(TextView view, boolean bold){
		view.getPaint().setFakeBoldText(bold);
	}

	public int getUlId() {
		return  ((GlobalParameterApplication) getActivity().getApplicationContext()).getUlId();
	}

	public String getUserName() {
		return ((GlobalParameterApplication) getActivity().getApplicationContext()).getUserName();
	}

	public String getUserPass() {
		return ((GlobalParameterApplication) getActivity().getApplicationContext()).getUserPass();
	}

	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		return false;
	}
}
