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
			else if (node.getChildren() != null)
			{
				for (SystemComponent component: node.getChildren())
				{
					list.add(component);
				}
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
				// creating a new directory using the factory
				SystemComponent dir = ComponentFactory.getSystemComponent(this, 
						SystemComponentType.DIRECTORY, directoryName, parentName, null);
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
				// creating a new directory using the factory
				//SystemComponent file = new File(fileName, fileContent, basedir);
				SystemComponent file = ComponentFactory.getSystemComponent(this, 
						SystemComponentType.FILE, fileName, parentName, fileContent);
				basedir.add(file);
				this.components.add(file);
			}
			else
			{
				throw new Exception("Directory " + parentName + " is not a directory.");
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
		
		ArrayList<SystemDisplayPair> branches = new ArrayList<SystemDisplayPair>();
		for (SystemComponent child: this.root.getChildren())
		{
			branches.add(new SystemDisplayPair(child, "/"));
		}
		String display = "";
		
		// processing all nodes in depth-first fashion
		while (!branches.isEmpty())
		{
			// getting the current node
			SystemDisplayPair pair = branches.get(0);
			branches.remove(0);
			SystemComponent node = pair.getKey();
			String path = pair.getValue();
			
			// final path
			path = path + "\\" + node.getName();
			
			if (node instanceof File)
			{
				// adding this complete path to the display string
				display = display + "\n" + path;
			}
			else if (node instanceof Directory)
			{
				if (node.getChildren() == null || node.getChildren().isEmpty())
				{
					// adding this complete path to the display string
					display = display + "\n" + path + "\\";
				}
				else
				{
					// adding all children to the list (depth-first so inserting them before already present items)
					int index = 0;
					for (SystemComponent child: node.getChildren())
					{
						branches.add(index, new SystemDisplayPair(child, path));
						index++;
					}
				}
			}
		}
		
		return display;
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
