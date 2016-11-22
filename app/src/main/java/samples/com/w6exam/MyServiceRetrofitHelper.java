package samples.com.w6exam;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by linke_000 on 22/11/2016.
 */

public class MyServiceRetrofitHelper {
    public static final String BASE_URL = "https://randomuser.me";

    public static class Factory {
        public static Retrofit create() {
            return new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .build();
        }

        public static Observable<Example> createLogin() {
            Retrofit retrofit = create();
            LoginService loginService = retrofit.create(LoginService.class);
            return loginService.getUsers();
        }
    }

    public interface LoginService {
        @GET("/api")
        Observable<Example> getUsers();
    }
}
