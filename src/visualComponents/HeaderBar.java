package visualComponents;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * @author benhubsch
 * 
 * The Class HeaderBar is a simple object that displays the file title and author to the
 * user.
 */
public class HeaderBar {
	private HBox myHBox;
	private Text sim_text;

	/**
	 * Instantiates a new HeaderBar object if no default header is passed.
	 */
	public HeaderBar() {
		this("");
	}
	
	/**
	 * Instantiates a new HeaderBar object if a default header is passed, as from the
	 * StartPage object.
	 *
	 * @param sim_type
	 */
	public HeaderBar(String sim_type) {
		myHBox = new HBox();
		myHBox.setId("header");
		
		sim_text = new Text(sim_type);
		sim_text.setId("title-text");
		myHBox.getChildren().add(sim_text);
	}

	/**
	 * Gets the HBox object.
	 *
	 * @return HBox
	 */
	public HBox getHBox() {
		return myHBox;
	}
	
	/**
	 * Sets the header.
	 *
	 * @param header
	 */
	public void setHeader(String header) {
		sim_text.setText(header);
	}
	
	public void setPane(BorderPane border_pane) {
		border_pane.setTop(myHBox);
	}
}
