package chat.impl;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
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
		
		while(true) {
			
			byte[] myData = new byte[1024];
			
			DatagramPacket data = new DatagramPacket(myData, myData.length);
			
			try {
				
				multiSock.receive(data);
				
			} catch (IOException e) {
				
				System.out.println(e.getMessage());
				
			}
			
			String fullMsg, username;
			
			String[] message;
			InetSocketAddress IP;
			
			fullMsg = new String(data.getData(), StandardCharsets.UTF_8);
			message = fullMsg.split("!");
			
			username = message[1];
			
			try {
				
				if (InetAddress.getByName(message[0]).isMulticastAddress()) {
					
					IP = new InetSocketAddress(InetAddress.getByName(message[0]), data.getPort());
					
				} else {
					
					IP = new InetSocketAddress(data.getAddress(), data.getPort());
					
				}
				
				StringBuilder sb = new StringBuilder();
				
				for (int i = 2; i < message.length; ++i) {
					
					sb.append(message[i]);
					
				}
				
				if (!username.equals(userAlias)) {
					
					c.mostrarMensaje(IP, username, sb.toString());
					
				}
				
			} catch (Exception e) {
				
				System.out.println(e.getMessage());
				
			}
			
		}		
		
	}

	@Override
	public void envia(InetSocketAddress sa, String mensaje) {		
		
		byte[] myData = mensaje.getBytes();
		
		DatagramPacket data = new DatagramPacket(myData, myData.length, sa.getAddress(), sa.getPort());
		
		try {
			
			multiSock.send(data);
			
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
