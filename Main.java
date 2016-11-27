
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;

public class Main {

	public static void main(String[] args) throws IOException, TikaException {
		float TimeConstant = .000006145188586929042f;
		Tika tika = new Tika();
		boolean quit = false;
		Crawler crawl = new Crawler();
		file_opener opener = new file_opener();
		Scanner scanInt = new Scanner(System.in);
		ArrayList<String> list = new ArrayList<String>();
		//File teestFile = new File("H:\\group project\\testfile.txt");
		//File teestFile1 = new File("H:\\group project\\test\\testfile.txt");
		//teestFile.renameTo(teestFile1);
		System.out.println("///////////////////////////");
		System.out.println("///////////////////////////");
		System.out.println("//                       //");
		System.out.println("//  SORTR Version 1.0    //");
		System.out.println("//                       //");
		System.out.println("//  Created by:          //");
		System.out.println("//  Alexander Valdes     //");
		System.out.println("//  Daniel Fernandes     //");
		System.out.println("//  Ian Dechene          //");
		System.out.println("//                       //");
		System.out.println("///////////////////////////");
		System.out.println("///////////////////////////");
		
		while(!quit){
			
			System.out.println("");
			System.out.println("");
			System.out.println("Hello, please select the function you wish to perform");
			System.out.println("1: discover and print all folders, subfolder, and files");
			System.out.println("2: Search by file name or frament of file name");
			System.out.println("3: Delete files by file type");
			System.out.println("4: Move files");
			System.out.println("5: Search by file type");
			System.out.println("6: Delete Directory");
			System.out.println("7: Search by text fragment");
			System.out.println("8: Quit program");
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
				System.out.println("Enter file name / fragment of file name");
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
				if((scanString.nextLine().toUpperCase() .equals("Y"))){
					crawl = new Crawler();
					crawl.deleteDirectory(root);
				}
			}
			if(choice == 7){
				ArrayList<File> tempArray = new ArrayList<File>();
				System.err.println("WARNING: THIS METHOD CAN TAKE A LONG TIME DEPENDING ON HOW MANY FILES IT SEARCHES");
				System.out.println("input root folder");
				String root = scanString.nextLine();
				root.replaceAll("\\\\", "\\\\\\\\");
				list = crawl.searchExt(".",root);
				float totalSize = 0.0f;
				for(int i = list.size(); i > 0; i--){
					File throwAway = new File(list.get(i-1));
					totalSize = totalSize + throwAway.length();
				}
				float time = (TimeConstant * totalSize);
				System.out.println("Please type in the text fragment you are searching for, it is not case sensitive.");
				String fragment = scanString.nextLine();
				System.err.println(list.size() + " file were found, this will take an estimated " + time + " seconds, are you sure you want to proceed? [Y/N]");
				if((scanString.nextLine().toUpperCase() .equals("Y"))){
					for(int i = list.size(); i > 0; i--){
						File throwAway = new File(list.get(i-1));
						String compare = opener.open(throwAway);
						if(compare != null && compare.toLowerCase().contains(fragment.toLowerCase())){
							tempArray.add(throwAway);
						}
					}
					System.out.println(tempArray.size() + " files were found that contained the text fragment [ '" + fragment + "' ]. Would you like to print the list? [Y/N]");
					if(scanString.nextLine().equals("Y") || scanString.nextLine().equals("y")){
						System.out.println("The folowing files contain the text fragment [' " + fragment + " ']");
						for(int i = tempArray.size(); i>0; i--){
							System.out.println(tempArray.get(i - 1));
						}
					}
				}
				crawl.reset();
			}
			if(choice == 8){
				System.err.println("ARE YOU SURE YOU WANT TO QUIT? [Y/N]");
				if(scanString.nextLine().toLowerCase().equals("y")){
					quit = true;
					System.err.println("TIKA SHUT DOWN, GOODBYE");
				}
			}
			/////////////////////////////////////////
			if(choice == 20){
				String dummyString = scanString.nextLine();
				String dummy2 = (dummyString + "\\\\");
				System.out.println(dummy2);
			}
			////////////////////////////////////////
		}	
	}
}
