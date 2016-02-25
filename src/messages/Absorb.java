/*
  * To change this template, choose Tools | Templates
  * and open the template in the editor.
  */
 package messages;
 
 import com.jme3.network.AbstractMessage;
 import com.jme3.network.serializing.Serializable;
 
 /**
  *
  * @author Theo
  */
 @Serializable
 public class Absorb extends AbstractMessage{
     public int sender;
     public int reciever;
     public boolean started;
     
     public Absorb(){
         
     }
     public Absorb(int sender, int reciever, boolean start_stop){
         this.sender = sender;
          this.reciever = reciever;
          this.started = start_stop;
      }
 }

