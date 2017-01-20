/*
 * @Title AnimSprite.java
 * @Copyright Copyright 2010-2015 Careland Software Co,.Ltd All Rights Reserved.
 * @author Zhouls
 * @date 2017-1-20 ÉÏÎç9:09:20
 * @version 1.0
 */
package com.zhouls.threehero.ui.view.model;

import com.zhouls.threehero.manager.LSAssertManager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Àà×¢ÊÍ
 * 
 * @author Zhouls
 * @date 2017-1-20 ÉÏÎç9:09:20
 */
public class AnimSprite extends DrawBean {

	private Bitmap mDrawBitmap;
	private int width;
	private int height;

	public AnimSprite() {
		mDrawBitmap = BitmapFactory.decodeStream(LSAssertManager
				.getInputStream("erwt.jpg"));
		width = mDrawBitmap.getWidth() / 12;
		height = mDrawBitmap.getHeight() / 2;
	}

	int step = 0;

	int count;

	/**
	 * @see com.zhouls.threehero.ui.view.model.DrawBean#logic()
	 */
	@Override
	public void logic() {
		// TODO Auto-generated method stub
		count++;
		if (count >= 10) {
			count = 0;
			step++;
			if (step >= 12) {
				step = 0;
			}
		}
	}

	/**
	 * @see com.zhouls.threehero.ui.view.model.DrawBean#draw(android.graphics.Canvas,
	 *      android.graphics.Paint)
	 */
	@Override
	public void draw(Canvas mCanvas, Paint mPaint) {
		// TODO Auto-generated method stub
		logic();
		mCanvas.save();
		mCanvas.clipRect(50, 50, 50 + width, 50 + height);
		mCanvas.drawBitmap(mDrawBitmap, width * (step - 1), 0, mPaint);
		mCanvas.restore();
	}
}
