package com.example.snakeandladder;
import javafx.util.Pair;
import java.util.ArrayList;

public class GameBoard {
    public static final int tileSize = 40;
    public static final int width = 10;
     static final int height = 10;
    static ArrayList<Pair<Integer,Integer>> positionCoordinates;
    static ArrayList<Integer>snakeLadderPosition;

    public  GameBoard(){
        populatePositionCoordinates();
        populateSnakeLadderPosition();
    }

    public int getXValue(int piecePosition){
        if(piecePosition<=100 && piecePosition>=1){
            return positionCoordinates.get(piecePosition).getKey();
        }
        else return positionCoordinates.get(1).getKey();
    }
    public int getYValue(int piecePosition){
        if(piecePosition<=100 && piecePosition>=1){
            return positionCoordinates.get(piecePosition).getValue();
        }
        else return positionCoordinates.get(1).getValue();
    }

    private void populatePositionCoordinates(){
        positionCoordinates = new ArrayList<>(100);
        for (int i = height-1; i >=0; i--) {
            for (int j = width-1; j >=0 ; j--) {
                int xTilePos;
                if(i%2 !=0 ){
                    xTilePos = tileSize*10 - (tileSize/2 + j * tileSize);
                }
                else {
                    xTilePos = tileSize/2 + j * tileSize;
                }
                int yTilePos = tileSize/2 + i * tileSize;
                int pos = 100-i*height - j;
//                System.out.println(i+ " " + j+ " " + pos + " " + xTilePos + " " + yTilePos);
                positionCoordinates.add(new Pair<Integer, Integer>(xTilePos,yTilePos) );
            }
        }
        for(int i=0; i<positionCoordinates.size(); i++){
            System.out.println(i + "  x : " + positionCoordinates.get(i).getKey() + "    y : " + positionCoordinates.get(i).getValue());
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

}
