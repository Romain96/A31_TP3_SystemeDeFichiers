package fileSystemModel;

// simple pair of two objects
public class SystemDisplayPair
{
	// attributes
	private SystemComponent key;
	private String value;
	
	
	///////////////////////////////////////////////////////////////////////////
	
	
	// constructor
	public SystemDisplayPair(SystemComponent key, String value)
	{
		this.key = key;
		this.value = value;
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	
	
	// getters/setters
	public SystemComponent getKey()
	{
		return key;
	}


	public String getValue()
	{
		return value;
	}


	public void setKey(SystemComponent key)
	{
		this.key = key;
	}


	public void setValue(String value)
	{
		this.value = value;
	}
}
