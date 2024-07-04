package org.vans4u.fleetwatch.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vans4u.fleetwatch.domain.Van;

public interface VanRepository  extends JpaRepository<Van, Long> {}
