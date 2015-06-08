package model;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import service.*;

import java.util.ArrayList;
import java.util.List;

public class Server implements Runnable {

	ServerSocket myServerSocket;
	Socket myClientSocket;
	Boolean serverON;

	public void Arrancar() {

		serverON = true;

		try {
			myServerSocket = new ServerSocket(4000);
		} catch (IOException ioe) {
			System.out
					.println("Could not create server socket on port 4000. Quitting.");
			System.exit(-1);
		}
		
		try {
			// Accept incoming connections.
			while (serverON) {
				Socket myClientSocket = myServerSocket.accept();

				ClientServiceThread cliThread = new ClientServiceThread(
						myClientSocket);
				cliThread.start();
			}
		} catch (IOException ioe) {
			System.out
					.println("Exception encountered on accept. Ignoring. Stack Trace :");
			ioe.printStackTrace();
		}

		try {
			myServerSocket.close();
			System.out.println("Server Stopped");
		} catch (Exception ioe) {
			System.out.println("Problem stopping server socket");
			System.exit(-1);
		}

	}

	class ClientServiceThread extends Thread {
		Socket myClientSocket;
		ObjectOutputStream outToClient = null;
		// BufferedReader inFromClient = null;
		DataInputStream inFromClient = null;

		public ClientServiceThread() {
			super();
		}

		ClientServiceThread(Socket s) {
			myClientSocket = s;

		}

		public void run() {
			nCliente n = new nCliente();
			List<Cliente> clientes = new ArrayList<>();

			try {
					outToClient = new ObjectOutputStream(
							myClientSocket.getOutputStream());
					// inFromClient = new BufferedReader(new InputStreamReader(
					// myClientSocket.getInputStream()));
					inFromClient = new DataInputStream(
							myClientSocket.getInputStream());

					String comando = inFromClient.readUTF();
					System.out.println(comando);
					System.out.println("Antes de Clientes");
					if (comando.equals("Clientes")) {
						System.out.println("Clientes");

						 clientes = n.listaClientes();
//						clientes.add(new Cliente());
//						clientes.add(new Cliente());
//						clientes.add(new Cliente());

						for (Cliente c : clientes) {
							outToClient.writeObject(c);

						}
					}
					System.out.println("Salio Clientes");
			} catch (Exception e) {
				e.printStackTrace();
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					outToClient.close();
					inFromClient.close();
					myClientSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Arrancar();
	}
}