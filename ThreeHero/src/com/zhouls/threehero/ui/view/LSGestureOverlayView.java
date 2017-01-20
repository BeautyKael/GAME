/*
 * @Title LSGestureView.java
 * @Copyright Copyright 2010-2015 Careland Software Co,.Ltd All Rights Reserved.
 * @author Zhouls
 * @date 2017-1-10 上午11:05:51
 * @version 1.0
 */
package com.zhouls.threehero.ui.view;

import java.io.File;
import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGestureListener;
import android.gesture.Prediction;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.zhouls.threehero.R;

/**
 * 手势View
 * 
 * @author Zhouls
 * @date 2017-1-10 上午11:05:51
 */
public class LSGestureOverlayView extends RelativeLayout {

	private GestureOverlayView mGestureView;
	private ImageView mShowImageView;
	GestureLibrary gestureLib;
	Bitmap ivBitmap;
	int i = 0;
	private int layout_width;
	private int layout_height;

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public LSGestureOverlayView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init(context);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public LSGestureOverlayView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context);
	}

	/**
	 * @param context
	 */
	public LSGestureOverlayView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context);
	}

	private void init(Context context) {
		setWillNotDraw(false);
		String path = Environment.getExternalStorageDirectory() + "/gesture/";
		File filePath = new File(path);
		if (!filePath.exists()) {
			filePath.mkdirs();
		}
		File file = new File(path + "gesture.lib");
		gestureLib = GestureLibraries.fromFile(file);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		LayoutInflater layoutInflater = LayoutInflater.from(context);
		View v = layoutInflater.inflate(R.layout.layout_gesture_view, null);
		mGestureView = (GestureOverlayView) v
				.findViewById(R.id.gv_draw_gesture);
		mShowImageView = (ImageView) v.findViewById(R.id.iv_show_gesture);
		mGestureView.addOnGestureListener(new OnGestureListener() {

			@Override
			public void onGestureStarted(GestureOverlayView overlay,
					MotionEvent event) {
				// TODO Auto-generated method stub
				mShowImageView.setVisibility(View.INVISIBLE);
			}

			@Override
			public void onGestureEnded(GestureOverlayView overlay,
					MotionEvent event) {
				// TODO Auto-generated method stub
				Gesture gesture = overlay.getGesture();
				ivBitmap = gesture.toBitmap(320, 720, 0, Color.BLUE);
				mShowImageView.setImageBitmap(ivBitmap);
				mShowImageView.setVisibility(View.VISIBLE);
				gestureLib.addGesture(++i + "", gesture);
				gestureLib.save();
				List<Prediction> lstOfPrediction = gestureLib
						.recognize(gesture);
				Log.d("gesture", lstOfPrediction.size() + "");
				for (Prediction temp : lstOfPrediction) {
					if (temp.score > 6) {
						Log.d("gesture", temp.name + "," + temp.score + "");
					}
				}
			}

			@Override
			public void onGestureCancelled(GestureOverlayView overlay,
					MotionEvent event) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onGesture(GestureOverlayView overlay, MotionEvent event) {
				// TODO Auto-generated method stub

			}
		});
		addView(v);
	}

	/**
	 * @see android.widget.RelativeLayout#onMeasure(int, int)
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		layout_height = getHeight();
		layout_width = getWidth();
	}

	/**
	 * @see android.widget.RelativeLayout#onLayout(boolean, int, int, int, int)
	 */
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		super.onLayout(changed, l, t, r, b);
	}

	/**
	 * @see android.view.View#onDraw(android.graphics.Canvas)
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
	}
}
