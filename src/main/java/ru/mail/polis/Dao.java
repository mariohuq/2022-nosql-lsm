package ru.mail.polis;

import java.util.Iterator;

public interface Dao<Data, E extends Entry<Data>> {

    /**
     * Returns ordered iterator of entries with keys between from (inclusive) and to (exclusive).
     * @param from lower bound of range (inclusive)
     * @param to upper bound of range (exclusive)
     * @return entries [from;to)
     */
    Iterator<E> get(Data from, Data to);

    /**
     * Returns entry by key. Note: default implementation is far from optimal.
     * @param key entry`s key
     * @return entry
     */
    default E get(Data key) {
        Iterator<E> iterator = get(key, null);
        if (!iterator.hasNext()) {
            return null;
        }
        E next = iterator.next();
        if (next.key().equals(key)) {
            return next;
        }
        return null;
    }

    /**
     * Returns ordered iterator of all entries with keys from (inclusive)
     * @param from lower bound of range (inclusive)
     * @return entries with key >= from
     */
    default Iterator<E> allFrom(Data from) {
        return get(from, null);
    }

    /**
     * Returns ordered iterator of all entries with keys < to
     * @param to upper bound of range (exclusive)
     * @return entries with key < to
     */
    default Iterator<E> allTo(Data to) {
        return get(null, to);
    }

    /**
     * Returns ordered iterator of all entries
     * @return all entries
     */
    default Iterator<E> all() {
        return get(null, null);
    }

    /**
     * Inserts of replaces entry
     * @param entry element to upsert
     */
    void upsert(E entry);

}
