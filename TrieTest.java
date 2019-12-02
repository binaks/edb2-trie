package com.binaks;

import org.junit.Test;

import static org.junit.Assert.*;

public class TrieTest {
    @Test
    public void testIsWord() {
        Trie trie = new Trie();

        assertFalse(trie.isWord("bla"));
        assertFalse(trie.isWord("1"));
//        assertTrue(trie.isWord("blabla"));
    }
}