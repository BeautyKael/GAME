/*
 * @Title ControlSprite.java
 * @Copyright Copyright 2010-2015 Careland Software Co,.Ltd All Rights Reserved.
 * @author Zhouls
 * @date 2017-1-20 ÉÏÎç11:28:31
 * @version 1.0
 */
package com.zhouls.threehero.ui.view.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.Log;

/**
 * Àà×¢ÊÍ
 * 
 * @author Zhouls
 * @date 2017-1-20 ÉÏÎç11:28:31
 */
public class ControlSprite extends DrawBean {

	private float lR = 300;
	private float rR = 300;
	private float cR = 20;
	private float lx, ly;
	private float rx, ry;
	private float cx, cy;

	public ControlSprite(float lx, float ly, float rx, float ry) {
		this.lx = lx;
		this.ly = ly;
		this.rx = rx;
		this.ry = ry;
	}

	public void onTouch(float x, float y) {
		Log.d("touch", x + "," + y);
		this.cx = x;
		this.cy = y;
		if (cx > 0
				&& cy > 0
				&& Math.sqrt((cx - lx) * (cx - lx) + (cy - ly) * (cy - ly)) >= lR) {
			double rang = Math.atan(Math.abs((cy - ly) * 0.1f / (cx - lx)));
			Log.d("rang",
					rang + "," + lR * Math.cos(rang) + "," + lR
							* Math.sin(rang));
			if (cx > lx) {
				cx = (float) (lx + lR * Math.cos(rang));
			} else {
				cx = (float) (lx - lR * Math.cos(rang));
			}
			if (cy > ly) {
				cy = (float) (ly + lR * Math.sin(rang));
			} else {
				cy = (float) (ly - lR * Math.sin(rang));
			}
		}
	}

	/**
	 * @see com.zhouls.threehero.ui.view.model.DrawBean#logic()
	 */
	@Override
	public void logic() {
		// TODO Auto-generated method stub

	}

	/**
	 * @see com.zhouls.threehero.ui.view.model.DrawBean#draw(android.graphics.Canvas,
	 *      android.graphics.Paint)
	 */
	@Override
	public void draw(Canvas mCanvas, Paint mPaint) {
		// TODO Auto-generated method stub
		mPaint.setColor(Color.RED);
		mPaint.setStyle(Style.STROKE);
		mCanvas.drawCircle(lx, ly, lR, mPaint);
		// mPaint.setColor(Color.BLUE);
		// mCanvas.drawCircle(rx, ry, rR, mPaint);
		if (cx > 0 && cy > 0) {
			mPaint.setColor(Color.BLACK);
			mPaint.setStyle(Style.FILL);
			mCanvas.drawCircle(cx, cy, cR, mPaint);
			mCanvas.drawLine(lx, ly, cx, cy, mPaint);
		}
		mPaint.reset();
	}

}
