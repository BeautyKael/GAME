/*
 * @Title MainSurfaceView.java
 * @Copyright Copyright 2010-2015 Careland Software Co,.Ltd All Rights Reserved.
 * @author Zhouls
 * @date Nov 18, 2016 11:58:19 AM
 * @version 1.0
 */
package com.zhouls.threehero.ui.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
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
import android.view.View.OnTouchListener;

import com.zhouls.threehero.ui.view.model.AnimSprite;
import com.zhouls.threehero.ui.view.model.ControlSprite;
import com.zhouls.threehero.ui.view.model.DrawBean;
import com.zhouls.threehero.ui.view.model.FreeDownBall;

/**
 * 主视图
 * 
 * @author Zhouls
 * @date Nov 18, 2016 11:58:19 AM
 */
public class LSGameSurfaceView extends SurfaceView implements
		SurfaceHolder.Callback, OnTouchListener {
	private DrawThread drawThread;
	private FpsThread fpsThread;
	private SurfaceHolder surfaceHolder;

	private int sH, sW;
	private float fpsx, fpsy;

	private List<DrawBean> lstOfDrawBean;

	ControlSprite cont;

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
		setOnTouchListener(this);
		lstOfDrawBean = new ArrayList<DrawBean>();
	}

	private void drawLst(Canvas mCanvas, Paint mPaint) {
		for (int i = 0; i < lstOfDrawBean.size(); i++) {
			if (null != lstOfDrawBean.get(i)) {
				lstOfDrawBean.get(i).draw(mCanvas, mPaint);
			}
		}
	}

	/**
	 * @param context
	 */
	public LSGameSurfaceView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context);
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public LSGameSurfaceView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init(context);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public LSGameSurfaceView(Context context, AttributeSet attrs) {
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
		sW = this.getWidth();
		sH = this.getHeight();
		fpsx = 50;
		fpsy = sH - 50;
		drawThread = new DrawThread();
		drawThread.start();
		fpsThread = new FpsThread();
		fpsThread.start();
		FreeDownBall ball = new FreeDownBall(200, 200, 30, sH, sW);
		lstOfDrawBean.add(ball);
		AnimSprite anim = new AnimSprite();
		lstOfDrawBean.add(anim);

		cont = new ControlSprite(500, 500, 300, 600);
		lstOfDrawBean.add(cont);
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

	private class FpsThread extends Thread {
		/**
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			while (!drawThread.isDestroyed) {
				drawThread.fps = drawThread.count;
				drawThread.count = 0;
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	}

	private class DrawThread extends Thread {

		public boolean isDestroyed = false;
		private Paint mPaint;
		private Canvas mCanvas;
		public int fps = 0;
		public int count = 0;

		public DrawThread() {
			mPaint = new Paint();
			mPaint.setAntiAlias(true);
		}

		/**
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			while (!isDestroyed) {
				count++;
				try {
					if (null != surfaceHolder) {
						mCanvas = surfaceHolder.lockCanvas();
						draw();
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
					// 相当于锁帧了200
					Thread.sleep(5);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}

		/**
		 * 绘制
		 * 
		 * @return void
		 * @author Zhouls
		 * @date 2016-12-29 下午3:32:35
		 */
		private void draw() {
			mCanvas.drawColor(Color.WHITE);
			mPaint.setTextSize(25);
			mPaint.setFakeBoldText(true);
			mPaint.setColor(Color.RED);
			mCanvas.drawText(fps + "", fpsx, fpsy, mPaint);
			drawLst(mCanvas, mPaint);
		}
	}

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
			cont.onTouch((int) event.getX(), (int) event.getY());
			break;
		case MotionEvent.ACTION_POINTER_DOWN:
			int dis = (int) spacing(
					new Point((int) event.getX(0), (int) event.getY(0)),
					new Point((int) event.getX(1), (int) event.getY(1)));
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
			cont.onTouch(0, 0);
			break;
		case MotionEvent.ACTION_MOVE:
			cont.onTouch((int) event.getX(), (int) event.getY());
			if (mode == ZOOM) {
				// Point lp = new Point((int) event.getX(0),
				// (int) event.getY(0));
				// Point rp = new Point((int) event.getX(1),
				// (int) event.getY(1));
				// int curDis = (int) spacing(lp, rp);
				// if (Math.abs(curDis - lastDis) > 20) {
				// float scale = curDis / (lastDis * 0.1f * 10);
				// Log.d("debug", scale + "");
				// matrix.postScale(scale, scale, (lp.x + rp.x) / 2,
				// (lp.y + rp.y) / 2);
				// }
			} else if (mode == DRAG) {
				if (null == lastP) {
					lastP = new Point();
					lastP.set(downP.x, downP.y);
				}
				float dx = event.getX() - lastP.x;
				float dy = event.getY() - lastP.y;
				if (Math.abs(dx) >= 20 || Math.abs(dy) >= 20) {
					lastP.set((int) event.getX(), (int) event.getY());
					// AlphaAnimation alphaAnim = new AlphaAnimation(0.0f,
					// 0.5f);
					// this.startAnimation(alphaAnim);
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
