package djmorel.bu.edu.ultimatetictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MultiplayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer);

        //Create Buttons
        Button confirmmpgButton = (Button) findViewById(R.id.confirmmpgButton);
        Button resetmpgButton = (Button) findViewById(R.id.resetmpgButton);
        Button homempgButton = (Button) findViewById(R.id.homempgButton);

        //make homempgButton go back to the MainActivity
        homempgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homempgIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(homempgIntent);
            }
        });

        //Code to scale size of TTT board based on device
        //Make sure it doesn't mess with the button layout!



        //Back end code for the TTT game (maybe it goes here...)




    }
}
