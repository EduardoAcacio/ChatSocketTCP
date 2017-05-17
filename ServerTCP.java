package br.com.socket.tcp;


import java.io.IOException;
import java.io.PrintStream;
import java.net.*;
import java.util.ArrayList;


public class ServerTCP {

	public static void main(String args[]) throws IOException{

		//Instanciando o objeto 'servidor' com a porta '12345'
		new ServerTCP(7777).execute();
	}

	private int door;
	//private List<PrintStream> clients;
	private ArrayList<PrintStream> clients;

	public ServerTCP(int door) {
		this.door = door;
		this.clients = new ArrayList<PrintStream>();
	}

	private void execute() throws IOException {
		@SuppressWarnings("resource")
		ServerSocket server = new ServerSocket(this.door);
		System.out.println("Door 7777 is open");

		while (true) {
			//Mostra IP do Cliente conectado
			Socket client = server.accept();
			System.out.println("New connection is open with client: " +   
					client.getInetAddress().getHostAddress());

			// adiciona saida do cliente à lista
			PrintStream ps = new PrintStream(client.getOutputStream());
			this.clients.add(ps);

			// cria tratador de cliente numa nova thread
			ClearClient tc = new ClearClient(client.getInputStream(), this);
			new Thread(tc).start();
		}
	}
	
	public void sendMessage(String msg) {
		// envia msg para todo mundo
		for (PrintStream client : this.clients) {
			client.println(msg);
		}
	}
}
