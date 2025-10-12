// соединеям бд с веб страницой
package ru.seleznev.bean;

import jakarta.faces.view.ViewScoped; // пока открыта страница
import jakarta.inject.Inject; // зависимости
import jakarta.inject.Named; // доступ
import ru.seleznev.entity.Player; // импорт классов
import ru.seleznev.entity.Team;
import ru.seleznev.service.PlayerService;
import ru.seleznev.service.TeamService;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class PlayerBean implements Serializable {

    private List<Player> players; // списки для таблицы
    private List<Team> teams;
    private Player selectedPlayer = new Player();

    // id выбранной команды из списка
    private Long selectedTeamId;
    // сервисы для работы
    @Inject
    private PlayerService playerService;

    @Inject
    private TeamService teamService;

    //jsf вызывает метод когда читает
    public Long getSelectedTeamId() {
        return selectedTeamId;
    }
    //когда записывает
    public void setSelectedTeamId(Long selectedTeamId) {
        this.selectedTeamId = selectedTeamId;
    }

    // загружаем игроков при первом обращении
    public List<Player> getPlayers() {
        if (players == null) {
            loadPlayers();
        }
        return players;
    }

    // загружаем команды при первом обращении
    public List<Team> getTeams() {
        if (teams == null) {
            loadTeams();
        }
        return teams;
    }

    public void loadPlayers() {
        players = playerService.getAllPlayers();
    }

    public void loadTeams() {
        teams = teamService.getAllTeams();
    }

    // главный метод
    public void savePlayer() { // вызывается при нажатии сохранить игрока
        System.out.println("Сохранение игрока");
        System.out.println("Игрок: " + selectedPlayer.getFirstName() + " " + selectedPlayer.getLastName());
        System.out.println("Выбран ID команды: " + selectedTeamId);

        // нахождение команды
        if (selectedTeamId != null) {
            Team team = teamService.findTeamById(selectedTeamId);
            if (team != null) {
                selectedPlayer.setTeam(team);
                System.out.println("Команда установлена: " + team.getName());
            } else {
                System.out.println("Команда не найдена по ID: " + selectedTeamId);
                return;
            }
        } else {
            System.out.println("Команда не выбрана!");
            return;
        }

        try { // сохранение игрока в бд
            playerService.savePlayer(selectedPlayer);
            players = null;
            selectedPlayer = new Player();
            selectedTeamId = null;
            System.out.println("Игрок успешно сохранен");
        } catch (Exception e) {
            System.err.println("Ошибка сохранения игрока: " + e.getMessage());
            e.printStackTrace();
        }
    }
    // вспомогательные методы если нужыли на карандаш или крестик
    public void editPlayer(Player player) {
        this.selectedPlayer = player;
    }

    public void deletePlayer(Player player) {
        playerService.deletePlayer(player.getId());
        players = null; // сбрасываем кэш
    }

    public Player getSelectedPlayer() {
        return selectedPlayer;
    }

    public void setSelectedPlayer(Player selectedPlayer) {
        this.selectedPlayer = selectedPlayer;
    }
}