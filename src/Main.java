import fileSystemModel.*;

public class Main
{
	
	public static void main(String[] args) throws Exception
	{
		FileSystem system = new FileSystem();
		
		SystemComponent dirDocuments = SystemComponentFactory.getSystemComponent("directory", "Documents", null);
		SystemComponent dirImages = SystemComponentFactory.getSystemComponent("directory", "Images", null);
		SystemComponent dirA31 = SystemComponentFactory.getSystemComponent("directory", "A31", null);
		SystemComponent dirUML = SystemComponentFactory.getSystemComponent("directory", "UML", null);
		
		SystemComponent fileUsers = SystemComponentFactory.getSystemComponent("file", "users.txt", "Jean, Marie, Paul, Vivianne");
		SystemComponent fileMain = SystemComponentFactory.getSystemComponent("file", "main.java", "public static void main(String[] args){}");
		SystemComponent filePhoto1 = SystemComponentFactory.getSystemComponent("file", "photo1.png", "PNGFILE");
		SystemComponent filePhoto2 = SystemComponentFactory.getSystemComponent("file", "photo2.jpg", "JPEGFILE");
		SystemComponent fileTerminal = SystemComponentFactory.getSystemComponent("file", "terminal.java", "public class Terminal {}");
		
		
		system.insertDirectory(dirDocuments);
		system.insertDirectory(dirImages);
		system.insertFile(fileUsers);
		
		system.setWorkingDir(dirDocuments);
		system.insertDirectory(dirA31);
		system.insertFile(fileMain);
		
		system.setWorkingDir(dirA31);
		system.insertDirectory(dirUML);
		system.insertFile(fileTerminal);
		
		system.setWorkingDir(dirImages);
		system.insertFile(filePhoto1);
		system.insertFile(filePhoto2);
		
		// reset to root
		system.setWorkingDir(system.getRoot());
		
		System.out.println(system);
	}

}
