import javax.swing.*;
import java.io.*;

public class ScoreLabel extends JLabel {
    private int record;
    private int score=0;
    private int bestScore =0;
    File file = new File("scores.txt");

    ScoreLabel(){
        super();
        setText("Current score : " +score +"  "+  "Record : " + record);
        setSize(100,100);

        getRecord();
    }

    void update(){
        score += 10;
        setText("Current score : " +score + "  "+ "Record : " + record);
    }

    void getRecord(){
        record = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null)
            {
               try {
                    int tempScore = Integer.parseInt(line);
                    if (tempScore > record)
                    {
                        record = tempScore;
                    }
                } catch (NumberFormatException e1) {
                     //System.err.println("ignoring invalid score: " + line);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException ex) {
            System.err.println("ERROR reading scores from file");
        }
        if(bestScore > record)
            record = bestScore;
    }

    void saveScore(){
        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(file, true));
            output.newLine();
            output.append("" + bestScore);
            output.close();

        } catch (IOException ex1) {
            System.out.printf("ERROR writing score to file: %s\n", ex1);
        }
    }

    void updateBestScore(){
        if(score > bestScore)
            bestScore = score;
    }

    void reset(){
        score = 0;
        getRecord();
    }
}
