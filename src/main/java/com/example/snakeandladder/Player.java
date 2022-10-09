package com.example.snakeandladder;

import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Player {
    Circle gamePiece;
    int xPosition;
    int yPosition;
    int currentPiecePosition;

    public static long lastMovementTime;
    static GameBoard gameBoard;

    Player(int tileSize, Color pieceColor){
        gameBoard = new GameBoard();
        this.currentPiecePosition = 1;
        this.xPosition = gameBoard.getXValue(currentPiecePosition);
        this.yPosition = gameBoard.getYValue(currentPiecePosition);
        this.gamePiece = new Circle(tileSize/2);
        gamePiece.setFill(pieceColor);
        gamePiece.setTranslateX(this.xPosition);
        gamePiece.setTranslateY(this.yPosition);
        lastMovementTime =  System.currentTimeMillis();
    }

    public void setAtStart(){
        currentPiecePosition = 1;
        movePlayer(0);
    }

    public void movePlayer(int diceValue){
        if(currentPiecePosition+diceValue<=100){
            this.currentPiecePosition+=diceValue;
        }
        translatePlayer();
    }
    public void playerAtSnakeOrLadder(){
        int newPosition = gameBoard.playerPositionAtSnakeOrLadder(this.currentPiecePosition);
        if(newPosition != -1){
            this.currentPiecePosition = newPosition;
            translatePlayer();
        }
    }

    public boolean getWinningStatus(){
        return currentPiecePosition == 100;
    }

    public Circle getGamePiece(){
        return this.gamePiece;
    }

    private  void translatePlayer(){
        this.xPosition = gameBoard.getXValue(currentPiecePosition);
        this.yPosition = gameBoard.getYValue(currentPiecePosition);
        TranslateTransition animate = new TranslateTransition(Duration.millis(1000),this.gamePiece);
//        System.out.println(x + " " + y);
        animate.setToX(this.xPosition);
        animate.setToY(this.yPosition);
        animate.setAutoReverse(false);
        animate.play();
        lastMovementTime =  System.currentTimeMillis();
//        System.out.println(lastMovementTime + "dddddddd");
    }

}
