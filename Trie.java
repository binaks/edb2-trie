package com.binaks;

public class Trie {
    private Node root;

    public Trie() {
        root = new Node("");
    }

    public void insertWord(String word) {
        Node current = root;

        if (isWord(word)) {
            return;
        }

        if (current.getChildren().isEmpty()) {
            // TODO: adiciona cada letra
        }

        for (int i = 0; i < word.length(); ++i) {
            String letter = String.valueOf(word.charAt(i));

            for (Node child : current.getChildren()) {
                if (letter.equals(child.getContent())) {

                    current = child;
                    break;
                }
            }

            if (!letter.equals(current.getContent())) {
                Node p = new Node(letter);
                current = p;
            }
        }

    }

    public boolean isWord(String word) {
        Node current = root;

        // percorrendo cada letra da palavra a ser procurada
        for (int i = 0; i < word.length(); ++i) {
                String letter = String.valueOf(word.charAt(i));

            if (current.getChildren().isEmpty()) {
                return false;
            }

            // a ideia é testar a letra com todos os filhos do nó atual
            for (Node child : current.getChildren()) {
                // se achar uma letra igual, o current vira esse nó e sai desse loop aqui pra procurar se tem a prox letra
                if (letter.equals(child.getContent())) {
                    // se chegou no final da palavra a ser procurada, retorna se é uma palavra ou não
                    if (i == word.length() - 1) {
                        return current.isWord();
                    }
                    current = child;
                    break;
                }
            }

            // se nao teve nenhum filho com a letra igual, entao nao é palavra
            if (!letter.equals(current.getContent())) return false;
        }

        return false;
    }

    public void deleteWord(String word) {

    }

}