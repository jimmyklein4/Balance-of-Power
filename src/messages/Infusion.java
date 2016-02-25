/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package messages;

import com.jme3.network.AbstractMessage;

/**
 *
 * @author Theo
 */
public class Infusion extends AbstractMessage{
    public int sender;
    public int reciever;
    public boolean started;
    
    public Infusion(){
        
    }
    public Infusion(int sender, int reciever, boolean started){
        this.sender = sender;
        this.reciever = reciever;
        this.started = started;
    }
}
