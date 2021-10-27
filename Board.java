import java.awt.*;
import java.sql.SQLOutput;

public class Board {

    private Block[][] board;
    private int size;

    public Board() {
        board = new Block[4][4];
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                board[r][c] = new Block(Color.BLACK, 1, c * 200, r * 200, 200);
            }
        }
        size = 200;
        insertBlock();
        insertBlock();
    }

    public void drawBoard(Graphics2D g2) {
        g2.setStroke(new BasicStroke(4));
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                g2.drawRect(size * c, size * r, size, size);
            }
        }
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                board[r][c].draw(g2);
            }
        }
    }

    public void insertBlock() {
        boolean emptySpace = false;
        while (emptySpace == false) {
            int randomR = (int) (Math.random() * 4);
            int randomC = (int) (Math.random() * 4);
            Block empty = board[randomR][randomC];
            int emptyValue = (int) (Math.random() * 20);
            if (empty.getValue() == 1) {
                emptySpace = true;
                if (emptyValue < 19)
                    empty.setValue(2);
                else
                    empty.setValue(4);
            }
        }
    }

    public void moveUp()
    {
        int moveCount = 0;
        for (int r = 0; r < 3; r++)
        {
            for (int c = 0; c < 4; c++)
            {
//                System.out.println("[" + r + "][" + c + "]");
                int boardValue = board[r][c].getValue();
                int boardBelowValue = board[r + 1][c].getValue();
                int boardAboveValue = 0;
                int board2AboveValue = 0;
                if (r > 0)
                    boardAboveValue = board[r - 1][c].getValue();
                if (r > 1)
                    board2AboveValue = board[r - 2][c].getValue();
                int mergeCount = 0;
                if (boardValue == 1 && boardBelowValue != 1) {
                    moveCount++;
                    board[r][c].setValue(boardBelowValue);
                    boardValue = boardBelowValue;
                    board[r + 1][c].setValue(1);
                    boardBelowValue = 1;
                    if (boardAboveValue == 1) {
                        moveCount++;
                        board[r - 1][c].setValue(boardValue);
                        boardAboveValue = boardValue;
                        board[r][c].setValue(1);
                        boardValue = 1;
                        if (board2AboveValue == 1) {
                            moveCount++;
                            board[r - 2][c].setValue(boardAboveValue);
                            board2AboveValue = boardAboveValue;
                            board[r - 1][c].setValue(1);
                            boardAboveValue = 1;
                        } else if (mergeCount < 1 && boardAboveValue == board2AboveValue && boardAboveValue * board2AboveValue != 1) {
                            mergeCount++;
                            board[r - 2][c].setValue(boardAboveValue * 2);
                            board2AboveValue = boardAboveValue*2;
                            board[r - 1][c].setValue(1);
                            boardAboveValue = 1;
                        }
                    } else if (mergeCount < 1 && boardValue == boardAboveValue && boardValue * boardAboveValue != 1) {
                        mergeCount++;
                        board[r - 1][c].setValue(boardValue * 2);
                        boardAboveValue = boardValue*2;
                        board[r][c].setValue(1);
                        boardValue = 1;
                    }
                } else if (mergeCount < 1 && boardBelowValue == boardValue && boardBelowValue * boardValue != 1) {
                    moveCount++;
                    board[r][c].setValue(boardBelowValue * 2);
                    boardValue = boardBelowValue*2;
                    board[r + 1][c].setValue(1);
                    boardBelowValue = 1;
                }
            }
        }
        if (moveCount > 0)
            insertBlock();
    }

    public void moveDown()
    {
        int moveCount = 0;
        for (int r = 3; r > 0; r--)
        {
            for (int c = 0; c < 4; c++)
            {
//                System.out.println("[" + r + "][" + c + "]");
                int boardValue = board[r][c].getValue();
                int boardAboveValue = board[r - 1][c].getValue();
                int boardBelowValue = 0;
                int board2BelowValue = 0;
                if (r < 3)
                    boardBelowValue = board[r + 1][c].getValue();
                if (r < 2)
                    board2BelowValue = board[r + 2][c].getValue();
                int mergeCount = 0;
                if (boardValue == 1 && boardAboveValue != 1) {
                    moveCount++;
                    board[r][c].setValue(boardAboveValue);
                    boardValue = boardAboveValue;
                    board[r - 1][c].setValue(1);
                    boardAboveValue = 1;
                    if (boardBelowValue == 1) {
                        moveCount++;
                        board[r + 1][c].setValue(boardValue);
                        boardBelowValue = boardValue;
                        board[r][c].setValue(1);
                        boardValue = 1;
                        if (board2BelowValue == 1) {
                            moveCount++;
                            board[r + 2][c].setValue(boardBelowValue);
                            board2BelowValue = boardBelowValue;
                            board[r + 1][c].setValue(1);
                            boardBelowValue = 1;
                        } else if (mergeCount < 1 && boardBelowValue == board2BelowValue && boardBelowValue * board2BelowValue != 1) {
                            mergeCount++;
                            board[r + 2][c].setValue(boardBelowValue * 2);
                            board2BelowValue = boardBelowValue*2;
                            board[r + 1][c].setValue(1);
                            boardBelowValue = 1;
                        }
                    } else if (mergeCount < 1 && boardValue == boardBelowValue && boardValue * boardBelowValue != 1) {
                        mergeCount++;
                        board[r + 1][c].setValue(boardValue * 2);
                        boardBelowValue = boardValue*2;
                        board[r][c].setValue(1);
                        boardValue = 1;
                    }
                } else if (mergeCount < 1 && boardAboveValue == boardValue && boardAboveValue * boardValue != 1) {
                    moveCount++;
                    board[r][c].setValue(boardAboveValue * 2);
                    boardValue = boardAboveValue*2;
                    board[r - 1][c].setValue(1);
                    boardAboveValue = 1;
                }
            }
        }
        if (moveCount > 0)
            insertBlock();
    }

    public void moveLeft()
    {
        int moveCount = 0;
        for (int c = 0; c < 3; c++) {
            for (int r = 0; r < 4; r++) {
                int boardValue = board[r][c].getValue();
                int boardRightValue = board[r][c+1].getValue();
                int boardLeftValue = 0;
                int board2LeftValue = 0;
                if (c > 0)
                    boardLeftValue = board[r][c-1].getValue();
                if (c > 1)
                    board2LeftValue = board[r][c-2].getValue();
                int mergeCount = 0;
                if (boardValue == 1 && boardRightValue != 1) {
                    moveCount++;
                    board[r][c].setValue(boardRightValue);
                    boardValue = boardRightValue;
                    board[r][c + 1].setValue(1);
                    boardRightValue = 1;
                    if (boardLeftValue == 1) {
                        moveCount++;
                        board[r][c-1].setValue(boardValue);
                        boardLeftValue = boardValue;
                        board[r][c].setValue(1);
                        boardValue = 1;
                        if (board2LeftValue == 1) {
                            moveCount++;
                            board[r][c-2].setValue(boardLeftValue);
                            board2LeftValue = boardLeftValue;
                            board[r][c-1].setValue(1);
                            boardLeftValue = 1;
                        } else if (mergeCount < 1 && boardLeftValue == board2LeftValue && boardLeftValue * board2LeftValue != 1) {
                            mergeCount++;
                            board[r][c - 2].setValue(boardLeftValue * 2);
                            board2LeftValue = boardLeftValue*2;
                            board[r][c - 1].setValue(1);
                            boardLeftValue = 1;
                        }
                    } else if (mergeCount < 1 && boardValue == boardLeftValue && boardValue * boardLeftValue != 1) {
                        mergeCount++;
                        board[r][c - 1].setValue(boardValue * 2);
                        boardLeftValue = boardValue*2;
                        board[r][c].setValue(1);
                        boardValue = 1;
                    }
                } else if (mergeCount < 1 && boardRightValue == boardValue && boardRightValue * boardValue != 1) {
                    moveCount++;
                    board[r][c].setValue(boardRightValue * 2);
                    boardValue = boardRightValue*2;
                    board[r][c + 1].setValue(1);
                    boardRightValue = 1;
                }
            }
        }
        if (moveCount > 0)
            insertBlock();
    }

    public void moveRight()
    {
        int moveCount = 0;
        for (int c = 3; c > 0; c--)
        {
            for (int r = 0; r < 4; r++)
            {
                int boardValue = board[r][c].getValue();
                int boardLeftValue = board[r][c - 1].getValue();
                int boardRightValue = 0;
                int board2RightValue = 0;
                if (c < 3)
                    boardRightValue = board[r][c + 1].getValue();
                if (c < 2)
                    board2RightValue = board[r][c + 2].getValue();
                int mergeCount = 0;
                if (boardValue == 1 && boardLeftValue != 1) {
                    moveCount++;
                    board[r][c].setValue(boardLeftValue);
                    boardValue = boardLeftValue;
                    board[r][c - 1].setValue(1);
                    boardLeftValue = 1;
                    if (boardRightValue == 1) {
                        moveCount++;
                        board[r][c + 1].setValue(boardValue);
                        boardRightValue = boardValue;
                        board[r][c].setValue(1);
                        boardValue = 1;
                        if (board2RightValue == 1) {
                            moveCount++;
                            board[r][c + 2].setValue(boardRightValue);
                            board2RightValue = boardRightValue;
                            board[r][c + 1].setValue(1);
                            boardRightValue = 1;
                        } else if (mergeCount < 1 && boardRightValue == board2RightValue && boardRightValue * board2RightValue != 1) {
                            mergeCount++;
                            board[r][c + 2].setValue(boardRightValue * 2);
                            board2RightValue = boardRightValue*2;
                            board[r][c + 1].setValue(1);
                            boardRightValue = 1;
                        }
                    } else if (mergeCount < 1 && boardValue == boardRightValue && boardValue * boardRightValue != 1) {
                        mergeCount++;
                        board[r][c + 1].setValue(boardValue * 2);
                        boardRightValue = boardValue*2;
                        board[r][c].setValue(1);
                        boardValue = 1;
                    }
                } else if (mergeCount < 1 && boardLeftValue == boardValue && boardLeftValue * boardValue != 1) {
                    moveCount++;
                    board[r][c].setValue(boardLeftValue * 2);
                    boardValue = boardLeftValue*2;
                    board[r][c - 1].setValue(1);
                    boardLeftValue = 1;
                }
            }
        }
        if (moveCount > 0)
            insertBlock();
    }
}
