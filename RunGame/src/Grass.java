import javax.swing.*;
import java.awt.*;

public class Grass extends GameIcon {

    private Image grass;

    public Grass(int frameWidth, int frameHeight){
        super(frameWidth, frameHeight);
        grass = (new ImageIcon("images\\grass.png").getImage());

        setX((int)(Math.random()*frameWidth));
        setY((int)(Math.random()*(frameHeight/2-50)+frameHeight/2+50));

        setIconWidth(grass.getWidth(null));
        setIconHeight(grass.getHeight(null));
    }

    public Image getImage(){
        return grass;
    }

    @Override
    public void move() {
        if(getX() > - getIconWidth())
            setX(getX() - speed);
        else
            setX(frameWidth);
    }
}
