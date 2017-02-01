package com.devshawn.coloring;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.Assertion;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.io.*;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class MainSpec {

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Test
    public void testExit() {
        exit.expectSystemExitWithStatus(0);

        String input = "3\n";
        StringWriter output = new StringWriter();
        Main.main(new Scanner(input), output);
    }

    @Test
    public void generateGraph() {
        exit.expectSystemExitWithStatus(0);
        exit.checkAssertionAfterwards(() -> assertThat(Main.outputString.toString(), containsString("Colors used: 1")));

        String input = "2\n"
                + "1\n" // 1 vertex
                + "0\n\n"
                + "3\n";

        StringWriter output = new StringWriter();
        Main.main(new Scanner(input), output);
    }

    @Test
    public void enterGraph() {
        exit.expectSystemExitWithStatus(0);
        exit.checkAssertionAfterwards(() -> {
            assertThat(Main.outputString.toString(), containsString("Colors used: 2"));
            assertThat(Main.outputString.toString(), containsString("Color 0 => 1"));
            assertThat(Main.outputString.toString(), containsString("Color 1 => 1"));
            assertThat(Main.outputString.toString(), containsString("Vertex 0 => color 0"));
            assertThat(Main.outputString.toString(), containsString("Vertex 1 => color 1"));
        });

        String input = "1\n"
                + "2\n"
                + "01\n"
                + "10\n\n"
                + "3\n";

        StringWriter output = new StringWriter();
        Main.main(new Scanner(input), output);
    }

    @Test
    public void invalidOption() {
        exit.expectSystemExitWithStatus(0);
        exit.checkAssertionAfterwards(() -> {
            assertThat(Main.outputString.toString(), containsString("Please choose an option."));
        });

        String input = "4\n"
                + "3\n";

        StringWriter output = new StringWriter();
        Main.main(new Scanner(input), output);
    }
}
