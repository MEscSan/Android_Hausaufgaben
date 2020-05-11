package com.example.questionsandanswers

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.settings_activity.*

class MainActivity : AppCompatActivity(){

    lateinit var questions : Array<String>;
    lateinit var answers : Array<String>;

    //Don't forget: method calls of nullable objects require '?'or '!!'(unsafe) symbols
    var questionView : TextView? = null;
    var answerBoolView : TextView? = null;
    var answerEditText : EditText? = null;
    var answerCheckButton : Button? = null;
    var nextButton : Button? = null;
    var counter : Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialise string-array variables with arrays saved in string-resources
        questions = this.resources.getStringArray(R.array.questions);
        answers = this.resources.getStringArray(R.array.answers);

        // Initialise views and show first question
        initViews();

        // Initialise edit-text field
        answerEditText = findViewById(R.id.answer);

        //Initialise buttons and register the listener
        answerCheckButton = this.answerCheck;
        nextButton = this.next;
        //answerCheckButton?.setOnClickListener(this);
        //nextButton?.setOnClickListener(this);
    }

    override fun onResume() {
        super.onResume()
        val sPrefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        val useContenProvider = sPrefs.getBoolean("useCP", false);
    }

    //Extra function to initialise View-Variables
    private fun initViews(){
        questionView = findViewById(R.id.question);
        questionView?.text = this.questions[0];
        answerBoolView = findViewById(R.id.answerBool);
    }

    fun onCheckClick(view: View?)
    {
        when(answerEditText?.text.toString()){
            answers[counter]-> answerBoolView?.text = "Right!";
            "" -> answerBoolView?.text = "You should have known that!";
            else -> answerBoolView?.text = "Nope! Try again (or google it...)"

        }
    }

    fun onNextClick(view: View?)
    {
        if(counter < (questions.size -1))
        {
            counter++;
            questionView?.text =  questions[counter];
            answerBoolView?.text = null;
            answerEditText?.text = null;
        }
        else
        {
            answerBoolView?.text = "That was all, want to try again?"
            counter = 0;
            questionView?.text =  questions[counter];
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_settings){

            return true;
        }
        return super.onOptionsItemSelected(item)
    }

}
