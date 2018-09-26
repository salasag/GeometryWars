import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class GameAnimation {
	private static final double FRAMES_PER_SECOND = 60;
	private Timeline myAnimation;
	private GameState myGameState;
	
	public GameAnimation(GameState gameState) {
		myGameState = gameState;
		myAnimation = new Timeline();
		myAnimation.setCycleCount(Timeline.INDEFINITE);
		double mildelay = 1000/FRAMES_PER_SECOND;
		double sdelay = 1/FRAMES_PER_SECOND;
		KeyFrame frame = new KeyFrame(Duration.millis(mildelay),
				e -> step(sdelay));
		myAnimation.getKeyFrames().add(frame);
		myAnimation.play();
	}

	private void step(double sdelay) {
		//System.out.println(this.getClass().getName() + ": Step");
		myGameState.update(sdelay);
	}

}
