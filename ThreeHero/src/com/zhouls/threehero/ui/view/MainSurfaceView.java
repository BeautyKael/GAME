/*
 * @Title MainSurfaceView.java
 * @Copyright Copyright 2010-2015 Careland Software Co,.Ltd All Rights Reserved.
 * @author Zhouls
 * @date Nov 18, 2016 11:58:19 AM
 * @version 1.0
 */
package com.zhouls.threehero.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.zhouls.threehero.ui.manager.HotSpotManager;
import com.zhouls.threehero.ui.utils.PhoneManager;

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
	private Matrix matrix;

	private int sH, sW;

	/**
	 * 初始化
	 * 
	 * @param context
	 * @return void
	 * @author Zhouls
	 * @date Nov 18, 2016 12:00:52 PM
	 */
	private void init(Context context) {
		surfaceHolder = getHolder();
		surfaceHolder.addCallback(this);
		sH = PhoneManager.getInstance().getsHeight();
		sW = PhoneManager.getInstance().getsWidth();
		matrix = new Matrix();
		setOnTouchListener(new THTouchListener());
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
		Log.d("debug", "surfaceDestroyed");
		drawThread = new DrawThread();
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
		Log.d("debug", "surfaceChanged");
	}

	/**
	 * @see android.view.SurfaceHolder.Callback#surfaceDestroyed(android.view.SurfaceHolder)
	 */
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		Log.d("debug", "surfaceDestroyed");
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
						if (null == mCanvas) {
							continue;
						}
						mCanvas.setMatrix(matrix);
						mCanvas.drawColor(Color.WHITE);
						mPaint.setTextSize(25);
						mPaint.setFakeBoldText(true);
						mPaint.setColor(Color.RED);
						mCanvas.drawLine(0, sW - 200, sH - 20, sW - 200, mPaint);
						mCanvas.drawLine(0, sW - 185, sH - 20, sW - 185, mPaint);
						mCanvas.drawRect(20, sW - 200, sH - 20, sW - 195,
								mPaint);
						mPaint.setColor(Color.BLUE);
						for (int i = 0; i < 11; i++) {
							int len = sH - 40;
							float pos = i * len / 11.0f + 20;
							mCanvas.drawRect(pos, sW - 210, pos + 5, sW - 200,
									mPaint);
							mCanvas.drawText("" + i, pos - 5, sW - 150, mPaint);
						}
						mCanvas.drawRect(sH - 25, sW - 210, sH - 20, sW - 200,
								mPaint);
						mCanvas.drawText(12 + "", sH - 30, sW - 150, mPaint);
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

	/**
	 * 
	 * 触摸事件
	 * 
	 * @author Zhouls
	 * @date Nov 25, 2016 11:41:54 AM
	 */
	private class THTouchListener implements OnTouchListener {
		// 初始状态
		public static final int NONE = 0;
		// 拖拽
		public static final int DRAG = 1;
		// 缩放
		public static final int ZOOM = 2;

		public static final float MAX_SCALE = 1.25f;
		public static final float MIN_SCALE = 0.75f;

		// 初始状态
		private int mode = NONE;
		private Point downP;
		private Point lastP;
		private int lastDis;

		public THTouchListener() {
		}

		/**
		 * @see android.view.View.OnTouchListener#onTouch(android.view.View,
		 *      android.view.MotionEvent)
		 */
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			v.onTouchEvent(event);
			int action = MotionEventCompat.getActionMasked(event);
			switch (action) {
			case MotionEvent.ACTION_DOWN:
				// 记录按下坐标
				mode = DRAG;
				downP = new Point();
				downP.set((int) event.getX(), (int) event.getY());
				break;
			case MotionEvent.ACTION_POINTER_DOWN:
				int dis = (int) spacing(new Point((int) event.getX(0),
						(int) event.getY(0)), new Point((int) event.getX(1),
						(int) event.getY(1)));
				if (dis > 100) {
					mode = ZOOM;
					lastDis = dis;
				}
				break;
			case MotionEvent.ACTION_POINTER_UP:
				mode = NONE;
				break;
			case MotionEvent.ACTION_UP:
				mode = NONE;
				downP = null;
				lastP = null;
				break;
			case MotionEvent.ACTION_MOVE:
				if (mode == ZOOM) {
					Point lp = new Point((int) event.getX(0),
							(int) event.getY(0));
					Point rp = new Point((int) event.getX(1),
							(int) event.getY(1));
					int curDis = (int) spacing(lp, rp);
					if (Math.abs(curDis - lastDis) > 20) {
						float scale = curDis / (lastDis * 0.1f * 10);
						Log.d("debug", scale + "");
						if (scale >= MIN_SCALE && scale <= MAX_SCALE) {
							matrix.postScale(scale, scale, (lp.x + rp.x) / 2,
									(lp.y + rp.y) / 2);
						}
					}
				} else if (mode == DRAG) {
					if (null == lastP) {
						lastP = new Point();
						lastP.set(downP.x, downP.y);
					}
					int dx = (int) (event.getX() - lastP.x);
					int dy = (int) (event.getY() - lastP.y);
					if (Math.abs(dx) >= 20 || Math.abs(dy) >= 20) {
						matrix.postTranslate(dx, dy);
						lastP.set((int) event.getX(), (int) event.getY());
					}
				}
				break;

			default:
				break;
			}
			return true;
		}

		/**
		 * 算2点距离
		 * 
		 * @param p1
		 * @param p2
		 * @return
		 * @return float
		 * @author Zhouls
		 * @date Nov 25, 2016 12:09:28 PM
		 */
		private float spacing(Point p1, Point p2) {
			float x = p1.x - p2.x;
			float y = p1.y - p2.y;
			return FloatMath.sqrt(x * x + y * y);
		}
	}
}
