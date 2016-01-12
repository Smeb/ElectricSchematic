package IO;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.FileInputStream;

/**
 * Created by henrymortimer on 12/01/2016.
 */
public class Reader
{
    private static Reader instance = null;
    protected Reader() {
        // Exists only to defeat instantiation.
    }
    public static Reader getInstance() {
        if(instance == null) {
            instance = new Reader();
        }
        return instance;
    }

    public JSONObject read(String file)
    {
        String jsonTxt;
        FileInputStream inputStream;
        JSONObject input;
        try {
            inputStream = new FileInputStream(file);
            jsonTxt = IOUtils.toString(inputStream);
            input = new JSONObject(jsonTxt);
        }catch(Exception e) {
            System.err.println(e);
            return null;
        }
        return input;
    }
}
