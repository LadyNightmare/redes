package redes;

import java.io.*;
import java.net.*;

public class Client {

	static final int serverPort = 12345;

	public static void main(String[] args) throws IOException {
		
		InetAddress serverAddr = InetAddress.getByName("");

		try {

			Socket sockfd = new Socket(serverAddr, serverPort);
			System.out.println("Local connection: " + serverAddr);

			BufferedReader in = new BufferedReader(new InputStreamReader(sockfd.getInputStream()));
			PrintWriter out = new PrintWriter(sockfd.getOutputStream(), true);
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
			String userInput;

			while ((userInput = stdIn.readLine()) != null) {

				if (userInput.equals(".")) {

					break;

				}

				out.println(userInput);
				System.out.println("echo: " + in.readLine());

			}

			out.close();
			in.close();
			stdIn.close();
			sockfd.close();

		} catch (UnknownHostException e) {

			System.err.println("Unknown: " + serverAddr);
			System.exit(1);

		}

	}

}
