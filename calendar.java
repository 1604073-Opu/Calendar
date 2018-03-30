package CALENDAR;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class calendar extends JFrame {

    JFrame mainframe = new JFrame("Calendar");
    JFrame inputframe;
    public JPanel panel = new JPanel(new GridLayout(5, 7));
    JLabel background;
    int year, month, w_day, total_days;

    public void frame() {
        mainframe.setResizable(false);
        mainframe.setLocation(400, 175);
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.setSize(500, 400);
        mainframe.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
        Show();
        mainframe.setVisible(true);
        
    }

    void Show() {
        mainframe.getContentPane().removeAll();
        mainframe.setContentPane(new JLabel(new ImageIcon(getClass().getResource((month + ".jpg")))));
        mainframe.setSize(499, 399);
        mainframe.setSize(500, 400);
        showYear();
        showMonth();
        Informations now=new Informations();
        total_days=now.setTotalDays(year, month);
        showDates();
        Buttons();
        JButton info=new JButton(new ImageIcon(getClass().getResource("info.png")));
        info.setBounds(440,325,32,32);
        info.setBorder(BorderFactory.createEmptyBorder());
        info.setFocusable(true);
        info.setContentAreaFilled(false);
        mainframe.add(info);
        info.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                infoActionPerformed(e);
            }
        });
    }
    
    public void infoActionPerformed(ActionEvent e)
    {
        JFrame info=new JFrame("Info");
        info.setLayout(null);
        info.setResizable(false);
        info.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        info.setVisible(true);
        info.setLocation(410,275);
        info.setSize(475,203);
        info.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
        info.setContentPane(new JLabel(new ImageIcon(getClass().getResource("background.jpg"))));
        JLabel me=new JLabel(new ImageIcon(getClass().getResource("me.JPG")));
        me.setBounds(0,0,130,175);
        JLabel text[]={new JLabel("Simple Calendar!"),new JLabel("Md. Nahidul Islam Opu"),new JLabel("Department of Computer Science & Engineering"),new JLabel("Chittagong University of Engineering & Technology"),new JLabel("March 30, 2018"),new JLabel("opu.nahidul@gmail.com")};
       for(int i=0;i<6;i++){
           text[i].setBounds(130,-90+i*20,340,200);
           if(i==0) text[i].setFont(new Font("Times New Roman",1,16));
           else text[i].setFont(new Font("Times New Roman",0,14));
        text[i].setForeground(Color.white);
        info.add(text[i]);
       }
       JButton ok=new JButton("OK");
       ok.setBackground(Color.BLACK);
       ok.setForeground(Color.WHITE);
       ok.setFocusable(false);
       ok.setLocation(250, 130);
       ok.setSize(60,30);
       info.add(ok);
        info.add(me);
        ok.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                info.setVisible(false);
            }
        });
    }
    
    void showYear() {
        JPanel p = new JPanel();
        p.setLocation(218, 10);
        p.setSize(50, 30);
        p.setBackground(Color.white);
        JLabel y = new JLabel();
        y.setBounds(218, 10, 50, 30);
        y.setFont(new java.awt.Font("Times New Roman", 1, 22));
        y.setText(String.valueOf(year));
        p.add(y);
        mainframe.add(p);
    }

    void showMonth() {
        JPanel p = new JPanel();
        p.setBackground(Color.white);
        p.setLocation(195, 48);
        p.setSize(98, 32);
        String[] m_names = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        JComboBox<String> m = new JComboBox<>(m_names);
        m.setSelectedIndex(month - 1);
        m.setVisible(true);
        p.add(m);
        m.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = (String) m.getSelectedItem();
                getSelectedMonth(s);
                Show();
            }
        });
        mainframe.add(p);
    }

    void showDates() {
        int i, x;
        panel.removeAll();
        panel.revalidate();
        panel.repaint();
        Informations now = new Informations();
        JLabel[] w_day1 = {new JLabel("  SUN"), new JLabel("  MON"), new JLabel("  TUE"), new JLabel("  WED"), new JLabel("  THU"), new JLabel("  FRI"), new JLabel("  SAT")};
        w_day1[6].setForeground(Color.green);
        w_day1[5].setForeground(Color.green);
        JPanel panel1 = new JPanel(new GridLayout(1, 7));
        panel1.setBackground(Color.white);
        panel.setBackground(Color.white);
        panel1.setSize(300, 30);
        panel1.setLocation(100, 100);
        for (i = 0; i < 7; i++) {
            panel1.add(w_day1[i]);
        }
        JLabel date[] = new JLabel[36];
        panel.setLocation(100, 120);
        panel.setSize(300, 180);
        w_day = now.week_day(year, month);
        for (i = 1; i <= 35; i++) {
            date[i] = new JLabel();
            if (year == now.year() && month == now.month() && (i - w_day + 1) == now.date()) {
                date[i].setForeground(Color.red);
            }
            else if (i % 7 == 0 || i % 7 == 6) {
                date[i].setForeground(Color.green);
            }
            if ((28 + (7 - w_day + 1)) < total_days) {
                x = (36 - w_day) + i;
            } else {
                x = 0;
            }
            if (i < w_day) {
                if (x == 0 || x > total_days) {
                    date[i].setText(" ");
                } else {
                    date[i].setText("    " + String.valueOf(x));
                }
            } else {
                if ((i - w_day + 1) > total_days) {
                    date[i].setText(" ");
                } else {
                    date[i].setText("    " + String.valueOf(i - w_day + 1));
                }
            }
            panel.add(date[i]);
        }
        mainframe.add(panel1);
        mainframe.add(panel);
    }

    void Buttons() {
        JButton previous = new JButton();
        previous.setToolTipText("Go To Previous Year");
        previous.setIcon(new ImageIcon(getClass().getResource("Previous.png")));
        previous.setBorder(BorderFactory.createEmptyBorder());
        previous.setContentAreaFilled(false);
        previous.setBounds(160, 12, 25, 25);
        previous.setFocusable(true);
        previous.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                year--;
                Show();
            }
        });
        mainframe.add(previous);
        JButton next = new JButton();
        next.setToolTipText("Go To Next Year");
        next.setIcon(new ImageIcon(getClass().getResource("Next.png")));
        next.setBounds(306, 12, 25, 25);
        next.setContentAreaFilled(false);
        next.setBorder(BorderFactory.createEmptyBorder());
        next.setFocusable(true);
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                year++;
                Show();
            }
        });
        mainframe.add(next);
        JButton m_next = new JButton();
        m_next.setToolTipText("Go To Next Month");
        m_next.setIcon(new ImageIcon(getClass().getResource("m_next.png")));
        m_next.setBounds(320, 50, 25, 25);
        m_next.setBorder(BorderFactory.createEmptyBorder());
        m_next.setFocusable(true);
        m_next.setContentAreaFilled(false);
        m_next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                month++;
                if (month > 12) {
                    month = 1;
                    year++;
                }
                Show();
            }
        });
        mainframe.add(m_next);
        JButton m_prev = new JButton();
        m_prev.setToolTipText("Go To Previous Month");
        m_prev.setIcon(new ImageIcon(getClass().getResource("m_prev.png")));
        m_prev.setBounds(143, 50, 25, 25);
        m_prev.setBorder(BorderFactory.createEmptyBorder());
        m_prev.setFocusable(true);
        m_prev.setContentAreaFilled(false);
        m_prev.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                month--;
                if (month < 1) {
                    month = 12;
                    year--;
                }
                Show();
            }
        });
        mainframe.add(m_prev);
        Informations now = new Informations();
        if (year != now.year() || month != now.month()) {
            JButton ret = new JButton(new ImageIcon(getClass().getResource("ret.png")));
            ret.setToolTipText("Return To Today's Calendar");
            ret.setBounds(40, 160, 32, 32);
            ret.setBorder(BorderFactory.createEmptyBorder());
            ret.setFocusable(true);
            ret.setContentAreaFilled(false);
            ret.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    year = now.year();
                    month = now.month();
                    Show();
                }
            });
            mainframe.add(ret);
        }
        JButton jump = new JButton(new ImageIcon(getClass().getResource("jump.png")));
        jump.setToolTipText("Jump To A Date");
        jump.setBounds(220, 320, 32, 32);
        jump.setBorder(BorderFactory.createEmptyBorder());
        jump.setFocusable(true);
        jump.setContentAreaFilled(false);
        jump.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jumpActionPerformed(e);
            }
        });
        mainframe.add(jump);
    }

    void getSelectedMonth(String s) {
        if ("January".equals(s)) {
            month = 1;
        } else if ("February".equals(s)) {
            month = 2;
        } else if ("March".equals(s)) {
            month = 3;
        } else if ("April".equals(s)) {
            month = 4;
        } else if ("May".equals(s)) {
            month = 5;
        } else if ("June".equals(s)) {
            month = 6;
        } else if ("July".equals(s)) {
            month = 7;
        } else if ("August".equals(s)) {
            month = 8;
        } else if ("September".equals(s)) {
            month = 9;
        } else if ("October".equals(s)) {
            month = 10;
        } else if ("November".equals(s)) {
            month = 11;
        } else if ("December".equals(s)) {
            month = 12;
        }
    }

    public void jumpActionPerformed(ActionEvent e) {
        inputframe = new JFrame("Jump to a year");
        inputframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        inputframe.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
        inputframe.setLocation(500, 275);
        inputframe.setSize(300, 200);
        inputframe.setResizable(false);
        inputframe.setVisible(true);
        JTextField read = new JTextField();
        JPanel P1 = new JPanel();
        P1.setLayout(null);
        JLabel show = new JLabel("Input Year:");
        show.setLocation(50, 10);
        show.setSize(100, 30);
        read.setLocation(120, 10);
        read.setSize(100, 30);
        JButton ok = new JButton("Submit");
        ok.setLocation(100, 120);
        ok.setSize(80, 25);
        P1.add(show);
        P1.add(ok);
        P1.add(read);
        String[] m_names = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        JComboBox<String> m = new JComboBox<>(m_names);
        m.setSelectedIndex(0);
        m.setVisible(true);
        m.setBounds(100,60,100,30);
        P1.add(m);
        inputframe.add(P1);
        month=1;
        m.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = (String) m.getSelectedItem();
                getSelectedMonth(s);
            }
        });
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = read.getText();
                year = Integer.parseInt(s);
                inputframe.setVisible(false);
                Show();
            }
        });
    }

    void setValues() {
        Informations data = new Informations();
        year = data.year();
        month = data.month();
    }

    public static void main(String[] args) {
        calendar main = new calendar();
        main.setValues();
        main.frame();
    }

}
