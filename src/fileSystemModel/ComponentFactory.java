package fileSystemModel;

// Factory - creates both File and Directory objects
public class ComponentFactory
{
	public static SystemComponent getSystemComponent(
			FileSystem system,
			SystemComponentType type,
			String name, 
			String parent, 
			String content) throws Exception
	{
		// creating a FILE
		if (type == SystemComponentType.FILE)
		{
			// checking if parent directory exists
			SystemComponent systemParent = system.find(parent);
			
			// parent not found in system
			if (systemParent == null)
			{
				throw new Exception("ComponentFactory : file system does not have a " + parent + " directory !");
			}
			else
			{
				return new File(name, content, systemParent);
			}
		}
		// creating a directory
		else if (type == SystemComponentType.DIRECTORY)
		{
			// checking if parent directory exists
			SystemComponent systemParent = system.find(parent);
						
			// parent not found in system
			if (systemParent == null)
			{
				throw new Exception("ComponentFactory : file system does not have a " + parent + " directory !");
			}
			else
			{
				return new Directory(name, systemParent);
			}
		}
		// ERROR - unsupported type
		else
		{
			throw new Exception("ComponentFactory : unsupported component type " + type);
		}
	}
}
