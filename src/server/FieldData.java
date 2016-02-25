/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import com.jme3.math.ColorRGBA;
import com.jme3.network.serializing.Serializable;

/**
 *
 * @author Rolf
 */
// -------------------------------------------------------------------------
@Serializable
public class FieldData {
    private final int INITIALENERGYLEVEL = 100;
    public int id;
    private int energyLevel;
    public float x, y, z;
    public ColorRGBA color;

    public FieldData() {
    }

    FieldData(int id, float x, float y, float z, ColorRGBA c) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.z = z;
        this.color = c;
        energyLevel = INITIALENERGYLEVEL;
    }
    
    public int getEnergyLevel(){
        return energyLevel;
    }
    
    public int changeEnergy(int amount){
        if(energyLevel > 0){
            if(energyLevel + amount > 0){
                energyLevel += amount;
            }
            else{
                energyLevel = 0;
                return(-(energyLevel + amount));
            }
            return -1;
        }
        return 0;
    }
}
