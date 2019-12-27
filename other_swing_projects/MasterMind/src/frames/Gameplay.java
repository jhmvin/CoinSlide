package frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import modules.AIModule;
import modules.Guess;
import modules.GuessSetInfo;

public class Gameplay extends JFrame implements ActionListener {

    private AIModule playmate;
    public Guess ai_code;
    public int slot_1;
    public int slot_2;
    public int slot_3;
    public int slot_4;

    public int color_1;
    public int color_2;
    public int color_3;
    public int color_4;

    //User data
    public ArrayList<String> user_guess_logs = new ArrayList<String>();
    public Guess user_guess;

    JButton cmd = new JButton("Start Game");

    public static void main(String[] args) {

        new Gameplay();

    }
    //ROUNDs----------------------------------
    static int round = 50;
    //ROUNDs----------------------------------

    Gameplay() {

        build_me();

        user_btn_red.addActionListener(this);
        user_btn_green.addActionListener(this);
        user_btn_blue.addActionListener(this);
        user_btn_yellow.addActionListener(this);
        user_btn_black.addActionListener(this);
        user_btn_white.addActionListener(this);
        user_color_edit.addActionListener(this);
        user_color_enter.addActionListener(this);
        cmd.addActionListener(this);
        disable_all();

    }

    public void disable_all() {
        user_btn_red.setEnabled(false);
        user_btn_green.setEnabled(false);
        user_btn_blue.setEnabled(false);
        user_btn_yellow.setEnabled(false);
        user_btn_black.setEnabled(false);
        user_btn_white.setEnabled(false);
        user_color_edit.setEnabled(false);
        user_color_enter.setEnabled(false);

    }

    public void enable_all() {
        user_btn_red.setEnabled(true);
        user_btn_green.setEnabled(true);
        user_btn_blue.setEnabled(true);
        user_btn_yellow.setEnabled(true);
        user_btn_black.setEnabled(true);
        user_btn_white.setEnabled(true);
        user_color_edit.setEnabled(true);
        user_color_enter.setEnabled(true);
    }

    Icon i1, i2, i3, i4;

    public void check_code() {
        if (box_fill == 5) {

            box_fill = 10;

            i1 = user_code_1.getIcon();
            i2 = user_code_2.getIcon();
            i3 = user_code_3.getIcon();
            i4 = user_code_4.getIcon();
            user_code_1.setIcon(isecret_box);
            user_code_2.setIcon(isecret_box);
            user_code_3.setIcon(isecret_box);
            user_code_4.setIcon(isecret_box);
            init_game_req();
            set_msg("Your Turn");
            referee("You're Code Has Been Registered");

        }
    }
    int box_fill = 1;
    int slot_fill = 1;

    Thread ai_th;

    public void ai_colors_reset() {
        this.setTitle("Opponent's Turn");
        ai_color_1.setIcon(ic_brick);
        ai_color_2.setIcon(ic_brick);
        ai_color_3.setIcon(ic_brick);
        ai_color_4.setIcon(ic_brick);
    }

    public void ai_turn() {
        ai_th = new Thread() {
            @Override
            public void run() {
                disable_all();
                ai_colors_reset();
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {
                    System.out.println(e);
                }
                ai_run(count - 1);
                if (count == playmate.generated_guess_set.size()) {
                    disable_all();
                    set_msg("You Lose!");
                    referee_warn("You Lose, Your code has been broken.");
                    user_code_1.setIcon(i1);
                    user_code_2.setIcon(i2);
                    user_code_3.setIcon(i3);
                    user_code_4.setIcon(i4);
                    ai_th.stop();
                }
                if ((round - count) == 0) {
                    disable_all();
                    set_msg("Draw!");
                    referee_warn("DRAW, Failed To Break Both Codes.");
                    user_code_1.setIcon(i1);
                    user_code_2.setIcon(i2);
                    user_code_3.setIcon(i3);
                    user_code_4.setIcon(i4);
                    ai_th.stop();
                }

                set_msg("Your Turn");
                enable_all();
                referee("Opponent's Guess\n" + playmate.gs_info.get(count - 1).toString());
                ai_th.stop();

            }

        };
        ai_th.start();
    }

    public void set_msg(String msg) {
        this.setTitle(msg + " -- [Rounds Left: " + (round - count) + "]");
    }

    public void ai_run(int index) {
        Guess g = playmate.generated_guess_set.get(index);

        for (int x = 0; x < g.toArrayList().size(); x++) {
            int val = Integer.parseInt(g.toArrayList().get(x).toString());

            if (x == 0) {
                if (val == red) {
                    ai_color_1.setIcon(ic_red);
                } else if (val == green) {
                    ai_color_1.setIcon(ic_green);
                } else if (val == blue) {
                    ai_color_1.setIcon(ic_blue);
                } else if (val == yellow) {
                    ai_color_1.setIcon(ic_yellow);
                } else if (val == black) {
                    ai_color_1.setIcon(ic_black);
                } else if (val == white) {
                    ai_color_1.setIcon(ic_white);
                }

            } else if (x == 1) {
                if (val == red) {
                    ai_color_2.setIcon(ic_red);
                } else if (val == green) {
                    ai_color_2.setIcon(ic_green);
                } else if (val == blue) {
                    ai_color_2.setIcon(ic_blue);
                } else if (val == yellow) {
                    ai_color_2.setIcon(ic_yellow);
                } else if (val == black) {
                    ai_color_2.setIcon(ic_black);
                } else if (val == white) {
                    ai_color_2.setIcon(ic_white);
                }

            } else if (x == 2) {
                if (val == red) {
                    ai_color_3.setIcon(ic_red);
                } else if (val == green) {
                    ai_color_3.setIcon(ic_green);
                } else if (val == blue) {
                    ai_color_3.setIcon(ic_blue);
                } else if (val == yellow) {
                    ai_color_3.setIcon(ic_yellow);
                } else if (val == black) {
                    ai_color_3.setIcon(ic_black);
                } else if (val == white) {
                    ai_color_3.setIcon(ic_white);
                }

            } else if (x == 3) {
                if (val == red) {
                    ai_color_4.setIcon(ic_red);
                } else if (val == green) {
                    ai_color_4.setIcon(ic_green);
                } else if (val == blue) {
                    ai_color_4.setIcon(ic_blue);
                } else if (val == yellow) {
                    ai_color_4.setIcon(ic_yellow);
                } else if (val == black) {
                    ai_color_4.setIcon(ic_black);
                } else if (val == white) {
                    ai_color_4.setIcon(ic_white);
                }

            }

        }

    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        if (e.getSource().equals(user_btn_red)) {
            if (box_fill == 10) {
                red_select();

                this.repaint();
                return;
            }

            red_set();
            this.repaint();
            check_code();
        } else if (e.getSource().equals(user_btn_green)) {
            if (box_fill == 10) {
                green_select();

                this.repaint();
                return;
            }

            green_set();
            this.repaint();
            check_code();
        } else if (e.getSource().equals(user_btn_blue)) {
            if (box_fill == 10) {
                blue_select();

                this.repaint();
                return;
            }

            blue_set();
            this.repaint();
            check_code();
        } else if (e.getSource().equals(user_btn_yellow)) {
            if (box_fill == 10) {
                yellow_select();

                this.repaint();
                return;
            }

            yellow_set();
            this.repaint();
            check_code();
        } else if (e.getSource().equals(user_btn_black)) {
            if (box_fill == 10) {
                black_select();

                this.repaint();
                return;
            }

            black_set();
            this.repaint();
            check_code();
        } else if (e.getSource().equals(user_btn_white)) {
            if (box_fill == 10) {
                white_select();

                this.repaint();
                return;
            }

            white_set();
            this.repaint();
            check_code();
        } else if (e.getSource().equals(user_color_edit)) {
            if (slot_fill == 2) {
                user_color_1.setIcon(ic_brick);
                slot_fill--;
                color_1 = 0;
            } else if (slot_fill == 3) {
                user_color_2.setIcon(ic_brick);
                slot_fill--;
                color_2 = 0;
            } else if (slot_fill == 4) {
                user_color_3.setIcon(ic_brick);
                slot_fill--;
                color_3 = 0;
            } else if (slot_fill == 5) {
                user_color_4.setIcon(ic_brick);
                slot_fill--;
                color_4 = 0;
            }
        } else if (e.getSource().equals(user_color_enter)) {
            if (box_fill <= 5) {
                referee_warn("Enter Your Secret Code First");
                return;
            }

            if (slot_fill != 5) {
                referee_warn("You Should Enter 4 Color Code");
            }
            if (slot_fill == 5) {
                slot_fill = 10;
                user_guess = new Guess(color_1, color_2, color_3, color_4);
                if (user_guess_logs.contains(user_guess.toString())) {
                    referee_warn("You Already Entered That Code!");
                    slot_fill = 5;
                    return;
                }
                user_score_counter();

                if (user_guess.toString().equals(ai_code.toString())) {
                    disable_all();
                    set_msg("You Win");
                    referee("Congratulations You Have Guessed The Code !");

                } else {
                    referee_warn("Wrong Code!\n" + gs_info.get(count - 1).toString());
                    slot_fill = 5;
                    ai_turn();
                }
            }
        } else if (e.getActionCommand().equalsIgnoreCase("Start Game")) {
            this.setTitle("Enter Your Code");
            cmd.setText("RESET");
            enable_all();
            cmd.setBackground(Color.red);
            cmd.setForeground(Color.white);
        } else if (e.getActionCommand().equalsIgnoreCase("RESET")) {
            //cmd.setText("Start Game");
            new Gameplay();
            dispose();

        }

    }

    //-------
    public void red_select() {
        if (slot_fill == 1) {
            user_color_1.setIcon(ic_red);
            color_1 = red;
            slot_fill++;
        } else if (slot_fill == 2) {
            user_color_2.setIcon(ic_red);
            color_2 = red;
            slot_fill++;
        } else if (slot_fill == 3) {
            user_color_3.setIcon(ic_red);
            color_3 = red;
            slot_fill++;
        } else if (slot_fill == 4) {
            user_color_4.setIcon(ic_red);
            color_4 = red;
            slot_fill++;
        }
    }

    public void green_select() {
        if (slot_fill == 1) {
            user_color_1.setIcon(ic_green);
            color_1 = green;
            slot_fill++;
        } else if (slot_fill == 2) {
            user_color_2.setIcon(ic_green);
            color_2 = green;
            slot_fill++;
        } else if (slot_fill == 3) {
            user_color_3.setIcon(ic_green);
            color_3 = green;
            slot_fill++;
        } else if (slot_fill == 4) {
            user_color_4.setIcon(ic_green);
            color_4 = green;
            slot_fill++;
        }
    }

    public void blue_select() {
        if (slot_fill == 1) {
            user_color_1.setIcon(ic_blue);
            color_1 = blue;
            slot_fill++;
        } else if (slot_fill == 2) {
            user_color_2.setIcon(ic_blue);
            color_2 = blue;
            slot_fill++;
        } else if (slot_fill == 3) {
            user_color_3.setIcon(ic_blue);
            color_3 = blue;
            slot_fill++;
        } else if (slot_fill == 4) {
            user_color_4.setIcon(ic_blue);
            color_4 = blue;
            slot_fill++;
        }
    }

    public void yellow_select() {
        if (slot_fill == 1) {
            user_color_1.setIcon(ic_yellow);
            color_1 = yellow;
            slot_fill++;
        } else if (slot_fill == 2) {
            user_color_2.setIcon(ic_yellow);
            color_2 = yellow;
            slot_fill++;
        } else if (slot_fill == 3) {
            user_color_3.setIcon(ic_yellow);
            color_3 = yellow;
            slot_fill++;
        } else if (slot_fill == 4) {
            user_color_4.setIcon(ic_yellow);
            color_4 = yellow;
            slot_fill++;
        }
    }

    public void black_select() {
        if (slot_fill == 1) {
            user_color_1.setIcon(ic_black);
            color_1 = black;
            slot_fill++;
        } else if (slot_fill == 2) {
            user_color_2.setIcon(ic_black);
            color_2 = black;
            slot_fill++;
        } else if (slot_fill == 3) {
            user_color_3.setIcon(ic_black);
            color_3 = black;
            slot_fill++;
        } else if (slot_fill == 4) {
            user_color_4.setIcon(ic_black);
            color_4 = black;
            slot_fill++;
        }
    }

    public void white_select() {
        if (slot_fill == 1) {
            user_color_1.setIcon(ic_white);
            color_1 = white;
            slot_fill++;
        } else if (slot_fill == 2) {
            user_color_2.setIcon(ic_white);
            color_2 = white;
            slot_fill++;
        } else if (slot_fill == 3) {
            user_color_3.setIcon(ic_white);
            color_3 = white;
            slot_fill++;
        } else if (slot_fill == 4) {
            user_color_4.setIcon(ic_white);
            color_4 = white;
            slot_fill++;
        }
    }

    //SET COLOR CODE FOR USER-----------------------------------------------
    //SET COLOR CODE FOR USER-----------------------------------------------
    //SET COLOR CODE FOR USER-----------------------------------------------
    public void red_set() {
        if (box_fill == 1) {
            user_code_1.setIcon(icb_red);
            slot_1 = red;
            box_fill++;
        } else if (box_fill == 2) {
            user_code_2.setIcon(icb_red);
            slot_2 = red;
            box_fill++;
        } else if (box_fill == 3) {
            user_code_3.setIcon(icb_red);
            slot_3 = red;
            box_fill++;
        } else if (box_fill == 4) {
            user_code_4.setIcon(icb_red);
            slot_4 = red;
            box_fill++;
        }
    }

    public void green_set() {
        if (box_fill == 1) {
            user_code_1.setIcon(icb_green);
            slot_1 = green;
            box_fill++;
        } else if (box_fill == 2) {
            user_code_2.setIcon(icb_green);
            slot_2 = green;
            box_fill++;
        } else if (box_fill == 3) {
            user_code_3.setIcon(icb_green);
            slot_3 = green;
            box_fill++;
        } else if (box_fill == 4) {
            user_code_4.setIcon(icb_green);
            slot_4 = green;
            box_fill++;
        }
    }

    public void blue_set() {
        if (box_fill == 1) {
            user_code_1.setIcon(icb_blue);
            slot_1 = blue;
            box_fill++;
        } else if (box_fill == 2) {
            user_code_2.setIcon(icb_blue);
            slot_2 = blue;
            box_fill++;
        } else if (box_fill == 3) {
            user_code_3.setIcon(icb_blue);
            slot_3 = blue;
            box_fill++;
        } else if (box_fill == 4) {
            user_code_4.setIcon(icb_blue);
            slot_4 = blue;
            box_fill++;
        }
    }

    public void yellow_set() {
        if (box_fill == 1) {
            user_code_1.setIcon(icb_yellow);
            slot_1 = yellow;
            box_fill++;
        } else if (box_fill == 2) {
            user_code_2.setIcon(icb_yellow);
            slot_2 = yellow;
            box_fill++;
        } else if (box_fill == 3) {
            user_code_3.setIcon(icb_yellow);
            slot_3 = yellow;
            box_fill++;
        } else if (box_fill == 4) {
            user_code_4.setIcon(icb_yellow);
            slot_4 = yellow;
            box_fill++;
        }
    }

    public void black_set() {
        if (box_fill == 1) {
            user_code_1.setIcon(icb_black);
            slot_1 = black;
            box_fill++;
        } else if (box_fill == 2) {
            user_code_2.setIcon(icb_black);
            slot_2 = black;
            box_fill++;
        } else if (box_fill == 3) {
            user_code_3.setIcon(icb_black);
            slot_3 = black;
            box_fill++;
        } else if (box_fill == 4) {
            user_code_4.setIcon(icb_black);
            slot_4 = black;
            box_fill++;
        }
    }

    public void white_set() {
        if (box_fill == 1) {
            user_code_1.setIcon(icb_white);
            slot_1 = white;
            box_fill++;
        } else if (box_fill == 2) {
            user_code_2.setIcon(icb_white);
            slot_2 = white;
            box_fill++;
        } else if (box_fill == 3) {
            user_code_3.setIcon(icb_white);
            slot_3 = white;
            box_fill++;
        } else if (box_fill == 4) {
            user_code_4.setIcon(icb_white);
            slot_4 = white;
            box_fill++;
        }
    }
    //SET COLOR CODE FOR USER-----------------------------------------------
    //SET COLOR CODE FOR USER-----------------------------------------------
    //SET COLOR CODE FOR USER-----------------------------------------------

    public void build_me() {
        load_rsc();
        setSize(420, 535);
        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        //decor
        div_1.setIcon(ic_divider);
        div_2.setIcon(ic_divider);
        div_3.setIcon(ic_divider);
        div_4.setIcon(ic_divider);
        div_5.setIcon(ic_divider);
        //AI
        ai_code_1.setIcon(isecret_box);
        ai_code_2.setIcon(isecret_box);
        ai_code_3.setIcon(isecret_box);
        ai_code_4.setIcon(isecret_box);
        pnl_ai_code.add(ai_code_1);
        pnl_ai_code.add(ai_code_2);
        pnl_ai_code.add(ai_code_3);
        pnl_ai_code.add(ai_code_4);
        //
        ai_color_fill_left.setIcon(ic_brick);
        ai_color_1.setIcon(ic_brick);
        ai_color_2.setIcon(ic_brick);
        ai_color_3.setIcon(ic_brick);
        ai_color_4.setIcon(ic_brick);
        ai_color_fill_right.setIcon(ic_brick);
        pnl_ai_colors.add(ai_color_fill_left);
        pnl_ai_colors.add(ai_color_1);
        pnl_ai_colors.add(ai_color_2);
        pnl_ai_colors.add(ai_color_3);
        pnl_ai_colors.add(ai_color_4);
        pnl_ai_colors.add(ai_color_fill_right);

        //User Color
        user_color_1.setIcon(ic_brick);
        user_color_2.setIcon(ic_brick);
        user_color_3.setIcon(ic_brick);
        user_color_4.setIcon(ic_brick);
        //
        user_color_edit.setIcon(ic_edit);
        user_color_enter.setIcon(ic_enter);
        user_color_edit.setPreferredSize(new Dimension(70, 70));
        user_color_enter.setPreferredSize(new Dimension(70, 70));
        user_color_edit.setBackground(Color.gray);
        user_color_enter.setBackground(Color.gray);

        pnl_user_colors.add(user_color_edit);
        pnl_user_colors.add(user_color_1);
        pnl_user_colors.add(user_color_2);
        pnl_user_colors.add(user_color_3);
        pnl_user_colors.add(user_color_4);
        pnl_user_colors.add(user_color_enter);
        //UserButtons
        user_btn_red.setIcon(ic_red);
        user_btn_green.setIcon(ic_green);
        user_btn_blue.setIcon(ic_blue);
        user_btn_yellow.setIcon(ic_yellow);
        user_btn_black.setIcon(ic_black);
        user_btn_white.setIcon(ic_white);
        //
        user_btn_red.setPreferredSize(new Dimension(70, 70));
        user_btn_green.setPreferredSize(new Dimension(70, 70));
        user_btn_blue.setPreferredSize(new Dimension(70, 70));
        user_btn_yellow.setPreferredSize(new Dimension(70, 70));
        user_btn_black.setPreferredSize(new Dimension(70, 70));
        user_btn_white.setPreferredSize(new Dimension(70, 70));
        //
        pnl_user_buttons.add(user_btn_red);
        pnl_user_buttons.add(user_btn_green);
        pnl_user_buttons.add(user_btn_blue);
        pnl_user_buttons.add(user_btn_yellow);
        pnl_user_buttons.add(user_btn_black);
        pnl_user_buttons.add(user_btn_white);
        //User Code
        user_code_1.setIcon(icb_brick);
        user_code_2.setIcon(icb_brick);
        user_code_3.setIcon(icb_brick);
        user_code_4.setIcon(icb_brick);
        pnl_user_code.add(user_code_1);
        pnl_user_code.add(user_code_2);
        pnl_user_code.add(user_code_3);
        pnl_user_code.add(user_code_4);

        add(pnl_ai_code);
        add(div_1);
        add(pnl_ai_colors);
        add(div_2);
        add(pnl_user_colors);
        add(div_3);
        add(pnl_user_buttons);
        add(div_4);
        add(pnl_user_code);
        add(div_5);

        cmd.setPreferredSize(new Dimension(420, 35));
        cmd.setFont(new Font("times new roman", 1, 20));
        cmd.setBackground(Color.white);
        add(cmd);
        //
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void load_rsc() {
        secret_box = new ImageIcon(getClass().getResource("/img/secret.png")).getImage();
        c_red = new ImageIcon(getClass().getResource("/img/red.png")).getImage();
        c_green = new ImageIcon(getClass().getResource("/img/green.png")).getImage();
        c_blue = new ImageIcon(getClass().getResource("/img/blue.png")).getImage();
        c_yellow = new ImageIcon(getClass().getResource("/img/yellow.png")).getImage();
        c_black = new ImageIcon(getClass().getResource("/img/black.png")).getImage();
        c_white = new ImageIcon(getClass().getResource("/img/white.png")).getImage();
        //
        c_edit = new ImageIcon(getClass().getResource("/img/edit.png")).getImage();
        c_enter = new ImageIcon(getClass().getResource("/img/enter.png")).getImage();
        //
        c_brick = new ImageIcon(getClass().getResource("/img/brick.png")).getImage();
        c_divider = new ImageIcon(getClass().getResource("/img/divider.png")).getImage();
        c_referee = new ImageIcon(getClass().getResource("/img/referee.jpg")).getImage();
        c_referee_no = new ImageIcon(getClass().getResource("/img/referee_no.jpg")).getImage();

        secret_box = secret_box.getScaledInstance(105, 105, java.awt.Image.SCALE_SMOOTH);
        c_red = c_red.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
        c_green = c_green.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
        c_blue = c_blue.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
        c_yellow = c_yellow.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
        c_black = c_black.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
        c_white = c_white.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
        //
        c_edit = c_edit.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
        c_enter = c_enter.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
        c_brick = c_brick.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);

        cb_red = c_red.getScaledInstance(105, 105, java.awt.Image.SCALE_SMOOTH);
        cb_green = c_green.getScaledInstance(105, 105, java.awt.Image.SCALE_SMOOTH);
        cb_blue = c_blue.getScaledInstance(105, 105, java.awt.Image.SCALE_SMOOTH);
        cb_yellow = c_yellow.getScaledInstance(105, 105, java.awt.Image.SCALE_SMOOTH);
        cb_black = c_black.getScaledInstance(105, 105, java.awt.Image.SCALE_SMOOTH);
        cb_white = c_white.getScaledInstance(105, 105, java.awt.Image.SCALE_SMOOTH);

        cb_brick = c_brick.getScaledInstance(105, 105, java.awt.Image.SCALE_SMOOTH);
        c_divider = c_divider.getScaledInstance(420, 10, java.awt.Image.SCALE_SMOOTH);
        c_referee = c_referee.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
        c_referee_no = c_referee_no.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
        //---------Load Image Icons
        isecret_box = new ImageIcon(secret_box);
        ic_red = new ImageIcon(c_red);
        ic_green = new ImageIcon(c_green);
        ic_blue = new ImageIcon(c_blue);
        ic_yellow = new ImageIcon(c_yellow);
        ic_black = new ImageIcon(c_black);
        ic_white = new ImageIcon(c_white);
        //
        icb_red = new ImageIcon(cb_red);
        icb_green = new ImageIcon(cb_green);
        icb_blue = new ImageIcon(cb_blue);
        icb_yellow = new ImageIcon(cb_yellow);
        icb_black = new ImageIcon(cb_black);
        icb_white = new ImageIcon(cb_white);
        //
        ic_edit = new ImageIcon(c_edit);
        ic_enter = new ImageIcon(c_enter);
        ic_brick = new ImageIcon(c_brick);
        icb_brick = new ImageIcon(cb_brick);
        ic_divider = new ImageIcon(c_divider);

        ic_referee = new ImageIcon(c_referee);
        ic_referee_no = new ImageIcon(c_referee_no);

    }

    public void init_game_req() {
        disable_all();
        playmate = new AIModule();
        playmate.set_feed(new Guess(slot_1, slot_2, slot_3, slot_4));
        playmate._generate_guess();
        System.out.println("--------------------------");
        System.out.println("USER COLOR CODE: " + transcribe(playmate.feed_guess.toString()));
        System.out.println("--------------------------");
        System.out.println("AI Generated Guess Set");
        System.out.println("--------------------------");
        for (int x = 0; x < playmate.generated_guess_set.size(); x++) {
            System.out.println(playmate.gs_info.get(x).toConsole() + " -- " + transcribe(playmate.generated_guess_set.get(x).toString()));
        }
        System.out.println("--------------------------");

        //Ai guess
        ai_code = new Guess(
                playmate.generate(1, 6),
                playmate.generate(1, 6),
                playmate.generate(1, 6),
                playmate.generate(1, 6)
        );
        System.out.println("AI COLOR CODE:");
        System.out.println(transcribe(ai_code.toString()));
        System.out.println("--------------------------");
        enable_all();
    }

    public String transcribe(String s) {
        String a = "";
        for (char c : s.toCharArray()) {
            if (Character.toString(c).equals(Integer.toString(red))) {
                a += "Red\t";
            } else if (Character.toString(c).equals(Integer.toString(green))) {
                a += "Green\t";
            } else if (Character.toString(c).equals(Integer.toString(blue))) {
                a += "Blue\t";
            } else if (Character.toString(c).equals(Integer.toString(yellow))) {
                a += "Yellow\t";
            } else if (Character.toString(c).equals(Integer.toString(black))) {
                a += "Black\t";
            } else if (Character.toString(c).equals(Integer.toString(white))) {
                a += "White\t";
            } else {
                //
            }

        }

        return a;
    }

    //Components
    //Dividing Labels
    JLabel div_1 = new JLabel();
    JLabel div_2 = new JLabel();
    JLabel div_3 = new JLabel();
    JLabel div_4 = new JLabel();
    JLabel div_5 = new JLabel();

    //AI Controls
    JLabel ai_code_1 = new JLabel();
    JLabel ai_code_2 = new JLabel();
    JLabel ai_code_3 = new JLabel();
    JLabel ai_code_4 = new JLabel();
    JPanel pnl_ai_code = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));

    //
    JLabel ai_color_fill_left = new JLabel();
    JLabel ai_color_1 = new JLabel();
    JLabel ai_color_2 = new JLabel();
    JLabel ai_color_3 = new JLabel();
    JLabel ai_color_4 = new JLabel();
    JLabel ai_color_fill_right = new JLabel();
    JPanel pnl_ai_colors = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));

    //User Controls
    JLabel user_color_1 = new JLabel();
    JLabel user_color_2 = new JLabel();
    JLabel user_color_3 = new JLabel();
    JLabel user_color_4 = new JLabel();
    JButton user_color_edit = new JButton();
    JButton user_color_enter = new JButton();
    JPanel pnl_user_colors = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));

    JButton user_btn_red = new JButton();
    JButton user_btn_green = new JButton();
    JButton user_btn_blue = new JButton();
    JButton user_btn_yellow = new JButton();
    JButton user_btn_black = new JButton();
    JButton user_btn_white = new JButton();
    JPanel pnl_user_buttons = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));

    //User Secret
    JLabel user_code_1 = new JLabel();
    JLabel user_code_2 = new JLabel();
    JLabel user_code_3 = new JLabel();
    JLabel user_code_4 = new JLabel();
    JPanel pnl_user_code = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));

    //shared constants
    public final static int red = 1;
    public final static int green = 2;
    public final static int blue = 3;
    public final static int yellow = 4;
    public final static int black = 5;
    public final static int white = 6;
    //Image Holders
    public Image secret_box;
    public Image c_red;
    public Image c_green;
    public Image c_blue;
    public Image c_yellow;
    public Image c_black;
    public Image c_white;
    //
    public Image cb_red;
    public Image cb_green;
    public Image cb_blue;
    public Image cb_yellow;
    public Image cb_black;
    public Image cb_white;
    //
    public Image c_edit;
    public Image c_enter;
    public Image c_brick;
    public Image cb_brick;
    public Image c_divider;

    public Image c_referee;
    public Image c_referee_no;
    //-----------------------------------------
    public ImageIcon isecret_box;
    public ImageIcon ic_red;
    public ImageIcon ic_green;
    public ImageIcon ic_blue;
    public ImageIcon ic_yellow;
    public ImageIcon ic_black;
    public ImageIcon ic_white;
    //
    public ImageIcon icb_red;
    public ImageIcon icb_green;
    public ImageIcon icb_blue;
    public ImageIcon icb_yellow;
    public ImageIcon icb_black;
    public ImageIcon icb_white;
    //
    public ImageIcon ic_edit;
    public ImageIcon ic_enter;
    public ImageIcon ic_brick;
    public ImageIcon icb_brick;
    public ImageIcon ic_divider;

    public ImageIcon ic_referee;
    public ImageIcon ic_referee_no;

    public void referee(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Referee", JOptionPane.INFORMATION_MESSAGE, ic_referee);
    }

    public void referee_warn(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Referee Warning", JOptionPane.INFORMATION_MESSAGE, ic_referee_no);
    }

    //user Exact Inexact counter
    boolean x1 = false;
    boolean x2 = false;
    boolean x3 = false;
    boolean x4 = false;
    int exact_count = 0;
    int inexact_count = 0;
    int count = 0;

    public void user_score_counter() {
        exact_count = 0;
        inexact_count = 0;

        x1 = false;
        x2 = false;
        x3 = false;
        x4 = false;

        count++;

//------------------------------------------------------------------
//------------------------------------------------------------------
        if (user_guess.slot_1 == ai_code.slot_1) {
            if (multi_check(1)) {

            } else {
                exact_count++;
                multi_gate(1);
            }

        } else {
            for (int x = 0; x <= 3; x++) {
                if (x == 0) {
                    continue;
                } else {
                    if (user_guess.toArrayList().get(x).equals(ai_code.slot_1)) {
                        if (multi_check(x + 1)) {
                            continue;
                        }
                        inexact_count++;
                        multi_gate(x + 1);
                        break;
                    }
                }
            }
        }
        if (user_guess.slot_2 == ai_code.slot_2) {
            if (multi_check(2)) {

            } else {
                exact_count++;
                multi_gate(2);
            }

        } else {
            for (int x = 0; x <= 3; x++) {
                if (x == 1) {
                    continue;
                } else {
                    if (user_guess.toArrayList().get(x).equals(ai_code.slot_2)) {
                        if (multi_check(x + 1)) {
                            continue;
                        }

                        inexact_count++;
                        multi_gate(x + 1);
                        break;
                    }
                }
            }
        }
        if (user_guess.slot_3 == ai_code.slot_3) {
            if (multi_check(3)) {

            } else {
                exact_count++;
                multi_gate(3);
            }

        } else {
            for (int x = 0; x <= 3; x++) {
                if (x == 2) {
                    continue;
                } else {
                    if (user_guess.toArrayList().get(x).equals(ai_code.slot_3)) {
                        if (multi_check(x + 1)) {
                            continue;
                        }
                        inexact_count++;
                        multi_gate(x + 1);
                        break;
                    }
                }
            }
        }
        if (user_guess.slot_4 == ai_code.slot_4) {
            if (multi_check(4)) {

            } else {
                exact_count++;
                multi_gate(4);
            }
        } else {
            for (int x = 0; x <= 3; x++) {
                if (x == 3) {
                    continue;
                } else {
                    if (user_guess.toArrayList().get(x).equals(ai_code.slot_4)) {
                        if (multi_check(x + 1)) {
                            continue;
                        }
                        inexact_count++;
                        multi_gate(x + 1);
                        break;
                    }
                }
            }
        }
                //Add Code
        //GuessMiss

        //
        //
        //Guess Near Hit
//------------------------------------------------------------------
//------------------------------------------------------------------
        int revalidate_count = 0;
        if (user_guess.slot_1 == ai_code.slot_1) {
            revalidate_count++;
        }
        if (user_guess.slot_2 == ai_code.slot_2) {
            revalidate_count++;
        }
        if (user_guess.slot_3 == ai_code.slot_3) {
            revalidate_count++;
        }
        if (user_guess.slot_4 == ai_code.slot_4) {
            revalidate_count++;
        }
        int total = exact_count + inexact_count;
        exact_count = revalidate_count;
        inexact_count = total - revalidate_count;
        user_guess_logs.add(user_guess.toString());
        gs_info.add(new GuessSetInfo(count, exact_count, inexact_count));
        //System.out.println(analyzer + " " + exact_count + " " + inexact_count + " " + generated_guess.toString());

    }//
    //--------------------------------------
    public ArrayList<GuessSetInfo> gs_info = new ArrayList<GuessSetInfo>();

    public void multi_gate(int x) {
        if (x == 1) {
            x1 = true;
        } else if (x == 2) {
            x2 = true;
        } else if (x == 3) {
            x3 = true;
        } else if (x == 4) {
            x4 = true;
        }
    }

    public boolean multi_check(int x) {
        boolean slot_taken = false;
        if (x == 1) {
            if (x1 == true) {
                slot_taken = true;
            } else {
                slot_taken = false;
            }
        } else if (x == 2) {
            if (x2 == true) {
                slot_taken = true;
            } else {
                slot_taken = false;
            }
        } else if (x == 3) {
            if (x3 == true) {
                slot_taken = true;
            } else {
                slot_taken = false;
            }
        } else if (x == 4) {
            if (x4 == true) {
                slot_taken = true;
            } else {
                slot_taken = false;
            }
        }
        return slot_taken;

    }

    //-----
}
