import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class FileSorter {

    public static void main(String[] args) throws IOException {
        String inputFile = "resources/leaderboards/input.txt";
        String outputFile = "resources/leaderboards/input.txt";
        String name;

        // Reading

        //public void LeaderboardReader(){
        FileReader fileReader = new FileReader(inputFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String inputLine;
        String boardID = bufferedReader.readLine();
        //GameBoard.setBoardID(boardID);
        List<String> lineList = new ArrayList<String>();

        while ((inputLine = bufferedReader.readLine()) != null) {
            lineList.add(inputLine);
        }

        fileReader.close();

        // Get each players info
        List<Profile> profileList = new ArrayList<Profile>();
        for(String eachLine : lineList){
            String[] eachWord = eachLine.split(" ");
            name = eachWord[0];
            int wins = Integer.parseInt(eachWord[1]);
            int losses = Integer.parseInt(eachWord[2]);
            profileList.add(new Profile(name,wins,losses));
            eachWord=null;
        }

        // Sorting
        Collections.sort(profileList);
        for(Profile profileOutput:profileList){
            System.out.println(profileOutput.name+" "+profileOutput.wins+" "+profileOutput.losses);
        }

        // Writing
        FileWriter fileWriter = new FileWriter(outputFile);
        PrintWriter out = new PrintWriter(fileWriter);
        out.println(boardID);
        for(Profile profileOutput:profileList){
            out.println(profileOutput.name+" "+profileOutput.wins+" "+profileOutput.losses);
        }

        out.flush();
        out.close();
        fileWriter.close();
    }
}

class Profile implements Comparable<Profile> {
    int wins;
    int losses;
    String name;

    Profile(String name, int wins, int losses){
        this.name=name;
        this.wins=wins;
        this.losses=losses;
    }

    @Override
    public int compareTo(Profile us) {
        if(this.wins<us.wins){
            return 1;
        }
        else if(this.wins==us.wins){
            return 0;
        }
        else{
            return -1;
        }
    }
}
