package samples.com.w6exam;

/**
 * Created by linke_000 on 22/11/2016.
 */
public class UpdateMapEvent {
    public final String first;
    public final String last;
    public final String url;

    public UpdateMapEvent(String first, String last, String url) {
        this.first = first;
        this.last = last;
        this.url = url;
    }
}
