package ru.seleznev.service;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import ru.seleznev.entity.Team;
import java.util.List;

@Stateless
public class TeamService {

    @PersistenceContext
    private EntityManager entityManager;

    public Team saveTeam(Team team) {
        System.out.println("Сохранение команд");

        if (team.getId() == null) {
            System.out.println("Добавляем новую команду: " + team.getName());
            entityManager.persist(team);
            System.out.println("Команда сохранена, ID: " + team.getId());
        } else {
            System.out.println("Обновляем команду ID: " + team.getId());
            team = entityManager.merge(team);
        }

        System.out.println("Сохранение завершено");
        return team;
    }

    public void deleteTeam(Long teamId) {
        System.out.println("Удаляем команду ID: " + teamId);

        Team team = entityManager.find(Team.class, teamId);
        if (team != null) {
            entityManager.remove(team);
            System.out.println("Команда удалена");
        }
    }

    public Team findTeamById(Long id) {
        return entityManager.find(Team.class, id);
    }

    public List<Team> getAllTeams() {
        System.out.println("Запрашиваем все команды");
        TypedQuery<Team> query = entityManager.createQuery("SELECT t FROM Team t", Team.class);
        List<Team> teams = query.getResultList();
        System.out.println("Найдено команд: " + teams.size());
        return teams;
    }

    public Team findTeamByName(String name) {
        TypedQuery<Team> query = entityManager.createQuery(
                "SELECT t FROM Team t WHERE t.name = :name", Team.class);
        query.setParameter("name", name);
        return query.getResultStream().findFirst().orElse(null);
    }
}