package redes;

import java.io.*;
import java.net.*;
import java.util.Scanner;

import org.omg.Messaging.SyncScopeHelper;

public class Client {

	static String IP;
	static int serverPort;

	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in).useDelimiter("\\s");
		
		System.out.println("java Client ");
		
		IP = sc.next();
		serverPort = sc.nextInt();
		
		String incoming;
		
		InetAddress serverAddr = InetAddress.getByName(IP);

		try {
			
			Socket sockfd = new Socket(serverAddr, serverPort);
			System.out.println("Local connection: " + serverAddr);

			BufferedReader in = new BufferedReader(new InputStreamReader(sockfd.getInputStream()));
			PrintWriter out = new PrintWriter(sockfd.getOutputStream(), true);
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
			String userInput;
			
			System.out.println("Connected to " + IP + ":" + serverPort);
			
			System.out.println(in.readLine());

			while ((userInput = stdIn.readLine()) != null) {

				if (userInput.equals("0")) {
					
					out.println(userInput);
					System.out.println("Waiting for a response...");
					System.out.println(in.readLine());

					break;

				} else {
					
					
					out.println(userInput);
					userInput = stdIn.readLine();
					out.println(userInput);
					System.out.println("Waiting for a response...");
					incoming = in.readLine();
					
					if(incoming == null) {
						
						userInput = null;
						
						out.close();
						in.close();
						stdIn.close();
						sockfd.close();
						sc.close();
						
					} else {
						
					System.out.println("echo: " + incoming);
					
					}
					
				}

			}
			
			out.close();
			in.close();
			stdIn.close();
			sockfd.close();
			sc.close();


		} catch (UnknownHostException e) {

			System.err.println("Unknown: " + serverAddr);
			System.exit(1);

		} catch (IOException e) {
			
			System.err.println("Server has closed.");
			System.exit(1);

			
		}

	}

}
