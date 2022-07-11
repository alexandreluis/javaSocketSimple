package javasocketserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author alexandreluis
 */
public class javaServerSocket
{

    public static void main(String args[]) throws IOException
    {
        String msgToSend = "";
        String msgFromclient = "";

        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        ServerSocket serverSocket = null;

        serverSocket = new ServerSocket(1234);

        Scanner scanner = new Scanner(System.in);

        while (true)
        {
            try
            {
                socket = serverSocket.accept();

                inputStreamReader = new InputStreamReader(socket.getInputStream());
                outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

                bufferedReader = new BufferedReader(inputStreamReader);
                bufferedWriter = new BufferedWriter(outputStreamWriter);

                while (true)
                {
                    if (!msgFromclient.equals(""))
                    {
                        msgFromclient = bufferedReader.readLine();
                        System.out.println("Message Client: " + msgFromclient);
                    }

                    System.out.println("Digite a mensagem para enviar: ");
                    msgToSend = scanner.nextLine();

                    bufferedWriter.write(msgToSend);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();

                    if (msgFromclient.equalsIgnoreCase("bye"))
                    {
                        break;
                    }
                }

                socket.close();
                inputStreamReader.close();
                outputStreamWriter.close();
                bufferedReader.close();
                bufferedWriter.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
