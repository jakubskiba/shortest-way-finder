package com.jskiba;

public class App {
    public static void main( String[] args )
    {
        try {
            new Controller().start(new UserInterface(System.out, System.in));
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
