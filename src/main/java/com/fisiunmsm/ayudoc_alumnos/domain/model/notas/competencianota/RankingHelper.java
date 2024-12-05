package com.fisiunmsm.ayudoc_alumnos.domain.model.notas.competencianota;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
@Service
@RequiredArgsConstructor
public class RankingHelper {
    public final RankingResultadoDTO calcularNotaPonderada(List<RankingDTO> notas) {
        // Calcular suma de (nota * peso) y suma de pesos
        double sumaNotasPonderadas = notas.stream()
                .mapToDouble(nota -> nota.getNota() * nota.getComponentepeso())
                .sum();
        double sumaPesos = notas.stream()
                .mapToDouble(RankingDTO::getComponentepeso)
                .sum();

        // Calcular la nota ponderada final
        double notaPonderada = sumaPesos > 0 ? sumaNotasPonderadas / sumaPesos : 0.0;
        BigDecimal notaRedondeada = BigDecimal.valueOf(notaPonderada)
                .setScale(2, RoundingMode.HALF_UP);

        // Crear el DTO de resultado tomando un ejemplo para los datos generales
        RankingDTO ejemplo = notas.getFirst();
        return new RankingResultadoDTO(
                ejemplo.getAlumnoid(),
                ejemplo.getNombres(),
                ejemplo.getApellidos(),
                ejemplo.getAlumnocodigo(),
                ejemplo.getCompetenciaid(),
                ejemplo.getCompetenciacodigo(),
                ejemplo.getNombrecompetencia(),
                notaRedondeada.doubleValue()
        );
    }
    // Método para ordenar los resultados por nota ponderada, puedes cambiar el criterio de orden si lo deseas
    public List<RankingResultadoDTO> ordenarRanking(List<RankingResultadoDTO> rankingResultados) {
        return rankingResultados.stream()
                .sorted(Comparator.comparingDouble(RankingResultadoDTO::getCompetenciaid)
                        .thenComparing(Comparator.comparingDouble(RankingResultadoDTO::getNotaponderada).reversed())
                )
                .toList();
    }
}
