package fileSystemModel;

import java.util.ArrayList;

public class Terminal
{
	// attributes
	private FileSystem system;
	
	
	///////////////////////////////////////////////////////////////////////////
	
	
	// constructor
	public Terminal() throws Exception
	{
		this.system = new FileSystem();	// creates the root
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	
	
	// touch(<filename>)
	public void touch(String filename) throws Exception
	{
		SystemComponent file = SystemComponentFactory.getSystemComponent("file", filename, "default content");
		this.system.insertFile(file);
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	
	
	// mkdir(<dirname>)
	public void mkdir(String dirname) throws Exception
	{
		SystemComponent dir = SystemComponentFactory.getSystemComponent("directory", dirname, null);
		this.system.insertDirectory(dir);
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	
	
	// ls()
	public void ls()
	{
		ArrayList<SystemComponent> children = this.system.getWorkingDir().getChildren();
		if (children.isEmpty())
		{
			System.out.println("");
		}
		
		String display = "";
		for (SystemComponent child: children)
		{
			if (child instanceof Directory)
			{
				display = display + child.getName() + "\\\n";
			}
			else
			{
				display = display + child.getName() + "\n";
			}
		}
		
		System.out.println(display);
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	
	
	// pwd()
	public void pwd()
	{
		System.out.println(Services.getFullPath(this.system.getWorkingDir()));
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	
	
	// cd(<dirname>)
	public void cd(String dirname) throws Exception
	{
		if (this.system.getWorkingDir().getChildren().isEmpty())
		{
			throw new Exception("Terminal ERROR - current working directory (" + this.system.getWorkingDir().getName() + ") is empty");
		}
		
		for (SystemComponent child: this.system.getWorkingDir().getChildren())
		{
			if (child instanceof Directory && child.getName().equals(dirname))
			{
				this.system.setWorkingDir(child);
				return;
			}
		}
		throw new Exception("Terminal ERROR - directory " + dirname + " does not exist");
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	
	
	// rm(<filename>)
	public void rm(String filename) throws Exception
	{
		this.system.deleteFile(filename);
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	
	
	// rmdir(<dirname>)
	public void rmdir(String dirname) throws Exception
	{
		this.system.deleteDirectory(dirname);
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	
	
	// mv(<oldname>,<newname>)
	public void mv(String oldname, String newname) throws Exception
	{
		this.system.moveComponent(oldname, newname);
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	
	
	// getters/setters
	public FileSystem getSystem()
	{
		return this.system;
	}
	
	public void setSytem(FileSystem system)
	{
		this.system = system;
	}
}
