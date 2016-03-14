package ahager3.calendarapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by aaron on 3/11/16.
 */
public class CalendarActivity extends AppCompatActivity{

    //Enter a date
    //move to new fragment which will show data
    // and buttons: back, create, delete

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, DateFragment.newInstance("Jan 1"))
                .addToBackStack(null)
                .commit();
    }


}
