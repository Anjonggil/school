package com.test.school.service.impl;

import com.test.school.common.error.BadRequestApiException;
import com.test.school.common.error.ApiExceptionEntity;
import com.test.school.common.error.ErrorCode;
import com.test.school.domain.entity.Subject;
import com.test.school.domain.request.SubjectRequest;
import com.test.school.domain.response.SubjectResponse;
import com.test.school.repository.StudentSubjectRepository;
import com.test.school.repository.SubjectRepository;
import com.test.school.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;
    private final StudentSubjectRepository studentSubjectRepository;

    @Override
    public SubjectResponse getSubjects(){
        List<Subject> subjectList = subjectRepository.findByAll();

        return SubjectResponse.createStudentResponse(subjectList);
    }

    @Transactional
    @Override
    public Subject createSubjects(SubjectRequest.Info subjectDto) {
        validateDuplicateSubject(subjectDto.getName());
        Subject subject = subjectDto.toEntity();

        return subjectRepository.save(subject);
    }

    //과목 중복 체크
    private void validateDuplicateSubject(String name) {
        Subject findSubject = subjectRepository.findSubjectByName(name);
        if (findSubject != null){
            StringBuilder sb = new StringBuilder();
            sb.append(ErrorCode.ALREADY_EXIST_SUBJECT.getMessage()).append(" [").append(findSubject.getName()).append("]");
            throw new BadRequestApiException(ApiExceptionEntity.builder()
                    .errorCode(ErrorCode.ALREADY_EXIST_SUBJECT.getCode())
                    .errorMessage(sb.toString())
                    .build());
        }
    }

    @Transactional
    @Override
    public Boolean deleteSubject(Long subjectId) {
        Subject findSubject = subjectRepository.findSubjectById(subjectId);

        if (findSubject == null){
            StringBuilder sb = new StringBuilder();
            sb.append(ErrorCode.SUBJECT_NOT_FOUND.getMessage()).append(" [").append(subjectId).append("]");
            throw new BadRequestApiException(ApiExceptionEntity.builder()
                    .errorCode(ErrorCode.SUBJECT_NOT_FOUND.getCode())
                    .errorMessage(sb.toString())
                    .build());
        }

        subjectRepository.delete(findSubject);
        return true;
    }
}
