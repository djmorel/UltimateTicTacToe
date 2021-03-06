package djmorel.bu.edu.ultimatetictactoe;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


public class MultiplayerActivity extends AppCompatActivity implements View.OnClickListener{


    private ToggleButton[][][][] tbuttons = new ToggleButton[3][3][3][3];

    private TextView[][][][] cellmoves = new TextView[3][3][3][3];

    private String[][] cellwins = new String[3][3];

    private Button[][] colorButton = new Button[3][3];

    private boolean player1Turn = true;

    private int turn = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer);

        //Create references for menu buttons
        final Button confirmmpgButton = (Button) findViewById(R.id.confirmmpgButton);
        Button resetmpgButton = (Button) findViewById(R.id.resetmpgButton);
        Button homempgButton = (Button) findViewById(R.id.homempgButton);

        //Adding Music
        final MediaPlayer background=MediaPlayer.create(MultiplayerActivity.this,R.raw.background);
        background.start();
        background.setLooping(true);

        //Create references for color buttons
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                String colorButtonID = "colorButton" + i + j;
                int resID = getResources().getIdentifier(colorButtonID,"id",getPackageName());
                colorButton[i][j] = findViewById(resID);
                colorButton[i][j].setBackgroundColor(Color.GRAY);
            }
        }

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

        //Set the cellwins array to be blank
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                cellwins[i][j] = "";
            }
        }

        //make homempgButton go back to the MainActivity
        homempgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                background.stop();
                Intent homempgIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(homempgIntent);
            }
        });

        //Confirm button Intent
        confirmmpgButton.setOnClickListener(new View.OnClickListener(){
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
                            if (player1Turn)
                            {
                                cellwins[Brow][Bcol] = "X";
                                //Change color of cell to reflect territorial control
                                colorButton[Brow][Bcol].setBackgroundColor(0xFF6fa2d6);
                            }
                            else {
                                cellwins[Brow][Bcol] = "O";
                                //Change color of cell to reflect territorial control
                                colorButton[Brow][Bcol].setBackgroundColor(0xFFca8491);
                            }
                        }
                        if (CheckBigWin())

                            if (player1Turn) {
                                player1Wins();
                                confirmmpgButton.setEnabled(false);

                                //Disable unselected tile buttons
                                for (int i = 0; i < 3; i++) {
                                    for (int j = 0; j < 3; j++) {
                                        for (int k = 0; k < 3; k++) {
                                            for (int l = 0; l < 3; l++) {
                                                //Check if a button hasn't been pressed already
                                                if (cellmoves[i][j][k][l].getText().equals("")) {
                                                    //Disable unselected buttons
                                                    tbuttons[i][j][k][l].setEnabled(false);
                                                    tbuttons[i][j][k][l].setBackgroundColor(Color.LTGRAY);
                                                }
                                            }
                                        }
                                    }
                                }
                            } else {
                                player2Wins();
                                confirmmpgButton.setEnabled(false);

                                //Disable unselected tile buttons
                                for (int i = 0; i < 3; i++) {
                                    for (int j = 0; j < 3; j++) {
                                        for (int k = 0; k < 3; k++) {
                                            for (int l = 0; l < 3; l++) {
                                                //Check if a button hasn't been pressed already
                                                if (cellmoves[i][j][k][l].getText().equals("")) {
                                                    //Disable unselected buttons
                                                    tbuttons[i][j][k][l].setEnabled(false);
                                                    tbuttons[i][j][k][l].setBackgroundColor(Color.LTGRAY);
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                    }
                    if (turn == 80) {
                        draw();
                        confirmmpgButton.setEnabled(false);
                    }

                    turn++;

                    //Change player turn
                    player1Turn = !player1Turn;

                    //Disable all of the buttons
                    //If statement for free play
                    for (int i = 0; i < 3; i++)
                    {
                        for (int j = 0; j < 3; j++)
                        {
                            for (int k = 0; k < 3; k++)
                            {
                                for (int l = 0; l < 3; l++)
                                {
                                    //Check if a button hasn't been pressed already
                                    if (cellmoves[i][j][k][l].getText().equals(""))
                                    {
                                        //Disable unselected buttons
                                        tbuttons[i][j][k][l].setEnabled(false);
                                        tbuttons[i][j][k][l].setBackgroundColor(Color.LTGRAY);
                                    }
                                }
                            }
                        }
                    }
                    //Enable the appropriate buttons if game isn't over
                    if (!CheckBigWin())
                    {
                        //Check if there are any open spaces
                        boolean isClosed = true;

                        for (int k = 0; k < 3; k++)
                        {
                            for (int l = 0; l < 3; l++)
                            {
                                //Check if a button hasn't been pressed already
                                if (cellmoves[Srow][Scol][k][l].getText().equals(""))
                                {
                                    //Enable buttons in target range
                                    tbuttons[Srow][Scol][k][l].setEnabled(true);
                                    tbuttons[Srow][Scol][k][l].setBackgroundColor(Color.WHITE);

                                    //Change isOpen to show that there was at least one enabled button
                                    isClosed = false;
                                }
                            }
                        }

                        //If no available buttons, enable all unselected buttons on the board
                        if (isClosed)
                        {
                            for (int i = 0; i < 3; i++)
                            {
                                for (int j = 0; j < 3; j++)
                                {
                                    for (int k = 0; k < 3; k++)
                                    {
                                        for (int l = 0; l < 3; l++)
                                        {
                                            //Check if a button hasn't been pressed already
                                            if (cellmoves[i][j][k][l].getText().equals(""))
                                            {
                                                //Enable buttons in target range
                                                tbuttons[i][j][k][l].setEnabled(true);
                                                tbuttons[i][j][k][l].setBackgroundColor(Color.WHITE);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });

        //Reset button intent
        resetmpgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetBoard();
                confirmmpgButton.setEnabled(true);
            }
        });

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

                        //Check if toggle button was enabled
                        if ( !(tbuttons[i][j][k][l].isEnabled()) )
                        {
                            //If toggle button wasn't enabled, keep its color gray
                            tbuttons[i][j][k][l].setBackgroundColor(Color.LTGRAY);
                        }
                        else
                        {
                            //Toggle button was enabled, so reset to white
                            tbuttons[i][j][k][l].setBackgroundColor(Color.WHITE);
                        }

                    }
                }
            }
        }

        //Turn on the selected toggle button
        ((ToggleButton) v).setChecked(true);
        v.setBackgroundColor(0xFFf07624);
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
                //Reset the cellwins array
                cellwins[i][j] = "";

                //Reset the colors
                colorButton[i][j].setBackgroundColor(Color.GRAY);
            }
        }
        //Reset the player1Turn
        player1Turn = true;
        //Reset the turn count
        turn = 0;
    }

    private boolean CheckSmallWin(int Brow, int Bcol)
    {
        //Check the rows for TTT
        for (int i = 0; i < 3; i++)
        {
            if (cellmoves[Brow][Bcol][i][0].getText().equals(cellmoves[Brow][Bcol][i][1].getText())
                    && cellmoves[Brow][Bcol][i][0].getText().equals(cellmoves[Brow][Bcol][i][2].getText())
                    && !cellmoves[Brow][Bcol][i][0].getText().equals(""))
            {
                return true;
            }
        }

        //Check the columns for TTT
        for (int i = 0; i < 3; i++)
        {
            if (cellmoves[Brow][Bcol][0][i].getText().equals(cellmoves[Brow][Bcol][1][i].getText())
                    && cellmoves[Brow][Bcol][0][i].getText().equals(cellmoves[Brow][Bcol][2][i].getText())
                    && !cellmoves[Brow][Bcol][0][i].getText().equals(""))
            {
                return true;
            }
        }

        //Check the back slash diagonal (\) for TTT
        if (cellmoves[Brow][Bcol][0][0].getText().equals(cellmoves[Brow][Bcol][1][1].getText())
                && cellmoves[Brow][Bcol][0][0].getText().equals(cellmoves[Brow][Bcol][2][2].getText())
                && !cellmoves[Brow][Bcol][0][0].getText().equals(""))
        {
            return true;
        }

        //Check the forward slash diagonal (/) for TTT
        if (cellmoves[Brow][Bcol][2][0].getText().equals(cellmoves[Brow][Bcol][1][1].getText())
                && cellmoves[Brow][Bcol][2][0].getText().equals(cellmoves[Brow][Bcol][0][2].getText())
                && !cellmoves[Brow][Bcol][2][0].getText().equals(""))
        {
            return true;
        }

        //If nothing above returned true, there isn't a TTT
        return false;
    }

    private boolean CheckBigWin()
    {

        //Check the rows for TTT
        for (int i = 0; i < 3; i++)
        {
            if (cellwins[i][0].equals(cellwins[i][1])
                    && cellwins[i][0].equals(cellwins[i][2])
                    && !cellwins[i][0].equals(""))
            {
                return true;
            }
        }

        //Check the columns for TTT
        for (int i = 0; i < 3; i++)
        {
            if (cellwins[0][i].equals(cellwins[1][i])
                    && cellwins[0][i].equals(cellwins[2][i])
                    && !cellwins[0][i].equals(""))
            {
                return true;
            }
        }

        //Check the back slash diagonal for TTT
        if (cellwins[0][0].equals(cellwins[1][1])
                && cellwins[0][0].equals(cellwins[2][2])
                && !cellwins[0][0].equals(""))
        {
            return true;
        }

        //Check the forward slash diagonal for TTT
        if (cellwins[2][0].equals(cellwins[1][1])
                && cellwins[2][0].equals(cellwins[0][2])
                && !cellwins[2][0].equals(""))
        {
            return true;
        }

        //If nothing above returned true, there isn't a TTT
        return false;
    }

    //Print Player 1 Wins
    private void player1Wins()
    {
        Toast.makeText(this, "Player 1 Wins!", Toast.LENGTH_SHORT).show();
    }

    //Print Player 2 Wins
    private void player2Wins()
    {
        Toast.makeText(this, "Player 2 Wins!", Toast.LENGTH_SHORT).show();
    }

    //Print Draw
    private void draw()
    {
        Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show();
    }
}
