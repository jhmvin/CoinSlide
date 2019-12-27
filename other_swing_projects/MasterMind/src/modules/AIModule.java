package modules;

import java.util.ArrayList;

public class AIModule {

    public final static int red = 1;
    public final static int green = 2;
    public final static int blue = 3;
    public final static int yellow = 4;
    public final static int black = 5;
    public final static int white = 6;

    public Guess feed_guess;

    public ArrayList<Guess> generated_guess_set = new ArrayList<Guess>();
    public ArrayList<GuessSetInfo> gs_info = new ArrayList<GuessSetInfo>();

    ArrayList<String> guess_logs = new ArrayList<String>();
    ArrayList<Integer> near_hit = new ArrayList<Integer>();

    Guess generated_guess;
    boolean _2ndloopgate = true;
    int analyzer = 0;

    public AIModule() {
        //void constructor
    }

    public void set_feed(Guess g) {
        feed_guess = g;
    }

    boolean _4 = false;
    int exact_count = 0;
    int inexact_count = 0;
    boolean x1 = false;
    boolean x2 = false;
    boolean x3 = false;
    boolean x4 = false;

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

    public void guess_set() {

        while (true) {
            int d1 = 0;
            int d2 = 0;
            int d3 = 0;
            int d4 = 0;

            _2ndloopgate = true;
            exact_count = 0;
            inexact_count = 0;

            if (_4 == false) {
                d1 = generate(1, 6);
                d2 = generate(1, 6);
                d3 = generate(1, 6);
                d4 = generate(1, 6);
            } else {
                ArrayList<String> ticked = new ArrayList<String>();
                //2nd Loop
                while (_2ndloopgate) {
                    int index = generate(0, 3);
                    if (ticked.contains(Integer.toString(index))) {
                        //Skip
                        if (ticked.size() == 4) {
                            _2ndloopgate = false;
                        }
                    } else {

                        //
                        if (d1 == 0) {
                            d1 = near_hit.get(index).intValue();
                            ticked.add(Integer.toString(index));
                            continue;
                        }
                        if (d2 == 0) {
                            d2 = near_hit.get(index).intValue();
                            ticked.add(Integer.toString(index));
                            continue;
                        }
                        if (d3 == 0) {
                            d3 = near_hit.get(index).intValue();
                            ticked.add(Integer.toString(index));
                            continue;
                        }
                        if (d4 == 0) {
                            d4 = near_hit.get(index).intValue();
                            ticked.add(Integer.toString(index));
                            continue;
                        }

                    }
                }

            }

            generated_guess = new Guess(d1, d2, d3, d4);

            if (guess_logs.contains(generated_guess.toString())) {
                //
            } else {
                x1 = false;
                x2 = false;
                x3 = false;
                x4 = false;

                analyzer++;
                if (generated_guess.toString().equalsIgnoreCase(feed_guess.toString())) {
                    exact_count = 4;
                    break;
                } else {

                    guess_logs.add(generated_guess.toString());

//------------------------------------------------------------------
//------------------------------------------------------------------
                    if (generated_guess.slot_1 == feed_guess.slot_1) {
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
                                if (generated_guess.toArrayList().get(x).equals(feed_guess.slot_1)) {
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
                    if (generated_guess.slot_2 == feed_guess.slot_2) {
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
                                if (generated_guess.toArrayList().get(x).equals(feed_guess.slot_2)) {
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
                    if (generated_guess.slot_3 == feed_guess.slot_3) {
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
                                if (generated_guess.toArrayList().get(x).equals(feed_guess.slot_3)) {
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
                    if (generated_guess.slot_4 == feed_guess.slot_4) {
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
                                if (generated_guess.toArrayList().get(x).equals(feed_guess.slot_4)) {
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
                    if ((inexact_count + exact_count) == 0) {
                        analyzer--;
                        continue;
                    }
                    //

                    //
                    //Guess Near Hit
                    if (((inexact_count + exact_count) == 4) && _4 == false) {
                        int orig_checksum = 0;
                        int guess_checksum = 0;
                        for (int x = 0; x <= 3; x++) {
                            orig_checksum += Math.pow(Integer.parseInt(feed_guess.toArrayList().get(x).toString()), 2);
                        }
                        for (int x = 0; x <= 3; x++) {
                            guess_checksum += Math.pow(Integer.parseInt(generated_guess.toArrayList().get(x).toString()), 2);
                        }
                        if (orig_checksum == guess_checksum) {
                            near_hit.add(generated_guess.slot_1);
                            near_hit.add(generated_guess.slot_2);
                            near_hit.add(generated_guess.slot_3);
                            near_hit.add(generated_guess.slot_4);
                            //-------
                            _4 = true;
                        } else {
                            if (!feed_guess.check_identity()) {
                                analyzer--;
                                continue;

                            }
                        }

                    }

//------------------------------------------------------------------
//------------------------------------------------------------------
                    int revalidate_count = 0;
                    if (generated_guess.slot_1 == feed_guess.slot_1) {
                        revalidate_count++;
                    }
                    if (generated_guess.slot_2 == feed_guess.slot_2) {
                        revalidate_count++;
                    }
                    if (generated_guess.slot_3 == feed_guess.slot_3) {
                        revalidate_count++;
                    }
                    if (generated_guess.slot_4 == feed_guess.slot_4) {
                        revalidate_count++;
                    }
                    int total = exact_count + inexact_count;
                    exact_count = revalidate_count;
                    inexact_count = total - revalidate_count;
                    generated_guess_set.add(generated_guess);
                    gs_info.add(new GuessSetInfo(analyzer, exact_count, inexact_count));
                    //System.out.println(analyzer + " " + exact_count + " " + inexact_count + " " + generated_guess.toString());

                }
            }

        }
        generated_guess_set.add(generated_guess);
        gs_info.add(new GuessSetInfo(analyzer, exact_count, inexact_count));
        //System.out.println("Guessed in: " + analyzer);
        //System.out.println(generated_guess.toString());

    }

    public void _generate_guess() {
        do {
            analyzer = 0;
            guess_logs.clear();
            near_hit.clear();
            _4 = false;
            generated_guess_set.clear();
            gs_info.clear();
            guess_set();
        } while (analyzer >= (Math.pow(black, green)) * 2);
    }

    public int generate(int from, int to) {
        int y = 0;
        while (true) {
            int x = (int) (Math.random() * 10);
            if (x >= from && x <= to) {
                y = x;
                break;
            }
        }
        return y;
    }

}
