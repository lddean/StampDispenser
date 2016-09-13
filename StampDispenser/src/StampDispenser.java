import java.lang.reflect.Array;
import java.util.*;


public class StampDispenser {
	
	  /**
     * Constructs a new StampDispenser that will be able to dispense the given 
     * types of stamps.
     *
     * @param stampDenominations The values of the types of stamps that the 
     *     machine should have.  Should be sorted in descending order and 
     *     contain at least a 1.
     */
	
	int[] stamps;
	
	
    public StampDispenser(int[] stampDenominations){
    	
    	if(stampDenominations.length == 0){
    		throw new IllegalArgumentException("ERROR: At least 1 stamp in denominations");
    	}
    	
    	boolean contain_one = false;
    	
    	for (int i = 0; i < stampDenominations.length; i++){
    		
    		if (stampDenominations[i] == 1){
    			contain_one = true;
    		}else{
    			continue;
    		}
    	}
    	
    	if (!contain_one){
    		throw new IllegalArgumentException("ERROR: contain at least a 1");
    	}
    	
    	Arrays.sort(stampDenominations);
    	
    	stamps = new int[stampDenominations.length];
    	
    	for (int i = stampDenominations.length - 1; i >= 0; i--){
    		
    		stamps[stampDenominations.length - i - 1] =  stampDenominations[i];
    	}
    	
    	for (int i = 0; i < stampDenominations.length - 1; i++){
    		
    		if (stamps[i] < stamps[i+1]){
    			
    			throw new IllegalArgumentException("ERROR:  Should be sorted in descending order");
    		}
    	}
    	
    }
    
    public void printFillRequest(int[] list, int request){
    	
    	System.out.println("your request are :  " + request);
    	
    	int n = request;
    	
    	while(n >= 1){
    		
    		System.out.print(list[n]);
    		System.out.println("  cent");
    		
    		n = n - list[n];
    	}
    	
    	System.out.println();
    	
    }
    /**
     * Returns the minimum number of stamps that the machine can dispense to
     * fill the given request.
     *
     * @param request The total value of the stamps to be dispensed.
     * 
     * 
     */
    public int calcMinNumStampsToFillRequest(int request)
    {  
        int[] num_stamps = new int[request +1];
        
        int[] last_value = new int[request + 1];
        
        for (int i = 0; i <= request ; i++){
        	num_stamps[i] = 0;
        	last_value[i] = 0;
        }
        
        for (int i = 1; i <= request; i++){
        	
        	int max = Integer.MAX_VALUE;
        	int lastUse = 0;
        	
        	for (int j = 0; j < stamps.length; j++){
        		
        		if(stamps[j] <= i){
        			
        			int key = num_stamps[i - stamps[j]] + 1;
        			
        			if(key < max){
        				max = key;
        				lastUse = stamps[j];
        				
        			}else{
        				continue;
        			}
        		}else{
        			continue;
        		}
        		
        	}
        	last_value[i] = lastUse;
        	num_stamps[i] = max;
        	
        }

        printFillRequest(last_value, request);

        return num_stamps[request];
    }
    

    public static void main(String[] args)
    {
        int[] denominations = { 90, 30, 24, 10, 6, 2, 1 };
        StampDispenser stampDispenser = new StampDispenser(denominations);
        System.out.println(stampDispenser.calcMinNumStampsToFillRequest(18));
        assert stampDispenser.calcMinNumStampsToFillRequest(18) == 3;
        
        //test sort in constructor
        int[] denominations1 = {24, 2, 6, 1, 10, 90 , 30};
        StampDispenser stampDispenser1 = new StampDispenser(denominations1);
        System.out.println(stampDispenser1.calcMinNumStampsToFillRequest(18));
        assert stampDispenser1.calcMinNumStampsToFillRequest(1) == 1;
        assert stampDispenser1.calcMinNumStampsToFillRequest(2) == 1;
        assert stampDispenser1.calcMinNumStampsToFillRequest(3) == 2;
        assert stampDispenser1.calcMinNumStampsToFillRequest(4) == 2;
        assert stampDispenser1.calcMinNumStampsToFillRequest(5) == 3;
        assert stampDispenser1.calcMinNumStampsToFillRequest(6) == 1;
        assert stampDispenser1.calcMinNumStampsToFillRequest(7) == 1;
        assert stampDispenser1.calcMinNumStampsToFillRequest(8) == 2;
        assert stampDispenser1.calcMinNumStampsToFillRequest(9) == 3;
        assert stampDispenser1.calcMinNumStampsToFillRequest(10) == 1;
        assert stampDispenser1.calcMinNumStampsToFillRequest(11) == 2;
        assert stampDispenser1.calcMinNumStampsToFillRequest(12) == 2;
        assert stampDispenser1.calcMinNumStampsToFillRequest(13) == 3;
        assert stampDispenser1.calcMinNumStampsToFillRequest(14) == 3;
        assert stampDispenser1.calcMinNumStampsToFillRequest(15) == 4;
        
        //Large request;
        assert stampDispenser1.calcMinNumStampsToFillRequest(100) == 2;
        assert stampDispenser1.calcMinNumStampsToFillRequest(111) == 4;
        assert stampDispenser1.calcMinNumStampsToFillRequest(120) == 2;
        assert stampDispenser1.calcMinNumStampsToFillRequest(124) == 4;
        assert stampDispenser1.calcMinNumStampsToFillRequest(130) == 3;
        assert stampDispenser1.calcMinNumStampsToFillRequest(150) == 3;
        assert stampDispenser1.calcMinNumStampsToFillRequest(200) == 4;
        
        //Large stamps value;
        
        int[] array = new int[15];
        
        array[0] = 1;
        
        for (int i = 1; i < 15 ; i++){
        	array[i] = array[i - 1] *2;
        }

        StampDispenser stampDispenser2 = new StampDispenser(array);
        System.out.println(stampDispenser2.calcMinNumStampsToFillRequest(18));
        assert stampDispenser1.calcMinNumStampsToFillRequest(18) == 2;
        System.out.println(stampDispenser2.calcMinNumStampsToFillRequest(1432));
        System.out.println(stampDispenser2.calcMinNumStampsToFillRequest(1024));
        System.out.println(stampDispenser2.calcMinNumStampsToFillRequest(2000));
        System.out.println(stampDispenser2.calcMinNumStampsToFillRequest(32767));
        assert stampDispenser1.calcMinNumStampsToFillRequest(32767) == 15;
    }

}
