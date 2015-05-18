package com.cisco.anutest;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class FileUtilities 
{
	File thisFolder = null;
	PrintWriter thisOutput;
	
	public FileUtilities(PrintWriter output)
	{
		thisFolder = new File(".");
		thisOutput = output;
	}
		
	public void listCurrentPath()
	{
		try {
			thisOutput.println("Current path is: " + thisFolder.getCanonicalPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void listFolderContents()
	{
		thisOutput.println("This folder contains: ");
		String [] list = thisFolder.list();
		for(int i = 0; i < list.length; i++)
		{
			thisOutput.println(list[i]);
		}
	}
	
	public void setCurrentDirectory(String newPath)
	{
		if(newPath.isEmpty())
			thisOutput.println("Please specify a new path, eg cd C:"+System.getProperty("file.separator")+"Documents");
		else
		{
			File newDir = new File (newPath);
			System.setProperty("user.dir", newDir.getAbsolutePath());
			thisFolder = new File(newDir.getAbsolutePath());
			listCurrentPath();
		}
	}
	public void deleteFolder(String folderToDelete)
	{
		if(folderToDelete.isEmpty())
			thisOutput.println("Please specify a folder name, eg rmdir oldFolder");
		else
		{
			File newFolder = new File(folderToDelete);
			boolean success = newFolder.delete();
			if(success)
				thisOutput.println("Deleted folder " + folderToDelete);
			else
				thisOutput.println("There was an error deleting the folder " + folderToDelete);
		}
	}			
	
	public void makeFolder(String newFolderName)
	{
		if(newFolderName.isEmpty())
			thisOutput.println("Please specify a folder name, eg mkdir newFolder");
		else
		{
			File newFolder = new File(newFolderName);
			boolean success = newFolder.mkdir();
			if(success)
				thisOutput.println("Created folder " + newFolderName);
			else
				thisOutput.println("There was an error creating the folder " + newFolderName);
		}
	}					
}