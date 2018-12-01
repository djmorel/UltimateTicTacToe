package djmorel.bu.edu.ultimatetictactoe;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class SinglePlayerActivity extends AppCompatActivity implements View.OnClickListener {

    private ToggleButton[][][][] tbuttons = new ToggleButton[3][3][3][3];

    private TextView[][][][] cellmoves = new TextView[3][3][3][3];

    private String[][] cellwins = new String[3][3];

    private int turn = 0;

    private boolean player1Turn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);

        //Create Buttons
        Button confirmspgButton = (Button) findViewById(R.id.confirmspgButton);
        Button resetspgButton = (Button) findViewById(R.id.resetspgButton);
        Button homespgButton = (Button) findViewById(R.id.homespgButton);

        //Create references for buttons via a loop
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                for (int k = 0; k < 3; k++)
                {
                    for (int l = 0; l < 3; l++)
                    {
                        String buttonID = "tButton" + i + j + k + l;
                        int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                        tbuttons[i][j][k][l] = findViewById(resID);
                        tbuttons[i][j][k][l].setOnClickListener(this);
                    }
                }
            }
        }

        //Create references for the TextView
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                for (int k = 0; k < 3; k++)
                {
                    for (int l = 0; l < 3; l++)
                    {
                        String textViewID = "TextView" + i + j + k + l;
                        int resID = getResources().getIdentifier(textViewID, "id", getPackageName());
                        cellmoves[i][j][k][l] = findViewById(resID);
                        cellmoves[i][j][k][l].setText("");
                    }
                }

            }
        }
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                cellwins[i][j] = "";
            }
        }

        //make homespgButton go back to the MainActivity
        homespgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homespgIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(homespgIntent);
            }
        });

        //Confirm button Intent
        confirmspgButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Make a blank int that will store the selected Toggle Button ID
                //int selTButtonID = 0;
                boolean selButton = false;
                int Brow = 0;
                int Bcol = 0;
                int Srow = 0;
                int Scol = 0;

                //Search for the checked Toggle Button
                for (int i = 0; i < 3; i++)
                {
                    for (int j = 0; j < 3; j++)
                    {
                        for (int k = 0; k < 3; k++)
                        {
                            for (int l = 0; l < 3; l++)
                            {
                                if (tbuttons[i][j][k][l].isChecked())
                                {
                                    selButton = true;
                                    Brow = i;
                                    Bcol = j;
                                    Srow = k;
                                    Scol = l;
                                }
                            }
                        }
                    }
                }

                //If a toggle button was checked, remove it from the board
                if (selButton)
                {
                    tbuttons[Brow][Bcol][Srow][Scol].setVisibility(View.INVISIBLE);
                    tbuttons[Brow][Bcol][Srow][Scol].setChecked(false);
                    tbuttons[Brow][Bcol][Srow][Scol].setEnabled(false);

                    //Make sure the TextView is empty
                    if (cellmoves[Brow][Bcol][Srow][Scol].getText().equals(""))
                    {
                        //Write an X or an O
                        if (player1Turn)
                        {
                            cellmoves[Brow][Bcol][Srow][Scol].setText("X");
                        }
                        else
                            cellmoves[Brow][Bcol][Srow][Scol].setText("O");
                    }
                    if (CheckSmallWin(Brow, Bcol))
                    {
                        if (cellwins[Brow][Bcol].equals(""))
                        {
                            if (player1Turn) {
                                cellwins[Brow][Bcol] = "X";
                            }
                            else{
                                cellwins[Brow][Bcol] = "O";
                            }
                        }
                        if (CheckBigWin())

                            if (player1Turn){
                                player1Wins();
                            }
                            else {
                                player2Wins();
                            }
                    }
                    if (turn == 81)
                        draw();
                    turn++;

                    //Change player turn
                    player1Turn = !player1Turn;
                }
            }
        });

        //Reset button intent
        resetspgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetBoard();
            }
        });



        //Code to scale size of TTT board based on device
        //Make sure it doesn't mess with the button layout!


    }

    //Makes sure only one toggle button is selected at a time
    @Override
    public void onClick(View v) {

        //Loop through and turn off all of the toggle buttons
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                for (int k = 0; k < 3; k++)
                {
                    for (int l = 0; l < 3; l++)
                    {
                        tbuttons[i][j][k][l].setChecked(false);
                        tbuttons[i][j][k][l].setBackgroundColor(Color.WHITE);
                    }
                }
            }
        }

        //Turn on the selected toggle button
        ((ToggleButton) v).setChecked(true);
        v.setBackgroundColor(Color.GREEN);
    }

    //Resets the board
    private void resetBoard()
    {
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                for (int k = 0; k < 3; k++)
                {
                    for (int l = 0; l < 3; l++)
                    {
                        //Remove the Text
                        cellmoves[i][j][k][l].setText("");

                        //Reset all of the toggle buttons to their initial state
                        tbuttons[i][j][k][l].setEnabled(true);
                        tbuttons[i][j][k][l].setVisibility(View.VISIBLE);
                        tbuttons[i][j][k][l].setChecked(false);
                        tbuttons[i][j][k][l].setBackgroundColor(Color.WHITE);
                    }
                }
            }
        }
        //Reset the player1Turn
        player1Turn = true;
    }

    private boolean CheckSmallWin(int Brow, int Bcol)
    {
        int count = 0;
        for (int i = 0; i < 3; i++)
        {
            //checks all rows
            for (int j = 0; j < 3; j++)
            {
                if (cellmoves[Brow][Bcol][i][j].getText().equals("X"))
                    count++;
                if (count == 3)
                    return true;
            }
            count = 0;
            for (int j = 0; j < 3; j++)
            {
                if (cellmoves[Brow][Bcol][i][j].getText().equals("O"))
                count++;
                if (count == 3)
                    return true;
            }
            count = 0;
            //checks all columns
            for (int j = 0; j < 3; j++)
            {
                if (cellmoves[Brow][Bcol][j][i].getText().equals("X"))
                    count++;
                if (count == 3)
                    return true;
            }
            count = 0;
            for (int j = 0; j < 3; j++)
            {
                if (cellmoves[Brow][Bcol][j][i].getText().equals("O"))
                count++;
                if (count == 3)
                    return true;
            }
            //checks \ diagonal
            count = 0;
            for (int j = 0; j < 3; j++)
            {
                if (cellmoves[Brow][Bcol][j][j].getText().equals("X"))
                    count++;
                if (count == 3)
                    return true;
            }
            count = 0;
            for (int j = 0; j < 3; j++)
            {
                if (cellmoves[Brow][Bcol][j][j].getText().equals("O"))
                    count++;
                if (count == 3)
                    return true;
            }
            //checks / diagonal
            count = 0;
            for (int j = 0; j < 3; j++)
            {
                if (cellmoves[Brow][Bcol][j][2-j].getText().equals("X"))
                    count++;
                if (count == 3)
                    return true;
            }
            count = 0;
            for (int j = 0; j < 3; j++)
            {
                if (cellmoves[Brow][Bcol][j][2-j].getText().equals("O"))
                    count++;
                if (count == 3)
                    return true;
            }
        }
        return false;
    }

    private boolean CheckBigWin()
    {
        int count = 0;
        for (int i = 0; i < 3; i++)
        {
            //Check all the rows
            for (int j = 0; j < 3; j++)
            {
                if (cellwins[i][j].equals("X"))
                    count++;
                if (count == 3)
                    return true;
            }
            count = 0;
            for (int j = 0; j < 3; j++)
            {
                if (cellwins[i][j].equals("O"))
                    count++;
                if (count == 3)
                    return true;
            }
            //check all columns
            count = 0;
            for (int j = 0; j < 3; j++)
            {
                if (cellwins[j][i].equals("X"))
                    count++;
                if (count == 3)
                    return true;
            }
            count = 0;
            for (int j = 0; j < 3; j++)
            {
                if (cellwins[j][i].equals("O"))
                    count++;
                if (count == 3)
                    return true;
            }
        }
        //check \ diagonal
        count = 0;
        for (int i = 0; i < 3; i++)
        {
            if (cellwins[i][i].equals("X"))
                count++;
            if (count == 3)
                return true;
        }
        count = 0;
        for (int i = 0; i < 3; i++)
        {
            if (cellwins[i][i].equals("O"))
                count++;
            if (count == 3)
                return true;
        }
        //check / diagonal
        count = 0;
        for (int i = 0; i < 3; i ++)
        {
            if (cellwins[2-i][i].equals("X"))
                count++;
            if (count == 3)
                return true;
        }
        count = 0;
        for (int i = 0; i < 3; i ++)
        {
            if (cellwins[2-i][i].equals("O"))
                count++;
            if (count == 3)
                return true;
        }
        return false;
    }

    private void player1Wins()
    {
        Toast.makeText(this, "Player 1 Wins!", Toast.LENGTH_SHORT).show();

    }

    private void player2Wins()
    {
        Toast.makeText(this, "Player 2 Wins!", Toast.LENGTH_SHORT).show();

    }

    private void draw()
    {
        Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show();

    }
}
