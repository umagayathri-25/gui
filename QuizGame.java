import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizGame extends JFrame {

    private JLabel welcomeLabel;
    private JButton startButton;
 

    private String[] questions = {"What is the capital of France?", "Which planet is known as the Red Planet?", "What is the largest mammal on Earth?","Which is the national sport of India?","Who is considered the Father of the Nation","Which city is called the Pink City in India?"};
    private String[][] options = {{"Paris", "Rome", "Berlin", "Madrid"}, {"Venus", "Mars", "Jupiter", "Saturn"}, {"Elephant", "Blue Whale", "Giraffe", "Dolphin"},{"Hockey","cricket","volleyball","Badminton"},{"Bhagat Singh","Jawaharlal Nehru","Mahatma Gandhi","Rani Laxmi Bai"},{"Andhra Pradesh","Assam","Chhattisgarh","Jaipur"}};
    private char[] correctAnswers = {'A', 'B', 'B','A','C','D'};

    private int currentQuestionIndex;
    private int score;

    private JLabel questionLabel;
    private JRadioButton optionARadio, optionBRadio, optionCRadio, optionDRadio;
    private JButton submitButton;
   


    public QuizGame() {
        super("Quiz Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());
        
        

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(204,204,255));
        headerPanel.setLayout(new FlowLayout());

        
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(Color.PINK);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding

        welcomeLabel = new JLabel("Welcome to the Quiz Game!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 30));
        centerPanel.add(welcomeLabel, gbc);

        ImageIcon imageIcon = new ImageIcon("C:/Users/Urmila Murali/Desktop/download.jpg");
        JLabel imageLabel = new JLabel(imageIcon);
        gbc.gridy = 1;
        centerPanel.add(imageLabel, gbc);
        setVisible(true);

        
        JPanel quizPanel = new JPanel();
        quizPanel.setBackground(Color.LIGHT_GRAY);
         

        startButton = new JButton("Start Quiz");
        quizPanel.add(startButton);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startQuiz();
            }
        });
        setLayout(null);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(headerPanel, BorderLayout.NORTH);
        getContentPane().add(quizPanel, BorderLayout.SOUTH);
        getContentPane().add(centerPanel, BorderLayout.CENTER);
        getContentPane().add(centerPanel, BorderLayout.CENTER);
        

        setVisible(true);
    }
    

    private void startQuiz() {
        getContentPane().removeAll();
        revalidate();
        repaint();

        currentQuestionIndex = 0;
        score = 0;

        displayNextQuestion();
    }
    private void displayNextQuestion() {
        if (currentQuestionIndex < questions.length) {
           questionLabel = new JLabel(questions[currentQuestionIndex]);
            questionLabel.setFont(new Font("Arial", Font.BOLD, 18));
            questionLabel.setOpaque(true);  // Set this to true to enable background color
            questionLabel.setBackground(new Color(204, 153, 255));
            optionARadio = new JRadioButton("A) " + options[currentQuestionIndex][0]);
            optionBRadio = new JRadioButton("B) " + options[currentQuestionIndex][1]);
            optionCRadio = new JRadioButton("C) " + options[currentQuestionIndex][2]);
            optionDRadio = new JRadioButton("D) " + options[currentQuestionIndex][3]);
            submitButton = new JButton("Submit");
         

            ButtonGroup buttonGroup = new ButtonGroup();
            buttonGroup.add(optionARadio);
            buttonGroup.add(optionBRadio);
            buttonGroup.add(optionCRadio);
            buttonGroup.add(optionDRadio);
            Font radioButtonFont = new Font("Arial", Font.PLAIN, 14); // Adjust the font size as needed
            optionARadio.setFont(radioButtonFont);
            optionBRadio.setFont(radioButtonFont);
            optionCRadio.setFont(radioButtonFont);
            optionDRadio.setFont(radioButtonFont);

            add(questionLabel);
            add(optionARadio);
            add(optionBRadio);
            add(optionCRadio);
            add(optionDRadio);
            setLayout(new GridLayout(6, 1));
            add(submitButton);
            submitButton.setBackground(new Color(204,153,255));
            submitButton.setForeground(new Color(255,0,0));

            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    checkAnswer();
                }
            });

            revalidate();
            repaint();
        } else {
            showResult();
        }
    }

    private void checkAnswer() {
        char userAnswer = getUserAnswer();
        if (userAnswer == correctAnswers[currentQuestionIndex]) {
            score++;
            JOptionPane.showMessageDialog(this, "Correct!");
        } else {
            JOptionPane.showMessageDialog(this, "Incorrect. The correct answer is " + correctAnswers[currentQuestionIndex]);
        }

        currentQuestionIndex++;
        getContentPane().removeAll();
        revalidate();
        repaint();
        displayNextQuestion();
    }

    private char getUserAnswer() {
        if (optionARadio.isSelected()) return 'A';
        else if (optionBRadio.isSelected()) return 'B';
        else if (optionCRadio.isSelected()) return 'C';
        else if (optionDRadio.isSelected()) return 'D';
        else return '\0'; // No option selected
    }

    private void showResult() {
        JOptionPane.showMessageDialog(this, "Quiz completed! Your final score: " + score + "/" + questions.length);
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new QuizGame();
            }
        });
    }
}
