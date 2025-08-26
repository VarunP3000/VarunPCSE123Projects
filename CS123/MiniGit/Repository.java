import java.util.*;
import java.text.SimpleDateFormat;

public class Repository {
    //This is the name of the Repository
    private String name;
    //This is the most recent commit of the Repository
    private Commit head;
    
    //This method creates a repository using a given name
    /*
     * @param: 
     * name: the name of the repository
    */
    public Repository(String name){
        this.name = name;
    }

    //This method returns the most recent commit of the repository if it exists or null 
    //if it doesn't
    /*
     * return statement: 
     * head: The most recent commit
    */
    public String getRepoHead(){
        if(head == null){
            return null;
        }else{
            return head.id;
        }
    }

    //This method returns the size of the repository 
    /*
     * return statement: 
     * head: The size of the repository
    */
    public int getRepoSize(){
        int size = 0;
        Commit temp = head;
        while(temp != null){
            size++;
            temp = temp.past;
        }
        return size;
    }

    //This method allows the user to commit to the repository
    /*
     * @param: 
     * message: The message the commit carries
     * return statement:
     * head: The id of the most recent commit
    */
    public String commit(String message){
        if(head == null){
            Commit commit = new Commit(message);
            head = commit;
        }else{
            Commit commit = new Commit(message, head);
            head = commit;
        }
        return head.id;
    }

    //This method checks if the repository has a commit that contains a certain id
    /*
     * @param: 
     * id: The id of the commit being looked for
     * return statement:
     * check: a boolean that is true if the commit with that id is there and false if it is not
    */
    public boolean contains(String id){
        boolean check = false;
        Commit temp = head;
        while(temp != null && !check){
            if(temp.id == id){
                check = true;
            }
            temp = temp.past;
        }
        return check;
    }

    //This method allows the user to remove a commit from the repository if it has a certain id
    /*
     * @param: 
     * targetId: The id of the commit being deleted
     * return statement:
     * check: a boolean that is true if the commit was removed and false if it is was not
     * If the boolean was false it means that the commit was not there
    */
    public boolean drop(String targetId){
        boolean check = false;
        Commit temp = head;
        if(temp!= null){
            if(temp.id.equals(targetId)){
                temp = temp.past;
                check = true;
            }else{
                while(temp.past != null && !check){
                    if(temp.past.id.equals(targetId)){
                        temp.past = temp.past.past;
                        check = true;
                    }else{
                        temp = temp.past;
                    }
                }
            }
        }
        head = temp;
        return check;
    }

    //This method returns the repository in the form of a string
    /*
     * return statement:
     * description: The repository in the form of a string which contains its current head
     * which is the most recent commit. If no head exists the method notifies the user that 
     * there have been no commits.
    */
    public String toString(){
        String description = name;
        if(head == null){
            description += " No commits ";
        }else{
            description += " - Current head: " + head.toString();
        }
        return description;
    }

    //This method allows the user to merge another repository with the current one, maintaing
    //The order of most recent to least recent, the most recent being the head
    //If the other repository is null this repository remains the same, and if this repository is 
    //null the other repository is moved in
    /*
     * @param: 
     * other: The other repository being merged into this one
    */
    public void synchronize(Repository other){
        if (other.head == null) {
            return;
        }
        if (head == null) {
            head = other.head;
            other.head = null;
            return;
        }
        Commit temp1 = head;
        Commit temp2 = other.head;
        if(temp1.timeStamp < temp2.timeStamp){
            while( temp1 != null){
                boolean check = true;
                while(check && temp2.past!=null){
                    if(temp1.timeStamp > temp2.past.timeStamp){
                        check = false;
                    }else{
                        temp2 = temp2.past;
                    }
                }
                Commit auxTemp = temp2.past;
                temp2.past = temp1;
                Commit auxTemp1 = temp1.past;
                temp1.past = auxTemp;
                temp2 = temp1;
                temp1 = auxTemp1;
            }
            head = other.head;
            other.head = null;
        }else if(temp2.timeStamp < temp1.timeStamp){
            while(temp2 != null){
                boolean check = true;
                while(check && temp1.past!=null){
                    if(temp2.timeStamp > temp1.past.timeStamp){
                        check = false;
                    }else{
                        temp1 = temp1.past;
                    }
                }
                Commit auxTemp = temp1.past;
                temp1.past = temp2;
                Commit auxTemp1 = temp2.past;
                temp2.past = auxTemp;
                temp1 = temp2;
                temp2 = auxTemp1;
            }
            other.head = null;
        }
    }

    //This method returns the history of the repository in the form of a string showing the most 
    //recent commit at the top and the least recent at the bottom of the list. The user is allowed
    //to choose how many previous commits they would like to see. If the amount of commits
    //they would like to see is greater than the list then the whole list would be returned.
    /*
     * @param: 
     * num: The amount of previous commits they would want to see
     * return statement:
     * history: The previous commits they chose to see all represented in the form of a string
     * with the most recent on top, and the least on the bottom
     * Exceptions: An illegal argument exception is throw if the amount of previous commits they
     * want to see is negative
    */
    public String getHistory(int num){
        if(num < 0){
            throw new IllegalArgumentException();
        }
        String history = "";
        Commit temp = head;
        int i = 0;
        while(temp != null && i < num){
            String message = temp.toString();
            history+=(message + "\n");
            temp = temp.past;
            i++;
        }
        return history;
    }

    /**
     * DO NOT MODIFY
     * A class that represents a single commit in the repository.
     * Commits are characterized by an identifier, a commit message,
     * and the time that the commit was made. A commit also stores
     * a reference to the immediately previous commit if it exists.
     *
     * Staff Note: You may notice that the comments in this 
     * class openly mention the fields of the class. This is fine 
     * because the fields of the Commit class are public. In general, 
     * be careful about revealing implementation details!
     */
    public class Commit {

        private static int currentCommitID;

        /**
         * The time, in milliseconds, at which this commit was created.
         */
        public final long timeStamp;

        /**
         * A unique identifier for this commit.
         */
        public final String id;

        /**
         * A message describing the changes made in this commit.
         */
        public final String message;

        /**
         * A reference to the previous commit, if it exists. Otherwise, null.
         */
        public Commit past;

        /**
         * Constructs a commit object. The unique identifier and timestamp
         * are automatically generated.
         * @param message A message describing the changes made in this commit.
         * @param past A reference to the commit made immediately before this
         *             commit.
         */
        public Commit(String message, Commit past) {
            this.id = "" + currentCommitID++;
            this.message = message;
            this.timeStamp = System.currentTimeMillis();
            this.past = past;
        }

        /**
         * Constructs a commit object with no previous commit. The unique
         * identifier and timestamp are automatically generated.
         * @param message A message describing the changes made in this commit.
         */
        public Commit(String message) {
            this(message, null);
        }

        /**
         * Returns a string representation of this commit. The string
         * representation consists of this commit's unique identifier,
         * timestamp, and message, in the following form:
         *      "[identifier] at [timestamp]: [message]"
         * @return The string representation of this collection.
         */
        @Override
        public String toString() {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date date = new Date(timeStamp);

            return id + " at " + formatter.format(date) + ": " + message;
        }

        /**
        * Resets the IDs of the commit nodes such that they reset to 0.
        * Primarily for testing purposes.
        */
        public static void resetIds() {
            Commit.currentCommitID = 0;
        }
    }
}