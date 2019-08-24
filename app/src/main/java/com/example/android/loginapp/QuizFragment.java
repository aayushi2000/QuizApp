package com.example.android.loginapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class QuizFragment  extends Fragment {
    Button submit;
    RadioGroup options;
    RadioButton op1, op2, op3, op4 , marked;
    TextView que;
    String correct, choice, quest;
    ArrayList<QuizClass> questions;
    QuizClass quizClass;
    int pos, markID, score;
    FinalScore finalScore;

    public interface FinalScore
    {
        void getFinalScore(int s);
    }
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        Activity activity = (Activity) context;
        finalScore= (FinalScore) activity;

    }
    public QuizFragment()
    {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        final View  rootview = inflater.inflate(R.layout.quiz,container, false);
        pos = 0;
        score =0;
        questions = new ArrayList<QuizClass>();
        quizClass= new QuizClass();

        Bundle bundle = getArguments();
        String category = bundle.getString("category");
        if (category.equals("sports"))
            questions = quizClass.SportsQuiz();
        else if (category.equals("gk"))
            questions = quizClass.GkQuiz();
        else
            questions= quizClass.ScienceQuiz();
        submit=rootview.findViewById(R.id.question_submit_button);
        options = rootview.findViewById(R.id.option_group);
        que = rootview.findViewById(R.id.questions);
        op1 =rootview.findViewById(R.id.option1);
        op2= rootview.findViewById(R.id.option2);
        op3 = rootview.findViewById(R.id.option3);
        op4 = rootview.findViewById(R.id.option4);

        UpdateQue();
        if(pos== questions.size()-1)
        {
            submit.setText("FINISH");
        }
        options.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radiogroup, int i) {
                submit.setEnabled(true);
                marked = rootview.findViewById(i);
                choice = marked.getText().toString();


            }
        });

        submit.setOnClickListener(new View.OnClickListener(){
           @Override
            public  void onClick(View view){
           checkCorrect(choice);
           pos++;
           if(pos == questions.size())
               finalScore.getFinalScore(score);
           else{
               if(pos == questions.size()-1)
               {
                   submit.setText("FINISH");
                   UpdateQue();
                   marked.setChecked(false);
                   submit.setEnabled(false);
               }
           }

           }
        });
    return rootview;}
    void UpdateQue()
    {
        que.setText(questions.get(pos).getQuestion());
        op1.setText(questions.get(pos).getOpt1());
        op2.setText(questions.get(pos).getOpt2());
        op3.setText(questions.get(pos).getOpt3());
        op4.setText(questions.get(pos).getOpt4());

        correct = questions.get(pos).getCorrect();
    }
    void checkCorrect(String tick)
    {
        if(tick.equals(correct))
        {
            score+=25;
        }
    }
}
