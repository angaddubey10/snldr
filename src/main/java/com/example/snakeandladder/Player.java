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


//        this.gamePiece = new Circle(tileSize/2);
////        gamePiece.setId("player2");
//
//        gamePiece.getStyleClass().add("style.css");
//        gamePiece.setTranslateX(0);
//        gamePiece.setTranslateY(0);
    }

    public void movePlayer(int diceValue){
        if(currentPiecePosition+diceValue<=100){
            this.currentPiecePosition+=diceValue;
        }
        translatePlayer();
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



    }

    private  void translatePlayer1(int x, int y, Circle b){
        TranslateTransition animate = new TranslateTransition(Duration.millis(1000),b);
        System.out.println(x + " " + y);
        animate.setToX(x);
        animate.setToY(y);
        animate.setAutoReverse(false);
        animate.play();



    }

}
