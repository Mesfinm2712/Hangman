import java.util.Random;
import java.awt.event.*;
import java.awt.*;
import java.applet.Applet;
import java.io.*;

public class hangman extends Applet implements ActionListener
{

    // declarations of buttons
    Button b, b2, sports, disney, music, restart, anotherword;


    //booleans declared
    boolean started = false;
    boolean started1 = true;
    boolean started2 = true;
    boolean catsports = true;
    boolean catdisney = false;
    boolean catmusic = false;
    
    //textfield declared
    TextField guessbox = new TextField ();
    //stringbuffer declared
    StringBuffer newword;
    //string and integer values declared
    String line, word, data, warning;
    int gameOver = 11, wrongAnswers;

    
   //init fucntion for applet
    public void init ()
    {
    
	setLayout (new FlowLayout ());

	//adds folling textfield
	this.guessbox = new TextField ();
	this.add (guessbox);
       
	//adds following buttons
	this.b = new Button ("GUESS");
	this.add (b);
	b.addActionListener (this);
	this.add (b);

	this.sports = new Button ("SPORTS");
	this.add (sports);
	sports.addActionListener (this);

	this.b2 = new Button ("EXIT GAME");
	this.add (b2);
	b2.addActionListener (this);

	this.disney = new Button ("DISNEY");
	this.add (disney);
	disney.addActionListener (this);
	disney.setVisible (true);

	this.music = new Button ("MUSIC");
	this.add (music);
	music.addActionListener (this);
	music.setVisible (true);

	this.restart = new Button ("NEW CATEGORY");
	this.add (restart);
	restart.addActionListener (this);
	restart.setVisible (true);

	this.anotherword = new Button ("ANOTHER WORD");
	this.add (anotherword);
	anotherword.addActionListener (this);
	anotherword.setVisible (true);

	//sets size of applet
	this.setSize (600, 500);
	
	//function called
	initHangman ();
    }


    public void initHangman ()
    {
	try //try catch statement here due to BufferedReader 
	{

	    wrongAnswers = 0; //wrongAnswers is originally 0

	    BufferedReader input; //input is what file to be read
	    if (catsports) //boolean sets up which files will be read based on which buttons are clicked
	    {
		input = new BufferedReader (new FileReader ("sports.txt"));

	    }
	    else
	    {
		if (catmusic)
		{
		    input = new BufferedReader (new FileReader ("musicalartistsbands.txt"));
		}
		else
		{

		    input = new BufferedReader (new FileReader ("disney.txt"));
		}
	    }

	    
	    line = input.readLine ();//variable line is equal to the data on the file

	    String[] word1;
	    // delimiter is being used to seperate each word
	    String delimiter = "[,\\s]";

	    // splits the string
	    word1 = line.split (delimiter);
	    
	    Random pickRandom = new Random ();

	   //this chooses a random number
	    int randomNum = pickRandom.nextInt (word1.length);
	    
	    word = new String (word1 [randomNum]);
	    
	    char output[] = new char [word.length ()];
	    
	    for (int i = 0 ; i < word.length () ; i++)//here is where word is masked
	    {
		output [i] = '.';//masked by "."
	    }
	    String a = new String (output);
	    newword = new StringBuffer (a);
	    guessbox.setText ("");//sets the textbox with no text

	    //resets variables
	    warning = "";
	    data = "";
	    repaint ();
	   
	}

	catch (IOException e)
	{
	}
    }


    public void paint (Graphics g)
    {
	super.paint (g);

	this.anotherword.setLocation (10, 320);//sets button location
	this.anotherword.setSize (110, 50);//sets button size

	this.b.setLocation (250, 350); 
	this.b.setSize (80, 20); 

	this.guessbox.setLocation (275, 300);
	this.guessbox.setSize (30, 20);

	this.sports.setLocation (125, 420);
	this.sports.setSize (110, 50); 

	this.disney.setLocation (245, 420); 
	this.disney.setSize (110, 50); 
	
	this.music.setLocation (365, 420); 
	this.music.setSize (110, 50); 

	this.b2.setLocation (10, 440); 
	this.b2.setSize (110, 50); 

	this.restart.setLocation (10, 380);
	this.restart.setSize (110, 50);
      
	if (!started)
	{
	    //sets buttons to visible or invisible
	    anotherword.setVisible (false);
	    b2.setVisible (false);
	    b.setVisible (false);
	    guessbox.setVisible (false);
	    restart.setVisible (false);

	    g.setColor (Color.BLACK); //sets black background
	    g.fillRect (0, 0, 640, 500); //black screen fills entire screen which is 640 by 500

	    g.setColor (Color.RED);
	    g.fillOval (450, 150, 100, 100);

	    g.setColor (Color.RED);
	    g.drawLine (500, 200, 500, 400);

	    g.setColor (Color.RED);
	    g.drawLine (400, 200, 500, 300);

	    g.setColor (Color.RED);
	    g.drawLine (400, 200, 500, 310);

	    g.setColor (Color.RED);
	    g.drawLine (390, 500, 500, 400);

	    g.setColor (Color.RED);
	    g.drawLine (450, 500, 500, 400);

	    g.setColor (Color.RED);
	    g.drawLine (60, 500, 60, 100);

	    g.setColor (Color.RED);
	    g.drawLine (60, 100, 500, 100);

	    g.setColor (Color.RED);
	    g.drawLine (500, 100, 500, 150);

	    g.setColor (Color.RED);
	    g.drawLine (150, 160, 200, 160);

	    g.setColor (Color.RED);
	    g.drawLine (220, 160, 270, 160);

	    g.setColor (Color.RED);
	    g.drawLine (290, 160, 340, 160);

	    g.setColor (Color.RED);
	    g.drawLine (360, 160, 410, 160);

	    Font font = new Font ("Courier New", 1, 90); //Initializes the font

	    g.setColor (Color.WHITE); //Sets the color of the font
	    g.setFont (font); //Sets the font
	    g.drawString ("HANGMAN", 110, 90); //Outputs the string

	    Font font1 = new Font ("Courier New", 1, 40); 

	    g.setColor (Color.WHITE);
	    g.setFont (font1); 
	    g.drawString ("Choose a Category ", 100, 350); 

	    Font font2 = new Font ("Courier New", 1, 16); 

	    g.setColor (Color.WHITE); 
	    g.setFont (font2); 
	    g.drawString ("Options (Sports, Disney Movies or Musical Artists/Bands)", 30, 400); //Outputs the string


	}
	else
	    {
		//sets buttons visible or invisible
		anotherword.setVisible (true);
		b2.setVisible (true);
		b.setVisible (true);
		guessbox.setVisible (true);
		sports.setVisible (false);
		disney.setVisible (false);
		music.setVisible (false);
		restart.setVisible (true);

		g.setColor (Color.BLACK); //sets black background
		g.fillRect (0, 0, 640, 500); //black screen fills entire screen which is 640 by 500

		if (!started1)
		{
		    Font font = new Font ("Courier New", 1, 90); //Initializes the font
		    g.setColor (Color.WHITE); //Sets the color of the font
		    g.setFont (font); //Sets the font
		    g.drawString ("HANGMAN", 110, 90); //Outputs the string

		    // draw commands

		    if (wrongAnswers > 0)
		    { 
			g.drawLine (390, 450, 500, 450);
		    }
		    if (wrongAnswers > 1)
		    { 
			g.drawLine (425, 450, 425, 350);
		    }
		    if (wrongAnswers > 2)
		    { 
			g.drawLine (425, 350, 475, 350);
		    }
		    if (wrongAnswers > 3)
		    {
			g.drawLine (425, 365, 440, 350);
		    }
		    if (wrongAnswers > 4)
		    { 
			g.drawLine (475, 350, 475, 375);
		    }
		    if (wrongAnswers > 5)
		    { 
			g.drawOval (470, 375, 11, 13);
		    }
		    if (wrongAnswers > 6)
		    {
			g.drawOval (470, 385, 16, 26);
		    }
		    if (wrongAnswers > 7)
		    {
			g.drawLine (460, 385, 470, 390);
		    }
		    if (wrongAnswers > 8)
		    {
			g.drawLine (483, 390, 493, 385);
		    }
		    if (wrongAnswers > 9)
		    { 
			g.drawLine (465, 420, 470, 405);
		    }
		    if (wrongAnswers > 10)
		    {
			g.drawLine (483, 405, 493, 420);
		    }

		    g.drawString (new String (newword), 70, 250);

		    Font font3 = new Font ("Courier New", 1, 20); //Initializes the font
		    g.setColor (Color.WHITE); //Sets the color of the font
		    g.setFont (font3); //Sets the font
		    
		    // warning and data displayed to user
		    g.drawString (warning, 100, 155);

		    g.drawString (data, 250, 475);


		}

	    }
    }


    public void actionPerformed (ActionEvent e)
    {
	if (e.getSource () == anotherword)
	{
	    //if button is clicked following code runs
	    guessbox.setEditable(true);
	    b.setEnabled (true);
	    initHangman ();
	}
	if (e.getSource () == b)
	{
	    //if button is clicked following code runs
	    dataOutput ();
	    guessbox.setText ("");
	    repaint ();
	}
	if (e.getSource () == sports)
	{
	    //if button is clicked following code runs
	    started = true;
	    started1 = false;
	    started2 = false;
	    catsports = true;
	    catdisney = false;
	    catmusic = false;
	    sports.setVisible (false);
	    repaint ();
	}
	if (e.getSource () == disney)
	{
	    //if button is clicked following code runs
	    started = true;
	    started1 = false;
	    started2 = true;
	    catsports = false;
	    catdisney = true;
	    catmusic = false;
	    repaint ();
	    initHangman ();

	}
	if (e.getSource () == music)
	{
	    //if button is clicked following code runs
	    started = true;
	    started1 = false;
	    started2 = true;
	    catmusic = true;
	    catsports = false;
	    catdisney = false;
	    repaint ();
	    initHangman ();
	}
	if (e.getSource () == b2)
	{
	    //if button is clicked it exits applet
	    System.exit (0);
	}
	if (e.getSource () == restart)
	{
	//if button is clicked following code runs
	guessbox.setEditable(true);
	b.setEnabled (true);
	started = false;
	started1 = true;
	started2 = true;
	catsports = true;
	catdisney = false;
	catmusic = false;
	sports.setVisible (true);
	disney.setVisible (true);
	music.setVisible (true);
	repaint ();
	}

    }


    public void dataOutput ()
    {
	//declaration of variables
	String userInput, guess;
	char notLetter;

	//this following code catches if the user enters the same letter twice or puts too much string in textfield
	userInput = guessbox.getText ();
	notLetter = userInput.charAt (0);

	if (!Character.isLetter (notLetter))
	{
	    warning = "Please re-enter a letter."; // checks to see if guess is a letter
	    return;
	}
	if (userInput.length () > 1)
	{
	    warning = "Please enter one letter at a time."; // checks to see if more than one letter is inputted
	    return;
	}


	//this following code checks to see if letter has already been guessed
	guess = new String (newword);
	if (guess.indexOf (userInput) != -1)
	{
	    warning = "Letter has already been guessed"; // checks to see if guess was already inputted before by user
	    return;
	}

	if (word.indexOf (userInput) == -1)
	{
	    warning = "";
	    wrongAnswers++; // adds plus one to wrongAnswers
	    if (wrongAnswers == gameOver)
	    {
		data= "GAME OVER!"; // if wrongAnswers equals to gameOver then it will let the user know that they lost
		warning = ("The correct word is " + word + ".");//tells user what the word is since the game is over
		guessbox.setEditable(false); //disables this function so user can't input anymore
		b.setEnabled (false);//disables this function so user can't click this button
	    }

	    return;
	}


	//this code outputs string when user guesses correctly
	for (int i = 0 ; i < word.length () ; i++)
	{
	    if (word.charAt (i) == notLetter)
	    {
		newword.setCharAt (i, notLetter);
	    }
	}
	guess = new String (newword); // prints out the newword with correct guess


	if (guess.indexOf ('.') == -1)
	{
	    data = "You guessed the word!"; //if the whole word is guessed then user wins
	    guessbox.setEditable(false);//disables this function so user can't input anymore
	    b.setEnabled (false);//disables this function so user can't click this button
	    return;
	}



	warning = ""; // erases message
	repaint (); // repaints
    }
}






