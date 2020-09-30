import fileSystemModel.*;

public class Main
{
	
	public static void main(String[] args) throws Exception
	{
		FileSystem system = new FileSystem();
		
		system.createFile("users.txt", "/", "Patrick\nStefanie\nJean-Eudes\nAnnie\nKate\nMichel");
		
		system.createDirectory("Documents", "/");
		system.createDirectory("A31", "Documents");
		system.createFile("main.java", "Documents", "public static void main (String[] args)\n{\nSystem.out.println(\"Hello world\");\n}");
		system.createDirectory("UML", "A31");
		system.createFile("terminal.java", "A31", "public static void main (String[] args)\n{\nSystem.out.println(\"Terminal\");\n}");
		
		system.createDirectory("Images", "/");
		system.createFile("photo1.png", "Images", "JPEGFILE");
		system.createFile("photo2.jpg", "Images", "PNGFILE");
		
		System.out.println(system);
		
		//Services services = new Services(system);
		//System.out.println(services.getFullPath("users.txt"));
		//System.out.println(services.getAllDescendants("/"));
		//System.out.println(services.getAllDescendantsWithName("/", "Documents"));
		
		//System.out.println("Size of / : " + services.getSizeOfDirectory("/"));
		//services.saveComponent("users.txt");
		//SystemComponent test = services.loadComponent("users.txt.ser");
		//System.out.println(test.getName());
		//System.out.println(test.getParent());
		//System.out.println(test.getContent());
		//System.out.println(test.getChildren());
	}

}
