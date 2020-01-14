package data_structure;

import java.io.Serializable;

public class MyHashTable<K extends Comparable<K>, V> implements Serializable {

    private Entry<K, V>[] table;
    private double loadFactor;
    private int usedBucketSize;

    public MyHashTable() {
        this.table = new Entry[3];
        this.loadFactor = 0.75;
        this.usedBucketSize = 0;
    }

    public void put(K key, V value) {
        double lf = usedBucketSize * 1.0 / this.table.length;
        if (lf > this.loadFactor) {
            expand();
        }

        int idx = key.hashCode() % this.table.length;

        if (this.table[idx] == null) {
            this.table[idx] = new Entry<>(key, value, null);
            this.usedBucketSize++;
        } else {
            Entry<K, V> cur = this.table[idx];
            while (cur != null) {
                if (cur.key.compareTo(key) == 0) {
                    cur.value = value;
                    break;
                }
                cur = cur.next;
            }

            if (cur == null) {
                this.table[idx] = new Entry<>(key, value, this.table[idx]);
            }
        }
    }

    public V get(K key) {
        int idx = key.hashCode() % this.table.length;
        if (this.table[idx] == null) {
            return null;
        } else {
            Entry<K, V> cur = this.table[idx];
            while (cur != null) {
                if (cur.key.compareTo(key) == 0) {
                    return cur.value;
                }
                cur = cur.next;
            }
            return null;
        }
    }

    public void remove(K key) {
        int idx = key.hashCode() % this.table.length;

        if (this.table[idx] == null) {
            return;
        } else {
            Entry<K, V> pre = null;
            Entry<K, V> cur = this.table[idx];
            while (cur != null) {
                if (cur.key.compareTo(key) == 0) {
                    if (pre == null) {
                        this.table[idx] = cur.next;
                    } else {
                        pre.next = cur.next;
                    }

                    if (this.table[idx] == null) {
                        this.usedBucketSize--;
                    }
                    return;
                }
                pre = cur;
                cur = cur.next;
            }
        }
    }

    private void expand() {
        Entry<K, V>[] oldTable = this.table;
        this.usedBucketSize = 0;
        this.table = new Entry[2 * oldTable.length];

        for (int i = 0; i < oldTable.length; i++) {
            Entry<K, V> cur = oldTable[i];
            while (cur != null) {
                this.put(cur.key, cur.value);
                cur = cur.next;
            }
        }
    }

    public Entry<K, V>[] getTable() {
        return table;
    }

    static class Entry<K extends Comparable<K>, V> implements Serializable{
        K key;
        V value;
        Entry<K, V> next;

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

}
