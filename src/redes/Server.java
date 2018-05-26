package redes;

import java.io.*;
import java.net.*;

public class Server {

	public static void main(String[] args) {

		String line;

		try {

			ServerSocket sockfd = new ServerSocket(12345);
			System.out.println("Server initialization..." + sockfd);

			while (true) {

				Socket newsockfd = sockfd.accept();
				System.out.println("New client, socket " + newsockfd);

				BufferedReader in = new BufferedReader(new InputStreamReader(newsockfd.getInputStream()));
				PrintWriter out = new PrintWriter(new OutputStreamWriter(newsockfd.getOutputStream()), true);

				boolean salir = false;

				while (!salir) {

					line = in.readLine();
					if (line != null) {

						out.println(line);

					} else {

						salir = true;

					}

				}

				newsockfd.close();

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}
