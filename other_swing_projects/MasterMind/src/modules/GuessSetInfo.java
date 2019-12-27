/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules;

public class GuessSetInfo {

    public int count, exact, in;

    public GuessSetInfo(int c, int e, int i) {
        this.count = c;
        this.exact = e;
        this.in = i;
    }

    public String toString() {
        return "Round: " + count + "\nExact: " + exact + "\nInexact: " + in;
    }

    public String toConsole() {
        return "Round: " + count + "\tE: " + exact + "\tI: " + in;
    }
}
