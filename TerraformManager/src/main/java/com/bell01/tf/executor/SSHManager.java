package com.bell01.tf.executor;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class SSHManager {
	private PrintStream listener;
	private JSch jschSSHChannel;
	private String userName;
	private String host;
	private int port;
	private int timeOut;
	private Session sesConnection;
	private boolean isIdBasedAuth;
	private String password;

	/**
	 * Keyfile based authentication
	 * 
	 */
	public SSHManager(String userName, String host, int port, String keyFilePath, String keyPassword, int timeOut) {

		this.userName = userName;
		this.host = host;
		this.port = port;
		this.timeOut = timeOut;

		jschSSHChannel = new JSch();

		try {
			if (keyFilePath != null) {
				if (keyPassword != null) {
					jschSSHChannel.addIdentity(keyFilePath, keyPassword);
				} else {
					jschSSHChannel.addIdentity(keyFilePath);
				}
			}
		} catch (JSchException e) {
			e.printStackTrace();
		}
	}

	/**
	 * password based authentication
	 * 
	 * @param isKeyBasedAuth
	 * @param userName
	 * @param host
	 * @param port
	 * @param password
	 * @param timeOut
	 */
	public SSHManager(boolean isKeyBasedAuth, String userName, String host, int port, String password, int timeOut) {
		this.isIdBasedAuth = isKeyBasedAuth;
		this.userName = userName;
		this.host = host;
		this.port = port;
		this.timeOut = timeOut;
		this.password = password;

		jschSSHChannel = new JSch();

	}

	public Session connect() throws JSchException {

		sesConnection = jschSSHChannel.getSession(userName, host, port);
		sesConnection.setConfig("StrictHostKeyChecking", "no");
		if (this.isIdBasedAuth) {
			sesConnection.setPassword(password);
		}
		sesConnection.connect(timeOut);
		return sesConnection;

	}

	public String sendCommand(String command) throws JSchException, IOException {
		StringBuilder outputBuffer = new StringBuilder();

		Channel channel = sesConnection.openChannel("exec");
		((ChannelExec) channel).setCommand(command);
		InputStream commandOutput = channel.getInputStream();
		channel.connect();
		int readByte = commandOutput.read();

		while (readByte != 0xffffffff) {
			outputBuffer.append((char) readByte);
			readByte = commandOutput.read();
		}

		channel.disconnect();

		return outputBuffer.toString();
	}

	public void close() {
		sesConnection.disconnect();
	}

	public PrintStream getListener() {
		return listener;
	}

	public void setListener(PrintStream listener) {
		this.listener = listener;
	}
}
