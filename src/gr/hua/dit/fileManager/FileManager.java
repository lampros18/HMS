package gr.hua.dit.fileManager;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
	public void writeCredentialsFile(String username, String password) {
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter("employee_credentials.dat", true));
			writer.append("username : " + username + ", password : " + password+"\n");
			writer.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
