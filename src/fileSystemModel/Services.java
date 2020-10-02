package fileSystemModel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

//collection of static methods methods on FileSystem
public class Services
{
	
	// fully qualified path of a component
	public static String getFullPath(SystemComponent component)
	{
		// if root
		if (component instanceof Directory && component.getParent() == null)
		{
			return "/\\";
		}
		
		// else climbing the tree using the parent reference until reaching the root
		SystemComponent node = component.getParent();
		String path = "";
		
		if (component instanceof Directory)
		{
			path = path + component.getName() + "\\";
		}
		else
		{
			path = path + component.getName();
		}
		
		
		while (node != null)
		{
			path = node.getName() + "\\" + path;
			node = node.getParent();
		}
		
		return path;
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	
	
	// list of all descendants of a component
	public static ArrayList<SystemComponent> getDescendants(SystemComponent component)
	{
		ArrayList<SystemComponent> descendants = new ArrayList<SystemComponent>();
		
		// if root with no child then no descendants
		if (component instanceof Directory && component.getChildren().isEmpty())
		{
			return descendants;
		}
		// if file then no descendants
		if (component instanceof File)
		{
			return descendants;
		}
		
		// else gathering all children and children on children and so on 
		// until reaching all leaves of the subtree having component as local root
		ArrayList<SystemComponent> process = new ArrayList<SystemComponent>();
		for (SystemComponent child: component.getChildren())
		{
			process.add(child);
		}
		
		while (!process.isEmpty())
		{
			SystemComponent node = process.get(0);
			process.remove(0);
			
			// leaf - file
			if (node instanceof File)
			{
				descendants.add(node);
			}
			else if (node instanceof Directory)
			{
				// leaf - empty repository
				if (node.getChildren().isEmpty())
				{
					descendants.add(node);
				}
				// intermediate node - directory with at least one child
				else
				{
					for (SystemComponent child: node.getChildren())
					{
						process.add(child);
					}
				}
			}
		}
		
		return descendants;
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	
	
	// list of all descendants of a component having a specific name
	public static ArrayList<SystemComponent> getDescendantsWithName(SystemComponent component, String name)
	{
		ArrayList<SystemComponent> descendants = new ArrayList<SystemComponent>();
		
		// if root with no child then no descendants
		if (component instanceof Directory && component.getChildren().isEmpty())
		{
			return descendants;
		}
		// if file then no descendants
		if (component instanceof File)
		{
			return descendants;
		}
		
		// else gathering all children and children on children and so on 
		// until reaching all leaves of the subtree having component as local root
		ArrayList<SystemComponent> process = new ArrayList<SystemComponent>();
		for (SystemComponent child: component.getChildren())
		{
			process.add(child);
		}
		
		while (!process.isEmpty())
		{
			SystemComponent node = process.get(0);
			process.remove(0);
			
			// leaf - file
			if (node instanceof File)
			{
				if (node.getName().equals(name))
				{
					descendants.add(node);
				}
			}
			else if (node instanceof Directory)
			{
				// leaf - empty repository
				if (node.getChildren().isEmpty())
				{
					if (node.getName().equals(name))
					{
						descendants.add(node);
					}
				}
				// intermediate node - directory with at least one child
				else
				{
					for (SystemComponent child: node.getChildren())
					{
						process.add(child);
					}
				}
			}
		}
		
		return descendants;
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	
	
	// complete size of a component
	public static int getSize(SystemComponent component)
	{
		// leaf - file - size is number of bytes of the "content" string
		if (component instanceof File)
		{
			return component.getContent().getBytes().length;
		}
		
		// leaf - empty directory - size is 4096 bytes
		if (component.getChildren().isEmpty())
		{
			return 4096;
		}
		
		// intermediate node - non empty directory - size is 4096 + size of children
		int subsize = 0;
		for (SystemComponent child: component.getChildren())
		{
			subsize = subsize + Services.getSize(child);
		}
		return 4096 + subsize;
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	
	
	// serialization
	public static void saveComponent(SystemComponent component, String dest)
	{
		try
		{
			FileOutputStream fileOut = new FileOutputStream(dest);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(component);
			out.close();
			fileOut.close();
		}
		catch (IOException i)
		{
			i.printStackTrace();
		}
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	
	
	// deserialization
	public static SystemComponent loadComponent(String src)
	{
		try
		{
			FileInputStream fileIn = new FileInputStream(src);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			SystemComponent component = (SystemComponent) in.readObject();
			in.close();
			fileIn.close();
			return component;
		}
		catch (IOException i)
		{
			i.printStackTrace();
			return null;
		}
		catch (ClassNotFoundException j)
		{
			j.printStackTrace();
			return null;
		}
	}
}
