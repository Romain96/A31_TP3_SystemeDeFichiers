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
		System.out.println(services.getAllDescendants("/"));
		System.out.println(services.getAllDescendantsWithName("/", "Documents"));
		
		System.out.println("Size of / : " + services.getSizeOfDirectory("/"));
		services.saveComponent("users.txt");
		SystemComponent test = services.loadComponent("users.txt.ser");
		System.out.println(test.getName());
		System.out.println(test.getParent());
		System.out.println(test.getContent());
		System.out.println(test.getChildren());
	}

}
