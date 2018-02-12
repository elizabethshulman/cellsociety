package visualComponents;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author benhubsch
 * 
 * This class serves static functions that are used throughout the program. All of
 * these functions are called many times in nearly every class. 
 *
 */
public class Helper {
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
