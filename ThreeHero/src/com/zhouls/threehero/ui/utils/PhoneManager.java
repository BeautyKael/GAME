/*
 * @Title PhoneUtils.java
 * @Copyright Copyright 2010-2015 Careland Software Co,.Ltd All Rights Reserved.
 * @author Zhouls
 * @date Nov 21, 2016 10:27:35 AM
 * @version 1.0
 */
package com.zhouls.threehero.ui.utils;

import android.content.Context;
import android.view.WindowManager;

/**
 * Àà×¢ÊÍ
 * 
 * @author Zhouls
 * @date Nov 21, 2016 10:27:35 AM
 */
public class PhoneManager {
	private Context mContext;

	private int sWidth;

	private int sHeight;

	private PhoneManager() {

	}

	public void init(Context mContext) {
		this.mContext = mContext;
		WindowManager wm = (WindowManager) mContext
				.getSystemService(Context.WINDOW_SERVICE);
		sWidth = wm.getDefaultDisplay().getWidth();
		sHeight = wm.getDefaultDisplay().getHeight();
	}

	/**
	 * º¯Êý×¢ÊÍ
	 * 
	 * @return
	 * @author Zhouls
	 * @date Nov 21, 2016 10:34:52 AM
	 */
	public Context getmContext() {
		return mContext;
	}

	/**
	 * @param mContext
	 *            the mContext to set
	 */
	public void setmContext(Context mContext) {
		this.mContext = mContext;
	}

	/**
	 * º¯Êý×¢ÊÍ
	 * 
	 * @return
	 * @author Zhouls
	 * @date Nov 21, 2016 10:34:52 AM
	 */
	public int getsWidth() {
		return sWidth;
	}

	/**
	 * @param sWidth
	 *            the sWidth to set
	 */
	public void setsWidth(int sWidth) {
		this.sWidth = sWidth;
	}

	/**
	 * º¯Êý×¢ÊÍ
	 * 
	 * @return
	 * @author Zhouls
	 * @date Nov 21, 2016 10:34:52 AM
	 */
	public int getsHeight() {
		return sHeight;
	}

	/**
	 * @param sHeight
	 *            the sHeight to set
	 */
	public void setsHeight(int sHeight) {
		this.sHeight = sHeight;
	}

	public static PhoneManager getInstance() {
		return Inner.instance;
	}

	private static class Inner {
		public static PhoneManager instance = new PhoneManager();
	}
}
