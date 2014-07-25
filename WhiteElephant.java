import java.util.*;
import java.math.*;
import java.io.*;
public class WhiteElephant{
    public static void main (String [] args) throws Exception{

        // there is no argument as a file input
        if(args.length == 0){
            System.out.println("Proper usage: java WhiteElephant file");
            System.exit(0);
        }

        WhiteElephant we = new WhiteElephant(args[0]);
        we.assignRecipients();
    }

    private String file;
    //initialize the instance

    public WhiteElephant(String filename){
        file = filename;
    }

    public void assignRecipients() throws Exception{
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = null;
        int count = 1;
        while ((line = reader.readLine()) != null) {
            System.out.println("Case " + count++ + "\nOriginal list: " + line);

            //detect and assign the participants here
            String[] recipients = generateAssignments(parse(line));
            printRecipients("Recipients list: ",recipients);
            System.out.println();
        }
        reader.close();
    }

    //turn string into string array with trimmed names
    public String[] parse(String line){
        if(line == null || line.length() == 0){
            return null;
        }
        String[] rawNames = line.replaceAll("^[,\\s]*", "").split("\\s*,[,\\s]*");
        return rawNames;
    }

    public String[] generateAssignments(String[] participants){
        if(participants == null){
            return null;
        }

        //get the number of participants; the type of length of an array is int
        int len = participants.length;
        String[] recipients = Arrays.copyOf(participants, len);

        //if the number of participants are less than two, the result is easy to figure out;
        if(len <= 2){
            if(len <= 1){
                System.out.println("There is " + len + " person in the game. No one thinks this would be fun.");
            }else{
                System.out.println("There are " + len + " people in the game. No one thinks this would be fun.");
            }
            return null;
        }

        //sort the names accroding alphabetically, if we found the duplicate name, we must deal with it
        Arrays.sort(recipients);

        //duplicate participants names solution: update names with different indices after them
        updateNames(recipients, len);

        //print sorted participants
        printRecipients("Formal list: ",recipients);

        // start from the last person, randomly select a person to assign to him/her
        for(int i = len - 1; i >= 1; i--){
            // generate a random number as current person's recipient
            int recipientIndex = (int)(Math.random() * i);
            String temp = recipients[recipientIndex];
            recipients[recipientIndex] = recipients[i];
            recipients[i] = temp;
        }
        return recipients;
    }

    //when duplicate name is found, add an index at its tail
    public void updateNames(String[] names, int len){
        boolean hasDuplicate = false;
        for(int i = 1; i < len; i++){
            int count = 1;
            int first = i - 1;
            while(i < len && names[i].equals(names[first])){
                names[i] = names[i] + "_" + count;
                count++;
                i++;
                hasDuplicate = true;
            }
            if(hasDuplicate){
                System.out.println("We have the same name here: " + names[i - count]);
                hasDuplicate = false;
            }
        }
    }

    //print the names of the string[], with prefix description
    public void printRecipients(String prefix, String[] recipients){
        if(recipients != null){
            System.out.print(prefix);
            int len = recipients.length;
            for(int i = 0; i < len - 1; i++){
                System.out.print(recipients[i] + ", ");
            }
            System.out.println(recipients[len - 1]);
        }
    }
}
