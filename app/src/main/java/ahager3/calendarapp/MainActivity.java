package ahager3.calendarapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            return;
        }
    }

    public void playPressed(View view) {
        //TODO: Put to start game play activity
        Intent startGame = new Intent(this, CalendarActivity.class);
        startActivity(startGame);
    }
}
