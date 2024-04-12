package com.peliculas.peliculasapp.infrastructure.adapter.mapper;
import com.peliculas.peliculasapp.domain.models.Networks;
import com.peliculas.peliculasapp.infrastructure.adapter.entities.NetworksEntity;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class NetworksEntityMapper implements ListMapper<Networks, NetworksEntity>{
    @Override
    public List<NetworksEntity> fromDomainModel(List<Networks> networksList) {
        return networksList.stream()
                .map(networks -> new NetworksEntity(
                        networks.getLogo_path(),
                        networks.getName(),
                        networks.getOrigin_country()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<Networks> toDomainModel(List<NetworksEntity> networksEntityList) {
        return networksEntityList.stream()
                .map(networksEntity -> new Networks(
                        networksEntity.getLogo_path(),
                        networksEntity.getName(),
                        networksEntity.getOrigin_country()
                ))
                .collect(Collectors.toList());
    }
}
