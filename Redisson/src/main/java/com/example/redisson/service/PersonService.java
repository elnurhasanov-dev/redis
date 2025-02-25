package com.example.redisson.service;

import com.example.redisson.dto.PersonDto;
import com.example.redisson.entity.Person;
import com.example.redisson.repository.PersonRepository;
import com.example.redisson.util.CacheUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PersonService {

    private static final String CACHE_PREFIX = "ms-person:";
    private final CacheUtil cacheUtil;
    private final PersonRepository personRepository;
    private final RedissonClient redissonClient;

    public Person save(Person person) {
        Person savedPerson = personRepository.save(person);
        cacheUtil.saveToCache(getKey(savedPerson.getId()), savedPerson, 10L, ChronoUnit.MINUTES);
        cacheUtil.deleteCache("ms-person:all");
        printCacheKeys();
        return savedPerson;
    }

    public Person getById(Long id) {
        Person cachedPerson = cacheUtil.getBucket(getKey(id));

        if (cachedPerson != null) return cachedPerson;

        return personRepository.findById(id)
                .map(person -> {
                    cacheUtil.saveToCache(getKey(id), person, 10L, ChronoUnit.MINUTES);
                    printCacheKeys();
                    return person;
                })
                .orElse(null);
    }

    public List<Person> getAll() {
        String cacheKey = "ms-person:all";
        List<Person> cachedPersons = cacheUtil.getBucket(cacheKey);
        if (cachedPersons != null) return cachedPersons;

        List<Person> persons = personRepository.findAll();
        cacheUtil.saveToCache(cacheKey, persons, 10L, ChronoUnit.MINUTES);
        return persons;
    }

    public Person update(Long id, PersonDto newPersonData) {
        return personRepository.findById(id)
                .map(person -> {
                    person.setName(newPersonData.getName());
                    person.setAge(newPersonData.getAge());
                    Person updatedPerson = personRepository.save(person);
                    cacheUtil.saveToCache(getKey(id), updatedPerson, 10L, ChronoUnit.MINUTES);
                    cacheUtil.deleteCache("ms-person:all");
                    printCacheKeys();
                    return updatedPerson;
                })
                .orElseThrow(() -> new EntityNotFoundException("Person not found"));
    }

    public void delete(Long id) {
        personRepository.deleteById(id);
        cacheUtil.deleteCache(getKey(id));
        printCacheKeys();
    }

    private String getKey(Long id) {
        return CACHE_PREFIX + id;
    }

    private void printCacheKeys() {
        Iterable<String> keysIterable = cacheUtil.getAllKeys();
        Set<String> keys = new HashSet<>();
        keysIterable.forEach(keys::add);
        System.out.println("Cache-də olan açarlar: " + keys);
    }
}
