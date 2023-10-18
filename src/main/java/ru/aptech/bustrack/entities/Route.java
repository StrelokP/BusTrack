package ru.aptech.bustrack.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "routes")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "num")
    private String num;

    @Column(name = "color")
    private String color;

    @ManyToMany
    @JoinTable(name = "routes_stations",
            joinColumns = @JoinColumn(name = "route_id"),
            inverseJoinColumns = @JoinColumn(name = "stations_id"))
    private Set<Station> stations = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "routes_transports",
            joinColumns = @JoinColumn(name = "route_id"),
            inverseJoinColumns = @JoinColumn(name = "transports_id"))
    private Set<Transport> transports = new LinkedHashSet<>();

}