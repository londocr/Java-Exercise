// There are 2N people a company is planning to interview. The cost of
// flying the i-th person to city A is costs[i][0], and the cost of flying
// the i-th person to city B is costs[i][1].
//
// Return the minimum cost to fly every person to a city such that exactly N
// people arrive in each city.
//
// Example:
//
// Input: [[10,20],[30,200],[400,50],[30,20]]
//
// Output: 110
//
// Explanation:
//
// The first person goes to city A for a cost of 10.
// The second person goes to city A for a cost of 30.
// The third person goes to city B for a cost of 50.
// The fourth person goes to city B for a cost of 20.
//
// The total minimum cost is 10 + 30 + 50 + 20 = 110 to have half the people
// interviewing in each city.

import java.util.ArrayList;

public class TestMain {

    private int [][] costs;
    private int nPeople;

    public TestMain(int [][] costs) throws Exception {
        if (costs.length % 2 == 1) {
            throw new Exception("There are no even amount of people");
        }
        this.costs = costs;
        this.nPeople = costs.length / 2;
    }

    public static void main(String[] args) {
        System.out.println("*****  Starting  *****");

        // First test
        int [][] arr0 = { {10,20}, {30,200}};
        try {
            TestMain test = new TestMain(arr0);
            int res = test.calculateCost();
            if (res != 50) {
                System.out.println("Error on result 0, is not equal to 50. Result: " + res);
            } else {
                System.out.println("Result 0: " + res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        int [][] arr1 = { {10,20}, {30,200}, {400,50}, {30,20} };
        try {
            TestMain test = new TestMain(arr1);
            int res = test.calculateCost();
            if (res != 110) {
                System.out.println("Error on result 1, is not equal to 110. Result: " + res);
            } else {
                System.out.println("Result 1: " + res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        int [][] arr2 = { {1000,20}, {3000,20}, {40,5000}, {20,2000} };
        try {
            TestMain test = new TestMain(arr2);
            int res = test.calculateCost();
            if (res != 100) {
                System.out.println("Error on result 2, is not equal to 100. Result: " + res);
            } else {
                System.out.println("Result 2: " + res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("*****  Finished  *****");
    }

    public int calculateCost() {
        // I'll find every posible combination and then search for the minimum cost
        ArrayList<Integer> allCosts = calculateCostRec(0, "", "", 0);
        int minimumCost = Integer.MAX_VALUE;
        for (int i = 0; i < allCosts.size(); i++) {
            minimumCost = Math.min(minimumCost, allCosts.get(i));
        }
        return minimumCost;
    }

    private ArrayList<Integer> calculateCostRec(int index, String cityA, String cityB, int totalCost) {

        if (index == this.costs.length) {
            if (cityA.length() == cityB.length()) {
                ArrayList<Integer> arrList = new ArrayList<Integer>();
                arrList.add(totalCost);
                return arrList;
            } else {
                ArrayList<Integer> arrList = new ArrayList<Integer>();
                arrList.add(Integer.MAX_VALUE);
                return arrList;
            }
        } else {
            if (cityA.length() < this.nPeople && cityB.length() < this.nPeople) {
                ArrayList<Integer> arrayListA = calculateCostRec(index + 1, cityA + index, cityB, totalCost + this.costs[index][0]);
                ArrayList<Integer> arrayListB = calculateCostRec(index + 1, cityA, cityB + index, totalCost + this.costs[index][1]);
                arrayListA.addAll(arrayListB);
                return arrayListA;
            } else if (cityA.length() < this.nPeople) {
                return calculateCostRec(index + 1, cityA + index, cityB, totalCost + this.costs[index][0]);
            } else {
                // cityB.length() < this.nPeople
                return calculateCostRec(index + 1, cityA, cityB + index, totalCost + this.costs[index][1]);
            }
        }
    }

}
