package sedion.jeffli.activity;

import java.util.Timer;
import java.util.TimerTask;

import sedion.jeffli.itp.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class ImageToLoginActivity extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image_to_login);
		final ImageView flower = (ImageView) findViewById(R.id.imageToLogin);
		// 加载第一份动画资源
		final Animation anim = AnimationUtils.loadAnimation(this, R.anim.anim);
		// 设置动画结束后保留结束状态
		anim.setFillAfter(true);

		final Handler handler = new Handler()
		{
			@Override
			public void handleMessage(Message msg)
			{
				if (msg.what == 0x123)
				{
					flower.startAnimation(anim);
				}
				if (msg.what == 0x345)
				{
					Intent intent = new Intent(ImageToLoginActivity.this, LoginActivity.class);
					startActivityForResult(intent, 0);
				}
			}
		};
		// 设置1秒后启动第二个动画
		new Timer().schedule(new TimerTask()
		{
			@Override
			public void run()
			{
				handler.sendEmptyMessage(0x123);
			}
		}, 1000);
		// 设置1.5秒后启动
		new Timer().schedule(new TimerTask()
		{
			@Override
			public void run()
			{
				handler.sendEmptyMessage(0x345);
			}
		}, 1500);
	}
}
