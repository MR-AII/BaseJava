package com.topjava.webapp.storage;

import com.topjava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage {
    private int size;
    private Resume[] storage = new Resume[10_000];

    public void update(Resume resume) {
        String uuid = resume.getUuid();
        int index = getIndex(uuid);
        if (index != -1) {
            storage[index] = resume;
        } else {
            System.out.println(uuid + " отсутствует в хранилище.");
        }
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        String uuid = resume.getUuid();
        int index = getIndex(uuid);
        if (index != -1) {
            System.out.println(uuid + " уже существует.");
        } else if (size != storage.length){
            storage[size] = resume;
            size++;
        } else {
            System.out.println("Хранилище переполнено.");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            return storage[index];
        } else {
            System.out.println(uuid + " отсутствует в хранилище");
        }
        return null;
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            size--;
            if (size - index >= 0) System.arraycopy(storage, index + 1, storage, index, size - index);
        } else {
            System.out.println(uuid + " отсутствует в хранилище");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}