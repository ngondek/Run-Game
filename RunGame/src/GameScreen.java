import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameScreen extends JPanel implements KeyListener {

    private ScoreLabel scoreLabel;

    private int frameHeight;
    private int frameWidth;

    private MainCharacter character;
    private Cloud[] clouds;
    private Grass[] grasses;
    private GameIcon []obstacles;

    private GameStatus gameStatus;

    private Image startImage = new ImageIcon("images\\start.png").getImage();
    private Image gameOverImage = new ImageIcon("images\\gameOver.png").getImage();


    GameScreen(int width, int height){

        frameWidth = width;
        frameHeight = height;

        character = new MainCharacter(frameWidth,frameHeight);

        clouds = new Cloud[6];
        for(int i =0; i <clouds.length; i++)
            clouds[i] = new Cloud(frameWidth,frameHeight);

        grasses = new Grass[5];
        for(int i =0; i <grasses.length; i++)
            grasses[i] = new Grass(frameWidth,frameHeight);

        obstacles = new GameIcon[4];
        obstacles[0] = new Bush(frameWidth,frameHeight);
        obstacles[1] = new Flower(frameWidth,frameHeight);
        obstacles[2] = new TwoFlowers(frameWidth,frameHeight);
        obstacles[3] = new Squirrel(frameWidth,frameHeight);

        scoreLabel = new ScoreLabel();
        add(scoreLabel);

        refresh();
    }

    private void refresh(){
        Thread thread = new Thread(() ->{
            while(true){
                try {
                    Thread.sleep(35);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                repaint();
            }
        }); thread.start();
    }
    void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    private void startTheGame () {
        setGameStatus(GameStatus.PLAYING);

        scoreLabel.reset();
        updateScore();

        moveObstacles();
        moveScene();
    }

    private void updateScore(){
        Thread thread = new Thread(() -> {
            while (gameStatus == GameStatus.PLAYING) {
                scoreLabel.update();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });thread.start();
    }

    synchronized private void moveObstacles(){
        Thread thread = new Thread(() ->{
            while(gameStatus == GameStatus.PLAYING) {

                int obstacle_id =(int) (Math.random() * (obstacles.length));

                    while (gameStatus == GameStatus.PLAYING &&  obstacles[obstacle_id].getX() > - obstacles[obstacle_id].getIconWidth()) {
                        obstacles[obstacle_id].move();
                        watcher(obstacle_id);
                        try {
                            Thread.sleep(25);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if(gameStatus == GameStatus.PLAYING)
                         obstacles[obstacle_id].setX(frameWidth);
            }

        });thread.start();
    }

    private void watcher(int id) {
        if ( character.getRectangle().intersects(obstacles[id].getRectangle()) )
            gameOver();
    }

    synchronized private void moveScene() {
        Thread thread = new Thread(() ->{
            while(gameStatus == GameStatus.PLAYING) {
                for(Cloud c : clouds)
                    c.move();
                for(Grass g : grasses)
                    g.move();
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });thread.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(new Color(100,140,200));
        g.fillRect(0,0,frameWidth, frameHeight /2+50);
        g.setColor(new Color(53, 151, 58));
        g.fillRect(0, frameHeight/2+50, frameWidth, frameHeight/2-50);

        for(Cloud c: clouds)
            g.drawImage(c.getImage(),c.getX(),c.getY(),null);

        for(Grass gr: grasses)
            g.drawImage(gr.getImage(),gr.getX(),gr.getY(),null);

        for(GameIcon obstacle :obstacles )
            g.drawImage(obstacle.getImage(),obstacle.getX(),obstacle.getY(),null);

        g.drawImage(character.getImage(),character.getX(),character.getY(),null);

        if(gameStatus == GameStatus.START)
            g.drawImage(startImage, (frameWidth-startImage.getWidth(null))/2,(frameHeight-startImage.getHeight(null))/2,null);
        if(gameStatus == GameStatus.GAMEOVER)
            g.drawImage(gameOverImage, (frameWidth-gameOverImage.getWidth(null))/2,(frameHeight-gameOverImage.getHeight(null))/2,null);
    }

    private void gameOver(){
        setGameStatus(GameStatus.GAMEOVER);
        scoreLabel.updateBestScore();
    }

    void endGame(){
        scoreLabel.saveScore();
    }

    private void reset()  {
        for(GameIcon gi : obstacles)
            gi.setX(frameWidth);

        character.setY(frameHeight/2);

        startTheGame();
    }

    private void jump (){
        Thread thread0 = new Thread(() -> {
            while (character.getY() >= 30 && gameStatus == GameStatus.PLAYING) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                character.moveUp();
            }
            while (character.getY() < frameHeight/2 && gameStatus == GameStatus.PLAYING) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                character.moveDown();
            }
        });thread0.start();
    }

    @Override
    public void keyTyped(KeyEvent e) { }
    @Override
    public void keyPressed(KeyEvent e) {

        if(gameStatus == GameStatus.PLAYING)
            jump();
        else if (gameStatus == GameStatus.START)
            startTheGame();
        else
            reset();
    }
    @Override
    public void keyReleased(KeyEvent e) {}

}
