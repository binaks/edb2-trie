package com.binaks;

import java.util.Vector;

public class Trie {
    private Node root;

    public Trie() {
        root = new Node("");
        root.setParent(null);
    }

    public Node getRoot() {
        return root;
    }

    public void insertWord(String word) {
        Node current = root;

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
                return;
            }

            // se o no atual tem filhos, preciso testar um por um
            else {
                boolean isPrefix = false;

                for (Node child : current.getChildren()) {
                    // isso significa que tem um no com a letra da palavra que eu quero adicionar
                    if (letter.equals(child.getContent())) {
                        isPrefix = true;

                        // se a palavra inteira ja for prefixo de outra que ta la
                        if (i == word.length() - 1) {
                            child.setWord(true);
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
                    return;
                }
            }
        }
    }

    public boolean hasWord(String word) {
        Node current = root;

        // percorrendo cada letra da palavra a ser procurada
        for (int i = 0; i < word.length(); ++i) {
            String letter = String.valueOf(word.charAt(i));

            if (current.getChildren().isEmpty()) {
                return false;
            }

            boolean found = false;

            // a ideia é testar a letra com todos os filhos do nó atual
            for (Node child : current.getChildren()) {
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
        Node current = root;

        // se a palavra nao estiver na arvore, nao precisa fazer nada
        if (!this.hasWord(word)) {
            return;
        }

        Vector<Node> words = new Vector<>();

        // checando se a palavra é prefixo de outra
        for (int i = 0; i < word.length(); ++i) {
            String letter = String.valueOf(word.charAt(i));

            for (Node child : current.getChildren()) {
                // achar a palavra, ver se tem filhos
                if (letter.equals(child.getContent())) {
                    current = child;

                    // adicionando ao vetor todas as palavras anteriores à que quero remover
                    if (current.isWord() && i < word.length() - 1) {
                        words.add(current);
                    }

                    if (i == word.length() - 1) {
                        // se tiver filhos soh nao eh mais palavra
                        if (!current.getChildren().isEmpty()) {
                            current.setWord(false);
                        }
                        // mas se nao tiver, tem que apagar ate o ultimo no que era palavra ou root
                        else {
                            Node lastWord = new Node();

                            if (!words.isEmpty()) {
                                lastWord = words.lastElement();
                            }

                            Node parentOfCurrent = current.getParent();

                            while (current != lastWord && current != root) {
                                parentOfCurrent.removeChild(current);
                                current = parentOfCurrent;
                                parentOfCurrent = current.getParent();
                            }
                        }
                    }

                    break;
                }
            }
        }
    }

}




















