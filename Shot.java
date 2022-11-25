import javax.swing.ImageIcon;


public class Shot extends Sprite {

    private String shot = "/img/shot.png";
    private String shot1 = "/img/shot.png";
    private final int H_SPACE = 6;
    private final int V_SPACE = 1;



    public Shot() {

        ImageIcon ii = new ImageIcon(this.getClass().getResource(shot));
        setImage(ii.getImage());
        setX(this.x + H_SPACE);
        setY(this.y - V_SPACE);

        ImageIcon ii1 = new ImageIcon(this.getClass().getResource(shot1));
        setImage1(ii1.getImage());
        setX1(this.x + H_SPACE);
        setY1(this.y - V_SPACE);
    }

}