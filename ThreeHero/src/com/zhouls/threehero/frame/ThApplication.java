/*
 * @Title ThApplication.java
 * @Copyright Copyright 2010-2015 Careland Software Co,.Ltd All Rights Reserved.
 * @author Zhouls
 * @date Nov 21, 2016 10:29:35 AM
 * @version 1.0
 */
package com.zhouls.threehero.frame;

import android.app.Application;

import com.zhouls.threehero.manager.LSAssertManager;
import com.zhouls.threehero.ui.utils.PhoneManager;

/**
 * ¿‡◊¢ Õ
 * 
 * @author Zhouls
 * @date Nov 21, 2016 10:29:35 AM
 */
public class ThApplication extends Application {
	/**
	 * @see android.app.Application#onCreate()
	 */
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		// ≥ı ºªØphone
		PhoneManager.getInstance().init(this);
		LSAssertManager.init(this);
	}
}
