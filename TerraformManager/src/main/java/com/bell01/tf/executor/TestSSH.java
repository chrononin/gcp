package com.bell01.tf.executor;

import java.io.ByteArrayOutputStream;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class TestSSH {
	
	public static void main(String args[]) throws JSchException, InterruptedException {
		Session session = null;
	    ChannelExec channel = null;
	    
	    try {
	        session = new JSch().getSession("sshuser", "172.16.0.11", 22);
	        session.setPassword("password");
	        session.setConfig("StrictHostKeyChecking", "no");
	        session.connect();
	        
	        channel = (ChannelExec) session.openChannel("exec");
	        channel.setCommand("ls -lart");
	        ByteArrayOutputStream responseStream = new ByteArrayOutputStream();
	        channel.setOutputStream(responseStream);
	        channel.connect();
	        
	        while (channel.isConnected()) {
	            Thread.sleep(100);
	        }
	        
	        String responseString = new String(responseStream.toByteArray());
	        System.out.println(responseString);
	    } finally {
	        if (session != null) {
	            session.disconnect();
	        }
	        if (channel != null) {
	            channel.disconnect();
	        }
	    }
	}

}
