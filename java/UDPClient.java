import java.io.*;
import java.net.*;

class UDPSenderThread extends Thread {

    public void run(){

        try {
            DatagramSocket clientSocket = new DatagramSocket();

            InetAddress IPAddress = InetAddress.getByName("localhost");

            byte[] sendData = new byte[1024];
            byte[] receiveData = new byte[1024];
            String sentence = "hello there";
            sendData = sentence.getBytes();

            // sending
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9791);
            clientSocket.send(sendPacket);

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            String modifiedSentence = new String(receivePacket.getData());

            System.out.println("FROM SERVER:" + modifiedSentence);
            clientSocket.close();
        } catch(Exception exp) {
            System.out.println("UDPSenderThread got exception\n:" + exp);
        }
    }
}

class UDPClient{
    public static void main(String args[]) throws Exception {
        UDPSenderThread sender = new UDPSenderThread();
        sender.start();

        // wating for thread to finish
        sender.join();
    }
}
