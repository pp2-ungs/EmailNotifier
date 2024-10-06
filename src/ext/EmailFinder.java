package ext;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EmailFinder {
    
    private static final String path = System.getProperty("user.home") + "/.TASkOcupado/Resources/Email.json";
    
    public static Map<String, String> getEmailMap() throws IOException {
        Gson gson = new Gson();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            return gson.fromJson(reader, HashMap.class);
        }
    }
    
}
