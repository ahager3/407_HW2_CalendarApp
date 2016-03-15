package ahager3.calendarapp;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


/**
 * Created by aaron on 3/12/16.
 */
public class EditFragment extends Fragment{

    public EditText buttonOne;

    public static EditFragment newInstance(String date){
        EditFragment fragment = new EditFragment();

        return fragment;
    }

    public EditFragment(){}

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
//            correct_responses = getArguments().getInt(ARG_1);
//            total_questions = getArguments().getInt(ARG_2);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = null;
        view = inflater.inflate(R.layout.fragment_date, container, false);

        // instantiate widgets here
//        buttonOne = (EditText) view.findViewById(R.id.answerOne);
//        answerTwo = (Button) view.findViewById(R.id.answerTwo);
//        question = (TextView) view.findViewById(R.id.textView);
//        question.setText("Question " + total_questions);
//        headerTextView = (TextView) view.findViewById(R.id.header);
//        headerTextView.setText("What is the square root of 100?");
//        answerOne.setText("10");
//        answerTwo.setText("100");

        return view;
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
