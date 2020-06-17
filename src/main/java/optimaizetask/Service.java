package optimaizetask;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Service {
    
    private static Service instance = null;
    
    private final Map<String, Integer> storeMap = new HashMap<>();
    
    
    public static Service getInstance() {
        
        if(instance == null) {
            instance = new Service();
        }
        
        return instance;
    }
    
    
    public void printHelpDescription() {
        
        System.out.println();
        System.out.println("-stats\tShow statistics");
        System.out.println("-help\tPrint help");
        System.out.println("-quit\tExit application");
        System.out.println();
    }
    
    
    public void saveInputString(String inputString) {
        
        if(inputString == null || inputString.isEmpty()) {
            return;
        }
        
        Integer currentCount = storeMap.get(inputString);
        if(currentCount == null) {
            storeMap.put(inputString, 1);
        }
        else {
            currentCount++;
            if(currentCount.equals(Integer.MAX_VALUE)) {
                System.err.println(String.format("Maximum number of storable count is reached for string %s", inputString));
                return;
            }
            
            storeMap.put(inputString, currentCount);
        }
        
        System.out.println("Successfully stored!");
        System.out.println();
    }
    
    
    public void printStatistics() {
        
        if(storeMap.isEmpty()) {
            System.err.println("No saved inputs found. Please enter some values before printing statistics");
            return;
        }
        
        List<Map.Entry<String, Integer>> list = new ArrayList<>(storeMap.entrySet());
        Collections.sort(list, new StatsComparator());
        
        long allStringsLength = 0;
        
        for(Map.Entry<String, Integer> mapEntry : list) {
            System.out.println(mapEntry.getValue() + " - " + mapEntry.getKey());
            allStringsLength += (long) mapEntry.getKey().length();
        }
        
        System.out.println("Average string length: " + (double) allStringsLength / list.size());
        System.out.println();
    }
    
    
    private class StatsComparator implements Comparator<Map.Entry<String, Integer>> {

        @Override
        public int compare(Map.Entry<String, Integer> mapEntry1, Map.Entry<String, Integer> mapEntry2) {
            int valuesCompareResult = mapEntry2.getValue().compareTo(mapEntry1.getValue());
            if(valuesCompareResult == 0) {
                return mapEntry1.getKey().compareTo(mapEntry2.getKey());
            }
            
            return valuesCompareResult;
        }
    }
}
