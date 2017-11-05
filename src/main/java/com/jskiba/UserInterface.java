package com.jskiba;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    PrintStream output;
    InputStream input;

    public UserInterface(PrintStream out, InputStream in) {
        this.output = out;
        this.input = in;
    }

    public List<String> getData() {
        List<String> lines = new ArrayList<>();
        Scanner in = new Scanner(this.input);
        while (in.hasNextLine()) {
            lines.add(in.nextLine());
        }
        return lines;
    }

    public void presentData(Node to) {
        this.output.println("Route:");
        to.getRoute().forEach(this.output::println);

        this.output.println("Distance:");
        this.output.println(to.getDistance());
    }
}
