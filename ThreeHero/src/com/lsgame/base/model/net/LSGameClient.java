/*
 * @Title LSGameClient.java
 * @Copyright Copyright 2010-2015 Careland Software Co,.Ltd All Rights Reserved.
 * @author Zhouls
 * @date 2016-12-20 ÏÂÎç12:16:01
 * @version 1.0
 */
package com.lsgame.base.model.net;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Àà×¢ÊÍ
 * 
 * @author Zhouls
 * @date 2016-12-20 ÏÂÎç12:16:01
 */
public class LSGameClient {
	private String name = "default";

	public LSGameClient(String name) {
		this.name = name;
	}

	private String ip;

	public void request() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				OutputStream os = null;
				BufferedWriter bw = null;
				try {
					InetAddress inetAddress = InetAddress.getLocalHost();
					Socket socket = new Socket(inetAddress, 1234);
					os = socket.getOutputStream();
					bw = new BufferedWriter(new OutputStreamWriter(os, "utf-8"));
					bw.write("start\n");
					bw.write(name + "\n");
					bw.write("end\n");
					bw.flush();
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					if (null != os) {
						try {
							os.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if (null != bw) {
						try {
							bw.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}).start();
	}
}
