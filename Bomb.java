import javax.swing.ImageIcon;

/**
 * 
 * @author 
 */
public class Bomb extends Sprite {

	private final String bomb = "/img/bomb.png";
	private final String bomb1 = "/img/bomb.png";
	private boolean destroyed;
	private boolean destroyed1;

	/*
	 * Constructor
	 */
	public Bomb(int x, int y) {
		setDestroyed(true);
		this.x = x;
		this.y = y;
		ImageIcon ii = new ImageIcon(this.getClass().getResource(bomb));
		setImage(ii.getImage());



		setDestroyed1(true);
		this.x1 = x1;
		this.y1 = y1;
		ImageIcon ii1 = new ImageIcon(this.getClass().getResource(bomb1));
		setImage1(ii1.getImage());
	}

	private void setDestroyed1(boolean destroyed1) {
		this.destroyed1 = destroyed1;
	}

	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}

	public boolean isDestroyed() {
		return destroyed;
	}
	public boolean isDestroyed1() {
		return destroyed1;
	}
}
