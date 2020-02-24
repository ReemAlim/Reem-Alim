package utilities;

import com.mashape.unirest.http.JsonNode;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;

public class CommonUtils {
    public CommonUtils(){}
     public static JSONObject jsonReader(String jsonFilePath) {
        try {
            FileReader reader = new FileReader(jsonFilePath);
            JSONParser jsonParser = new JSONParser();
            return  (JSONObject) jsonParser.parse(reader);
        } catch (Exception e) {
            System.out.println("Couldn't get the JSON file");
        }
        return null;
    }

    public static void writeIntoJSONFile(String jsonFilePath, JsonNode testDataResponse){

        try{
            JSONObject jObj = new JSONObject();
            jObj.put("User Data",testDataResponse);
            FileWriter fileWriter = new FileWriter(jsonFilePath, false);
            fileWriter.write(jObj.toJSONString());
            fileWriter.flush();
        }
        catch (Exception e){
            System.out.println("Couldnt write into the file");
        }
    }

}
