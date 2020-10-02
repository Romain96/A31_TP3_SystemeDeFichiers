package fileSystemModel;

import java.util.ArrayList;

public class FileSystem
{
	
	// attributes
	private SystemComponent root;
	private SystemComponent workingDir;

	
	///////////////////////////////////////////////////////////////////////////
	
	
	// constructor
	public FileSystem() throws Exception
	{
		// creating root
		this.root = SystemComponentFactory.getSystemComponent("root", "/", "");
		// setting the working directory on the created root
		this.workingDir = this.root;
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	
	
	// inserting a file in the tree at the current directory
	public void insertFile(SystemComponent file) throws Exception
	{
		if (this.filenameIsValid(file, this.workingDir))
		{
			this.workingDir.add(file);
		}
		else
		{
			throw new Exception("FileSystem.insertFile ERROR - " + file.getName() + " is already taken");
		}
	}
	
	
	// inserting a directory (empty or not) in the tree at the current directory
	public void insertDirectory(SystemComponent dir) throws Exception
	{
		if (this.dirnameIsValid(dir, this.workingDir))
		{
			this.workingDir.add(dir);
		}
		else
		{
			throw new Exception("FileSystem.insertDirectory ERROR - " + dir.getName() + " is already taken");
		}
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	
	
	// deleting a file in the tree at the current directory
	public void deleteFile(String filename) throws Exception
	{
		// searching amongst children of the current directory
		for (SystemComponent child: this.workingDir.getChildren())
		{
			if (child instanceof File && child.getName().equals(filename))
			{
				this.workingDir.getChildren().remove(child);
				return;
			}
		}
		throw new Exception("FileSystem.deleteFile ERROR - " + filename + " does not exist");
	}
	
	
	// deleting a directory (empty or not) in the tree at the current directory
	public void deleteDirectory(String dirname) throws Exception
	{
		// searching amongst children of the current directory
		for (SystemComponent child: this.workingDir.getChildren())
		{
			if (child instanceof Directory && child.getName().equals(dirname))
			{
				this.recursiveRemove(child);
				this.workingDir.getChildren().remove(child);
				return;
			}
		}
		throw new Exception("FileSystem.deleteDirectory ERROR - " + dirname + " does not exist");
	}
	
	private void recursiveRemove(SystemComponent node)
	{
		// halting case -> leaf / File
		if (node instanceof File)
		{
			node.parent = null;
			return;
		}
		
		// halting case -> leaf / empty Directory
		if (node instanceof Directory && node.getChildren().isEmpty())
		{
			node.parent = null;
			return;
		}
		
		// recursive case -> non-empty directory
		for (SystemComponent child: node.getChildren())
		{
			this.recursiveRemove(child);
		}
		node.children.clear();
		node.children = null;
		node.parent = null;
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	
	
	// generic move of component
	public void moveComponent(String oldname, String newname) throws Exception
	{
		// searching the file in the working directory
		boolean fold = false;
		boolean fnew = false;
		SystemComponent oldComponent = null;
		for (SystemComponent child: this.workingDir.getChildren())
		{
			if (child instanceof File && child.getName().equals(oldname) && !fold)
			{
				oldComponent = child;
				fold = true;
			}
			if (child instanceof File && child.getName().equals(newname) && !fnew)
			{
				fnew = true;
			}
		}
				
		// oldname not found
		if (!fold)
		{
			throw new Exception("FileSystem.moveComponent ERROR - " + oldname + " does not exist");
		}
				
		// newname already taken
		if (fnew)
		{
			throw new Exception("FileSystem.moveComponent ERROR - " + newname + " is already taken");
		}
		
		if (oldComponent instanceof Directory)
		{
			this.moveDirectory(oldComponent, newname);
		}
		else if (oldComponent instanceof File)
		{
			this.moveFile(oldComponent, newname);
		}
	}
	
	
	// moving a file (changing its local path/name) in the tree at the current directory
	private void moveFile(SystemComponent oldComponent, String newname)
	{	
		// renaming
		oldComponent.setName(newname);
	}
	
	
	// moving a directory (changing its local path/name) in the tree at the current directory
	private void moveDirectory(SystemComponent oldComponent, String newname)
	{
		// renaming
		oldComponent.setName(newname);
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	
	
	public ArrayList<String> getDisplay()
	{
		ArrayList<SystemComponent> leaves = new ArrayList<SystemComponent>();
		ArrayList<SystemComponent> nodes = new ArrayList<SystemComponent>();
		ArrayList<String> display = new ArrayList<String>();
		
		if (this.root.getChildren().isEmpty())
		{
			display.add((String) "/\\");
			return display;
		}
		
		// finding all leaves
		for (SystemComponent child: this.root.getChildren())
		{
			nodes.add(child);
		}
		while (!nodes.isEmpty())
		{
			SystemComponent node = nodes.get(0);
			nodes.remove(0);
			
			if (node instanceof File)
			{
				leaves.add(node);
			}
			else if (node instanceof Directory)
			{
				if (node.getChildren().isEmpty())
				{
					leaves.add(node);
				}
				else
				{
					for (SystemComponent child: node.getChildren())
					{
						nodes.add(child);
					}
				}
			}
		}
		
		// creating paths for all leaves
		for (SystemComponent leaf: leaves)
		{
			String path = "\\" + leaf.getName();
			SystemComponent parent = leaf.getParent();
			
			while(parent != null)
			{
				path = "\\" + parent.getName() + path;
				parent = parent.getParent();
			}
			display.add(path.substring(1));
		}
		
		return display;
	}
	
	
	public String toString()
	{
		ArrayList<String> branches = this.getDisplay();
		String display = "";
		
		for (String branch: branches)
		{
			display = display + "\n" + branch;
		}
		
		return display;
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	
	
	// checks whether the name of file is not already taken by another file inside dir
	private boolean filenameIsValid(SystemComponent file, SystemComponent wdir)
	{
		for (SystemComponent child: wdir.getChildren())
		{
			if (child instanceof File && child.getName().equals(file.getName()))
			{
				return false;
			}
		}
		return true;
	}
	
	
	// checks whether the name of directory is not already taken by another directory inside dir
	private boolean dirnameIsValid(SystemComponent dir, SystemComponent wdir)
	{
		for (SystemComponent child: wdir.getChildren())
		{
			if (child instanceof Directory && child.getName().equals(dir.getName()))
			{
				return false;
			}
		}
		return true;
	}
		
		
	///////////////////////////////////////////////////////////////////////////
	
	
	public SystemComponent getRoot()
	{
		return this.root;
	}
	
	public SystemComponent getWorkingDir()
	{
		return this.workingDir;
	}
	
	public void setRoot(SystemComponent root)
	{
		this.root = root;
	}
	
	public void setWorkingDir(SystemComponent workingDir)
	{
		this.workingDir = workingDir;
	}
}
