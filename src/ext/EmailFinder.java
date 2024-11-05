package ext;

import com.google.gson.Gson;
import core.Settings;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EmailFinder {

    public static Map<String, String> getEmailMap() throws IOException {
        Gson gson = new Gson();

        try (BufferedReader reader = new BufferedReader(new FileReader(Settings.RESOURCES + "Email.json"))) {
            return gson.fromJson(reader, HashMap.class);
        }
    }

}
