package com.example.loja;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Objeto de domínio
 */
record Produto(int id, String nome, boolean ativo){}

// porta de entrada (Input Port)
interface ListarProdutosUseCase {
    List<Produto> listarProdutosAtivos(); }

// lógica de negócios
class ListarProdutoService implements ListarProdutosUseCase {
    ProdutoRepository repository;

    public ListarProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Produto> listarProdutosAtivos() {
        return repository.listar()
                .stream()
                .filter(p -> p.ativo())
                .toList();
    }
}

//
interface ProdutoRepository {
    List<Produto> listar();
}

// adaptador de saída (Output Adapter)
class ProdutoRepositoryTxt implements ProdutoRepository {

    @Override
    public List<Produto> listar() {
        try {
            var is = this.getClass().getClassLoader().getResourceAsStream("produtos.txt");
            var txt = new String(is.readAllBytes());
            return parseProducts(txt);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Produto> parseProducts(String txt) {
        return Arrays.stream(txt.split("\n"))
                .map(linha -> {
                   var campos = linha.split(",");
                   return new Produto(
                           Integer.parseInt(campos[0]),
                           campos[1],
                           Boolean.valueOf(campos[2])
                   );
                })
                .toList();
    }
}

public class Exemplo {
    @Test
    void deve_listar_somente_produtos_ativos(){
        // Output Adapter (que nesse caso é um mock)
        ProdutoRepository repository = () -> {
            return List.of(
                    new Produto(1, "arroz", true),
                    new Produto(2, "feijão", true),
                    new Produto(3, "vinho", false)
            );
        };

        var service = new ListarProdutoService(repository);
        var produtos = service.listarProdutosAtivos();

        assertEquals(2, produtos.size());
    }

    public static void main(String[] args) {
        var repository = new ProdutoRepositoryTxt();
        var service = new ListarProdutoService(repository);
        System.out.println(service.listarProdutosAtivos());
    }
}
