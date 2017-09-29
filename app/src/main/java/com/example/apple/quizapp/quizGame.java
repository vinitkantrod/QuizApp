package com.example.apple.quizapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.apple.quizapp.R.id.radioGroup;

/**
 * Created by vinit on 27/09/17.
 */

public class quizGame extends AppCompatActivity{

    private questionLibrary mQuestionLibrary = new questionLibrary();
    private TextView mQuestionView;
    private RadioGroup mRadioGroup;
    private TextView progressCount;
    private RadioButton mButtonChoice1;
    private RadioButton mButtonChoice2;
    private RadioButton mButtonChoice3;
    private RadioButton mButtonChoice4;

    private CheckBox mCheckBox1;
    private CheckBox mCheckBox2;
    private CheckBox mCheckBox3;
    private CheckBox mCheckBox4;

    private LinearLayout mquestionLinearLayout1;
    private EditText mEditTextAns;

    private TextView mErrorAlert;

    private Button mSubmitButton;
    private ProgressBar progressBar;
    private int mQuestionLength;
    private String mAnswer;
    private int mScore = 0;
    private int mQuestionNumber = 0;
    private TextView questionNumberView;

    String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_list);
        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("name");

        mQuestionView = (TextView) findViewById(R.id.questionTextView);
        mRadioGroup = (RadioGroup) findViewById(radioGroup);
        mButtonChoice1 = (RadioButton) findViewById(R.id.radio1);
        mButtonChoice2 = (RadioButton) findViewById(R.id.radio2);
        mButtonChoice3 = (RadioButton) findViewById(R.id.radio3);
        mButtonChoice4 = (RadioButton) findViewById(R.id.radio4);

        mCheckBox1 = (CheckBox) findViewById(R.id.checkbox1);
        mCheckBox2 = (CheckBox) findViewById(R.id.checkbox2);
        mCheckBox3 = (CheckBox) findViewById(R.id.checkbox3);
        mCheckBox4 = (CheckBox) findViewById(R.id.checkbox4);

        mErrorAlert = (TextView) findViewById(R.id.alertError);
        mEditTextAns = (EditText) findViewById(R.id.edit_text_ans);
        mquestionLinearLayout1 = (LinearLayout) findViewById(R.id.questionLinearLayout1);

        mSubmitButton = (Button) findViewById(R.id.submitButton);
        mQuestionLength = mQuestionLibrary.getQuestionLength();
        questionNumberView = (TextView) findViewById(R.id.questionNumberView);
        questionNumberView.setText(String.valueOf(mQuestionNumber+1));
        progressCount = (TextView) findViewById(R.id.progessCount);
        progressCount.setText(getProgressBarText(mQuestionNumber, mQuestionLength));
        progressBar = (ProgressBar) findViewById(R.id.progressBarView);
        progressBar.setMax(mQuestionLength);
        progressBar.setProgress(mScore);
        updateQuestion();
    }

    private String getProgressBarText(int MScore, int MQuestionLength) {
        String PROGRESSBARTEXT = " Correct Answer out of ";
        return String.valueOf(MScore) + PROGRESSBARTEXT + String.valueOf(MQuestionLength);
    }

    private String getButtonText(boolean finish) {
        if (finish) {
            return "Finish and Get ScoreCard";
        } else {
            return "Next";
        }
    }

    public void updateQuestion() {

        mRadioGroup.clearCheck();
        if ((mQuestionNumber+1) == 3) {
            mCheckBox1.setVisibility(View.VISIBLE);
            mCheckBox1.setText(mQuestionLibrary.getChoice1(mQuestionNumber));
            mCheckBox2.setVisibility(View.VISIBLE);
            mCheckBox2.setText(mQuestionLibrary.getChoice2(mQuestionNumber));
            mCheckBox3.setVisibility(View.VISIBLE);
            mCheckBox3.setText(mQuestionLibrary.getChoice3(mQuestionNumber));
            mCheckBox4.setVisibility(View.VISIBLE);
            mCheckBox4.setText(mQuestionLibrary.getChoice4(mQuestionNumber));
            mRadioGroup.setVisibility(View.GONE);
        } else if ((mQuestionNumber + 1) == 5) {
            mquestionLinearLayout1.setVisibility(View.VISIBLE);
            mRadioGroup.setVisibility(View.GONE);
        } else {
            mCheckBox1.setVisibility(View.GONE);
            mCheckBox2.setVisibility(View.GONE);
            mCheckBox3.setVisibility(View.GONE);
            mCheckBox4.setVisibility(View.GONE);
            mquestionLinearLayout1.setVisibility(View.GONE);
            mRadioGroup.setVisibility(View.VISIBLE);
            mButtonChoice1.setText(mQuestionLibrary.getChoice1(mQuestionNumber));
            mButtonChoice2.setText(mQuestionLibrary.getChoice2(mQuestionNumber));
            mButtonChoice3.setText(mQuestionLibrary.getChoice3(mQuestionNumber));
            mButtonChoice4.setText(mQuestionLibrary.getChoice4(mQuestionNumber));
        }
        mQuestionView.setText(mQuestionLibrary.getQuestions(mQuestionNumber));
        questionNumberView = (TextView) findViewById(R.id.questionNumberView);
        questionNumberView.setText(String.valueOf(mQuestionNumber+1));
        mAnswer = mQuestionLibrary.getCorrectAnswer(mQuestionNumber);
        mQuestionNumber++;
        mSubmitButton.setText(getButtonText(false));
        if (mQuestionNumber == mQuestionLength) {
            mSubmitButton.setText(getButtonText(true));
        }
        progressCount.setText(getProgressBarText(mScore, mQuestionLength));
        progressBar.setProgress(mScore);
    }

    public void checkAnswer(View view) {
        String ERROR="Please select atleast One checkbox";

        if (mQuestionNumber == 3) {
            boolean check1 = mCheckBox1.isChecked();
            boolean check2 = mCheckBox2.isChecked();
            boolean check3 = mCheckBox3.isChecked();
            boolean check4 = mCheckBox4.isChecked();
            if (!check1 && !check2 && !check3 && !check4) {
                mErrorAlert.setVisibility(View.VISIBLE);
                mErrorAlert.setText(ERROR);
            } else {
                mErrorAlert.setVisibility(View.GONE);
                mScore += (check1 && check3 && !check2 && !check4) ? 1 : 0;
                updateQuestion();
            }
        } else {
            if (mQuestionNumber == 5) {

                String ans = mEditTextAns.getText().toString().toLowerCase().trim();
                if (ans.equals(mAnswer)) {
                    mScore += 1;
                } else {
                    mScore += 0;
                }
                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                updateQuestion();
            } else {
                mErrorAlert.setVisibility(View.GONE);
                mquestionLinearLayout1.setVisibility(View.GONE);
                RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
                if (radioGroup.getCheckedRadioButtonId() != -1) {
                    int id = radioGroup.getCheckedRadioButtonId();
                    View radioButton = radioGroup.findViewById(id);
                    int radioId = radioGroup.indexOfChild(radioButton);
                    RadioButton btn = (RadioButton) radioGroup.getChildAt(radioId);
                    String mGetAnswer = btn.getText().toString();
                    if (mGetAnswer.equals(mAnswer)) {
                        mScore += 1;
                    } else {
                        mScore += 0;
                    }
                    if (mQuestionNumber >= mQuestionLength) {
                        Toast.makeText(this, "Final Score is " + mScore + ". Thanks for taking Quiz", Toast.LENGTH_SHORT).show();
                        String scoreCard;
                        double avgNumber = mQuestionLength / 2.0;
                        if (mScore > avgNumber) {
                            scoreCard = "You have passed the test and scored above average marks.";
                        } else {
                            scoreCard = "You should try to improve this score further.";
                        }
                        String text = "Hi " + name + "\n";
                        text += "Congratulation for Solving Tech StartUp Quiz. You Scored " + mScore + " points out of " + mQuestionLength + "\n";
                        text += scoreCard + "\n\n";
                        text += "All the best and keep spreading Knowledge :-)\n";
                        text += "\n\n";
                        text += "Thanks and Regards,\n";
                        text += "Vinit\n";
                        Intent intent = new Intent(Intent.ACTION_SENDTO);
                        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Congratulation, Quiz App Result for " + name + " is declared");
                        intent.putExtra(Intent.EXTRA_TEXT, text);
                        if (intent.resolveActivity(getPackageManager()) != null) {
                            startActivity(intent);
                        }

                    } else {
                        updateQuestion();
                    }

                }
            }
        }

    }

}
