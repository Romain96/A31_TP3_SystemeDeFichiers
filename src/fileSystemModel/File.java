package fileSystemModel;

import java.util.ArrayList;

// Represents a file with a content (leaf)
public class File implements SystemComponent
{
	
	// attributes
	private String name;
	private String content;
	private SystemComponent parent;
	
	///////////////////////////////////////////////////////////////////////////

	
	// constructor
	public File(String name, String content, SystemComponent parent)
	{
		this.name = name;
		this.content = content;
		this.parent = parent;
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	
	
	@Override
	public String getName()
	{
		return this.name;
	}

	@Override
	public String getContent()
	{
		return this.content;
	}

	@Override
	public SystemComponent getParent()
	{
		return this.parent;
	}

	@Override
	public ArrayList<SystemComponent> getChildren()
	{
		// no children - it's a file not a directory
		return null;
	}

	@Override
	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public void setContent(String content)
	{
		this.content = content;
	}

	@Override
	public void setParent(SystemComponent parent)
	{
		this.parent = parent;
	}

	@Override
	public void setChildren(ArrayList<SystemComponent> children)
	{
		return;
	}

}
