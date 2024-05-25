package com.example.geektrust;

    import java.io.BufferedReader;
    import java.io.FileReader;
    import java.io.IOException;
    import java.util.Arrays;
    import java.util.LinkedList;
    import java.util.List;

    import com.example.geektrust.appConfig.ApplicationConfig;
    import com.example.geektrust.commands.CommandInvoker;
    import com.example.geektrust.exceptions.NoSuchCommandException;

    public class Main {

    	public static void main(String[] args) {
    		List<String> commandLineArguments = new LinkedList<>(Arrays.asList(args));
		    run(commandLineArguments);
    	}
    	public static void run(List<String> commandLineArgs) {
            ApplicationConfig applicationConfig = new ApplicationConfig();
            CommandInvoker commandInvoker = applicationConfig.getCommandInvoker();
            BufferedReader reader;
            String inputFile = commandLineArgs.get(0);
            try {
                reader = new BufferedReader(new FileReader(inputFile));
                String line = reader.readLine();
                while (line != null) {
                    List<String> tokens = Arrays.asList(line.split(" "));
                    commandInvoker.executeCommand(tokens.get(0),tokens);
                    line = reader.readLine();
                }
                reader.close();
            } catch (IOException | NoSuchCommandException e) {
                e.printStackTrace();
            }

       }

    }