package cellsociety_team10;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class HandlerHolder {
	private EventHandler<MouseEvent> pred;
	private EventHandler<MouseEvent> seg;
	private EventHandler<MouseEvent> life;
	private EventHandler<MouseEvent> fire;
	
	public HandlerHolder(EventHandler<MouseEvent> pred_handler, EventHandler<MouseEvent> seg_handler, EventHandler<MouseEvent> life_handler, EventHandler<MouseEvent> fire_handler) {
		pred = pred_handler;
		seg = seg_handler;
		life = life_handler;
		fire = fire_handler;
	}
	
	public EventHandler<MouseEvent> getPredHandler() {
		return pred;
	}
	
	public EventHandler<MouseEvent> getSegHandler() {
		return seg;
	}
	
	public EventHandler<MouseEvent> getLifeHandler() {
		return life;
	}
	
	public EventHandler<MouseEvent> getFireHandler() {
		return fire;
	}
}
