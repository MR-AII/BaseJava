package com.topjava.webapp.storage.arrayStorage;

import com.topjava.webapp.exception.ExistStorageException;
import com.topjava.webapp.exception.NotExistStorageException;
import com.topjava.webapp.exception.StorageException;
import com.topjava.webapp.model.Resume;
import com.topjava.webapp.storage.AbstractStorage;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void updateResume(Resume resume, Object searchKey) {
        storage[(int) searchKey] = resume;
    }

    @Override
    public void saveResume(Resume resume, Object searchKey) {
        if (size != STORAGE_LIMIT) {
            insertElement(resume, (int) searchKey);
            size++;
        } else {
            throw new StorageException("Хранилище переполнено", resume.getUuid());
        }
    }

    @Override
    public Resume getResume(Resume resume, Object searchKey) {
        return storage[(int) searchKey];
    }

    @Override
    public void deleteResume(Resume resume, Object searchKey) {
        int indx = (int) searchKey;
        size--;
        if (size - indx >= 0) System.arraycopy(storage, indx + 1, storage, indx, size - indx);
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected boolean isExist(Object index) {
        return (int) index >= 0;
    }

    protected abstract Object getKey(String uuid);

    protected abstract void insertElement(Resume resume, int index);
}