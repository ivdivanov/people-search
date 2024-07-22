package net.isbg.people.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.isbg.people.validation.ExactLengthOrNull;
import net.isbg.people.validation.PersonName;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "people", uniqueConstraints={@UniqueConstraint(columnNames = "name")})
public class People {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    @NonNull
    @PersonName(message = "Only allowing letters (cyrillic or latin) and dashes or spaces in between!")
    @Size(max = 90)
    private String name;

    @Column(name = "pin")
    @ExactLengthOrNull(length = 10, message = "PIN has to be exactly 10 characters long")
    @Pattern(regexp = "\\d*", message = "PIN must contain only numbers")
    private String pin;

    @OneToMany(mappedBy = "people", cascade = CascadeType.ALL)
    private Set<Mails> mails;

    @OneToMany(mappedBy = "people", cascade = CascadeType.ALL)
    private Set<Addresses> addresses;
}