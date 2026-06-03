package cr.ac.una.job.services;

import cr.ac.una.job.dtos.caracteristica.CaracteristicaResponse;
import cr.ac.una.job.dtos.caracteristica.CreateCaracteristicaRequest;
import cr.ac.una.job.models.Caracteristica;
import cr.ac.una.job.repositories.ICaracteristicaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CaracteristicaService {

    private final ICaracteristicaRepository repository;

    public CaracteristicaService(ICaracteristicaRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<CaracteristicaResponse> getRoots() {
        return repository.findByPadreIsNull().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<CaracteristicaResponse> getChildren(Long padreId) {
        return repository.findByPadreIdCaracteristica(padreId).stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<CaracteristicaResponse> getAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    public CaracteristicaResponse create(CreateCaracteristicaRequest request) {
        Caracteristica padre = (request.getPadreId() == null) ? null
                : repository.findById(request.getPadreId()).orElse(null);

        Caracteristica c = new Caracteristica(null, request.getNombre().trim(), padre);
        return toResponse(repository.save(c));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private CaracteristicaResponse toResponse(Caracteristica c) {
        Long padreId = (c.getPadre() == null) ? null : c.getPadre().getIdCaracteristica();
        return new CaracteristicaResponse(c.getIdCaracteristica(), c.getNombre(), padreId);
    }
}