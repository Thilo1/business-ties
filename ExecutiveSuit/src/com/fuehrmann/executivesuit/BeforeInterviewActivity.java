package com.fuehrmann.executivesuit;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;

import com.fuehrmann.executivesuit.QuestionParser.EconomyScreen;
import com.fuehrmann.executivesuit.QuestionParser.GameScreen;
import com.fuehrmann.executivesuit.QuestionParser.Question;
import com.fuehrmann.executivesuit.QuestionParser.SingleTextViewScreen;
import com.fuehrmann.executivesuit.QuizMaster.Job;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;

public class BeforeInterviewActivity extends Activity {
	
	
	private QuizMaster quizMaster;
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_interview);

        List<GameScreen> gameScreenList = null;
        
        QuestionParser qp = new QuestionParser();
        
        InputStream is = getResources().openRawResource(R.raw.questions);

		try {
			gameScreenList = qp.parse(is);
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		quizMaster = new QuizMaster(gameScreenList);
    }

    @Override
    protected void onStop() {
    	super.onStop();
    	super.onDestroy();
    }
    
	public void init(View view){
		quizMaster.start();
		updateView();
    }
    
   public void answer(View view){
    	
    	int id = view.getId();
    	
    	String answerText   = "";
    	String inputText    = "";
    	String questionText = "";
    	
    	Button btn;
    	EditText editText;
    	TextView textView;
    	
    	switch (id){
    	
    				case R.id.button_continue:
    					
    						btn = (Button) view;
    						
    						answerText = ""+btn.getText();
    						
    						editText = (EditText) findViewById(R.id.text_put_name);
    						
    						textView = (TextView) findViewById(R.id.question_name);
    						
    						if (quizMaster.getCurrentGameScreen() instanceof SingleTextViewScreen){
    							
    							textView = (TextView) findViewById(R.id.question_name);
    							
    						}
    						
    						if (quizMaster.getCurrentGameScreen() instanceof EconomyScreen){
    							
    							textView = (TextView) findViewById(R.id.economy_screen_textView);
    							
    						}
    						
    						if (editText != null) inputText = editText.getText().toString();
    						
    						if (textView != null) questionText = textView.getText().toString();
    						
    						break;
    				
    				case R.id.answer1: 
    		               btn = (Button) view;
    		               answerText = ""+btn.getText();
    		               break;
    				
    				case R.id.answer2: 
 		               	   btn = (Button) view;
 		                   answerText = ""+btn.getText();
 		                   break;
 		            
    				case R.id.answer3: 
 		                   btn = (Button) view;
 		                   answerText = ""+btn.getText();
 		                   break;
 		               
    				case R.id.answer4: 
 		                   btn = (Button) view;
 		                   answerText = ""+btn.getText();
 		                   break;
 		               
    				case R.id.answer5: 
 		                   btn = (Button) view;
 		                   answerText = ""+btn.getText();
 		                   break;

    				case R.id.button_job1:
    						btn = (Button) view;
    						answerText = ""+btn.getText();
    						break;
    						
    				case R.id.button_job2:
						btn = (Button) view;
						answerText = ""+btn.getText();
						break;
						
    				case R.id.button_job3:
						btn = (Button) view;
						answerText = ""+btn.getText();
						break;
					
    				case R.id.button_job4:
						btn = (Button) view;
						answerText = ""+btn.getText();
						break;
						
    				case R.id.button_job5:
						btn = (Button) view;
						answerText = ""+btn.getText();
						break;
    	}

    	quizMaster.update(questionText, answerText, inputText);
    	updateView();
    }
    
    @SuppressLint("CutPasteId")
	@TargetApi(11)
	private void updateView(){
    	
    	String text;
    	TextView the_textView;
    	Button bto1;
    	Button bto2;
    	Button bto3;
    	Button bto4;
    	Button bto5;
    	Button bto6;
    	
    	//Wenn Story nach eine Frage dargestellt werden soll
    	if (!(quizMaster.getStory().equals("")) ){
    		setContentView(R.layout.story_screen);
    		TextView textView = (TextView) findViewById(R.id.question_name);
    		textView.setText(quizMaster.getStory());
    		return;
    	}
    	
    	//Wenn ein Game Screen dran ist
    	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>  GAME_SCREEN: " + quizMaster.getCurrentGameScreen().getTypeid());
		switch (quizMaster.getCurrentGameScreen().getTypeid()){
		
								case GameScreen.TYPE_INPUT:
									setContentView(R.layout.input_type_question);
									TextView textView = (TextView) findViewById(R.id.question_name);
		    						textView.setText(((Question)quizMaster.getCurrentGameScreen()).getQuestion());
		    						EditText editText = (EditText) findViewById(R.id.text_put_name);
									editText.setText(((Question)quizMaster.getCurrentGameScreen()).getHint());
									break;
									
								case GameScreen.TYPE_MC2:
									setContentView(R.layout.mc_question_two);
									break;
									
								case GameScreen.TYPE_MC3:
									setContentView(R.layout.mc_question_three);
									break;
									
								case GameScreen.TYPE_MC4:
									setContentView(R.layout.mc_question_four);
									break;
									
								case GameScreen.TYPE_MC5:
									setContentView(R.layout.mc_question_five);
									the_textView = (TextView) findViewById(R.id.question);
									bto1 = (Button) findViewById(R.id.answer1);
									bto2 = (Button) findViewById(R.id.answer2);
									bto3 = (Button) findViewById(R.id.answer3);
									bto4 = (Button) findViewById(R.id.answer4);
									bto5 = (Button) findViewById(R.id.answer5);
									the_textView.setText(((Question)quizMaster.getCurrentGameScreen()).getQuestion());
									bto1.setText(((Question)quizMaster.getCurrentGameScreen()).getChoices()[0].getCtext());
									bto2.setText(((Question)quizMaster.getCurrentGameScreen()).getChoices()[1].getCtext());
									bto3.setText(((Question)quizMaster.getCurrentGameScreen()).getChoices()[2].getCtext());
									bto4.setText(((Question)quizMaster.getCurrentGameScreen()).getChoices()[3].getCtext());
									bto5.setText(((Question)quizMaster.getCurrentGameScreen()).getChoices()[4].getCtext());
									break;
									
							   case GameScreen.SINGLE_TEXT_VIEW_SCREEN:
								    setContentView(R.layout.story_screen);
								    text =  quizMaster.getCurrentGameScreen().getTextViewText();
								    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> SINGLE TEXT VIEW SCREEN"+text);
								    the_textView = (TextView) findViewById(R.id.question_name);
						    		the_textView.setText(text);
									break;
									
							   case GameScreen.ECONOMY_SCREEN:
								    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ECONOMY SCREEN ");
								    setContentView(R.layout.economy_screen);
								    text = quizMaster.getCurrentGameScreen().getTextViewText();
								    the_textView = (TextView) findViewById(R.id.economy_screen_textView);
						    		the_textView.setText(text);
									break;
									
							   case GameScreen.PERFORMANCE_REVIEW_SCREEN:
								    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Performance Review Screen ");
								    setContentView(R.layout.performance_review);
								    text = quizMaster.getCurrentGameScreen().getTextViewText();
								    ArrayList<Job> jobs = quizMaster.getJobOpportunities();
								    
								    bto1 = (Button) findViewById(R.id.button_job1);
									bto2 = (Button) findViewById(R.id.button_job2);
									bto3 = (Button) findViewById(R.id.button_job3);
									bto4 = (Button) findViewById(R.id.button_job4);
									bto5 = (Button) findViewById(R.id.button_job5);
									bto6 = (Button) findViewById(R.id.button_job6);
								    
									bto1.setText(jobs.get(0).getJobDesc());
									bto2.setText(jobs.get(1).getJobDesc());
									bto3.setText(jobs.get(2).getJobDesc());
									bto4.setText(jobs.get(3).getJobDesc());
									bto5.setText(jobs.get(4).getJobDesc());
									bto6.setText(jobs.get(5).getJobDesc());
									
								    the_textView = (TextView) findViewById(R.id.head);
						    		the_textView.setText(text);
									break;
								
									/* TODO grob der code
							   case GameScreen.REMEMBERED_FOR_SCREEN:
								   	setContentView(R.layout.remembered_for);
								   	ListView listView = (ListView) findViewById(R.id.memories);
									ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.remembered_for,R.id.remembered_for_textview);
									for(int i = 0 ; i < quizMaster.goodMemories.size(); i++){
										adapter.add(quizMaster.goodMemories.get(i));
									}
									if (listView != null){
										
										listView.setAdapter(adapter);
										System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<ADAPTER SET.");
									}
									break;
									*/
    	}
    }
}
