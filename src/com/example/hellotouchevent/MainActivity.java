package com.example.hellotouchevent;

import android.os.Bundle;
import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends Activity implements OnTouchListener{
	
	private RelativeLayout mRlFather;
	private RelativeLayout mRlMezi;
	private DisplayMetrics dm;
	private int lastX, lastY;     

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		dm = getResources().getDisplayMetrics();     
        final int screenWidth = dm.widthPixels;     
        final int screenHeight = dm.heightPixels - 50;    
		setListener();
	}


	private void initView() {
		mRlFather = (RelativeLayout) findViewById(R.id.rl_father);
		mRlMezi = (RelativeLayout) findViewById(R.id.rl_mezi);
	}
	
	private void setListener() {
		mRlFather.setOnTouchListener(this);
		mRlMezi.setOnTouchListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	@Override
	public boolean onTouch(View v, MotionEvent event) {
		
		int ea = event.getAction();     
		final int screenWidth = dm.widthPixels;     
        final int screenHeight = dm.heightPixels; 
		switch(v.getId()){
		case R.id.rl_father:
			break;
		case R.id.rl_mezi:
			switch(ea){
			case MotionEvent.ACTION_DOWN:     
                lastX = (int) event.getRawX();// 获取触摸事件触摸位置的原始X坐标     
                lastY = (int) event.getRawY();     
                break;     
            case MotionEvent.ACTION_MOVE:     
               int dx = (int) event.getRawX() - lastX;     
                int dy = (int) event.getRawY() - lastY;     
               int l = v.getLeft() + dx;     
               int b = v.getBottom() + dy;     
                int r = v.getRight() + dx;     
                int t = v.getTop() + dy;     
               // 下面判断移动是否超出屏幕     
                if (l < 0) {     
                    l = 0;     
                   r = l + v.getWidth();     
                }     
                if (t < 0) {     
                  t = 0;     
                    b = t + v.getHeight();     
                }     
                if (r > screenWidth) {     
                    r = screenWidth;     
                    l = r - v.getWidth();     
               }     
                if (b > screenHeight) {     
                    b = screenHeight;     
                   t = b - v.getHeight();     
               }     
                v.layout(l, t, r, b);     
                lastX = (int) event.getRawX();     
               lastY = (int) event.getRawY();     
               v.postInvalidate();     
                break;                    
                case MotionEvent.ACTION_UP:     
              break; 
			default:
				break;
			}
		default :
			break;
		}
		return true;
	}

}
