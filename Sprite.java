import java.awt.Image;

/**
 * 
 * @author
 */
public class Sprite {

        private boolean visible;
        private Image image;
        protected int x;
        protected int y;
        protected boolean dying;
        protected int dx;

        private boolean visible1;
        private Image image1;
        protected int x1;
        protected int y1;
        protected boolean dying1;
        protected int dx1;

        /*
         * Constructor
         */
        public Sprite() {
            visible = true;
            visible1 = true;
        }

        public void die() {
            visible = false;
            visible1 = false;
        }

        public boolean isVisible() {
            return visible;
        }

        public boolean isVisible1() {
            return visible1;
        }
        protected void setVisible(boolean visible) {
            this.visible = visible;
        }
        protected void setVisible1(boolean visible1) {
            this.visible1 = visible1;
        }
        public void setImage(Image image) {
            this.image = image;
        }
        public void setImage1(Image image1) {
            this.image1 = image1;
        }

        public Image getImage() {
            return image;
        }
        public Image getImage1() {
            return image1;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }
        public int getY() {
            return y;
        }

        public int getX() {
            return x;
        }

        public void setDying(boolean dying) {
            this.dying = dying;
        }

        public boolean isDying() {
            return this.dying;
        }

        public void setX1(int x1) {
        this.x1 = x1;
    }

        public void setY1(int y1) {
        this.y1 = y1;
    }
        public int getY1() {
        return y1;
    }

        public int getX1() {
        return x1;
    }

        public void setDying1(boolean dying1) {
        this.dying1 = dying1;
    }

        public boolean isDying1() {
        return this.dying1;
    }
}