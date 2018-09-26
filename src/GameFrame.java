import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class GameFrame implements VisualFrame{
	
	public static final double REPULSION = 25;
	private AnchorPane myFrame;
	private GameState myGameState;
	private Group myGroup;
	private Ship myShip;
    private double myMouseX;
    private double myMouseY;
	private GateSpawner myGateSpawner;
	private EnemySpawner myEnemySpawner;
	private List<Enemy> myEnemyList;
	private List<Gate>  myGateList;
	private ScoreBoard myScoreBoard;	
	
	public GameFrame(GameState gameState) {
		myGameState = gameState;
		myEnemyList = new ArrayList<Enemy>();
		myGateList = new ArrayList<Gate>();
		myFrame = new AnchorPane();
		AnchorPane.setBottomAnchor(myFrame, 0.0);
		AnchorPane.setTopAnchor   (myFrame, 0.0);
		AnchorPane.setLeftAnchor  (myFrame, 0.0);
		AnchorPane.setRightAnchor (myFrame, 0.0);
		initializeMouseListener();
		
		myGroup = new Group();
		myFrame.getChildren().add(myGroup);
		myShip = new Ship(this);
		new ScoreBoard(this);
		myEnemySpawner = new EnemySpawner(this);
		myGateSpawner  = new GateSpawner(this);
		myGroup.getChildren().add(myShip.getView());
		
	}

	private void initializeMouseListener() {
		myFrame.setOnMouseMoved(new EventHandler<MouseEvent>() {
			@Override public void handle(MouseEvent event) {
		      myMouseX = event.getSceneX();
		      myMouseY = event.getSceneY();
		      }
		    });
		myFrame.setOnMouseExited(new EventHandler<MouseEvent>() {
		    @Override public void handle(MouseEvent event) {
		    	}
			});
	}
	
	public double getMouseX() {
		return myMouseX;
	}
	
	public double getMouseY() {
		return myMouseY;
	}
	
	@Override
	public Parent getFrame() {
		return myFrame;
	}

	public double getWidth() {
		return myGameState.getWidth();
	}
	
	public double getHeight() {
		return myGameState.getHeight();
	}
	
	public Ship getShip() {
		return myShip;
	}
	
	public List<Enemy> getEnemyList() {
		return myEnemyList;
	}
	
	public void addEnemy(Enemy enemy) {
		myEnemyList.add(enemy);
		myGroup.getChildren().add(enemy.getView());
	}
	
	public void removeEnemy(Enemy enemy) {
		myScoreBoard.addScore(Enemy.POINT_VALUE);
		myEnemyList.remove(enemy);
		myGroup.getChildren().remove(enemy.getView());
	}
	
	public void addGate(Gate gate) {
		myGateList.add(gate);
		myGroup.getChildren().addAll(gate.getLine(),gate.getEnds().getKey(),gate.getEnds().getValue());
	}
	
	public void removeGate(Gate gate) {
		myScoreBoard.addScore(Gate.POINT_VALUE);
		myGateList.remove(gate);
		myGroup.getChildren().removeAll(gate.getLine(),gate.getEnds().getKey(),gate.getEnds().getValue());
	}
	
	@Override
	public void update(double sdelay) {
		//Spawning
		myEnemySpawner.spawn(sdelay);
		myGateSpawner.spawn(sdelay);
		//Movement
		myShip.move(sdelay);
		for (int i = 0; i < myEnemyList.size(); i++) {
			myEnemyList.get(i).move(sdelay);
		}
		for (int i = 0; i < myGateList.size(); i++) {
			myGateList.get(i).move(sdelay);
		}
		//Collisions
		for (int i = 0; i < myEnemyList.size(); i++) {
			myEnemyList.get(i).collides(myShip);			
		}
		for (int i = 0; i < myGateList.size(); i++) {
			myGateList.get(i).collides(myShip);
		}
	}

	public void addScoreBoard(ScoreBoard scoreBoard) {
		myScoreBoard = scoreBoard;
		myGroup.getChildren().addAll(myScoreBoard.getView());
	}

	
}
