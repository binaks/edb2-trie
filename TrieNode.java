package com.binaks;

import java.util.Vector;

public class TrieNode {
    private boolean isWord;
    private Vector<TrieNode> children;
    private String content;
    private String text;

    public TrieNode() {
        this.children = new Vector<>();
        this.isWord = false;
        this.text = "";
    }

    public TrieNode(String content) {
        this.content = content;
        this.children = new Vector<>();
        this.isWord = false;
        this.text = "";
    }

    public Vector<TrieNode> getChildren() {
        return children;
    }

    public String getContent() {
        return content;
    }

    public boolean isWord() {
        return isWord;
    }

    public void setWord(boolean word) {
        isWord = word;
    }

    public void addChild(String content) {
        TrieNode child = new TrieNode(content);
        this.children.add(child);
    }

    public void removeChild(TrieNode child) {
        this.children.remove(child);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}