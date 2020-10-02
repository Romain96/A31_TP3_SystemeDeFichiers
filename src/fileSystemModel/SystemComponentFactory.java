package fileSystemModel;

public class SystemComponentFactory
{
	public static SystemComponent getSystemComponent(String type, String name, String content) throws Exception
	{
		if (type.equalsIgnoreCase("root"))
		{
			return new Directory("/", "");
		}
		else if (type.equalsIgnoreCase("file"))
		{
			if (SystemComponentFactory.nameIsValid(name))
			{
				return new File(name, content);
			}
			else
			{
				throw new Exception("ERROR : " + name + " is not a valid file name");
			}
		}
		else if (type.equalsIgnoreCase("directory"))
		{
			if (SystemComponentFactory.nameIsValid(name))
			{
				return new Directory(name, content);
			}
			else
			{
				throw new Exception("ERROR : " + name + " is not a valid directory name");
			}
		}
		else
		{
			return null;
		}
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	
	
	private static boolean nameIsValid(String name)
	{
		if (name.contains("/"))
		{
			return false;
		}
		else if (name.contains("\\"))
		{
			return false;
		}
		else if (name.contains(" "))
		{
			return false;
		}
		else if (name.equals("..") || name.equals("."))
		{
			return false;
		}
		return true;
	}
}
