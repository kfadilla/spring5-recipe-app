package guru.springframework.services;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.converters.UomToUomCommand;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UomServiceImpl implements UomService {
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final UomToUomCommand command;
    public UomServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository, UomToUomCommand command) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.command = command;
    }

    @Override
    public Set<UnitOfMeasureCommand> listAll() {
        return StreamSupport.stream(unitOfMeasureRepository.findAll().spliterator(), false)
                            .map(command::convert)
                            .collect(Collectors.toSet());
    }
}
