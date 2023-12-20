package sports.nunes.nunes.sports.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sports.nunes.nunes.sports.Dto.ProdutoDto;
import sports.nunes.nunes.sports.entities.Produto;
import sports.nunes.nunes.sports.repository.ProdutoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository repository;

    @Transactional(readOnly = true)
    public List<ProdutoDto> findAll() {
        List<Produto> all = repository.findAll();
        return all.stream().map(ProdutoDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProdutoDto findById(Long id) {
        Optional<Produto> byId = repository.findById(id);
        Produto entity = byId.orElseThrow(IllegalArgumentException::new);
        return new ProdutoDto(entity);
    }

    @Transactional
    public ProdutoDto insert(ProdutoDto dto) {
        Produto produto = new Produto();
        copyDtoToEntity(dto, produto);
        produto = repository.save(produto);
        return new ProdutoDto(produto);
    }

    @Transactional
    public ProdutoDto update(Long id, ProdutoDto dto) {
        try {
            Produto referenceById = repository.getReferenceById(id);
            copyDtoToEntity(dto, referenceById);
            referenceById = repository.save(referenceById);
            return new ProdutoDto(referenceById);
        } catch (EntityNotFoundException e) {
            throw new IllegalArgumentException("Id " + id + " não encontrado!");
        }
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Id " + id + " não encontrado!");
        } else {
            repository.deleteById(id);
        }
    }

    private void copyDtoToEntity(ProdutoDto dto, Produto produto) {
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());
    }

}
