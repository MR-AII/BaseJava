package com.topjava.webapp.storage.mapStorage;

import com.topjava.webapp.model.Resume;
import com.topjava.webapp.storage.AbstractStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> resumeMap = new HashMap<>();

    @Override
    protected void updateResume(Resume resume, Object searchKey) {
        resumeMap.put(resume.getUuid(), resume);
    }

    @Override
    protected void saveResume(Resume resume, Object searchKey) {
        resumeMap.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume getResume(Resume resume, Object searchKey) {
        return resumeMap.get(resume.getUuid());
    }

    @Override
    protected void deleteResume(Resume resume, Object searchKey) {
        resumeMap.remove(((Resume) searchKey).getUuid());
    }

    @Override
    protected boolean isExist(Object uuid) {
        return uuid != null;
    }

    @Override
    protected Object getKey(String uuid) {
        return resumeMap.get(uuid);
    }

    @Override
    public void clear() {
        resumeMap.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = new ArrayList<>(resumeMap.values());
        return list;
    }

    @Override
    public int size() {
        return resumeMap.size();
    }
}
