import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameInitializer extends Application{

	private Stage myStage;
	private Scene myScene;
	private GamePlatform myGamePlatform;
	//private GameAnimation myGameAnimation;
	private GameState myGameState;
	public static final int SCREEN_WIDTH  = 800;
	public static final int SCREEN_HEIGHT = 600;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println(this.getClass().getName() + ": Start Program");
		myStage = primaryStage;
		
		myGameState = new GameState();
		myGamePlatform = new GamePlatform(myGameState);
		myScene = new Scene(myGamePlatform.getFrame(), SCREEN_WIDTH, SCREEN_HEIGHT);
		myStage.setResizable(false);
		myStage.setTitle("Geometry Wars");
		myStage.setScene(myScene);
		myStage.show();
		new GameAnimation(myGameState);
		System.out.println(this.getClass().getName() + ": Program Started");
	}

	public void run(String[] args) {
		System.out.println(this.getClass().getName() + ": Run");
		launch(args);
		System.out.println(this.getClass().getName() + ": End");
	}

}

