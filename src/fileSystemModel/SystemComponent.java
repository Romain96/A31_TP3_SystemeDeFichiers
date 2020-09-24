package fileSystemModel;

import java.util.ArrayList;

public interface SystemComponent
{
	// getters/setters
	public String getName();
	public String getContent();
	public SystemComponent getParent();
	public ArrayList<SystemComponent> getChildren();
	
	public void setName(String name);
	public void setContent(String content);
	public void setParent(SystemComponent parent);
	public void setChildren(ArrayList<SystemComponent> children);
	
	// methods
	public void add(SystemComponent component);
	public void remove(SystemComponent component);
	
	// debug - print component
	public String toString();
}
