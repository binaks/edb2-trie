package com.binaks;

import java.io.File;
import java.util.Scanner;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {
	    String fileName = args[0];
        String prefix = args[1];
        int qty = -1;

        if (args.length == 3) {
            qty = Integer.parseInt(args[2]);
        }

        Trie trie = new Trie();

        File file = new File(fileName);

        try {
            Scanner sc = new Scanner (file);

            while (sc.hasNextLine()) {
                String word = sc.nextLine();
                trie.insertWord(word);
            }

        } catch(Exception e){System.out.println(e);}

        Vector<String> words;

        if (qty >= 0) {
            words = trie.autoComplete(prefix, qty);
        } else {
            words = trie.autoComplete(prefix);
        }

        for (String word : words) {
            System.out.println(word);
        }
    }
}