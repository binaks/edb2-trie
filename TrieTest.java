package com.binaks;

import org.junit.Test;

import static org.junit.Assert.*;

public class TrieTest {
    @Test
    public void testIsWord() {
        Trie trie = new Trie();
        trie.insertWord("tapi");

        assertFalse(trie.hasWord(""));
        assertFalse(trie.hasWord("ok"));
        assertFalse(trie.hasWord("ta"));
        assertFalse(trie.hasWord("tapia"));
        assertTrue(trie.hasWord("tapi"));
    }
}