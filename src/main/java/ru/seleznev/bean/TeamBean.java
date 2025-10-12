// teambean попроще, сохраняем данные или удаляем команды
package ru.seleznev.bean;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import ru.seleznev.entity.Team;
import ru.seleznev.service.TeamService;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class TeamBean implements Serializable {

    private List<Team> teams;
    private Team selectedTeam = new Team();

    @Inject
    private TeamService teamService;

    public List<Team> getTeams() {
        if (teams == null) {
            loadTeams();
        }
        return teams;
    }

    public void loadTeams() {
        System.out.println("Загрузка списка команд");
        teams = teamService.getAllTeams();
        System.out.println("Загружено команд: " + teams.size());
    }

    public void saveTeam() {
        System.out.println("Вызван saveTeam()");
        System.out.println("Выбранная команда: " + selectedTeam);

        teamService.saveTeam(selectedTeam);

        System.out.println("Сбрасываем кэш и перезагружаем данные");
        teams = null;
        loadTeams();
        selectedTeam = new Team();

        System.out.println("saveTeam() завершен");
    }

    public void editTeam(Team team) {
        System.out.println("Редактируем команду: " + team);
        this.selectedTeam = team;
    }

    public void deleteTeam(Team team) {
        System.out.println("Удаляем команду: " + team);
        teamService.deleteTeam(team.getId());
        teams = null;
        loadTeams();
    }

    public Team getSelectedTeam() {
        return selectedTeam;
    }

    public void setSelectedTeam(Team selectedTeam) {
        this.selectedTeam = selectedTeam;
    }
}