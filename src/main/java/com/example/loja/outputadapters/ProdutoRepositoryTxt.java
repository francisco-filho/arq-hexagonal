package com.example.loja.outputadapters;

import com.example.loja.applicationcore.domain.Produto;
import com.example.loja.applicationcore.outputports.ProdutoRepository;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

// adaptador de saída (Output Adapter)
public class ProdutoRepositoryTxt implements ProdutoRepository {

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
