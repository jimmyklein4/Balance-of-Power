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
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import server.FieldData;
import java.util.concurrent.Callable;


/**
 *
 * @author Rolf
 * @author Modifed by Jimmy Klein
 */
public class ClientPlayfield {
    SimpleApplication sa;
    LinkedList<FieldData> fd1;
    
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
        
        sg.setName("sphere"+ fd.id);
        
        sphereNode.attachChild(sg);
    }
    
    public void updatePlayfield(LinkedList<FieldData> fd, Node sphereNode){
        ListIterator<FieldData> i;
        FieldData f = null;
        i = fd.listIterator();
        
        while(i.hasNext()){
            f = i.next();
            final Spatial g = sphereNode.getChild("sphere"+f.id);
            final float s = (float)f.getEnergyLevel()/ 100f;
            System.out.println("float: "+s);
            //g.setLocalScale(s);
            //sa.enqueue(g);
            sa.enqueue(new Callable<Spatial>(){
                public Spatial call(){
                    g.setLocalScale(s);
                    return g;
                }
            });
        }

    }
    
}
