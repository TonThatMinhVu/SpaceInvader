import javax.swing.ImageIcon;

/**
 * 
 * @author 
 */
public class Bomb extends Sprite {

	private final String bomb = "/img/bomb.png";
	private final String bomb1 = "/img/bomb.png";
	private boolean destroyed;

	/*
	 * Constructor
	 */
	public Bomb(int x, int y) {
		setDestroyed(true);
		this.x = x;
		this.y = y;
		ImageIcon ii = new ImageIcon(this.getClass().getResource(bomb));
		setImage(ii.getImage());

		ImageIcon ii1 = new ImageIcon(this.getClass().getResource(bomb1));
		setImage1(ii1.getImage());
	}


	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}

	public boolean isDestroyed() {
		return destroyed;
	}

}
