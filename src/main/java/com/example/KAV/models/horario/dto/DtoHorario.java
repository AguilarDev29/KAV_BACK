package com.example.KAV.models.horario.dto;

import com.example.KAV.models.horario.Horario;

import java.time.format.DateTimeFormatter;

public record DtoHorario(String dia, String hora) {

    public DtoHorario(Horario horario){
        this(horario.getDia().name(), horario.getHora()
                .format(DateTimeFormatter.ofPattern("HH:mm")));
    }
}
