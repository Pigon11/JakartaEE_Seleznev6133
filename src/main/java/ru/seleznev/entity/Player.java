package ru.seleznev.entity; // указываю где находится класс

import jakarta.persistence.*; // импортирую все JPA аннотации для работы с БД

// Теперь нужно объявить класс и аннотации
@Entity // класс = таблица
@Table(name = "player") // пусть имя будет такое
public class Player {

    @Id // первичный ключ и генерируется автоматически
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // столбца таблицы
    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Column(name = "position", nullable = false, length = 50)
    private String position;

    // получается, что нужна связь: многие игроки к одной команде
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;
    // получаем столбец в team_id который ссылается на таблицу team

    // конструкторы
    public Player() {} // нужно создать пустой конструктор для jpa

    public Player(String firstName, String lastName, String position, Team team) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.team = team;
    }

    // геттеры для чтения значений, сеттеры для установки значений
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public Team getTeam() { return team; }
    public void setTeam(Team team) { this.team = team; }

    @Override // вывод объекта
    public String toString() {
        return "Player{id=" + id + ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' + ", position='" + position + '\'' + '}';
    }
}