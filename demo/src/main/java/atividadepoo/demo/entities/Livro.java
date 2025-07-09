package atividadepoo.demo.entities;

import atividadepoo.demo.enums.TipoPublicacao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "ano_publicacao", nullable = false)
    private Integer anoPublicacao;

    @Column(name = "isbn", nullable = false)
    private String isbn;

    @Column(name = "preco", nullable = false)
    private BigDecimal preco;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoPublicacao tipoPublicacao;

    @ManyToOne
    @JoinColumn(name = "editora_id", nullable = false)
    private Editora editora;

    @ManyToMany
    @JoinTable(
            name = "livro_autor",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private Set<Autor> autores = new HashSet<>();

    public Livro() {}

    public Livro(String titulo, Integer anoPublicacao, String isbn, BigDecimal preco, TipoPublicacao tipoPublicacao) {
        this.titulo = titulo;
        this.anoPublicacao = anoPublicacao;
        this.isbn = isbn;
        this.preco = preco;
        this.tipoPublicacao = tipoPublicacao;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", anoPublicacao='" + anoPublicacao + '\'' +
                ", isbn='" + isbn + '\'' +
                ", preco=" + preco +
                ", tipoPublicacao=" + tipoPublicacao +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Livro livro = (Livro) o;
        return Objects.equals(id, livro.id) && Objects.equals(titulo, livro.titulo) && Objects.equals(anoPublicacao, livro.anoPublicacao) && Objects.equals(isbn, livro.isbn) && Objects.equals(preco, livro.preco) && tipoPublicacao == livro.tipoPublicacao;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, anoPublicacao, isbn, preco, tipoPublicacao);
    }
}
