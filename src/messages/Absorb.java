/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package messages;

/**
 *
 * @author Theo
 */
public class Absorb {
    public int sender;
    public int reciever;
    public boolean start_stop;
    
    public Absorb(){
        
    }
    public Absorb(int sender, int reciever){
        this.sender = sender;
        this.reciever = reciever;
    }
    public Absorb(boolean start_stop){
        this.start_stop = start_stop;
    }
}
