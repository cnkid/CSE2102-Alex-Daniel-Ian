import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		boolean quit = false;
		Crawler crawl = new Crawler();
		Scanner scanInt = new Scanner(System.in);
		ArrayList<String> list = new ArrayList<String>();
		while(!quit){
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("Hello, please select the function you wish to perform");
			System.out.println("1: discover and print all folders, subfolder, and files");
			System.out.println("2: Search by file name or fragment of text");
			System.out.println("3: Delete files by file type");
			System.out.println("4: Move files");
			System.out.println("5: Search by file type");
			System.out.println("6: Delete Directory");
			System.out.println("7: Quit program");
			Scanner scanString = new Scanner(System.in);
			int choice = scanInt.nextInt();
			if(choice == 1){
				
				System.out.println("enter root folder");
				String root = scanString.nextLine();
				root.replaceAll("\\\\", "\\\\\\\\");
				crawl = new Crawler();
				crawl.crawl(root, true);
				
			}
			if(choice == 2){
				System.out.println("Enter root folder");
				String root = scanString.nextLine();
				System.out.println("Enter file name / fragment of text");
				String text = scanString.nextLine();
				root.replaceAll("\\\\", "\\\\\\\\");
				crawl = new Crawler();
				list = crawl.searchExt(text, root);
				crawl.displayList(list);
			}
			if(choice == 3){
				System.out.println("Enter root folder");
				String root = scanString.nextLine();
				System.out.println("Enter file type");
				String text = scanString.nextLine();
				if(!text.contains(".")){
					text = "." + text;
				}
				root.replaceAll("\\\\", "\\\\\\\\");
				crawl = new Crawler();
				list = crawl.searchExt(text, root);
				crawl.delete(list,false);
			}
			if(choice == 5){
				System.out.println("Enter root folder");
				String root = scanString.nextLine();
				System.out.println("Enter file type");
				String text = scanString.nextLine();
				if(!text.contains(".")){
					text = "." + text;
				}
				root.replaceAll("\\\\", "\\\\\\\\");
				crawl = new Crawler();
				list = crawl.searchExt(text, root);
				crawl.displayList(list);
			}
			if(choice == 6){
				System.out.println("Enter root folder");
				String root = scanString.nextLine();
				System.err.println("Are you sure you want to delete [ " + root + " ] and all its sub directories and files? [Y/N]");
				root.replaceAll("\\\\", "\\\\\\\\");
				if((scanString.nextLine() .equals("Y")) || scanString.nextLine() .equals("y")){
					crawl = new Crawler();
					crawl.deleteDirectory(root);
				}
			}
		}	
	}
}
