package com.gladysz.csvconverter.person.batch;

import com.gladysz.csvconverter.person.domain.PersonInput;
import com.gladysz.csvconverter.person.domain.PersonOutput;
import org.jspecify.annotations.Nullable;
import org.springframework.batch.infrastructure.item.ItemProcessor;

import java.time.LocalDate;
import java.time.Period;

public class PersonProcessor implements ItemProcessor<PersonInput, PersonOutput> {

    @Override
    public @Nullable PersonOutput process(PersonInput item) {

        Integer age = Period.between(item.getBirthDate(), LocalDate.now()).getYears();

        return new PersonOutput(item.getFirstName(), item.getLastName(), age);
    }
}
