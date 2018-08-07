import java.net.*;
import java.io.*;
import java.util.*;

class HttpServerSession extends Thread
{
	private Socket socket;

	public HttpServerSession(Socket accepted)
	{
		socket = accepted;
	}

	public void run()
	{
		try
		{
			BufferedReader reader = new BufferedReader( new InputStreamReader(socket.getInputStream())); 
			String request = reader.readLine();
			String[] parts = request.split(" ");
			if (parts.length != 3)
			{

			}
			else
			{
				String line;
				while(true)
				{
					line = reader.readLine();
					if (line == null)
					{
						//handle end of file
					}
					if (line.compareTo("") == 0)
					{
						break;
					}
				}
				BufferedOutputStream writer = new BufferedOutputStream(socket.getOutputStream());
				println(writer, line);
				println(writer, "Hello World");
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	private void println(BufferedOutputStream writer, String message)
		throws IOException
	{

		String toSend = message + "\r\n";
		byte[] array = toSend.getBytes();
		for (int i = 0; i < array.length; i++)
		{
			writer.write(array[i]);
		}
		return;
	}

}
