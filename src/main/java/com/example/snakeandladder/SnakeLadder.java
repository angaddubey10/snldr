package com.example.snakeandladder;
import javafx.animation.AnimationTimer;
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
import javafx.stage.Stage;
import java.io.IOException;

public class SnakeLadder extends Application {
    public int diceValue;
    public Label randResult;
    public static final int tileSize = 40;
    public static final int width = 10;
    public static final int height = 10;

    public boolean playerOneTurn = true;
    public boolean playerTwoTurn = true;
     public boolean gameStart = false;
    public Button gameButton;
    private Group tileGroup = new Group();
    Player playerOne;
    Player playerTwo;

    private Parent createContent(){
        Pane root = new Pane();
        root.setPrefSize(width* tileSize, (height* tileSize)+80);
        root.getChildren().addAll(tileGroup);

//        for (int i = 0; i < height; i++) {
//            for (int j = 0; j < width; j++) {
//                Tile  tile = new Tile(tileSize, tileSize);
//                tile.setTranslateX(j * tileSize);
//                tile.setTranslateY(i * tileSize);
//                tileGroup.getChildren().add(tile);
//            }
//        }

        Button button1Player = new Button("Player One");
        button1Player.setTranslateX(320);
        button1Player.setTranslateY(400);
        button1Player.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStart){
                    if(playerOneTurn){
                        getDiceValue();
                        randResult.setText(String.valueOf(diceValue));
                        playerOne.movePlayer(diceValue);
                        playerOneTurn = false;
                        playerTwoTurn = true;
                        gameOver();
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
                    if(playerTwoTurn){
                        getDiceValue();
                        randResult.setText(String.valueOf(diceValue));
                        playerTwo.movePlayer(diceValue);
                        playerTwoTurn = false;
                        playerOneTurn = true;
                        gameOver();
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
                button1Player.setDisable(false);
                button2Player.setDisable(false);
                gameStart = true;
                playerOne.setAtStart();
                playerTwo.setAtStart();

            }
        });

        playerOne = new Player(tileSize, Color.RED);
        playerTwo = new Player(tileSize, Color.PURPLE);

        randResult = new Label("0");
        randResult.setTranslateX(150);
        randResult.setTranslateY(410);

        Image img = new Image("C:\\Users\\angaddubey\\IdeaProjects\\snldr\\src\\snakeladder.jpg");
        ImageView bgImage = new ImageView();
        bgImage.setImage(img);

        bgImage.setFitHeight(tileSize*height);
        bgImage.setFitWidth(tileSize*width);

        button1Player.setDisable(true);
        button2Player.setDisable(true);

        tileGroup.getChildren().addAll( bgImage, randResult, playerOne.getGamePiece(), playerTwo.getGamePiece(), button1Player, button2Player, gameButton);
        return root;
    }

    private void getDiceValue(){
        diceValue = (int)(Math.random()*6+1);
    }

    void gameOver(){
        if(playerOne.getWinningStatus()==true){
            randResult.setText("Player One Won");
            gameButton.setText("Start Again");
            gameStart=false;
        }
        else if(playerTwo.getWinningStatus()==true){
            randResult.setText("Player One Won");
            gameButton.setText("Start Again");
            gameStart=false;
        }
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
                long currentTime =  System.currentTimeMillis();
                long dt = currentTime -  Player.lastMovementTime;
//                System.out.println(currentTime + "  " + Player.lastMovementTime + "   " + dt);

                if(dt>1e3){
                    Player.lastMovementTime = currentTime;
//                    System.out.println(currentTime + "  " + Player.lastMovementTime);

                    playerOne.playerAtSnakeOrLadder();
                    playerTwo.playerAtSnakeOrLadder();
                }
            }
        };

        timer.start();
    }

    public static void main(String[] args) {
        launch();
    }
}
