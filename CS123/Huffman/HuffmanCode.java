// Varun Panuganti
// 3/14/2024
// CSE 123 
// Huffman
// TA: Ido Avnon
import java.util.*;
import java.io.*;

//This class can compress and decompress a file, using Huffman Encoding
public class HuffmanCode {
    //This is the priority queue used to prioritize the characters in the text being encoded
    private Queue<HuffmanNode> pq;

    //This is a constructor method that creates a HuffmanCode object using an array of frequencies 
    //which describes how many of each character there is.
    /*
    @param:
    frequencies: an array of frequencies which describes how many of each character there is
    */
    public HuffmanCode(int[] frequencies){
        this.pq = new PriorityQueue<>();
        for(int i = 0; i < frequencies.length; i++){
            if(frequencies[i] != 0){
                HuffmanNode aux = new HuffmanNode(frequencies[i], i);
                pq.add(aux);
            }
        }
        while(1<pq.size()){
            HuffmanNode aux1 = pq.remove();
            HuffmanNode aux2 = pq.remove();
            int frequency = aux1.frequency + aux2.frequency;
            HuffmanNode newAux = new HuffmanNode(frequency, -1);
            newAux.left = aux1;
            newAux.right = aux2;
            pq.add(newAux);
        }
    }

    public String toString(){
        HuffmanNode root = pq.peek();
        return toStringHelper(root);
    }

    public String toStringHelper(HuffmanNode curr){
        if(curr.left != null && curr.right != null){
            return ("" + curr.char1 + "[" +  toStringHelper(curr.left) + "]" + "[" +  toStringHelper(curr.right) + "]");
        }
        return "" + curr.char1;
    }

    //This method creates a HuffmanCode object using previously constructed code
    //The code must be in standard format, which consists of multiple pairs of lines.
    //The first line in the pair is the ASCII code of the character, and the second line
    //is the Huffman encoding of the character. 
    /*
    @param:
    input: The scanner used to prompt the users input
    */
    public HuffmanCode(Scanner input){
        this.pq = new PriorityQueue<>();
        HuffmanNode root = new HuffmanNode(0, -1);
        int i = 0;
        while(input.hasNext()){
            System.out.println("iteration: " + i);
            int asciiValue = Integer.parseInt(input.nextLine());
            String code = input.nextLine();
            root = scannerConstructorHelper(asciiValue, code, root);
            System.out.println();
            i++;
        }
        pq.add(root);
    }

    //This is a helper method used by the HuffmanCode constructor which uses a scanner.
    //It navigates the HuffmanCode object to find the character based on it's Huffman encoding
    /*
    @param:
    asciivalue: The assciiValue of the character in the HuffmanNode being added
    code: The instructions left to navigate the HuffmanCode object
    curr: The current HuffmanNode being added in the HuffmanCode object 
    */
    private HuffmanNode scannerConstructorHelper(int asciiValue, String code, HuffmanNode curr){
        if(!code.isEmpty()){
            char choice = code.charAt(0);
            if(curr==null){
                curr = new HuffmanNode(0, -1);
            }
            if(choice == '0'){
                curr.left = scannerConstructorHelper(asciiValue, code.substring(1), curr.left);
            }else if(choice == '1'){
                curr.right = scannerConstructorHelper(asciiValue, code.substring(1), curr.right);
            }
        }else{
            //System.out.println("end char: " + asciiValue);
            curr = new HuffmanNode(0, asciiValue); 
        }
        return curr;
    }

    //This method prints the contents of the current HuffmanCode object to a given output stream
    //in standard format, which consists of multiple pairs of lines.
    //The first line in the pair is the ASCII code of the character, and the second line
    //is the Huffman encoding of the character. 
    /*
    @param:
    output: The output stream that the contents of the HuffmanCode object are being saved to
    */
    public void save(PrintStream output){
        HuffmanNode root = pq.peek();
        saveHelper("", root, output);
    }

    //This method is a helper method for save that helps print the contents of the current 
    //HuffmanCode object to a given output stream, in standard format.
    /*
    @param:
    soFar: The Huffman encoding of the character built so so far
    curr: The current HuffmanTreeNode so far in the navigation of the HuffmanCode object
    output: The output stream that the contents of the HuffmanCode object are being printed to
    */
    private void saveHelper(String soFar, HuffmanNode curr, PrintStream output){
        if(curr.right == null && curr.left == null){
            output.println(curr.char1);
            output.println(soFar);
        }else{
            if(curr.left!=null){
                saveHelper((soFar+"0"), curr.left, output);
            }
            if(curr.right!=null){
                saveHelper((soFar+"1"), curr.right, output);
            }
        }
    }

    //This method reads individual bits from the input stream and writes the corresponding 
    //characters to the output. It stops reading when the BitInputStream is empty.
    /*
    @param:
    input: The
    */
    public void translate(BitInputStream input, PrintStream output){
        HuffmanNode root = pq.peek();
        while(input.hasNextBit()){
            translateHelper(input, output, root);
        }
    }

    public void translateHelper(BitInputStream input, PrintStream output, HuffmanNode curr){
        if(curr.left==null && curr.right==null){
            output.write(curr.char1);
        }
        else{
            int i = input.nextBit();
            if(i == 0){
                translateHelper(input, output, curr.left);
            }else if(i == 1){
                translateHelper(input, output, curr.right);
            }
        }
    }

    private static class HuffmanNode implements Comparable<HuffmanNode>{
        public final int frequency;
        public final int char1;
        public HuffmanNode left;
        public HuffmanNode right;

        public HuffmanNode(int frequency, int char1){
            this.frequency = frequency;
            this.char1 = char1;
        }

        public int compareTo(HuffmanNode other){
            if (this.frequency < other.frequency) {
                return -1;
            } else if (this.frequency == other.frequency) {
                return 0;
            } else {
                return 1;
            }
        }
    }
}