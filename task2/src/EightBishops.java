import javax.swing.*;
import java.awt.*;

public class EightBishops extends Board{
    private int[][] pieces;
    private int nrOfBishops = 8;
    Image piece;
    EightBishops(int x, int y, int squareSize, String dir){
        super(x, y, squareSize);
        loadBishop(dir);
        pieces = new int[8][2];
        resetPieces();
    }
    EightBishops(int x, int y, int squareSize, String dir, Color lSquare, Color dSquare){
        super(x, y, squareSize, lSquare, dSquare);
        loadBishop(dir);
        pieces = new int[8][2];
        resetPieces();
        for(int i = 0; i < nrOfBishops; ++i) {
            pushBishop(i/nrOfBishops, i%nrOfBishops);
        }
    }
    public void resetPieces() {
        for (int i = 0; i < 8; ++i) {
            pieces[i][0] = -1;
            pieces[i][1] = -1;
        }
    }
    public void displayBoard(Graphics g, JPanel p){
        displayBoard(g);
        if(!visibility)
            return;
        for(int i = 0; i < nrOfBishops; ++i){
            displayBishop(i, g, p);
        }
        for(int i = 0; i < nrOfBishops; ++i){
            displayBishopMovement(i, g);
        }
    }
    public void pushBishop(int x, int y){
        int i = 0;
        boolean exists = false;
        while (i != nrOfBishops && pieces[i][0] != -1) {
            if(exists && i != nrOfBishops-1) {
                pieces[i][0] = pieces[i+1][0];
                pieces[i][1] = pieces[i+1][1];
            }
            else if(exists && i == nrOfBishops-1){
                pieces[i][0] = x;
                pieces[i][1] = y;
                return;
            }
            else if(pieces[i][0] == x && pieces[i][1] == y && !exists) {
                exists = true;
                if(i != nrOfBishops-1) pieces[i][0] = pieces[i+1][0];
                if(i != nrOfBishops-1) pieces[i][1] = pieces[i+1][1];
            }
            i++;
        }
        if(exists){
            pieces[i-1][0] = x;
            pieces[i-1][1] = y;
        }
        else if(i == nrOfBishops){
            for(i = 1; i < nrOfBishops; ++i){
                pieces[i-1][0] = pieces[i][0];
                pieces[i-1][1] = pieces[i][1];
            }
            pieces[nrOfBishops-1][0] = x;
            pieces[nrOfBishops-1][1] = y;
        }
        else {
            pieces[i][0] = x;
            pieces[i][1] = y;
        }
    }

    public void removeLastBishop(){
        int i = 0;
        while (i != nrOfBishops && pieces[i][0] !=-1) {
            i++;
        }
        if(i == 0){
            return;
        }
        pieces[i-1][0] = -1;
        pieces[i-1][1] = -1;
    }
    private void loadBishop(String dir) {
        piece = Toolkit.getDefaultToolkit().getImage(dir);
    }
    public void displayBishop(int bishopNr, Graphics g, JPanel p){
        int bX = pieces[bishopNr][0], bY = pieces[bishopNr][1];
        if(pieces[bishopNr][0] == -1) {
            return;
        }
        g.drawImage(piece, x+squareSize*bX, y+squareSize*bY, squareSize, squareSize, p);
    }
    private void displayBishopMovement(int bishopID, Graphics g){
        int bX = pieces[bishopID][0], bY = pieces[bishopID][1];
        if(bX == -1)
            return;
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.RED);
        g2.setStroke(new BasicStroke(4));
        for(int i = 0; i < 4; ++i) {
            int offsetX = squareSize, offsetY = squareSize;
            switch (i) {
                case 0:
                    offsetX *= ((bX > bY) ? bX-bY : 0);
                    offsetY *= ((bX > bY) ? 0 : bY-bX);
                    break;
                case 1:
                    offsetX *= ((bX > rows-1-bY) ? bX-(rows-1-bY) : 0);
                    offsetY *= ((bX > rows-1-bY) ? rows-1 : bX+bY);
                    break;
                case 2:
                    offsetX *= ((columns-1-bX > bY) ?  bX+bY: columns-1);
                    offsetY *= ((columns-1-bX > bY) ? 0 : bY-(columns-1-bX));
                    break;
                case 3:
                    offsetX *= ((bX > bY) ? columns-1 : bX+rows-1-bY);
                    offsetY *= ((bX > bY) ? bY+columns-1-bX : rows-1);
                    break;
            }
            if(bX*squareSize == offsetX && bY*squareSize == offsetY)
                continue;
            int dirX = ((offsetX > bX*squareSize) ? -1 : 1 );
            int dirY = ((offsetY > bY*squareSize) ? -1 : 1 );
            g2.drawLine(x+squareSize/2+squareSize*bX, y+squareSize/2+squareSize*bY, x+squareSize/2+offsetX, y+squareSize/2+offsetY);
            g2.drawLine(x+squareSize/2+offsetX, y+squareSize/2+offsetY, x+squareSize/2+offsetX, y+squareSize/2+offsetY+dirY*squareSize/4);
            g2.drawLine(x+squareSize/2+offsetX, y+squareSize/2+offsetY, x+squareSize/2+offsetX+dirX*squareSize/4, y+squareSize/2+offsetY);
        }
    }
}
