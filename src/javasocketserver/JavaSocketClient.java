package javasocketserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author alexandreluis
 */
public class JavaSocketClient
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        String msgToSend;
        
        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
            
        Scanner scanner = new Scanner(System.in);

        try
        {
            socket = new Socket("localhost", 1234);

            inputStreamReader = new InputStreamReader(socket.getInputStream());
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);


            while (true)
            {
                System.out.println("Digite a mensagem para enviar: ");
                msgToSend = scanner.nextLine();
                
                bufferedWriter.write(msgToSend);
                bufferedWriter.newLine();
                bufferedWriter.flush();

                if(!bufferedReader.equals(""))
                {
                    System.out.println("Message Server: " + bufferedReader.readLine());
                }
                

                if (msgToSend.equalsIgnoreCase("BYE"))
                    break;
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                if (socket != null)
                    socket.close();

                if (inputStreamReader != null)
                    inputStreamReader.close();

                if (outputStreamWriter != null)
                    outputStreamWriter.close();

                if (bufferedReader != null)
                    bufferedReader.close();

                if (bufferedWriter != null)
                    bufferedWriter.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}