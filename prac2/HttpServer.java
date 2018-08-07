import java.net.*;
import java.io.*;
import java.util.*;

class HttpServer
{
	public static void main(String args[])
	{
		System.out.println("Web Server Starting");
		int port = 8080;
		try
		{
			ServerSocket server = new ServerSocket(port);
			while (true)
			{
				System.out.println("Waiting for connection...");
				Socket client = server.accept();				
				HttpServerSession sesh = new HttpServerSession(client);				
				System.out.println("Connection accepted");
				InetAddress IP = client.getInetAddress();
				String address = IP.getHostAddress();
				System.out.println("IP: " + address);
				sesh.run();
				//Done with the client
				//client.close();
			}
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
}
				 
				
