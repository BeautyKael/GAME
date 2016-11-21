/*
 * @Title HZhaoyun.java
 * @Copyright Copyright 2010-2015 Careland Software Co,.Ltd All Rights Reserved.
 * @author Zhouls
 * @date Nov 18, 2016 11:50:42 AM
 * @version 1.0
 */
package com.zhouls.threehero.model;

/**
 * ¿‡◊¢ Õ
 * 
 * @author Zhouls
 * @date Nov 18, 2016 11:50:42 AM
 */
public class HZhaoyun extends BaseHero {

	public HZhaoyun() {
		name = "’‘‘∆";
		attr = new BaseAttr();
		attr.hp = 500;
		attr.mp = 300;
		attr.att = 100;
		attr.def = 150;
		level = 1;
		exp = 0;
	}

	/**
	 * @see com.zhouls.threehero.model.IHero#move()
	 */
	@Override
	public void move(int x, int y) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see com.zhouls.threehero.model.IHero#attack()
	 */
	@Override
	public void attack() {
		// TODO Auto-generated method stub

	}

}
