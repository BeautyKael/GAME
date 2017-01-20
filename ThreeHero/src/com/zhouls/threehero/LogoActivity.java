/*
 * @Title LogoActivity.java
 * @Copyright Copyright 2010-2015 Careland Software Co,.Ltd All Rights Reserved.
 * @author Zhouls
 * @date 2016-12-9 ÏÂÎç4:05:56
 * @version 1.0
 */
package com.zhouls.threehero;

import android.app.Activity;
import android.content.Intent;
import android.gesture.GestureOverlayView;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

/**
 * Àà×¢ÊÍ
 * 
 * @author Zhouls
 * @date 2016-12-9 ÏÂÎç4:05:56
 */
public class LogoActivity extends Activity {
	/**
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */

	GestureOverlayView gv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logo);
		// turn();
	}

	protected void turn() {
		// ImageView imgLogo = (ImageView) findViewById(R.id.logo);
		// imgLogo.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		// Intent intent = new Intent();
		// intent.setClass(LogoActivity.this, MainActivity.class);
		// startActivity(intent);
		// }
		// });
	}
}
