
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

public class GamePlatform implements VisualFrame{
	
	private AnchorPane myFrame;
	private GameState myGameState;
	private VisualFrame myFrameContent;
	
	public GamePlatform(GameState gameState) {
		myGameState = gameState;
		myGameState.setGamePlatform(this);
		myFrame = new AnchorPane();
		myGameState.setFrame(MenuFrame.class.getName());
	}

	@Override
	public Parent getFrame() {
		return myFrame;
	}

	public void setFrame(VisualFrame frame) {
		if(myFrameContent != null){
			myFrame.getChildren().remove(myFrameContent.getFrame());
		}
		myFrame.getChildren().add(frame.getFrame());
		myFrameContent = frame;
	}

	public VisualFrame getFrameContent() {
		return myFrameContent;
	}

	@Override
	public void update(double sdelay) {
		// TODO Auto-generated method stub
		
	}

}
