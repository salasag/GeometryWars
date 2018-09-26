import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.util.Pair;

@Deprecated
public class MouseInfo {
	private Label myInfoLabel;

	public MouseInfo() {
	    myInfoLabel = new Label("Mouse Location Monitor");

	    myInfoLabel.setOnMouseMoved(new EventHandler<MouseEvent>() {
	      @Override public void handle(MouseEvent event) {
	        String msg =
	          "(x: "       + event.getX()      + ", y: "       + event.getY()       + ") -- " +
	          "(sceneX: "  + event.getSceneX() + ", sceneY: "  + event.getSceneY()  + ") -- " +
	          "(screenX: " + event.getScreenX()+ ", screenY: " + event.getScreenY() + ")";
	        System.out.println(msg);
	        myInfoLabel.setText(msg);
	      }
	    });

	    myInfoLabel.setOnMouseExited(new EventHandler<MouseEvent>() {
	      @Override public void handle(MouseEvent event) {
	      }
	    });
	}
	
	public Pair<Double, Double> getCoordinates(){
		String [] coordStrings = myInfoLabel.getText().split(" ");
		return new Pair<Double,Double>((Double.parseDouble(coordStrings[0])), Double.parseDouble(coordStrings[1]));
	}
}
