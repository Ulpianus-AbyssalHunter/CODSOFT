//Task 4 - Quiz Application with Timer

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class QuizApplication {
    private JFrame frame;
    private JLabel questionLabel;
    private JRadioButton[] optionButtons;
    private ButtonGroup optionsGroup;
    private JButton nextButton;
    private JLabel timerLabel;
    private Timer timer;
    private int timeRemaining;

    private List<Question> questions;
    private int currentQuestionIndex;
    private int score;
    private List<Boolean> results;

    public QuizApplication() {
        frame = new JFrame("Quiz Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        questionLabel = new JLabel();
        frame.add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));

        optionsGroup = new ButtonGroup();
        optionButtons = new JRadioButton[4];
        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JRadioButton();
            optionsGroup.add(optionButtons[i]);
            optionsPanel.add(optionButtons[i]);
        }
        frame.add(optionsPanel, BorderLayout.CENTER);

        JPanel timerAndButtonPanel = new JPanel();
        timerAndButtonPanel.setLayout(new FlowLayout());

        timerLabel = new JLabel("Time remaining: 10");
        timerAndButtonPanel.add(timerLabel);

        nextButton = new JButton("Next");
        nextButton.addActionListener(new NextButtonListener());
        timerAndButtonPanel.add(nextButton);

        frame.add(timerAndButtonPanel, BorderLayout.SOUTH);

        questions = loadQuestions();
        currentQuestionIndex = 0;
        score = 0;
        results = new ArrayList<>();

        loadNextQuestion();

        frame.setVisible(true);
    }

    private List<Question> loadQuestions() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("What is the capital of France?", new String[]{"Berlin", "Paris", "Madrid", "Rome"}, 1));
        questions.add(new Question("What is 2 + 2?", new String[]{"3", "4", "5", "6"}, 1));
        questions.add(new Question("Which planet is known as the Red Planet?", new String[]{"Earth", "Mars", "Jupiter", "Saturn"}, 1));
        return questions;
    }

    private void loadNextQuestion() {
        if (currentQuestionIndex < questions.size()) {
            Question q = questions.get(currentQuestionIndex);
            questionLabel.setText(q.getQuestion());
            String[] options = q.getOptions();
            for (int i = 0; i < options.length; i++) {
                optionButtons[i].setText(options[i]);
                optionButtons[i].setSelected(false);
            }
            timeRemaining = 10;
            timerLabel.setText("Time remaining: " + timeRemaining);
            timer = new Timer(1000, new TimerListener());
            timer.start();
        } else {
            showResults();
        }
    }

    private class NextButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            timer.stop();
            Question q = questions.get(currentQuestionIndex);
            int selectedOption = -1;
            for (int i = 0; i < optionButtons.length; i++) {
                if (optionButtons[i].isSelected()) {
                    selectedOption = i;
                    break;
                }
            }
            if (selectedOption == q.getCorrectAnswer()) {
                score++;
                results.add(true);
            } else {
                results.add(false);
            }
            currentQuestionIndex++;
            loadNextQuestion();
        }
    }

    private class TimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            timeRemaining--;
            timerLabel.setText("Time remaining: " + timeRemaining);
            if (timeRemaining <= 0) {
                timer.stop();
                nextButton.doClick();
            }
        }
    }

    private void showResults() {
        StringBuilder resultMessage = new StringBuilder();
        resultMessage.append("Your score: ").append(score).append("/").append(questions.size()).append("\n");
        for (int i = 0; i < questions.size(); i++) {
            resultMessage.append("Question ").append(i + 1).append(": ");
            resultMessage.append(results.get(i) ? "Correct\n" : "Incorrect\n");
        }
        JOptionPane.showMessageDialog(frame, resultMessage.toString());
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(QuizApplication::new);
    }
}