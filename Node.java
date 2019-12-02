package com.binaks;

import java.util.Vector;

public class Node {
    private Vector<Node> children;
    private String content;
    private boolean isWord;

    public Node() {
        this.children = new Vector<>();
        this.isWord = false;
    }

    public Node(String content) {
        this.content = content;
        this.children = new Vector<>();
        this.isWord = false;
    }

    public Vector<Node> getChildren() {
        return children;
    }

    public void setChildren(Vector<Node> children) {
        this.children = children;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isWord() {
        return isWord;
    }

    public void setWord(boolean word) {
        isWord = word;
    }

    public void addChild(String content) {
        Node child = new Node(content);
        this.children.add(child);
    }
}
