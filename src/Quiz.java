import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Quiz implements ActionListener {
    String[] questions = {
      "Which company created Java?",
      "Which year was Java created?",
      "What was Java originally called?",
      "Who is credited with creating Java?",
            "What was Java made for?"
    };

    //2d Array to hold possible answers
    String[][] options = {
            {"Sun Microsystems", "Starbucks", "Microsoft", "Alphabet"},
            {"1989", "1996", "1972", "1492"},
            {"Apple", "Latte", "Oak", "Koffing"},
            {"Steve Jobs", "Bill Gates", "James Gosling", "Mark Zuckerberg"},
            {"Gaming", "Mobile Devices", "Machine Learning", "Embedded Network Applications"}
    };

    //Array of correct answers
    char[] answers = {
            'A',
            'B',
            'C',
            'C',
            'D'
    };

    char guess;
    char answer;
    int index;
    int correct_guesses = 0;
    int total_questions = questions.length;
    int result;
    int seconds = 10;

    /**
     * Create GUI
     */

    JFrame frame = new JFrame();
    JTextField textfield = new JTextField();
    JTextArea textArea = new JTextArea();
    JButton buttonA = new JButton();
    JButton buttonB = new JButton();
    JButton buttonC = new JButton();
    JButton buttonD = new JButton();
    JLabel answer_labelA = new JLabel();
    JLabel answer_labelB = new JLabel();
    JLabel answer_labelC = new JLabel();
    JLabel answer_labelD = new JLabel();
    JLabel time_label = new JLabel();
    JLabel seconds_left = new JLabel();
    JTextField number_right = new JTextField();
    JTextField percentage = new JTextField();

    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            seconds--;
            seconds_left.setText(String.valueOf(seconds));

            if (seconds <= 0) {
                displayAnswer();
            }
        }
    });
    /**
     * Constructor
     */

    public Quiz() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 650);
        frame.getContentPane().setBackground(new Color(191, 175, 153));
        frame.setLayout(null);
        frame.setResizable(false);

        textfield.setBounds(0, 0, 650, 50);
        textfield.setBackground(new Color(153, 140, 122));
        textfield.setForeground(new Color(20, 20, 20));
        textfield.setFont(new Font("Monospaced", Font.BOLD, 30));
        textfield.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        textfield.setHorizontalAlignment(JTextField.CENTER);
        textfield.setEditable(false);
        //textfield.setText("TESTING");

        textArea.setBounds(0, 50, 650, 50);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(new Color(153, 140, 122));
        textArea.setForeground(new Color(20, 20, 20));
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
        textArea.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        textArea.setEditable(false);
        //textArea.setText("Sample Testing");

        buttonA.setBounds(0, 100, 100, 100);
        buttonA.setBackground(new Color(153, 140, 122));
        buttonA.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        buttonA.setFont(new Font("Helvetica", Font.PLAIN, 35));
        buttonA.setFocusable(false);
        buttonA.addActionListener(this);
        buttonA.setText("A");

        buttonB.setBounds(0, 200, 100, 100);
        buttonB.setBackground(new Color(153, 140, 122));
        buttonB.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        buttonB.setFont(new Font("Helvetica", Font.PLAIN, 35));
        buttonB.setFocusable(false);
        buttonB.addActionListener(this);
        buttonB.setText("B");

        buttonC.setBounds(0, 300, 100, 100);
        buttonC.setBackground(new Color(153, 140, 122));
        buttonC.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        buttonC.setFont(new Font("Helvetica", Font.PLAIN, 35));
        buttonC.setFocusable(false);
        buttonC.addActionListener(this);
        buttonC.setText("C");

        buttonD.setBounds(0, 400, 100, 100);
        buttonD.setBackground(new Color(153, 140, 122));
        buttonD.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        buttonD.setFont(new Font("Helvetica", Font.PLAIN, 35));
        buttonD.setFocusable(false);
        buttonD.addActionListener(this);
        buttonD.setText("D");

        answer_labelA.setBounds(125, 100, 500, 100);
        answer_labelA.setBackground(new Color(153, 140, 122));
        answer_labelA.setForeground(new Color(20, 20, 20));
        answer_labelA.setFont(new Font("Monospaced", Font.PLAIN, 30));
        //answer_labelA.setText("Testing A");

        answer_labelB.setBounds(125, 200, 500, 100);
        answer_labelB.setBackground(new Color(153, 140, 122));
        answer_labelB.setForeground(new Color(20, 20, 20));
        answer_labelB.setFont(new Font("Monospaced", Font.PLAIN, 30));
        //answer_labelB.setText("Testing B");

        answer_labelC.setBounds(125, 300, 500, 100);
        answer_labelC.setBackground(new Color(153, 140, 122));
        answer_labelC.setForeground(new Color(20, 20, 20));
        answer_labelC.setFont(new Font("Monospaced", Font.PLAIN, 30));
        //answer_labelC.setText("Testing C");

        answer_labelD.setBounds(125, 400, 500, 100);
        answer_labelD.setBackground(new Color(153, 140, 122));
        answer_labelD.setForeground(new Color(20, 20, 20));
        answer_labelD.setFont(new Font("Monospaced", Font.PLAIN, 30));
        //answer_labelD.setText("Testing D");

        seconds_left.setBounds(535, 510, 100, 100);
        seconds_left.setBackground(new Color(153, 140, 122));
        seconds_left.setForeground(new Color(20, 20, 20));
        seconds_left.setFont(new Font("Helvetica", Font.BOLD, 40));
        seconds_left.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        seconds_left.setOpaque(true);
        seconds_left.setHorizontalAlignment(JTextField.CENTER);
        seconds_left.setText(String.valueOf(seconds));

        time_label.setBounds(535, 475, 100, 25);
        time_label.setBackground(new Color(153, 140, 122));
        time_label.setForeground(new Color(20, 20, 20));
        time_label.setFont(new Font("Monospaced", Font.PLAIN, 16));
        time_label.setHorizontalAlignment(JTextField.CENTER);
        time_label.setText("Timer");

        number_right.setBounds(225, 225, 200, 100);
        number_right.setBackground(new Color(153, 140, 122));
        number_right.setForeground(new Color(20, 20, 20));
        number_right.setFont(new Font("Monospaced", Font.BOLD, 50));
        number_right.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        number_right.setHorizontalAlignment(JTextField.CENTER);
        number_right.setEditable(false);

        percentage.setBounds(225, 325, 200, 100);
        percentage.setBackground(new Color(153, 140, 122));
        percentage.setForeground(new Color(20, 20, 20));
        percentage.setFont(new Font("Monospaced", Font.BOLD, 50));
        percentage.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        percentage.setHorizontalAlignment(JTextField.CENTER);
        percentage.setEditable(false);

        frame.add(time_label);
        frame.add(seconds_left);
        frame.add(answer_labelA);
        frame.add(answer_labelB);
        frame.add(answer_labelC);
        frame.add(answer_labelD);
        frame.add(buttonA);
        frame.add(buttonB);
        frame.add(buttonC);
        frame.add(buttonD);
        frame.add(textfield);
        frame.add(textArea);
        frame.setVisible(true);

        nextQuestion();

    }

    /**
     * Move to next question
     */
    public void nextQuestion(){
        if(index >= total_questions){
            results();
        } else {
            textfield.setText("Question " + (index+1));
            textArea.setText(questions[index]);
            answer_labelA.setText(options[index][0]);
            answer_labelB.setText(options[index][1]);
            answer_labelC.setText(options[index][2]);
            answer_labelD.setText(options[index][3]);

            timer.start();
        }
    }

    /**
     * Button clicks
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        if (e.getSource()==buttonA){
            answer='A';
            if(answer==answers[index]) {
                correct_guesses++;
            }
        }
        if (e.getSource()==buttonB){
            answer='B';
            if(answer==answers[index]) {
                correct_guesses++;
            }
        }

        if (e.getSource()==buttonC){
            answer='C';
            if(answer==answers[index]) {
                correct_guesses++;
            }
        }
        if (e.getSource()==buttonD){
            answer='D';
            if(answer==answers[index]) {
                correct_guesses++;
            }
        }
        displayAnswer();
    }

    public void displayAnswer(){
        timer.stop();
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        if(answers[index] != 'A'){
            answer_labelA.setForeground(new Color(255,0,0));
        }
        if(answers[index] != 'B'){
            answer_labelB.setForeground(new Color(255,0,0));
        }
        if(answers[index] != 'C'){
            answer_labelC.setForeground(new Color(255,0,0));
        }
        if(answers[index] != 'D'){
            answer_labelD.setForeground(new Color(255,0,0));
        }

        Timer pause = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answer_labelA.setForeground(new Color(20,20,20));
                answer_labelB.setForeground(new Color(20,20,20));
                answer_labelC.setForeground(new Color(20,20,20));
                answer_labelD.setForeground(new Color(20,20,20));

                answer = ' ';
                seconds=10;
                seconds_left.setText(String.valueOf(seconds));
                buttonA.setEnabled(true);
                buttonB.setEnabled(true);
                buttonC.setEnabled(true);
                buttonD.setEnabled(true);

                index++;

                nextQuestion();;
            }
        });
        pause.setRepeats(false);
        pause.start();
    }

    public void results(){

        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        result = (int)((correct_guesses/(double)total_questions)*100);

        textfield.setText("RESULTS");
        textArea.setText("");
        answer_labelA.setText("");
        answer_labelB.setText("");
        answer_labelC.setText("");
        answer_labelD.setText("");

        number_right.setText(correct_guesses+"/"+total_questions);
        percentage.setText(result+"%");

        frame.add(percentage);
        frame.add(number_right);

    }

}//end of program
