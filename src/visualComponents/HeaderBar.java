package visualComponents;

import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * 
 * @author Ben Hubsch
 * 
 *
 */
public class HeaderBar {
	private HBox hbox;
	private Text sim_text;

	public HeaderBar() {
		this("");
	}
	
	public HeaderBar(String sim_type) {
		hbox = new HBox();
		hbox.setId("header");
		
		sim_text = new Text(sim_type);
		sim_text.setId("title-text");
		hbox.getChildren().add(sim_text);
	}

	public HBox getHBox() {
		return hbox;
	}
	
	public void setHeader(String header) {
		sim_text.setText(header);
	}
}
