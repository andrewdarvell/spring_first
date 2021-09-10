package ru.darvell.gb.spring.component;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.darvell.gb.spring.domain.Product;
import ru.darvell.gb.spring.domain.nonpersist.ShoppingCartEntry;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Component
@NoArgsConstructor
public class ShoppingCart {

    private final Set<ShoppingCartEntry> entries = new HashSet<>();
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void addProduct(Product product) {
        readWriteLock.writeLock().lock();
        entries.add(
                entries.stream().filter(e -> e.getProductId().equals(product.getId())).findFirst()
                        .orElse(new ShoppingCartEntry())
                        .addProduct(product)
        );
        readWriteLock.writeLock().unlock();
    }

    public void removeProduct(Product product) {
        readWriteLock.writeLock().lock();
        Optional<ShoppingCartEntry> cartEntryOptional = entries.stream().filter(e -> e.getProductId().equals(product.getId()))
                .findFirst();
        if (cartEntryOptional.map(ShoppingCartEntry::decreaseAndIsOver).orElse(false)) {
            entries.remove(cartEntryOptional.get());
        }
        readWriteLock.writeLock().unlock();
    }

    public int getCount(){
        return entries.size();
    }

    public Set<ShoppingCartEntry> getEntries() {
        readWriteLock.readLock().lock();
        Set<ShoppingCartEntry> newCart = new HashSet<>();
        entries.forEach(e -> newCart.add(new ShoppingCartEntry(e)));
        readWriteLock.readLock().unlock();
        return newCart;
    }
}
