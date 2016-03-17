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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;


/**
 * Created by aaron on 3/12/16.
 */
public class EditFragment extends Fragment{

    public EditText add;
    public EditText delete;
    public TextView header;
    public TextView list;
    public static String ARG = "arg";
    public String day;

    public static EditFragment newInstance(String date){
        EditFragment fragment = new EditFragment();
        Bundle args = new Bundle();
        args.putString(ARG, date);
        fragment.setArguments(args);
        return fragment;
    }

    public EditFragment(){}

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            day = getArguments().getString(ARG);
//            total_questions = getArguments().getInt(ARG_2);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = null;
        view = inflater.inflate(R.layout.fragment_edit, container, false);

        // instantiate widgets here
        header = (TextView) view.findViewById(R.id.date);
        header.setText(day + "\nEvents");
        list = (TextView) view.findViewById(R.id.list);
        String str = getEvents(day);
        list.setText(str);
        add = (EditText) view.findViewById(R.id.add);
        delete = (EditText) view.findViewById(R.id.delete);
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

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeData(add.getText().toString());
                // Go to a QuizFragment
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_fragment_container, EditFragment.newInstance(day))
                        .addToBackStack(null)
                        .commit();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteLineNumber(delete.getText().toString());
                // Go to a QuizFragment
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_fragment_container, EditFragment.newInstance(day))
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    public void deleteLineNumber(String lineNum){
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        String contents = sharedPref.getString(day, "No events");
        SharedPreferences.Editor editor = sharedPref.edit();
        Scanner scnr = new Scanner(contents);
        String data = "";
        int i = 0;
        while(true){
            i++;
            System.out.println(lineNum + "|");
            if (i != Integer.parseInt(lineNum.substring(0, lineNum.length() - 22))){
                try {
                    data += scnr.nextLine();
                } catch(Exception e){break;}
            }
        }
        editor.putString(day, data);
        editor.commit();
    }

    public String getEvents(String date){

        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        String contents = sharedPref.getString(date, "No events");
        String data = "";
        String line = "";

        Scanner scanner = new Scanner(contents);
        while(true) {
            try {
                line = scanner.nextLine();
                System.out.println("Line read");
            }
            catch(Exception e){
                System.out.println(e);
                break;
            }
            if (line.length() > date.length() && line.substring(0, date.length() - 1).equals(date)) {
                // add line to date
                data += "*" + line.substring(date.length(), line.length() - 1) + "\n";
            }
        }
        return contents;
    }

    public void writeData(String data){
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        String contents = sharedPref.getString(day, "");
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(day, contents + "\n" + data.substring(0, data.length() - 13));
        editor.commit();

//        File file = new File("events.txt");
//        try {
//            FileOutputStream stream = new FileOutputStream(file);
//            System.out.println("Failed creating FileOutputStream");
//            stream.write("text-to-write".getBytes());
//            stream.close();
//            System.out.println("Success!!!!!!!!!!!!!!!!!!!!!!!!!");
//        }
//        catch(Exception e){
//            System.out.println(e);
//        }
    }

}
