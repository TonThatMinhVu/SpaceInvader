import javax.swing.ImageIcon;
public class Shot1 extends Sprite {
    private String shot1 = "/img/shot.png";
    private final int H_SPACE1 = 6;
    private final int V_SPACE1 = 1;
    public Shot1(int x1, int y1) {

        ImageIcon ii1 = new ImageIcon(this.getClass().getResource(shot1));
        setImage1(ii1.getImage());
        setX1(x1 + H_SPACE1);
        setY1(y1 - V_SPACE1);


    }
    public Shot1(){
    }
}
