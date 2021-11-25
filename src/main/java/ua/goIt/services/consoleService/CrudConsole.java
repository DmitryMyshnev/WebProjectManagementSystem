package ua.goIt.services.consoleService;


import java.util.List;
import java.util.Optional;

public interface CrudConsole {
    void save(String arg);

    void update(String arg);

    void delete(String arg);

    List<Object> getAll();

    Optional<Object> findById(Long id);

}
