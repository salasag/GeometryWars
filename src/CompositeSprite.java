import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.shape.Shape;

public class CompositeSprite {

	private double myX;
	private double myY;
	private ArrayList<Shape> myShapes;
	
	public CompositeSprite(Collection<Shape> shapes) {
		myShapes = new ArrayList<Shape>();
		myShapes.addAll(shapes);
		// TODO Auto-generated constructor stub
	}
	
	public void setX(double x) {
		double changeX = x - this.myX;
		for (int i = 0; i < myShapes.size(); i++) {
			myShapes.get(i).setLayoutX(myShapes.get(i).getLayoutX()+changeX);
		}
		this.myX = x;
	}
	
	public void setY(double y) {
		double changeY = y - this.myY;
		for (int i = 0; i < myShapes.size(); i++) {
			myShapes.get(i).setLayoutY(myShapes.get(i).getLayoutY()+changeY);
		}
		this.myY = y;
	}
	
	public Collection<Shape> getShapes() {
		return myShapes;
	}
	
	public double getX() {
		return myX;
	}
	
	public double getY() {
		return myY;
	}
	
}
