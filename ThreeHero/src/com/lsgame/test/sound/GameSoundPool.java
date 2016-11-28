/*
 * @Title GameSoundPool.java
 * @Copyright Copyright 2010-2015 Careland Software Co,.Ltd All Rights Reserved.
 * @author Zhouls
 * @date Nov 28, 2016 2:47:56 PM
 * @version 1.0
 */
package com.lsgame.test.sound;

import java.io.FileDescriptor;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;

/**
 * ������
 * 
 * @author Zhouls
 * @date Nov 28, 2016 2:47:56 PM
 */
public class GameSoundPool {
	private SoundPool sp;

	/**
	 * ������
	 * 
	 * @param maxStreams
	 *            ��Ƶ�صĴ�С
	 * @param streamType
	 *            ����������(AudioManager.STREAM_MUSIC)
	 * @param srcQuality
	 *            ��Ƶ��������Ĭ��Ϊ0
	 */
	public GameSoundPool(int maxStreams, int streamType, int srcQuality) {
		sp = new SoundPool(maxStreams, streamType, srcQuality);
	}

	/**
	 * ������Դ
	 * 
	 * @param path
	 *            �ļ�·��
	 * @param priority
	 * @return
	 * @return int
	 * @author Zhouls
	 * @date Nov 28, 2016 2:56:45 PM
	 */
	public int load(String path, int priority) {
		return sp.load(path, priority);
	}

	/**
	 * ����Դid ������Դ
	 * 
	 * @param context
	 * @param resId
	 * @param priority
	 * @return
	 * @return int
	 * @author Zhouls
	 * @date Nov 28, 2016 2:57:03 PM
	 */
	public int load(Context context, int resId, int priority) {
		return sp.load(context, resId, priority);
	}

	/**
	 * ��assetfile
	 * 
	 * @param afd
	 * @param priority
	 * @return
	 * @return int
	 * @author Zhouls
	 * @date Nov 28, 2016 2:57:19 PM
	 */
	public int load(AssetFileDescriptor afd, int priority) {
		return sp.load(afd, priority);
	}

	/**
	 * ���ļ�
	 * 
	 * @param fd
	 * @param offset
	 * @param length
	 * @param priority
	 * @return
	 * @return int
	 * @author Zhouls
	 * @date Nov 28, 2016 2:57:37 PM
	 */
	public int load(FileDescriptor fd, long offset, long length, int priority) {
		return sp.load(fd, offset, length, priority);
	}

	/**
	 * ���ü��ػص�����
	 * 
	 * @param listener
	 * @return void
	 * @author Zhouls
	 * @date Nov 28, 2016 2:57:46 PM
	 */
	public void setOnLoadCompleteListener(OnLoadCompleteListener listener) {
		sp.setOnLoadCompleteListener(listener);
	}

	public boolean unload(int i) {
		return sp.unload(i);
	}

	/**
	 * ����
	 * 
	 * @param soundID
	 * @param leftVolume
	 *            0.0f~1.0f
	 * @param rightVolume
	 *            0.0f~1.0f
	 * @param priority
	 * @param loop
	 * @param rate
	 * @return
	 * @return int
	 * @author Zhouls
	 * @date Nov 28, 2016 3:17:32 PM
	 */
	public int play(int soundID, float leftVolume, float rightVolume,
			int priority, int loop, float rate) {
		return sp.play(soundID, leftVolume, rightVolume, priority, loop, rate);
	}

	public void pause(int i) {
		sp.pause(i);
	}

	public void resume(int i) {
		sp.resume(i);
	}

	public void autoPause() {
		sp.autoPause();
	}

	public void autoResume() {
		sp.autoResume();
	}

	public void stop(int i) {
		sp.stop(i);
	}

	public void setVolume(int i, float f, float f1) {
		sp.setVolume(i, f, f1);
	}

	public void setPriority(int i, int j) {
		sp.setPriority(i, j);
	}

	public void setLoop(int i, int j) {
		sp.setLoop(i, j);
	}

	public void setRate(int i, float f) {
		sp.setRate(i, f);
	}

	public void release() {
		sp.release();
	}
}
