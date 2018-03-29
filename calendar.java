package CALENDAR;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class calendar extends JFrame {

    JFrame mainframe=new JFrame("Calendar");
    JFrame inputframe;
    static public JPanel panel=new JPanel(new GridLayout(5, 7));
    JLabel background;
    static int year, month, w_day, total_days;

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
        setTotalDays(year, month);
        showDates();
        Buttons();
    }

    void showYear() {
        JPanel p = new JPanel();
        p.setLocation(218, 10);
        p.setSize(50, 30);
        JLabel y = new JLabel();
        y.setBounds(218, 10, 50, 30);
        y.setFont(new java.awt.Font("Times New Roman", 1, 22));
        y.setText(String.valueOf(year));
        p.add(y);
        mainframe.add(p);
    }

    void showMonth() {
        JPanel p = new JPanel();
        p.setLocation(195, 48);
        p.setSize(98, 32);
        String[] m_names = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        JComboBox<String> m = new JComboBox<>(m_names);
        m.setSelectedIndex(month-1);
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
        JLabel[] w_day1 ={new JLabel("  SUN"),new JLabel("  MON"),new JLabel("  TUE"),new JLabel("  WED"),new JLabel("  THU"),new JLabel("  FRI"),new JLabel("  SAT")};
        w_day1[6].setForeground(Color.green);
        w_day1[5].setForeground(Color.green);
        JPanel panel1 = new JPanel(new GridLayout(1,7));
        panel1.setSize(300, 30);
        panel1.setLocation(100,100);
        for(i=0;i<7;i++)
        panel1.add(w_day1[i]);
        JLabel date[] = new JLabel[36];
        panel.setLocation(100, 120);
        panel.setSize(300, 180);
        w_day = now.week_day(year, month);
        for (i = 1; i <= 35; i++) {
            date[i] = new JLabel();
            if (year == now.year() && month == now.month() && (i - w_day + 1) == now.date()) {
                date[i].setForeground(Color.red);
            }
            if (i % 7 == 0 || i % 7 == 6) {
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
                    date[i].setText("    "+String.valueOf(x));
                }
            } else {
                if ((i - w_day + 1) > total_days) {
                    date[i].setText(" ");
                } else {
                    date[i].setText("    "+String.valueOf(i - w_day + 1));
                }
            }
            panel.add(date[i]);
        }
        mainframe.add(panel1);
        mainframe.add(panel);
    }
    
    void Buttons()
    {
        JButton previous=new JButton();
        previous.setIcon(new ImageIcon(getClass().getResource("Previous.png")));
        previous.setBorder(BorderFactory.createEmptyBorder());
        previous.setContentAreaFilled(false);
        previous.setBounds(160, 12, 25, 25);
        previous.setFocusable(true);
        previous.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                year--;
            Show();}
        });
        mainframe.add(previous);
        JButton next=new JButton();
        next.setIcon(new ImageIcon(getClass().getResource("Next.png")));
        next.setBounds(306,12,25,25);
        next.setContentAreaFilled(false);
        next.setBorder(BorderFactory.createEmptyBorder());
        next.setFocusable(true);
        next.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                year++;
                Show();
            }
        });
        mainframe.add(next);
    }
    
    void setTotalDays(int y, int m) {
        if (m == 1) {
            total_days = 31;
        } else if (m == 2) {
            if (y % 400 == 0 || (y % 4 == 0 && y % 100 != 0)) {
                total_days = 29;
            } else {
                total_days = 28;
            }
        } else if (m == 3) {
            total_days = 31;

        } else if (m == 4) {
            total_days = 30;

        } else if (m == 5) {
            total_days = 31;

        } else if (m == 6) {
            total_days = 30;

        } else if (m == 7) {
            total_days = 31;

        } else if (m == 8) {
            total_days = 31;

        } else if (m == 9) {
            total_days = 30;

        } else if (m == 10) {
            total_days = 31;

        } else if (m == 11) {
            total_days = 30;

        } else if (m == 12) {
            total_days = 31;
        }
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
