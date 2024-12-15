package com.example.loja.applicationcore.outputports;

import com.example.loja.applicationcore.domain.Produto;

import java.util.List;

//
public interface ProdutoRepository {
    List<Produto> listar();
}
