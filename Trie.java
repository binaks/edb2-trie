package com.binaks;

import jdk.dynalink.beans.StaticClass;

import java.util.Stack;
import java.util.Vector;

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode("");
    }

    public TrieNode getRoot() {
        return root;
    }

    public void insertWord(String word) {
        TrieNode current = root;

        // se a palavra ja tá na árvore, nao precisa fazer nada
        if (hasWord(word)) {
            return;
        }

        // precisa checar se tem prefixo ja na arvore
        for (int i = 0; i < word.length(); ++i) {
            String letter = String.valueOf(word.charAt(i));

            // aqui eh se chega em uma parte que nao tem mais nada pra baixo na arvore
            if (current.getChildren().isEmpty()) {
                // faz sentido ficar nesse loop só criando o resto dos filhos
                while (i < word.length()) {
                    letter = String.valueOf(word.charAt(i));
                    current.addChild(letter);
                    current = current.getChildren().elementAt(0);
                    i++;
                }
                // sai do loop, vira uma palavra mesmo e sai
                current.setWord(true);
                current.setText(word);
                return;
            }

            // se o no atual tem filhos, preciso testar um por um
            else {
                boolean isPrefix = false;

                for (TrieNode child : current.getChildren()) {
                    // isso significa que tem um no com a letra da palavra que eu quero adicionar
                    if (letter.equals(child.getContent())) {
                        isPrefix = true;

                        // se a palavra inteira ja for prefixo de outra que ta la
                        if (i == word.length() - 1) {
                            child.setWord(true);
                            child.setText(word);
                            return;
                        }

                        current = child;
                        // se ja achou um que é igual, não precisa mais procurar nos outros
                        break;
                    }
                }

                // se chega aqui eh porque nao eh prefixo de nenhuma palavra
                if (!isPrefix) {
                    while (i < word.length()) {
                        letter = String.valueOf(word.charAt(i));
                        current.addChild(letter);
                        current = current.getChildren().lastElement();
                        i++;
                    }

                    current.setWord(true);
                    current.setText(word);
                    return;
                }
            }
        }
    }

    public boolean hasWord(String word) {
        TrieNode current = root;

        // percorrendo cada letra da palavra a ser procurada
        for (int i = 0; i < word.length(); ++i) {
            String letter = String.valueOf(word.charAt(i));

            if (current.getChildren().isEmpty()) {
                return false;
            }

            boolean found = false;

            // a ideia é testar a letra com todos os filhos do nó atual
            for (TrieNode child : current.getChildren()) {
                // se achar uma letra igual, o current vira esse nó e sai desse loop aqui pra procurar se tem a prox letra
                if (letter.equals(child.getContent())) {
                    // se chegou no final da palavra a ser procurada, retorna se é uma palavra ou não
                    if (i == word.length() - 1) {
                        return child.isWord();
                    }
                    current = child;
                    found = true;
                    break;
                }
            }

            // se nao teve nenhum filho com a letra igual, entao nao é palavra
            if (!found) return false;
        }

        return false;
    }

    public void deleteWord(String word) {
        TrieNode current = root;

        // se a palavra nao estiver na arvore, nao precisa fazer nada
        if (!this.hasWord(word)) {
            return;
        }

        Stack<TrieNode> pastLetters = new Stack<>();
        Vector<TrieNode> pastWords = new Vector<>();

        // checando se a palavra é prefixo de outra
        for (int i = 0; i < word.length(); ++i) {
            String letter = String.valueOf(word.charAt(i));

            for (TrieNode child : current.getChildren()) {
                // achar a palavra, ver se tem filhos
                if (letter.equals(child.getContent())) {
                    pastLetters.push(current);
                    current = child;

                    // adicionando ao vetor todas as palavras anteriores à que quero remover
                    if (current.isWord() && i < word.length() - 1) {
                        pastWords.add(current);
                    }

                    if (i == word.length() - 1) {
                        // se tiver filhos soh nao eh mais palavra
                        if (!current.getChildren().isEmpty()) {
                            current.setWord(false);
                            current.setText("");
                        }
                        // mas se nao tiver, tem que apagar ate o ultimo no que era palavra ou root
                        else {
                            TrieNode lastWord = new TrieNode();

                            if (!pastWords.isEmpty()) {
                                lastWord = pastWords.lastElement();
                            }

                            while (current != lastWord && current != root) {
                                TrieNode parentOfCurrent = pastLetters.peek();

                                parentOfCurrent.removeChild(current);
                                current = parentOfCurrent;

                                pastLetters.pop();
                            }
                        }
                    }

                    break;
                }
            }
        }
    }

    public Vector<String> autoComplete(String prefix) {
        TrieNode current = root;
        Vector<String> words = new Vector<>();

        for (int i = 0; i < prefix.length(); ++i) {
            String letter = String.valueOf(prefix.charAt(i));

            for (TrieNode child : current.getChildren()) {
                if (letter.equals(child.getContent())) {
                    current = child;
                    break;
                }
            }
        }

        return findWordsAfter(current, words);
    }

    public Vector<String> autoComplete(String prefix, int qty) {
        Vector<String> words = autoComplete(prefix);

        if (qty < words.size()) {
            words.setSize(qty);
        }

        return words;
    }

    public Vector<String> findWordsAfter(TrieNode node, Vector<String> words) {
        if (node.isWord()) {
            words.add(node.getText());
        }

        if (node.getChildren().isEmpty()) {
            return words;
        }

        for (TrieNode child : node.getChildren()) {
            findWordsAfter(child, words);
        }

        return words;
    }
}




















