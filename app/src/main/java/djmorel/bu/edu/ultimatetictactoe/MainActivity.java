package djmorel.bu.edu.ultimatetictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create textView
        TextView apptitleTextView = (TextView) findViewById(R.id.apptitleTextView);
        TextView apptitle2TextView = (TextView) findViewById(R.id.apptitle2TextView);

        //Create buttons
        Button playButton = (Button) findViewById(R.id.playButton);
        Button how2playButton = (Button) findViewById(R.id.how2playButton);

        //Make the playButton go to the SelectGameMode Activity
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gamemodeIntent = new Intent(getApplicationContext(), SelectGameMode.class);
                startActivity(gamemodeIntent);
            }
        });

        //Make the how2playButton go to the How2PlayActivity
        how2playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent how2playIntent = new Intent(getApplicationContext(), How2PlayActivity.class);
                startActivity(how2playIntent);
            }
        });
    }
}
