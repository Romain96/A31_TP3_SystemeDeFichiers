package fileSystemModel;

import java.util.ArrayList;

// Represents a directory containing both File and Directory (both SystemComponent)
public class Directory implements SystemComponent
{
	
	// attributes
	public String name;
	public SystemComponent parent;
	public ArrayList<SystemComponent> children;
	
	
	///////////////////////////////////////////////////////////////////////////
	
	
	// constructor
	public Directory(String name, SystemComponent parent)
	{
		this.name = name;
		this.parent = parent;
		this.children = new ArrayList<SystemComponent>();
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	
	
	@Override
	public void add(SystemComponent component)
	{
		this.children.add(component);
	}
	
	
	@Override
	public void remove(SystemComponent component)
	{
		this.children.remove(component);
	}

	
	@Override
	public String getName()
	{
		return this.name;
	}

	@Override
	public String getContent()
	{
		return null;
	}

	@Override
	public SystemComponent getParent()
	{
		return this.parent;
	}

	@Override
	public ArrayList<SystemComponent> getChildren()
	{
		return this.children;
	}

	@Override
	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public void setContent(String content)
	{
		return;
	}

	@Override
	public void setParent(SystemComponent parent)
	{
		this.parent = parent;
	}

	@Override
	public void setChildren(ArrayList<SystemComponent> children)
	{
		this.children = children;
	}

}
