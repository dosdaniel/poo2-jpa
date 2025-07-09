package atividadepoo.demo;

import atividadepoo.demo.entities.Autor;
import atividadepoo.demo.entities.Editora;
import atividadepoo.demo.entities.Livro;
import atividadepoo.demo.enums.TipoPublicacao;
import atividadepoo.demo.repository.AutorRepository;
import atividadepoo.demo.repository.EditoraRepository;
import atividadepoo.demo.repository.LivroRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            EditoraRepository editoraRepository,
            AutorRepository autorRepository,
            LivroRepository livroRepository) {
        return args -> {
            System.out.println("--- INICIANDO APLICAÇÃO E PERSISTINDO DADOS ---");

            //Criar e salvar a Editora ---
            Editora editora = new Editora();
            editora.setNome("Editora Intrínseca");
            editora.setCidade("Rio de Janeiro");
            editoraRepository.save(editora);
            System.out.println("Editora salva com ID: " + editora.getId());

            // Criar e salvar o Autor ---
            Autor autor = new Autor();
            autor.setNome("J.R.R. Tolkien");
            autor.setNacionalidade("Sul-Africano, Britânico");
            autorRepository.save(autor);
            System.out.println("Autor salvo com ID: " + autor.getId());

            //Criar o Livro e associar as entidades salvas ---
            Livro livro = new Livro();
            livro.setTitulo("O Senhor dos Anéis");
            livro.setAnoPublicacao(1954);
            livro.setIsbn("978-85-9508-474-2");
            livro.setPreco(new BigDecimal("149.90"));
            livro.setTipoPublicacao(TipoPublicacao.IMPRESSO);


            livro.setEditora(editora);
            livro.getAutores().add(autor);

            // Salvar o Livro (o JPA cuidará da tabela de junção) ---
            livroRepository.save(livro);
            System.out.println("Livro salvo com ID: " + livro.getId());
            System.out.println("--------------------------------------------------");
        };
    }

}
