/**
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Pulic License version 3.0.
 * http://www.gnu.org/licenses/gpl-3.0.de.html
 *
 */
/**
 * @author besting
 * @Copyright (C) SurgiTAIX AG
 */
package org.ornet.sdclib.common;

import java.util.LinkedHashMap;
import java.util.Map;

public class MaxHashMap<K, V> extends LinkedHashMap<K, V> {

    public interface RemoveEldestHandler<K, V> {

        void handle(Map.Entry<K, V> entry);

    }

    private int maxSize;
    private RemoveEldestHandler<K, V> removeEldestHandler;

    public MaxHashMap(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        boolean b = size() > getMaxSize();
        if (b && removeEldestHandler != null) {
            removeEldestHandler.handle(eldest);
        }
        return b;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public void touch(K key) {
        V val = remove(key);
        put(key, val);
    }

}
