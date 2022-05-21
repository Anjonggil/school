package com.test.school.service.impl;

import com.test.school.common.error.BadRequestApiException;
import com.test.school.common.error.ApiExceptionEntity;
import com.test.school.common.error.ErrorCode;
import com.test.school.domain.Subject;
import com.test.school.domain.request.SubjectRequest;
import com.test.school.domain.response.SubjectResponse;
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

    @Override
    public SubjectResponse getSubjects(){
        List<Subject> subjectList = subjectRepository.findByAll();

        return SubjectResponse.createStudentResponse(subjectList);
    }

    @Transactional
    @Override
    public Long createSubjects(SubjectRequest.Info subjectDto) {
        validateDuplicateSubject(subjectDto.getName());
        Subject subject = subjectDto.toEntity();

        subjectRepository.save(subject);
        return subject.getId();
    }

    //과목 중복 체크
    private void validateDuplicateSubject(String name) {
        Subject findSubject = subjectRepository.findSubjectByName(name);
        if (findSubject != null){
            throw new BadRequestApiException(ApiExceptionEntity.builder()
                    .errorCode(ErrorCode.ALREADY_EXIST_SUBJECT.getCode())
                    .errorMessage("이미 존재하는 과목입니다." +"["+findSubject.getName()+"]")
                    .build());
        }
    }

    @Transactional
    @Override
    public Boolean deleteSubject(Long subjectId) {
        Subject subject = subjectRepository.findSubjectById(subjectId);

        if (subject == null){
            return false;
        }

        subjectRepository.delete(subject);
        return true;
    }

    public Subject getSubject(Long subjectId) {
        Subject findSubject = subjectRepository.findSubjectById(subjectId);
        if (findSubject == null) {
            throw new BadRequestApiException(ApiExceptionEntity.builder()
                    .errorCode(ErrorCode.SUBJECT_NOT_FOUND.getCode())
                    .errorMessage("과목을 찾을 수 없습니다." + " [" + subjectId + "]")
                    .build());
        }
        return findSubject;
    }
}
