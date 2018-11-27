package djmorel.bu.edu.ultimatetictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SelectGameMode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_game_mode);

        //Create text view
        TextView gamemodeTextView = (TextView) findViewById(R.id.gamemodeTextView);

        //Create buttons
        Button singleplayerButton = (Button) findViewById(R.id.singleplayerButton);
        Button multiplayerButton = (Button) findViewById(R.id.multiplayerButton);
        Button homeButton = (Button) findViewById(R.id.homeButton);

        //Make the homeButton return to the MainActivity
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(homeIntent);
            }
        });

        //Make the singleplayerButton go to the SinglePlayerActivity
        singleplayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent singleplayerIntent = new Intent(getApplicationContext(), SinglePlayerActivity.class);
                startActivity(singleplayerIntent);
            }
        });

        //Make the multiplayerButton go to the MultiplayerActivity
        multiplayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent multiplayerIntent = new Intent(getApplicationContext(), MultiplayerActivity.class);
                startActivity(multiplayerIntent);
            }
        });
    }
}
