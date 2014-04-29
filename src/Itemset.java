import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;

/**
 * User: Ryan Bottoms
 * Date: 4/28/2014
 * Time: 8:15 PM
 */
public class Itemset {
    public static void main (String[] args) throws IOException {
        int numDocuments = 210519;
        int numWords = 5000;
        double threshold = .04;
        int[] frequencies = new int[numWords];
        //create characteristic matrix
        //Initially, matrix is all 0's
        BitSet[] bitMatrix = new BitSet[numWords];
        for (int i = 0; i < numWords; i++) {
            bitMatrix[i] = new BitSet(numDocuments);
        }
        //parse data set and create matrix that
        //describes the occurrence of each word in each song
        File file = new File("mxm_dataset_train.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        int index;
        int count = 0;
        while((line = br.readLine()) != null) {
            //splitting up each line by comma
            String[] parts = line.split(",");
            for (int i = 2; i < parts.length; i++) {
                //splitting up word indices and counts
                String[] temp = parts[i].split(":");
                //convert word index to integer
                index = Integer.parseInt(temp[0]);
                //set the bit value of the word in the song to 1
                bitMatrix[index-1].set(count);
                //increment the counts of words found in each document
                frequencies[index-1]++;
            }
            count++;
        }
        //structure to keep track of indices of words that satisfy threshold
        ArrayList<Integer> indices = new ArrayList<>();
        //keep track of the number of words above threshold
        int wordAboveThreshold = 0;
        //ignore the first 100 words
        //the first 100 words are assumed to be stop words
        //find the number of words that have occurrences about the threshold
        for (int j = 100; j < frequencies.length; j++) {
            if (frequencies[j] > (double) numDocuments * threshold) {
                wordAboveThreshold++;
                indices.add(j);
            }
        }
        System.out.println("The number of single words that appear in at least 4% of lyrics is " + wordAboveThreshold + ".");

        //ignore words that don't appear in at least 4% of lyrics
        //find number pairs that appear in at least 4% of lyrics

    }
}
