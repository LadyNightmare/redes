package chat.impl;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;

import chat.ifaces.Comunicacion;
import chat.ifaces.Controlador;
import chat.impl.DialogoPuerto.PuertoAlias;

// Clase a implementar 
public class ComunicacionImpl implements Comunicacion {
	
	Controlador c;
	MulticastSocket multiSock;
	String userAlias;

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
		
		
		
	}

	@Override
	public void envia(InetSocketAddress sa, String mensaje) {
		
		
		
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
