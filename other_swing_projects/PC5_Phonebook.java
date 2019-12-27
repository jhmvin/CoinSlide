package PracticeProblems;

import java.util.Scanner;

public class PC5_Phonebook {

    public static void main(String[] args) {
        //instance
        Scanner scanme = new Scanner(System.in);

        //global var
        String myChoice;
        String prefix = "+639";
        String[] myNumbers = new String[50]; //Max of 50 Contacts only
        String[] myNames = new String[50]; //Max of 50 Names only
        int entryCount = 0;
        int retrieveCount = 0;
        boolean number_ok;

        //used Predefined Numbers
        String[] definedNames = {"Melvin", "Cheslie", "Bella", "Kolleen", "Pillows"};
        String[] definedNumbers = {"+639368955866", "+639068670618", "+639265680427", "+639162504517", "+639267854896"};

        //-------------------
        String find_name;
        String find_num;
        String update_choice;
        boolean name_found;

        //Filling Arrays
        for (int x = 0; x < 50; x++) {
            myNumbers[x] = "";
            myNames[x] = "";
        }

        do {
            //Primary Key Check
            entryCount = 0; //reset
            for (int x = 0; x < 50; x++) {
                if (myNames[x].isEmpty()) {
                    //do nothing
                } else {
                    entryCount++;
                }
            }

            System.out.println("------------");
            System.out.println("►Phonebook◄");
            System.out.println("------------");
            System.out.println("[C] Create Contact");
            System.out.println("[V] View Contacts");
            System.out.println("[E] Edit Contacts");
            System.out.println("[D] Delete Contact");
            System.out.println("[Q] Quit");

            System.out.print("Your Choice: ");
            myChoice = scanme.nextLine();

            myChoice = myChoice.toLowerCase();
//-----------------------Create Block Start
            if (myChoice.equals("c")) {

                System.out.println("►Create Contact");
                for (int x = 0; x < 50; x++) {
                    if (myNames[x].isEmpty()) {
                        System.out.print("Contact Name: ");
                        myNames[x] = scanme.nextLine();

                        do {
                            number_ok = false;

                            System.out.print("Contact Number: ");
                            myNumbers[x] = scanme.nextLine();

                            if (myNumbers[x].length() == 9) {
                                String temp_num;
                                temp_num = myNumbers[x];
                                int digit_count = 0;

                                for (int y = 0; y < myNumbers[x].length(); y++) {

                                    if (Character.isDigit(temp_num.charAt(y))) {
                                        digit_count++;
                                    } else {
                                        //do Nothing

                                    }
                                }

                                if (digit_count == myNumbers[x].length()) {
                                    number_ok = true;
                                } else {
                                    System.err.println("Invalid Character Found, Please Enter Digits Only");

                                }
                            } else {
                                System.err.println("Contact Number Should Be 9 Digits Only");
                            }

                            myNumbers[x] = prefix.concat(myNumbers[x]);

                        } while (number_ok == false);

                        break;

                    }

                }

                if (entryCount == 50) {
                    System.err.println("Contact List is Full !!!");

                }

            } //Create Block End
            //-----------------------Create Block End
            //----------------------Retrieve Block Start
            else if (myChoice.equals("v")) {

                System.out.println("►View Contacts");
                retrieveCount = 0;

                if (entryCount == 0) {
                    System.err.println("Contact List is Empty !!!");
                } else {
                    System.out.println("No.\tName\t\tMobile Number");
                    for (int x = 0; x < 50; x++) {
                        if (myNames[x].isEmpty()) {
                            //do Nothing
                        } else {
                            retrieveCount++;
                            System.out.print(retrieveCount);
                            System.out.print("\t");
                            System.out.print(myNames[x]);
                            System.out.print("\t\t");
                            System.out.print(myNumbers[x]);
                            System.out.println("");

                        }

                    }
                }

            } //----------------------Retrieve Block End
            //--------------------//Update Block
            else if (myChoice.equals("e")) {

                System.out.println("►Edit Contacts");
                name_found = false;

                System.out.print("Search Name: ");
                find_name = scanme.nextLine();

                for (int x = 0; x < 50; x++) {
                    if (myNames[x].equalsIgnoreCase(find_name)) {
                        System.out.print("Enter New Name: ");
                        myNames[x] = scanme.nextLine();

                        do {
                            number_ok = false;

                            System.out.print("New Contact Number: ");
                            myNumbers[x] = scanme.nextLine();

                            if (myNumbers[x].length() == 9) {
                                String temp_num;
                                temp_num = myNumbers[x];
                                int digit_count = 0;

                                for (int y = 0; y < myNumbers[x].length(); y++) {

                                    if (Character.isDigit(temp_num.charAt(y))) {
                                        digit_count++;
                                    } else {
                                        //do Nothing

                                    }
                                }

                                if (digit_count == myNumbers[x].length()) {
                                    number_ok = true;
                                } else {
                                    System.err.println("Invalid Character Found, Please Enter Digits Only");

                                }
                            } else {
                                System.err.println("Contact Number Should Be 9 Digits Only");
                            }

                            myNumbers[x] = prefix.concat(myNumbers[x]);

                        } while (number_ok == false);

                        name_found = true;
                        break;
                    } else {
                        //do nothing
                    }

                }

                if (name_found == false) {
                    System.err.println("Contact Name Not Found !\n");

                }

            } //--------------------//Update Block END
            //-------------Delete Block
            else if (myChoice.equals("d")) {

                if (entryCount != 0) {

                    name_found = false;

                    System.out.println("►Delete Contact");
                    System.out.print("Enter Contact Name: ");
                    find_name = scanme.nextLine();

                    for (int x = 0; x < 50; x++) {

                        if (find_name.equalsIgnoreCase(myNames[x])) {

                            myNames[x] = "";
                            myNumbers[x] = "";

                            System.err.println("Contact Deleted");
                            name_found = true;
                            break;
                        } else {
                            //do nothing
                        }

                    }

                    if (name_found == false) {
                        System.err.println("Contact Not Found !");
                    }

                } else {
                    System.err.println("Contact List is Empty !!!");
                }
            } //--------------Delete Block End
            else if (myChoice.equals("q")) {

            } else if (myChoice.equals("m")) {

                for (int x = 0; x < 5; x++) {

                    myNames[x] = definedNames[x];
                    myNumbers[x] = definedNumbers[x];

                }
                System.err.println("Special Code: Load Predefined Contacts");

            } else if (myChoice.equals("n")) {

                //Filling Arrays
                for (int x = 0; x < 50; x++) {
                    myNumbers[x] = "";
                    myNames[x] = "";
                }

                System.err.println("Special Code: Delete All Contacts");

            } else {
                System.err.println("Invalid Choice !!!");
            }
        } while (!myChoice.equals("q")); //End of do while
    } //end Main

}// end Class
