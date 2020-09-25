package fileSystemModel;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
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
	public ArrayList<String> getAllDescendants(String ancestor) throws Exception
	{
		// finding the file/directory name
		SystemComponent component = this.system.find(ancestor);
		
		if (component == null)
		{
			throw new Exception("Services.getAllDescendants : " + ancestor + " does not exist !");
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
					leaves.add(node);
					for (SystemComponent child: node.getChildren())
					{
						toProcess.add(child);
					}
				}
			}
			System.out.println("TEST : \n" + leaves);
			
			// climbing the tree to get partial paths up to the starting directory
			ArrayList<String> descendants = new ArrayList<String>();
			for (SystemComponent leaf: leaves)
			{
				if (leaf.getParent() == null)
				{
					descendants.add("/");
				}
				else
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
			}
			
			return descendants;
		}
	}
	
	
	// list of all descendants of a common ancestor having a specific name
	public ArrayList<String> getAllDescendantsWithName(String ancestor, String name) throws Exception
	{
		// finding the file/directory name
		SystemComponent component = this.system.find(ancestor);
		
		if (component == null)
		{
			throw new Exception("Services.getAllDescendantsWithName : " + ancestor + " does not exist !");
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
					if (node.getName().equalsIgnoreCase(name))
					{
						leaves.add(node);
					}
				}
				else
				{
					if (node.getName().equalsIgnoreCase(name))
					{
						leaves.add(node);
					}
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
	
	
	// total size of a directory
	private int getRecSizeOfDirectory(SystemComponent dir)
	{
		if (dir instanceof File)
		{
			return dir.getContent().getBytes(StandardCharsets.UTF_8).length;
		}
		else if (dir instanceof Directory)
		{
			// empty directory
			if (dir.getChildren() == null)
			{
				return 4096;
			}
			else
			{
				int subsize = 4096;
				for (SystemComponent child: dir.getChildren())
				{
					subsize = subsize + this.getRecSizeOfDirectory(child);
				}
				return subsize;
			}
		}
		return 0;
	}
	
	public int getSizeOfDirectory(String dirname) throws Exception
	{
		// finding the file/directory name
		SystemComponent component = this.system.find(dirname);
				
		if (component == null)
		{
			throw new Exception("Services.getSizeOfDirectory : " + dirname + " does not exist !");
		}
		else if (!(component instanceof Directory))
		{
			throw new Exception("Services.getSizeOfDirectory : " + dirname + " is not a directory !");
		}
		else
		{
			return this.getRecSizeOfDirectory(component);
		}
	}
	
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
