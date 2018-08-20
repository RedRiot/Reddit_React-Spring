package com.lhsys.backenddemo.repositories;

import com.lhsys.backenddemo.models.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByName(String name);

}
