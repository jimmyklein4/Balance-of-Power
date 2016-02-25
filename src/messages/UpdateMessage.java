package messages;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;
import java.util.LinkedList;
import server.FieldData;

/**
 *
 * @author Chris
 */
@Serializable
public class UpdateMessage extends AbstractMessage{
    public LinkedList<FieldData> field;
    
    public UpdateMessage(){
        
    }
    
    public UpdateMessage(LinkedList<FieldData> field){
        this.field = field;
    }
}
