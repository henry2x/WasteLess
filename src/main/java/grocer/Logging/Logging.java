package grocer.Logging;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.server.LogStream;

import com.sun.org.slf4j.internal.Logger;


public class Logging {
	
			
	public static String outputLogs() throws FileNotFoundException {
		FileReader fr = new FileReader("./log.txt");
		BufferedReader br = new BufferedReader(fr);

		StringBuilder sb = new StringBuilder();
		String strCurrentLine;
		try {
			while ((strCurrentLine = br.readLine()) != null) {
				if (strCurrentLine.contains("INFO")) {

				} else {
					sb.append(strCurrentLine);
					sb.append("\n");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}

}
