package hello;

import java.util.Collection;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import hello.TbApiKey;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface TbApiKeyRepository extends CrudRepository<TbApiKey, Integer> {

}

