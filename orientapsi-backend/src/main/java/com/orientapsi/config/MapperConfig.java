package com.orientapsi.config;

import com.orientapsi.dto.*;
import com.orientapsi.model.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MapperConfig {

    @Primary
    @Bean
    public ModelMapper defaultMapper() {
        return new ModelMapper();
    }

    // ========== MAPPERS CE ==========

    @Bean("casoMapper")
    public ModelMapper casoMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setAmbiguityIgnored(true);

        mapper.createTypeMap(Caso.class, CasoDTO.class)
                .addMapping(src -> src.getConsultante().getIdConsultante(), CasoDTO::setIdConsultante)
                .addMapping(src -> src.getConvocatoria().getIdConvocatoria(), CasoDTO::setIdConvocatoria)
                .addMapping(src -> src.getConsultante().getNombreCompleto(), CasoDTO::setNombreConsultante)
                .addMapping(src -> src.getConsultante().getMotivoConsulta(), CasoDTO::setMotivoConsulta)
                .addMapping(src -> src.getConsultante().getDisponibilidadHoraria(), CasoDTO::setDisponibilidadHoraria);

        // Con STRICT no hace matching implícito, así que no necesitamos skip
        mapper.createTypeMap(CasoDTO.class, Caso.class);

        return mapper;
    }

    @Bean("evaluacionMapper")
    public ModelMapper evaluacionMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setAmbiguityIgnored(true);

        mapper.createTypeMap(EvaluacionCaso.class, EvaluacionCasoDTO.class)
                .addMapping(src -> src.getCaso().getIdCaso(), EvaluacionCasoDTO::setIdCaso)
                .addMapping(src -> src.getPracticante().getIdUsuario(), EvaluacionCasoDTO::setIdPracticante)
                .addMapping(src -> src.getPracticante().getNombre(), EvaluacionCasoDTO::setNombrePracticante);

        mapper.createTypeMap(EvaluacionCasoDTO.class, EvaluacionCaso.class)
                .addMappings(m -> {
                    m.skip(EvaluacionCaso::setCaso);
                    m.skip(EvaluacionCaso::setPracticante);
                });

        return mapper;
    }

    @Bean("entrevistaMapper")
    public ModelMapper entrevistaMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setAmbiguityIgnored(true);

        mapper.createTypeMap(EntrevistaInicial.class, EntrevistaInicialDTO.class)
                .addMapping(src -> src.getCaso().getIdCaso(), EntrevistaInicialDTO::setIdCaso)
                .addMapping(src -> src.getPracticante().getIdUsuario(), EntrevistaInicialDTO::setIdPracticante);

        mapper.createTypeMap(EntrevistaInicialDTO.class, EntrevistaInicial.class)
                .addMappings(m -> {
                    m.skip(EntrevistaInicial::setCaso);
                    m.skip(EntrevistaInicial::setPracticante);
                });

        return mapper;
    }

    @Bean("derivacionMapper")
    public ModelMapper derivacionMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setAmbiguityIgnored(true);

        mapper.createTypeMap(Derivacion.class, DerivacionDTO.class)
                .addMapping(src -> src.getCaso().getIdCaso(), DerivacionDTO::setIdCaso)
                .addMapping(src -> src.getRegistradoPor() != null ? src.getRegistradoPor().getIdUsuario() : null, DerivacionDTO::setRegistradoPor);

        mapper.createTypeMap(DerivacionDTO.class, Derivacion.class)
                .addMappings(m -> {
                    m.skip(Derivacion::setCaso);
                    m.skip(Derivacion::setRegistradoPor);
                });

        return mapper;
    }

    @Bean("salonMapper")
    public ModelMapper salonMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setAmbiguityIgnored(true);

        mapper.createTypeMap(Salon.class, SalonDTO.class)
                .addMapping(src -> src.getDocente() != null ? src.getDocente().getIdUsuario() : null, SalonDTO::setIdDocente)
                .addMapping(src -> src.getDocente() != null ? src.getDocente().getNombre() : null, SalonDTO::setNombreDocente);

        mapper.createTypeMap(SalonDTO.class, Salon.class)
                .addMappings(m -> m.skip(Salon::setDocente));

        return mapper;
    }

    @Bean("adMapper")
    public ModelMapper adMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setAmbiguityIgnored(true);

        mapper.createTypeMap(AcompananteDirecto.class, AcompananteDirectoDTO.class)
                .addMapping(src -> src.getUsuario().getIdUsuario(), AcompananteDirectoDTO::setIdUsuario)
                .addMapping(src -> src.getSalon().getIdSalon(), AcompananteDirectoDTO::setIdSalon)
                .addMapping(src -> src.getSalon().getNombre(), AcompananteDirectoDTO::setNombreSalon);

        mapper.createTypeMap(AcompananteDirectoDTO.class, AcompananteDirecto.class)
                .addMappings(m -> {
                    m.skip(AcompananteDirecto::setUsuario);
                    m.skip(AcompananteDirecto::setSalon);
                });

        return mapper;
    }

    @Bean("disponibilidadMapper")
    public ModelMapper disponibilidadMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setAmbiguityIgnored(true);

        mapper.createTypeMap(DisponibilidadAd.class, DisponibilidadAdDTO.class)
                .addMapping(src -> src.getAcompananteDirecto().getIdAd(), DisponibilidadAdDTO::setIdAd);

        mapper.createTypeMap(DisponibilidadAdDTO.class, DisponibilidadAd.class)
                .addMappings(m -> m.skip(DisponibilidadAd::setAcompananteDirecto));

        return mapper;
    }

    @Bean("asignacionMapper")
    public ModelMapper asignacionMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setAmbiguityIgnored(true);

        mapper.createTypeMap(Asignacion.class, AsignacionDTO.class)
                .addMapping(src -> src.getCaso().getIdCaso(), AsignacionDTO::setIdCaso)
                .addMapping(src -> src.getAcompananteDirecto().getIdAd(), AsignacionDTO::setIdAd)
                .addMapping(src -> src.getCaso().getCodigo(), AsignacionDTO::setCodigoCaso)
                .addMapping(src -> src.getCaso().getConsultante().getNombreCompleto(), AsignacionDTO::setNombreConsultante)
                .addMapping(src -> src.getAcompananteDirecto().getNombrePreferido(), AsignacionDTO::setNombreAd)
                .addMapping(src -> src.getAsignadoPor() != null ? src.getAsignadoPor().getIdUsuario() : null, AsignacionDTO::setAsignadoPor);

        mapper.createTypeMap(AsignacionDTO.class, Asignacion.class)
                .addMappings(m -> {
                    m.skip(Asignacion::setCaso);
                    m.skip(Asignacion::setAcompananteDirecto);
                    m.skip(Asignacion::setAsignadoPor);
                });

        return mapper;
    }

    @Bean("sesionMapper")
    public ModelMapper sesionMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setAmbiguityIgnored(true);

        mapper.createTypeMap(Sesion.class, SesionDTO.class)
                .addMapping(src -> src.getAsignacion().getIdAsignacion(), SesionDTO::setIdAsignacion);

        mapper.createTypeMap(SesionDTO.class, Sesion.class)
                .addMappings(m -> m.skip(Sesion::setAsignacion));

        return mapper;
    }

    @Bean("cierreMapper")
    public ModelMapper cierreMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setAmbiguityIgnored(true);

        mapper.createTypeMap(CierreCaso.class, CierreCasoDTO.class)
                .addMapping(src -> src.getCaso().getIdCaso(), CierreCasoDTO::setIdCaso)
                .addMapping(src -> src.getCerradoPor() != null ? src.getCerradoPor().getIdUsuario() : null, CierreCasoDTO::setCerradoPor);

        mapper.createTypeMap(CierreCasoDTO.class, CierreCaso.class)
                .addMappings(m -> {
                    m.skip(CierreCaso::setCaso);
                    m.skip(CierreCaso::setCerradoPor);
                });

        return mapper;
    }

    @Bean("convocatoriaMapper")
    public ModelMapper convocatoriaMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setAmbiguityIgnored(true);

        // Converter explícito: Usuario -> Integer (idUsuario)
        org.modelmapper.Converter<Usuario, Integer> usuarioToId = ctx ->
                ctx.getSource() == null ? null : ctx.getSource().getIdUsuario();

        mapper.createTypeMap(Convocatoria.class, ConvocatoriaDTO.class)
                .addMappings(m -> m.using(usuarioToId)
                        .map(Convocatoria::getCreadoPor, ConvocatoriaDTO::setCreadoPor));

        mapper.createTypeMap(ConvocatoriaDTO.class, Convocatoria.class)
                .addMappings(m -> m.skip(Convocatoria::setCreadoPor));

        return mapper;
    }
}