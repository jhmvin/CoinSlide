package modules;

import java.util.ArrayList;

public class Guess {

    //shared constants
    public final static int red = 1;
    public final static int green = 2;
    public final static int blue = 3;
    public final static int yellow = 4;
    public final static int black = 5;
    public final static int white = 6;

    public int slot_1;
    public int slot_2;
    public int slot_3;
    public int slot_4;


    public Guess(int a, int b, int c, int d) {
        this.slot_1 = a;
        this.slot_2 = b;
        this.slot_3 = c;
        this.slot_4 = d;
    }

    @Override
    public String toString() {
        return Integer.toString(slot_1)
                + Integer.toString(slot_2)
                + Integer.toString(slot_3)
                + Integer.toString(slot_4);
    }

    public ArrayList toArrayList() {
        ArrayList<Integer> temp = new ArrayList<Integer>();
        temp.add(slot_1);
        temp.add(slot_2);
        temp.add(slot_3);
        temp.add(slot_4);
        return temp;
    }

    public boolean check_identity() {
        boolean ide = false;
        if ((slot_1 == slot_2) && (slot_2 == slot_3) && (slot_3 == slot_4)) {
            ide = true;
        }

        return ide;
    }


}
