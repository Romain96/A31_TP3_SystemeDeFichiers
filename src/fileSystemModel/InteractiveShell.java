package fileSystemModel;

import java.io.Console;
import java.util.List;

import jdk.jshell.JShell;
import jdk.jshell.SnippetEvent;

public class InteractiveShell
{
	
	public static void main(String[] args)
	{
		Console console = System.console();

		try (JShell jsh = JShell.create())
		{
			// imports
			jsh.eval("import fileSystemModel.*;");
			
			// creating a Terminal (creates a FileSystem with a root)
			jsh.eval("Terminal t = 	new Terminal();");
			
			// infinite loop (evaluations)
			do
			{
				System.out.print("> ");
				
				String input = console.readLine();
				if (input == null)
				{
					break;	// CTRL + D
				}
				
				String[] arg = input.split("\\s");	// whitespace character
				input = "t." + arg[0] + "(";
				if (arg.length > 1)
				{
					input = input + '"' + arg[1] + '"';
				}
				input = input + ");";
				
				// evaluating the command
				List<SnippetEvent> events = jsh.eval(input);
					
				for (SnippetEvent e: events)
				{
					if (e.value() != null)
					{
						System.out.print(e.value());
					}
					else
					{
						System.err.println(e);
					}
					System.out.flush();
				}
			}
			while (true);
		}
		

		//FileSystem system = new FileSystem();
		
		//SystemComponent dirDocuments = SystemComponentFactory.getSystemComponent("directory", "Documents", null);
		//SystemComponent dirImages = SystemComponentFactory.getSystemComponent("directory", "Images", null);
		//SystemComponent dirA31 = SystemComponentFactory.getSystemComponent("directory", "A31", null);
		//SystemComponent dirUML = SystemComponentFactory.getSystemComponent("directory", "UML", null);
		
		//SystemComponent fileUsers = SystemComponentFactory.getSystemComponent("file", "users.txt", "Jean, Marie, Paul, Vivianne");
		//SystemComponent fileMain = SystemComponentFactory.getSystemComponent("file", "main.java", "public static void main(String[] args){}");
		//SystemComponent filePhoto1 = SystemComponentFactory.getSystemComponent("file", "photo1.png", "PNGFILE");
		//SystemComponent filePhoto2 = SystemComponentFactory.getSystemComponent("file", "photo2.jpg", "JPEGFILE");
		//SystemComponent fileTerminal = SystemComponentFactory.getSystemComponent("file", "terminal.java", "public class Terminal {}");
		
		
		//system.insertDirectory(dirDocuments);
		//system.insertDirectory(dirImages);
		//system.insertFile(fileUsers);
		
		//system.setWorkingDir(dirDocuments);
		//system.insertDirectory(dirA31);
		//system.insertFile(fileMain);
		
		//system.setWorkingDir(dirA31);
		//system.insertDirectory(dirUML);
		//system.insertFile(fileTerminal);
		
		//system.setWorkingDir(dirImages);
		//system.insertFile(filePhoto1);
		//system.insertFile(filePhoto2);
		
		// reset to root
		//system.setWorkingDir(system.getRoot());
		
		//System.out.println(system);
		
		//System.out.println(Services.getFullPath(dirUML));
		
		//System.out.println(Services.getDescendants(system.getRoot()));
		
		//System.out.println(Services.getSize(dirUML));
		//System.out.println(Services.getSize(dirDocuments));
		//System.out.println(Services.getSize(fileUsers));
		//System.out.println(Services.getSize(system.getRoot()));
		
		//Services.saveComponent(fileUsers, "users.ser");
		//SystemComponent loaded = Services.loadComponent("users.ser");
		//System.out.println(loaded);
	}

}
