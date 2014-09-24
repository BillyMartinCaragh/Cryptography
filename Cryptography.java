/**
 * Write a description of class Crypto here.
 * 
 * @author Billy Martin 12406742
 * @version 27/03/2014
 */
public class crypto

{

public static void main(String[]args)

{

     long start = System.currentTimeMillis();
     long key = pKey(2744,24852977,8414508);

     System.out.println("Private Key is "+key); //PRINT OUT PRIVATE KEY
     System.out.println("Message is "+msg(key,24852977,15268076,743675));//MESSAGE
     

     long end = System.currentTimeMillis() - start;

     System.out.println(end+"milliseconds");

}



public static long modPow(long number, long power, long modulus)

{
//raises a number to a power with the given modulus
//when raising a number to a power, the number quickly becomes too large to handle
//you need to multiply numbers in such a way that the result is consistently moduloe d to keep it in the range
//however you want the algorithm to work quickly - having a multiplication loop would result in an O(n) algorithm!
//the trick is to use recursion-keep breaking the problem down into smaller pieces and use the modMult method to join them back together

    if(power==0) //ANYTHING TO THE POWER OF 0 IS 1
     {
        return 1;
    }
            else if (power%2==0)

                {

                    long halfpower=modPow(number, power/2, modulus); 

                    return modMult(halfpower,halfpower,modulus);

                }

                else

                    {

                        long halfpower=modPow(number, power/2, modulus);
                        long firstbit = modMult(halfpower,halfpower,modulus);
                        
                        return modMult(firstbit,number,modulus);

                    }

}






public static long modMult(long first, long second, long modulus)

{

//multiplie s the first number by the second number with the given modulus
//a long can have a maximum of 19 digits. Therefore, if you're multiplying two ten digits numbers the usual way, things will go wrong
//you need to multiply numbers in such a way that the result is consistently moduloed to keep it in the range
//however you want the algorithm to work quickly - having an addition loop would result in an O(n) algorithm!
//the trick is to use recursion - keep breaking down the multiplication into smaller pieces and mod each of the pieces individually


   if(second==0)
  {
      return 0;
    }
    

        else if (second%2==0)

        {

                long half=modMult(first, second/2, modulus);
                
                return (half+half)%modulus;

            }

            else

                {

                    long half=modMult(first, second/2, modulus);
                    
                    return (half+half+first)%modulus;

                }

}






public static long pKey(long number, long modulus, long result1)

{

    long key = 0;
    long result2 = 1;

    while(true)

    {

        key++; //BRUTE FORCE
        result2 = result2*number; //DOING CALCULATIONS
        result2 = result2%modulus;//KEEPING IT DOWN
        
        
        if(result2 == result1)//ACTUAL ANWSER

        {

           return key; //POWER

       }

    }

}





public static long msg(long key, long modulus, long c1, long c2)

   {

    long power = modulus-1-key; 
    long result = (modPow(c1,power,modulus)*c2)%modulus; //FOLLOWING FORMULAE

    return result; //ENCRYPTED MESSAGE

  }

}
