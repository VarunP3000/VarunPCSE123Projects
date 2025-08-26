// TODO: Implement your QuizTree class

import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

public class QuizTree {
    private QuizTreeNode root;

    public QuizTree(Scanner InputFile){
        this.root = quizTreeHelper(InputFile);
    }

    private QuizTreeNode quizTreeHelper(Scanner input){
        String node = input.nextLine();
        QuizTreeNode root = createNode(node);
        if(!node.contains("END:")){
            root.leftChoice = quizTreeHelper(input);
            root.rightChoice = quizTreeHelper(input);
        }else{
            root = createNode(node.replace("END:", ""));
        }
        return root;
    }

    public void export(PrintStream outputFile){
        exportHelper(root, outputFile);
    }

    private void exportHelper(QuizTreeNode root, PrintStream outputFile){
        if(root!=null){
            outputFile.println(root.toString());
            exportHelper(root.leftChoice, outputFile);
            exportHelper(root.rightChoice, outputFile);
        }
    }

    public void addQuestion(String toReplace, String choices, String leftResult, String rightResult){
        root = addQuestionHelper(root, toReplace, choices, leftResult, rightResult);
    }

    private QuizTreeNode addQuestionHelper(QuizTreeNode root, String toReplace, String choices, String leftResult, String rightResult){
        if(root.rightChoice == null && root.leftChoice == null){
            String description = root.description;
            if(description.equals(toReplace)){
                QuizTreeNode replacement = createNode(choices);
                replacement.leftChoice = createNode(leftResult);
                replacement.rightChoice = createNode(rightResult);
                return replacement;
            }
        }else{
            root.leftChoice = addQuestionHelper(root.leftChoice, toReplace, choices, leftResult, rightResult);
            root.rightChoice = addQuestionHelper(root.rightChoice, toReplace, choices, leftResult, rightResult);
        }
        return root;
    }

    public void takeQuiz(Scanner console){
        takeQuizHelper(console, root, 0);
    }

    private void takeQuizHelper(Scanner console, QuizTreeNode root, int score){
        score+=root.score;
        System.out.println("root.leftChoice != null && root.rightChoice != null:" + root.leftChoice != null && root.rightChoice != null);
        if(root.leftChoice != null && root.rightChoice != null){
            String description = root.description;
            String[] choices = description.split("/");
            String choice1 = choices[0];
            String choice2 = choices[1];
            System.out.println("Do you prefer " + choice1 + " or " + choice2 + "?");
            String answer = console.nextLine();
            if(answer.equals(choice1)){
                takeQuizHelper(console, root.leftChoice, score);
            }else if(answer.equals(choice2)){
                takeQuizHelper(console, root.rightChoice, score);
            }
        }else{
            System.out.println("Your result is: " + root.description);
            System.out.println("Your score is: " + root.score);
        }
    }

    public void creativeExtension(Scanner console){
        System.out.print("What is your cutoff?");
        String cutoff = console.nextLine();
        int cutOff = Integer.parseInt(cutoff);
        Random rand = new Random();
        creativeExtensionHelper(console, root, 0, 1, cutOff, rand);
    }

    private void creativeExtensionHelper(Scanner console, QuizTreeNode root, int score, int curr, int cutOff, Random rand){
        if(root.leftChoice != null && root.rightChoice != null){
            String description = root.description;
            String[] choices = description.split("/");
            String choice1 = choices[0];
            String choice2 = choices[1];
            String answer = "";
            if(curr == cutOff){
                System.out.println("You have reached the cutoff");
            }
            if(curr<cutOff){
                System.out.println("Do you prefer " + choice1 + " or " + choice2 + "?");
                answer = console.nextLine();
            }else{
                int choice = rand.nextInt(2);
                answer = choices[choice];
            }
            if(answer.equals(choice1)){
                creativeExtensionHelper(console, root.leftChoice, score, curr+1, cutOff, rand);
            }else if(answer.equals(choice2)){
                creativeExtensionHelper(console, root.rightChoice, score, curr+1, cutOff, rand);
            }
        }else{
            System.out.println("Your result is: " + root.description);
            System.out.println("Your score is: " + root.score);
        }
    }

    public String toString(){
        return toStringHelper(root);
    }

    public String toStringHelper(QuizTreeNode curr){
        if(curr.leftChoice != null && curr.rightChoice != null){
            return (curr.toString() + "[" +  toStringHelper(curr.leftChoice) + "]" + "[" +  toStringHelper(curr.rightChoice) + "]");
        }
        return curr.toString();
    }

    public QuizTreeNode createNode(String choices){
        String[] split = choices.split("-");
        String result = split[0];
        String stringScore = split[1];
        int score = Integer.parseInt(stringScore);
        QuizTreeNode node = new QuizTreeNode(score, result);
        return node;
    }

    // PROVIDED
    // Returns the given percent rounded to two decimal places.
    private double roundTwoPlaces(double percent) {
        return (double) Math.round(percent * 100) / 100;
    }

    private static class QuizTreeNode{
        public QuizTreeNode leftChoice;
        public QuizTreeNode rightChoice;
        final public String description;
        final public int score;

        public QuizTreeNode(int score, String description){
            this.score = score;
            this.description = description;
        }

        public String toString(){
            if(rightChoice == null && leftChoice == null){
                return ("END: " + description+"-"+score);
            }
            return (description+"-"+score);
        }
    }
}
