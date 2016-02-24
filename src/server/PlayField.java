package server;

import com.jme3.math.ColorRGBA;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author Rolf
 */
public class PlayField {

    public final static float MINMAX = 10f;
    public final static float RADIUS = 1f;
    public LinkedList<FieldData> data;
    public final int MAXNUMPLAYERS = 8;

    // -------------------------------------------------------------------------
    public PlayField() {
        data = new LinkedList<FieldData>();
    }

    // -------------------------------------------------------------------------
    public boolean addElement(int id) {
        Random rand = new Random();
        float x = rand.nextFloat() * 2 * MINMAX - MINMAX;
        float y = rand.nextFloat() * 2 * MINMAX - MINMAX;
        float z = rand.nextFloat() * 2 * MINMAX - MINMAX;
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        ColorRGBA c = new ColorRGBA(r, g, b, 1.0f);
        //
        if(data.size() < MAXNUMPLAYERS){
            FieldData newData = new FieldData(id, x, y, z, c);
            data.addLast(newData);
            return (true);
        }
        else{
            //(TODO)add new message telling the "client" that he cannot play
            //kick client message
            return(false);
        }
    }
}
