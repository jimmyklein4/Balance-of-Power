/*
 * The Game Server contains the game logic
 */
package server;

import com.jme3.network.Message;
import messages.ChangeEnergy;
import messages.NewClientMessage;
import messages.UpdateMessage;

/**
 *
 * @author Rolf
 */
public class GameServer implements ServerNetworkListener {

    ServerNetworkHandler networkHandler;
    PlayField playfield;

    // -------------------------------------------------------------------------
    public static void main(String[] args) {
        System.out.println("Starting Game Server at port " + ServerNetworkHandler.SERVERPORT);
        GameServer gs = new GameServer();
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
            }
        }
    }

    // -------------------------------------------------------------------------
    public GameServer() {
        networkHandler = new ServerNetworkHandler(this);
        playfield = new PlayField();
    }


    // -------------------------------------------------------------------------
    // Methods required by ServerNetworkHandler
    public void messageReceived(Message msg) {
        if(msg instanceof ChangeEnergy){
            //We now know an energy change is happening
            ChangeEnergy message = (ChangeEnergy)msg;
            playfield.data.get(message.sender).changeEnergy(message.energy);
            int returned = playfield.data.get(message.reciever).changeEnergy(message.energy);
            if(returned == 0){
                //do nothing
            }
            else{
                playfield.data.get(message.sender).changeEnergy(returned);
            }
            UpdateMessage upd = new UpdateMessage(playfield.data);
        }
    }

    // -------------------------------------------------------------------------
    public Message newConnectionReceived(int connectionID) throws Exception {
        // put player on random playfield
        boolean ok = playfield.addElement(connectionID);
        if (!ok) {
            throw new Exception("Max number of players exceeded.");
        }
        // send entire playfield to new client
        NewClientMessage iniCM = new NewClientMessage(connectionID, playfield.data);
        return (iniCM);
    }
}
