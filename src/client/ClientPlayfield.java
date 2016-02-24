/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Sphere;
import server.FieldData;

/**
 *
 * @author Rolf
 * @author Modifed by Jimmy Klein
 */
public class ClientPlayfield {
    SimpleApplication sa;
    
    public ClientPlayfield(SimpleApplication sa){
        this.sa = sa;
        initKeys();
    }
    
    public void addSphere(FieldData fd){
        Sphere s = new Sphere(32,32,1);
        Geometry sg = new Geometry("",s);
        Material mat = new Material(sa.getAssetManager(), "Common/MatDefs/Light/Lighting.j3md");
        mat.setBoolean("UseMaterialColors", true);
        mat.setColor("Ambient", fd.color);
        mat.setColor("Diffuse", ColorRGBA.Orange);
        mat.setColor("Specular", ColorRGBA.White);
        mat.setFloat("Shininess", 20f); // shininess from 1-128
        sg.setMaterial(mat);
        sg.setLocalTranslation(fd.x, fd.y, fd.z);
        sa.getRootNode().attachChild(sg);
    }
    
    private void initKeys(){
        sa.getInputManager().addMapping("Left", new KeyTrigger(KeyInput.KEY_A));
        sa.getInputManager().addMapping("Right", new KeyTrigger(KeyInput.KEY_D));
        sa.getInputManager().addMapping("Up", new KeyTrigger(KeyInput.KEY_W));
        sa.getInputManager().addMapping("Down", new KeyTrigger(KeyInput.KEY_S));
        
        sa.getInputManager().addListener(analogListener, new String[]{"Left","Right","Up","Down"});
    }
    
    private AnalogListener analogListener = new AnalogListener() {

        //TODO: Add planet movement here
        public void onAnalog(String name, float value, float tpf) {
            if(name.equals("Left")){
                
            }            
            if(name.equals("Right")){
                
            }            
            if(name.equals("Up")){
                
            }            
            if(name.equals("Down")){
                
            }
        }
    };
}
