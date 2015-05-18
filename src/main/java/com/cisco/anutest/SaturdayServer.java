package com.cisco.anutest;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SaturdayServer 
{
	ServerSocket myListener;
	
	public static void main (String args[])
	{
		new SaturdayServer();	
	}
	
	public SaturdayServer()
	{
		try
		{
			myListener = new ServerSocket(4444);
			System.out.println("I'm listening on port 4444...!");
		}
		catch (IOException e) 
		{
			System.out.println("Could not listen on port 4444");
			System.exit(-1);
		}
		while(true)
		{
			try
			{
				Socket thisSocket = myListener.accept();			
				
				ClientWorker worker = new ClientWorker(thisSocket);
				worker.start();
			} 
			catch (IOException e) 
			{
				System.out.println("Accept failed: 4444");
				System.exit(-1);
			}
		}
	}
}
