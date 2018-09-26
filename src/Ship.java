import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ship implements Sprite{

	public static final double SIZE = 10;
	public static final int VELOCITY = 100;
	private Circle mySprite;
	private GameFrame myGameFrame;


	public Ship(GameFrame gameFrame) {
		myGameFrame = gameFrame;
		mySprite = new Circle();
		mySprite.setCenterX(myGameFrame.getWidth()/2);
		mySprite.setCenterY(myGameFrame.getHeight()/2);
		mySprite.setRadius(SIZE);
		mySprite.setFill(Color.BLUE);
	}
	
	
	@Override
	public Node getView() {
		return mySprite;
	}

	@Override
	public void move(double sdelay) {
		double spriteX = mySprite.getCenterX();
		double spriteY = mySprite.getCenterY();
		double mouseX  = myGameFrame.getMouseX();
		double mouseY  = myGameFrame.getMouseY();
		double deltaX  = mouseX - spriteX;
		double deltaY  = mouseY - spriteY;
		double normalizingFactor = Math.sqrt(deltaX*deltaX + deltaY*deltaY);
		double normalizedX = deltaX / normalizingFactor;
		double normalizedY = deltaY / normalizingFactor;
		double stepX = normalizedX * sdelay * VELOCITY;
		double stepY = normalizedY * sdelay * VELOCITY;
		if(Math.abs(stepX) < Math.abs(deltaX)){
			mySprite.setCenterX(spriteX + stepX);
		} else {
			mySprite.setCenterX(mouseX);
		}
		if(Math.abs(stepY) < Math.abs(deltaY)){
			mySprite.setCenterY(spriteY + stepY);
		} else {
			mySprite.setCenterY(mouseY);
		}	
	}

	@Override
	public double getX() {
		return mySprite.getCenterX();
	}

	@Override
	public double getY(){
		return mySprite.getCenterY();
	}
	
}
