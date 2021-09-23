package com.ngng.bebot;

public interface Transposable <T> {
    /**
     * @param midiValue
     * @return Transposable
     */
    public T transpose(int midiValue);
}
