package co.edu.unbosque.netflixbackend.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.netflixbackend.dto.DocumentalDTO;
import co.edu.unbosque.netflixbackend.model.Documental;
import co.edu.unbosque.netflixbackend.repository.DocumentalRepository;

@Service
public class DocumentalService {

    @Autowired
    private DocumentalRepository documentalRepository;
    
    @Autowired
    private ModelMapper modelMapper;

    public DocumentalDTO buscarPorNombre(String nombre) {
        Documental encontrado = documentalRepository.findByNombre(nombre);
        if (encontrado != null) {
            return modelMapper.map(encontrado, DocumentalDTO.class);
        } else {
            return null;
        }
    }

    public List<Documental> buscarTodos() {
        return documentalRepository.findAll();
    }

    public List<Documental> buscarPorPopularidad(double maxPopularidad) {
        return documentalRepository.findByPopularidad(maxPopularidad);
    }
}
