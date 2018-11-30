package djmorel.bu.edu.ultimatetictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class SinglePlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);

        //Create Buttons
        Button confirmspgButton = (Button) findViewById(R.id.confirmspgButton);
        Button resetspgButton = (Button) findViewById(R.id.resetspgButton);
        Button homespgButton = (Button) findViewById(R.id.homespgButton);

        //Cell Buttons
        CheckBox cellspg00Button = (CheckBox) findViewById(R.id.cellspg00Button);
        CheckBox cellspg01Button = (CheckBox) findViewById(R.id.cellspg01Button);
        CheckBox cellspg02Button = (CheckBox) findViewById(R.id.cellspg02Button);
        CheckBox cellspg03Button = (CheckBox) findViewById(R.id.cellspg03Button);
        CheckBox cellspg04Button = (CheckBox) findViewById(R.id.cellspg04Button);
        CheckBox cellspg05Button = (CheckBox) findViewById(R.id.cellspg05Button);
        CheckBox cellspg06Button = (CheckBox) findViewById(R.id.cellspg06Button);
        CheckBox cellspg07Button = (CheckBox) findViewById(R.id.cellspg07Button);
        CheckBox cellspg08Button = (CheckBox) findViewById(R.id.cellspg08Button);

        CheckBox cellspg10Button = (CheckBox) findViewById(R.id.cellspg10Button);
        CheckBox cellspg11Button = (CheckBox) findViewById(R.id.cellspg11Button);
        CheckBox cellspg12Button = (CheckBox) findViewById(R.id.cellspg12Button);
        CheckBox cellspg13Button = (CheckBox) findViewById(R.id.cellspg13Button);
        CheckBox cellspg14Button = (CheckBox) findViewById(R.id.cellspg14Button);
        CheckBox cellspg15Button = (CheckBox) findViewById(R.id.cellspg15Button);
        CheckBox cellspg16Button = (CheckBox) findViewById(R.id.cellspg16Button);
        CheckBox cellspg17Button = (CheckBox) findViewById(R.id.cellspg17Button);
        CheckBox cellspg18Button = (CheckBox) findViewById(R.id.cellspg18Button);

        CheckBox cellspg20Button = (CheckBox) findViewById(R.id.cellspg20Button);
        CheckBox cellspg21Button = (CheckBox) findViewById(R.id.cellspg21Button);
        CheckBox cellspg22Button = (CheckBox) findViewById(R.id.cellspg22Button);
        CheckBox cellspg23Button = (CheckBox) findViewById(R.id.cellspg23Button);
        CheckBox cellspg24Button = (CheckBox) findViewById(R.id.cellspg24Button);
        CheckBox cellspg25Button = (CheckBox) findViewById(R.id.cellspg25Button);
        CheckBox cellspg26Button = (CheckBox) findViewById(R.id.cellspg26Button);
        CheckBox cellspg27Button = (CheckBox) findViewById(R.id.cellspg27Button);
        CheckBox cellspg28Button = (CheckBox) findViewById(R.id.cellspg28Button);


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
