package samples.com.w6exam;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivityTAG_";
    private String json = "{\"results\":[{\"gender\":\"female\",\"name\":{\"title\":\"miss\",\"first\":\"carole\",\"last\":\"lawrence\"},\"location\":{\"street\":\"9637 oak lawn ave\",\"city\":\"melbourne\",\"state\":\"western australia\",\"postcode\":4941},\"email\":\"carole.lawrence@example.com\",\"login\":{\"username\":\"bluelion206\",\"password\":\"mick\",\"salt\":\"7PtL6rQh\",\"md5\":\"73b7ec1c6978461f0cb74c0dc85084ea\",\"sha1\":\"387de18a4fe241317406ecfe0223bed02eb3ea07\",\"sha256\":\"11314cbae0011812f86d5d31d651c43589b40b896b1cb670f79158315603c425\"},\"dob\":\"1970-05-08 17:14:21\",\"registered\":\"2005-10-09 20:58:03\",\"phone\":\"01-3916-6692\",\"cell\":\"0484-726-822\",\"id\":{\"name\":\"TFN\",\"value\":\"184918146\"},\"picture\":{\"large\":\"https://randomuser.me/api/portraits/women/62.jpg\",\"medium\":\"https://randomuser.me/api/portraits/med/women/62.jpg\",\"thumbnail\":\"https://randomuser.me/api/portraits/thumb/women/62.jpg\"},\"nat\":\"AU\"}],\"info\":{\"seed\":\"3f6325e309cf4ef6\",\"results\":1,\"page\":1,\"version\":\"1.1\"}}";
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Gson gson = new Gson();
        //Log.d(TAG, "onCreate: " + json);
        Example example = gson.fromJson(json, Example.class);
        //Log.d(TAG, "onCreate: " + example.toString());
        /*try {
            new NotificationsTask().execute();
        } catch (Exception e) {

        }*/
        Observable<Example> resultGithubObservable = MyServiceRetrofitHelper.
                Factory.createLogin(); // user


        resultGithubObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Example>() {

                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onNext(Example example) {
                        Log.d(TAG, "onNext: " + example.toString());
                        List<Result> results = example.getResults();
                        for (Result r : results) {
                            Log.d(TAG, "onNext: " + r.toString());
                        }
                    }
                });

    }

    public class NotificationsTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            Request request = new Request.Builder()
                    .url("https://randomuser.me/api")
                    .build();
            Response response = null;
            try {
                response = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                Log.d("ANSWER", response.body().string());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

    }

}
