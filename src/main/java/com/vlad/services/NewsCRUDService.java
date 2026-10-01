package com.vlad.services;

import com.vlad.news.dto.NewsDTO;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collection;
import java.util.TreeMap;

@Service
public class NewsCRUDService implements CRUDService<NewsDTO>{

    private final TreeMap<Long, NewsDTO> storage = new TreeMap<>();

    @Override
    public NewsDTO getById(Long id) {
        System.out.println("Get by id:" + id);
        return storage.get(id);
    }

    @Override
    public Collection<NewsDTO> getAll() {
        System.out.println("Get all");
        return storage.values();
    }

    @Override
    public NewsDTO create(NewsDTO item) {
        System.out.println("Create");
        Long nextId = (storage.isEmpty() ? 0 : storage.lastKey()) + 1;
        item.setId(nextId);
        item.setDate(Instant.now());
        storage.put(nextId, item);
        return item;
    }

    @Override
    public void update(Long id, NewsDTO item) {
        System.out.println("Updated" + id);
        if (!storage.containsKey(id)) {
            return;
        }
        item.setId(id);
        item.setDate(Instant.now());
        storage.put(id, item);
    }

    @Override
    public void deleteById(Long id) {
        System.out.println("Deleted" + id);
        storage.remove(id);
    }
}
