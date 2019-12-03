package com.binaks;

import org.junit.Test;

import java.util.Vector;

import static org.junit.Assert.*;

public class TrieTest {
    @Test
    public void testInsertAndRemove() {
        Trie trie = new Trie();
        trie.insertWord("tapi");
        trie.insertWord("tapioca");
        trie.insertWord("tap");
        trie.insertWord("fuligi");

        assertFalse(trie.hasWord(""));
        assertFalse(trie.hasWord("ok"));
        assertFalse(trie.hasWord("ta"));
        assertFalse(trie.hasWord("tapia"));
        assertTrue(trie.hasWord("tapi"));
        assertTrue(trie.hasWord("tap"));
        assertTrue(trie.hasWord("tapioca"));
        assertTrue(trie.hasWord("fuligi"));

        trie.deleteWord("tapi");
        assertFalse(trie.hasWord("tapi"));

        trie.deleteWord("tapioca");
        assertTrue(trie.hasWord("tap"));
        assertFalse(trie.hasWord("tapioca"));

        trie.deleteWord("tap");
        trie.deleteWord("fuligi");

        assertTrue(trie.getRoot().getChildren().isEmpty());
    }

    @Test
    public void testText() {
        Trie trie = new Trie();
        trie.insertWord("ta");

        assertTrue(trie.getRoot().getChildren().elementAt(0).getChildren().elementAt(0).getText().equals("ta"));
        assertFalse(trie.getRoot().getChildren().elementAt(0).getText().equals("t"));
    }

    @Test
    public void testFindWordsAfter() {
        Trie trie = new Trie();
        trie.insertWord("tapi");
        trie.insertWord("tapioca");
        trie.insertWord("tap");
        trie.insertWord("fuligi");

        Vector<String> words = new Vector<>();
        assertFalse(trie.findWordsAfter(trie.getRoot(), words).contains("ta"));
        assertTrue(trie.findWordsAfter(trie.getRoot(), words).contains("tap"));
    }

    @Test
    public void testAutoComplete() {
        Trie trie = new Trie();
        trie.insertWord("tapi");
        trie.insertWord("tapioca");
        trie.insertWord("tap");
        trie.insertWord("fuligi");

        assertTrue(trie.autoComplete("ta").contains("tap"));
        assertTrue(trie.autoComplete("ta").contains("tapi"));
        assertTrue(trie.autoComplete("ta").contains("tapioca"));
        assertFalse(trie.autoComplete("ta").contains("tapio"));
        assertFalse(trie.autoComplete("ta").contains("fuligi"));
    }
}