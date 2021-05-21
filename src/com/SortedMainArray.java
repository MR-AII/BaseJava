package com;

import com.topjava.webapp.model.Resume;
import com.topjava.webapp.storage.ArrayStorage;
import com.topjava.webapp.storage.SortedArrayStorage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Interactive test for com.topjava.webapp.storage.ArrayStorage implementation
 * (just run, no need to understand)
 */
public class SortedMainArray {
    private static SortedArrayStorage SORTED_ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Resume r;
        while (true) {
            System.out.print("Введите одну из команд - (list | size | save uuid | update uuid | delete uuid | get uuid | clear | exit): ");
            String[] params = reader.readLine().trim().toLowerCase().split(" ");
            if (params.length < 1 || params.length > 2) {
                System.out.println("Неверная команда.");
                continue;
            }
            String uuid = null;
            if (params.length == 2) {
                uuid = params[1].intern();
            }
            switch (params[0]) {
                case "list":
                    printAll();
                    break;
                case "size":
                    System.out.println(SORTED_ARRAY_STORAGE.size());
                    break;
                case "save":
                    r = new Resume();
                    r.setUuid(uuid);
                    SORTED_ARRAY_STORAGE.save(r);
                    printAll();
                    break;
                case "delete":
                    SORTED_ARRAY_STORAGE.delete(uuid);
                    printAll();
                    break;
                case "get":
                    System.out.println(SORTED_ARRAY_STORAGE.get(uuid));
                    break;
                case "update":
                    r = new Resume();
                    r.setUuid(uuid);
                    SORTED_ARRAY_STORAGE.update(r);
                    printAll();
                    break;
                case "clear":
                    SORTED_ARRAY_STORAGE.clear();
                    printAll();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Неверная команда.");
                    break;
            }
        }
    }

    static void printAll() {
        Resume[] all = SORTED_ARRAY_STORAGE.getAll();
        System.out.println("----------------------------");
        if (all.length == 0) {
            System.out.println("Empty");
        } else {
            for (Resume r : all) {
                System.out.println(r);
            }
        }
        System.out.println("----------------------------");
    }
}