package ru.darvell.gb.spring.dao.impl;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import ru.darvell.gb.spring.component.HibernateSessionFactoryManager;
import ru.darvell.gb.spring.domain.Product;
import ru.darvell.gb.spring.dao.ProductDAO;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductDAOImpl implements ProductDAO {

    private final HibernateSessionFactoryManager factoryManager;

    @Override
    public List<Product> getAll() {
        try (Session session = factoryManager.getSession()) {
            session.beginTransaction();
            List<Product> products = session.createQuery("from Product").getResultList();
            session.getTransaction().commit();
            return products;
        }
    }

    @Override
    public Optional<Product> findById(long id) {
        try (Session session = factoryManager.getSession()) {
            session.beginTransaction();
            Optional<Product> optional = Optional.ofNullable(session.get(Product.class, id));
            session.getTransaction().commit();
            return optional;
        }
    }


    @Override
    public Product saveOrUpdate(Product product) {
        try (Session session = factoryManager.getSession()) {
            session.beginTransaction();
            session.saveOrUpdate(product);
            session.getTransaction().commit();

            return product;
        }
    }

    @Override
    public void deleteById(long id) {
        try (Session session = factoryManager.getSession()) {
            session.beginTransaction();
            findById(id).ifPresent(session::delete);
            session.getTransaction().commit();
        }
    }


}
