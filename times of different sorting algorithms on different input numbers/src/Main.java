import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;
import java.io.*;
import java.util.*;

class Main {
    public static void main(String args[]) throws IOException {

        /*

        // X axis data
        int[] inputAxis = {512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 251282};

        // results on random data
        double[][] yAxis = new double[4][10];
        yAxis[0] = new double[]{0, 0, 0, 1, 4, 19, 69, 269, 1116, 4612};
        yAxis[1] = new double[]{0, 0, 0, 0, 0, 1, 2, 5, 11, 28};
        yAxis[2] = new double[]{194, 178, 214, 179, 240, 223, 210, 180, 218, 181};
        yAxis[3] = new double[]{200, 156, 138, 115, 144, 150, 171, 116, 133, 132};
        showAndSaveChart("Tests on Random Data", inputAxis, yAxis);

        // results on sorted data
        double[][] yAxisSorted = new double[4][10];
        yAxisSorted[0] = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        yAxisSorted[1] = new double[]{0, 0, 0, 0, 0, 0, 1, 5, 4, 10};
        yAxisSorted[2] = new double[]{217, 178, 181, 184, 245, 222, 214, 177, 228, 181};
        yAxisSorted[3] = new double[]{139, 140, 128, 111, 139, 127, 143, 114, 132, 126};
        showAndSaveChart("Tests on Sorted Data", inputAxis, yAxisSorted);


        // results on reversed data
        double[][] yAxisReversed = new double[4][10];
        yAxisReversed[0] = new double[]{0, 0, 0, 2, 8, 34, 136, 548, 2219, 8204};
        yAxisReversed[1] = new double[]{0, 0, 0, 0, 0, 0, 1, 5, 9, 11};
        yAxisReversed[2] = new double[]{238, 191, 178, 236, 259, 245, 224, 192, 240, 184};
        yAxisReversed[3] = new double[]{133, 114, 122, 112, 144, 121, 123, 113, 120, 120};
        showAndSaveChart("Tests on Reversed Sorted Data", inputAxis, yAxisReversed);
        */

        // sir, i need to remove chart because,
        // sometimes, my program is crashing because of the chart.
        // i added to report in detail way.

        List<List<String>> records = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("TrafficFlowDataset.csv"));){
            while (scanner.hasNextLine()){
                records.add(getRecordFromLine(scanner.nextLine()));
            }
        }

        int[] recordsFlowDuration = new int[251281];
        for (int i = 1; i<records.size() ; i++){
            recordsFlowDuration[i-1] = Integer.parseInt(records.get(i).get(7));
        }

        //512
        int[] array512 = new int[512];

        //insertion - random
        long tempDurationData = 0;
        long startTime = System.nanoTime();
        for (int i = 0; i < 10; i++){
            long tempDuration = System.nanoTime();
            System.arraycopy(recordsFlowDuration,0,array512,0,512);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
            insertionSort(array512);
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime - (10*tempDurationData));  //divide by 1000000 to get milliseconds
        System.out.println("512 - random data - insertion sort: " + duration/10000000);

        //insertion - sorted
        startTime=System.nanoTime();
        for (int i=0; i<10; i++){
            insertionSort(array512);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime);  //divide by 1000000 to get milliseconds
        System.out.println("512 - sorted data - insertion sort: " + duration/10000000);

        //insertion - reversed
        reverseArray(array512,512);
        startTime = System.nanoTime();
        tempDurationData = 0;
        for (int i=0; i<10; i++){
            insertionSort(array512);
            long tempDuration = System.nanoTime();
            reverseArray(array512,512);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));  //divide by 1000000 to get milliseconds
        System.out.println("512 - reversed data - insertion sort: " + duration/10000000);

        System.out.println("*********************************************************");

        //merge - random
        tempDurationData = 0;
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            long tempDuration = System.nanoTime();
            System.arraycopy(recordsFlowDuration,0,array512,0,512);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
            sort(array512,0,512-1);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));  //divide by 1000000 to get milliseconds
        System.out.println("512 - random data - merge sort: " + duration/10000000);

        //merge - sorted
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            sort(array512,0,512-1);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println("512 - sorted data - merge sort: " + duration/10000000);

        //merge reversed
        tempDurationData = 0;
        startTime = System.nanoTime();
        reverseArray(array512,512);
        for (int i=0; i<10; i++){
            sort(array512,0,511);
            long tempDuration = System.nanoTime();
            reverseArray(array512,512);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("512 - reversed data - merge sort: " + duration/10000000);

        System.out.println("*********************************************************");

        //counting - random
        tempDurationData = 0;
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            long tempDuration = System.nanoTime();
            System.arraycopy(recordsFlowDuration,0,array512,0,512);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
            countSort(array512);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));  //divide by 1000000 to get milliseconds
        System.out.println("512 - random data - counting sort: " + duration/10000000);

        //counting - sorted
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            countSort(array512);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println("512 - sorted data - counting sort: " + duration/10000000);

        //counting reversed
        tempDurationData = 0;
        startTime = System.nanoTime();
        reverseArray(array512,512);
        for (int i=0; i<10; i++){
            countSort(array512);
            long tempDuration = System.nanoTime();
            reverseArray(array512,512);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("512 - reversed data - counting sort: " + duration/10000000);

        System.out.println("*********************************************************");

        //pigeonhole - random
        tempDurationData = 0;
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            long tempDuration = System.nanoTime();
            System.arraycopy(recordsFlowDuration,0,array512,0,512);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
            pigeonhole_sort(array512);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));  //divide by 1000000 to get milliseconds
        System.out.println("512 - random data - pigeonhole sort: " + duration/10000000);

        //counting - sorted
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            pigeonhole_sort(array512);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println("512 - sorted data - pigeonhole sort: " + duration/10000000);

        //counting reversed
        tempDurationData = 0;
        startTime = System.nanoTime();
        reverseArray(array512,512);
        for (int i=0; i<10; i++){
            pigeonhole_sort(array512);
            long tempDuration = System.nanoTime();
            reverseArray(array512,512);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("512 - reversed data - pigeonhole sort: " + duration/10000000);

        System.out.println("*********************************************************");

        //1024
        int[] array1024 = new int[1024];

        //insertion - random
        tempDurationData=0;
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            long tempDuration = System.nanoTime();
            System.arraycopy(recordsFlowDuration,0,array1024,0,1024);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
            insertionSort(array1024);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("1024 - random data - insertion sort: " + duration/10000000);

        //insertion - sorted
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            insertionSort(array1024);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println("1024 - sorted data - insertion sort: " + duration/10000000);

        //insertion - reversed
        tempDurationData=0;
        startTime = System.nanoTime();
        reverseArray(array1024,1024);
        for (int i=0; i<10; i++){
            insertionSort(array1024);
            long tempDuration = System.nanoTime();
            reverseArray(array1024,1024);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime -(10*tempDurationData));
        System.out.println("1024 - reversed data - insertion sort: " + duration/10000000);

        System.out.println("*********************************************************");

        //merge - random
        tempDurationData=0;
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            long tempDuration = System.nanoTime();
            System.arraycopy(recordsFlowDuration,0,array1024,0,1024);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
            sort(array1024,0,1023);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("1024 - random data - merge sort: " + duration/10000000);

        //merge - sorted
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            sort(array1024,0,1023);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println("1024 - sorted data - merge sort: " + duration/10000000);

        //merge reversed
        tempDurationData = 0;
        startTime = System.nanoTime();
        reverseArray(array1024,1024);
        for (int i=0; i<10; i++){
            sort(array1024,0,1023);
            long tempDuration = System.nanoTime();
            reverseArray(array1024,1024);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("1024 - reversed data - merge sort: " + duration/10000000);

        System.out.println("*********************************************************");

        //counting - random
        tempDurationData=0;
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            long tempDuration = System.nanoTime();
            System.arraycopy(recordsFlowDuration,0,array1024,0,1024);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
            countSort(array1024);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("1024 - random data - counting sort: " + duration/10000000);

        //counting - sorted
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            countSort(array1024);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println("1024 - sorted data - counting sort: " + duration/10000000);

        //counting - reversed
        tempDurationData = 0;
        startTime = System.nanoTime();
        reverseArray(array1024,1024);
        for (int i=0; i<10; i++){
            countSort(array1024);
            long tempDuration = System.nanoTime();
            reverseArray(array1024,1024);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("1024 - reversed data - counting sort: " + duration/10000000);

        System.out.println("*********************************************************");

        //pigeonhole - random
        tempDurationData=0;
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            long tempDuration = System.nanoTime();
            System.arraycopy(recordsFlowDuration,0,array1024,0,1024);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
            pigeonhole_sort(array1024);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("1024 - random data - pigeonhole sort: " + duration/10000000);

        //pigeonhole - sorted
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            pigeonhole_sort(array1024);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println("1024 - sorted data - pigeonhole sort: " + duration/10000000);

        //pigeonhole - reversed
        tempDurationData = 0;
        startTime = System.nanoTime();
        reverseArray(array1024,1024);
        for (int i=0; i<10; i++){
            pigeonhole_sort(array1024);
            long tempDuration = System.nanoTime();
            reverseArray(array1024,1024);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("1024 - reversed data - pigeonhole sort: " + duration/10000000);

        System.out.println("*********************************************************");


        //2048
        int[] array2048 = new int[2048];

        //insertion - random
        tempDurationData=0;
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            long tempDuration = System.nanoTime();
            System.arraycopy(recordsFlowDuration,0,array2048,0,2048);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
            insertionSort(array2048);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("2048 - random data - insertion sort: " + duration/10000000);

        //insertion - sorted
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            insertionSort(array2048);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println("2048 - sorted data - insertion sort: " + duration/10000000);

        //insertion - reversed
        tempDurationData=0;
        startTime = System.nanoTime();
        reverseArray(array2048,2048);
        for (int i=0; i<10; i++){
            insertionSort(array2048);
            long tempDuration = System.nanoTime();
            reverseArray(array2048,2048);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime -(10*tempDurationData));
        System.out.println("2048 - reversed data - insertion sort: " + duration/10000000);

        System.out.println("*********************************************************");

        //merge - random
        tempDurationData=0;
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            long tempDuration = System.nanoTime();
            System.arraycopy(recordsFlowDuration,0,array2048,0,2048);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
            sort(array2048,0,2047);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("2048 - random data - merge sort: " + duration/10000000);

        //merge - sorted
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            sort(array2048,0,2047);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println("2048 - sorted data - merge sort: " + duration/10000000);

        //merge reversed
        tempDurationData = 0;
        startTime = System.nanoTime();
        reverseArray(array2048,2048);
        for (int i=0; i<10; i++){
            sort(array2048,0,2047);
            long tempDuration = System.nanoTime();
            reverseArray(array2048,2048);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("2048 - reversed data - merge sort: " + duration/10000000);

        System.out.println("*********************************************************");

        //counting - random
        tempDurationData=0;
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            long tempDuration = System.nanoTime();
            System.arraycopy(recordsFlowDuration,0,array2048,0,2048);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
            countSort(array2048);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("2048 - random data - counting sort: " + duration/10000000);

        //counting - sorted
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            countSort(array2048);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println("2048 - sorted data - counting sort: " + duration/10000000);

        //counting - reversed
        tempDurationData = 0;
        startTime = System.nanoTime();
        reverseArray(array2048,2048);
        for (int i=0; i<10; i++){
            countSort(array2048);
            long tempDuration = System.nanoTime();
            reverseArray(array2048,2048);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("2048 - reversed data - counting sort: " + duration/10000000);

        System.out.println("*********************************************************");

        //pigeonhole - random
        tempDurationData=0;
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            long tempDuration = System.nanoTime();
            System.arraycopy(recordsFlowDuration,0,array2048,0,2048);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
            pigeonhole_sort(array2048);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("2048 - random data - pigeonhole sort: " + duration/10000000);

        //pigeonhole - sorted
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            pigeonhole_sort(array2048);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println("2048 - sorted data - pigeonhole sort: " + duration/10000000);

        //pigeonhole - reversed
        tempDurationData = 0;
        startTime = System.nanoTime();
        reverseArray(array2048,2048);
        for (int i=0; i<10; i++){
            pigeonhole_sort(array2048);
            long tempDuration = System.nanoTime();
            reverseArray(array2048,2048);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("2048 - reversed data - pigeonhole sort: " + duration/10000000);

        System.out.println("*********************************************************");


        //4096
        int[] array4096 = new int[4096];

        //insertion - random
        tempDurationData=0;
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            long tempDuration = System.nanoTime();
            System.arraycopy(recordsFlowDuration,0,array4096,0,4096);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
            insertionSort(array4096);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("4096 - random data - insertion sort: " + duration/10000000);

        //insertion - sorted
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            insertionSort(array4096);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println("4096 - sorted data - insertion sort: " + duration/10000000);

        //insertion - reversed
        tempDurationData=0;
        startTime = System.nanoTime();
        reverseArray(array4096,4096);
        for (int i=0; i<10; i++){
            insertionSort(array4096);
            long tempDuration = System.nanoTime();
            reverseArray(array4096,4096);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime -(10*tempDurationData));
        System.out.println("4096 - reversed data - insertion sort: " + duration/10000000);

        System.out.println("*********************************************************");

        //merge - random
        tempDurationData=0;
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            long tempDuration = System.nanoTime();
            System.arraycopy(recordsFlowDuration,0,array4096,0,4096);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
            sort(array4096,0,4096-1);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("4096 - random data - merge sort: " + duration/10000000);

        //merge - sorted
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            sort(array4096,0,4096-1);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println("4096 - sorted data - merge sort: " + duration/10000000);

        //merge reversed
        tempDurationData = 0;
        startTime = System.nanoTime();
        reverseArray(array4096,4096);
        for (int i=0; i<10; i++){
            sort(array4096,0,4096-1);
            long tempDuration = System.nanoTime();
            reverseArray(array4096,4096);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("4096 - reversed data - merge sort: " + duration/10000000);

        System.out.println("*********************************************************");

        //counting - random
        tempDurationData=0;
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            long tempDuration = System.nanoTime();
            System.arraycopy(recordsFlowDuration,0,array4096,0,4096);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
            countSort(array4096);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("4096 - random data - counting sort: " + duration/10000000);

        //counting - sorted
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            countSort(array4096);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println("4096 - sorted data - counting sort: " + duration/10000000);

        //counting - reversed
        tempDurationData = 0;
        startTime = System.nanoTime();
        reverseArray(array4096,4096);
        for (int i=0; i<10; i++){
            countSort(array4096);
            long tempDuration = System.nanoTime();
            reverseArray(array4096,4096);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("4096 - reversed data - counting sort: " + duration/10000000);

        System.out.println("*********************************************************");

        //pigeonhole - random
        tempDurationData=0;
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            long tempDuration = System.nanoTime();
            System.arraycopy(recordsFlowDuration,0,array4096,0,4096);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
            pigeonhole_sort(array4096);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("4096 - random data - pigeonhole sort: " + duration/10000000);

        //pigeonhole - sorted
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            pigeonhole_sort(array4096);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println("4096 - sorted data - pigeonhole sort: " + duration/10000000);

        //pigeonhole - reversed
        tempDurationData = 0;
        startTime = System.nanoTime();
        reverseArray(array4096,4096);
        for (int i=0; i<10; i++){
            pigeonhole_sort(array4096);
            long tempDuration = System.nanoTime();
            reverseArray(array4096,4096);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("4096 - reversed data - pigeonhole sort: " + duration/10000000);

        System.out.println("*********************************************************");


        //8192
        int[] array8192 = new int[8192];

        //insertion - random
        tempDurationData=0;
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            long tempDuration = System.nanoTime();
            System.arraycopy(recordsFlowDuration,0,array8192,0,8192);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
            insertionSort(array8192);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("8192 - random data - insertion sort: " + duration/10000000);

        //insertion - sorted
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            insertionSort(array8192);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println("8192 - sorted data - insertion sort: " + duration/10000000);

        //insertion - reversed
        tempDurationData=0;
        startTime = System.nanoTime();
        reverseArray(array8192,8192);
        for (int i=0; i<10; i++){
            insertionSort(array8192);
            long tempDuration = System.nanoTime();
            reverseArray(array8192,8192);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime -(10*tempDurationData));
        System.out.println("8192 - reversed data - insertion sort: " + duration/10000000);

        System.out.println("*********************************************************");

        //merge - random
        tempDurationData=0;
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            long tempDuration = System.nanoTime();
            System.arraycopy(recordsFlowDuration,0,array8192,0,8192);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
            sort(array8192,0,8192-1);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("8192 - random data - merge sort: " + duration/10000000);

        //merge - sorted
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            sort(array8192,0,8192-1);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println("8192 - sorted data - merge sort: " + duration/10000000);

        //merge reversed
        tempDurationData = 0;
        startTime = System.nanoTime();
        reverseArray(array8192,8192);
        for (int i=0; i<10; i++){
            sort(array8192,0,8192-1);
            long tempDuration = System.nanoTime();
            reverseArray(array8192,8192);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("8192 - reversed data - merge sort: " + duration/10000000);

        System.out.println("*********************************************************");

        //counting - random
        tempDurationData=0;
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            long tempDuration = System.nanoTime();
            System.arraycopy(recordsFlowDuration,0,array8192,0,8192);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
            countSort(array8192);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("8192 - random data - counting sort: " + duration/10000000);

        //counting - sorted
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            countSort(array8192);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println("8192 - sorted data - counting sort: " + duration/10000000);

        //counting - reversed
        tempDurationData = 0;
        startTime = System.nanoTime();
        reverseArray(array8192,8192);
        for (int i=0; i<10; i++){
            countSort(array8192);
            long tempDuration = System.nanoTime();
            reverseArray(array8192,8192);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("8192 - reversed data - counting sort: " + duration/10000000);

        System.out.println("*********************************************************");

        //pigeonhole - random
        tempDurationData=0;
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            long tempDuration = System.nanoTime();
            System.arraycopy(recordsFlowDuration,0,array8192,0,8192);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
            pigeonhole_sort(array8192);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("8192 - random data - pigeonhole sort: " + duration/10000000);

        //pigeonhole - sorted
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            pigeonhole_sort(array8192);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println("8192 - sorted data - pigeonhole sort: " + duration/10000000);

        //pigeonhole - reversed
        tempDurationData = 0;
        startTime = System.nanoTime();
        reverseArray(array8192,8192);
        for (int i=0; i<10; i++){
            pigeonhole_sort(array8192);
            long tempDuration = System.nanoTime();
            reverseArray(array8192,8192);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("8192 - reversed data - pigeonhole sort: " + duration/10000000);

        System.out.println("*********************************************************");


        //16384
        int[] array16384 = new int[16384];

        //insertion - random
        tempDurationData=0;
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            long tempDuration = System.nanoTime();
            System.arraycopy(recordsFlowDuration,0,array16384,0,16384);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
            insertionSort(array16384);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("16384 - random data - insertion sort: " + duration/10000000);

        //insertion - sorted
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            insertionSort(array16384);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println("16384 - sorted data - insertion sort: " + duration/10000000);

        //insertion - reversed
        tempDurationData=0;
        startTime = System.nanoTime();
        reverseArray(array16384,16384);
        for (int i=0; i<10; i++){
            insertionSort(array16384);
            long tempDuration = System.nanoTime();
            reverseArray(array16384,16384);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime -(10*tempDurationData));
        System.out.println("16384 - reversed data - insertion sort: " + duration/10000000);

        System.out.println("*********************************************************");

        //merge - random
        tempDurationData=0;
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            long tempDuration = System.nanoTime();
            System.arraycopy(recordsFlowDuration,0,array16384,0,16384);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
            sort(array16384,0,16384-1);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("16384 - random data - merge sort: " + duration/10000000);

        //merge - sorted
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            sort(array16384,0,16384-1);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println("16384 - sorted data - merge sort: " + duration/10000000);

        //merge reversed
        tempDurationData = 0;
        startTime = System.nanoTime();
        reverseArray(array16384,16384);
        for (int i=0; i<10; i++){
            sort(array16384,0,16384-1);
            long tempDuration = System.nanoTime();
            reverseArray(array16384,16384);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("16384 - reversed data - merge sort: " + duration/10000000);

        System.out.println("*********************************************************");

        //counting - random
        tempDurationData=0;
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            long tempDuration = System.nanoTime();
            System.arraycopy(recordsFlowDuration,0,array16384,0,16384);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
            countSort(array16384);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("16384 - random data - counting sort: " + duration/10000000);

        //counting - sorted
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            countSort(array16384);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println("16384 - sorted data - counting sort: " + duration/10000000);

        //counting - reversed
        tempDurationData = 0;
        startTime = System.nanoTime();
        reverseArray(array16384,16384);
        for (int i=0; i<10; i++){
            countSort(array16384);
            long tempDuration = System.nanoTime();
            reverseArray(array16384,16384);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("16384 - reversed data - counting sort: " + duration/10000000);

        System.out.println("*********************************************************");

        //pigeonhole - random
        tempDurationData=0;
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            long tempDuration = System.nanoTime();
            System.arraycopy(recordsFlowDuration,0,array16384,0,16384);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
            pigeonhole_sort(array16384);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("16384 - random data - pigeonhole sort: " + duration/10000000);

        //pigeonhole - sorted
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            pigeonhole_sort(array16384);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println("16384 - sorted data - pigeonhole sort: " + duration/10000000);

        //pigeonhole - reversed
        tempDurationData = 0;
        startTime = System.nanoTime();
        reverseArray(array16384,16384);
        for (int i=0; i<10; i++){
            pigeonhole_sort(array16384);
            long tempDuration = System.nanoTime();
            reverseArray(array16384,16384);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("16384 - reversed data - pigeonhole sort: " + duration/10000000);

        System.out.println("*********************************************************");


        //32768
        int[] array32768 = new int[32768];

        //insertion - random
        tempDurationData=0;
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            long tempDuration = System.nanoTime();
            System.arraycopy(recordsFlowDuration,0,array32768,0,32768);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
            insertionSort(array32768);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("32768 - random data - insertion sort: " + duration/10000000);

        //insertion - sorted
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            insertionSort(array32768);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println("32768 - sorted data - insertion sort: " + duration/10000000);

        //insertion - reversed
        tempDurationData=0;
        startTime = System.nanoTime();
        reverseArray(array32768,32768);
        for (int i=0; i<10; i++){
            insertionSort(array32768);
            long tempDuration = System.nanoTime();
            reverseArray(array32768,32768);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime -(10*tempDurationData));
        System.out.println("32768 - reversed data - insertion sort: " + duration/10000000);

        System.out.println("*********************************************************");

        //merge - random
        tempDurationData=0;
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            long tempDuration = System.nanoTime();
            System.arraycopy(recordsFlowDuration,0,array32768,0,32768);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
            sort(array32768,0,32768-1);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("32768 - random data - merge sort: " + duration/10000000);

        //merge - sorted
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            sort(array32768,0,32768-1);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println("32768 - sorted data - merge sort: " + duration/10000000);

        //merge reversed
        tempDurationData = 0;
        startTime = System.nanoTime();
        reverseArray(array32768,32768);
        for (int i=0; i<10; i++){
            sort(array32768,0,32768-1);
            long tempDuration = System.nanoTime();
            reverseArray(array32768,32768);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("32768 - reversed data - merge sort: " + duration/10000000);

        System.out.println("*********************************************************");

        //counting - random
        tempDurationData=0;
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            long tempDuration = System.nanoTime();
            System.arraycopy(recordsFlowDuration,0,array32768,0,32768);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
            countSort(array32768);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("32768 - random data - counting sort: " + duration/10000000);

        //counting - sorted
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            countSort(array32768);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println("32768 - sorted data - counting sort: " + duration/10000000);

        //counting - reversed
        tempDurationData = 0;
        startTime = System.nanoTime();
        reverseArray(array32768,32768);
        for (int i=0; i<10; i++){
            countSort(array32768);
            long tempDuration = System.nanoTime();
            reverseArray(array32768,32768);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("32768 - reversed data - counting sort: " + duration/10000000);

        System.out.println("*********************************************************");

        //pigeonhole - random
        tempDurationData=0;
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            long tempDuration = System.nanoTime();
            System.arraycopy(recordsFlowDuration,0,array32768,0,32768);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
            pigeonhole_sort(array32768);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("32768 - random data - pigeonhole sort: " + duration/10000000);

        //pigeonhole - sorted
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            pigeonhole_sort(array32768);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println("32768 - sorted data - pigeonhole sort: " + duration/10000000);

        //pigeonhole - reversed
        tempDurationData = 0;
        startTime = System.nanoTime();
        reverseArray(array32768,32768);
        for (int i=0; i<10; i++){
            pigeonhole_sort(array32768);
            long tempDuration = System.nanoTime();
            reverseArray(array32768,32768);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("32768 - reversed data - pigeonhole sort: " + duration/10000000);

        System.out.println("*********************************************************");

        //65536
        int[] array65536 = new int[65536];

        //insertion - random
        tempDurationData=0;
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            long tempDuration = System.nanoTime();
            System.arraycopy(recordsFlowDuration,0,array65536,0,65536);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
            insertionSort(array65536);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("65536 - random data - insertion sort: " + duration/10000000);

        //insertion - sorted
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            insertionSort(array65536);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println("65536 - sorted data - insertion sort: " + duration/10000000);

        //insertion - reversed
        tempDurationData=0;
        startTime = System.nanoTime();
        reverseArray(array65536,65536);
        for (int i=0; i<10; i++){
            insertionSort(array65536);
            long tempDuration = System.nanoTime();
            reverseArray(array65536,65536);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime -(10*tempDurationData));
        System.out.println("65536 - reversed data - insertion sort: " + duration/10000000);

        System.out.println("*********************************************************");

        //merge - random
        tempDurationData=0;
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            long tempDuration = System.nanoTime();
            System.arraycopy(recordsFlowDuration,0,array65536,0,65536);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
            sort(array65536,0,65536-1);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("65536 - random data - merge sort: " + duration/10000000);

        //merge - sorted
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            sort(array65536,0,65536-1);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println("65536 - sorted data - merge sort: " + duration/10000000);

        //merge reversed
        tempDurationData = 0;
        startTime = System.nanoTime();
        reverseArray(array65536,65536);
        for (int i=0; i<10; i++){
            sort(array65536,0,65536-1);
            long tempDuration = System.nanoTime();
            reverseArray(array65536,65536);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("65536 - reversed data - merge sort: " + duration/10000000);

        System.out.println("*********************************************************");

        //counting - random
        tempDurationData=0;
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            long tempDuration = System.nanoTime();
            System.arraycopy(recordsFlowDuration,0,array65536,0,65536);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
            countSort(array65536);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("65536 - random data - counting sort: " + duration/10000000);

        //counting - sorted
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            countSort(array65536);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println("65536 - sorted data - counting sort: " + duration/10000000);

        //counting - reversed
        tempDurationData = 0;
        startTime = System.nanoTime();
        reverseArray(array65536,65536);
        for (int i=0; i<10; i++){
            countSort(array65536);
            long tempDuration = System.nanoTime();
            reverseArray(array65536,65536);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("65536 - reversed data - counting sort: " + duration/10000000);

        System.out.println("*********************************************************");

        //pigeonhole - random
        tempDurationData=0;
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            long tempDuration = System.nanoTime();
            System.arraycopy(recordsFlowDuration,0,array65536,0,65536);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
            pigeonhole_sort(array65536);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("65536 - random data - pigeonhole sort: " + duration/10000000);

        //pigeonhole - sorted
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            pigeonhole_sort(array65536);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println("65536 - sorted data - pigeonhole sort: " + duration/10000000);

        //pigeonhole - reversed
        tempDurationData = 0;
        startTime = System.nanoTime();
        reverseArray(array65536,65536);
        for (int i=0; i<10; i++){
            pigeonhole_sort(array65536);
            long tempDuration = System.nanoTime();
            reverseArray(array65536,65536);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("65536 - reversed data - pigeonhole sort: " + duration/10000000);

        System.out.println("*********************************************************");


        //131072
        int[] array131072 = new int[131072];

        //insertion - random
        tempDurationData=0;
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            long tempDuration = System.nanoTime();
            System.arraycopy(recordsFlowDuration,0,array131072,0,131072);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
            insertionSort(array131072);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("131072 - random data - insertion sort: " + duration/10000000);

        //insertion - sorted
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            insertionSort(array131072);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println("131072 - sorted data - insertion sort: " + duration/10000000);

        //insertion - reversed
        tempDurationData=0;
        startTime = System.nanoTime();
        reverseArray(array131072,131072);
        for (int i=0; i<10; i++){
            insertionSort(array131072);
            long tempDuration = System.nanoTime();
            reverseArray(array131072,131072);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime -(10*tempDurationData));
        System.out.println("131072 - reversed data - insertion sort: " + duration/10000000);

        System.out.println("*********************************************************");

        //merge - random
        tempDurationData=0;
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            long tempDuration = System.nanoTime();
            System.arraycopy(recordsFlowDuration,0,array131072,0,131072);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
            sort(array131072,0,131072-1);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("131072 - random data - merge sort: " + duration/10000000);

        //merge - sorted
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            sort(array131072,0,131072-1);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println("131072 - sorted data - merge sort: " + duration/10000000);

        //merge reversed
        tempDurationData = 0;
        startTime = System.nanoTime();
        reverseArray(array131072,131072);
        for (int i=0; i<10; i++){
            sort(array131072,0,131072-1);
            long tempDuration = System.nanoTime();
            reverseArray(array131072,131072);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("131072 - reversed data - merge sort: " + duration/10000000);

        System.out.println("*********************************************************");

        //counting - random
        tempDurationData=0;
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            long tempDuration = System.nanoTime();
            System.arraycopy(recordsFlowDuration,0,array131072,0,131072);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
            countSort(array131072);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("131072 - random data - counting sort: " + duration/10000000);

        //counting - sorted
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            countSort(array131072);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println("131072 - sorted data - counting sort: " + duration/10000000);

        //counting - reversed
        tempDurationData = 0;
        startTime = System.nanoTime();
        reverseArray(array131072,131072);
        for (int i=0; i<10; i++){
            countSort(array131072);
            long tempDuration = System.nanoTime();
            reverseArray(array131072,131072);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("131072 - reversed data - counting sort: " + duration/10000000);

        System.out.println("*********************************************************");

        //pigeonhole - random
        tempDurationData=0;
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            long tempDuration = System.nanoTime();
            System.arraycopy(recordsFlowDuration,0,array131072,0,131072);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
            pigeonhole_sort(array131072);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("131072 - random data - pigeonhole sort: " + duration/10000000);

        //pigeonhole - sorted
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            pigeonhole_sort(array131072);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println("131072 - sorted data - pigeonhole sort: " + duration/10000000);

        //pigeonhole - reversed
        tempDurationData = 0;
        startTime = System.nanoTime();
        reverseArray(array131072,131072);
        for (int i=0; i<10; i++){
            pigeonhole_sort(array131072);
            long tempDuration = System.nanoTime();
            reverseArray(array131072,131072);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("131072 - reversed data - pigeonhole sort: " + duration/10000000);

        System.out.println("*********************************************************");


        //251281
        int[] array251281 = new int[251281];

        //insertion - random
        tempDurationData = 0;
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            long tempDuration = System.nanoTime();
            System.arraycopy(recordsFlowDuration,0,array251281,0,251281);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
            insertionSort(array251281);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("251281 - random data - insertion sort: " + duration/10000000);


        //insertion - sorted
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            insertionSort(array251281);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println("251281 - sorted data - insertion sort: " + duration/10000000);


        //insertion - reversed
        tempDurationData = 0;
        reverseArray(array251281,251281);
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            insertionSort(array251281);
            long tempDuration = System.nanoTime();
            reverseArray(array251281,251281);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("251281 - reversed data - insertion sort: " + duration/10000000);

        System.out.println("*********************************************************");

        //merge - random
        tempDurationData = 0;
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            long tempDuration = System.nanoTime();
            System.arraycopy(recordsFlowDuration,0,array251281,0,251281);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
            sort(array251281,0,251281-1);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("251281 - random data - merge sort: " + duration/10000000);

        //merge - sorted
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            sort(array251281,0,251281-1);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println("251281 - sorted data - merge sort: " + duration/10000000);

        //merge reversed
        tempDurationData = 0;
        startTime = System.nanoTime();
        reverseArray(array251281,251281);
        for (int i=0; i<10; i++){
            sort(array251281,0,251281-1);
            long tempDuration = System.nanoTime();
            reverseArray(array251281,251281);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("251281 - reversed data - merge sort: " + duration/10000000);

        System.out.println("*********************************************************");

        //counting - random
        tempDurationData=0;
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            long tempDuration = System.nanoTime();
            System.arraycopy(recordsFlowDuration,0,array251281,0,251281);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
            countSort(array251281);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("251281 - random data - counting sort: " + duration/10000000);

        //counting - sorted
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            countSort(array251281);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println("251281 - sorted data - counting sort: " + duration/10000000);

        //counting - reversed
        tempDurationData = 0;
        startTime = System.nanoTime();
        reverseArray(array251281,251281);
        for (int i=0; i<10; i++){
            countSort(array251281);
            long tempDuration = System.nanoTime();
            reverseArray(array251281,251281);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("251281 - reversed data - counting sort: " + duration/10000000);

        System.out.println("*********************************************************");

        //pigeonhole - random
        tempDurationData=0;
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            long tempDuration = System.nanoTime();
            System.arraycopy(recordsFlowDuration,0,array251281,0,251281);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
            pigeonhole_sort(array251281);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("251281 - random data - pigeonhole sort: " + duration/10000000);

        //pigeonhole - sorted
        startTime = System.nanoTime();
        for (int i=0; i<10; i++){
            pigeonhole_sort(array251281);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println("251281 - sorted data - pigeonhole sort: " + duration/10000000);

        //pigeonhole - reversed
        tempDurationData = 0;
        startTime = System.nanoTime();
        reverseArray(array251281,251281);
        for (int i=0; i<10; i++){
            pigeonhole_sort(array251281);
            long tempDuration = System.nanoTime();
            reverseArray(array251281,251281);
            long tempDurationEnd = System.nanoTime();
            tempDurationData = tempDurationEnd - tempDuration;
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime - (10*tempDurationData));
        System.out.println("251281 - reversed data - pigeonhole sort: " + duration/10000000);

        System.out.println("*********************************************************");
    }

    static void pigeonhole_sort(int[] arr)
    {
        int n = arr.length;
        int min = arr[0];
        int max = arr[0];
        int range, i, j, index;

        for (int a = 0; a < n; a++) {
            if (arr[a] > max)
                max = arr[a];
            if (arr[a] < min)
                min = arr[a];
        }

        range = max - min + 1;
        int[] phole = new int[range];
        Arrays.fill(phole, 0);

        for (i = 0; i < n; i++)
            phole[arr[i] - min]++;

        index = 0;

        for (j = 0; j < range; j++)
            while (phole[j]-- > 0)
                arr[index++] = j + min;
    }

    static void countSort(int[] arr)
    {
        int max = Arrays.stream(arr).max().getAsInt();
        int min = Arrays.stream(arr).min().getAsInt();
        int range = max - min + 1;
        int count[] = new int[range];
        int output[] = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            count[arr[i] - min]++;
        }

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i] - min] - 1] = arr[i];
            count[arr[i] - min]--;
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = output[i];
        }
    }

    static void insertionSort(int[] array) {
        int n = array.length;
        for (int j = 1; j < n; j++) {
            int key = array[j];
            int i = j-1;
            while ( (i > -1) && ( array [i] > key ) ) {
                array [i+1] = array [i];
                i--;
            }
            array[i+1] = key;
        }
    }

    static void merge(int[] arr, int l, int m, int r)
    {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];

        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    static void sort(int[] arr, int l, int r)
    {
        if (l < r) {
            int m =l+ (r-l)/2;
            sort(arr, l, m);
            sort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    static void reverseArray(int[] intArray, int size)
    {
        int i, k, temp;
        for (i = 0; i < size / 2; i++) {
            temp = intArray[i];
            intArray[i] = intArray[size - i - 1];
            intArray[size - i - 1] = temp;
        }
    }

    public static List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<String>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }

    public static void showAndSaveChart(String title, int[] xAxis, double[][] yAxis) throws IOException {
        // Create Chart
        XYChart chart = new XYChartBuilder().width(800).height(600).title(title)
                .yAxisTitle("Time in Milliseconds").xAxisTitle("Input Size").build();

        // Convert x axis to double[]
        double[] doubleX = Arrays.stream(xAxis).asDoubleStream().toArray();

        // Customize Chart
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);

        // Add a plot for a sorting algorithm
        chart.addSeries("Insertion Sort", doubleX, yAxis[0]);
        chart.addSeries("Merge Sort", doubleX, yAxis[1]);
        chart.addSeries("Pigeonhole Sort", doubleX, yAxis[2]);
        chart.addSeries("Counting Sort", doubleX, yAxis[3]);

        // Save the chart as PNG
        BitmapEncoder.saveBitmap(chart, title + ".png", BitmapEncoder.BitmapFormat.PNG);

        // Show the chart
        new SwingWrapper(chart).displayChart();
    }
}