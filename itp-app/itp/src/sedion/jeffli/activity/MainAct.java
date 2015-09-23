package sedion.jeffli.activity;

import sedion.jeffli.entity.Results;
import sedion.jeffli.itp.R;
import sedion.jeffli.util.ActivityUtils;
import sedion.jeffli.util.GlobalParameterApplication;
import sedion.jeffli.util.WarnUtils;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.KeyEvent;

import com.slidingmenu.lib.SlidingMenu;

public class MainAct extends FragmentActivity 
{
	
	//滑动菜单
	private SlidingMenu menu;
	//其他数据 
	private static Results results;
		
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slidingmenu_main);
        
        menu = new SlidingMenu(this);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		menu.setShadowWidthRes(R.dimen.slidingmenu_shadow_width);
		menu.setShadowDrawable(R.drawable.slidingmenu_shadow);
		menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		menu.setBehindWidth(600);
		menu.setFadeDegree(0.35f);
		menu.setBehindOffset(dm.widthPixels*30/100);
		menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		
		
		menu.setMode(SlidingMenu.LEFT);	//这里模式设置为左菜单，RIGHT显示右菜单，LEFT显示左菜单，LEFT_RIGH显示左右菜单
		menu.setContent(R.layout.slidingmenu_content);
		menu.setMenu(R.layout.slidingmenu_menu);
		
		//右菜单暂时不用
		/*menu.setSecondaryMenu(R.layout.slidingmenu_menu_2);
		menu.setSecondaryShadowDrawable(R.drawable.slidingmenu_shadow_2);*/
		
		getSupportFragmentManager()
			.beginTransaction()
			.replace(R.id.slidingmenu_content, new MainFragment(menu))
			.commit();//设置主页面内容
		
		getSupportFragmentManager()
			.beginTransaction()
			.replace(R.id.slidingmenu_menu, new MainLeftFragment())
			.commit();//设置左菜单内容
		
		/*getSupportFragmentManager()
			.beginTransaction()
			.replace(R.id.slidingmenu_menu_2, new MainRightFragment(menu))
			.commit();//设置右菜单内容*/       
    }

    @Override
    protected void onResume() {
    	super.onResume();
    	ActivityUtils.clearAll();
    }
    
    private long currentTime;
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if(System.currentTimeMillis() - currentTime < 2000){
				this.finish();
			}else{
				WarnUtils.toast(this, R.string.warn_wether_to_exit);
			}
			currentTime = System.currentTimeMillis();
			return true;
		}
		return false;
	}

	//初始化数据
	private void initDatas()
	{
		
 		Intent intent = getIntent();//获取启动该ResultActivity的Intent
 		Bundle bundle = intent.getExtras();//获取该Intent所携带的数据
 		
 		results 	  = (Results) bundle.getSerializable(Results.RESULTS_NAME);//从bundle数据包中取出数据
 		
 		System.out.println("MainAct..."+results.getUlId()+".."+results.getResults());
 		
	}
		
}
