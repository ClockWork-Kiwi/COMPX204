import java.net.*;
import java.io.*;
import java.util.*;

class HttpServerSession extends Thread {
    private Socket socket;

    public HttpServerSession(Socket accepted) {
        socket = accepted;
    }

    public void run(String filePath) {
        try {
            BufferedOutputStream writer = new BufferedOutputStream(socket.getOutputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String request = reader.readLine();
            String[] parts = request.split(" ");
            //System.out.println(parts[1].substring(1));
            if (parts.length != 3) {

            } else {
                String line;
                while (true) {
                    line = reader.readLine();
                    if (line == null) {
                        //handle end of file
                    }

                    if (line.compareTo("") == 0) {
                        break;
                    }
                }
                if (parts[0].compareTo("GET") == 0) {
                    String fileName = parts[1].substring(1);
                    File tmpDir = new File(filePath + fileName);
                    //File tmpDir = new File("/Users/GGPC/Desktop/Uni Work/204/COMPX204/prac2/" + fileName);
                    if (tmpDir.exists()) {
                        FileInputStream fileReader = new FileInputStream(fileName);
                        println(writer, "HTTP/1.1 200 OK");
                        println(writer, "");
                        byte[] array = new byte[1024];
                        int something = 0;
                        while ((something = fileReader.read(array)) != -1) {
                            writer.write(array, 0, something);
                            //Thread.sleep(100);
                        }
                        fileReader.close();
                    } else {

                        println(writer, "HTTP/1.1 404 Not Found");
                        println(writer, "");
                        println(writer, "Error 404: File Not Found");
                    }
                    writer.flush();
                    writer.close();
                    socket.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private void println(BufferedOutputStream writer, String message)
            throws IOException {

        String toSend = message + "\r\n";
        byte[] array = toSend.getBytes();
        for (int i = 0; i < array.length; i++) {
            writer.write(array[i]);
        }
        //System.out.println(toSend);
        return;
    }

}
