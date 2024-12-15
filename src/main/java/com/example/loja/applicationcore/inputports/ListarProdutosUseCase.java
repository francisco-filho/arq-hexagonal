package com.example.loja.applicationcore.inputports;

import com.example.loja.applicationcore.domain.Produto;

import java.util.List;

// porta de entrada (Input Port)
public interface ListarProdutosUseCase {
    List<Produto> listarProdutosAtivos();
}
