package com.service.repairs.domain.repository;

import com.service.repairs.domain.Request;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RequestRepository extends CrudRepository<Request, Long> {
}
