package com.rakuten.challenge.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Table(name = CharacterEntity.TABLE_NAME)
@Entity(name = CharacterEntity.ENTITY_NAME)
@Data
public class CharacterEntity {

    public static final String TABLE_NAME = "dnd_character";
    public static final String ENTITY_NAME = "CharacterEntity";

    public CharacterEntity() {
    }

    public CharacterEntity(String name, Integer age, String raceIndex, String classIndex) {
        this.name = name;
        this.age = age;
        this.raceIndex = raceIndex;
        this.classIndex = classIndex;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "race_index", nullable = false)
    private String raceIndex;

    @Column(name = "class_index", nullable = false)
    private String classIndex;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;
}
