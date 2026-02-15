// You are assigned to put some amount 
// of boxes onto one truck. You are given
// a 2D array boxTypes, where boxTypes[i] = [numberOfBoxesi, numberOfUnitsPerBoxi]:
// numberOfBoxesi is the number of 
// boxes of type i.
// numberOfUnitsPerBoxi is the number of units 
// in each box of the type i.
// You are also given an integer truckSize,
// which is the maximum number of boxes that
// can be put on the truck. You can choose
// any boxes to put on the truck as long as 
// the number of boxes does not exceed truckSize.
// Return the maximum total number of units
// that can be put on the truck.

// 1 <= boxTypes.length <= 1000
// 1 <= numberOfBoxesi, numberOfUnitsPerBoxi <= 1000
// 1 <= truckSize <= 10^6
class Solution {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes,(a,b)->b[1]-a[1]);
        int totalBoxes=0;
        int totalUnits=0;
        int i=0;
        for(int[] type:boxTypes) {
            if(truckSize==0) break;
            int boxes=Math.min(type[0] ,truckSize);
            totalBoxes+=boxes;
            totalUnits+=boxes*type[1];
            truckSize-=boxes;
        }

        return totalUnits;


    }
}