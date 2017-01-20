/*
 * @Title LSAssertManager.java
 * @Copyright Copyright 2010-2015 Careland Software Co,.Ltd All Rights Reserved.
 * @author Zhouls
 * @date 2017-1-20 上午9:11:54
 * @version 1.0
 */
package com.zhouls.threehero.manager;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;

/**
 * 资源管理
 * 
 * @author Zhouls
 * @date 2017-1-20 上午9:11:54
 */
public class LSAssertManager {

	public static Context mContext;

	public static void init(Context appCtx) {
		mContext = appCtx;
	}

	public static InputStream getInputStream(String fileName) {
		if (null == mContext) {
			return null;
		}
		try {
			return mContext.getAssets().open(fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
