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
public class ChangeEnergy extends AbstractMessage{
    public int energy;
    public int player;
    
    public ChangeEnergy(){
        
    }
    //----------------------------------
    public ChangeEnergy(int en, int player){
        energy = en;
        this.player = player;
    }
    
}
