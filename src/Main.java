import fileSystemModel.*;

public class Main
{
	
	public static void main(String[] args) throws Exception
	{
		FileSystem system = new FileSystem();
		
		system.createDirectory("Documents", "/");
		system.createDirectory("Images", "/");
		system.createFile("users.txt", "/", "Richard\nEllen\nHenry\nSarah");
		system.createFile("Holydays.jpg", "Images", "JPEGFILE");
		
		System.out.println(system);
		
		Services services = new Services(system);
		System.out.println(services.getFullPath("users.txt"));
		System.out.println(services.getAllDescendants("Images"));
	}

}
