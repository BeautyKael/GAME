/*
 * @Title GameSoundLoader.java
 * @Copyright Copyright 2010-2015 Careland Software Co,.Ltd All Rights Reserved.
 * @author Zhouls
 * @date Nov 28, 2016 3:37:07 PM
 * @version 1.0
 */
package com.lsgame.test.sound;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.util.Log;

import com.lsgame.base.model.LSTask;
import com.zhouls.threehero.ui.utils.PhoneManager;

/**
 * 声音加载器
 * 
 * @author Zhouls
 * @date Nov 28, 2016 3:37:07 PM
 */
public class GameSoundLoader {
	private final String TAG = "GameSoundLoader";
	private Map<String, Object> soundMap;
	private GameSoundPool gameSoundPool;
	private final static int MAX_POOL_SIZE = 500;

	private GameSoundLoader() {
		soundMap = new HashMap<String, Object>();
		gameSoundPool = new GameSoundPool(MAX_POOL_SIZE,
				AudioManager.STREAM_MUSIC, 0);
	}

	/**
	 * 加载资源
	 * 
	 * @param resId
	 * @return void
	 * @author Zhouls
	 * @date Nov 28, 2016 3:43:39 PM
	 */
	public void load(final Map<String, Integer> map,
			final IOnLoadCompleteListener listener) {
		LSTask.add(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				gameSoundPool
						.setOnLoadCompleteListener(new OnLoadCompleteListener() {

							@Override
							public void onLoadComplete(SoundPool sp,
									int simapId, int status) {
								// TODO Auto-generated method stub
								int size = map.size();
								if (null != listener) {
									listener.onLoadProgress(simapId, size);
								}
								Log.d(TAG, simapId + "," + status);
								if (simapId == size && null != listener) {
									listener.onLoadComplete();
								}
							}
						});
				Set<String> keys = map.keySet();
				Iterator<String> iterator = keys.iterator();
				while (iterator.hasNext()) {
					String temp = iterator.next();
					Integer resId = map.get(temp);
					int sid = gameSoundPool.load(PhoneManager.getInstance()
							.getmContext(), resId, 0);
					soundMap.put(temp, sid);
					Log.d(TAG, temp);
				}
			}
		});
	}

	public interface IOnLoadCompleteListener {
		public void onLoadProgress(int curIndex, int totolSize);

		public void onLoadComplete();
	}

	public static GameSoundLoader getInstance() {
		return InnerInstance.instance;
	}

	private static class InnerInstance {
		public static GameSoundLoader instance = new GameSoundLoader();
	}

}
