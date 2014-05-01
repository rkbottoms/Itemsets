import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * User: Ryan Bottoms
 * Date: 4/28/2014
 * Time: 8:15 PM
 */
public class Itemset {
    public static void main(String[] args) throws IOException {
        final int numDocuments = 210519;
        final int numWords = 5000;
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
        while ((line = br.readLine()) != null) {
            //splitting up each line by comma
            String[] parts = line.split(",");
            for (int i = 2; i < parts.length; i++) {
                //splitting up word indices and counts
                String[] temp = parts[i].split(":");
                //convert word index to integer
                index = Integer.parseInt(temp[0]);
                //set the bit value of the word in the song to 1
                bitMatrix[index - 1].set(count);
                //increment the counts of words found in each document
                frequencies[index - 1]++;
            }
            count++;
        }
        br.close();
        //structure to keep track of indices of words that satisfy threshold
        List<Integer> indices = new ArrayList<>();
        //ignore the first 100 words
        //the first 100 words are assumed to be stop words
        //find the number of words that have occurrences about the threshold
        for (int j = 100; j < frequencies.length; j++) {
            if (frequencies[j] > (double) numDocuments * threshold) {
                //add the index of the word
                indices.add(j);
                System.out.println(j);
            }
        }
        System.out.println("The number of single words that appear in at least 4% of lyrics is " + indices.size() + ".");
        List<Pair> pairList = new ArrayList<>();
        int pairIndex1, pairIndex2;
        for(int i = 0; i < indices.size() - 1; i++) {
            for(int j = i + 1; j < indices.size(); j++) {
                pairIndex1 = indices.get(i);
                pairIndex2 = indices.get(j);

                if(checkPairs(bitMatrix, pairIndex1, pairIndex2)) {
                    //create pair object
                    //store object in array
                    Pair<Integer, Integer> p = Pair.createPair(pairIndex1, pairIndex2);
                    System.out.println(p);
                    pairList.add(p);
                }
            }
        }
        System.out.println("The number of pairs that appear in at least 4% of lyrics is " + pairList.size() + ".");
        //create array for indices that compose the pairs
        List pairIndices = removeDuplicates(pairList);
        List< Triple<Integer, Integer, Integer> > tripleList = new ArrayList<>();
        int tripleIndex1, tripleIndex2, tripleIndex3;
        for(int i = 0; i < pairList.size() - 2; i++) {
            for(int j = i + 1; j < pairList.size() - 1; j++) {
                for(int k = j + 1; k < pairList.size(); k++) {
                    tripleIndex1 = (int) pairIndices.get(i);
                    tripleIndex2 = (int) pairIndices.get(j);
                    tripleIndex3 = (int) pairIndices.get(k);

                    if(checkTriples(bitMatrix,tripleIndex1, tripleIndex2, tripleIndex3)) {
                        //create triple object
                        //store object in array
                        Triple<Integer,Integer,Integer> t = Triple.createTriple(tripleIndex1, tripleIndex2, tripleIndex3);
                        System.out.println(t);
                        tripleList.add(t);
                    }
                }
            }
        }
        System.out.println("The number of triples that appear in at least 4% of lyrics is " + tripleList.size() + ".");
    }
    private static boolean checkPairs(BitSet[] bitMatrix, int val1, int val2) {
        int count = 0;
        for(int i = 0; i < 210519; i++){
            if(bitMatrix[val1].get(i) && bitMatrix[val2].get(i)) {
                count++;
            }
        }
        return count > 210519 * .04;
    }
    private static boolean checkTriples(BitSet[] bitMatrix, int val1, int val2, int val3) {
        int count = 0;
        for(int i = 0; i < 210519; i++) {
            if(bitMatrix[val1].get(i) && bitMatrix[val2].get(i) && bitMatrix[val3].get(i)) {
                count++;
            }
        }

        return count > (double) 210519 * .04;
    }

    public static List removeDuplicates(List<Pair> list) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (Pair pair : list) {
            if (set.add((Integer) pair.getElement1())) {
                result.add((Integer) pair.getElement1());
            }
            if (set.add((Integer) pair.getElement2())) {
                result.add((Integer) pair.getElement2());
            }
        }
        return result;
    }
}
