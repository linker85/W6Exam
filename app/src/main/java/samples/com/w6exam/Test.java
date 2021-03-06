package samples.com.w6exam;

import com.google.gson.Gson;

/**
 * Created by linke_000 on 22/11/2016.
 */

public class Test {
    private static String json = "{\"results\":[{\"gender\":\"female\",\"name\":{\"title\":\"miss\",\"first\":\"carole\",\"last\":\"lawrence\"},\"location\":{\"street\":\"9637 oak lawn ave\",\"city\":\"melbourne\",\"state\":\"western australia\",\"postcode\":4941},\"email\":\"carole.lawrence@example.com\",\"login\":{\"username\":\"bluelion206\",\"password\":\"mick\",\"salt\":\"7PtL6rQh\",\"md5\":\"73b7ec1c6978461f0cb74c0dc85084ea\",\"sha1\":\"387de18a4fe241317406ecfe0223bed02eb3ea07\",\"sha256\":\"11314cbae0011812f86d5d31d651c43589b40b896b1cb670f79158315603c425\"},\"dob\":\"1970-05-08 17:14:21\",\"registered\":\"2005-10-09 20:58:03\",\"phone\":\"01-3916-6692\",\"cell\":\"0484-726-822\",\"id\":{\"name\":\"TFN\",\"value\":\"184918146\"},\"picture\":{\"large\":\"https://randomuser.me/api/portraits/women/62.jpg\",\"medium\":\"https://randomuser.me/api/portraits/med/women/62.jpg\",\"thumbnail\":\"https://randomuser.me/api/portraits/thumb/women/62.jpg\"},\"nat\":\"AU\"}],\"info\":{\"seed\":\"3f6325e309cf4ef6\",\"results\":1,\"page\":1,\"version\":\"1.1\"}}";

    public static void main (String args[]) {
        Gson gson = new Gson();
        Example example = gson.fromJson(json, Example.class);
        System.out.println(example.toString());
    }
}
