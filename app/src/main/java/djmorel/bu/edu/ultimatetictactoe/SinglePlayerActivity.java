package djmorel.bu.edu.ultimatetictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SinglePlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);

        //Create Buttons
        Button confirmspgButton = (Button) findViewById(R.id.confirmspgButton);
        Button resetspgButton = (Button) findViewById(R.id.resetspgButton);
        Button homespgButton = (Button) findViewById(R.id.homespgButton);

        //make homespgButton go back to the MainActivity
        homespgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homespgIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(homespgIntent);
            }
        });

        //Code to scale size of TTT board based on device
        //Make sure it doesn't mess with the button layout!



        //Back end code for the TTT game (maybe it goes here...)

    }
}
