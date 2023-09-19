package ru.job4j.generics;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class RoleStoreTest {

    @Test
    void whenAddAndFindThenUsernameIsIvan() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Ivan"));
        Role result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Ivan");
    }

    @Test
    void whenAddAndFindThenUserIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Ivan"));
        Role result = store.findById("2");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindUsernameIsIvan() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Ivan"));
        store.add(new Role("1", "Alex"));
        Role result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Ivan");
    }

    @Test
    void whenReplaceThenUsernameIsAlex() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Ivan"));
        store.replace("1", new Role("1", "Alex"));
        Role result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Alex");
    }

    @Test
    void whenNoReplaceUserThenNoChangeUsername() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Ivan"));
        store.replace("2", new Role("2", "Alex"));
        Role result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Ivan");
    }

    @Test
    void whenDeleteUserThenUserIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Ivan"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteUserThenUsernameIsIvan() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Ivan"));
        store.delete("2");
        Role result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Ivan");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Ivan"));
        boolean result = store.replace("1", new Role("1", "Alex"));
        assertThat(result).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Ivan"));
        boolean result = store.replace("2", new Role("2", "Alex"));
        assertThat(result).isFalse();
    }
}