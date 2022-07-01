package kr.momjobgo.eyou.web.service.impl;

import groovy.transform.ToString;
import kr.momjobgo.eyou.web.jpa.entity.TestEntity;
import kr.momjobgo.eyou.web.jpa.entity.TestJoinEntity;
import kr.momjobgo.eyou.web.jpa.entity.UserEntity;
import kr.momjobgo.eyou.web.jpa.repository.TestJoinRepository;
import kr.momjobgo.eyou.web.jpa.repository.TestRepository;
import kr.momjobgo.eyou.web.service.TestService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@ToString
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;

    private final TestJoinRepository testJoinRepository;

    public TestServiceImpl(TestRepository testRepository, TestJoinRepository testJoinRepository) {
        this.testRepository = testRepository;
        this.testJoinRepository = testJoinRepository;
    }

    @Override
    public List<TestEntity> testJoin() {
        return testRepository.findAll();
    }

    @Override
    public List<TestJoinEntity> testJoin2() {
        return testJoinRepository.findAll();
    }

    // 모두 가져오기.
    @Override
    public List<TestEntity> getAll() {
        return testRepository.findAll();
    }

    @Override
    public TestEntity getTest(Long id) {
        System.out.println("-----1---------------");
        Optional<TestEntity> testEntity = testRepository.findById(id);

        System.out.println("getTest Start");
        System.out.println(testEntity.toString().toString());
        System.out.println("getTest End");
        if(testEntity.isPresent()){
            return testEntity.get();
        } else {
            return null;
        }

    }

    @Override
    public TestEntity insertTest(String name) {

        TestEntity testEntity = new TestEntity();
        testEntity.setName(name);

        return testRepository.save(testEntity);
    }

    @Override
    public TestEntity insertTest2(TestEntity entity) {
        return testRepository.save(entity);
    }

    @Override
    public TestEntity updateTest(TestEntity entity) {

        TestEntity result = null;

        Optional<TestEntity> testEntity = testRepository.findById(entity.getId());

        if(testEntity.isPresent()){
            if(testEntity.get().getName().equals("수정해봅시다")){
                result = testRepository.save(entity);
            }
        }
        return result;
    }

    @Override
    public String deleteTest(Long id) {
        if(testRepository.findById(id).isPresent()) {
            testRepository.deleteById(id);
            return "삭제성공";
        }
        else {
            return "아이디가 존재하지 않음";
        }
    }

    @Override
    public List<TestEntity> findByName(String name) {
        return testRepository.findByName(name);
    }

    @Override
    public List<TestEntity> findByName2(String name) {
        return testRepository.findByNameContains(name);
    }


}
