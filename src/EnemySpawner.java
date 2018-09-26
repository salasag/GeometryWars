import javafx.geometry.Point2D;
import javafx.util.Pair;

public class EnemySpawner implements Spawner{

	private double timeElapsed;
	private GameFrame myGameFrame;
	private double spawnPeriod = 5; //How many seconds between each spawn
	public EnemySpawner(GameFrame gameFrame) {
		myGameFrame = gameFrame;
	}
	
	@Override
	public void spawn(double sdelay) {
		if(timeElapsed % spawnPeriod < sdelay){
			spawnGroup(10); //Change num
		}
		timeElapsed += sdelay;
	}

	private void spawnGroup(int spawnAmount) {
		Pair<Point2D, Point2D> range = getRange();
		for(int i = 0; i < spawnAmount; i++){
			double xRandom = Math.random();
			double xValue = range.getKey().getX() + xRandom * (range.getValue().getX() - range.getKey().getX());
			double yRandom = Math.random(); 
			double yValue = range.getKey().getY() + yRandom * (range.getValue().getY() - range.getKey().getY());
			new Enemy(myGameFrame,xValue,yValue);
		}
	}

	private Pair<Point2D, Point2D> getRange() {
		int cornerNumber = ((int)(Math.random()*4));
		double frameWidth = myGameFrame.getWidth();
		double frameHeight = myGameFrame.getHeight();
		double spawnAreaWidth;
		double spawnAreaHeight;
		if(frameWidth > 400){
			spawnAreaWidth = 50;
		} else {
			spawnAreaWidth = frameWidth / 8;
		}
		if(frameHeight > 400){
			spawnAreaHeight = 50;
		} else {
			spawnAreaHeight = frameWidth / 8;
		}
		System.out.println(this.getClass().getName() + ": " + frameWidth + " " + frameHeight + " " + spawnAreaHeight + " " + spawnAreaWidth);
		if(cornerNumber == 0){
			Point2D point1 = new Point2D(0, 0);
			Point2D point2 = new Point2D(spawnAreaWidth, spawnAreaHeight);
			return new Pair<Point2D,Point2D>(point1, point2);
		}else if(cornerNumber == 1){
			Point2D point1 = new Point2D(0, frameHeight - spawnAreaHeight);
			Point2D point2 = new Point2D(spawnAreaWidth, frameHeight);
			return new Pair<Point2D,Point2D>(point1, point2);
		}else if(cornerNumber == 2){
			Point2D point1 = new Point2D(frameWidth - spawnAreaWidth, 0);
			Point2D point2 = new Point2D(frameWidth, spawnAreaHeight);
			return new Pair<Point2D,Point2D>(point1, point2);
		}else if(cornerNumber == 3){
			Point2D point1 = new Point2D(frameWidth - spawnAreaWidth, frameHeight - spawnAreaHeight);
			Point2D point2 = new Point2D(frameWidth, frameHeight);
			return new Pair<Point2D,Point2D>(point1, point2);
		}else {
			System.out.println(this.getClass().getName() + ": Error in getRange()");
			return null;
		}
	}

}
