/*
 * @Title LSGameServer.java
 * @Copyright Copyright 2010-2015 Careland Software Co,.Ltd All Rights Reserved.
 * @author Zhouls
 * @date 2016-12-20 ÉÏÎç11:30:57
 * @version 1.0
 */
package com.lsgame.base.model.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import android.text.TextUtils;
import android.util.Log;

/**
 * Àà×¢ÊÍ
 * 
 * @author Zhouls
 * @date 2016-12-20 ÉÏÎç11:30:57
 */
public class LSGameServer {

	public LSGameServer() {

	}

	private boolean isStoped = false;

	public void start() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				ServerSocket serverSocket = null;
				try {
					serverSocket = new ServerSocket(1234);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (null == serverSocket) {
					return;
				}
				while (!isStoped) {
					try {
						Socket socket = serverSocket.accept();
						LSGameServerRunnable thread = new LSGameServerRunnable(
								socket);
						thread.start();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	public class LSGameServerRunnable extends Thread {

		Socket socket;

		public LSGameServerRunnable(Socket socket) {
			this.socket = socket;
		}

		/**
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				InputStream is = socket.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(
						is, "utf-8"));
				String temp;
				while (!TextUtils.isEmpty(temp = br.readLine())) {
					Log.d("LSGameServer", temp);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
