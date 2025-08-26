import java.util.*;

public class InvertedIndex {
    public static void main(String[] args) {
        List<String> docs = new ArrayList<>();
        docs.add("Raiders of the Lost Ark");
        docs.add("The Temple of Doom");
        docs.add("The Last Crusade");
        
        Map<String, Set<String>> result = createIndex(docs);
        System.out.println(docs);
        System.out.println();
        System.out.println(result);
    }

    // TODO: Write and document your createIndex method here
    public static Map<String, Set<String>> createIndex(List<String> docs){
        Map<String, Set<String>> result = new HashMap<String, Set<String>>();
        for(String key : docs){
            for (String word1 : key.split(" ")) {
                String word = word1.toLowerCase();
                if(!result.containsKey(word)){
                    Set<String> aux = new HashSet<>();
                    aux.add(key);
                    result.put(word, aux);
                }else{
                    result.get(word).add(key);
                }
                //System.out.println(result);
            }
            //System.out.println();
        }
        return result;
    }
}
