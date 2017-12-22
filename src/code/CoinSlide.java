package code;

import java.awt.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class CoinSlide {

    final static DragPanel dragMe = new DragPanel();

    public CoinSlide() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Coin Slider");
        initComponents(frame);
        frame.setSize(500, 500);
        //frame.pack();

        frame.setVisible(true);
    }

    public static void main(String s[]) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CoinSlide();
            }
        });

    }

    private void initComponents(JFrame frame) {
        frame.getContentPane().add(dragMe);
    }
}

class DragPanel extends JPanel {

    //Coin Image
    Image logo;
    ArrayList<Rectangle> coins = new ArrayList<>();
    ArrayList<Point> valid_point = new ArrayList<>();
    int preX, preY;
    boolean isFirstTime = true;
    Rectangle area;
    boolean pressOut = false;
    private Dimension dim = new Dimension(400, 300);
    public int selected_coin = 0;
    //
    boolean is_right;
    boolean is_left;
    boolean is_up;
    boolean is_down;
    //
    Point right;
    Point left;
    Point up;
    Point down;
    //
    Point save_point;
    //
    //
    int update_x;
    int update_y;
    //
    ArrayList<LocationMemory> saved = new ArrayList<LocationMemory>();
    ArrayList<ArrayList> slot = new ArrayList<ArrayList>();

    public void save_me() {
        saved.clear();
        saved = new ArrayList<LocationMemory>();
        for (int x = 0; x < 8; x++) {
            saved.add(new LocationMemory(x, coins.get(x).getLocation()));
        }
        slot.add(saved);
    }

    public DragPanel() {
        create_coins();
        create_points();
        logo = new ImageIcon(getClass().getResource("/img/img.jpg")).getImage();
        logo = logo.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
        repaint();
        setBackground(Color.white);
        addMouseMotionListener(new MyMouseAdapter());
        addMouseListener(new MyMouseAdapter());
    }

    public void create_coins() {
        for (int x = 0; x < 8; x++) {
            coins.add(new Rectangle(0, 0, 100, 100));
        }
    }

    public void create_points() {
        valid_point.clear();
        valid_point.add(new Point(1, 1));//1
        valid_point.add(new Point(101, 1));//2
        valid_point.add(new Point(201, 1));//3
        valid_point.add(new Point(301, 1));//4

        valid_point.add(new Point(1, 101));//5
        valid_point.add(new Point(101, 101));//6
        valid_point.add(new Point(201, 101));//7
        valid_point.add(new Point(301, 101));//8

        valid_point.add(new Point(1, 201));//9
        valid_point.add(new Point(101, 201));//10
        valid_point.add(new Point(201, 201));//11
        valid_point.add(new Point(301, 201));//12
    }

    @Override
    public Dimension getPreferredSize() {
        return dim;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        if (isFirstTime) {
            area = new Rectangle(dim);
            coins.get(0).setLocation(1, 1);//1
            coins.get(1).setLocation(301, 1);//2
            coins.get(2).setLocation(1, 101);//3
            coins.get(3).setLocation(101, 101);//4
            coins.get(4).setLocation(201, 101);//5
            coins.get(5).setLocation(301, 101);//6
            coins.get(6).setLocation(1, 201);//7
            coins.get(7).setLocation(301, 201);//8

            isFirstTime = false;
        }

        g2d.setColor(Color.black);
        for (int x = 0; x < 8; x++) {
            g2d.drawImage(logo, coins.get(x).x, coins.get(x).y, coins.get(x).width, coins.get(x).height, null);
        }

    }

    boolean checkRect(Rectangle rec) {
        if (area == null) {
            //return false;
        }

        if (area.contains(rec.x, rec.y, rec.getWidth(), rec.getHeight())) {
            return true;
        }

        //boundery
        int new_x = rec.x;
        int new_y = rec.y;

        if ((rec.x + rec.getWidth()) > area.getWidth()) {
            new_x = (int) area.getWidth() - (int) (rec.getWidth() - 1);
        }
        if (rec.x <= 0) {
            new_x = 1;
        }
        if ((rec.y + rec.getHeight()) > area.getHeight()) {
            new_y = (int) area.getHeight() - (int) (rec.getHeight() - 1);
        }
        if (rec.y <= 0) {
            new_y = 1;
        }

        rec.setLocation(new_x, new_y);
        return false;
        //boundery

    }

//    public boolean check_coin(Rectangle rec) {
//        boolean inter = false;
//        //--per coind
//        System.out.println(selected_coin);
//        for (int x = 0; x < 8; x++) {
//            if (x == selected_coin) {
//                continue;
//            } else {
//
//                if (coins.get(x).intersects(rec)) {
//                    int c_y = coins.get(x).y;
//                    int c_x = coins.get(x).x;
//                    int m_y = rec.y;
//                    int m_x = rec.x;
//                    int o_y = rec.y;
//                    int o_x = rec.x;
//                    //y
//                    if (c_y < m_y) {
//                        m_y = (c_y + 101);
//                        rec.setLocation(o_x, m_y);
//
//                    }
//                    if (c_y > m_y) {
//                        m_y = (c_y - 101);
//                        rec.setLocation(o_x, m_y);
//
//                    }
//
//                    //x
//                    if (c_x < m_x) {
//                        m_x = (c_x + 101);
//                        rec.setLocation(m_x, o_y);
//
//                    }
//                    if (c_x > m_x) {
//                        m_x = (c_x - 101);
//                        rec.setLocation(m_x, o_y);
//
//                    }
//
//                    System.out.println("intersect");
//                    inter = true;
//                }
//
//            }
//
//        }
//        //-----
//        return inter;
//    }
    public void coin_bound(Point r) {
        int x = r.x;
        int y = r.y;

        right = new Point(x + 100, y);
        left = new Point(x - 100, y);
        up = new Point(x, y - 100);
        down = new Point(x, y + 100);

        is_right = true;
        is_left = true;
        is_up = true;
        is_down = true;
        boolean check_right = false;
        boolean check_left = false;

        for (Rectangle c : coins) {
            if (c.contains(right) || (right.x > area.width)) {
                is_right = false;
            }
            if (c.contains(left) || (left.x <= 0)) {
                is_left = false;
            }
            if (c.contains(up) || (up.y <= 0)) {
                is_up = false;
            }
            if (c.contains(down) || (down.y > area.height)) {
                is_down = false;
            }
            //2 up
            if (is_up) {
                if (c.contains(new Point(up.x + 100, up.y))) {
                    check_right = true;
//                    System.out.println("right");
                }
                if (c.contains(new Point(up.x - 100, up.y))) {
//                    System.out.println("left");
//                    check_left = true;
                }
            }
            //2 down
            if (is_down) {
                if (c.contains(new Point(down.x + 100, down.y))) {
                    check_right = true;
//                    System.out.println("right");
                }
                if (c.contains(new Point(down.x - 100, down.y))) {
//                    System.out.println("left");
                    check_left = true;
                }
            }

        }
        //up
//        if (is_up && check_left && check_right) {
//            is_up = true;
//        } else {
//            check_right = false;
//            check_left = false;
//            is_up = false;
//        }
//        //down
//        if (is_down && check_left && check_right) {
//            is_down = true;
//        } else {
//            check_right = false;
//            check_left = false;
//            is_down = false;
//        }
//        System.out.println(right + " " + left + " " + up + " " + down);
//        System.out.println(is_right + " " + is_left + " " + is_up + " " + is_down);
        update_x = x;
        update_y = y;
    }

    private class MyMouseAdapter extends MouseAdapter {

        Rectangle r;

        @Override
        public void mouseMoved(MouseEvent e) {
            repaint();
            //System.out.println(e.getX() + " " + e.getY());
            if (!pressOut) {
                for (Rectangle co : coins) {
                    if (co.contains(e.getX(), e.getY())) {
                        r = co;
                        //preX = r.x - e.getX();
                        //preY = r.y - e.getY();
                    }
                }

            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            selected_coin = 0;

//            if (rect.contains(e.getX(), e.getY())) {
//                r = rect;
//                preX = r.x - e.getX();
//                preY = r.y - e.getY();
//                updateLocation(e);
//            } else if (rect1.contains(e.getX(), e.getY())) {
//                r = rect1;
//                preX = r.x - e.getX();
//                preY = r.y - e.getY();
//                updateLocation(e);
//            } else {
            for (int x = 0; x < 8; x++) {

                if (coins.get(x).contains(e.getX(), e.getY())) {
                    r = coins.get(x);
                    preX = r.x - e.getX();
                    preY = r.y - e.getY();
                    selected_coin = x;
//                    System.out.println("clicked");
//                    System.out.println(r.getLocation());
                    updateLocation(e);
//                    System.out.println("clicked");
//                    System.out.println(save_point);

                    return;
                }
            }
            pressOut = true;
//            }

        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (!pressOut) {
                updateLocation(e);
            } else {

            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (r.contains(e.getX(), e.getY())) {
                updateLocation(e);
                r.setLocation(save_point);
//                System.out.println(save_point);
                repaint();
            } else {
                pressOut = false;

            }
        }

        public void updateLocation(MouseEvent e) {
            int x_loc = update_x;
            int y_loc = update_y;

            if (is_right && (e.getX() > update_x)) {
                if (preX + e.getX() < x_loc) {
                    r.setLocation(x_loc, r.y);
                } else {
                    r.setLocation(preX + e.getX(), r.y);
                }
                is_left = false;
            }
            if (is_left && (e.getX() < update_x)) {
                if (preX + e.getX() > x_loc) {
                    r.setLocation(x_loc, r.y);
                } else {
                    r.setLocation(preX + e.getX(), r.y);
                }
                is_right = false;
            }
            if (is_up && (e.getY() < update_y)) {
                if (preY + e.getY() > y_loc) {
                    r.setLocation(r.x, y_loc);
                } else {
                    r.setLocation(r.x, preY + e.getY());
                }
                is_down = false;
            }
            if (is_down && (e.getY() > update_y)) {
                if (preY + e.getY() < y_loc) {
                    r.setLocation(r.x, y_loc);
                } else {
                    r.setLocation(r.x, preY + e.getY());
                }
                is_up = false;
            }

            for (Point p : valid_point) {
                if (new Rectangle(p.x, p.y, 25, 25).contains(r.getLocation())) {
                    save_point = p;
                    //With Animation
                    r.setLocation(save_point);
                    break;
                }
                if (new Rectangle(p.x, (p.y - 25), 25, 25).contains(r.getLocation())) {
                    save_point = p;
                    //With Animation
                    r.setLocation(save_point);
                    break;
                }
                if (new Rectangle((p.x - 25), p.y, 25, 25).contains(r.getLocation())) {
                    save_point = p;
                    //With Animation
                    r.setLocation(save_point);
                    break;
                }

            }
            //Minimal Glitch no Animation
//            r.setLocation(save_point);

            for (Point p : valid_point) {

                if (p.equals(r.getLocation())) {
                    coin_bound(r.getLocation());
//                    System.out.println("bounded");
                }

            }

            checkRect(r);
            repaint();
        }
    }
}

class LocationMemory {

    int coin_number;
    Point coin_position;

    public LocationMemory(int coin, Point pos) {
        this.coin_number = coin;
        this.coin_position = pos;
    }

    public int get_coin() {
        return coin_number;
    }

    public Point get_pos() {
        return coin_position;
    }
}
