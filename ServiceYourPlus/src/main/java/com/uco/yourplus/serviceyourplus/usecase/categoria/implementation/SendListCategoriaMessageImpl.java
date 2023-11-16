package com.uco.yourplus.serviceyourplus.usecase.categoria.implementation;

import com.uco.yourplus.serviceyourplus.domain.CategoriaDomain;
import com.uco.yourplus.serviceyourplus.usecase.categoria.SendListCategoriaMessage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SendListCategoriaMessageImpl implements SendListCategoriaMessage {

    private final
    @Override
    public List<CategoriaDomain> execute(Optional<CategoriaDomain> domain) {
        return null;
    }
}
