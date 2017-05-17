package br.com.socket.tcp;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientTCP {
	public static void main(String[] args) 
			throws UnknownHostException, IOException {
		
		//Chama a propria classe ClientTCP, usando o seu construtor
		new ClientTCP("127.0.0.1", 7777).execute();
	}

	private String host;
	private int door;

	public ClientTCP(String host, int door){
		this.host = host;
		this.door = door;
	}
	
	public void execute() throws UnknownHostException, IOException {
		Socket client  = new Socket(this.host, this.door);
		System.out.println("The client have been conected in server");
		
		// thread para receber mensagens do servidor
		Receiver r = new Receiver(client.getInputStream());
	    new Thread(r).start();
	    
	    // lê msgs do teclado e manda pro servidor
	    Scanner input = new Scanner(System.in);
	    PrintStream output = new PrintStream(client.getOutputStream());
	    while (input.hasNextLine()) {
	    	output.println(input.nextLine());
	}
	
	output.close();
	input.close();
	client.close();
	}
}
