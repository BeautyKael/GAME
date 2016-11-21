/*
 * @Title BaseController.java
 * @Copyright Copyright 2010-2015 Careland Software Co,.Ltd All Rights Reserved.
 * @author Zhouls
 * @date Nov 21, 2016 8:35:09 AM
 * @version 1.0
 */
package com.zhouls.threehero.controller.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.zhouls.threehero.R;
import com.zhouls.threehero.controller.IController;

/**
 * »ù´¡¿ØÖÆÆ÷
 * 
 * @author Zhouls
 * @date Nov 21, 2016 8:35:09 AM
 */
public class BaseControllerView extends RelativeLayout implements
		OnClickListener {
	private IController listener;
	private static final String TAG = "BaseControllerView";
	private Button btnLeft;
	private Button btnRight;
	private Button btnAttack;
	private Button btnJump;

	private void init(Context context) {
		LayoutInflater layoutInflater = LayoutInflater.from(context);
		View view = layoutInflater.inflate(R.layout.lib_controller, null);
		btnLeft = (Button) view.findViewById(R.id.lib_controller_left);
		btnRight = (Button) view.findViewById(R.id.lib_controller_right);
		btnAttack = (Button) view.findViewById(R.id.lib_controller_attack);
		btnJump = (Button) view.findViewById(R.id.lib_controller_jump);
		addView(view);
		btnLeft.setOnClickListener(this);
		btnRight.setOnClickListener(this);
		btnAttack.setOnClickListener(this);
		btnJump.setOnClickListener(this);
	}

	public void setOnControlFuctionListener(IController listener) {
		this.listener = listener;
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public BaseControllerView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init(context);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public BaseControllerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context);
	}

	/**
	 * @param context
	 */
	public BaseControllerView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context);
	}

	private float onMoveLeft() {
		// TODO Auto-generated method stub
		Log.d(TAG, "onMoveLeft");
		if (null != listener) {
			listener.onMoveLeft();
		}
		return 0;
	}

	private float onMoveRight() {
		// TODO Auto-generated method stub
		Log.d(TAG, "onMoveRight");
		if (null != listener) {
			listener.onMoveRight();
		}
		return 0;
	}

	private float onJump() {
		// TODO Auto-generated method stub
		Log.d(TAG, "onJump");
		if (null != listener) {
			listener.onJump();
		}
		return 0;
	}

	private boolean onAttack() {
		// TODO Auto-generated method stub
		Log.d(TAG, "onAttack");
		if (null != listener) {
			listener.onAttack();
		}
		return false;
	}

	/**
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == btnLeft) {
			onMoveLeft();
		} else if (v == btnRight) {
			onMoveRight();
		} else if (v == btnAttack) {
			onAttack();
		} else if (v == btnJump) {
			onJump();
		}
	}
}
