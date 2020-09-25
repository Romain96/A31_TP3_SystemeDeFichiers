package fileSystemModel;

import java.util.ArrayList;

// Contains all files & directories (the complete tree)
public class FileSystem
{
	
	// attributes
	public SystemComponent root;	// root directory
	public ArrayList<SystemComponent> components;	// all files & subdirectories
	
	
	///////////////////////////////////////////////////////////////////////////
	
	
	// constructor
	public FileSystem()
	{
		// creates the root directory & adds it to the list
		this.components = new ArrayList<SystemComponent>();
		this.root = new Directory("/", null);
		this.components.add(this.root);
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	
	// methods
	
	// finding a specific component by name by 
	// traversing the tree in a breadth-first fashion
	public SystemComponent find(String name)
	{	
		if (this.root.getName().equalsIgnoreCase(name))
		{
			return this.root;
		}
		
		ArrayList<SystemComponent> list = new ArrayList<SystemComponent>();
		for (SystemComponent component: this.root.getChildren())
		{
			list.add(component);
		}
		
		while(!list.isEmpty())
		{
			SystemComponent node = list.get(0);
			list.remove(0);
			
			if (node.getName().equalsIgnoreCase(name))
			{
				return node;
			}
			
			for (SystemComponent component: node.getChildren())
			{
				list.add(component);
			}
		}
		return null;
	}
	
	
	// create a new directory inside a specific directory
	public void createDirectory(String directoryName, String parentName) throws Exception
	{
		// finding parent name by descending the tree (breadth-first)
		SystemComponent basedir = this.find(parentName);
		if (basedir == null)
		{
			throw new Exception("Directory " + parentName + " does not exist.");
		}
		else
		{
			if (basedir instanceof Directory)
			{
				// creating a new directory
				SystemComponent dir = new Directory(directoryName, basedir);
				basedir.add(dir);
				this.components.add(dir);
			}
			else
			{
				throw new Exception("Directory " + parentName + " is not a directory.");
			}
		}
	}
	
	// create a new file inside a specific directory
	public void createFile(String fileName, String parentName, String fileContent) throws Exception
	{
		// finding parent name by descending the tree (breadth-first)
		SystemComponent basedir = this.find(parentName);
		if (basedir == null)
		{
			throw new Exception("Directory " + parentName + " does not exist.");
		}
		else
		{
			if (basedir instanceof Directory)
			{
				// creating a new directory
				SystemComponent file = new File(fileName, fileContent, basedir);
				basedir.add(file);
				this.components.add(file);
			}
			else
			{
				throw new Exception("Directory " + parentName + " is not a directory.");
			}
		}
	}
	
	// remove a file
	// remove a directory
	// move a file from a directory to another
	// move a directory from a directory to another
	
	private void getTreeString(SystemComponent node, String localPath, ArrayList<String> branches)
	{
		if (node.getChildren() == null)
		{
			branches.add(localPath + "/" + node.getName());
		}
		else
		{
			for (SystemComponent child: node.getChildren())
			{
				this.getTreeString(child,  localPath + "/" + node.getName(), branches);
			}
		}
	}
	
	// to string - prints the tree (depth-first fashion)
	public String toString()
	{
		if (this.root.getChildren() == null)
		{
			return this.root.getName();
		}
		
		// finding leaves
		ArrayList<String> list = new ArrayList<String>();
		for (SystemComponent child: this.root.getChildren())
		{
			// not printing "/"
			this.getTreeString(child, "", list);
		}
		
		String tree = "";
		for (String branch: list)
		{
			tree = tree + branch + "\n";
		}
		return tree;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	// getters/setters
	public SystemComponent getRoot()
	{
		return root;
	}
	
	public ArrayList<SystemComponent> getComponents()
	{
		return this.components;
	}
	
	public void setRoot(SystemComponent root)
	{
		this.root = root;
	}
	
	public void setComponents(ArrayList<SystemComponent> components)
	{
		this.components = components;
	}
}
