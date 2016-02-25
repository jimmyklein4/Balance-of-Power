/*
 * The Game Server contains the game logic
 */
package server;

import com.jme3.network.Message;
import messages.Absorb;
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
    Absorb a[] = new Absorb[8];

    // -------------------------------------------------------------------------
    public static void main(String[] args) {
        System.out.println("Starting Game Server at port " + ServerNetworkHandler.SERVERPORT);
        GameServer gs = new GameServer();
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
            }
            for(Absorb b : gs.a){
                if(b.started){
                    gs.playfield.data.get(b.sender).changeEnergy(-1);
                }
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
            updateClients(upd);
        }
        if(msg instanceof Absorb){
            Absorb message = (Absorb)msg;
            a[message.sender] = message;
            playfield.data.get(a[message.sender].sender).changeEnergy(0);
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
    
    private void updateClients(Message msg){
        networkHandler.broadcast(msg);
    }
}
