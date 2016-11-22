package samples.com.w6exam;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by linke_000 on 22/11/2016.
 */
@Module
public class MyRetroModule {

    public static final String BASE_URL = "https://randomuser.me/";

    @Provides
    public NotificationsAdapter providesNotificationManager() {
        return new NotificationsAdapter();
    }

    @Provides
    public Retrofit provideRetrofitFactory() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();
    }

    @Provides
    public Observable<Example> provideCreateLogin(Retrofit retrofit) {
        LoginService loginService = retrofit.create(LoginService.class);
        return loginService.getUsers();
    }

    @Provides
    public OkHttpClient provideOkHttpClient() {
        return new OkHttpClient();
    }


    public interface LoginService {
        @GET("api")
        Observable<Example> getUsers();
    }
}