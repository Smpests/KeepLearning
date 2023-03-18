package tutorial.mockitospring.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class NameService {
    public String getGlobalUniqueName(String id) {
        return id + UUID.randomUUID().toString();
    }
}
