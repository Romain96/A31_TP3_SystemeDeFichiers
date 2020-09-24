import fileSystemModel.*;

public class Main
{
	
	public static void main(String[] args) throws Exception
	{
		FileSystem system = new FileSystem();
		system.createDirectory("Documents", "/");
		system.createFile("users.txt", "/", "Richard\nEllen\nHenry\nSarah");
	}

}
