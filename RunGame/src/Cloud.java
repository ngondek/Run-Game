import javax.swing.*;
import java.awt.*;

public class Cloud extends GameIcon {

    private Image cloud;

    public Cloud(int frameWidth, int frameHeight){
        super(frameWidth, frameHeight);
        cloud = (new ImageIcon("images\\cloud.dark.png").getImage());

        setX((int)(Math.random()*frameWidth));
        setY((int)(Math.random()*(10)));

        setIconWidth(cloud.getWidth(null));
        setIconHeight(cloud.getHeight(null));
    }

    public Image getImage(){
        return cloud;
    }

    @Override
    public void move() {
        if(getX() > - getIconWidth())
            setX(getX() - speed);
        else
            setX(frameWidth);
    }
}
