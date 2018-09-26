import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class GameState {
	private Map<String,VisualFrame> mySceneMap;
	private GamePlatform myGamePlatform;
	
	public GameState() {
		mySceneMap = new HashMap<String, VisualFrame>();
	}
	
	
	
	
	/**
	 * Update Methods
	 * @param sdelay 
	 */
	public void update(double sdelay) {
		myGamePlatform.getFrameContent().update(sdelay);
	}
	
	
	/**
	 * Scene Control Methods
	 */
	public VisualFrame getFrame(String name) {
		if (!containsFrame(name)) {
			mySceneMap.put(name, getNewFrame(name));
		}
		return mySceneMap.get(name);
	}
	
	public boolean isCurrentFrame(String name) {
		return mySceneMap.get(name) == myGamePlatform.getFrameContent();
	}
	
	public boolean containsFrame(String name) {
		return mySceneMap.containsKey(name);
	}

	private VisualFrame getNewFrame(String name) {
		VisualFrame newFrame = null;
		try {
			Class<?> frameClass = Class.forName(name);
			Constructor<?> frameConstructor = frameClass.getConstructor(GameState.class);
			newFrame = (VisualFrame)frameConstructor.newInstance(this);
		} catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return newFrame;
	}
	
	public void setFrame(String name) {
		myGamePlatform.setFrame(getFrame(name));
	}
	
	/**
	 * Getter and Setters 
	 */
	public GamePlatform getGamePlatform() {
		return myGamePlatform;
	}
	
	public void setGamePlatform(GamePlatform gamePlatform) {
		this.myGamePlatform = gamePlatform;
	}
	
	public double getHeight() {
		//System.out.println(this.getClass().getSimpleName() + ": " + myGamePlatform.getFrame().getBoundsInParent().getHeight());
		return myGamePlatform.getFrame().getBoundsInParent().getHeight();
		
	}

	public double getWidth() {
		//System.out.println(this.getClass().getSimpleName() + ": " + myGamePlatform.getFrame().getBoundsInParent().getWidth());
		return myGamePlatform.getFrame().getBoundsInParent().getWidth();
	}








	
	
}
