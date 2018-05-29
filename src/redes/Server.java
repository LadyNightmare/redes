package redes;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server {
	
	public static String crypto (String word, int value) {
		
		StringBuilder sb=new StringBuilder();
		char currentChar;
		int letters = 'Z' - 'A' +1;
		value %= letters;
		
		for(int i = 0; i < word.length(); ++i){
			
			currentChar = word.charAt(i);
			
			if(Character.isLetter(currentChar)) {
				
				if(Character.isLowerCase(currentChar) && 
						currentChar + value > 'z' ||
						Character.isUpperCase(currentChar) &&
						currentChar + value > 'Z') {
					
					currentChar -= letters;
					
				}
				
				currentChar += value;
				 
				
			}
			
			sb.append(currentChar);
			
		}
		
		return sb.toString();
		
	}

	public static void main(String[] args) throws UnknownHostException {
		
		int serverPort;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("java Server ");
		
		serverPort = sc.nextInt();
		
		sc.close();

		String line;

		try {

			//Creating the server socket for the communication
			
			ServerSocket sockfd = new ServerSocket(serverPort);
			sockfd.setReceiveBufferSize(1);
			System.out.println("Server initialization..." + sockfd);
			System.out.println("Waiting for a new client...");

			while (true) {
				

				//Creating the socket to accept the connection
				
				Socket newsockfd = sockfd.accept();
				System.out.println("New client, socket " + newsockfd);

				//Creating buffer and writer to the connected client
				
				BufferedReader in = new BufferedReader(new InputStreamReader(newsockfd.getInputStream()));
				PrintWriter out = new PrintWriter(newsockfd.getOutputStream(), true);
				
				//We send the message to the connected client
				
				out.println("Welcome to the cryptography server. Enter a number"
						+ " to codify your text and your text.");
				
				boolean salir = false;
				
				String word;
				
				//if there's nothing left to print, we close the connection

				while (!salir) {
					
					newsockfd.setSoTimeout(40000);
					
					line = in.readLine();
					
					if (line != null) {
						
						if (line.equals("0")) {
							
							out.println("ok.");
							salir = true;
							System.out.println("Waiting for a new client...");
							
						} else {
							
							word = in.readLine();
						
							out.println(crypto(word,Integer.parseInt(line)));
						
						}
						
					}

					

				}

				newsockfd.close();

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}
