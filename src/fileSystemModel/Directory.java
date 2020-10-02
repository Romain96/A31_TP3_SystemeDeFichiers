package fileSystemModel;

import java.util.ArrayList;

public class Directory extends SystemComponent
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5065330822629587725L;

	// constructor
	public Directory(String name, String content)
	{
		super(name, content);
		this.children = new ArrayList<SystemComponent>();
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	
	
	@Override
	public void add(SystemComponent component)
	{
		// adding the component as a child of the current one
		this.children.add(component);
		// and designating the current component as the given component's parent
		component.setParent(this);
	}
	
	
	@Override
	public String toString()
	{
		return this.name;
	}

}
