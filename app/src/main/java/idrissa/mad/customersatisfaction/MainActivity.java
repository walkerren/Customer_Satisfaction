package idrissa.mad.customersatisfaction;


import android.content.DialogInterface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity implements ListenSocket.MainCallback {
    int butID=0;
    TextView txt_ticket;
    TextView txt_counter;
    TextView txt_service;
    static int PREV_BUTID;
    Button btn_array []=new Button[10];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbarTop = (Toolbar) findViewById(R.id.toolbar_top);
        TextView mTitle = (TextView) toolbarTop.findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbarTop);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        txt_ticket=findViewById(R.id.textView);
        txt_counter=findViewById(R.id.textView2);
        txt_service=findViewById(R.id.textView3);

        PREV_BUTID=0;
        for(int i=0;i<10;i++){
            String btn_id="btn_"+(i+1);
            butID = getResources().getIdentifier(btn_id, "id", getPackageName());
            btn_array[i] =  (Button)findViewById(butID);
            btn_array[i].setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    String resourceName = view.getResources().getResourceName(view.getId());
                    int currButton=Integer.parseInt(resourceName.substring(40));
                    int prevButton=getPreviousID();
                    if(currButton!=prevButton){
                        setColor(view, currButton,prevButton);
                    }
                }
            });
        }

        try {
            createSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setColor(View view,int currButton, int prevButton){
            resetPreviousColor(prevButton);
            view.setBackgroundResource( R.drawable.blue_shape);  /** When selected, button changes to blue colour*/
            setPreviousID(currButton);
    }

    public void submitRating(View view){
        int rating=getPreviousID();
        if(rating==0){
            Toast.makeText(MainActivity.this,"Please choose a Rating", Toast.LENGTH_SHORT).show();
        }else{
            resetPreviousColor(rating);
            showSuccessDialog();
            setPreviousID(0);
        }
    }

    public void createSocket() throws IOException {
        new ListenSocket(this).execute();
    }

    public void showSuccessDialog(){
        ViewGroup viewGroup = findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(this).inflate(R.layout.success_dialog, viewGroup, false);

        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        Button btn_close=dialogView.findViewById(R.id.buttonOk);

        //finally creating the alert dialog and displaying it
        final AlertDialog alertDialog = builder.create();

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    public void resetPreviousColor(int prevButton){
        switch (prevButton) {
            case 1:
                btn_array[0].setBackgroundResource(R.drawable.red_shape);
                break;
            case 2:
                btn_array[1].setBackgroundResource(R.drawable.red_shape2);
                break;
            case 3:
                btn_array[2].setBackgroundResource(R.drawable.red_shape3);
                break;
            case 4:
                btn_array[3].setBackgroundResource(R.drawable.red_shape4);
                break;
            case 5:
                btn_array[4].setBackgroundResource(R.drawable.red_shape5);
                break;
            case 6:
                btn_array[5].setBackgroundResource(R.drawable.khaki_shape);
                break;
            case 7:
                btn_array[6].setBackgroundResource(R.drawable.khaki_shape2);
                break;
            case 8:
                btn_array[7].setBackgroundResource(R.drawable.khaki_shape3);
                break;
            case 9:
                btn_array[8].setBackgroundResource(R.drawable.green_shape);
                break;
            case 10:
                btn_array[9].setBackgroundResource(R.drawable.green_shape2);
                break;
            default:
                //System.out.println("Y");
                break;
        }
    }

    public void setPreviousID(int buttonNum){
        PREV_BUTID=buttonNum;
    }

    public int getPreviousID(){
        return PREV_BUTID;
    }

    @Override
    public void updateTextview(String myString) {
        txt_ticket.setText(myString);
        txt_counter.setText(myString);
        txt_service.setText(myString);
        //Log.i("CONNECTION ACCEPTED","UPDATE TEXTVIEW");
    }
}
