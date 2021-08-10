package com.topjava.webapp.storage.listStorage;

import com.topjava.webapp.model.Resume;
import com.topjava.webapp.storage.AbstractStorage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private List<Resume> resumeList = new ArrayList<>();

    @Override
    public void clear() {
        resumeList.clear();
    }

    @Override
    public Resume[] getAll() {
        return resumeList.toArray(new Resume[size()]);
    }

    @Override
    public int size() {
        return resumeList.size();
    }

    @Override
    protected void updateResume(Resume resume, Object index) {
        resumeList.set((Integer) index, resume);
    }

    @Override
    protected void saveResume(Resume resume, Object index) {
        resumeList.add(resume);
    }

    @Override
    protected Resume getResume(Resume resume, Object index) {
        return resumeList.get((Integer) index);
    }

    @Override
    protected void deleteResume(Resume resume, Object index) {
        resumeList.remove(resume);
    }

    @Override
    protected boolean isExist(Object index) {
        return index != null;
    }

    @Override
    protected Integer getKey(String uuid) {
        for (int i = 0; i < resumeList.size(); i++) {
            if (uuid.equals(resumeList.get(i).getUuid())) {
                return i;
            }
        }
        return null;
    }
}
