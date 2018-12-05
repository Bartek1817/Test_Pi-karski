package server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import server.Pytanie;
import server.JDBC;

public class SerwerThread extends Thread {
	Socket mySocket;

	public SerwerThread(Socket socket) {
		super(); // konstruktor klasy Thread
		mySocket = socket;
	}

	public void run() {
		try {
			if (mySocket.getLocalPort() == 753) {
				BufferedReader in = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
				String str = in.readLine();
				System.out.println(mySocket.getInetAddress() + " : " + str);
				String[] data = str.split(",");
				OutputStream outputStream = mySocket.getOutputStream();
				ObjectOutputStream objOutputStream = new ObjectOutputStream(outputStream);
				switch (data[0]) {
				case "getPytania":
					System.out.println(JDBC.pobierzPytania());
					objOutputStream.writeObject(JDBC.pobierzPytania());
					objOutputStream.flush();
					break;

				default:
					break;
				}
				System.out.println(mySocket.getInetAddress() + " : roz³¹czam");
			} else if (mySocket.getLocalPort() == 754) {
				System.out.println("Jestem w Porcie 754 Dodaje Dobra ODP");
				InputStream inputStream = mySocket.getInputStream();
				ObjectInputStream objInputStream = null;
				objInputStream = new ObjectInputStream(inputStream);
				Pytanie p = (Pytanie) objInputStream.readObject();
				JDBC.dodajDobre(p.getID());

			} else if (mySocket.getLocalPort() == 755) {
				System.out.println("Jestem w Porcie 755 Dodaje Z³¹ ODP");
				InputStream inputStream = mySocket.getInputStream();
				ObjectInputStream objInputStream = null;
				objInputStream = new ObjectInputStream(inputStream);
				Pytanie p = (Pytanie) objInputStream.readObject();
				JDBC.dodajZ³e(p.getID());
			}
			mySocket.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
