/*
 * @Title LSHandlerPoster.java
 * @Copyright Copyright 2010-2015 Careland Software Co,.Ltd All Rights Reserved.
 * @author Zhouls
 * @date Nov 28, 2016 4:34:49 PM
 * @version 1.0
 */
package com.lsgame.base.model;

import java.util.LinkedList;
import java.util.Queue;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

/**
 * Àà×¢ÊÍ
 * 
 * @author Zhouls
 * @date Nov 28, 2016 4:34:49 PM
 */
public class LSHandlerPoster extends Handler {
	private final static int ASYNC = 0;
	private final static int SYNC = 1;
	private Queue<Runnable> asyncQueue;
	private int maxMillsInsideHandleMessage;
	private boolean asyncActive;

	public LSHandlerPoster(Looper looper, int maxMillsInsideHandleMessage) {
		super(looper);
		asyncQueue = new LinkedList<Runnable>();
		this.maxMillsInsideHandleMessage = maxMillsInsideHandleMessage;
	}

	public void async(Runnable runnable) {
		synchronized (asyncQueue) {
			asyncQueue.offer(runnable);
			if (!asyncActive) {
				asyncActive = true;
				if (!sendMessage(obtainMessage(ASYNC))) {
					Log.e("HandlerPoster", "Could not send handler message");
				}
			}
		}
	}

	/**
	 * @see android.os.Handler#handleMessage(android.os.Message)
	 */
	@Override
	public void handleMessage(Message msg) {
		// TODO Auto-generated method stub
		super.handleMessage(msg);
		switch (msg.what) {
		case ASYNC:
			

			break;
		case SYNC:

			break;
		}
	}
}
