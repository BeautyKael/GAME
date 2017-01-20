/*
 * @Title FreeDownBall.java
 * @Copyright Copyright 2010-2015 Careland Software Co,.Ltd All Rights Reserved.
 * @author Zhouls
 * @date 2017-1-13 下午4:19:12
 * @version 1.0
 */
package com.zhouls.threehero.ui.view.model;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

/**
 * 自由落体小球
 * 
 * @author Zhouls
 * @date 2017-1-13 下午4:19:12
 */
public class FreeDownBall extends DrawBean {

	private int startX, startY;
	private long startUtc;
	private float drawX, drawY;
	private float maxX, maxY;
	private int gy = 10;
	private float t;
	private int r = 10;

	public FreeDownBall(int x, int y) {
		this.startX = x;
		this.startY = y;
		this.startUtc = System.currentTimeMillis() / 100;
	}

	public FreeDownBall(int x, int y, int r, int maxX, int maxY) {
		this.startX = x;
		this.startY = y;
		this.r = r;
		this.startUtc = System.currentTimeMillis() / 100;
		this.maxX = maxX;
		this.maxY = maxY;
	}

	/**
	 * @see com.zhouls.threehero.ui.view.model.DrawBean#logic()
	 */
	@Override
	public void logic() {
		// TODO Auto-generated method stub
		long curUtc = System.currentTimeMillis() / 100;
		if (drawY > maxY || drawX > maxX) {
			// t = (int) (curUtc - startUtc);
			// Log.d("draw", drawX + "," + drawY);
			// drawY = 2 * maxY - (int) (startY + 0.5f * gy * t * t);
			// drawX = startX;
		} else {
			if (drawY <= 0 || drawX <= 0) {
				drawX = startX;
				drawY = startY;
			} else {
				t = (curUtc - startUtc) / 10.0f;
				Log.d("draw", drawX + "," + drawY);
				drawY = (startY + 0.5f * gy * t * t * 5.0f / 1.08f);
				drawX = startX;
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
		mCanvas.drawCircle(drawX, drawY, r, mPaint);
		Log.d("draw", drawX + "," + drawY);
	}
}
