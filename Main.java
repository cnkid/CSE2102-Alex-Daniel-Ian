import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Hello, please select the function you wish to perform");
		System.out.println("1: discover and print all folders, subfolder, and files");
		System.out.println("2: Delete files");
		System.out.println("3: Move files");
		System.out.println("4: Scan file type");
		Scanner scanInt = new Scanner(System.in);
		Scanner scanString = new Scanner(System.in);
		int choice = scanInt.nextInt();
		if(choice == 1){
			Crawler crawl = new Crawler();
			System.out.println("enter root folder (NOTE ALL / MUST BE REPLACED BY //)");
			String root = scanString.nextLine();
			crawl.crawl(root);
		}
		
	}

}
