package controller.main.Connection.client;

import controller.player.Player;
import model.dataManagement.DataManager;
import view.screen.ClientPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class ConnectionServiceForClient extends Thread{

    PrintWriter printer ;
    BufferedReader reader ;

    ClientPanel clientPanel ;

    Player clientPlayer;
    ArrayList<Player> otherPlayers ;

    public ConnectionServiceForClient(OutputStream outputStream, InputStream inputStream,Player clientPlayer , ClientPanel clientPanel){
        printer = new PrintWriter(outputStream) ;
        reader = new BufferedReader(new InputStreamReader(inputStream)) ;
        this.clientPlayer = clientPlayer ;
        this.clientPanel = clientPanel ;

        initialize() ;
    }

    void initialize(){

    }


    @Override
    public void run() {
        super.run();

        try {

            sendClientPlayerToServer();
            receiveOtherPlayersFromServer();


            prepareTimer();
            timer.start();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void sendClientPlayerToServer(){
        clientPlayer.save();
        printer.println(clientPlayer);
        printer.flush();
    }



    public void receiveOtherPlayersFromServer() throws IOException, InterruptedException {
        otherPlayers = new ArrayList<>();

        String data ;
        while (reader.ready() && (data = reader.readLine()) !=null){
                Player player = DataManager.load(data);
                otherPlayers.add(player);
                clientPanel.addPlayer(player);

        }
    }




    Timer timer ;
    public void prepareTimer(){
        timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    receiveNewPlayerFromServer();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }) ;

    }

    public void stopTimer(){
        timer.stop();
    }

    public void retartTimer(){
        timer.restart();
    }

    void receiveNewPlayerFromServer() throws IOException {
        String data ;
        if(reader.ready() &&(data = reader.readLine())!=null){
            Player newPlayer = DataManager.load(data) ;
            otherPlayers.add(newPlayer) ;
            clientPanel.addPlayer(newPlayer);
        }

    }

    //getter


    public ArrayList<Player> getOtherPlayers() {
        return otherPlayers;
    }
}
