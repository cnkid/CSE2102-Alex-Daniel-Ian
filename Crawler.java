import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.FileSystems;
import java.util.Scanner;

public class Crawler{
	private String _tempPath = "";
	private int _numFiles = 0;
	private int _numFolders = 0;
	
	public int crawl(String path){
		File start = new File(path);
		File[] fList = start.listFiles();
		for (File file : fList){
			if (file.isFile()){
				System.out.println(file.getAbsolutePath());
				_numFiles++;
			}
			else if (file.isDirectory()){
				_numFolders++;
				_tempPath = file.getAbsolutePath();
				crawl(_tempPath);
			}
			System.out.println(file.getName());
		}
		//System.out.println("there are " + _numFiles + " Files");
		//System.out.println("there are " + _numFolders + " Folders");
		return _numFolders;
	}
	
	public void deleteExt(String ext, String root){
		Scanner sc = new Scanner(System.in);
		File start = new File(root);
		File[] fList = start.listFiles();
		File[] deleteArray = start.listFiles();
		int arrayNum = 0;
		for (File file : fList){
			if (file.isFile()){
				if(file.getName().contains(ext)){
					deleteArray[arrayNum] = file;
					arrayNum ++;
				}
			}
			else if (file.isDirectory()){
				_numFolders++;
				_tempPath = file.getAbsolutePath();
			}
		}
		System.out.println("YOU ARE ABOUT TO DELETE " + arrayNum + " FILES! ARE YOU SURE? [Y/N]");
		if(sc.nextLine() .equals("Y")){
			File tempfile = null;
			while(arrayNum != 0){
				arrayNum = arrayNum - 1;
				tempfile = deleteArray[arrayNum];
				tempfile.delete();
			}
		}
		
	}
	
}