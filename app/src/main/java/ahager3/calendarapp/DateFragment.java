package ahager3.calendarapp;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


/**
 * Created by aaron on 3/12/16.
 */
public class DateFragment extends Fragment{

    public static final String DATE;

    public static DateFragment newInstance(String date){
        DateFragment fragment = new DateFragment();

        return fragment;
    }

    public DateFragment(){}

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
//            correct_responses = getArguments().getInt(ARG_1);
//            total_questions = getArguments().getInt(ARG_2);
        }
    }

    public String getDate(String date){
        // File file = new File("events.txt");
        String data = "";
        Scanner scanner = new Scanner("events.txt");
        String line = scanner.nextLine();
        if(line.substring(0, date.length()).equals(date)){
            // add line to date
            data += line + "\n";
        }
        return data;
    }
    private String date;
    public void writeData(String data){
        File file = new File("events.txt");
        try {
            PrintWriter pw = new PrintWriter(file);
            pw.println(date + data);
        }
        catch(FileNotFoundException e){
            System.out.println("FileNotFoundException");
        }
    }

}
