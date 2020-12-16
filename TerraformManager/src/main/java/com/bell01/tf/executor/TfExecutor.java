package com.bell01.tf.executor;

import com.jcraft.jsch.JSchException;

public class TfExecutor {

	public static void main(String[] args) throws InterruptedException {
		
		SSHManager sm = new SSHManager(true, "sshuser", "172.16.0.11", 22, "password", 300);
		try {
			sm.connect();
			Thread.sleep(3000);
			sm.close();
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
