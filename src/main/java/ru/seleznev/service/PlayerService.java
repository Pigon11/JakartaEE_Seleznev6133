// переходим к логике. Операции с игроками

package ru.seleznev.service;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import ru.seleznev.entity.Player;
import ru.seleznev.entity.Team;
import java.util.List;

@Stateless
public class PlayerService {

    @PersistenceContext // glass сам подключается к бд
    private EntityManager entityManager;
    // добавление или обновление игрока
    public Player savePlayer(Player player) {
        System.out.println("Сохранение игрока");

        if (player.getId() == null) {
            System.out.println("Добавление нового игрока: " + player.getFirstName() + " " + player.getLastName());
            entityManager.persist(player);
            System.out.println("Игрок сохранен, ID: " + player.getId());
        } else {
            System.out.println("Обновляем игрока ID: " + player.getId());
            player = entityManager.merge(player);
        }

        return player;
    }
    // удаление игрока по id
    public void deletePlayer(Long playerId) {
        Player player = entityManager.find(Player.class, playerId);
        if (player != null) {
            entityManager.remove(player);
        }
    }
    // поиск игрока по id
    public Player findPlayerById(Long id) {
        return entityManager.find(Player.class, id);
    }
    // все игроки
    public List<Player> getAllPlayers() {
        TypedQuery<Player> query = entityManager.createQuery("SELECT p FROM Player p", Player.class);
        return query.getResultList();
    }
    // поиск игроков по имени
    public List<Player> findPlayersByFirstName(String firstName) {
        TypedQuery<Player> query = entityManager.createQuery(
                "SELECT p FROM Player p WHERE p.firstName = :firstName", Player.class);
        query.setParameter("firstName", firstName);
        return query.getResultList();
    }
    // поиск по фамилии
    public List<Player> findPlayersByLastName(String lastName) {
        TypedQuery<Player> query = entityManager.createQuery(
                "SELECT p FROM Player p WHERE p.lastName = :lastName", Player.class);
        query.setParameter("lastName", lastName);
        return query.getResultList();
    }
    // поиск по позиции
    public List<Player> findPlayersByPosition(String position) {
        TypedQuery<Player> query = entityManager.createQuery(
                "SELECT p FROM Player p WHERE p.position = :position", Player.class);
        query.setParameter("position", position);
        return query.getResultList();
    }
    // поиск по команде
    public List<Player> findPlayersByTeam(Team team) {
        TypedQuery<Player> query = entityManager.createQuery(
                "SELECT p FROM Player p WHERE p.team = :team", Player.class);
        query.setParameter("team", team);
        return query.getResultList();
    }
    // поиск игроков по id команды
    public List<Player> findPlayersByTeamId(Long teamId) {
        TypedQuery<Player> query = entityManager.createQuery(
                "SELECT p FROM Player p WHERE p.team.id = :teamId", Player.class);
        query.setParameter("teamId", teamId);
        return query.getResultList();
    }
}