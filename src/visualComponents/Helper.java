package visualComponents;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * 
 * @author Ben Hubsch
 * 
 * This class serves static functions that are used throughout the program. All of
 * these functions are called many times in nearly every class. 
 *
 */
public class Helper {
	/**
	 * This is a convenient helper function that adds a Node to a Group.
	 * @param root This is the Group that we are attaching a Node to.
	 * @param n This is the Node that is being attached to a Group for display
	 * on screen.
	 */
	public static void addToRoot(Group root, Node n) {
		root.getChildren().add(n);
	}
	
	public static Image generateImage(String filename) {
		return new Image(filename);
	}

	/**
	 * This is a helper function that generates ImageView objects.
	 * @param filename This is the string that specifies which image to generate. 
	 * @return ImageView This is the ImageView object.
	 */
	public static ImageView generateImageView(String filename) {
		return generateImageView(filename, 0);
	}

	/**
	 * This is a helper function that generates ImageView objects.
	 * @param filename This is the string that specifies which image to generate. 
	 * @param fit_height This is the desired height of the ImageView.
	 * @return ImageView This is the ImageView object.
	 */
	public static ImageView generateImageView(String filename, double fit_height) {
		Image image_temp = new Image(filename);
		ImageView temp = new ImageView(image_temp);
		temp.setPreserveRatio(true);
		temp.setFitHeight(fit_height);
		return temp;
	}
}
