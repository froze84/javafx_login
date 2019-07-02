
import java.io.*;

import java.util.*;
import java.math.BigInteger;
public class NewClass{
    public static int func(int n) {
      BigInteger b1,b2,b3;
       b2=new BigInteger("1000000007");
       BigInteger f = new BigInteger("1");
       for (int i = 1; i <= n; i++) {
           f = f.multiply(new BigInteger(i + ""));
           f=f.mod(b2);
       }
      
       b3=f.mod(b2);
      return b3.intValue();
   }
  public static void main(String[] args){
      BigInteger a1,a2,a3,a4;
    a2=new BigInteger("1");
    a4=new BigInteger("1000000007");
    Scanner in= new Scanner(System.in);
    int n=in.nextInt();
    int[] arr=new int[n];
    for(int i=0;i<n;i++)
    {
        arr[i]=in.nextInt();
    }
    int q;
    q=in.nextInt();
    for(int j=0;j<q;j++)
    {
    int l=in.nextInt();
    int r=in.nextInt();
        if(l==r)
        {
            System.out.println(1);
        }
        else
        {
          for(int k=l-1;k<r;k++)
     	  { 
     	      int o = func(arr[k]);
     	     //ystem.out.println("here"+ arr[k]);
            a1=BigInteger.valueOf(o);
          	a3=a1.multiply(a2);
          	a2=a3;
          }
          a2=a2.mod(a4);
          for(int m=0;m<(r-l-1);m++)
          {
          a2=a2.multiply(a2);
        //System.out.println("here1" + a2.toString());
          }
          a2=a2.mod(a4);
          int h=a2.intValue();
          System.out.println(h);
          
        }
        
    }
  }
}
    
    
    
   
  

      