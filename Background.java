import javax.swing.ImageIcon;

/**
 * 
 * @author
 */
public class Background extends Sprite implements Commons {

	private final String background= "img/backgr2.png";
	private int width;

	/*
	 * Constructor
	 */
	public Background() {
		ImageIcon ii2 = new ImageIcon(this.getClass().getResource(background));

		setWidth(ii2.getImage().getWidth(null));

		setImage(ii2.getImage());
		setX(0);
		setY(0);
	}

	/*
	 * Getters & Setters
	 */

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
}
