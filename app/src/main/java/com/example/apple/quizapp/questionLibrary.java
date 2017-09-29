package com.example.apple.quizapp;

/**
 * Created by vinit on 27/09/17.
 */

public class questionLibrary {

    private String mQuestions[] = {
            "Who invented the BALLPOINT PEN?",
            "The hot and cold deserts together occupy nearly ____ land area of the world.",
            "Which of the following is not a hill station in India?",
            "Which scientist discovered the radioactive element radium?",
            "Earth is protected from Ultra voilet radiation by ?",
            "The Himalayan mountain system belongs to which of the following?",
            "What Galileo invented?",
            "The latitude of a place expresses its angular position relative to the plane of",
            "Which IIT institutes have developed biosensor to detect kidney disorders?",
            "Who invented Jet Engine?",
            "Nuclear sizes are expressed in a unit named",
            "Rectifiers are used to convert",
            "The intersecting lines drawn on maps and globes are"
    };

    private String mChoices[][] = {
            {"Biro Brothers", "Waterman Brothers", "Bicc Brothers", "Write Brothers"},
            {"1/2","1/4th","1/3rd","3/4th"},
            {"Shimla", "Chandigarh", "Darjeeling", "Bangalore"},
            {"Isaac Newton", "Albert Einstein", "Benjamin Franklin", "Marie Curie"},
            {"", "", "", ""},
            {"Volcanic mountains", "Residual mountains", "Block mountains","Fold mountains"},
            {"Barometer", "Pendulum clock", "Microscope", "Thermometer"},
            {"axis of earth", "north pole", "south pole", "equator"},
            {"IIT Bombay and IIT Indore", "IIT Kanpur and IIT Bombay", "IIT Indore and IIT Delhi", "IIT Delhi and IIT Kanpur"},
            {"Sir Frank Whittle", "Gottlieb Daimler", "Roger Bacon", "Lewis E. Waterman"},
            {"Fermi", "angstrom", "newton", "tesla"},
            {"Direct current to Alternating current", "Alternating current to Direct current", "high voltage to low voltage", "low voltage to high voltage"},
            {"latitudes", "longitudes", "geographic grids", "None of the above"}
    };

    private String mCorrectAnswers[] = {
            "Biro Brothers",
            "1/3rd",
            "",
            "Marie Curie",
            "ozone",
            "Fold mountains",
            "Thermometer",
            "equator",
            "IIT Bombay and IIT Indore",
            "Sir Frank Whittle",
            "Fermi",
            "Alternating current to Direct current",
            "geographic grids"
    };

    public String getQuestions(int a) {
        String questions = mQuestions[a];
        return questions;
    };

    public String getChoice1(int a) {
        String choice = mChoices[a][0];
        return choice;
    };

    public String getChoice2(int a) {
        String choice = mChoices[a][1];
        return choice;
    };

    public String getChoice3(int a) {
        String choice = mChoices[a][2];
        return choice;
    };

    public String getChoice4(int a) {
        String choice = mChoices[a][3];
        return choice;
    };

    public String getCorrectAnswer(int a) {
        String answer = mCorrectAnswers[a];
        return answer;
    }

    public int getQuestionLength(){
        return mQuestions.length;
    }


}
