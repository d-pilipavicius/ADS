import java.awt.*;

public class Board {
    protected boolean visibility = true;
    protected int x = 0, y = 0;
    protected int squareSize = 32;
    protected Color lSquare = Color.white, dSquare = Color.black;
    protected int rows = 8, columns = 8;

    public Board(int x, int y, int squareSize){
        this.x = x;
        this.y = y;
        this.squareSize = squareSize;
    }
    public Board(int x, int y, int squareSize, Color lSquare, Color dSquare){
        this.x = x;
        this.y = y;
        this.squareSize = squareSize;
        this.lSquare = lSquare;
        this.dSquare = dSquare;
    }

    public void setVisibility(boolean visible) {
        visibility = visible;
    }

    public void displayBoard(Graphics g){
        if(!visibility)
            return;
        g.setFont(new Font("",0,squareSize/2));
        for (int i = 0; i < rows; ++i){
            g.setColor(Color.black);
            g.drawString(8-i+"", x-squareSize/2, y+squareSize*2/3+squareSize*i);
            for(int j = 0; j < columns; ++j) {
                if((i+j)%2==0)
                    g.setColor(lSquare);
                else
                    g.setColor(dSquare);
                g.fillRect(x+squareSize*j, y+squareSize*i, squareSize, squareSize);
            }
        }
        g.setColor(Color.BLACK);
        char letter = 'A';
        for(int i = 0; i < columns; ++i){
            g.drawString((letter++)+"", x+squareSize/3+squareSize*i, y+squareSize*rows+squareSize/2);
        }
    }
}
