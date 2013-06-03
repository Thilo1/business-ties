package com.fuehrmann.executivesuit;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

public class QuestionParser {

	private static final String ns = null;
	
	public List<GameScreen> parse(InputStream in) throws XmlPullParserException, IOException{
		try {
			XmlPullParser parser = Xml.newPullParser();
			parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
			parser.setInput(in,null);
			parser.nextTag();
			return readFeed(parser);
		} finally {
			in.close();
		}
	}
	
	private List<GameScreen> readFeed(XmlPullParser parser) throws XmlPullParserException, IOException{
		List<GameScreen> entries = new ArrayList<GameScreen>();
		
		parser.require(XmlPullParser.START_TAG, ns, "game");
		
		while (parser.next() != XmlPullParser.END_TAG){
			if (parser.getEventType() != XmlPullParser.START_TAG){
				continue;
			}
			String name = parser.getName();
			// Starts by looking for the question tag
			if (name.equals("question")){
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> question parsed");
				entries.add(readQuestion(parser));
			}
			
			// mod
			
			if (name.equals("JobOptionsScreen")){
				entries.add(readJobOptionsScreen(parser));
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> JobOptionsScreen parsed");
			}
			
			if (name.equals("SingleTextViewScreen")){
				entries.add(readSingleTextViewScreen(parser));
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> SingleTextViewScreen parsed");
			}
			
			if (name.equals("EconomyScreen")){
				entries.add(readEconomyScreen(parser));
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> EconomyScreen parsed");
			}
			
			if (name.equals("PerformanceReviewScreen")){
				entries.add(readPerformanceReviewScreen(parser));
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> PerformanceReviewScreen parsed");
			}
			if (name.equals("CongratulationsScreen")){
				entries.add(readCongratulationsScreen(parser));
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> CongratulationsScreen parsed");
			}
			
			if (name.equals("JobDescriptionScreen")){
				entries.add(readJobDescriptionScreen(parser));
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> JobDescriptionScreen parsed");
			}
			
			if (name.equals("RememberedForScreen")){
				entries.add(readRememberedForScreen(parser));
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> RememberedForScreen parsed");
			}
			
			// ende mod
			
			if(!name.equals("question") && !name.equals("SingleTextViewScreen") && !name.equals("EconomyScreen")
					&& !name.equals("PerformanceReviewScreen") && !name.equals("CongratulationsScreen") 
					&& !name.equals("JobDescriptionScreen")&& !name.equals("RememberedForScreen") && !name.equals("JobOptionsScreen")) {
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>> skip...");
				skip(parser);
			}
		}
		return entries;
	}
	
	private GameScreen readJobOptionsScreen(XmlPullParser parser) throws XmlPullParserException, IOException {
		String textViewText="";
		
		parser.require(XmlPullParser.START_TAG, ns, "JobOptionsScreen");

		while (parser.next() != XmlPullParser.END_TAG) {
			
	        if (parser.getEventType() != XmlPullParser.START_TAG) {
	            continue;
	        }
	        
	        String name = parser.getName();
	        
	        if (name.equals("TextViewText")) {
	          textViewText = readTextViewText(parser);
	            
	        }
	        else {
	            skip(parser);
	        }
	    }
		return new JobOptionsScreen(textViewText);
	}
	
	private GameScreen readRememberedForScreen(XmlPullParser parser) throws XmlPullParserException, IOException {
		String textViewText="";
		
		parser.require(XmlPullParser.START_TAG, ns, "RememberedForScreen");

		while (parser.next() != XmlPullParser.END_TAG) {
			
	        if (parser.getEventType() != XmlPullParser.START_TAG) {
	            continue;
	        }
	        
	        String name = parser.getName();
	        
	        if (name.equals("TextViewText")) {
	          textViewText = readTextViewText(parser);
	            
	        }
	        else {
	            skip(parser);
	        }
	    }
		return new RememberedForScreen(textViewText);
	}
	
	private GameScreen readJobDescriptionScreen(XmlPullParser parser) throws XmlPullParserException, IOException {
String textViewText="";
		
		parser.require(XmlPullParser.START_TAG, ns, "JobDescriptionScreen");

		while (parser.next() != XmlPullParser.END_TAG) {
			
	        if (parser.getEventType() != XmlPullParser.START_TAG) {
	            continue;
	        }
	        
	        String name = parser.getName();
	        
	        if (name.equals("TextViewText")) {
	          textViewText = readTextViewText(parser);
	            
	        }
	        else {
	            skip(parser);
	        }
	    }
		return new JobDescriptionScreen(textViewText);
	}

	private GameScreen readCongratulationsScreen(XmlPullParser parser) throws XmlPullParserException, IOException {
		String textViewText="";
		
		parser.require(XmlPullParser.START_TAG, ns, "CongratulationsScreen");

		while (parser.next() != XmlPullParser.END_TAG) {
			
	        if (parser.getEventType() != XmlPullParser.START_TAG) {
	            continue;
	        }
	        
	        String name = parser.getName();
	        
	        if (name.equals("TextViewText")) {
	          textViewText = readTextViewText(parser);
	            
	        }
	        else {
	            skip(parser);
	        }
	    }
		return new CongratulationsScreen(textViewText);
	}
	private GameScreen readPerformanceReviewScreen(XmlPullParser parser) throws XmlPullParserException, IOException {
		String textViewText="";
		
		parser.require(XmlPullParser.START_TAG, ns, "PerformanceReviewScreen");

		while (parser.next() != XmlPullParser.END_TAG) {
			
	        if (parser.getEventType() != XmlPullParser.START_TAG) {
	            continue;
	        }
	        
	        String name = parser.getName();
	        
	        if (name.equals("TextViewText")) {
	          textViewText = readTextViewText(parser);
	            
	        }
	        else {
	            skip(parser);
	        }
	    }
		return new PerformanceReviewScreen(textViewText);
	}
	
	

	private EconomyScreen readEconomyScreen(XmlPullParser parser) throws XmlPullParserException, IOException {
		String textViewText="";
		
		parser.require(XmlPullParser.START_TAG, ns, "EconomyScreen");

		while (parser.next() != XmlPullParser.END_TAG) {
			
	        if (parser.getEventType() != XmlPullParser.START_TAG) {
	            continue;
	        }
	        
	        String name = parser.getName();
	        
	        if (name.equals("TextViewText")) {
	          textViewText = readTextViewText(parser);
	            
	        }
	        else {
	            skip(parser);
	        }
	    }
		return new EconomyScreen(textViewText);
	}
	
	
	
	private SingleTextViewScreen readSingleTextViewScreen(XmlPullParser parser) throws XmlPullParserException, IOException {
		String textViewText="";
		
		parser.require(XmlPullParser.START_TAG, ns, "SingleTextViewScreen");

		while (parser.next() != XmlPullParser.END_TAG) {
	        if (parser.getEventType() != XmlPullParser.START_TAG) {
	            continue;
	        }
	        
	        String name = parser.getName();
	        
	        if (name.equals("TextViewText")) {
	          textViewText = readTextViewText(parser);
	            
	        }  
	        else {
	            skip(parser);
	        }
	    }
		return new SingleTextViewScreen(textViewText);
	}
	

	// Parses the contents of an entry. If it encounters a title, summary, or link tag, hands them off
	// to their respective "read" methods for processing. Otherwise, skips the tag.
	private Question readQuestion(XmlPullParser parser) throws XmlPullParserException, IOException {
	    parser.require(XmlPullParser.START_TAG, ns, "question");
	    
	    String   question = null;
	    Choice[] choices = new Choice[6];
	    int numberOfChoices = 0;
	    String   hint = null;
	    String job = null;
	    
	    int typeid=-1;
	    int level=-1;
	    int number=-1;
	    
	    while (parser.next() != XmlPullParser.END_TAG) {
	        if (parser.getEventType() != XmlPullParser.START_TAG) {
	            continue;
	        }
	        String name = parser.getName();
	        if (name.equals("qtext")) {
	            question = readQtext(parser);
	            
	        } else if (name.equals("choice")) {
	        	
	        	
	            choices[numberOfChoices] = readChoices(parser);
	            numberOfChoices++;
	            
	            
	        } else if (name.equals("hint")) {
	            hint = readHint(parser);
	        
	        } else if (name.equals("typeid")) {
	            typeid = readTypeid(parser);
	            
	        } else if (name.equals("level")) {
	            level = readLevel(parser);
	            
	        } else if (name.equals("number")) {
	            number = readNumber(parser);
	         
	        }     else if (name.equals("job")) {
		            job = readJob(parser);  
		        
	            
	        } else {
	            skip(parser);
	        }
	    }
	    return new Question(question, choices, hint, typeid, level, number,job);
	}
	
	private int readNumber(XmlPullParser parser) throws IOException, XmlPullParserException {
		parser.require(XmlPullParser.START_TAG, ns, "number");
	    int number = Integer.parseInt(readText(parser));
	    parser.require(XmlPullParser.END_TAG, ns, "number");
	    return number;
	}

	private int readLevel(XmlPullParser parser) throws IOException, XmlPullParserException {
		parser.require(XmlPullParser.START_TAG, ns, "level");
	    int level = Integer.parseInt(readText(parser));
	    parser.require(XmlPullParser.END_TAG, ns, "level");
	    return level;
	}

	private int readTypeid(XmlPullParser parser) throws IOException, XmlPullParserException {
		parser.require(XmlPullParser.START_TAG, ns, "typeid");
	    int typeid = Integer.parseInt(readText(parser));
	    parser.require(XmlPullParser.END_TAG, ns, "typeid");
	    return typeid;
	}
	
	// Processes summary tags in the feed.
		private String readHint(XmlPullParser parser) throws IOException, XmlPullParserException {
		    parser.require(XmlPullParser.START_TAG, ns, "hint");
		    String hint = readText(parser);
		    parser.require(XmlPullParser.END_TAG, ns, "hint");
		    return hint;
		}

		// Processes summary tags in the feed.
		private String readJob(XmlPullParser parser) throws IOException, XmlPullParserException {
		    parser.require(XmlPullParser.START_TAG, ns, "job");
		    String job = readText(parser);
		    parser.require(XmlPullParser.END_TAG, ns, "job");
		    return job;
		}
		
	// Processes qtext tags in the feed.
	private String readQtext(XmlPullParser parser) throws IOException, XmlPullParserException {
	    parser.require(XmlPullParser.START_TAG, ns, "qtext");
	    String qtext = readText(parser);
	    parser.require(XmlPullParser.END_TAG, ns, "qtext");
	    return qtext;
	}
	
	private String readTextViewText(XmlPullParser parser) throws IOException, XmlPullParserException {
	    parser.require(XmlPullParser.START_TAG, ns, "TextViewText");
	    String text = readText(parser);
	    parser.require(XmlPullParser.END_TAG, ns, "TextViewText");
	    return text;
	}
	
	// Processes choice tags in the feed.
	private Choice readChoices(XmlPullParser parser) throws IOException, XmlPullParserException {
		String ctext=null;
		String story=null;
		int rank=-1;
		String memory=null;
		
		parser.require(XmlPullParser.START_TAG, ns, "choice");
		 while (parser.next() != XmlPullParser.END_TAG) {
		        if (parser.getEventType() != XmlPullParser.START_TAG) {
		            continue;
		        }
		        String name = parser.getName();
		        if (name.equals("ctext")) {
		        	parser.require(XmlPullParser.START_TAG, ns, "ctext");
		    	    ctext = readText(parser);
		    	   
		    	    parser.require(XmlPullParser.END_TAG, ns, "ctext");
		        } else if (name.equals("story")) {
		        	parser.require(XmlPullParser.START_TAG, ns, "story");
		    	    story = readText(parser);
		    	    
		    	    parser.require(XmlPullParser.END_TAG, ns, "story");
		        } else if (name.equals("rank")) {
		        	parser.require(XmlPullParser.START_TAG, ns, "rank");
		    	    rank = Integer.parseInt(readText(parser));
		    	   
		    	    parser.require(XmlPullParser.END_TAG, ns, "rank");
		        } else if (name.equals("memory")) {
		        	parser.require(XmlPullParser.START_TAG, ns, "memory");
		    	    memory = readText(parser);
		    	   
		    	    parser.require(XmlPullParser.END_TAG, ns, "memory");
		        } else {
			            skip(parser);
			        }
		        }
	    return new Choice(ctext,story,rank, memory);
	}
	
	
	
	// For the tags title and summary, extracts their text values.
	private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
	    String result = "";
	    if (parser.next() == XmlPullParser.TEXT) {
	        result = parser.getText();
	        parser.nextTag();
	    }
	    return result;
	}
	
	private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
	    if (parser.getEventType() != XmlPullParser.START_TAG) {
	        throw new IllegalStateException();
	    }
	    int depth = 1;
	    while (depth != 0) {
	        switch (parser.next()) {
	        case XmlPullParser.END_TAG:
	            depth--;
	            break;
	        case XmlPullParser.START_TAG:
	            depth++;
	            break;
	        }
	    }
	 }
	
	public abstract class GameScreen{
		
		public static final int TYPE_INPUT = 1;
	    public static final int TYPE_MC2 = 2;
	    public static final int TYPE_MC3 = 3;
	    public static final int TYPE_MC4 = 4;
	    public static final int TYPE_MC5 = 5;
	    public static final int SINGLE_TEXT_VIEW_SCREEN = 6;
	    public static final int ECONOMY_SCREEN = 7;
	    public static final int PERFORMANCE_REVIEW_SCREEN = 8;
	    public static final int CONGRATULATIONS_SCREEN = 9;
	    public static final int JOB_DESCRIPTION_SCREEN = 10;
	    public static final int REMEMBERED_FOR_SCREEN = 11;
	    public static final int JOB_OPTIONS_SCREEN = 12;
	    
	    public static final int GOOD = 1;
	    public static final int AVG  = 2;
	    public static final int BAD  = 3;
		
		protected int typeid;
		protected String textViewText;
		
		public int getTypeid() {
			return typeid;
		}
		
		public String getTextViewText(){
			return textViewText;
		}
		
	}

	
	
	public class Question extends GameScreen{
		
	    private	String   question;
	    private Choice[] choices;
	    private String   hint;
	    private int      level;
		private int      number;
		private String   job;
		
		
		public String getQuestion() {
			return question;
		}

		public void setQuestion(String question) {
			this.question = question;
		}
		
		public Choice[] getChoices() {
			return choices;
		}

		public void setChoices(Choice[] choices) {
			this.choices = choices;
		}
		
		public String getHint() {
			return hint;
		}

		public void setHint(String hint) {
			this.hint = hint;
		}
		
		public int getLevel() {
			return level;
	    }

		public int getNumber() {
			return number;
		}

		public String getJob() {
			return job;
		}

		public void setJob(String job) {
			this.job = job;
		}

		public Question(String question, Choice[] choices, String hint, int typeid, int level, int number, String job){
			this.question = question;
			this.choices  = choices;
			this.hint = hint;
			this.typeid = typeid;
			this.level = level;
			this.number = number;
			this.job = job;
		}
	}
	
	public class Choice{
		 	private	String   ctext;
		    private String   story;
		    private int   	 rank;
		    private String   memory;
		    
		    public Choice(String ctext, String story, int rank, String memory){
		    	this.ctext  = ctext;
		    	this.story  = story;
		    	this.rank   = rank;
		    	this.memory = memory;
		    }

			public String getCtext() {
				return ctext;
			}

			public String getStory() {
				return story;
			}

			public int getRank() {
				return rank;
			}

			public String getMemory() {
				return memory;
			}
		    
	}
	
	public class SingleTextViewScreen extends GameScreen{

		public SingleTextViewScreen(String tvt){
			typeid = SINGLE_TEXT_VIEW_SCREEN;
			textViewText = tvt;
		}
		
	}
	
	public class JobOptionsScreen extends GameScreen{

		public JobOptionsScreen(String tvt){
			typeid = JOB_OPTIONS_SCREEN;
			textViewText = tvt;
		}
		
	}
	
	public class EconomyScreen extends GameScreen{
		
		public EconomyScreen(String tvt){
			typeid = ECONOMY_SCREEN;
			textViewText = tvt;
		}
		
	}
	
	public class PerformanceReviewScreen extends GameScreen{

		public PerformanceReviewScreen(String tvt){
			typeid = PERFORMANCE_REVIEW_SCREEN;
			textViewText = tvt;
		}
		
	}
	
    public class CongratulationsScreen extends GameScreen{
		
		public CongratulationsScreen(String tvt){
			typeid = CONGRATULATIONS_SCREEN;
			textViewText = tvt;
		}
		
	}
	
	public class JobDescriptionScreen extends GameScreen{
		
		public JobDescriptionScreen(String tvt){
			typeid = JOB_DESCRIPTION_SCREEN;
			textViewText = tvt;
		}	
	}
	
    public class RememberedForScreen extends GameScreen{
		
		public RememberedForScreen(String tvt){
			typeid = REMEMBERED_FOR_SCREEN;
			textViewText = tvt;
		}	
	}
}
