import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Interview {

 /*   Replace each element in an integer array with the multiplication of all other elements of array.
    e.g.
            Input = {2, 1, 4, 9}
    Output = {36, 72, 18, 8}

1. Identify test cases.
2. Write a program/function for the logic.*/

    // 1. Verify if the input int array has greater than 0 elements
    //2. Verify if all numbers present in the integer array are within Integer range values as per java.
    //3. Verify if the input array passed should be of integer type
    //4. Validate if multiplication of other elements doesnt exceed Integer value range as per java.
    //5. Verify if the function created produces the same output int array as expected.

    public static int[] getMultipliedArray(int [] num)
    {
        if(num.length==0)
        {
            return new int[]{};
        }
        int[] resultArray = new int[num.length];
        int multiplyNum = 1;

        List<Integer> inputElements = new ArrayList<>();
        for(int n:num)
        {
            inputElements.add(n);
        }



        for(int i=0;i<=num.length-1;i++)
        {
            inputElements.remove(i);
           multiplyNum = inputElements.stream().reduce((x,y)->x*y).get();
           resultArray[i]=multiplyNum;
           System.out.println("current:"+multiplyNum);
            inputElements.add(i,num[i]);
        }
        return resultArray;

    }

    public static int[] getMultipliedArray2(int[] num)
    {

        int left =0;
        int right = num.length-1;
        int multiply=1;
        int resultArray[] = new int[num.length];

        for(int i=0;i<num.length;i++)
        {
            int temp =num[i];
            num[i]=1;
           while (left<right)
           {


             multiply *= num[left]*num[right];
             left++;
             right--;
           }
            resultArray[i]=multiply;
           left=0;
           right=num.length-1;
           multiply=1;
           num[i]=temp;

        }
        return  resultArray;
    }

    public static void main(String[] args)
    {
        int arr[] = new int[] {2, 1, 4, 9};
       // System.out.println(Arrays.toString(getMultipliedArray(arr)));
        System.out.println(Arrays.toString(getMultipliedArray2(arr)));
    }
}
