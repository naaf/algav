package m1.algav.tools;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Main {

	protected static String REPERTOIRE = "shakespear";

	public static void main(String[] args) throws IOException {
		File repertoire = new File(REPERTOIRE);
		final File[] files = repertoire.listFiles();
		System.out.println(files.length);
		for (File file : files) {
			System.out.println(file.getName());
		}
		Input input = new InputFromFile(files[0]);
		System.out.println(Arrays.toString(input.getText().split("\n")));
		
	}

}
