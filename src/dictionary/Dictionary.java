package dictionary;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Scanner;

public class Dictionary {
	public static void main(String[] args) {
		System.out.println("sherwain");
		String content = new String();
		File file = new File("C:\\Users\\sherwain\\dictionary.txt");
		LinkedList<String> list = new LinkedList<String>();
		LinkedList<String> tmp = new LinkedList<String>();
		
		String input = "applepiebook";

		try {
			Scanner sc = new Scanner(new FileInputStream(file));
			while (sc.hasNextLine()) {
				content = sc.nextLine();
				list.add(content);
			}
			sc.close();

		}
		catch (FileNotFoundException fnf) {
			fnf.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("\nProgram terminated Safely...");
		}

		
		
		tmp.addAll(list);
		
		LinkedHashSet<String> set = new LinkedHashSet<String>();
		disintegrate(input, input, set, input.length(), input.length(), 3, 1);
		
		tmp.retainAll(set);
		System.out.println(tmp);
		
	}
	
	
	public static LinkedHashSet<String> disintegrate(String text, String origText, LinkedHashSet<String> set, int inputLength, int origLength, int subStrLength, int pos){
		if (text.length() < subStrLength){
			set.add((subStrLength < inputLength) ? origText.substring(0, subStrLength + 1) : origText.substring(0, subStrLength));
			return set;
		}
		if (subStrLength <= text.length() )
			set.add(text.substring(0, subStrLength));
		
		disintegrate(text.substring(pos), origText, set, inputLength, text.substring(1).length(), subStrLength, pos);
		disintegrate(origText.substring(pos), origText, set, inputLength, origText.length(), ++subStrLength,  pos);
		return set;
	}
	
}
