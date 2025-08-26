import java.util.*;

import java.awt.*;

//This class allows the user to create Mondrian Art
public class Mondrian {
    //The length of the canvas
    private int length;
    //The width of the canvas
    private int width;
    //The canvas represented in pixels
    private Color[][] pixels;
    //The colors used to fill in sections
    private Color[] colors;
    //The object used to create random bounds for the splits and random colors
    private Random rand;

    /*
     * This method calls the basicMondrian helper method which creates the Mondrian picture
     * @param: The canvas represented in pixels 
    */
    public void paintBasicMondrian(Color[][] pixels){
        this.length = pixels.length-1;
        this.width = pixels[0].length-1;
        this.pixels = pixels;
        Color[] color = {Color.RED, Color.YELLOW, Color.CYAN, Color.WHITE};
        this.colors = color;
        this.rand = new Random();
        basicMondrian(0, length, 0, width);
    }

    /*
     * This method creates a basic Mondrian art which splits a section of the page vertically
     * if its width is greater than 1/4 of the original width, splits it horizontally if the
     * height is greater than 1/4 of the original height, and both vertically and horizontally if
     * both the height and width are greater than 1/4 of the original
     * @params:
     * length1: The lower bound of the height
     * length2: The higher bound of the height
     * width1: The lower bound of the width
     * width2: The higher bound of the width
    */
    private void basicMondrian(int length1, int length2, int width1, int width2){
        Random rand = new Random();
        int lengthDif = length2 - length1 + 1;
        int widthDif = width2 - width1 + 1;
        if(lengthDif < length/4 && widthDif < width/4){
            int index = rand.nextInt(4);
            Color color = colors[index];
            for(int i = length1+1; i <= length2-1; i++){
                for(int j = width1+1; j <= width2-1; j++){
                    pixels[i][j] = color;
                }
            }
        }else if(length/4 <= lengthDif && width/4 <= widthDif){
            int newLength = rand.nextInt(length2 - length1-1) + length1 + 2;
            int newWidth = rand.nextInt(width2 - width1-1) + width1 + 2;
            basicMondrian(length1, newLength, width1, newWidth);
            basicMondrian(newLength, length2, width1, newWidth);
            basicMondrian(length1, newLength, newWidth, width2);
            basicMondrian(newLength, length2, newWidth, width2);
        }else if(length/4 <= lengthDif){
            int newLength = rand.nextInt(length2 - length1-1) + length1 + 2;
            basicMondrian(length1, newLength, width1, width2);
            basicMondrian(newLength, length2, width1, width2);
        }else if(width/4 <= widthDif){
            int newWidth = rand.nextInt(width2 - width1-1) + width1 + 2;
            basicMondrian(length1, length2, width1, newWidth);
            basicMondrian(length1, length2, newWidth, width2);
        }
    }

    /*
     * This method calls the complexMondrian helper method which creates the Mondrian picture
     * @param: The canvas represented in pixels 
    */
    public void paintComplexMondrian(Color[][] pixels){
        this.length = pixels.length-1;
        this.width = pixels[0].length-1;
        this.pixels = pixels;
        Color[] color = {Color.RED, Color.YELLOW, Color.CYAN, Color.WHITE};
        this.colors = color;
        this.rand = new Random();
        complexMondrian(0, length, 0, width);
    }

    /*
     * This method creates a complex Mondrian art which splits a section of the page vertically
     * a random amount of times if its width is greater than 1/4 of the original width, splits 
     * it horizontally a random amount of times if the height is greater than 1/4 of the original 
     * height, and both vertically and horizontally a different amount of times which are both random
     * if both the height and width are greater than 1/4 of the original
     * @params:
     * length1: The lower bound of the height
     * length2: The higher bound of the height
     * width1: The lower bound of the width
     * width2: The higher bound of the width
    */
    public void complexMondrian(int length1, int length2, int width1, int width2){
        int lengthDif = length2 - length1 + 1;
        int widthDif = width2 - width1 + 1;
        if(lengthDif < length/4 && widthDif < width/4){
            int index = rand.nextInt(4);
            Color color = colors[index];
            for(int i = length1+1; i < length2-1; i++){
                for(int j = width1+1; j < width2-1; j++){
                    pixels[i][j] = color;
                }
            }
        }else if(length/4 <= lengthDif && width/4 <= widthDif){
            int splitLength = rand.nextInt(1, 4);
            int splitWidth = rand.nextInt(1, 4);
            // int[]lengthSplits = new int[splitLength+2];
            // int[]widthSplits = new int[splitWidth+2];
            // lengthSplits[0] = length1;
            // widthSplits[0] = width1;
            // splitLength = lengthSplits.length;
            // splitWidth = widthSplits.length;
            // lengthSplits[splitLength-1] = length2;
            // widthSplits[splitWidth-1] = width2;
            // for(int i = 1; i < splitLength-1; i++){
            //     int newLength = rand.nextInt(length2 - length1 - 1) + length1;
            //     lengthSplits[i] = newLength;
            //     length1 = newLength;
            // }
            // for(int i = 1; i < splitWidth-1; i++){
            //     int newWidth = rand.nextInt(width2 - width1 - 1) + width1;
            //     widthSplits[i] = newWidth;
            //     width1 = newWidth;
            // }
            for(int i = 0; i < splitLength; i++){
                int newLength = rand.nextInt(length2 - length1 - 1) + length1;
                for(int j = 0; j < splitWidth; j++){
                    int newWidth = rand.nextInt(width2 - width1 - 1) + width1;
                    complexMondrian(length1, newLength, width1, newWidth);
                    width1 = newWidth;
                }
                complexMondrian(length1, length2, width1, width2);
                length1 = newLength;   
            }
            complexMondrian(length1, length2, width1, width2);
        }else if(length/4 <= lengthDif){
            int split = rand.nextInt(1, 4);
            for(int i = 0; i < split; i++){
                int newLength = rand.nextInt(length2 - length1 - 1) + length1;
                complexMondrian(length1, newLength, width1, width2);
                length1 = newLength;
            }
            complexMondrian(length1, length2, width1, width2);
        }else if(width/4 <= widthDif){
            int split = rand.nextInt(1, 4);
            for(int i = 0; i < split; i++){
                int newWidth = rand.nextInt(width2 - width1 - 1) + width1;
                complexMondrian(length1, length2, width1, newWidth);
                width1 = newWidth;
            }
            complexMondrian(length1, length2, width1, width2);
        }
    }
}
