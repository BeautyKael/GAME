/*
 * @Title DrawBean.java
 * @Copyright Copyright 2010-2015 Careland Software Co,.Ltd All Rights Reserved.
 * @author Zhouls
 * @date 2017-1-13 ����4:17:23
 * @version 1.0
 */
package com.zhouls.threehero.ui.view.model;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * ��ע��
 * 
 * @author Zhouls
 * @date 2017-1-13 ����4:17:23
 */
public abstract class DrawBean {

	/**
	 * ִ���߼�
	 * 
	 * @return void
	 * @author Zhouls
	 * @date 2017-1-13 ����4:20:00
	 */
	public abstract void logic();

	/**
	 * ����
	 * 
	 * @param mCanvas
	 * @param mPaint
	 * @return void
	 * @author Zhouls
	 * @date 2017-1-13 ����4:18:29
	 */
	public abstract void draw(Canvas mCanvas, Paint mPaint);

}
