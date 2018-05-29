package chat.impl;

import java.net.InetAddress;
import java.net.InetSocketAddress;

import chat.ifaces.Comunicacion;
import chat.ifaces.Controlador;
import chat.impl.DialogoPuerto.PuertoAlias;

// Clase a implementar 
public class ComunicacionImpl implements Comunicacion {

	@Override
	public void crearSocket(PuertoAlias pa) {
	}

	@Override
	public void setControlador(Controlador c) {
	}

	@Override
	public void runReceptor() {
	}

	@Override
	public void envia(InetSocketAddress sa, String mensaje) {
	}

	@Override
	public void joinGroup(InetAddress multi) {
	}

	@Override
	public void leaveGroup(InetAddress multi) {
	}

}
