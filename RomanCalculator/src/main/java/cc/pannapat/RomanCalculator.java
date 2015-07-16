package cc.pannapat;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class RomanCalculator {

	static int[] ARABIC_NUM_LIST = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
	static String[] ROMAN_LETTER_LIST = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };

	public static void main(String[] args) throws Exception {


		String[][] inputList = RomanCalculator.readInput();

		RomanCalculator calculator = new RomanCalculator();

		String[] outputList = new String[inputList.length];
		for (int i = 0; i < inputList.length; i++) {
			outputList[i] = calculator.plus(inputList[i][0], inputList[i][1]);
		}

		RomanCalculator.writeOutput(outputList);

	}

	public String plus(String operand1, String operand2) {
		int arabicSum = romanToArabic(operand1) + romanToArabic(operand2);
		return arabicToRoman(arabicSum);
	}

	private String arabicToRoman(int arabic) {

		String roman = "";
		int numOfLetter = 0;
		int currentArabicNum;
		int length = ARABIC_NUM_LIST.length;

		for (int idx = 0; idx < length; idx++) {
			currentArabicNum = ARABIC_NUM_LIST[idx];
			numOfLetter = arabic / currentArabicNum;
			arabic -= numOfLetter * currentArabicNum;

			while (numOfLetter-- > 0) {
				roman += ROMAN_LETTER_LIST[idx];
			}

			if (arabic == 0) {
				break;
			}
		}

		return roman;

	}

	private int romanToArabic(String roman) {

		int arabic = 0;
		for (int idx = 0, len = roman.length(); idx < len; idx++) {
			char currentRomanLetter = roman.charAt(idx);
			if (len >= 2 && idx < len - 1) {
				int nextVal = letterToNum(roman.charAt(idx + 1));
				int currentVal = letterToNum(roman.charAt(idx));

				if (nextVal > currentVal) {
					arabic += (nextVal - currentVal);
					idx++;
					continue;
				}
			}
			arabic += letterToNum(currentRomanLetter);
		}
		return arabic;

	}

	private int letterToNum(char letter) {

		switch (letter) {
		case 'I':
			return 1;
		case 'V':
			return 5;
		case 'X':
			return 10;
		case 'L':
			return 50;
		case 'C':
			return 100;
		case 'D':
			return 500;
		case 'M':
			return 1000;
		default:
			return -1;
		}

	}

	static private String[][] readInput() throws IOException  {

		// read input's filename from console
		InputStreamReader streamReader = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(streamReader);
		String content;
		
		while(true){
			System.out.print("Please input file name: ");
			String inputFileName = reader.readLine();

			// read input from file
			File inputFile = new File(inputFileName);
			try {
				content = new Scanner(inputFile).useDelimiter("\\Z").next();
				break;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("File cannot be found.");
			}
		}

		// split input with new line character
		String[] rawInputList = content.split("\n");
		String[][] inputList = new String[rawInputList.length][2];
		for (int i = 0; i < rawInputList.length; i++) {
			inputList[i] = rawInputList[i].split("\\+"); // split each line with
															// + sign
		}
		return inputList;
	}

	// write output to file named "output-roman.txt" in the current directory
	private static void writeOutput(String[] outputList) throws FileNotFoundException, UnsupportedEncodingException {
		String outputFileName = "output-roman.txt";
		PrintWriter out = new PrintWriter(outputFileName, "UTF-8");
		for (int i = 0; i < outputList.length; i++) {
			out.println(outputList[i]);
		}
		out.close();
		System.out.println("-----------DONE-----------");
		System.out.println("See the output in the file: \""+outputFileName+"\"");
	}

}
