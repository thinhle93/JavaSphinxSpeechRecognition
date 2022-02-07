package sphinx;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;

import java.awt.AWTException;
import java.awt.Robot;

public class TranscriverDemo {
	
	public static HashMap<String, Integer> numWords = new HashMap<String, Integer>() {
		{
			put("one", 1);
			put("two", 2);
			put("three", 3);
			put("four", 4);
			put("five", 5);
			put("six", 6);
			put("seven", 7);
			put("eight", 8);
			put("nine", 9);
			put("ten", 10);
			
		}
	};
	
	public static void main(String[] args) throws IOException {
		
		
		Configuration config = new Configuration();
		
		config.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
		config.setDictionaryPath("src\\main\\resources\\pronundict.dic");
		config.setLanguageModelPath("src\\main\\resources\\languageModel.lm");
		Robot r = null;
		try {
			r = new Robot();
		} catch (AWTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			LiveSpeechRecognizer speech = new LiveSpeechRecognizer(config);
			speech.startRecognition(true);
			
			SpeechResult speechResult = null;
			
			while ((speechResult = speech.getResult()) != null) {
				String voiceCommand = speechResult.getHypothesis().toLowerCase();
				System.out.println("Voice Command is " + voiceCommand);
				
				if (voiceCommand.equalsIgnoreCase("Open fire fox")) {
					Runtime.getRuntime().exec("cmd.exe /c start firefox www.google.com");
				} else if (voiceCommand.equalsIgnoreCase("Close fire fox")) {
					Runtime.getRuntime().exec("cmd.exe /c TASKKILL /IM firefox.exe");
				}
				else if (voiceCommand.equalsIgnoreCase("say hi")) {
					System.out.println("Hi this is the speech recognizer");
				}
				else if (voiceCommand.equalsIgnoreCase("go to sleep")) {
					System.out.println("Goodnight");
				}
				else if (voiceCommand.contains("scroll down")) {
					System.out.println("scrolling down doooood");
					String[] nums = voiceCommand.split(" ");
					int num = 0;
					for (int i = 0; i < nums.length; i++) {
						if (numWords.containsKey(nums[i])) {
							num += numWords.get(nums[i]);
						}
					}
					
					r.mouseWheel(num);
				}
				else if (voiceCommand.contains("scroll up")) {
//					Runtime rt = Runtime.getRuntime();
					String[] nums = voiceCommand.split(" ");
					int num = 0;
					for (int i = 0; i < nums.length; i++) {
						if (numWords.containsKey(nums[i])) {
							num += numWords.get(nums[i]);
						}
					}
					
					r.mouseWheel(-num);
				}
				
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
