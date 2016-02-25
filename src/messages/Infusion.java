/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package messages;

/**
 *
 * @author Theo
 */
public class Infusion {
    public int sender;
    public int reciever;
    public boolean start_stop;
    
    public Infusion(){
        
    }
    public Infusion(int sender, int reciever, boolean start_stop){
        this.sender = sender;
        this.reciever = reciever;
    }
}
