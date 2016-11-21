/*
 * @Title BGSevenColor.java
 * @Copyright Copyright 2010-2015 Careland Software Co,.Ltd All Rights Reserved.
 * @author Zhouls
 * @date Nov 18, 2016 2:22:04 PM
 * @version 1.0
 */
package com.zhouls.threehero.model.bg;

import android.graphics.Color;

/**
 * Àà×¢ÊÍ
 * 
 * @author Zhouls
 * @date Nov 18, 2016 2:22:04 PM
 */
public class BGSevenColor {

	public static int color(int ins) {
		int color = 0;
		switch (ins % 7) {
		case 0:
			color = Color.rgb(255, 0, 0);
			break;
		case 1:
			color = Color.rgb(255, 165, 0);
			break;
		case 2:
			color = Color.rgb(255, 255, 0);
			break;
		case 3:
			color = Color.rgb(0, 255, 0);
			break;
		case 4:
			color = Color.rgb(0, 127, 255);
			break;
		case 5:
			color = Color.rgb(0, 0, 255);
			break;
		case 6:
			color = Color.rgb(139, 0, 255);
			break;
		}
		return color;
	}
}
