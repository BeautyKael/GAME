/*
 * @Title MainSurfaceView.java
 * @Copyright Copyright 2010-2015 Careland Software Co,.Ltd All Rights Reserved.
 * @author Zhouls
 * @date Nov 18, 2016 11:58:19 AM
 * @version 1.0
 */
package com.zhouls.threehero.ui.view;

import com.zhouls.threehero.ui.manager.HotSpotManager;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

/**
 * 主视图
 * 
 * @author Zhouls
 * @date Nov 18, 2016 11:58:19 AM
 */
public class MainSurfaceView extends SurfaceView implements
		SurfaceHolder.Callback {
	private DrawThread drawThread;
	private SurfaceHolder surfaceHolder;

	private int sWidth, sHeight;

	/**
	 * 初始化
	 * 
	 * @param context
	 * @return void
	 * @author Zhouls
	 * @date Nov 18, 2016 12:00:52 PM
	 */
	private void init(Context context) {
		drawThread = new DrawThread();
		surfaceHolder = getHolder();
		surfaceHolder.addCallback(this);
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		sWidth = wm.getDefaultDisplay().getWidth();
		sHeight = wm.getDefaultDisplay().getHeight();
	}

	/**
	 * @param context
	 */
	public MainSurfaceView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context);
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public MainSurfaceView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init(context);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public MainSurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context);
	}

	/**
	 * @see android.view.SurfaceHolder.Callback#surfaceCreated(android.view.SurfaceHolder)
	 */
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		drawThread.start();
	}

	/**
	 * @see android.view.SurfaceHolder.Callback#surfaceChanged(android.view.SurfaceHolder,
	 *      int, int, int)
	 */
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see android.view.SurfaceHolder.Callback#surfaceDestroyed(android.view.SurfaceHolder)
	 */
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		drawThread.isDestroyed = true;

	}

	private class DrawThread extends Thread {

		public boolean isDestroyed = false;
		private Paint mPaint;
		private Canvas mCanvas;

		public DrawThread() {
			mPaint = new Paint();
		}

		/**
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			int count = 0;
			while (!isDestroyed) {
				try {
					if (null != surfaceHolder) {
						mCanvas = surfaceHolder.lockCanvas();
						mCanvas.drawColor(Color.WHITE);
						mPaint.setTextSize(25);
						mPaint.setFakeBoldText(true);
						mPaint.setColor(Color.RED);
						// mCanvas.drawLine(0, sHeight - 200, sWidth - 20,
						// sHeight - 200, mPaint);
						// mCanvas.drawLine(0, sHeight - 185, sWidth - 20,
						// sHeight - 185, mPaint);
						mCanvas.drawRect(20, sHeight - 200, sWidth - 20,
								sHeight - 195, mPaint);
						mPaint.setColor(Color.BLUE);
						for (int i = 0; i < 11; i++) {
							int len = sWidth - 40;
							float pos = i * len / 11.0f + 20;
							mCanvas.drawRect(pos, sHeight - 210, pos + 5,
									sHeight - 200, mPaint);
							mCanvas.drawText("" + i, pos - 5, sHeight - 150,
									mPaint);
						}
						mCanvas.drawRect(sWidth - 25, sHeight - 210,
								sWidth - 20, sHeight - 200, mPaint);
						mCanvas.drawText(12 + "", sWidth - 30, sHeight - 150,
								mPaint);
						// 绘制热区
						HotSpotManager.getInstance().drawSpot(mCanvas, mPaint);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if (null != surfaceHolder) {
							surfaceHolder.unlockCanvasAndPost(mCanvas);
						}
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
				try {
					Thread.sleep(1);
				} catch (Exception e) {
					// TODO: handle exception
				}
				count++;
			}
		}
	}
}
