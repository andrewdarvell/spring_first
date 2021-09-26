package ru.darvell.gb.spring.component;

import lombok.NoArgsConstructor;
import ru.darvell.gb.spring.domain.Product;
import ru.darvell.gb.spring.domain.nonpersist.ShoppingCartEntry;

import java.util.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@NoArgsConstructor
public class ShoppingCart {

    private final Map<Long, ShoppingCartEntry> entries = new HashMap<>();
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void addProduct(Product product) {
        try {
            readWriteLock.writeLock().lock();
            entries.put(product.getId(),
                    entries.getOrDefault(product.getId(), new ShoppingCartEntry()).addProduct(product)
            );
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void removeProduct(Product product) {
        try {
            readWriteLock.writeLock().lock();
            ShoppingCartEntry shoppingCartEntry = entries.get(product.getId());
            if (shoppingCartEntry.decreaseAndIsOver()) {
                entries.remove(product.getId());
            }
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public int getCount() {
        return entries.size();
    }

    public Set<ShoppingCartEntry> getEntries() {
        try {
            readWriteLock.readLock().lock();
            Set<ShoppingCartEntry> newCart = new HashSet<>();
            entries.forEach((k,v) -> newCart.add(new ShoppingCartEntry(v)));
            return newCart;
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}
