package com.example.loja.inputadapters;

import com.example.loja.applicationcore.domain.ListarProdutoService;
import com.example.loja.applicationcore.domain.Produto;
import com.example.loja.applicationcore.outputports.ProdutoRepository;
import com.example.loja.outputadapters.ProdutoRepositoryTxt;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
