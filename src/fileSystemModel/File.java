package fileSystemModel;

public class File extends SystemComponent
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3485542272635765960L;

	// constructor
	public File(String name, String content)
	{
		super(name, content);
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	

	@Override
	public void add(SystemComponent component)
	{
		// cannot add a component to a File
		return;
	}
	
	@Override
	public String toString()
	{
		return this.name;
	}

}
