package samples.com.w6exam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivityTAG_";
    private String json = "{\"results\":[{\"gender\":\"female\",\"name\":{\"title\":\"miss\",\"first\":\"carole\",\"last\":\"lawrence\"},\"location\":{\"street\":\"9637 oak lawn ave\",\"city\":\"melbourne\",\"state\":\"western australia\",\"postcode\":4941},\"email\":\"carole.lawrence@example.com\",\"login\":{\"username\":\"bluelion206\",\"password\":\"mick\",\"salt\":\"7PtL6rQh\",\"md5\":\"73b7ec1c6978461f0cb74c0dc85084ea\",\"sha1\":\"387de18a4fe241317406ecfe0223bed02eb3ea07\",\"sha256\":\"11314cbae0011812f86d5d31d651c43589b40b896b1cb670f79158315603c425\"},\"dob\":\"1970-05-08 17:14:21\",\"registered\":\"2005-10-09 20:58:03\",\"phone\":\"01-3916-6692\",\"cell\":\"0484-726-822\",\"id\":{\"name\":\"TFN\",\"value\":\"184918146\"},\"picture\":{\"large\":\"https://randomuser.me/api/portraits/women/62.jpg\",\"medium\":\"https://randomuser.me/api/portraits/med/women/62.jpg\",\"thumbnail\":\"https://randomuser.me/api/portraits/thumb/women/62.jpg\"},\"nat\":\"AU\"}],\"info\":{\"seed\":\"3f6325e309cf4ef6\",\"results\":1,\"page\":1,\"version\":\"1.1\"}}";
    OkHttpClient client = new OkHttpClient();
    private EventBus eventBus = EventBus.getDefault();
    @Inject
    Observable<Example> retroModule;

    @BindView(R.id.a_notifications_recycler)
    public RecyclerView notificationRecyclerView;

    List<Result> notificationArrayList;
    @Inject
    NotificationsAdapter notificationAdapter;

    MyRetroModule myRetro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        myRetro = new MyRetroModule();
        DaggerMyComponent.builder().
                myRetroModule(myRetro).
                build().
                inject(this); // instance

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(final UpdateMapEvent event) {
        Log.d(TAG, "onEvent: " + event.first);
    }

    public void doMagic(View view) {
        retroModule
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Example>() {
                    @Override
                    public void onStart() {
                        Log.d(TAG, "onStart: ");
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                    }


                    @Override
                    public void onNext(Example example) {
                        // 1. get a reference to recyclerView

                        // 2. set layoutManger
                        notificationRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        // 3. Get data from database
                        notificationArrayList = example.getResults();
                        // 4. set adapter
                        notificationAdapter.setNotificationsArrayList(notificationArrayList, getApplicationContext());
                        //notificationAdapter = new NotificationsAdapter(notificationArrayList);
                        notificationRecyclerView.setAdapter(notificationAdapter);
                        notificationRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        notificationRecyclerView.setItemAnimator(new DefaultItemAnimator());
                        notificationRecyclerView.addItemDecoration(new SimpleDecorator(getApplicationContext(), LinearLayoutManager.VERTICAL));
                        // 5. notify changes
                        notificationAdapter.notifyDataSetChanged();

                    }
                });
    }

}
