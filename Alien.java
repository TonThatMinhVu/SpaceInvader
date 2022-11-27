import javax.swing.ImageIcon;

/**
 * 
 * @author 
 */
public class Alien extends Sprite {

    private Bomb bomb;
    private Bomb bomb1;
    private final String alien = "/img/alien.png";
    private final String alien1 = "/img/boss.png";


    public Alien(int x, int y) {
        this.x = x;
        this.y = y;

        bomb = new Bomb(x, y);
        bomb1 = new Bomb(x, y);
        ImageIcon ii = new ImageIcon(this.getClass().getResource(alien));
        setImage(ii.getImage());
        ImageIcon ii1 = new ImageIcon(this.getClass().getResource(alien1));
        setImage(ii1.getImage());
    }

    public void act(int direction) {
        this.x += direction;
    }
    public void act2(int direction1){
        this.x += direction1;
    }

    /*
     * Getters & Setters
     */

    
	public Bomb getBomb() {
		return bomb;
	}
    public Bomb getBomb1() {
        return bomb1;
    }
}

