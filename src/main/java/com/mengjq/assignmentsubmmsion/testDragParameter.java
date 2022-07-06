package com.mengjq.assignmentsubmmsion;

import java.io.IOException;

public class testDragParameter {
    public static void main(String[] args) throws IOException {
        // print the parameter of the system
        System.out.println("The parameter of the system:");
        System.out.println("The number of processors: \t" + Runtime.getRuntime().availableProcessors());
        System.out.println("The maximum memory: \t" + Runtime.getRuntime().maxMemory());
        System.out.println("The free memory: \t" + Runtime.getRuntime().freeMemory());
        System.out.println("The total memory: \t" + Runtime.getRuntime().totalMemory());
        System.out.println("The available processors: \t" + Runtime.getRuntime().availableProcessors());
        System.out.println("The maximum file length: \t" + Runtime.getRuntime().maxMemory());

        // print the program parameter
        System.out.println("\nThe program parameter:" + args.length);
        try {
            System.out.println("args[0]: " + args[0]);
            System.out.println("args[1]: " + args[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("\n\tThe program parameter is not enough.");
        }

        // wait for the user to press a key
        // print an echo for notify user that the program is running
        System.out.println("\n Press any key to continue...");
        System.in.read();
    }
}
