import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameFrame extends JFrame  {


    private int frameHeight;
    private int frameWidth;

    private GameScreen screen;


    public GameFrame() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frameHeight = (int) (screenSize.getHeight() * 0.6);
        frameWidth = (int) (screenSize.getWidth() * 0.75);
        setSize(frameWidth, frameHeight);
        setResizable(false);

        screen = new GameScreen(frameWidth,frameHeight);
        getContentPane().add(screen);
        addKeyListener(screen);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                screen.endGame();
                System.exit(0);
            }
        });

        setVisible(true);

        start();
    }

    void start() {
        screen.setGameStatus(GameStatus.START);
    }

    public static void main(String[] args)  {
           new GameFrame();
    }

}
