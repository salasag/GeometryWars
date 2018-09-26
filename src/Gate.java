import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Pair;

public class Gate implements Sprite{

	public static final double GATE_LENGTH = 80;
	public static final double GATE_END_RADIUS = 5;
	private static final double MAX_ROTATIONAL_SPEED = 50.0 / 180 * Math.PI; //Degrees/second
	private static final double MAX_MOVEMENT_SPEED = 20; //Pixels/second
	private static final double MAX_MOVEMENT_ACCELERATION = 3; //Pixels/second^2
	private static final Color END_COLOR = Color.RED;
	private static final Color LINE_COLOR = Color.GREEN;
	private static final double EXPLOSION_RADIUS = 80;
	public static final int POINT_VALUE = 50;
	private static final double MAX_ROTATIONAL_ACCELERATION = MAX_ROTATIONAL_SPEED / 3;
	private double myXVelocity = 0;
	private double myYVelocity = 0;
	private double myAngle = 0;
	
	private Line myLine;
	private Pair<Circle, Circle> myEnds;
	private GameFrame myGameFrame;
	private double myRotationVelocity;
	
	public Gate(GameFrame gameFrame, double x1, double y1, double x2, double y2) {
		myGameFrame = gameFrame;
		Line line = new Line(x1, y1, x2, y2);
		line.setFill(LINE_COLOR);
		Circle circle1 = new Circle(x1, y1, GATE_END_RADIUS);
		Circle circle2 = new Circle(x2, y2, GATE_END_RADIUS);
		circle1.setFill(END_COLOR);
		circle2.setFill(END_COLOR);
		myLine = line;
		myEnds = new Pair<Circle,Circle>(circle1, circle2);
		myAngle = Math.atan((y2-y1)/(x2-x1)); //TODO is this right?
		myGameFrame.addGate(this);
	}

	@Override
	@Deprecated
	public Node getView() {
		return null;
	}

	@Override
	public void move(double sdelay) {
		double rotationAcceleration = (Math.random()-.5)*MAX_ROTATIONAL_ACCELERATION*sdelay;
		double rotationVelocity = (Math.random()-.5)*MAX_ROTATIONAL_SPEED*sdelay;
		if (Math.abs(rotationAcceleration+rotationVelocity) < MAX_ROTATIONAL_SPEED) {
			myRotationVelocity += rotationAcceleration;
		} else {
			myRotationVelocity -= rotationAcceleration;
		}
		rotate(myRotationVelocity);
		
		double xAcceleration = Math.random()*MAX_MOVEMENT_ACCELERATION * 2 - MAX_MOVEMENT_ACCELERATION;
		if (Math.abs(xAcceleration+myXVelocity) < MAX_MOVEMENT_SPEED) {
			myXVelocity += xAcceleration;
		} else {
			myXVelocity -= xAcceleration;
		}
		double yAcceleration = Math.random()*MAX_MOVEMENT_ACCELERATION * 2 - MAX_MOVEMENT_ACCELERATION;
		if (Math.abs(yAcceleration+myYVelocity) < MAX_MOVEMENT_SPEED) {
			myYVelocity += yAcceleration;
		} else {
			myYVelocity -= yAcceleration;
		}
		double xIncrement = sdelay*myXVelocity;
		double yIncrement = sdelay*myYVelocity;
		myLine.setStartX(myLine.getStartX() + xIncrement);
		myLine.setStartY(myLine.getStartY() + yIncrement);
		myEnds.getKey().setCenterX(myLine.getStartX());
		myEnds.getKey().setCenterY(myLine.getStartY());
		myLine.setEndX  (myLine.getEndX()   + xIncrement);
		myLine.setEndY  (myLine.getEndY()   + yIncrement);
		myEnds.getValue().setCenterX(myLine.getEndX());
		myEnds.getValue().setCenterY(myLine.getEndY());
		//TODO set boundaries
	}

	private void rotate(double rotationIncrement) {
		myAngle = ((myAngle + rotationIncrement + Math.PI/2) % Math.PI) - Math.PI/2;
		myLine.setRotate(myAngle);
		double xMiddle = getX();
		double yMiddle = getY();
		myLine.setStartX(xMiddle + Math.cos(myAngle) * GATE_LENGTH / 2);
		myLine.setStartY(yMiddle + Math.sin(myAngle) * GATE_LENGTH / 2);
		myLine.setEndX  (xMiddle - Math.cos(myAngle) * GATE_LENGTH / 2);
		myLine.setEndY  (yMiddle - Math.sin(myAngle) * GATE_LENGTH / 2);
		//System.out.println(this.getClass().getName() + "| XVal: "+xMiddle+" | YVal: "+yMiddle+
		//		      "\n"+this.getClass().getName() + "| SrtX: "+myLine.getStartX()+" | SrtY: " + myLine.getStartY());
		myEnds.getKey().setCenterX(myLine.getStartX());
		myEnds.getKey().setCenterY(myLine.getStartY());
		myEnds.getValue().setCenterX(myLine.getEndX());
		myEnds.getValue().setCenterY(myLine.getEndY());
	}
	
	private double distance(double x1, double y1, double x2, double y2) {
		double xDist = x2 - x1;
		double yDist = y2 - y1;
		return Math.sqrt(xDist * xDist + yDist * yDist);
	}
	
	private boolean collidesEnds(Sprite other) {
		double distance1 = distance(other.getX(), other.getY(), myEnds.getKey().getCenterX(), myEnds.getKey().getCenterY());
		double distance2 = distance(other.getX(), other.getY(), myEnds.getValue().getCenterX(), myEnds.getValue().getCenterY());
		return Math.min(distance1, distance2) < GATE_END_RADIUS + Ship.SIZE;
	}
	
	private boolean collidesLine(Sprite other) {
		double xDist = other.getX() - this.getX();
		double yDist = other.getY() - this.getY();
		double angle = Math.atan((yDist)/(xDist));
		double distance = Math.sqrt(xDist*xDist + yDist*yDist);
		return distance < GATE_LENGTH/2 + Ship.SIZE && distance * Math.abs(angle - myAngle) < Ship.SIZE;
	}
	
	@Override //TODO Botched af
	public double getX() {
		return (myLine.getStartX() + myLine.getEndX())/2;
	}

	@Override
	public double getY() {
		return (myLine.getStartY() + myLine.getEndY())/2;
	}

	public Pair<Circle, Circle> getEnds() {
		return myEnds;
	}
	
	public Line getLine() {
		return myLine;
	}
	
	public void collides(Ship myShip) { //TODO

		if(collidesEnds(myShip)){
			//Ship Dies
			System.out.println(this.getClass().getSimpleName()+ ": End Collision with Ship");
		}
		
		if(collidesLine(myShip)){
			explode(); //Add points
			System.out.println(this.getClass().getSimpleName()+ ": Line Collision with Ship");
		}
		
	}

	private void explode() {
		myGameFrame.removeGate(this);
		Circle circle1 = myEnds.getKey();
		Circle circle2 = myEnds.getValue();
		
		for(int i = 0; i < myGameFrame.getEnemyList().size(); i++){
			Enemy enemy = myGameFrame.getEnemyList().get(i);
			if(distance(enemy.getX(),enemy.getY(),circle1.getCenterX(),circle1.getCenterY()) < EXPLOSION_RADIUS + Enemy.SIZE || 
			   distance(enemy.getX(),enemy.getY(),circle2.getCenterX(),circle2.getCenterY()) < EXPLOSION_RADIUS + Enemy.SIZE){
				myGameFrame.removeEnemy(enemy);
				i--;
			}
		}
		
	}

}
