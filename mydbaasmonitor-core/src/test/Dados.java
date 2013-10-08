package test;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Dados {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File file = new File("/home/david/Desktop/dados.txt");
		StringBuilder dados = new StringBuilder();
        try {  
            Scanner arq = new Scanner(file);  
            while (arq.hasNextLine()) {
                dados.append(","+arq.nextLine().trim());
            }
            System.out.println(dados.toString());
            arq.close();  
        } catch (IOException ioe) {  
            ioe.printStackTrace();  
        }
	}

}
