/**
 * ReadFEN.java
 * A program for converting chess positions as input in FEN to 2D ASCHII representation
 * Homerwork 2, problem 1
 */

/**
 * import Arrays to use various methods for manupulating arrays
 * import Scanner to read user input
 * import File to read input File
 * import StringBuilder to build up our output
 * import FilenotFoundException to raise Exception when file input does not exist
 */

import java.util.Arrays ;
import java.util.Scanner;
import java.io.File;
import java.lang.StringBuilder;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class ReadFEN{

    /**
     * main function that uses all other methods below to produce the 2D Array of FEN
     * prints out the final output if the input was a user input
     * returns file if input as a file
     * @param args
     */

    public static void main(String[] args){
        String fen = read_input(args);
        String [] fen_arr = fen.split("/");
        if(check_rows(fen_arr) && check_worth(fen_arr) && check_validity(fen) && check_digits(fen_arr)){
            String output = build_output(fen_arr);
            if(args.length>0){
                try{
                    FileWriter output_file = new FileWriter(args[1]);
                    output_file.write(output);
                    output_file.close();

                }catch(IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                }
            }else{
            System.out.println(output);
            }
        }else{
            System.out.println("invalid input");
        }
    }

    /**
     * read_input method that checks if the input was a file or user input
     * based on type of input reads and returns the both of them in the same format as a stirng
     * if file does not exist rises exception
     * @param args
     * @return
     */

    private static String read_input(String[] args){
        Scanner input=null;
        if(args.length>0){
            try{input = new Scanner(new File(args[0]));
            }catch(FileNotFoundException fnfe){
                System.err.println("File does not exist");
                System.exit(1);
            }
        }else{
            System.out.println("Please enter a FEN:");
            input=new Scanner(System.in);
        }
        return input.nextLine();
    }

    /**
     * check_rows checks if the input contains excatly 8 rows
     * returns true of number of rows equals to 8
     * @param fen_arr
     * @return
     */

    private static boolean check_rows(String[] fen_arr){
        boolean output = true;
        if(fen_arr.length!=8){
            output = false;
        }
        return output;
    }

    /**
     * check_worth checks if each row contains exactly information worth 8 columns
     * return true if valye of information equals to 8
     * @param fen_arr
     * @return
     */

    private static boolean check_worth(String[] fen_arr){
        boolean output = true;
        for(int m = 0; m < fen_arr.length; m++){
            int value=0;
            for(int d = 0; d < fen_arr[m].length(); d++){
                if(Character.isDigit(fen_arr[m].charAt(d))){
                    value=value+(Character.getNumericValue(fen_arr[m].charAt(d)));
                }else{
                    value=value+1;}
                }
            if(value!=8){
                output = false;
            }
        }
        return output;
    }

    /**check_validity checks if all charecters used in input are a part of given valid string
     * return true if all charecter are part of valid string
     * @param fen
     * @return
     */

    private static boolean check_validity(String fen){
        boolean output = true;
        String valid = "pnbrqkPNBRQK12345678/";
        for(int t = 0; t < fen.length(); t++){
            int validity=valid.indexOf(fen.charAt(t));
            if(validity==-1){
                output = false;
            }
        }
        return output;
    }

    /**
     * check_digits checks if there is another digit after a digit
     * return true if there is no row where a digit is given right after another digit
     * @param fen_arr
     * @return
     */

    private static boolean check_digits(String[] fen_arr){
        boolean output = true;
        for(int v = 0; v < fen_arr.length; v++){
            for(int w = 0; w < (fen_arr[v].length()-1); w++){
                if(Character.isDigit(fen_arr[v].charAt(w)) && Character.isDigit(fen_arr[v].charAt(w+1))){
                    output = false;
                }
            }
        }
        return output;
    }

    /**
     * build_output method uses StringBuilder to build final output
     * final output returns a digit as a number of dots, amout of which equals to numerica value of a digit
     * returns a letter as a letter
     * retruns each row from the new line
     * @param fen_arr
     * @return
     */

    private static String build_output(String[] fen_arr){
        StringBuilder sb = new StringBuilder();
            for(int i = 0; i < 8; i++){
                 for(int j = 0; j < fen_arr[i].length(); j++){
                     if(Character.isDigit(fen_arr[i].charAt(j))){
                        for(int k = 0; k < Character.getNumericValue(fen_arr[i].charAt(j)); k++){
                            sb.append(".");
                        }
                    }else{sb.append(fen_arr[i].charAt(j));
                    }
                    }
                 sb.append("\n");
            }
        return sb.toString();

    }
}

//for checking purposes: 8/kb6/p7/2K5/8/5N2/8/8/
