package com.cisco.anutest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientWorker extends Thread
{
	Socket mySocket;
	PrintWriter socketOutput;
	boolean keepRunning = true;
	String command = null;
	
	public static String CD = "cd";
	public static String CLOSE = "close";
	public static String LS = "ls";
	public static String MKDIR = "mkdir";
	public static String PWD = "pwd";
	public static String RMDIR = "rmdir";
	public static String HELP = "help";
	
	public ClientWorker(Socket accept) 
	{
		mySocket = accept;
		try {
			socketOutput = new PrintWriter(mySocket.getOutputStream(),true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run()
	{
		try 
		{			
			socketOutput.println("Waiting for a command...");
			
			FileUtilities util = new FileUtilities(socketOutput);
			
			while (keepRunning)
			{			
				command = readInputStream(mySocket.getInputStream());
				
				if(command.equals(CLOSE))
				{
					keepRunning = false;
					socketOutput.println("Shutting down thread...");
				}
				else if(command.equals(PWD))				
					util.listCurrentPath();				
				else if(command.equals(LS))				
					util.listFolderContents();				
				else if (command.startsWith(MKDIR))
				{
					String newFolderName = (command.substring(MKDIR.length(), command.length())).trim();
					util.makeFolder(newFolderName);
				}	
				else if (command.startsWith(RMDIR))
				{
					String newFolderName = (command.substring(RMDIR.length(), command.length())).trim();
					util.deleteFolder(newFolderName);
				}	
				else if (command.startsWith(CD))
				{
					String newPath = (command.substring(CD.length(), command.length())).trim();
					util.setCurrentDirectory(newPath);
					util = new FileUtilities(socketOutput);
				}
				else if (command.equals(HELP))
				{
					socketOutput.println("Supported commands are:");
					socketOutput.println(PWD + " - show working path");
					socketOutput.println(MKDIR + " - create folder");
					socketOutput.println(RMDIR + " - delete folder");				
					socketOutput.println(LS + " - list directory contents");
					socketOutput.println(CD + " - change directory");
					socketOutput.println(CLOSE + " - close connection");
				}
				else
					socketOutput.println("Unrecognised command: " + command);					
			}
		} 
		catch (Exception e) 
		{
			socketOutput.println(e.getMessage());
		}
		finally
		{
			try {
				mySocket.close();
			} catch (Exception e) {				
				socketOutput.println(e.getMessage());
			}
		}
		
	}

	private static String readInputStream (InputStream stream) throws IOException
	{
		String streamText = null;
		BufferedReader buffer = null;
		try
		{
			buffer = new BufferedReader (new InputStreamReader (stream));
			streamText = buffer.readLine();
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return streamText;
	}
}
