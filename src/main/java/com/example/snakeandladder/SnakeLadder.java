package com.example.snakeandladder;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;

public class SnakeLadder extends Application {
    public int diceValue;
    public Label randResult;

//    public long prevTime = 0;
//
//    public int cirPos[][] = new int[10][10];
//    public int ladderPosition[][] = new int[6][3];
    public static final int tileSize = 40;
    public static final int width = 10;
    public static final int height = 10;

    public Circle player1;
    public Circle player2;

    public int playerPosition1 = 1;
    public int playerPosition2 = 1;

    public boolean player1Turn = true;
    public boolean player2Turn = true;

    public static int player1XPos = tileSize /2;
    public static int player1YPos = tileSize * 10 - tileSize /2;

    public static int player2XPos = tileSize /2;
    public static int player2YPos = tileSize * 10 - tileSize /2;

    public int posCir1 = 1;
    public int posCir2 = 1;
    public boolean gameStart = true;

    public Button gameButton;
    private Group tileGroup = new Group();

    ArrayList<Pair<Integer,Integer>> positionCoordinates;

    ArrayList<Integer>snakeLadderPosition;

    private void populatePositionCoordinates(){
        positionCoordinates = new ArrayList<>(100);
        for (int i = height-1; i >=0; i--) {
            for (int j = width-1; j >=0 ; j--) {
                Tile  tile = new Tile(tileSize, tileSize);
                tile.setTranslateX(j * tileSize);
                tile.setTranslateY(i * tileSize);
                tileGroup.getChildren().add(tile);
                int xTilePos;
                if(i%2 !=0 ){
                    xTilePos = tileSize*10 - (tileSize/2 + j * tileSize);
                }
                else {
                    xTilePos = tileSize/2 + j * tileSize;
                }
                int yTilePos = tileSize/2 + i * tileSize;
                int pos = 100-i*height - j;
                System.out.println(i+ " " + j+ " " + pos + " " + xTilePos + " " + yTilePos);
                positionCoordinates.add(new Pair<Integer, Integer>(xTilePos,yTilePos) );
            }
        }
    }

    private  void   populateSnakeLadderPosition(){
        snakeLadderPosition = new ArrayList<>(101);
        for (int i = 0; i < 101; i++) {
            snakeLadderPosition.add(i);
        }
        snakeLadderPosition.set(14,35);
        snakeLadderPosition.set(22,78);
        snakeLadderPosition.set(29,8);
        snakeLadderPosition.set(31,70);
        snakeLadderPosition.set(74,86);
        snakeLadderPosition.set(76,37);
        snakeLadderPosition.set(89,68);
        snakeLadderPosition.set(97,80);
    }

    Player playerOne;
    Player playerTwo;


    private Parent createContent(){
        Pane root = new Pane();
        root.setPrefSize(width* tileSize, (height* tileSize)+80);
        root.getChildren().addAll(tileGroup);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Tile  tile = new Tile(tileSize, tileSize);
                tile.setTranslateX(j * tileSize);
                tile.setTranslateY(i * tileSize);
                tileGroup.getChildren().add(tile);
            }
        }

//        player1 = new Circle(tileSize /2);
//        player1.setId("player1");
//        player1.setFill(Color.AQUA);
//        player1.getStyleClass().add("style.css");
//        player1.setTranslateX(player1XPos);
//        player1.setTranslateY(player1YPos);
//
//        player2 = new Circle(tileSize /2);
//        player2.setId("player2");
//        player2.setFill(Color.CHOCOLATE);
//        player2.getStyleClass().add("style.css");
//        player2.setTranslateX(player2XPos);
//        player2.setTranslateY(player2YPos);

        playerOne = new Player(tileSize, Color.RED);
        playerTwo = new Player(tileSize, Color.PURPLE);

        Button button1Player = new Button("Player One");
        button1Player.setTranslateX(320);
        button1Player.setTranslateY(400);
        button1Player.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStart){
                    if(player1Turn){
                        getDiceValue();
                        randResult.setText(String.valueOf(diceValue));
                        playerOne.movePlayer(diceValue);
//                        move1Player();
//                        translatePlayer(player1XPos, player1YPos, player1);
                        player1Turn = false;
                        player2Turn = true;
//                        snakeFall();
                    }
                }
            }
        });

        Button button2Player = new Button("Player2");
        button2Player.setTranslateX(40);
        button2Player.setTranslateY(400);
        button2Player.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStart){
                    if(player2Turn){
                        getDiceValue();
                        randResult.setText(String.valueOf(diceValue));
//                        move2Player();
//                        translatePlayer(player2XPos, player2YPos, player2);
                        playerTwo.movePlayer(diceValue);
                        player2Turn = false;
                        player1Turn = true;
//                        snakeFall();

                    }
                }
            }
        });

        gameButton = new Button("Start Game");
        gameButton.setTranslateX(200);
        gameButton.setTranslateY(420);
        gameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameButton.setText("Game Started");
//                player1XPos = tileSize /2;
//                player1YPos = tileSize *10 - tileSize /2;
//
//                player2XPos = tileSize /2;
//                player2YPos = tileSize *10 - tileSize /2;
//
//                player1.setTranslateX(player1XPos);
//                player1.setTranslateY(player1YPos);
//                player2.setTranslateX(player2XPos);
//                player2.setTranslateY(player2YPos);
                gameStart = true;

            }
        });

        randResult = new Label("0");
        randResult.setTranslateX(150);
        randResult.setTranslateY(410);

        Image img = new Image("C:\\Users\\angaddubey\\IdeaProjects\\snldr\\src\\snakeladder.jpg");
        ImageView bgImage = new ImageView();
        bgImage.setImage(img);

        bgImage.setFitHeight(tileSize*height);
        bgImage.setFitWidth(tileSize*width);

        tileGroup.getChildren().addAll( bgImage, randResult,playerOne.getGamePiece(), playerTwo.getGamePiece(), button1Player, button2Player, gameButton);
//        tileGroup.getChildren().addAll(player2);
        return root;
    }

    private Parent createContent1(){
        Pane root = new Pane();
        root.setPrefSize(width* tileSize, (height* tileSize)+80);
        root.getChildren().addAll(tileGroup);

        populatePositionCoordinates();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Tile  tile = new Tile(tileSize, tileSize);
                tile.setTranslateX(j * tileSize);
                tile.setTranslateY(i * tileSize);
                tileGroup.getChildren().add(tile);
            }
        }

        player1 = new Circle(tileSize /2);
        player1.setId("player1");
        player1.setFill(Color.AQUA);
        player1.getStyleClass().add("style.css");
        player1.setTranslateX(player1XPos);
        player1.setTranslateY(player1YPos);

        player2 = new Circle(tileSize /2);
        player2.setId("player2");
        player2.setFill(Color.CHOCOLATE);
        player2.getStyleClass().add("style.css");
        player2.setTranslateX(player2XPos);
        player2.setTranslateY(player2YPos);

        Button button1Player = new Button("Player1");
        button1Player.setTranslateX(320);
        button1Player.setTranslateY(400);
        button1Player.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStart){
                    if(player1Turn){
                        getDiceValue();
                        randResult.setText(String.valueOf(diceValue));
                        move1Player();
                        translatePlayer(player1XPos, player1YPos, player1);
                        player1Turn = false;
                        player2Turn = true;
//                        snakeFall();
                    }
                }
            }
        });

        Button button2Player = new Button("Player2");
        button2Player.setTranslateX(40);
        button2Player.setTranslateY(400);
        button2Player.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStart){
                    if(player2Turn){
                        getDiceValue();
                        randResult.setText(String.valueOf(diceValue));
                        move2Player();
                        translatePlayer(player2XPos, player2YPos, player2);
                        player2Turn = false;
                        player1Turn = true;
//                        snakeFall();

                    }
                }
            }
        });

        gameButton = new Button("Start Game");
        gameButton.setTranslateX(200);
        gameButton.setTranslateY(420);
        gameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameButton.setText("Game Started");
                player1XPos = tileSize /2;
                player1YPos = tileSize *10 - tileSize /2;

                player2XPos = tileSize /2;
                player2YPos = tileSize *10 - tileSize /2;

                player1.setTranslateX(player1XPos);
                player1.setTranslateY(player1YPos);
                player2.setTranslateX(player2XPos);
                player2.setTranslateY(player2YPos);
                gameStart = true;

            }
        });

        randResult = new Label("0");
        randResult.setTranslateX(150);
        randResult.setTranslateY(410);

        Image img = new Image("C:\\Users\\angaddubey\\IdeaProjects\\snldr\\src\\snakeladder.jpg");
        ImageView bgImage = new ImageView();
        bgImage.setImage(img);

        bgImage.setFitHeight(400);
        bgImage.setFitWidth(400);

        tileGroup.getChildren().addAll( bgImage, randResult,player2, player1, button1Player, button2Player, gameButton);
//        tileGroup.getChildren().addAll(player2);
        return root;
    }
    private void getDiceValue(){
        diceValue = (int)(Math.random()*6+1);
    }



    private void move1Player(){
        for(int i = 0; i< diceValue; i++){
            if(posCir1 % 2 == 1){
                player1XPos+=40;
            }
            if(posCir1 % 2 == 0){
                player1XPos-=40;
            }

            if(player1XPos > 380){
                player1YPos -= 40;
                player1XPos -= 40;
                posCir1++;
            }

            if(player1XPos < 20){
                player1YPos -= 40;
                player1XPos += 40;
                posCir1++;
            }

            if( player1XPos<15 ||  player1YPos<15 ){
                player1XPos = 20;
                player1YPos = 40;
                randResult.setText("Player One Won");
                gameButton.setText("Start Again");
            }
        }
    }

    private void move2Player(){
        for(int i = 0; i< diceValue; i++){
            if(posCir2 % 2 == 1){
                player2XPos+=40;
            }
            if(posCir2 % 2 == 0){
                player2XPos-=40;
            }

            if(player2XPos > 380){
                player2YPos -= 40;
                player2XPos -= 40;
                posCir2++;
            }

            if(player2XPos < 20){
                player2YPos -= 40;
                player2XPos += 40;
                posCir2++;
            }

            if( player2XPos<15 ||  player2YPos<15 ) {
                player2XPos = 20;
                player2YPos = 20;
                randResult.setText("Player Two Won");
                gameButton.setText("Start Again");
            }
        }
    }

    private void snakeFall(){
        if(player1XPos == 140 && player1YPos == 380){
            player1XPos = 260;
            player1YPos = 340;
            translatePlayer(player1XPos, player1YPos, player1);
        }
    }

    private  void translatePlayer(int x, int y, Circle b){
        TranslateTransition animate = new TranslateTransition(Duration.millis(1000),b);
        System.out.println(x + " " + y);
        animate.setToX(x);
        animate.setToY(y);
        animate.setAutoReverse(false);
        animate.play();
    }
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Snake and Ladder");
        stage.setScene(scene);
        stage.show();

//        AnimationTimer timer = new AnimationTimer() {
//            @Override
//            public void handle(long l) {
//                long dt = l - prevTime;
//                if(dt>2e9){
//                    prevTime = l;
//                    snakeFall();
//                }
//
//            }
//        };
//
//        timer.start();
    }

    public static void main(String[] args) {
        launch();
    }
}
