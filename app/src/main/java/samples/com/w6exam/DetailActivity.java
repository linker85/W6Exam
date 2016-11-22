package samples.com.w6exam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.firstD)
    public TextView firstName;
    @BindView(R.id.lastD)
    public TextView lastName;
    @BindView(R.id.image)
    public ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle getBundle = null;
        getBundle    = this.getIntent().getExtras();
        String first = getBundle.getString("firstName");
        firstName.setText(first);
        String last  = getBundle.getString("lastName");
        lastName.setText(last);
        String url   = getBundle.getString("url");
        Picasso.with(getApplicationContext()).load(url).into(image);

    }
}
