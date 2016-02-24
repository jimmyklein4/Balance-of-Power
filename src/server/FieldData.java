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
    
    public boolean addEnergy(int add){
        if(energyLevel > 0){
            energyLevel += add;
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * @param sub
     * @return -1 if true, 0 if false
     */
    public int subtractEnergy(int sub){
        if(energyLevel > 0){
            if(energyLevel - sub > 0){
                energyLevel -= sub;
            }
            else{
                energyLevel = 0;
                return (sub - energyLevel);
            }
            return -1;
        }
        else{
            return 0;
        }
    }
}
