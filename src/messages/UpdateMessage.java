/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package messages;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;
import java.util.LinkedList;
import server.FieldData;

/**
 *
 * @author Expression Chris is undefined on line 12, column 14 in Templates/Classes/Class.java.
 */
public class UpdateMessage extends AbstractMessage{
    public LinkedList<FieldData> field;
    
    public UpdateMessage(){
        
    }
    
    public UpdateMessage(LinkedList<FieldData> field){
        this.field = field;
    }
}
