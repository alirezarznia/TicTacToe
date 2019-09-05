package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageView[] images = new ImageView[9];
    private boolean isFirstStarter=  true;
    byte[][] array = new byte[9][9];
    private ImageView p1 ;
    private ImageView p2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        images[0] = findViewById(R.id.img1);
        images[1] = findViewById(R.id.img2);
        images[2] = findViewById(R.id.img3);
        images[3] = findViewById(R.id.img4);
        images[4] = findViewById(R.id.img5);
        images[5] = findViewById(R.id.img6);
        images[6] = findViewById(R.id.img7);
        images[7] = findViewById(R.id.img8);
        images[8] = findViewById(R.id.img9);
        p1= findViewById(R.id.imgp1);
        p2= findViewById(R.id.imgp2);
        p2.setVisibility(View.INVISIBLE);
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView img =(ImageView) view;
                int ii = (int)view.getTag();
                int row =(int) Math.floor(ii/3.0);
                int col = ii-(row*3);
                if(array[row][col] !=0){
                    Toast.makeText(MainActivity.this , "WTF :/" , Toast.LENGTH_SHORT).show();
                    return;
                }
                if(isFirstStarter){
                    images[ii].setImageResource(R.mipmap.x);
                    array[row][col]=1;
                    p1.setVisibility(View.INVISIBLE);
                    p2.setVisibility(View.VISIBLE);
                }
                else{
                    p2.setVisibility(View.INVISIBLE);
                    p1.setVisibility(View.VISIBLE);
                    images[ii].setImageResource(R.mipmap.o);
                    array[row][col]=2;
                }
                boolean blWin = checkWin(row, col);
                if(!blWin){
                    boolean isCell = checkCell();
                    if(!isCell) {
                        setBegin();

                        Toast.makeText(MainActivity.this , "Draw", Toast.LENGTH_LONG).show();

                    }

                }
                isFirstStarter=!isFirstStarter;


            }
        };
        for (int i = 0; i < 9; i++) {
            images[i].setTag(i);
            images[i].setOnClickListener(clickListener);
        }
    }
    private boolean checkCell(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(array[i][j] ==0) return true;
            }
        }
        return false;

    }

    private void printMessage(){
        if(isFirstStarter){
            Toast.makeText(MainActivity.this , "Player 1 Won", Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(MainActivity.this , "Player 2 Won", Toast.LENGTH_LONG).show();
    }
    private void setBegin(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                array[i][j]=0;
                int cur= i*3;
                images[cur+j].setImageResource(0);
            }
        }

        p2.setVisibility(View.INVISIBLE);
        p1.setVisibility(View.VISIBLE);
        isFirstStarter=false;

    }
    private boolean checkWin(int row , int ii){
        int current = array[row][ii];

        boolean winRow=false ,winCol=false ,winDia= false;
        for (int i = 0; i < 3; i++) {
            if(array[row][i] != current){
                winRow=true;
            }
            if(array[i][ii] != current){
                winCol=true;
            }
        }
        if(!(winCol&&winRow)){
            printMessage();
            setBegin();
            return true;
        }
        if((array[0][0]==array[1][1] && array[1][1]==array[2][2] && array[2][2]==current )
                ||(array[2][0]==array[1][1] && array[1][1]==array[0][2] && array[0][2]==current)) {
            winDia = true;
        }
        if(winDia){
            printMessage();
            setBegin();
            return true;
        }

        return false;
    }
}
