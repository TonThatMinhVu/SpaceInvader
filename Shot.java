import javax.swing.ImageIcon;


public class Shot extends Sprite {

    private String shot = "/img/shot.png";
    private String shot1 = "/img/shot.png";
    private final int H_SPACE = 6;
    private final int V_SPACE = 1;
    private final int H_SPACE1 = 6;
    private final int V_SPACE1 = 1;



    public Shot() {

        ImageIcon ii = new ImageIcon(this.getClass().getResource(shot));
        setImage(ii.getImage());
        setX(x + H_SPACE);
        setY(y - V_SPACE);

        ImageIcon ii1 = new ImageIcon(this.getClass().getResource(shot1));
        setImage1(ii1.getImage());
        setX1(x1 + H_SPACE1);
        setY1(y1 - V_SPACE1);
    }
}