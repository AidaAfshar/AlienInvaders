package controller.main.client;

import controller.player.Player;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class Client extends Thread {

    Socket socket ;

    String IP ;
    int port ;
    Player player ;

    PrintStream printer ;

    public Client(String IP , int port , Player player){
        this.IP = IP ;
        this.port = port ;
        this.player = player ;
    }


    @Override
    public void run() {
        super.run();
        try {

            socket = new Socket(IP , port) ;
            printer = new PrintStream(socket.getOutputStream()) ;
            player.save();
    //        System.out.println(player);
            printer.println(player);
            printer.flush();
    //        System.out.println("inside run-client");


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
