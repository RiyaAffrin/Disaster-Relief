/*Riya Affrin
05/15/24
CSE 123 BG
TA: Eric Bae
P2 Disaster Relief
 */

 import java.util.*;

 public class Client {
     private static Random rand = new Random();
 
     public static void main(String[] args) throws Exception {
         // List<Region> scenario = createRandomScenario(10, 10, 100, 1000, 100000);
         List<Region> scenario = createSimpleScenario();
         System.out.println(scenario);
         
         double budget = 2000;
         Allocation allocation = allocateRelief(budget, scenario);
         printResult(allocation, budget);
     }
 
     //Here is the allocateRelief method. The method passes in a double named budget, a list of
     //regions called sites. This Method returns the best allocation which aid should be given. 
     public static Allocation allocateRelief(double budget, List<Region> sites) {
         // TODO: implement your method here
         Allocation bestLocation = new Allocation();
         Set<Allocation> allocations = generateOptions(budget, sites);
         int maxPop = 0;
 
         for(Allocation checking : allocations){
             int alSize = checking.totalPeople();
 
             if(alSize > maxPop){
                 maxPop = alSize;
                 bestLocation = checking;
 
             }
         }
         return bestLocation;
     }
     
     
     // TODO: add any of your own helper methods here
 
     //generateOptions is a helper method to allocateRelief. This method passes in
     //double named budget, a list of regions called regions.
     //This method is returing a set of allocations 
     //The purpose of this method is to produce a set of allocations containing their budget and
     //and their population
     
     private static Set<Allocation> generateOptions(double budget, List<Region> regions){
 
         Set<Allocation> allocations = new HashSet<>();
         optionsHelper(new Allocation(), budget, regions, allocations);
         return allocations;
     }
     
     //This is a helper method to generateOptions. It takes in more parameters such as
     //Allocation currentAllocation, double budgetRemain, List<Region> regionRemain, 
     //and Set<Allocation> valid). This method also does not return anything as it is void.
 
     private static void optionsHelper(Allocation currentAllocation, double budgetRemain,
     List<Region> regionRemain, Set<Allocation> valid) {
 
         if (regionRemain.isEmpty()) {
             //Base case
             if (budgetRemain >= 0) {
                 valid.add(currentAllocation);
             }
             //Recursive case 
         }else{
             for (int i = 0; i< regionRemain.size(); i++) {
 
                 Region curr = regionRemain.get(i);
                 double currentCost = curr.getCost(currentAllocation.size());
 
                 if (budgetRemain >= currentCost) {
                     List<Region> regionList = new ArrayList<>(regionRemain);
                     regionList.remove(i);
                     Allocation newAllocation = currentAllocation.withRegion(curr);
                     optionsHelper(newAllocation, budgetRemain - currentCost, regionList, valid);
 
                 }
             }
             List<Region> regionList= new ArrayList<>(regionRemain.subList(1,regionRemain.size()));
             optionsHelper(currentAllocation, budgetRemain, regionList, valid);
         }
     }
 
     ///////////////////////////////////////////////////////////////////////////
     // PROVIDED HELPER METHODS - **DO NOT MODIFY ANYTHING BELOW THIS LINE!** //
     ///////////////////////////////////////////////////////////////////////////
     
     public static void printAllocations(Set<Allocation> allocations) {
         System.out.println("All Allocations:");
         for (Allocation a : allocations) {
             System.out.println("  " + a);
         }
     }
 
     public static void printResult(Allocation alloc, double budget) {
         System.out.println("Result: ");
         System.out.println("  " + alloc);
         System.out.println("  People helped: " + alloc.totalPeople());
         System.out.printf("  Cost: $%.2f\n", alloc.totalCost());
         System.out.printf("  Unused budget: $%.2f\n", (budget - alloc.totalCost()));
     }
 
     public static List<Region> createRandomScenario(int numLocs, int minPop, int maxPop,
                                                     double minCostPer, double maxCostPer) {
         List<Region> result = new ArrayList<>();
 
         for (int i = 0; i < numLocs; i++) {
             int pop = rand.nextInt(minPop, maxPop + 1);
             double cost = rand.nextDouble(minCostPer, maxCostPer) * pop;
             result.add(new Region("Region #" + i, pop, round2(cost)));
         }
 
         return result;
     }
 
     public static List<Region> createSimpleScenario() {
         List<Region> result = new ArrayList<>();
 
         result.add(new Region("Region #1", 50, 500));
         result.add(new Region("Region #2", 100, 700));
         result.add(new Region("Region #3", 60, 1000));
         result.add(new Region("Region #4", 20, 1000));
         result.add(new Region("Region #5", 200, 900));
 
         return result;
     }    
 
     private static double round2(double num) {
         return Math.round(num * 100) / 100.0;
     }
 }