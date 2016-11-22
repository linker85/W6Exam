package samples.com.w6exam;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    public TextView firstName;

    public TextView lastName;

    public ImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        firstName = (TextView) findViewById(R.id.firstD);
        lastName = (TextView) findViewById(R.id.lastD);
        image = (ImageView) findViewById(R.id.image);

        SharedPreferences sharedPref = null;
        try {
            sharedPref   = getApplicationContext().
                    getSharedPreferences("my_park_meter_pref", Context.MODE_PRIVATE);
            String first = sharedPref.getString("firstName", "0");
            String last  = sharedPref.getString("lastName", "0");
            String url  = sharedPref.getString("url", "0");

            Bundle getBundle = null;
            getBundle    = this.getIntent().getExtras();

            firstName.setText(first);
            lastName.setText(last);
            Picasso.with(getApplicationContext()).load(url).into(image);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
