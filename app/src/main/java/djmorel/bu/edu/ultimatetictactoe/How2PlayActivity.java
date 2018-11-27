package djmorel.bu.edu.ultimatetictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class How2PlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how2_play);

        //Create TextView
        TextView infoTextView = (TextView) findViewById(R.id.infoTextView);

        //Create Button
        Button homeh2pButton = (Button) findViewById(R.id.homeh2pButton);

        //Make homeh2pButton return to the MainActivity
        homeh2pButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeh2pIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(homeh2pIntent);
            }
        });
    }
}
