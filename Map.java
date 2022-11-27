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

    private void gameInit() {
        aliens1 = new ArrayList();

        ImageIcon ii1 = new ImageIcon(this.getClass().getResource(alienpix1));

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                Alien alien1 = new Alien(alienX1 + 18 * j, alienY1 + 18 * i);
                alien1.setImage(ii1.getImage());
                aliens1.add(alien1);
            }
        }

        player1 = new Player();
        shot1 = new Shot();

        if (animator1 == null || !ingame1) {
            animator1 = new Thread(this);
            animator1.start();
        }
    }

    public void drawAliens1(Graphics G) {
        Iterator it1 = aliens1.iterator();

        while (it1.hasNext()) {
            Alien alien1 = (Alien) it1.next();

            if (alien1.isVisible()) {
                G.drawImage(alien1.getImage1(), alien1.getX1(), alien1.getY1(), this);
            }

            if (alien1.isDying1()) {
                alien1.die();
            }
        }
    }

    public void drawPlayer1(Graphics G) {
        if (player1.isVisible()) {
            G.drawImage(player1.getImage1(), player1.getX1(), player1.getY1(), this);
        }

        if (player1.isDying1()) {
            player1.die();
            havewon1 = false;
            ingame1 = false;
        }
    }

    public void drawGameEnd1 (Graphics G) {
        G.drawImage(gameend1.getImage1(), 0, 0, this);
    }

    public void drawShot1(Graphics G) {
        if (shot1.isVisible())
            G.drawImage(shot1.getImage1(), shot1.getX1(), shot1.getY1(), this);
    }

    public void drawBombing1(Graphics G) {
        Iterator i31 = aliens1.iterator();

        while (i31.hasNext()) {
            Alien a1 = (Alien) i31.next();

            Bomb b1 = a1.getBomb1();

            if (!b1.isDestroyed()) {
                G.drawImage(b1.getImage1(), b1.getX1(), b1.getY1(), this);
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
            drawAliens1(G);
            drawPlayer1(G);
            drawShot1(G);
            drawBombing1(G);
        }

        Toolkit.getDefaultToolkit().sync();
        G.dispose();
    }

    private void drawGameend1(Graphics G) {
        G.drawImage(gameend1.getImage1(), 0, 0, this);
    }


    public void gameOver1() {
        Graphics G = this.getGraphics();

        gameend1 = new GameOver();
        vunnet1 = new Won();

        // g.setColor(Color.black);
        G.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGTH);
        if (havewon1 == true) {
            G.drawImage(vunnet1.getImage1(), 0, 0, this);
        } else {
            G.drawImage(gameend1.getImage1(), 0, 0, this);
        }
        G.setColor(new Color(0, 32, 48));
        G.fillRect(50, BOARD_WIDTH / 2 - 30, BOARD_WIDTH - 100, 50);
        G.setColor(Color.white);
        G.drawRect(50, BOARD_WIDTH / 2 - 30, BOARD_WIDTH - 100, 50);

        Font small1 = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr1 = this.getFontMetrics(small1);

        G.setColor(Color.white);
        G.setFont(small1);
        G.drawString(message1, (BOARD_WIDTH - metr1.stringWidth(message1)) / 2,
                BOARD_WIDTH / 2);
    }

    public void animationCycle1() {
        if (deaths1 == NUMBER_OF_ALIENS_TO_DESTROY) {
            ingame1 = false;
            message1 = "Congratulation !!!";
        }

        // player

        player1.act1();

        // shot
        if (shot1.isVisible()) {
            Iterator it1 = aliens1.iterator();
            int shotX1 = shot1.getX1();
            int shotY1 = shot1.getY1();

            while (it1.hasNext()) {
                Alien alien1 = (Alien) it1.next();
                int alienX1 = alien1.getX1();
                int alienY1 = alien1.getY1();

                if (alien1.isVisible() && shot1.isVisible()) {
                    if (shotX1 >= (alienX1) && shotX1 <= (alienX1 + ALIEN_WIDTH)
                            && shotY1 >= (alienY1)
                            && shotY1 <= (alienY1 + ALIEN_HEIGHT)) {
                        ImageIcon ii1 = new ImageIcon(getClass().getResource(
                                expl1));
                        alien1.setImage1(ii1.getImage());
                        alien1.setDying1(true);
                        deaths1++;
                        shot1.die();
                    }
                }
            }

            int y1 = shot1.getY1();
            y1 -= 8;
            if (y1 < 0)
                shot1.die();
            else
                shot1.setY1(y1);
        }

        // aliens

        Iterator it11 = aliens1.iterator();

        while (it11.hasNext()) {
            Alien a11 = (Alien) it11.next();
            int x1 = a11.getX1();

            if (x1 >= BOARD_WIDTH - BORDER_RIGHT && direction1 != -1) {
                direction1 = -1;
                Iterator i11 = aliens1.iterator();
                while (i11.hasNext()) {
                    Alien a21 = (Alien) i11.next();
                    a21.setY1(a21.getY1() + GO_DOWN);
                }
            }

            if (x1 <= BORDER_LEFT && direction1 != 1) {
                direction1 = 1;

                Iterator i21 = aliens1.iterator();
                while (i21.hasNext()) {
                    Alien a1 = (Alien) i21.next();
                    a1.setY1(a1.getY1() + GO_DOWN);
                }
            }
        }

        Iterator it1 = aliens1.iterator();

        while (it1.hasNext()) {
            Alien alien1 = (Alien) it1.next();
            if (alien1.isVisible()) {

                int y1 = alien1.getY1();

                if (y1 > GROUND - ALIEN_HEIGHT) {
                    havewon1 = false;
                    ingame1 = false;
                    message1 = "...";
                }

                alien1.act2(direction1);
            }
        }

        // bombs

        Iterator i31 = aliens1.iterator();
        Random generator1 = new Random();

        while (i31.hasNext()) {
            int shot1 = generator1.nextInt(15);
            Alien a1 = (Alien) i31.next();
            Bomb b1 = a1.getBomb1();
            if (shot1 == CHANCE && a1.isVisible() && b1.isDestroyed()) {

                b1.setDestroyed(false);
                b1.setX1(a1.getX1());
                b1.setY1(a1.getY1());
            }

            int bombX1 = b1.getX1();
            int bombY1 = b1.getY1();
            int playerX1 = player1.getX1();
            int playerY1 = player1.getY1();

            if (player1.isVisible() && !b1.isDestroyed()) {
                if (bombX1 >= (playerX1) && bombX1 <= (playerX1 + PLAYER_WIDTH)
                        && bombY1 >= (playerY1)
                        && bombY1 <= (playerY1 + PLAYER_HEIGHT)) {
                    ImageIcon ii1 = new ImageIcon(this.getClass().getResource(
                            expl1));
                    player1.setImage1(ii1.getImage());
                    player1.setDying1(true);
                    b1.setDestroyed(true);
                    ;
                }
            }

            if (!b1.isDestroyed()) {
                b1.setY1(b1.getY1() + 1);
                if (b1.getY1() >= GROUND - BOMB_HEIGHT) {
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
            animationCycle1();

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
        gameOver1();
    }

    private class TA extends KeyAdapter {

        public void keyReleased(KeyEvent E) {
            player1.keyReleased1(E);
        }

        public void keyPressed(KeyEvent E) {

            player1.keyPressed1(E);

            int x1 = player1.getX1();
            int y1 = player1.getY1();

            if (ingame1) {
                int key1 = E.getKeyCode();
                if (key1 == KeyEvent.VK_SPACE) {

                    if (!shot1.isVisible())
                        shot1 = new Shot(x1, y1);
                }
            }
        }
    }
}
