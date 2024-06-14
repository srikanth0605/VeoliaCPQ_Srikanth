package testscenarios;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.Test;
import compareJSON.FindDocumentDifferences;

public class CompareJSONforDifferences extends FindDocumentDifferences  {
		
	@Test
	public void compareJSON() throws IOException  {
		
		String ctrlFile = "src/test/data/JSON_Control/UVLight_control.json";
		String testFile = "src/test/data/JSON_Test/UVLight_test.json";
		writeJSONDifferences(ctrlFile, testFile);
	}

	
}
