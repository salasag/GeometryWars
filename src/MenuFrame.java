import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class MenuFrame implements VisualFrame{

	private AnchorPane myFrame;
	private GameState myGameState;

	public MenuFrame(GameState gameState) {
		myGameState = gameState;
		
		myFrame = new AnchorPane();
		AnchorPane.setBottomAnchor(myFrame, 0.0);
		AnchorPane.setTopAnchor   (myFrame, 0.0);
		AnchorPane.setLeftAnchor  (myFrame, 0.0);
		AnchorPane.setRightAnchor (myFrame, 0.0);
		Button startButton = new Button("Start");
		startButton.setMinHeight(100);
		//AnchorPane.setBottomAnchor(startButton, 0.0);
		AnchorPane.setTopAnchor		(startButton, 100.0);
		AnchorPane.setLeftAnchor	(startButton, 100.0);
		AnchorPane.setRightAnchor	(startButton, 100.0);
		startButton.setOnAction(e -> startGame());
		
		myFrame.getChildren().add(startButton);
	}
	
	private void startGame() {
		myGameState.setFrame(GameFrame.class.getName());
	}
	
	@Override
	public Parent getFrame() {
		return myFrame;
	}

	@Override
	public void update(double sdelay) {
		// TODO Auto-generated method stub
		
	}
	
}
