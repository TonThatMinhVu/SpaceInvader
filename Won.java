import javax.swing.*;

/**
 * 
 * @author 
 */
public class Won extends Sprite implements Commons{
    private final String won = "/img/won.jpg";
    private final String won1 = "/img/won.jpg";
    private int width;
    private int width1;
    
    /*
     * Constructor
     */
    public Won() {

        ImageIcon ii = new ImageIcon(this.getClass().getResource(won));

        width = ii.getImage().getWidth(null); 

        setImage(ii.getImage());
        setX(0);
        setY(0);

        ImageIcon ii1 = new ImageIcon(this.getClass().getResource(won1));

        width1 = ii1.getImage().getWidth(null);

        setImage1(ii1.getImage());
        setX1(0);
        setY1(0);
    }

    public int getWidth1(){
        return width1;
    }
    public void setWidth1(int width1) {
        this.width1 = width1;
    }


    /*
     * Getters & Setters
     */
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
}
