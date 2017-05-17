package br.com.socket.tcp;

import java.io.InputStream;
import java.util.Scanner;

public class ClearClient implements Runnable{
	private InputStream client;
	private ServerTCP server;
	
	public ClearClient(InputStream client, ServerTCP server){
		this.client = client;
		this.server = server;
	}
	
	@Override
	public void run() {
		//coments
		Scanner s = new Scanner(this.client);
		while (s.hasNextLine()){
			server.sendMessage(s.nextLine());
		}
		s.close();
	}
	

}
