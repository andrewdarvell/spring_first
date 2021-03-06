package ru.darvell.gb.spring.repository.product_type;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.darvell.gb.spring.domain.product_type.DictValueType;

@Repository
public interface DictValueTypeRepository extends JpaRepository<DictValueType, Long> {
}
