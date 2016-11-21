/*
 * @Title IController.java
 * @Copyright Copyright 2010-2015 Careland Software Co,.Ltd All Rights Reserved.
 * @author Zhouls
 * @date Nov 21, 2016 8:30:54 AM
 * @version 1.0
 */
package com.zhouls.threehero.controller;

/**
 * Àà×¢ÊÍ
 * 
 * @author Zhouls
 * @date Nov 21, 2016 8:30:54 AM
 */
public interface IController {

	public float onMoveLeft();

	public float onMoveRight();

	public float onJump();

	public boolean onAttack();

	public int onReleaseSkill();

}
