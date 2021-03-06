package ru.yandex.practicum.filmorate;

import lombok.RequiredArgsConstructor;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.filmStorage.FilmDbStorage;
import ru.yandex.practicum.filmorate.storage.userStorage.UserDbStorage;

import java.time.LocalDate;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@AutoConfigureTestDatabase
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class FilmoRateApplicationTests {

    private final UserDbStorage userStorage;
    private final FilmDbStorage filmStorage;
     User user = new User("123@mail.ru", "Vasya", "Vasiliy",
             LocalDate.of(1997,01,01) );
     User userToUpdate = new User(1,"123@mail.ru", "vanya", "Ivan",
             LocalDate.of(1997,01,01) );
     Mpa mpa = new Mpa(1, "");
     Film film = new Film("Matrix", "description",
             LocalDate.of(2000,01,01), 100, mpa);
     Film filmToUpdate = new Film(1,"Matrix: Revolution", "description",
             LocalDate.of(2000,01,01), 50, mpa);


    @Test
    public void aTestFindUserById() {
        userStorage.add(user);
        User userOptional = userStorage.getToId(1);
        assertThat(userOptional)
                .hasFieldOrPropertyWithValue("id", 1)
                .hasFieldOrPropertyWithValue("name", "Vasiliy");
    }

    @Test
    public void cTestUpdateUser() {
        userStorage.update(userToUpdate);
        User userOptional = userStorage.getToId(userToUpdate.getId());
        assertThat(userOptional)
                .hasFieldOrPropertyWithValue("id", 1)
                .hasFieldOrPropertyWithValue("name","Ivan");
    }

    @Test
    public void dTestGetAllUser() {
        Collection<User> users = userStorage.getAll();
        assertThat(users).size().isNotNull();
        assertThat(users.size()).isEqualTo(1);
    }

    @Test
    public void bTestFindFilmById() {
        filmStorage.add(film);
        Film filmOptional = filmStorage.getToId(1);
        assertThat(filmOptional)
                .hasFieldOrPropertyWithValue("id", 1)
                .hasFieldOrPropertyWithValue("name", "Matrix");
    }

    @Test
    public void eTestUpdateFilm() {
        filmStorage.update(filmToUpdate);
        Film filmOptional = filmStorage.getToId(filmToUpdate.getId());
        assertThat(filmOptional)
                .hasFieldOrPropertyWithValue("id", 1)
                .hasFieldOrPropertyWithValue("name", "Matrix: Revolution");
    }

    @Test
    public void fTestGetAllFilms() {
        Collection<Film> films = filmStorage.getAll();
        assertThat(films).size().isNotNull();
        assertThat(films.size()).isEqualTo(1);
    }
}