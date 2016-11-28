/*
 * @Title HotSpotManager.java
 * @Copyright Copyright 2010-2015 Careland Software Co,.Ltd All Rights Reserved.
 * @author Zhouls
 * @date Nov 21, 2016 10:21:52 AM
 * @version 1.0
 */
package com.zhouls.threehero.ui.manager;

import java.io.IOException;

import com.zhouls.threehero.ui.utils.PhoneManager;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

/**
 * Àà×¢ÊÍ
 * 
 * @author Zhouls
 * @date Nov 21, 2016 10:21:52 AM
 */
public class HotSpotManager {
	public HotSpot hotSpot;

	private HotSpotManager() {
		hotSpot = new HotSpot();
		hotSpot.x = 150;
		hotSpot.y = PhoneManager.getInstance().getsWidth() / 2;
		hotSpot.txt = "example";
		try {
			hotSpot.img = BitmapFactory.decodeStream(PhoneManager.getInstance()
					.getmContext().getAssets().open("60500.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static HotSpotManager getInstance() {
		return Inner.instance;
	}

	public void drawSpot(Canvas mCanvas, Paint mPaint) {
		mCanvas.drawText(hotSpot.txt, hotSpot.x, hotSpot.y, mPaint);
		mCanvas.drawBitmap(hotSpot.img, hotSpot.x, hotSpot.y, mPaint);
	}

	private static class Inner {
		public static HotSpotManager instance = new HotSpotManager();
	}

}
