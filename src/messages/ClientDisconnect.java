package messages;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

/**
 * @author Chris
 */
@Serializable
public class ClientDisconnect extends AbstractMessage{
    int ID;
    
    public ClientDisconnect(){
        
    }
    
    public ClientDisconnect(int ID){
        this.ID = ID;
    }
}
