package com.binaks;

import org.junit.Test;

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
}