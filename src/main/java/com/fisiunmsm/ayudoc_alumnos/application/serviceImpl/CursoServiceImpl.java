package com.fisiunmsm.ayudoc_alumnos.application.serviceImpl;

import com.fisiunmsm.ayudoc_alumnos.application.service.CursoService;
import com.fisiunmsm.ayudoc_alumnos.domain.model.inscripcionCurso.CursoDecoder;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.CursoTable;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.inscripcionCurso.CursoMapper;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Base64;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class CursoServiceImpl implements CursoService {
    private final CursoRepository cursoRepository;
    private final CursoMapper cursoMapper;

    @Override
    public Mono<String> generarCodigo(Long idCurso) {
        return cursoRepository.findById(idCurso)
                .map(cursoTable -> {
                    // Concatenar los atributos del curso, incluyendo el departamentoId
                    String base = cursoTable.getId()
                            + "/" + cursoTable.getPeriodoacademicoid()
                            + "/" + cursoTable.getPlanestudiosid()
                            + "/" + cursoTable.getInstitucionid()
                            + "/" + cursoTable.getDepartamentoid(); // Añadir departamentoId

                    // Convertir el String a bytes y luego a Base64
                    return Base64.getUrlEncoder().encodeToString(base.getBytes(StandardCharsets.UTF_8)); // Codificación Base64
                });
    }

    @Override
    public Mono<CursoTable> decodificarCodigoCurso(String codigo) {
        return Mono.fromSupplier(() -> {
            try {
                // Decodificar de Base64 a bytes
                byte[] decodedBytes = Base64.getUrlDecoder().decode(codigo);

                // Convertir el byte array a un String usando UTF-8
                String decodedString = new String(decodedBytes, StandardCharsets.UTF_8);

                // Separar el código en las partes correspondientes utilizando el separador '/'
                String[] partes = decodedString.split("/");

                // Verificar que se reciban todas las partes necesarias
                if (partes.length < 5) {
                    throw new IllegalArgumentException("Error: Código de curso incompleto.");
                }

                // Asumiendo que los datos originales son longs
                Long cursoId = Long.parseLong(partes[0]);
                Long periodoAcademicoId = Long.parseLong(partes[1]);
                Long planEstudiosId = Long.parseLong(partes[2]);
                Long institucionId = Long.parseLong(partes[3]);
                Long departamentoId = Long.parseLong(partes[4]); // Decodificar departamentoId

                CursoDecoder curso = new CursoDecoder();
                curso.setId(cursoId);
                curso.setPeriodoacademicoid(periodoAcademicoId);
                curso.setPlanestudiosid(planEstudiosId);
                curso.setInstitucionid(institucionId);
                curso.setDepartamentoid(departamentoId); // Asignar departamentoId

                return cursoMapper.toEntity(curso);
            } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
                // Manejar el caso en que el formato no es válido
                throw new IllegalArgumentException("Error: Código de curso no válido - " + e.getMessage());
            }
        });
    }

    @Override
    public Mono<CursoTable> findCursoTableById(Long id) {
        return cursoRepository.findById(id);
    }

}
