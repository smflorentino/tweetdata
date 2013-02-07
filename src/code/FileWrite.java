package code;
import java.io.*;

public class FileWrite {
	FileWriter _fstream;
	BufferedWriter _out;
	
	public FileWrite() {
		try {
			_fstream = new FileWriter("out.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		_out = new BufferedWriter(_fstream);
	}
	
	public void writeLine(String s) {
		try {
			_out.write(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void closeFile() {
		try {
			_out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
