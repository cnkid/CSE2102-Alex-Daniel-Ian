import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		boolean quit = false;
		Crawler crawl = new Crawler();
		Scanner scanInt = new Scanner(System.in);
		while(!quit){
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("Hello, please select the function you wish to perform");
			System.out.println("1: discover and print all folders, subfolder, and files");
			System.out.println("2: Delete files");
			System.out.println("3: Move files");
			System.out.println("4: Scan file type");
			System.out.println("5: Quit program");
			Scanner scanString = new Scanner(System.in);
			int choice = scanInt.nextInt();
			if(choice == 1){
				
				System.out.println("enter root folder");
				String root = scanString.nextLine();
				root.replaceAll("\\\\", "\\\\\\\\");
				crawl.crawl(root);
			}
			if(choice == 2){
				System.out.println("enter root folder");
				String root = scanString.nextLine();
				root.replaceAll("\\\\", "\\\\\\\\");
				crawl.deleteExt(".docx", root); //is only deleting root folder.
			}
			if(choice == 5){
				quit = true;
				System.out.println("End of program");
			}
		}
		
		
	}

}
