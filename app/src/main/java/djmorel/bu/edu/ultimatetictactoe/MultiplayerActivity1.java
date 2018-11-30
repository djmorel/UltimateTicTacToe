package djmorel.bu.edu.ultimatetictactoe;



import android.content.Intent;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;

import android.widget.TextView;

import android.widget.Toast;

import android.widget.ToggleButton;




import org.w3c.dom.Text;



public class MultiplayerActivity extends AppCompatActivity implements View.OnClickListener{



    private ToggleButton[][] tbuttons = new ToggleButton[9][9];

    private TextView[][] cellmoves = new TextView[9][9];

    private Cellwins[][] cwins = new Cellwins[3][3]; //idk how to do this MAKE SURE IT is GOOD

    //private boolean player1Turn = true;

    private int turn = 0; //i was thinking of just using this since its simpler

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_multiplayer);

        //Create references for menu buttons

        Button confirmmpgButton = (Button) findViewById(R.id.confirmmpgButton);

        Button resetmpgButton = (Button) findViewById(R.id.resetmpgButton);

        Button homempgButton = (Button) findViewById(R.id.homempgButton);


        //Create references for buttons via a loop

        for (int i = 0; i < 9; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                String buttonID = "tButton00" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                tbuttons[i][j] = findViewById(resID);
                tbuttons[i][j].setOnClickListener(this);
            }
        }

        //Create references for the TextView

        for (int i = 0; i < 9; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                String textViewID = "TextView00" + i + j;
                int resID = getResources().getIdentifier(textViewID, "id", getPackageName());
                cellmoves[i][j] = findViewById(resID);
                cellmoves[i][j].setText("");
            }
        }

        //Create reference for cellwins
        for (int i = 0; i < 3; i++)     //MAKE SURE thiS IS GOOD
        {
            for (int j = 0; j < 3; j++)
            {
                cwins[i][j].setText("");
            }
        }

        //make homempgButton go back to the MainActivity

        homempgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                int n1 = 0;
                int n2 = 0;

                //Search for the checked Toggle Button

                for (int i = 0; i < 9; i++)
                {
                    for (int j = 0; j < 9; j++)
                    {
                        if (tbuttons[i][j].isChecked())
                        {
                            selButton = true;
                            n1 = i;
                            n2 = j;
                        }
                    }
                }

                //If a toggle button was checked, remove it from the board

                if (selButton)
                {
                    tbuttons[n1][n2].setVisibility(View.INVISIBLE);
                    tbuttons[n1][n2].setEnabled(false);

                    //Make sure the TextView is empty

                    if(cellmoves[n1][n2].getText().equals(""))
                    {

                        //Write an X or an O

                        if (turn%2 == 0)
                        {
                            cellmoves[n1][n2].setText("X");
                        }
                        else
                            cellmoves[n1][n2].setText("O");

                    }
                    if (Checkforsmallwin) 
                    {
                        if (n1 < 3)
                            int row = 0;
                        if (n1 > 2 && n1 < 6)
                            int row = 1;
                        if (n1 > 5 && m1 < 9)
                            int row = 2
                        int col = n1 - 3*row;
                        if (turn%2 == 0)
                            cwins[row][col].setText("X");
                        else
                            cwins[row][col].setText("O");
                        if (Checkforbigwin)
                        {
                            if (turn%2 = 0)
                                player1Wins();
                            else
                                player2Wins();
                        }
                    }
                    if (turn == 81)
                        draw();
                }

                //Change player turn
                turn++;
                //player1Turn = !player1Turn; when would player1Turn == player1Turn?
            }
        });

        //Reset button intent

        resetmpgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetBoard();
            }
        });


        //Code to scale size of TTT board based on device

        //Make sure it doesn't mess with the button layout!





        //Back end code for the TTT game (maybe it goes here...)



    }



    //Makes sure only one toggle button is selected at a time

    @Override

    public void onClick(View v) {

        //Loop through and turn off all of the toggle buttons

        for (int i = 0; i < 9; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                tbuttons[i][j].setChecked(false);
            }
        }

        //Turn on the selected toggle button

        ((ToggleButton) v).setChecked(true);
    }



    //Resets the board

    private void resetBoard()
    {
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                //Remove the Text

                cellmoves[i][j].setText("");

                //Reset all of the toggle buttons to their initial state

                tbuttons[i][j].setEnabled(true);
                tbuttons[i][j].setVisibility(View.VISIBLE);
                tbuttons[i][j].setChecked(false);

                //Reset the player1Turn

                player1Turn = true;
            }
        }
    }



    private void player1Wins()
    {
        Toast.makeText(this, "Player 1 Wins!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void player2Wins()
    {
        Toast.makeText(this, "Player 2 Wins!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void draw()
    {
        Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private boolean isFull(int n1) //MAKE SURE THIS IS GOOD //need to somehow use this so only tbuttons[n1] are touchable if false
    {
        int count = 0;
        for (int i = 0; i < 9; i++)
            if(cellmoves[n1][i] != "")
                count++;
        if(count == 9 )
            return true;
        return false;
    }

    private boolean Checkforbigwin()
    {
        int count = 0;
        for (int i = 0; i < 3; i++)
        {    
            for (int j = 0; j < 3; j++)
            {
                if (cwins[i][j] == 'X')
                    count++;
                if (count == 3)
                    return true;
            }
            count = 0;
            for (int j = 0; j < 3; j++)
            {
                if (cwins[i][j] == 'O')
                    count++;
                if (count == 3)
                    return true;
            }
            count = 0;
            for (int j = 0; j < 3; j++)
            {
                if (cwins[j][i] == 'X')
                    count++;
                if (count == 3)
                    return true;
            }
            count = 0;
            for (int j = 0; j < 3; j++)
            {
                if (cwins[j][i] == 'O')
                    count++;
                if (count == 3)
                    return true;
            }
        }
        //check Diagnols 
        count = 0;
        for (int i = 0; i < 3; i ++)
        {
            if (cwins[i][i] == 'X')
                count++;
            if (count == 3)
                return true;
        }
        count = 0;
        for (int i = 0; i < 3; i ++)
        {
            if (cwinsi][i] == 'O')
                count++;
            if (count == 3)
                return true;
        }
        count = 0;
        for (int i = 0; i < 3; i ++)
        {
            if (cwins[2-i][i] == 'X')
                count++;
            if (count == 3)
                return true;
        }
        count = 0;
        for (int i = 0; i < 3; i ++)
        {
            if (cwins[2-i][i] == 'O')
                count++;
            if (count == 3)
                return true;
        }
        return false;
    }

    private boolean Checkforsmallwin(int n1)
    {
        int count = 0;
        //check rows first
        for (int i = 0; i < 3; i++)
        {
            if (cellmoves[n1][i] == "X")
                count++;  
            if (count == 3)
                retrn true;   
        }   
        count = 0; 
        for (int i = 3; i < 6; i++)
        {
            if (cellmoves[n1][i] == "X")
                count++;  
            if (count == 3)
                retrn true; 
        }
        count = 0;
        for (int i = 6; i < 9; i++)
        {
            if (cellmoves[n1][i] == "X")
                count++;  
            if (count == 3)
                retrn true; 
        }
        count = 0;
        for (int i = 0; i < 3; i++)
        {
            if (cellmoves[n1][i] == "O")
                count++;  
            if (count == 3)
                retrn true;       
        }
        count = 0; 
        for (int i = 3; i < 6; i++)
        {
            if (cellmoves[n1][i] == "O")
                count++; 
            if (count == 3)
                retrn true;  
        }
        count = 0;
        for (int i = 6; i < 9; i++)
        {
            if (cellmoves[n1][i] == "O")
                count++;
            if (count == 3)
                retrn true;   
        }
        count = 0;
        //check columns second
        for (int i = 0; i < 9; i = i + 3)
        {
            if (cellmoves[n1][i] == "X")
                count++; 
            if (count == 3)
                retrn true;         
        }
        count = 0; 
        for (int i = 1; i < 9; i = i + 3)
        {
            if (cellmoves[n1][i] == "X")
                count++;
            if (count == 3)
                retrn true;    
        }
        count = 0;
        for (int i = 2; i < 9; i = i + 3)
        {
            if (cellmoves[n1][i] == "X")
                count++; 
            if (count == 3)
                retrn true;   
        }
        count = 0;
        for (int i = 0; i < 9; i = i + 3)
        {
            if (cellmoves[n1][i] == "O")
                count++;
            if (count == 3)
                retrn true;          
        }
        count = 0; 
        for (int i = 1; i < 9; i = i + 3)
        {
            if (cellmoves[n1][i] == "O")
                count++;  
            if (count == 3)
                retrn true;  
        }
        count = 0;
        for (int i = 2; i < 9; i = i + 3)
        {
            if (cellmoves[n1][i] == "O")
                count++;
            if (count == 3)
                retrn true;    
        }
        count = 0;
        //check diagnols last
        for (int i = 0; i < 9; i = i + 4)
        {
            if (cellmoves[n1][i] == "X")
                count++;
            if (count == 3)
                retrn true;
        }
        count = 0;
        for (int i = 2; i < 7; i = i + 2)
        {
            if (cellmoves[n1][i] == "X")
                count++;
            if (count == 3)
                retrn true; 
        }
        count = 0;
        for (int i = 0; i < 9; i = i + 4)
        {
            if (cellmoves[n1][i] == "O")
                count++;
            if (count == 3)
                retrn true;
        }
        count = 0;
        for (int i = 2; i < 7; i = i + 2)
        {
            if (cellmoves[n1][i] == "O")
                count++;
            if (count == 3)
                retrn true; 
        }
        return false;
    }
}