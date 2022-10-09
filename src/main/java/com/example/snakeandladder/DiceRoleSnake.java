package com.example.snakeandladder;

import javafx.animation.AnimationTimer;
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

import java.io.IOException;

public class DiceRoleSnake extends Application {
    public int rand;
    public Label randResult;

    public long prevTime = 0;

    public int cirPos[][] = new int[10][10];
    public int ladderPosition[][] = new int[6][3];
    public static final int Tile_Size = 40;
    public static final int width = 10;
    public static final int height = 10;

    public Circle player1;
    public Circle player2;

    public int playerPosition1 = 1;
    public int playerPosition2 = 1;

    public boolean player1Turn = true;
    public boolean player2Turn = true;

    public static int player1XPos = Tile_Size/2;
    public static int player1YPos = Tile_Size * 10 - Tile_Size/2;

    public static int player2XPos = Tile_Size/2;
    public static int player2YPos = Tile_Size * 10 - Tile_Size/2;

    public int posCir1 = 1;
    public int posCir2 = 1;
    public boolean gameStart = true;

    public Button gameButton;
    private Group tileGroup = new Group();
    private Parent createContent(){
        Pane root = new Pane();
        root.setPrefSize(width*Tile_Size, (height*Tile_Size)+80);
        root.getChildren().addAll(tileGroup);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Tile  tile = new Tile(Tile_Size, Tile_Size);
                tile.setTranslateX(j * Tile_Size);
                tile.setTranslateY(i * Tile_Size);
                tileGroup.getChildren().add(tile);
            }
        }

        player1 = new Circle(Tile_Size/2);
        player1.setId("player1");
        player1.setFill(Color.AQUA);
        player1.getStyleClass().add("style.css");
        player1.setTranslateX(player1XPos);
        player1.setTranslateY(player1YPos);

        player2 = new Circle(Tile_Size/2);
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
                        randResult.setText(String.valueOf(rand));
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
                        randResult.setText(String.valueOf(rand));
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
                player1XPos = Tile_Size/2;
                player1YPos = Tile_Size*10 - Tile_Size/2;

                player2XPos = Tile_Size/2;
                player2YPos = Tile_Size*10 - Tile_Size/2;

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
        rand  = (int)(Math.random()*6+1);
        rand = 3;
    }



    private void move1Player(){
        for(int i=0; i<rand; i++){
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
        for(int i=0; i<rand; i++){
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

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                long dt = l - prevTime;
                if(dt>2e9){
                    prevTime = l;
                    snakeFall();
                }

            }
        };

        timer.start();
    }

    public static void main(String[] args) {
        launch();
    }
}