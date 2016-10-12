import java.io.File;
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
	
}