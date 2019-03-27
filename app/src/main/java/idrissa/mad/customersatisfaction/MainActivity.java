package idrissa.mad.customersatisfaction;


import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int butID=0;
    Button btn_array []=new Button[10];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbarTop = (Toolbar) findViewById(R.id.toolbar_top);
        TextView mTitle = (TextView) toolbarTop.findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbarTop);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        for(int i=0;i<10;i++){
            System.out.println(butID);
            String btn_id="btn_"+(i+1);
            butID = getResources().getIdentifier(btn_id, "id", getPackageName());
            btn_array[i] =  (Button)findViewById(butID);
            btn_array[i].setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    String resourceName = view.getResources().getResourceName(view.getId());
                    int numButton=Integer.parseInt(resourceName.substring(39));

                    /**----------------------*/
                    /** INSTEAD OF LOOPING THROUGH ALL BUTTONS, RETRIEVE THE PREVIOUSLY CLICKED BUTTON*/
                    /**----------------------*/
                    for(int i=0;i<10;i++){
                        if(i!=(numButton-1)){
                            //Toast.makeText(MainActivity.this,"Loop "+i+" was entered.", Toast.LENGTH_SHORT).show();

                            switch (i+1){
                                case 1:
                                    btn_array[0].setBackgroundResource( R.drawable.red_shape);
                                case 2:
                                    btn_array[1].setBackgroundResource( R.drawable.red_shape2);
                                case 3:
                                    btn_array[2].setBackgroundResource( R.drawable.red_shape3);
                                case 4:
                                    btn_array[3].setBackgroundResource( R.drawable.red_shape4);
                                case 5:
                                    btn_array[4].setBackgroundResource( R.drawable.red_shape5);
                                case 6:
                                    btn_array[5].setBackgroundResource( R.drawable.khaki_shape);
                                case 7:
                                    btn_array[6].setBackgroundResource( R.drawable.khaki_shape2);
                                case 8:
                                    btn_array[7].setBackgroundResource( R.drawable.khaki_shape3);
                                case 9:
                                    btn_array[8].setBackgroundResource( R.drawable.green_shape);
                                case 10:
                                    btn_array[9].setBackgroundResource( R.drawable.green_shape2);
                                default:
                                    System.out.println(0);
                            }
                        }
                    }
                    view.setBackgroundResource( R.drawable.blue_shape);
                    Toast.makeText(MainActivity.this,"Rating "+numButton+" was selected.", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
