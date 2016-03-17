package ahager3.calendarapp;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
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
public class DateFragment extends Fragment{

    public EditText buttonOne;

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

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = null;
        view = inflater.inflate(R.layout.fragment_date, container, false);

        buttonOne = (EditText) view.findViewById(R.id.buttonOne);
//        answerTwo = (Button) view.findViewById(R.id.answerTwo);
//        question = (TextView) view.findViewById(R.id.textView);
//        question.setText("Question " + total_questions);
//        headerTextView = (TextView) view.findViewById(R.id.header);
//        headerTextView.setText("What is the square root of 100?");
//        answerOne.setText("10");
//        answerTwo.setText("100");

        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("Jan 12", "Seeded Event Value");
        editor.commit();

        buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Go to a QuizFragment
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_fragment_container, EditFragment.newInstance(buttonOne.getText().toString()))
                        .addToBackStack(null)
                        .commit();
            }
        });



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
