import javax.swing.ImageIcon;

/**
 * 
 * @author 
 */
public class Alien extends Sprite {

    private Bomb bomb;
    private Bomb1 bomb1;
    private final String alien = "/img/alien.png";
    private final String alien1 = "/img/alien.png";


    public Alien(int x, int y) {
        this.x = x;
        this.y = y;

        bomb = new Bomb(x, y);
        ImageIcon ii = new ImageIcon(this.getClass().getResource(alien));
        setImage(ii.getImage());

        this.x1 = x1;
        this.y1 = y1;

        bomb1 = new Bomb1(x1, y1);
        ImageIcon ii1 = new ImageIcon(this.getClass().getResource(alien1));
        setImage1(ii1.getImage());
    }

    public void act(int direction) {
        this.x += direction;
    }
    public void act2(int direction1){
        this.x1 += direction1;
    }

    /*
     * Getters & Setters
     */

    
	public Bomb getBomb() {
		return bomb;
	}
    public Bomb1 getBomb1() {
        return bomb1;
    }
}