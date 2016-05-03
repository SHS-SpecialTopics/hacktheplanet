package android.realmtest.com.realmtest;

import android.app.Application;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;

import java.util.logging.Handler;

/**
 * Created by rayqu on 4/19/2016.
 */

public class EquationList extends AppCompatActivity{

    boolean suspended = false;
    myDate time = new myDate(0);
    double equationVal;

    //Need True Values
    double security = .9;
    double panic = 1;
    int shuffle = 0;
    double networking = -.5;
    double trueMid = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.equation_list);

        Bundle extras = getIntent().getExtras();

        String time_extra = extras.getString("time_view");
        String equation_extra = extras.getString("equation_view");
        String log_exta = extras.getString("logarithmic_view");

        final TextView time_view = (TextView) findViewById(R.id.time_view);
        final EditText equation_view = (EditText) findViewById(R.id.SecurityEdit);
        final EditText logarithmic_view = (EditText) findViewById(R.id.LogGrowthEdit);


        time_view.setText(time_extra);
        equation_view.setText(equation_extra);
        logarithmic_view.setText(log_exta);






    }




}
