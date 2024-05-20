public class Algorithm {
    private final int rows = 8;
    private final int columns = 8;
    private final int bishops = 8;
    private boolean newSave = false;
    private String lastSaved;
    private EightBishops connectedTo;

    Algorithm(EightBishops eightBishops) {
        connectedTo = eightBishops;
        lastSaved = "1040414234443646";
        pushVariationToBoard(lastSaved);
    }

    public String variation(int[][] board){
        String temp = "";
        for(int i = 0; i < rows; ++i) {
            for(int j = 0; j < columns; ++j) {
                if(board[i][j] == 1){
                    temp += j+""+i;
                }
            }
        }
        return temp;
    }
    private void saveVariation(int[][] board){
        lastSaved = variation(board);
    }
    public void pushVariationToBoard(String var){
        for(int i = 0; i < bishops; ++i){
            int x = Character.getNumericValue( var.charAt(2*i) );
            int y = Character.getNumericValue( var.charAt(2*i+1) );
            connectedTo.pushBishop(x, y);
        }
    }
    private void placeBishops(int[][] board, String last, int bishopNr){
        int[][] tempB = new int[rows][columns];
        copyBoardVariation(board, tempB);
        int x = Character.getNumericValue( last.charAt(bishopNr*2) );
        int y = Character.getNumericValue( last.charAt(bishopNr*2+1) );
        int square = x+y*columns;
        if(bishops-1 == bishopNr){
            square++;
        }
        for(int i = square; i <= columns*rows-(bishops-bishopNr); ++i){
            if(bishopNr == 0 && i > 32){
                newSave = false;
                return;
            }
            if(checkForSkip(i) || board[i/columns][i%columns] == 2){
                continue;
            }
            copyBoardVariation(board, tempB);
            markMovement(tempB, i);
            if(bishopNr == bishops-1){
                if(checkIfSolved(tempB)){
                    saveVariation(tempB);
                    newSave = true;
                    return;
                }
                else{
                    newSave = false;
                }
            }
            else{
                placeBishops(tempB, last, bishopNr+1);
                if(newSave)
                    return;
                last = rewriteVar(last, bishopNr, i+1);
            }
        }
        newSave = false;
    }
    private String rewriteVar(String var, int bishopID, int square){
        var = var.substring(0, bishopID*2);
        for(int i = 0; i < bishops-bishopID; ++i){
            var += square%columns;
            var += square/columns;
        }
        return var;
    }
    private boolean checkForSkip(int square){
        final int[] skip = {0, 2, 5, 7, 9, 14, 16, 23, 40, 47, 49, 54, 56, 58, 61, 63};
        for(int i = square/5; skip[i] <= square; ++i){
            if(square == skip[i]){
                return true;
            }
            if(i == skip.length-1)
                break;
        }
        return false;
    }
    private boolean checkIfSolved(int[][] board){
        for(int i = 0; i < rows; ++i){
            for(int j = 0; j < columns; ++j){
                if(board[i][j] == 0)
                    return false;
            }
        }
        return true;
    }
    private void markMovement(int[][] board, int square){
        int x = square%rows;
        int y = square/rows;
        board[y][x] = 1;
        for(int i = 0; i < rows-1; ++i){
            if(x+i < columns && y+i < rows && board[y+i][x+i] == 0){
                board[y+i][x+i] = 2;
            }
            if(x-i >= 0 && y+i < rows && board[y+i][x-i] == 0){
                board[y+i][x-i] = 2;
            }
            if(x-i >= 0 && y-i >= 0 && board[y-i][x-i] == 0){
                board[y-i][x-i] = 2;
            }
            if(x+i < columns && y-i >= 0 && board[y-i][x+i] == 0){
                board[y-i][x+i] = 2;
            }
        }
    }

    public void nextVariation(){
        placeBishops(new int[8][8], lastSaved, 0);
        pushVariationToBoard(lastSaved);
    }

    private void copyBoardVariation(int[][] source, int[][] destination) {
        for(int i = 0; i < rows; ++i){
            for(int j = 0; j < columns; ++j){
                destination[i][j] = source[i][j];
            }
        }
    }
}
