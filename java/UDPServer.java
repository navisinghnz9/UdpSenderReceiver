import java.io.*;
import java.net.*;

class UDPReceiverThread extends Thread {

    public void run(){

        try {

            DatagramSocket serverSocket = new DatagramSocket(9791);
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];

            while(true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                System.out.println("LISTENING...");

                serverSocket.receive(receivePacket);
                String sentence = new String( receivePacket.getData());
                System.out.println("RECEIVED: " + sentence);

                InetAddress IPAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();

                String capitalizedSentence = sentence.toUpperCase();
                sendData = capitalizedSentence.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                serverSocket.send(sendPacket);
            }
        } catch(Exception exp) {
            System.out.println("UDPReceiverThread got exception\n:" + exp);
        }
    }
}

class UDPServer {
    public static void main(String args[]) throws Exception {
        UDPReceiverThread receiver = new UDPReceiverThread();
        receiver.start();

        // wating for thread to finish
        receiver.join();
    }
}
