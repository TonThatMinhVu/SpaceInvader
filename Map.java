import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
public class Map extends JPanel implements Runnable, Commons  {
    private static final long serialVersionUID = 1L;

    private Dimension d1;
    private ArrayList aliens1;
    private Player player1;
    private Shot shot1;
    private GameOver gameend1;
    private Won vunnet1;

    private int alienX1 = 150;
    private int alienY1 = 25;
    private int direction1 = -1;
    private int deaths1 = 0;

    private boolean ingame1 = true;
    private boolean havewon1 = true;
    private final String expl1 = "/img/explosion.png";
    private final String alienpix1 = "/img/alien.png";
    private String message1 = "You lose this game !!!";

    private Thread animator1;

    /*
     * Constructor
     */
    public Map() {
        addKeyListener(new Map.TA());
        setFocusable(true);
        d1 = new Dimension(BOARD_WIDTH, BOARD_HEIGTH);
        setBackground(Color.black);

        gameInit();
        setDoubleBuffered(true);
    }

    public void addNotify() {
        super.addNotify();
        gameInit();
    }

    public void gameInit() {
        aliens1 = new ArrayList();

        ImageIcon ii = new ImageIcon(this.getClass().getResource(alienpix1));

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                Alien alien = new Alien(alienX1 + 18 * j, alienY1 + 18 * i);
                alien.setImage(ii.getImage());
                aliens1.add(alien);
            }
        }

        player1 = new Player();
        shot1 = new Shot();

        if (animator1 == null || !ingame1) {
            animator1 = new Thread(this);
            animator1.start();
        }
    }

    public void drawAliens(Graphics g) {
        Iterator it = aliens1.iterator();

        while (it.hasNext()) {
            Alien alien = (Alien) it.next();

            if (alien.isVisible()) {
                g.drawImage(alien.getImage(), alien.getX(), alien.getY(), this);
            }

            if (alien.isDying()) {
                alien.die();
            }
        }
    }

    public void drawPlayer(Graphics g) {
        if (player1.isVisible()) {
            g.drawImage(player1.getImage(), player1.getX(), player1.getY(), this);
        }

        if (player1.isDying()) {
            player1.die();
            havewon1 = false;
            ingame1 = false;
        }
    }

    public void drawGameEnd(Graphics g) {
        g.drawImage(gameend1.getImage(), 0, 0, this);
    }

    public void drawShot(Graphics g) {
        if (shot1.isVisible())
            g.drawImage(shot1.getImage(), shot1.getX(), shot1.getY(), this);
    }

    public void drawBombing(Graphics g) {
        Iterator i3 = aliens1.iterator();

        while (i3.hasNext()) {
            Alien a1 = (Alien) i3.next();

            Bomb b1 = a1.getBomb();

            if (!b1.isDestroyed()) {
                g.drawImage(b1.getImage(), b1.getX(), b1.getY(), this);
            }
        }
    }

    public void paint(Graphics G) {
        super.paint(G);

        G.setColor(Color.white);
        G.fillRect(0, 0, d1.width, d1.height);
        G.setColor(Color.blue);

        if (ingame1) {

            G.drawLine(0, GROUND, BOARD_WIDTH, GROUND);
            drawAliens(G);
            drawPlayer(G);
            drawShot(G);
            drawBombing(G);
        }

        Toolkit.getDefaultToolkit().sync();
        G.dispose();
    }

    public void gameOver() {
        Graphics G = this.getGraphics();

        gameend1 = new GameOver();
        vunnet1 = new Won();

        // g.setColor(Color.black);
        G.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGTH);
        if (havewon1 == true) {
            G.drawImage(vunnet1.getImage(), 0, 0, this);
        } else {
            G.drawImage(gameend1.getImage(), 0, 0, this);
        }
        G.setColor(new Color(0, 32, 48));
        G.fillRect(50, BOARD_WIDTH / 2 - 30, BOARD_WIDTH - 100, 50);
        G.setColor(Color.white);
        G.drawRect(50, BOARD_WIDTH / 2 - 30, BOARD_WIDTH - 100, 50);

        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = this.getFontMetrics(small);

        G.setColor(Color.white);
        G.setFont(small);
        G.drawString(message1, (BOARD_WIDTH - metr.stringWidth(message1)) / 2,
                BOARD_WIDTH / 2);
    }

    public void animationCycle() {
        if (deaths1 == NUMBER_OF_ALIENS_TO_DESTROY) {
            ingame1 = false;
            message1 = "Congratulation !!!";
        }

        // player

        player1.act();

        // shot
        if (shot1.isVisible()) {
            Iterator it = aliens1.iterator();
            int shotX1 = shot1.getX();
            int shotY1 = shot1.getY();

            while (it.hasNext()) {
                Alien alien = (Alien) it.next();
                int alienX1 = alien.getX();
                int alienY1 = alien.getY();

                if (alien.isVisible() && shot1.isVisible()) {
                    if (shotX1 >= (alienX1) && shotX1 <= (alienX1 + ALIEN_WIDTH)
                            && shotY1 >= (alienY1)
                            && shotY1 <= (alienY1 + ALIEN_HEIGHT)) {
                        ImageIcon ii1 = new ImageIcon(getClass().getResource(
                                expl1));
                        alien.setImage(ii1.getImage());
                        alien.setDying(true);
                        deaths1++;
                        shot1.die();
                    }
                }
            }

            int y = shot1.getY();
            y -= 8;
            if (y < 0)
                shot1.die();
            else
                shot1.setY(y);
        }

        // aliens

        Iterator it1 = aliens1.iterator();

        while (it1.hasNext()) {
            Alien a1 = (Alien) it1.next();
            int x = a1.getX();

            if (x >= BOARD_WIDTH - BORDER_RIGHT && direction1 != -1) {
                direction1 = -1;
                Iterator i1 = aliens1.iterator();
                while (i1.hasNext()) {
                    Alien a2 = (Alien) i1.next();
                    a2.setY(a2.getY() + GO_DOWN);
                }
            }

            if (x <= BORDER_LEFT && direction1 != 1) {
                direction1 = 1;

                Iterator i2 = aliens1.iterator();
                while (i2.hasNext()) {
                    Alien a = (Alien) i2.next();
                    a.setY(a.getY() + GO_DOWN);
                }
            }
        }

        Iterator it = aliens1.iterator();

        while (it.hasNext()) {
            Alien alien = (Alien) it.next();
            if (alien.isVisible()) {

                int y = alien.getY();

                if (y > GROUND - ALIEN_HEIGHT) {
                    havewon1 = false;
                    ingame1 = false;
                    message1 = "...";
                }

                alien.act(direction1);
            }
        }

        // bombs

        Iterator i3 = aliens1.iterator();
        Random generator = new Random();

        while (i3.hasNext()) {
            int shot1 = generator.nextInt(15);
            Alien a1 = (Alien) i3.next();
            Bomb b1 = a1.getBomb();
            if (shot1 == CHANCE && a1.isVisible() && b1.isDestroyed()) {

                b1.setDestroyed(false);
                b1.setX(a1.getX());
                b1.setY(a1.getY());
            }

            int bombX1 = b1.getX();
            int bombY1 = b1.getY();
            int playerX1 = player1.getX();
            int playerY1 = player1.getY();

            if (player1.isVisible() && !b1.isDestroyed()) {
                if (bombX1 >= (playerX1) && bombX1 <= (playerX1 + PLAYER_WIDTH)
                        && bombY1 >= (playerY1)
                        && bombY1 <= (playerY1 + PLAYER_HEIGHT)) {
                    ImageIcon ii = new ImageIcon(this.getClass().getResource(
                            expl1));
                    player1.setImage(ii.getImage());
                    player1.setDying(true);
                    b1.setDestroyed(true);
                    ;
                }
            }

            if (!b1.isDestroyed()) {
                b1.setY(b1.getY() + 1);
                if (b1.getY() >= GROUND - BOMB_HEIGHT) {
                    b1.setDestroyed(true);
                }
            }
        }
    }

    public void run() {
        long beforeTime1, timeDiff1, sleep1;

        beforeTime1 = System.currentTimeMillis();

        while (ingame1) {
            repaint();
            animationCycle();

            timeDiff1 = System.currentTimeMillis() - beforeTime1;
            sleep1 = DELAY - timeDiff1;

            if (sleep1 < 0)
                sleep1 = 1;
            try {
                Thread.sleep(sleep1);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }
            beforeTime1 = System.currentTimeMillis();
        }
        gameOver();
    }

    private class TA extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
            player1.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {

            player1.keyPressed(e);

            int x = player1.getX();
            int y = player1.getY();

            if (ingame1) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_SPACE) {

                    if (!shot1.isVisible())
                        shot1 = new Shot(x, y);
                }
            }
        }
    }
}
