package client;

import com.jme3.app.DebugKeysAppState;
import com.jme3.input.MouseInput;
import com.jme3.app.SimpleApplication;
import com.jme3.app.StatsAppState;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.network.Message;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.BloomFilter;
import com.jme3.scene.Spatial;
import com.jme3.shadow.DirectionalLightShadowRenderer;
import com.jme3.system.AppSettings;
import com.jme3.util.SkyFactory;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.LinkedList;
import messages.Absorb;
import messages.ChangeEnergy;
import messages.NewClientMessage;
import messages.UpdateMessage;
import server.FieldData;

public class GameClient extends SimpleApplication implements ClientNetworkListener, ActionListener {
    //

    private int ID = -1;
    protected ClientNetworkHandler networkHandler;
    private ClientPlayfield playfield;
    private boolean sent = true;
    public LinkedList<FieldData> data;

    // -------------------------------------------------------------------------
    public static void main(String[] args) {
        System.out.println("Starting Client");
        //
        AppSettings aps = getAppSettings();
        //
        GameClient app = new GameClient();
        app.setShowSettings(false);
        app.setSettings(aps);
        app.start();
    }

    // -------------------------------------------------------------------------
    public GameClient() {
        // this constructor has no fly cam!
        super(new StatsAppState(), new DebugKeysAppState());
    }

    // -------------------------------------------------------------------------
    @Override
    public void simpleInitApp() {
        setPauseOnLostFocus(false);
        //
        // CONNECT TO SERVER!
        networkHandler = new ClientNetworkHandler(this);
        //
        initGui();
        initCam();
        initLightandShadow();
        initPostProcessing();
        initKeys();
    }

    // -------------------------------------------------------------------------
    public void SimpleUpdate(float tpf) {
    }

    // -------------------------------------------------------------------------
    // Initialization Methods
    // -------------------------------------------------------------------------
    private static AppSettings getAppSettings() {
        AppSettings aps = new AppSettings(true);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        screen.width *= 0.75;
        screen.height *= 0.75;
        aps.setResolution(screen.width, screen.height);
        return (aps);
    }

    // -------------------------------------------------------------------------
    private void initGui() {
        setDisplayFps(true);
        setDisplayStatView(false);
    }

    // -------------------------------------------------------------------------
    private void initLightandShadow() {
        // Light1: white, directional
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection((new Vector3f(-0.7f, -1.3f, -0.9f)).normalizeLocal());
        sun.setColor(ColorRGBA.Gray);
        rootNode.addLight(sun);

        // Light 2: Ambient, gray
        AmbientLight ambient = new AmbientLight();
        ambient.setColor(new ColorRGBA(0.7f, 0.7f, 0.7f, 1.0f));
        rootNode.addLight(ambient);

        // SHADOW
        // the second parameter is the resolution. Experiment with it! (Must be a power of 2)
        DirectionalLightShadowRenderer dlsr = new DirectionalLightShadowRenderer(assetManager, 1024, 1);
        dlsr.setLight(sun);
        viewPort.addProcessor(dlsr);
    }

    // -------------------------------------------------------------------------
    private void initPostProcessing() {
        FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
        BloomFilter bloom = new BloomFilter();
        bloom.setBlurScale(2.0f);
        bloom.setBloomIntensity(2.0f);
        fpp.addFilter(bloom);
        viewPort.addProcessor(fpp);
        Spatial sky = SkyFactory.createSky(assetManager, "Textures/SKY.JPG", true);
        getRootNode().attachChild(sky);
    }

    // -------------------------------------------------------------------------
    private void initCam() {
        //flyCam.setEnabled(false);
        cam.setLocation(new Vector3f(3f, 15f, 15f));
        cam.lookAt(new Vector3f(0, 0, 3), Vector3f.UNIT_Y);
    }

    // -------------------------------------------------------------------------
    // This client received its InitialClientMessage.
    private void initGame(NewClientMessage msg) {
        System.out.println("Received initial message from server. Initializing playfield.");
        //
        // store ID
        this.ID = msg.ID;
        System.out.println("My ID: " + this.ID);
        playfield = new ClientPlayfield(this);
        for (FieldData fd : msg.field) {
            playfield.addSphere(fd);
        }
    }

    // -------------------------------------------------------------------------
    // Keyboard & Mouse input
    private void initKeys() {
        inputManager.addMapping("Select", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));        
        
        inputManager.addMapping("Donation", new KeyTrigger(KeyInput.KEY_D));
        inputManager.addMapping("Attack", new KeyTrigger(KeyInput.KEY_A));
        inputManager.addMapping("Absorb", new KeyTrigger(KeyInput.KEY_W));
        inputManager.addMapping("Infusion", new KeyTrigger(KeyInput.KEY_S));
        
        inputManager.addMapping("PL_EXPLODE", new KeyTrigger(KeyInput.KEY_SPACE));
        inputManager.addListener(this, new String[]{"PL_EXPLODE", "Select", "Donation", 
                                            "Attack", "Absorb", "Infusion"});
        
    }

    // key action
    public void onAction(String name, boolean isPressed, float tpf) {
        if (isPressed) {
        }
    }
    

    // -------------------------------------------------------------------------
    // message received
    public void messageReceived(Message msg) {
        if (msg instanceof NewClientMessage) {
            NewClientMessage ncm = (NewClientMessage)msg;
            if (this.ID == -1) {
                initGame(ncm);
            } else {
                playfield.addSphere(ncm.field.getLast());
            }
        }
        if (msg instanceof UpdateMessage){
            UpdateMessage um = (UpdateMessage)msg;
            System.out.println("Update Message recieved from server");
            for(int i = 0;i<8; i++){
                FieldData f = um.field.get(i);
                data.add(i, f);
            }
        }
    }
    
    public void attack(int energy, int sender, int reciever){
        ChangeEnergy msg = new ChangeEnergy(energy, sender, reciever);
        networkHandler.send(msg);
    }
    
    public void absorb(int sender, int reciever){
        Absorb msg = new Absorb(sender, reciever, sent);
        sent = !sent;
        networkHandler.send(msg);
    }
}
