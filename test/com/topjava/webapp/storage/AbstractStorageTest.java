package com.topjava.webapp.storage;

import com.topjava.webapp.exception.ExistStorageException;
import com.topjava.webapp.exception.NotExistStorageException;
import com.topjava.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractStorageTest {
    protected final Storage storage;

    private static final String UUID_1 = "UUID_1";
    private final Resume resume1 = new Resume(UUID_1);

    private static final String UUID_2 = "UUID_2";
    private final Resume resume2 = new Resume(UUID_2);

    private static final String UUID_3 = "UUID_3";
    private final Resume resume3 = new Resume(UUID_3);

    private static final String UUID_4 = "UUID_4";
    private final Resume resume4 = new Resume(UUID_4);

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(resume1);
        storage.save(resume2);
        storage.save(resume3);
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        storage.update(resume1);
        Assert.assertSame(resume1, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(resume4);
    }

    @Test
    public void save() {
        storage.save(resume4);
        Assert.assertEquals(4, storage.size());
        Assert.assertSame(resume4, storage.get(UUID_4));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(resume1);
    }

    @Test
    public void get() {
        Assert.assertEquals(resume1, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(UUID_4);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        storage.get(UUID_1);
        Assert.assertEquals(2, storage.size());
    }

    @Test
    public void getAll() {
        Resume[] resumes = new Resume[]{resume1, resume2, resume3};
        Resume[] allResume = storage.getAll();
        Assert.assertArrayEquals(resumes, allResume);
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }
}