package fileSystemModel;

import java.util.ArrayList;

// Contains services on FileSystem
public class Services
{
	
	// attributes
	private FileSystem system;
	
	///////////////////////////////////////////////////////////////////////////
	
	// constructor
	public Services(FileSystem system)
	{
		this.system = system;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	// complete path of a file/directory
	public String getFullPath(String name) throws Exception
	{
		// finding the file/directory name
		SystemComponent component = this.system.find(name);
		
		if (component == null)
		{
			throw new Exception("Services.getFullPath : " + name + " does not exist !");
		}
		else
		{
			String fullName = component.getName();
			SystemComponent node = component.getParent();
			while (node != null)
			{
				fullName = node.getName() + "/" + fullName;
				node = node.getParent();
			}
			// removing double //
			return fullName.substring(1);
		}
	}
	
	// list of all descendants of a common ancestor
	public ArrayList<String> getAllDescendants(String name) throws Exception
	{
		// finding the file/directory name
		SystemComponent component = this.system.find(name);
		
		if (component == null)
		{
			throw new Exception("Services.getAllDescendants : " + name + " does not exist !");
		}
		else
		{	
			// finding all descendants (leaves : empty directories & files)
			ArrayList<SystemComponent> leaves = new ArrayList<SystemComponent>();
			ArrayList<SystemComponent> toProcess = new ArrayList<SystemComponent>();
			toProcess.add(component);
			
			while (!toProcess.isEmpty())
			{
				SystemComponent node = toProcess.get(0);
				toProcess.remove(0);
				if (node.getChildren() == null)
				{
					leaves.add(node);
				}
				else
				{
					for (SystemComponent child: node.getChildren())
					{
						toProcess.add(child);
					}
				}
			}
			
			// climbing the tree to get partial paths up to the starting directory
			ArrayList<String> descendants = new ArrayList<String>();
			for (SystemComponent leaf: leaves)
			{
				String localName = leaf.getName();
				SystemComponent node = leaf.getParent();
				while (!node.getName().equalsIgnoreCase(component.getName()))
				{
					localName = node.getName() + "/" + localName;
					node = node.getParent();
				}
				if (component.getParent() == null)
				{
					descendants.add("/" + localName);
				}
				else
				{
					descendants.add(component.getName() + "/" + localName);
				}
			}
			
			return descendants;
		}
	}
	
	// list of all descendants of a common ancestor having a specific name
	
	// total size of a directory
	
	// serialization of path
	
	///////////////////////////////////////////////////////////////////////////
	
	// getters/setters
	public FileSystem getFileSystem()
	{
		return this.system;
	}
	
	
	public void setFileSystem(FileSystem system)
	{
		this.system = system;
	}
}
