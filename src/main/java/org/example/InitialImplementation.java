package org.example;

import java.util.Arrays;

public class InitialImplementation {
    private char[] characterBuffer;
    private int currentLength;
    private static final int DEFAULT_CAPACITY = 10;

    public InitialImplementation() {
        this.characterBuffer = new char[DEFAULT_CAPACITY];
        this.currentLength = 0;
    }

    public int length() {
        return currentLength;
    }

    public void append(char newCharacter) {
        ensureCapacity();
        characterBuffer[currentLength++] = newCharacter;
    }

    private void ensureCapacity() {
        if (currentLength == characterBuffer.length) {
            characterBuffer = Arrays.copyOf(characterBuffer, characterBuffer.length * 2);
        }
    }

    public char get(int position) {
        if (position < 0 || position >= currentLength) {
            throw new IndexOutOfBoundsException("Invalid position");
        }
        return characterBuffer[position];
    }
}
