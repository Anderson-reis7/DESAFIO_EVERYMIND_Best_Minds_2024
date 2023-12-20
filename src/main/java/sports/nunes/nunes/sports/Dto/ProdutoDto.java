package sports.nunes.nunes.sports.Dto;

import lombok.Getter;
import lombok.Setter;
import sports.nunes.nunes.sports.entities.Produto;

@Getter
@Setter
public class ProdutoDto {
    private Long id;
    private String nome;
    private String descricao;
    private Double preco;

    public ProdutoDto() {
    }

    public ProdutoDto(Produto entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.descricao = entity.getDescricao();
        this.preco = entity.getPreco();
    }
}
