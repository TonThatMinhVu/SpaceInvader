import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

/**
 * 
 * @author
 */
public class Player extends Sprite implements Commons {

	private final int START_Y = 400;
	private final int START_X = 270;
	private final int START_Y1 = 600;
	private final int START_X1 = 300;

	private final String player = "/img/craft.png";
	private final String player1 = "/img/craft.png";
	private int width;
	private int width1;

	/*
	 * Constructor
	 */
	public Player() {
		ImageIcon ii = new ImageIcon(this.getClass().getResource(player));

		width = ii.getImage().getWidth(null);

		setImage(ii.getImage());
		setX(START_X);
		setY(START_Y);

		ImageIcon ii1 = new ImageIcon(this.getClass().getResource(player1));

		width1 = ii1.getImage().getWidth(null);

		setImage1(ii1.getImage());
		setX1(START_X1);
		setY1(START_Y1);
	}

	public void act() {
		x += dx;
		if (x <= 2)
			x = 2;
		if (x >= BOARD_WIDTH - 2 * width)
			x = BOARD_WIDTH - 2 * width;
	}

	public void act1() {
		x1 += dx1;
		if (x1 <= 2)
			x1 = 2;
		if (x1 >= BOARD_WIDTH - 2 * width1)
			x1 = BOARD_WIDTH - 2 * width1;
	}

		public void keyPressed(KeyEvent e){
			int key = e.getKeyCode();

			if (key == KeyEvent.VK_LEFT) {
				dx = -2;
			}

			if (key == KeyEvent.VK_RIGHT) {
				dx = 2;
			}

		}

		public void keyReleased (KeyEvent E){
			int key1 = E.getKeyCode();

			if (key1 == KeyEvent.VK_LEFT) {
				dx1 = -2;
			}

			if (key1 == KeyEvent.VK_RIGHT) {
				dx1 = 2;
			}
		}

	}
