import javafx.geometry.Point2D;
import javafx.util.Pair;

public class GateSpawner implements Spawner{

	private double timeElapsed;
	private GameFrame myGameFrame;
	private double spawnPeriod = 5; //How many seconds between each spawn
	
	public GateSpawner(GameFrame gameFrame) {
		myGameFrame = gameFrame;
	}
	
	
	@Override
	public void spawn(double sdelay) {
		if(timeElapsed % spawnPeriod < sdelay){
			spawnGate();
		}
		timeElapsed += sdelay;
	}
	
	private void spawnGate() {
		Pair<Point2D, Point2D> coords = getPoints();
		new Gate(myGameFrame,coords.getKey().getX(),coords.getKey().getY(),coords.getValue().getX(),coords.getValue().getY());
	}


	private Pair<Point2D, Point2D> getPoints(){
		double frameWidth = myGameFrame.getWidth();
		double frameHeight = myGameFrame.getHeight();
		double pt1X = Math.random()*frameWidth;
		double pt1Y = Math.random()*frameHeight;
		double pt2X = 0;
		double pt2Y = 0;
		while(true){
			double angle = Math.random()*2*Math.PI;
			double xDiff = Math.cos(angle) * Gate.GATE_LENGTH;
			double yDiff = Math.sin(angle) * Gate.GATE_LENGTH;
			pt2X = pt1X + xDiff;
			pt2Y = pt1Y + yDiff;
			if(pt2X > 0 && pt2X < frameWidth && 
			   pt2Y > 0 && pt2Y < frameHeight){
				break;
			}
		}
		return new Pair<Point2D,Point2D>(new Point2D(pt1X, pt1Y), new Point2D(pt2X, pt2Y));
	}

}
