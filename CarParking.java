//===========================================================================================
// PROGRAMMER:      Eren Ozbay
// PANTHER ID:      2452687
//
// CLASS:           COP3337 
// SEMESTER:        Spring 2021
// Language:        Java
//
// Project:         Project 7
// DATE:            April 18 2021
// Java Version:    13.0.2
//
// Description:     Creating a FIU parking lot program that uses stacks and 
//                  queues to control the parking of cars
//============================================================================================
import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class CarParking {


    public static void main(String[] args) {
        //creates a stack for the parking lot and street, and queue for the
        //cars on the street
        Stack<Integer> parkingLot = new Stack<>(); 
        Stack<Integer> street = new Stack<>();
       Queue<Integer> carsQueue = new LinkedList<>();
        Scanner input = new Scanner(System.in);
        int licensePlate;
        System.out.println("Welcome to the FIU parking garage");

        for(;;){ //infinite for loop
        System.out.println("Please type the decal you have, only decal \"FIU\" will be allowed in");
        String decal = input.nextLine().toUpperCase(); // converts user input to uppercase
        
        if(decal.equals("FIU")){
            System.out.println("");
            System.out.println("If you are parking a car please type the licence plate number: ");
            System.out.println("If you are removing your car please type your licence plate number"
                                 + " with a negative - symbol in front of it");
            System.out.println("Type 0 to stop the program");
            //try to convert input string to integer, gives a catch if user does not input integer
            try{       
                 licensePlate = Integer.parseInt(input.nextLine());
         
            if(licensePlate > 0){   //puts users car by plate # in the lot or queue if full
                if(parkingLot.size() <20){  //adds car to lot if lot is not full
                    parkingLot.push(licensePlate);  
                }else{                      //adds car to queue if lot is full
                    carsQueue.add(licensePlate);
                }    
            }else if (licensePlate < 0 ){   //removes the persons car
                licensePlate = licensePlate * -1;      
                if (parkingLot.contains(licensePlate)){     //checks to see if car is in lot before removal
                    while(parkingLot.peek() != licensePlate){   //moves car to street until car is found
                            street.push(parkingLot.peek());     //moves top car to street list
                            parkingLot.pop();                   //removes top car from parkinglot list
                    }
                    parkingLot.pop();                   //removes users inputed plate number
                    while(!street.empty()){             //puts cars back into parking lot until street is empty
                        parkingLot.push(street.peek()); //pushes car from street to parking lot
                        street.pop();                   //removes car from street after being pushed to lot
                    }
                    //moves next car from the queue to parking lot if the queue is not empty
                    if(!carsQueue.isEmpty()){        
                    parkingLot.push(carsQueue.peek());
                    carsQueue.remove();
                    }
                }else{
                    System.out.println("Sorry, That car is not in the parking lot");
                }
            }else if (licensePlate == 0){           //ends program is user inputs 0
				break;
            }
                System.out.println("");
                System.out.println("Current cars in parking lot: " + parkingLot);
                System.out.println("Current cars in Queue:" + carsQueue);
            }catch (NumberFormatException nfe){     //gives a message if user did not input an integer
                System.out.println("inputed value is not an integer");
            }
        }else{
            System.out.println("Sorry, you do not have the correct decal to use the garage");
        }
        }
    
    }
}

