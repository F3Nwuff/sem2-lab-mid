
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
public class Main implements ActionListener {
    JFrame game = new JFrame("○×ゲーム");
    JPanel gpan1 = new JPanel();
    JPanel gpan2 = new JPanel();
    JPanel gpan3 = new JPanel();
    JLabel title = new JLabel();
    JLabel opt = new JLabel("what would you like to do ?");
    JButton[] gameb = new JButton[9];
    JButton restart = new JButton("restart");
    JButton rematch = new JButton("rematch");
    JButton exit = new JButton("Exit");
    int count = 0;
    boolean p1;
    int m = 0;
    int n = 0;
    char r = ' ';
    JLabel x_score = new JLabel("    X = "+ m);
    JLabel o_score = new JLabel("    O = "+ n);
    JPanel score = new JPanel();
    Main(){
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setSize(300, 480);
        game.getContentPane().setBackground(new Color(245, 245, 245));
        game.setVisible(true);
        game.setLayout(new BorderLayout());

        title.setBackground(new Color(15, 82, 175));
        title.setForeground(new Color(200, 0, 0));
        title.setFont(new Font("Times New Roman", Font.BOLD, 50));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setOpaque(true);

        gpan1.setLayout(new BorderLayout());
        gpan1.setBounds(0, 0, 300, 80);

        gpan2.setLayout(new BorderLayout());
        gpan2.setBounds(0, 0, 300,100 );

        gpan3.setSize(300,300);
        gpan3.setLayout(new GridLayout(3, 3));
        gpan3.setBackground(new Color(245, 245, 245));

        x_score.setFont(new Font("Times New Roman", Font.BOLD, 20));
        o_score.setFont(new Font("Times New Roman", Font.BOLD, 20));
        score.setLayout(new GridLayout(2,1));
        score.add(x_score);
        score.add(o_score);
        gpan2.add(score, BorderLayout.WEST);

        for (int i = 0; i < 9; i++) {
            gameb[i] = new JButton();
            gpan3.add(gameb[i]);
            gameb[i].setFont(new Font("Times New Roman", Font.BOLD, 50));
            gameb[i].setFocusable(false);
            gameb[i].addActionListener(this);
        }

        gpan1.add(title);
        game.add(gpan1, BorderLayout.NORTH);
        game.add(gpan2, BorderLayout.SOUTH);
        game.add(gpan3);
        startgame();
    }
    public void startgame(){
        for(int i = 0;i<9;i++){
            if (i%2 == 0) {
                p1 = true;
                title.setText("X turn");
            } else {
                p1 = false;
                title.setText("O turn");
            }
        }
    }
    public void gameover(String a){
        rematch.addActionListener(this);
        restart.addActionListener(this);
        exit.addActionListener(this);
        opt.setFont(new Font("Times New Roman", Font.BOLD, 10));
        restart.setSize(80,20);
        rematch.setSize(80,20);
        exit.setSize(80,20);
        JPanel exitpanel = new JPanel();
        exitpanel.setLayout(new GridLayout(4,1));
        exitpanel.add(opt);
        exitpanel.add(restart);
        exitpanel.add(rematch);
        exitpanel.add(exit);
        gpan2.add(exitpanel, BorderLayout.EAST);
        exitpanel.setVisible(true);
    }
    public void matchcheck() {
        String[] gamebText = new String[9];
        for (int i = 0; i < gameb.length; i++) {
            gamebText[i] = gameb[i].getText();
        }

        int[][] wincom = {
                {0, 1, 2}, {0, 4, 8}, {0, 3, 6},
                {1, 4, 7}, {2, 4, 6}, {2, 5, 8},
                {3, 4, 5}, {6, 7, 8}
        };

        int[][] com = {
                {0, 1, 2}, {0, 3, 6}, {0, 4, 8},
                {1, 4, 7}, {2, 4, 6}, {2, 5, 8},
                {3, 4, 5}, {6, 7, 8}
        };

        for (int[] w : wincom) {
            String c = gamebText[w[0]];
            if (!c.isEmpty() && c.equals(gamebText[w[1]]) && c.equals(gamebText[w[2]])) {
                if (c.equals("X")) {
                    m += 1;
                    x_score.setText("    X = "+m);
                    o_score.setText("    O = "+n);
                    xwins(w[0], w[1], w[2]);
                } else if (c.equals("O")) {
                    n += 1;
                    x_score.setText("    X = "+m);
                    o_score.setText("    O = "+n);
                    owins(w[0], w[1], w[2]);
                }
                return;
            }
        }

        if (Arrays.stream(gamebText).noneMatch(String::isEmpty)) {
            title.setText("Match Tie");
            gameover("Match Tie");
            return;
        }
    }

    public void xwins(int x1, int x2, int x3) {
        gameb[x1].setBackground(Color.RED);
        gameb[x2].setBackground(Color.RED);
        gameb[x3].setBackground(Color.RED);
        for (int i = 0; i < 9; i++) {
            gameb[i].setEnabled(false);
        }
        title.setText("X wins");
        gameover("X Wins");
    }
    public void owins(int x1, int x2, int x3) {
        gameb[x1].setBackground(Color.RED);
        gameb[x2].setBackground(Color.RED);
        gameb[x3].setBackground(Color.RED);
        for (int i = 0; i < 9; i++) {
            gameb[i].setEnabled(false);
        }
        title.setText("O Wins");
        gameover("O Wins");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == restart) {
            game.dispose();
            new start();
        } else if (e.getSource() == rematch) {
            for (int i = 0; i < gameb.length; i++) {
                gameb[i].setText("");
                gameb[i].setBackground(new JButton().getBackground());
                gameb[i].setEnabled(true);
            }
            count = 0;
            p1 = true;
            title.setText("X turn");
            x_score.setText("    X = "+ m);
            o_score.setText("    O = "+ n);
        }else if (e.getSource() == exit) {
            game.dispose();
        }
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == gameb[i]) {
                if (p1) {
                    if (gameb[i].getText() == "") {
                        gameb[i].setForeground(new Color(255, 0, 0));
                        gameb[i].setText("X");
                        p1 = false;
                        title.setText("O turn");
                        count++;
                        matchcheck();
                    }
                } else {
                    if (gameb[i].getText() == "") {
                        gameb[i].setForeground(new Color(0, 0, 255));
                        gameb[i].setText("O");
                        p1 = true;
                        title.setText("X turn");
                        count++;
                        matchcheck();
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws Exception {
        new Main();
    }
}