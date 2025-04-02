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
    public void insert(char element, int index) {
        if (index < 0 || index > currentLength) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        ensureCapacity();
        System.arraycopy(characterBuffer, index, characterBuffer, index + 1, currentLength - index);
        characterBuffer[index] = element;
        currentLength++;
    }
    public char delete(int index) {
        if (index < 0 || index >= currentLength) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        char removed = characterBuffer[index];
        System.arraycopy(characterBuffer, index + 1, characterBuffer, index, currentLength - index - 1);
        currentLength--;
        return removed;
    }

    public void deleteAll(char element) {
        int newSize = 0;
        for (int i = 0; i < currentLength; i++) {
            if (characterBuffer[i] != element) {
                characterBuffer[newSize++] = characterBuffer[i];
            }
        }
        currentLength = newSize;
    }
    public InitialImplementation clone() {
        InitialImplementation clonedList = new InitialImplementation();
        clonedList.characterBuffer = Arrays.copyOf(this.characterBuffer, this.currentLength);
        clonedList.currentLength = this.currentLength;
        return clonedList;
    }

    public void reverse() {
        for (int i = 0; i < currentLength / 2; i++) {
            char temp = characterBuffer[i];
            characterBuffer[i] = characterBuffer[currentLength - 1 - i];
            characterBuffer[currentLength - 1 - i] = temp;
        }
    }
    public int findFirst(char element) {
        for (int i = 0; i < currentLength; i++) {
            if (characterBuffer[i] == element) {
                return i;
            }
        }
        return -1;
    }

    public int findLast(char element) {
        for (int i = currentLength - 1; i >= 0; i--) {
            if (characterBuffer[i] == element) {
                return i;
            }
        }
        return -1;
    }
    public char get(int position) {
        if (position < 0 || position >= currentLength) {
            throw new IndexOutOfBoundsException("Invalid position");
        }
        return characterBuffer[position];
    }
}
