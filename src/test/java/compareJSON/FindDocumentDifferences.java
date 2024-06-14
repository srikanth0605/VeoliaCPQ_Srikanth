package compareJSON;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class FindDocumentDifferences {
	
	public void writeJSONDifferences(String ctrlFile, String testFile) throws IOException {
		String diffFile = "src/test/data/JSON_Differences/UVLight_difference.json";
		JSONObject ctrlJSON = readControlJSON(ctrlFile);
		JSONObject testJSON = readTestJSON(testFile);
		JSONObject differences = compareJSONObjects(ctrlJSON, testJSON);
		
		// Write differences to a file
        try (FileWriter file = new FileWriter(diffFile)) {
            file.write(differences.toString(4)); // Pretty print with an indent of 4 spaces
        }
	}
	
	private JSONObject readControlJSON(String ctrlFile) {
		System.out.println("This is Control JSON");
		//String ctrlFile = "/veolia.cpq/src/main/resources/JSON_Control/UVLight_control.json";
		JSONObject ctrlJSON = null;
		//Read JSON file
		try {
			ctrlJSON = new JSONObject(new JSONTokener(new FileReader(ctrlFile)));
		}catch (IOException | JSONException e) {
            e.printStackTrace();
        }
		return ctrlJSON;
	}
	
	private JSONObject readTestJSON(String testFile) {
		
		System.out.println("This is Test JSON");
		//String testFile = "/veolia.cpq/src/main/resources/JSON_Test/UVLight_test.json";
		JSONObject testJSON = null;
		//Read JSON file
		try {
			testJSON = new JSONObject(new JSONTokener(new FileReader(testFile)));
		}catch (IOException | JSONException e) {
            e.printStackTrace();
        }
		return testJSON;
	}
	
	private static JSONObject compareJSONObjects(JSONObject json1, JSONObject json2) {
        JSONObject differences = new JSONObject();

        try {
            for (String key : JSONObject.getNames(json1)) {
                if (!json2.has(key)) {
                    differences.put(key, "Missing in JSON 2");
                } else {
                    Object value1 = json1.get(key);
                    Object value2 = json2.get(key);

                    if (!value1.equals(value2)) {
                        differences.put(key, "Different values: " + value1 + " / " + value2);
                    }
                }
            }

            for (String key : JSONObject.getNames(json2)) {
                if (!json1.has(key)) {
                    differences.put(key, "Missing in JSON 1");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return differences;
    }

}
