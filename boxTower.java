
package algo_Ex2;

import java.util.Random;

public class boxTower {

    public static class Box {                                        //class Box

        int height;
        int width;
        int length;

        public Box(int height, int width, int length) {
            this.height = height;
            this.width = width;
            this.length = length;
        }

        public Box(Box newBox) {
            this.height = newBox.height;
            this.width = newBox.width;
            this.length = newBox.length;
        }
    }

    public static void creatArrays(int height[],int width[], int length[], int size) {          // creating random arrays
        Random rand = new Random();
        for(int i = 0; i < size; i++) {
            height[i] = rand.nextInt(200) + 1;
            width[i] = rand.nextInt(200) + 1;
            length[i] = rand.nextInt(200) + 1;
        }
    }

    public static void ArraysToBox(int height[], int width[], int length[], Box arr[]) {      // convert from arrays to box arr
        int size = height.length;
        for(int i=0;i<size; i++) {
            arr[i] = new Box(height[i], width[i], length[i]);
        }
    }

    public static void PrintBuilding(Box[] boxArr, int size) {
        for (int i = 0; i < size; i++) {
            System.out.println( "Box " + (i+1) + ":     height: " + boxArr[i].height + "        length: " +boxArr[i].length + "        width: " +boxArr[i].width );
        }
        System.out.println("\n");
    }


    public static void zeroArr(int A[]) {
        for (int i = 0; i < A.length; i++)
            A[i] = 0;
    }

    public static void printArr(int A[]) {
        System.out.println(java.util.Arrays.toString(A));
//        System.out.println("\n");
    }





    public static int FindHighestBuilding(int height[], int width[], int length[]) {
        int n = height.length;

        Box[] boxArr = new Box[n];
        ArraysToBox( height, width, length, boxArr);
        /////////////////////////////////// Creating all the arrays ///////////////////////////////////

        int cubeVolume[] = new int[n];     // arr for calculate box volume
        for (int i = 0; i < n; i++) {
            cubeVolume[i] = height[i] * width[i] * length[i];
        }

        int totalTower[] = new int[n];     // arr for box total tower
        zeroArr(totalTower);

        int sumArr[] = new int[n];         // create arr for sum the series
        zeroArr(sumArr);

        int WidLenArr[] = new int[n];      // Connecting two arrays: length and width together.
        for (int i = 0; i < n; i++) {
            WidLenArr[i] = width[i] + length[i];
        }
        /////////////////////////////////////////////////////////////////////////////////////////////


        sumArr[0] = WidLenArr[0];
        totalTower[0] = height[0] * width[0] * length[0];

        // looking for a descending series j > i
        for(int i = 1; i < n; i++) {

            int max = 0;
            int towerMax = 0;
            int j;

            for(j = 0; j < i; j++) {
                if(WidLenArr[j] > WidLenArr[i] && sumArr[j] > max){
                    max = sumArr[j];
                    towerMax = height[j] * width[j] * length[j];
                }
            }
            sumArr[i] = max + WidLenArr[i];

            if(towerMax != 0){
                int t = 0;
                for(int k = 0; k < i; k++){
                    if(sumArr[k] > t && WidLenArr[k] > WidLenArr[i]){
                        t = sumArr[k];
                        towerMax = totalTower[k];
                    }
                }
            }
            totalTower[i] = towerMax + cubeVolume[i];
        }


        int heightBuilding = sumArr[0];
        int index = 0;

        for(int i = 0; i < n; i++) {
            if(sumArr[i] > heightBuilding){
                heightBuilding = sumArr[i];
                index = i;
            }
        }

////////////////////////////////// printing //////////////////////////////////

        System.out.println(" \nBox list: ");
        PrintBuilding(boxArr, n);

        System.out.println(" \nheight arr: ");
        printArr(height);

        System.out.println(" \nwidth arr: ");
        printArr(width);

        System.out.println(" \nlength arr: ");
        printArr(length);

        System.out.println(" \nsum arr: width + length");
        printArr(WidLenArr);

        System.out.println(" \nsum series arr: ");
        printArr(sumArr);

        System.out.println(" \ntotal tower arr: ");
        printArr(totalTower);

        System.out.println(" \ntower height: " + totalTower[index]);

////////////////////////////////// end printing //////////////////////////////////

        return totalTower[index];

    }






















    public static void main(String[] args) {

        System.out.println("*******  Example with 30 random boxes  *******\n");
        int height[] = new int[30];
        int width[]  = new int[30];
        int length[] = new int[30];

        creatArrays(height, width, length, 30);

        FindHighestBuilding(height, width, length);



//        int n2 = 8;
//        int h[] = {1, 1, 1, 1, 1, 1, 1, 1};
//        int w[] = {1, 8, 5, 9, 7, 6, 15, 3};
//        int l[] = {1, 8, 5, 9, 7, 6, 15, 3};
//
//        Box[] tempBoxArr = new Box[n2];
//        ArraysToBox(h, w, l, tempBoxArr);
//        System.out.println("\n");
//        PrintBuilding(tempBoxArr, n2);
//
//        FindHighestBuilding(h, w, l);


    }


    //END
}