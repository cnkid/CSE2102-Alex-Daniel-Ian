import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;


public class file_opener {
	public String open (String filePath) throws FileNotFoundException{
		File file = new File(filePath);
		try {
			Scanner scan = new Scanner(file);
			while (scan.hasNextLine()){
				System.out.println(scan.nextLine());
			}
			
		}
		catch(FileNotFoundException e){
			System.err.println("FILE WAS NOT FOUND, VERIFY PATH OF FILE AND/OR FILE NAME");
		}
		return "";
	}

}
