package ru.job4j.algo.greedy;

public class GasStation {
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int tank = 0;
        int start = 0;
        int numGasStations = gas.length;
        int numTransportationCosts = cost.length;
        for (int i = 0; i < numGasStations; i++) {
            int index = i % numTransportationCosts;
            tank += gas[index] - cost[index];
            if (tank < 0) {
                if (start == numTransportationCosts - 1) {
                    break;
                }
                i = start++;
                numGasStations = numTransportationCosts + start;
                tank = 0;
            }
        }
        return tank >= 0 ? start : -1;
    }

    public static void main(String[] args) {
        Runtime run = Runtime.getRuntime();
        long memoryBefore = run.totalMemory() - run.freeMemory();
        int[] gas = {1, 2, 3, 4, 5};
        int[] cost = {3, 4, 5, 1, 2};
        int result = canCompleteCircuit(gas, cost);
        long memoryAfter = run.totalMemory() - run.freeMemory();
        System.out.println("Использовано памяти: " + (memoryAfter - memoryBefore) + " байт.");
        System.out.print(result > 0 ? "Индекс стартовой станции: " + result : "Проехать круг невозможно");
    }
}
