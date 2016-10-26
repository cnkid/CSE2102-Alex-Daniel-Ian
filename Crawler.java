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
	private ArrayList<String> _fileList = new ArrayList<String>();
	private ArrayList<String> _folderList = new ArrayList<String>();
	private String _choice;
	private int _add;
	
	public Crawler(){
		_numFiles = 0;
		_numFolders = 0;
		_fileList.clear();
		_folderList.clear();
		_add = 0;
	}
	
	public void reset(){
		_numFiles = 0;
		_numFolders = 0;
		_fileList.clear();
		_folderList.clear();
		_add = 0;
	}
	
	
	public void crawl(String path, boolean mod){
		File start = new File(path);
		File[] fList = start.listFiles();
		for (File file : fList){
			if (file.isFile()){
				if(mod){
					System.out.println(file.getAbsolutePath());
				}
				this._numFiles++;
			}
			else if (file.isDirectory()){
				this._folderList.add(this._numFolders, file.getAbsolutePath());
				this._numFolders++;
				this._tempPath = file.getAbsolutePath();
				crawl(this._tempPath, mod);
			}
			if(mod){
				System.out.println(file.getName());
			}
		}
	}
	
	public ArrayList<String> searchExt(String ext, String root){
		File start = new File(root);
		File[] fList = start.listFiles();
		for (File file : fList){
			if (file.isFile() && file.getAbsolutePath().contains(ext)){
				_fileList.add(_numFiles, file.getAbsolutePath());
				_numFiles++;
			}
			else if (file.isDirectory()){
				_numFolders++;
				_tempPath = file.getAbsolutePath();
				String ext1 = ext;
				searchExt(ext1 , _tempPath);
			}
		}
		return _fileList;
	}
	
	public void delete(ArrayList<String> list, Boolean mod){
		Scanner sc = new Scanner(System.in);
		if(!mod){
			System.out.println("YOU ARE ABOUT TO DELETE " + list.size() + " FILES! ARE YOU SURE? [Y/N]");
			this._choice = sc.nextLine();
		}
		else if(mod && _add == 0){
			System.out.println("YOU ARE ABOUT TO DELETE " + list.size() + " FILES AND " + (_numFolders + 1) + " FOLDERS ARE YOU SURE? [Y/N]");
			this._choice = sc.nextLine();
		}
		if(((_choice.equals("Y")) || (_choice.equals("y")))){
			while( list.size() > 0){
				this._numFiles = list.size() - 1;
				String path = list.get(this._numFiles);
				path.replaceAll("\\\\", "\\\\\\\\");
				System.err.println("DELETING FILE [ " + list.get(this._numFiles) + " ]");
				list.remove(this._numFiles);
				File temp = new File(path);
				temp.delete();
			}
			if(mod && _add == 1){
				while(_folderList.size() > 0){
					this._numFolders = this._folderList.size() - 1;
					String path = this._folderList.get(_numFolders);
					path.replaceAll("\\\\", "\\\\\\\\");
					System.err.println("DELETING FOLDER [ " + this._folderList.get(this._numFolders) + " ]");
					this._folderList.remove(this._numFolders);
					File temp = new File(path);
					temp.delete();
				}
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
	
	public void deleteDirectory(String root){
		ArrayList<String> tempList = new ArrayList<String>();
		tempList = this.searchExt(".",root);
		this.delete(tempList,true);
		this.reset();
		this.crawl(root, false);
		this._add = this._add + 1;
		this.delete(tempList,true);
		this.reset();
		File temp1 = new File(root);
		temp1.delete();
		
	}
		
}