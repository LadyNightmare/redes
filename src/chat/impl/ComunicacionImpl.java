package chat.impl;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.util.Scanner;

import javax.print.attribute.SetOfIntegerSyntax;
import javax.swing.JOptionPane;

import chat.ifaces.Comunicacion;
import chat.ifaces.Controlador;
import chat.impl.DialogoPuerto.PuertoAlias;


// Clase a implementar 
public class ComunicacionImpl implements Comunicacion {
	
	Controlador c;
	MulticastSocket multiSock;
	String userAlias;
	
	//Un solo socket para todo
	

	@Override
	public void crearSocket(PuertoAlias pa) {
		
		try {
			
			multiSock = new MulticastSocket(pa.puerto);
			this.userAlias = pa.alias;
			//InetAddress add = InetAddress.getByName("192.168.0.255");
			InetAddress add = InetAddress.getByName("192.168.0.105");
			multiSock.setInterface(add);
			
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
				
		DatagramPacket data = new DatagramPacket(new byte[1024], 1024);
		
		try {
			
			multiSock.receive(data);
			c.mostrarMensaje(multiSock.getLocalSocketAddress(), userAlias, data.toString());
			
		} catch (SocketException e) {
			
			System.out.println(e.getMessage());
			
		} catch (IOException e) {
			
			System.out.println(e.getMessage());
			
		}
		
		
		
		
	}

	@Override
	public void envia(InetSocketAddress sa, String mensaje) {		
		
		//String input = JOptionPane.showInputDialog("Insert your words.\n");
				
		//byte[] word = input.getBytes();
		
		DatagramPacket data = new DatagramPacket(mensaje.getBytes(), mensaje.length(), multiSock.getInetAddress(), multiSock.getLocalPort());
		
		try {
			
			multiSock.send(data);
			
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
