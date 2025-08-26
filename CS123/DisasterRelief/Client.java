import java.util.*;

public class Client {
    private static Random rand = new Random();

    public static void main(String[] args) throws Exception {
        // List<Location> scenario = createRandomScenario(10, 10, 100, 1000, 100000);
        List<Location> scenario = createSimpleScenario();
        System.out.println("scenario: " + scenario);
        
        double budget = 2000;
        allocateRelief(budget, scenario);
        Allocation allocation = allocateRelief(budget, scenario);
        printResult(allocation, budget);
    }

    /*
    This method returns the combination of locations that helps the most people, or
    if there are multiple combinations that helps the most people, then allocation that
    costs the least is returned. If there are multiple allocations that meet this requirement
    again, then the first allocation that met these requirements is returned
    @param: 
    budget: The maximum budget available to help people
    sites: All of the locations that require help
    return statement: The allocation that helps the most people, or costs the least if there is a 
    tie in most people helped, or is the first allocation to meet these requirements
    */
    public static Allocation allocateRelief(double budget, List<Location> sites) {
        // TODO: implement your method here
        //Set<Location> chosenSites = new HashSet<>();
        Allocation aloc = new Allocation();
        aloc = allocateRelief(budget, sites, aloc, 0);
        return aloc;
    }

    // TODO: add any of your own helper methods here
    
    public static Allocation allocateRelief(double budget, List<Location> sites, Allocation aloc, int j) {
        Allocation best = aloc;
        if(!sites.isEmpty()){
            for(int i = 0; i < sites.size(); i++){
                Location loc = sites.remove(i);
                Allocation result = allocateRelief(budget, sites, aloc.withLoc(loc), j+1);
                if(result.totalCost() <= budget){
                    if(best.totalPeople() < result.totalPeople() || (best.totalPeople() == result.totalPeople() && result.totalCost() < best.totalCost())){
                        best = result;
                    }
                }
                sites.add(i, loc);
            }
        }
        return best;
    }
    // PROVIDED HELPER METHODS - **DO NOT MODIFY ANYTHING BELOW THIS LINE!**

    public static void printResult(Allocation alloc, double budget) {
        System.out.println("Result: ");
        System.out.println("  " + alloc);
        System.out.println("  People helped: " + alloc.totalPeople());
        System.out.printf("  Cost: $%.2f\n", alloc.totalCost());
        System.out.printf("  Unused budget: $%.2f\n", (budget - alloc.totalCost()));
    }

    public static List<Location> createRandomScenario(int numLocs, int minPop, int maxPop, double minCostPer, double maxCostPer) {
        List<Location> result = new ArrayList<>();

        for (int i = 0; i < numLocs; i++) {
            int pop = rand.nextInt(minPop, maxPop + 1);
            double cost = rand.nextDouble(minCostPer, maxCostPer) * pop;
            result.add(new Location("Location #" + i, pop, round2(cost)));
        }

        return result;
    }

    public static List<Location> createSimpleScenario() {
        List<Location> result = new ArrayList<>();

        result.add(new Location("Location #1", 50, 500));
        result.add(new Location("Location #2", 100, 700));
        result.add(new Location("Location #3", 60, 1000));
        result.add(new Location("Location #4", 20, 1000));
        result.add(new Location("Location #5", 200, 900));

        return result;
    }    

    private static double round2(double num) {
        return Math.round(num * 100) / 100.0;
    }
}
