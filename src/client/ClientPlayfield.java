/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Sphere;
import java.util.List;
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
    }
    
    public void addSphere(FieldData fd, Node sphereNode){
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
        
        sphereNode.attachChild(sg);
    }
    
}
