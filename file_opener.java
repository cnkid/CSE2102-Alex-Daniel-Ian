
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;


public class file_opener {
	file_opener() throws IOException, TikaException{
		Tika tika = new Tika();
		File throwAway = new File("H:\\group project\\test.docx");
		System.err.println("Tika initializing, this usually only takes a few seconds");
		tika.parseToString(throwAway);
		System.err.println("Tika initialized");
		
	}

	public String open(File file) throws IOException, TikaException{
		Tika tika = new Tika();
		try{
			String testString = tika.parseToString(file);
			return testString;
		}catch(Exception e){System.err.println(file.getAbsolutePath() + " WAS NOT FOUND OR COULD NOT BE READ");}
		return null;            
	}
	
	
	public boolean supportedText(File file){
		return false;
	}
}