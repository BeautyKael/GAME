/*
 * @Title LSTask.java
 * @Copyright Copyright 2010-2015 Careland Software Co,.Ltd All Rights Reserved.
 * @author Zhouls
 * @date Nov 28, 2016 4:15:53 PM
 * @version 1.0
 */
package com.lsgame.base.model;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 任务管理器 后续添加队列机制
 * 
 * @author Zhouls
 * @date Nov 28, 2016 4:15:53 PM
 */
public class LSTask {
	private static ExecutorService threadPoolExecutor = Executors
			.newCachedThreadPool();

	public static void add(Runnable command) {
		threadPoolExecutor.execute(command);
	}
}
