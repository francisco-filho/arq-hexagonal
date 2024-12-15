package com.example.loja.applicationcore.domain;

import com.example.loja.applicationcore.inputports.ListarProdutosUseCase;
import com.example.loja.applicationcore.outputports.ProdutoRepository;

import java.util.List;

// lógica de negócios
public class ListarProdutoService implements ListarProdutosUseCase {
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
