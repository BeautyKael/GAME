package com.zhouls.threehero;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.os.Bundle;

import com.lsgame.test.sound.GameSoundLoader;
import com.lsgame.test.sound.GameSoundLoader.IOnLoadCompleteListener;
import com.lsgame.test.sound.GameSoundPool;
import com.zhouls.threehero.controller.IController;
import com.zhouls.threehero.controller.view.BaseControllerView;
import com.zhouls.threehero.ui.manager.HotSpotManager;

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
		test();
	}

	public void test() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("sound_001", R.raw.msg);
		map.put("sound_002", R.raw.msg);
		map.put("sound_003", R.raw.msg);
		map.put("sound_004", R.raw.msg);
		map.put("sound_005", R.raw.msg);
		map.put("sound_006", R.raw.msg);
		GameSoundLoader.getInstance().load(map, new IOnLoadCompleteListener() {

			@Override
			public void onLoadComplete() {
				// TODO Auto-generated method stub

			}

			@Override
			public void onLoadProgress(int curIndex, int totolSize) {
				// TODO Auto-generated method stub

			}
		});
	}
}
