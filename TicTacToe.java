
public class TicTacToe {
    private int rows;
    private int collumns;
    private char matrix[][];

    public TicTacToe() {// cunstructor for default matrix 3x3
        this.matrix = new char[3][3];// creates new matrix with size 3x3
        for (int i = 0; i < 3; i++) {// goes through rows
            for (int j = 0; j < 3; j++) {// goes through collumns
                matrix[i][j] = '.';// fill matrix with '.'
            }
        }
    }

    public char[][] getMatrix() {// returns matrix, cause it is private and other classes can't acess it
        return matrix;
    }

    public TicTacToe(int rows, int collumns) {// 1b.
        this.matrix = new char[rows][collumns];// creates array with size of input
        this.rows = rows;// assigns given row and collumn to instance variables rows and collumns
        this.collumns = collumns;
        for (int i = 0; i < rows; i++) {// goes through rows
            for (int j = 0; j < collumns; j++) {///goes through collumns
                matrix[i][j] = '.';// filling matrix with '.'
            }
        }
    }

    public TicTacToe(char[][] startingArray) {// takes existing 2d array to deep copy
        rows = startingArray.length;// num of rows from original array
        collumns = startingArray[0].length;// num of collumns from original array
        matrix = new char[rows][collumns];// creating new array
        for (int i = 0; i < rows; i++) {// going through rows to copy
            for (int j = 0; j < collumns; j++) {// going through collumns to copy
                matrix[i][j] = startingArray[i][j];// copying
            }
        }
    }

    private void initboard() {// because asked to be private by task
        rows = matrix.length;// num of rows
        collumns = matrix[0].length;// num of collumns
        for (int i = 0; i < rows; i++) {// going through rows
            for (int j = 0; j < collumns; j++) {// going through collumns
                matrix[i][j] = '.';// emptying
            }
        }

    }

    public void initBoard() {// public method to use private initboard
        initboard();
    }

    public boolean isEmpty(int row, int col) {// 1e
        if (matrix[row][col] == '.') {// checks if tile given by user, row, collumn is empty(has '.')
            return true;
        } else {
            return false;
        }
    }

    public char getTile(int row, int col) {// 1f
        return matrix[row][col];// returns what is in tile given by user
    }

    public void setTile(int row, int col, char player) {// 1g
        matrix[row][col] = player;// puts either X or O given by user to the given tile
    }

    public boolean gameOver() {// 1h
        if (checkRows() || checkCols() || isDiagonal()) {// checks if any of checks was succesfull
            return true;// using isDiagonal, beacuse checkDiagonal private
        } else {
            return false;
        }
    }

    private boolean checkRows() {// check rows
        for (int i = 0; i < rows; i++) {// goes through rows
            char symbol = matrix[i][0];// takes symbol of every row, and first collumn as symbol all others should be
                                       // equal to
            boolean same = true;// boolean variable to see if symbols are equal or not
            for (int j = 0; j < collumns; j++) {// goes through collumns
                if (matrix[i][j] != symbol || symbol == '.') {// checks if symbol at row, collumn equal to first symbol
                                                              // of each row
                    same = false;// if not equal same is false
                }
            }
            if (same) {// if same return true
                return true;
            }
        }
        return false;// if not same return false
    }

    private boolean checkCols() {// checking collumns
        for (int i = 0; i < collumns; i++) {
            char symbol = matrix[0][i];// takes symbol at 0th row at each collumn
            boolean same = true;// boolean variable to see if symbols equal
            for (int j = 0; j < rows; j++) {// go through collumns
                if (matrix[j][i] == symbol && symbol != '.') {// if symbols are equal to original one and not '.' then
                                                              // true
                    same = true;
                } else {
                    same = false;
                }
            }
            if (same) {
                return true;
            }
        }
        return false;

    }

    public boolean isDiagonal() {// because task asks checkDiagonal to be private
        return checkDiagonal();
    }

    private boolean checkDiagonal() {
        if (mainDiagonal() || oppositeDiagonal()) {// checks if both diagonal checks were succesfull
            return true;
        } else {
            return false;
        }
    }

    private boolean mainDiagonal() {// checks diagonal from top left to bottom right
        char symbol = matrix[0][0];// takes first symbol at top left as symbol, that all others should follow
        for (int i = 0; i < rows; i++) {// goes through diagonal
            if (matrix[i][i] != symbol || symbol == '.') {// using ii, because positions will be same, eg:00,11,22,nn
                return false;// checking if given tile has same symbol as first symbol of diagonal, excluding
                             // empty tiles
            }
        }
        return true;
    }

    public boolean oppositeDiagonal() {// checks diagonal from top right to bottom left
        char symbol = matrix[0][collumns - 1];// symbol is at top right
        for (int i = 0; i < rows; i++) {// goes through diagonal
            if (matrix[i][collumns - 1 - i] != symbol || symbol == '.') {// collumns-1, stays from begining, by -i,
                                                                         // moves down the diagonal
                return false;
            }
        }
        return true;
    }

    public String toString() {// 1I3
        String answer = "";// String for answer as a astring
        for (int i = 0; i < rows; i++) {// goes through rows
            for (int j = 0; j < collumns; j++) {// goes through collumns
                answer += matrix[i][j] + " ";// adding symbol from matrix to asnwer
            }
            answer += "\n";
        }
        return answer;
    }

    public void printMatrix() {// printing matrix
        System.out.println(toString());
    }
}