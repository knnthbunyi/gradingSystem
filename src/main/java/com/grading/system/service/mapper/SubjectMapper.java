package com.grading.system.service.mapper;

import com.grading.system.domain.Subject;
import com.grading.system.service.dto.SubjectDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Subject} and its DTO {@link SubjectDTO}.
 */
@Mapper(componentModel = "spring")
public interface SubjectMapper extends EntityMapper<SubjectDTO, Subject> {}
