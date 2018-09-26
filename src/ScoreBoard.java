import javafx.scene.Node;
import javafx.scene.text.Text;

public class ScoreBoard implements VisualNode {

	Text myScoreText;
	int myScore = 0;
	int myMultiplier = 0;
	private GameFrame myGameFrame;
	
	public ScoreBoard(GameFrame gameFrame) {
		
		myGameFrame = gameFrame;
		myScoreText = new Text();
		addScore(0);
		myScoreText.setX(10);
		myScoreText.setY(10);
		myGameFrame.addScoreBoard(this);
		
	}
	
	public void addScore(int toAdd) {
		myScore += myMultiplier * toAdd;
		myMultiplier++;
		myScoreText.setText("Score: "+myScore+" \nMultiplier: "+myMultiplier);
	}

	@Override
	public Node getView() {
		return myScoreText;
	}
	
}
