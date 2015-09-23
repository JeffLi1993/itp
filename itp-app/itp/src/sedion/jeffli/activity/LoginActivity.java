package sedion.jeffli.activity;


import org.apache.commons.lang3.StringUtils;

import sedion.jeffli.constant.LoginActivityConstant;
import sedion.jeffli.entity.Results;
import sedion.jeffli.itp.R;
import sedion.jeffli.util.GlobalParameterApplication;
import sedion.jeffli.util.UIHelper;
import sedion.wq.itp.webservice.WebService;
import sedion.wq.itp.webservice.WebServiceHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginActivity extends Activity
{
	// 登陆按钮
	private TextView loginButton;
	// 帐号输入框
	private EditText accountEditText;
	// 密码输入框
	private EditText passwordEditText;
	// 密码保存选择框
	private CheckBox rememberPassCheckBox;
	// 账号清除按钮
	private ImageView login_del;
	
	// 保存数据
	private SharedPreferences sp;

	// 交互数据
	private Results results;

	// 登陆后台任务
	private LoginAsyncTask loginAsyncTask;

	private final String USER_NAME = "USER_NAME";
	private final String USER_PASS = "PASSWORD";
	private final String ISCHECK   = "ISCHECK";
	private final String USER_INFO = "userInfo";
	
	@SuppressLint("WorldReadableFiles")
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		// 控件初始实例化
		loginButton = (TextView) findViewById(R.id.activity_login_btn_login);
		accountEditText = (EditText) findViewById(R.id.activity_login_account);
		passwordEditText = (EditText) findViewById(R.id.activity_login_password);
		rememberPassCheckBox = (CheckBox) findViewById(R.id.cb_mima);
		login_del=(ImageView)findViewById(R.id.login_delt_image);
		
		// 获得实例对象
		sp = getSharedPreferences(USER_INFO, Activity.MODE_PRIVATE);

		// 判断记住密码多选框的状态
		if (sp.getBoolean(ISCHECK, false))
		{
			System.out.println(sp.getString(USER_NAME, "")+"::"+sp.getString(USER_PASS, ""));
			// 设置默认是记录密码状态
			rememberPassCheckBox.setChecked(true);
			accountEditText.setText(sp.getString(USER_NAME, ""));
			passwordEditText.setText(sp.getString(USER_PASS, ""));
		}

		login_del.setOnClickListener(new OnClickListener()//账号清除按钮控件事件绑定
		{
			
			@Override
			public void onClick(View arg0)
			{
				accountEditText.setText("");
				login_del.setVisibility(View.GONE);
			}
		});
		
		accountEditText.addTextChangedListener(new TextWatcher()//账号改变控件事件绑定
		{
			
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3)
			{
				login_del.setVisibility(View.VISIBLE);
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3)
			{
			}
			
			@Override
			public void afterTextChanged(Editable arg0)
			{
				if(accountEditText.getText().toString().equals(""))
				{
					login_del.setVisibility(View.GONE);
				}
			}
		});
		
		loginButton.setOnClickListener(new OnClickListener()//登录控件事件绑定
				{
					@Override
					public void onClick(View v)
					{

						String userName = accountEditText.getText().toString();
						String userPassword = passwordEditText.getText()
								.toString();
						String[] params = new String[]
						{ userName, userPassword };

						if (StringUtils.isNotEmpty(userName)
								&& StringUtils.isNotEmpty(userPassword))
						{
							loginAsyncTask = new LoginAsyncTask();
							loginAsyncTask.execute(params);
							loginButton.setEnabled(false);
						} else
						{
							UIHelper.ToastMessage(LoginActivity.this,
									LoginActivityConstant
											.getUserNameOrUserPassNoEmpty());
						}

					}
				});

		// 监听记住密码多选框按钮事件
		rememberPassCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener()
		{
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked)
			{
				if (rememberPassCheckBox.isChecked())
				{
					System.out.println("记住密码已选中");
					sp.edit().putBoolean(ISCHECK, true).commit();

				} else
				{

					System.out.println("记住密码没有选中");
					sp.edit().putBoolean(ISCHECK, false).commit();

				}

			}
		});
	}

	class LoginAsyncTask extends AsyncTask<String, Integer, Integer>
	{
		private Results result = null;
		private String userName = null;
		private String userPass = null;

		@Override
		protected Integer doInBackground(String... params)
		{
			result = WebService.login(params[0], params[1]);// webService交互 返回数据
			results = result;

			if (result != null)
			{
				Log.e("resultid", result.getResults() + "");
				if (result.getResults() == WebServiceHelper.SUCCESS)
				{
					userName = params[0];
					userPass = params[1];
				}

				return result.getResults();
			} else
			{
				return WebServiceHelper.RESULT_NET_FAIL;
			}

		}

		@Override
		protected void onPostExecute(Integer result)
		{
			if (loginAsyncTask != null)
			{
				loginButton.setEnabled(true);

				// 根据不同情况处理
				if (result == WebServiceHelper.RESULT_NET_FAIL)
					UIHelper.ToastMessage(LoginActivity.this,
							LoginActivityConstant.getNetWorkUnavailable());
				else if (result == WebServiceHelper.FAILURE)
					UIHelper.ToastMessage(LoginActivity.this,
							LoginActivityConstant.getUserNameOrUserPassIsFail());
				else if (result == WebServiceHelper.SUCCESS)
				{
					// 登录成功和记住密码框为选中状态才保存用户信息
					if (rememberPassCheckBox.isChecked())
					{
						String userName = accountEditText.getText().toString();
						String userPass = passwordEditText.getText().toString();
						// 记住用户名、密码、
						sp.edit().putString(USER_NAME,userName).commit();
						sp.edit().putString(USER_PASS,userPass).commit();
					}
					
					UIHelper.ToastMessage(LoginActivity.this,
							LoginActivityConstant.getLoginSuccess());

					GlobalParameterApplication gpa = (GlobalParameterApplication) getApplicationContext();// 定义全局变量
					gpa.setUlId(results.getUlId());
					gpa.setUserName(userName);
					gpa.setUserPass(userPass);

					try
					{
						Bundle bundle = new Bundle();// 创建一个Bundle对象
						bundle.putSerializable(Results.RESULTS_NAME, results);
						Intent intent = new Intent(LoginActivity.this,
								MainAct.class);
						intent.putExtras(bundle);
						startActivity(intent);// 启动对应的activity
					} catch (Exception e)
					{
						throw new RuntimeException(
								"new Intent(LoginActivity.this,MainAct.class) ERROR!!",
								e);
					}

				} else
					UIHelper.ToastMessage(LoginActivity.this,
							LoginActivityConstant.getItpGoesError());

			}
		}

		@Override
		protected void onCancelled()
		{
			loginAsyncTask = null;

			loginButton.setEnabled(true);
		}
	}
	
}
