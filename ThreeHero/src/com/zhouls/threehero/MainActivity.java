package com.zhouls.threehero;

import com.zhouls.threehero.controller.IController;
import com.zhouls.threehero.controller.view.BaseControllerView;
import com.zhouls.threehero.ui.manager.HotSpotManager;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		BaseControllerView baseControllerView = (BaseControllerView) findViewById(R.id.controller);
		baseControllerView.setOnControlFuctionListener(new IController() {

			@Override
			public int onReleaseSkill() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public float onMoveRight() {
				// TODO Auto-generated method stub
				HotSpotManager.getInstance().hotSpot.x += 20;
				return 0;
			}

			@Override
			public float onMoveLeft() {
				// TODO Auto-generated method stub
				HotSpotManager.getInstance().hotSpot.x -= 20;
				return 0;
			}

			@Override
			public float onJump() {
				// TODO Auto-generated method stub
				HotSpotManager.getInstance().hotSpot.y -= 60;
				return 0;
			}

			@Override
			public boolean onAttack() {
				// TODO Auto-generated method stub
				return false;
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}