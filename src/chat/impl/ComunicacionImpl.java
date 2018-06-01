package chat.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

import chat.ifaces.Comunicacion;
import chat.ifaces.Controlador;
import chat.impl.DialogoPuerto.PuertoAlias;

// Clase a implementar 
public class ComunicacionImpl implements Comunicacion {
	
	Controlador c;
	MulticastSocket multiSock;
	String userAlias;
	DatagramPacket p = new DatagramPacket(new byte[256], 256);
	

	@Override
	public void crearSocket(PuertoAlias pa) {
		
		try {
			
			multiSock = new MulticastSocket(pa.puerto);
			this.userAlias = pa.alias;
			
		} catch (IOException e) {
			
			System.out.println(e.getMessage());
			
		}
		
	}

	@Override
	public void setControlador(Controlador c) {
		
		this.c = c;
		
	}

	@Override
	public void runReceptor() {
		
		DatagramSocket dataSock;		
		DatagramPacket data = new DatagramPacket(new byte[256], 256);
		
		try {
			
			dataSock = new DatagramSocket();
			multiSock.send(data);
			
		} catch (SocketException e) {
			
			System.out.println(e.getMessage());
			
		} catch (IOException e) {
			
			System.out.println(e.getMessage());
			
		}
		
		
		
		
	}

	@Override
	public void envia(InetSocketAddress sa, String mensaje) {
		
		DatagramSocket dataSock;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Write your message.\n");
		
		byte[] word = sc.nextLine().getBytes();
		
		DatagramPacket data = new DatagramPacket(word, word.length, multiSock.getInetAddress(), multiSock.getLocalPort());
		
		try {
			
			dataSock = new DatagramSocket();
			multiSock.receive(data);
			dataSock.close();
			
		} catch (SocketException e) {
			
			System.out.println(e.getMessage());
			
		} catch (IOException e) {
			
			System.out.println(e.getMessage());
			
		}
		
	}

	@Override
	public void joinGroup(InetAddress multi) {
		
		try {
			
			multiSock.joinGroup(multi);
			
		} catch (IOException e) {
			
			System.out.println(e.getMessage());
			
		}
		
	}

	@Override
	public void leaveGroup(InetAddress multi) {
		
		try {
			
			multiSock.leaveGroup(multi);
			
		} catch (IOException e) {
			
			System.out.println(e.getMessage());
			
		}
		
	}

}
