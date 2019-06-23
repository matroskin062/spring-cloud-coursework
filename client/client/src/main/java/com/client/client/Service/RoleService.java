package com.client.client.Service;

import com.client.client.Model.Authorities;
import org.springframework.data.repository.CrudRepository;

public interface RoleService extends CrudRepository<Authorities, Integer> {
}
