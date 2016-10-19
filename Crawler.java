import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.Scanner;

public class Crawler{
	private String _tempPath = "";
	private int _numFiles = 0;
	private int _numFolders = 0;
	private ArrayList<String> _list = new ArrayList<String>();
	
	public void reset(){
		_numFiles = 0;
		_numFolders = 0;
		_list.clear();
	}
	
	
	public void crawl(String path){
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
	}
	
	public ArrayList<String> searchExt(String ext, String root){
		File start = new File(root);
		File[] fList = start.listFiles();
		for (File file : fList){
			if (file.isFile() && file.getAbsolutePath().contains(ext)){
				_list.add(_numFiles, file.getAbsolutePath());
				_numFiles++;
			}
			else if (file.isDirectory()){
				_numFolders++;
				_tempPath = file.getAbsolutePath();
				String ext1 = ext;
				searchExt(ext1 , _tempPath);
			}
		}
		return _list;
	}
	
	public void delete(ArrayList<String> list){
		Scanner sc = new Scanner(System.in);
		System.out.println("YOU ARE ABOUT TO DELETE " + list.size() + " FILES! ARE YOU SURE? [Y/N]");
		String choice = sc.nextLine();
		if((choice.equals("Y")) || (choice.equals("y"))){
			while( list.size() > 0){
				_numFiles = list.size() - 1;
				String path = list.get(_numFiles);
				path.replaceAll("\\\\", "\\\\\\\\");
				list.remove(_numFiles);
				File temp = new File(path);
				temp.delete();
			}
			
		}
		else{
			System.err.println("OPERATION ABORTED");
		}
	}
	
	public void displayList(ArrayList<String> list){
		while(list.size() > 0){
			System.out.println(list.size() + ": [ " + list.get(list.size() - 1) + " ]");
			list.remove(list.size() - 1);
		}
	}
		
}