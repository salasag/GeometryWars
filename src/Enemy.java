import java.util.List;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Enemy implements Sprite{

	public static final double SIZE = 7;
	public static final int VELOCITY = 50;
	public static final int POINT_VALUE = 50;
	private Circle mySprite;
	private GameFrame myGameFrame;
	
	public Enemy(GameFrame gameFrame, double x, double y) {
		myGameFrame = gameFrame;
		mySprite = new Circle();
		mySprite.setCenterX(x);
		mySprite.setCenterY(y);
		mySprite.setRadius(SIZE);
		mySprite.setFill(Color.RED);
		gameFrame.addEnemy(this);
	}
	
	@Override
	public Node getView() {
		return mySprite;
	}

	@Override
	public void move(double sdelay) {
		Ship ship = myGameFrame.getShip();
		double spriteX = mySprite.getCenterX();
		double spriteY = mySprite.getCenterY();
		double shipX = ship.getX();
		double shipY = ship.getY();
		double deltaX  = shipX - spriteX;
		double deltaY  = shipY - spriteY;
		double normalizingFactor = Math.sqrt(deltaX*deltaX + deltaY*deltaY);
		double normalizedX = deltaX / normalizingFactor;
		double normalizedY = deltaY / normalizingFactor;
		double stepX = normalizedX * sdelay * VELOCITY;
		double stepY = normalizedY * sdelay * VELOCITY;
		List<Enemy> otherEnemys = myGameFrame.getEnemyList();
		for(Enemy enemy : otherEnemys){
			if(enemy == this){
				continue;
			}
			stepX += (this.getX()-enemy.getX())/Math.pow(getDistance(enemy),2)*GameFrame.REPULSION*sdelay;
			stepY += (this.getY()-enemy.getY())/Math.pow(getDistance(enemy),2)*GameFrame.REPULSION*sdelay;
		}
		if(Math.abs(stepX) < Math.abs(deltaX)){
			mySprite.setCenterX(spriteX + stepX);
		} else {
			mySprite.setCenterX(shipX);
		}
		if(Math.abs(stepY) < Math.abs(deltaY)){
			mySprite.setCenterY(spriteY + stepY);
		} else {
			mySprite.setCenterY(shipY);
		}
	}

	private double getDistance(Sprite other) {
		double xDist = other.getX() - this.getX();
		double yDist = other.getY() - this.getY();
		return Math.sqrt(xDist*xDist + yDist*yDist);
	}

	@Override
	public double getX() {
		return mySprite.getCenterX();
	}

	@Override
	public double getY() {
		return mySprite.getCenterY();
	}

	public void collides(Ship myShip) {
		double distance = getDistance(myShip);
		if(distance < Enemy.SIZE + Ship.SIZE){
			//TODO End game or lose life
			System.out.println(this.getClass().getName() + ": Enemy-Ship Collision");
		}
		
	}

}
