package fileSystemModel;
import java.util.ArrayList;

public abstract class SystemComponent
{
	// attributes
	protected String name;
	protected String content;
	protected SystemComponent parent;
	protected ArrayList<SystemComponent> children;
	
	
	///////////////////////////////////////////////////////////////////////////
	
	// constructor
	public SystemComponent(String name, String content)
	{
		this.name = name;
		this.content = content;
		this.parent = null;
		this.children = null;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	// abstract methods
	public abstract void add(SystemComponent component);
	
	///////////////////////////////////////////////////////////////////////////
	
	// methods
	public String getName()
	{
		return this.name;
	}
	
	public String getContent()
	{
		return this.content;
	}
	
	public SystemComponent getParent()
	{
		return this.parent;
	}
	
	public ArrayList<SystemComponent> getChildren()
	{
		return this.children;
	}
	
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setContent(String content)
	{
		this.content = content;
	}
	
	public void setParent(SystemComponent parent)
	{
		this.parent = parent;
	}
	
	public void setChildren(ArrayList<SystemComponent> children)
	{
		this.children = children;
	}
}
