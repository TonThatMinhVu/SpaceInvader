import javax.swing.ImageIcon;

/**
 * 
 * @author
 */
public class GameOver extends Sprite implements Commons {

	private final String gameOver = "/img/gameover.png";
	private final String gameOver1 = "/img/gameover.png";
	private int width;
	private int width1;

	/*
	 * Constructor
	 */
	public GameOver() {
		ImageIcon ii = new ImageIcon(this.getClass().getResource(gameOver));

		setWidth(ii.getImage().getWidth(null));

		setImage(ii.getImage());
		setX(0);
		setY(0);

		ImageIcon ii1 = new ImageIcon(this.getClass().getResource(gameOver1));

		setWidth1(ii1.getImage().getWidth(null));

		setImage1(ii1.getImage());
		setX1(0);
		setY1(0);
	}

	/*
	 * Getters & Setters
	 */
	public int getWidth() {
		return width;
	}

	public int getWidth1() {return width1;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	public void setWidth1(int width1) {this.width1 = width1; }
}
