import javax.swing.ImageIcon;
public class Alien1 extends Sprite {
    private Bomb bomb1;
    private final String alien1 = "/img/alien.png";
    public Alien1 (int x1, int y1) {
        this.x1 = x1;
        this.y1 = y1;
        bomb1 = new Bomb(x1, y1);
        ImageIcon ii1 = new ImageIcon(this.getClass().getResource(alien1));
        setImage1(ii1.getImage());
    }

    public void act2(int direction1){
        this.x1 += direction1;
    }

    /*
     * Getters & Setters
     */


    public Bomb getBomb1() {
        return bomb1;
    }
}
