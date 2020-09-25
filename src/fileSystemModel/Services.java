package fileSystemModel;

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
		// finding the file name
		SystemComponent component = this.system.find(name);
		
		if (component == null)
		{
			throw new Exception("Services : " + name + " does not exist !");
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
