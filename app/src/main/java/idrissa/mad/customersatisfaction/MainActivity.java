package idrissa.mad.customersatisfaction;


import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int butID=0;
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
        PREV_BUTID=0;
        for(int i=0;i<10;i++){
            String btn_id="btn_"+(i+1);
            butID = getResources().getIdentifier(btn_id, "id", getPackageName());
            System.out.println(butID);
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
    }

    public void setColor(View view,int currButton, int prevButton){
            resetPreviousColor(prevButton);
            view.setBackgroundResource( R.drawable.blue_shape);  /** When selected, button changes to blue colour*/
            setPreviousID(currButton);
            Toast.makeText(MainActivity.this,"Rating "+currButton+" was selected.", Toast.LENGTH_SHORT).show();
    }

    public void submitRating(View view){
        int rating=getPreviousID();
        if(rating==0){
            Toast.makeText(MainActivity.this,"Please choose a Rating", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivity.this,"Final Rating "+rating+" was selected.", Toast.LENGTH_SHORT).show();
            resetPreviousColor(rating);
            setPreviousID(0);
        }
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
}
