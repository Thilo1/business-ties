package com.fuehrmann.executivesuit;

import java.util.ArrayList;
import java.util.List;

import com.fuehrmann.executivesuit.QuestionParser.GameScreen;
import com.fuehrmann.executivesuit.QuestionParser.Question;

public class QuizMaster {

	public static final String CONTINUE_BUTTON = "continue";
	public static final String EMPTY_STRING = "";
	public static final String ECONOMY_IS_FAIR = "fair";
	public static final String ECONOMY_IS_SHRINKING = "shrinking";
	public static final String ECONOMY_IS_EXPANDING = "expanding";
	
	public static final int TEN_POINTS = 10;
	public static final int FIVE_POINTS = 5;
	
	public static final int ECONOMY_SHRINKING = -1;
	public static final int ECONOMY_FAIR = 0;
	public static final int ECONOMY_EXPANDING = 1;
	
	public static int SCORE = 0;
	public static int LEVEL_NUMBER = 0;
	public static int GAME_SCREEN_COUNTER = 0;
	public static String PLAYER_NAME = "";
	
	public static boolean IS_GOOD_PLAYER;
	public static int ECONOMY_STATE = 0;
	public static String ECONOMY_STATE_STRING = "";
	
	public static int GOOD_ANSWERS_IN_THIS_LEVEL_COUNTER=0;
	public static int AVG_ANSWERS_IN_THIS_LEVEL_COUNTER=0;
	public static int BAD_ANSWERS_IN_THIS_LEVEL_COUNTER=0;
	
	
	public Job myJob;
	public static boolean JOB_IS_GRANTED=false;
	
	public GameScreen currentGameScreen;
	public String story;

	public List<GameScreen> gameScreenList;
	public List<String> goodMemories;
	public List<String> avgMemories;
	public List<String> badMemories;
	
	public List<Job> jobList;
	public Job jobFromJoblist = null;
	
	
    
	public void update(String question, String answer, String input){
		System.out.println("GAME SCREEN COUNTER " + GAME_SCREEN_COUNTER);
		System.out.println("Parameters in update in QUIZMASTER, answer: " + answer);
		System.out.println("Parameters in update in QUIZMASTER, question:  " + question);
		System.out.println("Parameters in update in QUIZMASTER, input: " + input);
		
		if (question.equals(((Question)gameScreenList.get(0)).getQuestion())) {
			PLAYER_NAME=input;
		}
		
		log();
		
		// ckecken, ob ein Job ausgewählt wurde
		for (int i=0;i<jobList.size();i++){
			if (jobList.get(i).jobDesc.equals(answer)) {
				jobFromJoblist = jobList.get(i); 
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> job: " + jobFromJoblist.jobDesc);
				assessment();
			}
		}
		
		setWetherPlayerIsGoodOrNot();

		// Wenn der continue Button gedrückt wurde die Story reset
		if (answer.equals(CONTINUE_BUTTON) && input.equals(EMPTY_STRING) && !story.equals(EMPTY_STRING)){
			story=EMPTY_STRING;
			
			return;
		}
		
		// Start: die Erste Frage als currentQuestion
		if (question.equals(EMPTY_STRING) && answer.equals(EMPTY_STRING) && input.equals(EMPTY_STRING)){
			currentGameScreen = (Question) gameScreenList.get(0);
			return;
		}
		
		// Wenn eine Antwort gegeben wurde ( und nicht input )
		else if(!(answer.equals(CONTINUE_BUTTON) && input.equals(EMPTY_STRING))){
			evaluateAnswer(question,answer,input);
		}

			// wenn job granted, dann kommen jetzt die Fragen zu diesem Job
		
			if(JOB_IS_GRANTED){
				
				for (int i = 0; i < gameScreenList.size(); i++){
					
					if (gameScreenList.get(i) instanceof Question){
						
						System.out.println("myJob: " + myJob.jobDesc);
						if (((Question)gameScreenList.get(i)).getJob().equals(myJob.jobDesc)){
						
							currentGameScreen = gameScreenList.get(i);
							GAME_SCREEN_COUNTER = i;
							break;
						}						
					}
				}

			JOB_IS_GRANTED=false;
			return;
			
		}
			// WEnn keine Jobauswahl getroffen wurde, dann nächste Frage
			GAME_SCREEN_COUNTER++;
			currentGameScreen = gameScreenList.get(GAME_SCREEN_COUNTER);
	}
	
	private void assessment() {
		
		//jetzt economy state, memeories und score beachten und damit feststellen ob man den job bekommen hat.
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>XXXXXXXXXXXXX>>> assesment");
		int tempScore = SCORE;
		
		if (!IS_GOOD_PLAYER) tempScore -= FIVE_POINTS;
		if (IS_GOOD_PLAYER) tempScore  += FIVE_POINTS;
		
		if (ECONOMY_STATE == ECONOMY_SHRINKING) tempScore -= FIVE_POINTS;
		if (ECONOMY_STATE == ECONOMY_EXPANDING) tempScore += FIVE_POINTS;
	
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> job: " + jobFromJoblist.jobDesc);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> tempScore: " + tempScore);
		if (tempScore >= jobFromJoblist.getMinScore()){
			JOB_IS_GRANTED = true;
			myJob = jobFromJoblist;
			GOOD_ANSWERS_IN_THIS_LEVEL_COUNTER=0;
			AVG_ANSWERS_IN_THIS_LEVEL_COUNTER=0;
			BAD_ANSWERS_IN_THIS_LEVEL_COUNTER=0;
		}
		else{
			JOB_IS_GRANTED = false;
			myJob = null;
		}
	}

	public void log(){
		System.out.println("ECONOMY_STATE:  " + ECONOMY_STATE_STRING);
		System.out.println("PLAYER_STATE: " + IS_GOOD_PLAYER);
		System.out.println("SCORE: " + SCORE);
		System.out.println("JOB IS GRANTED: " + JOB_IS_GRANTED);
	}
	
	public void evaluateAnswer(String question, String answer, String input){
		story = EMPTY_STRING;
		String memory = EMPTY_STRING;
		int rank      = -1;
		
		if(currentGameScreen instanceof Question){
		for (int i = 0; i < ((Question)currentGameScreen).getTypeid(); i++){
			if (((Question)currentGameScreen).getChoices()[i].getCtext().equals(answer) ) {
				memory = ((Question)currentGameScreen).getChoices()[i].getMemory();
				story  = ((Question)currentGameScreen).getChoices()[i].getStory();
				rank   = ((Question)currentGameScreen).getChoices()[i].getRank();
			}
		}
		}
		
		if (rank==Question.GOOD) {
			if(!memory.equals(EMPTY_STRING)) {
				goodMemories.add(memory);
			}
			SCORE += TEN_POINTS;
			GOOD_ANSWERS_IN_THIS_LEVEL_COUNTER++;
		}
		
		if (rank==Question.AVG ) {
			if(!memory.equals(EMPTY_STRING)) {
				avgMemories.add(memory);
			}
			SCORE += FIVE_POINTS;
			AVG_ANSWERS_IN_THIS_LEVEL_COUNTER++;
		}
		
		if (rank==Question.BAD ) {
		 if(!memory.equals(EMPTY_STRING)) {
			 badMemories.add(memory);
		 }
			SCORE -= TEN_POINTS;
			BAD_ANSWERS_IN_THIS_LEVEL_COUNTER++;
		}
		
	}
	

	public QuizMaster(List<GameScreen> questionsList){
		gameScreenList = questionsList;
		goodMemories = new ArrayList<String>();
		avgMemories = new ArrayList<String>();
		badMemories = new ArrayList<String>();
		story = EMPTY_STRING;
		jobList = new ArrayList<Job>();
		
		String[] arr = {"1 Week Vacation","Own Desk","Working Telephone", "NCSC  Key-Chain"};
		
		// Level 6 Jobs
		jobList.add(new Job("Chief Executive Officer", 120,6,1000000,arr));
		jobList.add(new Job("Chief Financial Officer", 100,6,500000,arr));
		jobList.add(new Job("Chief Technical Architect", 70,6,200000,arr));
		jobList.add(new Job("Marketing Research Supervisor", 50,6,50000,arr));
		
		// Level 5 Jobs
		jobList.add(new Job("Assistant Product Manager", 50,5,100000,arr));
		jobList.add(new Job("Technical Architect", 50,5,50000,arr));
		jobList.add(new Job("Production Foreman", 50,5,50000,arr));
		jobList.add(new Job("Account Manager", 50,5,50000,arr));
		jobList.add(new Job("Senior Marketing Clerk", 45,5,50000,arr));
		
		// Level 4 Jobs
		jobList.add(new Job("Programmer", 40,4,50000,arr));
		jobList.add(new Job("Enterprise Help Desk - Supervisor", 40,4,50000,arr));
		jobList.add(new Job("Shift Leader - Assembling", 40,4,50000,arr));
		jobList.add(new Job("Analyst", 40,4,50000,arr));
		jobList.add(new Job("Marketing Assistant", 40,4,50000,arr));
		
		// Level 3 Jobs
		jobList.add(new Job("Skilled Assembler", 30,3,50000,arr));
		jobList.add(new Job("Controlling Trainee", 30,3,50000,arr));
		jobList.add(new Job("Engineering Trainee", 30,3,50000,arr));
		jobList.add(new Job("Enterprise Help Desk - Agent", 30,3,50000,arr));
		
		// Level 2 jobs
		jobList.add(new Job("General Helper - Office", -30,2,50000,arr));
		jobList.add(new Job("Programming Trainee", -30,2,50000,arr));
		jobList.add(new Job("Assembler Trainee ", -30,2,50000,arr));
		jobList.add(new Job("Sales Trainee", -100,2,50000,arr));
		
		// Level 1 jobs
		
		jobList.add(new Job("Maintenance Personnel", -100,1,50000,arr));
		jobList.add(new Job("General Helper - Mailroom", -100,1,50000,arr));
	}

	public void start(){
		update(EMPTY_STRING,EMPTY_STRING,EMPTY_STRING);
	}
	
	public void calcEconomy(){
		double eco = Math.random();
		if (eco < 0.3333) 				   {
			ECONOMY_STATE = ECONOMY_SHRINKING;
			ECONOMY_STATE_STRING = ECONOMY_IS_SHRINKING;
		}
		if (0.3333 <= eco && eco < 0.6666) {
			ECONOMY_STATE = ECONOMY_FAIR;
			ECONOMY_STATE_STRING = ECONOMY_IS_FAIR;
		}
		if (eco >= 0.6666) {
			ECONOMY_STATE = ECONOMY_EXPANDING;
			ECONOMY_STATE_STRING = ECONOMY_IS_EXPANDING;
		}
	}

	private void setWetherPlayerIsGoodOrNot(){
		if (goodMemories.size() > badMemories.size() ){
			IS_GOOD_PLAYER = true;
		}
		else IS_GOOD_PLAYER = false;
	}
	public GameScreen getCurrentGameScreen() {
		return  currentGameScreen;
	}
	
	public String getStory() {
		return story;
	}
	
	public Job getMyJob() {
		return myJob;
	}

	public Job getJobFromJoblist() {
		return jobFromJoblist;
	}

	public void setJobFromJoblist(Job jobFromJoblist) {
		this.jobFromJoblist = jobFromJoblist;
	}
	
	public ArrayList<Job> getJobOpportunities(){
		
		ArrayList<Job> jobs = new ArrayList<Job>();
		
		for(int i = 0; i < jobList.size(); i++ ){
			
		if (jobList.get(i).minScore <= SCORE + TEN_POINTS){
			
			jobs.add(jobList.get(i));
			System.out.println("added: "+jobList.get(i).getJobDesc());
			if(jobs.size()==6) return jobs;
		}
			
		}
		return jobs;
	}
	
	public String getLevelPerformance(){
		
		if(GOOD_ANSWERS_IN_THIS_LEVEL_COUNTER > BAD_ANSWERS_IN_THIS_LEVEL_COUNTER && GOOD_ANSWERS_IN_THIS_LEVEL_COUNTER > AVG_ANSWERS_IN_THIS_LEVEL_COUNTER) return "good";
		if(AVG_ANSWERS_IN_THIS_LEVEL_COUNTER > BAD_ANSWERS_IN_THIS_LEVEL_COUNTER && AVG_ANSWERS_IN_THIS_LEVEL_COUNTER > GOOD_ANSWERS_IN_THIS_LEVEL_COUNTER) return "average";
		if(BAD_ANSWERS_IN_THIS_LEVEL_COUNTER > GOOD_ANSWERS_IN_THIS_LEVEL_COUNTER && BAD_ANSWERS_IN_THIS_LEVEL_COUNTER > AVG_ANSWERS_IN_THIS_LEVEL_COUNTER) return "bad";
		if(BAD_ANSWERS_IN_THIS_LEVEL_COUNTER == GOOD_ANSWERS_IN_THIS_LEVEL_COUNTER && BAD_ANSWERS_IN_THIS_LEVEL_COUNTER == AVG_ANSWERS_IN_THIS_LEVEL_COUNTER) return "average";
		return QuizMaster.EMPTY_STRING;
	}
	
	public class Job{
		
		private String jobDesc;
		private int    minScore;
		private int    careerLevel;
		private int    salary;
		private String[] perks;
		
		public Job(String desc, int score, int level, int salary, String[] perks){
			this.jobDesc = desc;
			this.minScore = score;
			this.careerLevel = level;
			this.setSalary(salary);
			this.setPerks(perks);
		}
		
		public String getJobDesc() {
			return jobDesc;
		}

		public int getCareerLevel() {
			return careerLevel;
		}
		
		public int getMinScore(){
			return minScore;
		}

		public int getSalary() {
			return salary;
		}

		public void setSalary(int salary) {
			this.salary = salary;
		}

		public String[] getPerks() {
			return perks;
		}

		public void setPerks(String[] perks) {
			this.perks = perks;
		}
	}
	
}