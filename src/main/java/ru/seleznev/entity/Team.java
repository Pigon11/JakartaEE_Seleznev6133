package ru.seleznev.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity // таблица
@Table(name = "team") // имя таблицы
public class Team {

    @Id // первичный ключ
    @GeneratedValue(strategy = GenerationType.IDENTITY) // автоматическая нумерация
    private Long id;

    @Column(name = "name", nullable = false, length = 100) // связь с столбцом "name"
    private String name;

    @Column(name = "city", nullable = false, length = 100)
    private String city;

    @Column(name = "stadium", nullable = false, length = 150)
    private String stadium;

    // нужна связь с игроками , чтобы я мог добавить игрока в нужную мне команду: Одна команда ко многим игрокам
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Player> players = new ArrayList<>();

    // конструкторы
    public Team() {} // обязательный пустой конструктор

    public Team(String name, String city, String stadium) {
        this.name = name;
        this.city = city;
        this.stadium = stadium;
    }

    // геттеры и сеттеры
    public Long getId() { return id; } // поличил id
    public void setId(Long id) { this.id = id; } // установил id
    // и так для отсальных полей
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getStadium() { return stadium; }
    public void setStadium(String stadium) { this.stadium = stadium; }

    public List<Player> getPlayers() { return players; }
    public void setPlayers(List<Player> players) { this.players = players; }

    // добавление игрока
    public void addPlayer(Player player) {
        players.add(player); // добавляем игрока в спиок
        player.setTeam(this); // заносим в нужную команду
    }

    @Override
    public String toString() {
        return "Team{id=" + id + ", name='" + name + '\'' + ", city='" + city + '\'' + '}';
    }
}


