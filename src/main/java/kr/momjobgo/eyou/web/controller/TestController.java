package kr.momjobgo.eyou.web.controller;

import kr.momjobgo.eyou.web.jpa.entity.TestEntity;
import kr.momjobgo.eyou.web.service.TestService;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/test")
public class TestController {

    private TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    /**
     * Integer 숫자 8byte
     * String 글자
     * Double
     * @return
     */

    @GetMapping("/getData1")   //
    public String getData1() {
        System.out.println("getData1 controller");
        return "getData1 Sucees!!";
    }

    @GetMapping("/getData2")//
    public String getData2(@RequestParam("tEst")String test) { //@RequestParam은 url뒤에 붙는 파라미터의 값을 가져올때 사용
        System.out.println("getData2 controller");
        System.out.println("tEst = "+test);
        String rs = "";
        if (test.equals("1")){
            rs = "getData2 success";
        } else {
            rs = "getData2 nothing";
        }
        return rs;
    }

//    @GetMapping("/getTest?id=11")//
//    public ResponseEntity<?> getTest2(@RequestParam String name) { //@PathVariable url에서 각 구분자에 들어오는 값을 처리할때 사용
//        System.out.println("getTest controller");
//        System.out.println("id = "+name);
//        return ResponseEntity.ok().body(testService.findByName(name));
//    }


    @PostMapping("/postData1") //
    public String postData1(HttpServletRequest request) {
        System.out.println("postData1 controller");
        String dd = request.getParameter("demo");
        System.out.println("demo = "+dd);
        String rs = "";
        if(dd.equals("1")){
            rs = "postData1 success";
        } else {
            rs = "postData1 nothing";
        }
        return rs;
    }

    @GetMapping("/join/test")   // table join을 위한 예제
    public ResponseEntity<?> test() {
        return ResponseEntity.ok(testService.testJoin());
    }

    @GetMapping("/test")    // url로 넘겨받은 param을 그대로 return해줌
    public String test(@RequestParam String param) {
        return param;
    }

    @GetMapping("/join/test2")
    public ResponseEntity<?> test2() {
        return ResponseEntity.ok(testService.testJoin2());
    }

    @GetMapping("/test/all")
    public ResponseEntity<?> getAll() {
       return ResponseEntity.ok().body( testService.getAll() );
    }

    @GetMapping("/test/{id}")   // id에 해당되는 name 받아오기
    public ResponseEntity<?> getTest(@PathVariable Long id) {
        System.out.println("getTest id");
        System.out.println(id);
        return ResponseEntity.ok().body( testService.getTest(id) );
    }

    @PostMapping("/test")   // name을 가진 새 데이터 생성
    public ResponseEntity<?> postTest(@RequestParam String name){
        return ResponseEntity.status(HttpStatus.CREATED).body(testService.insertTest(name));
    }

    @PostMapping("/test2")  // id가 존재하면 update, id가 없으면 auto_increment한 id로 생성
    public ResponseEntity<?> postTest(@RequestBody TestEntity testEntity){
        return ResponseEntity.status(HttpStatus.CREATED).body(testService.insertTest2(testEntity));
    }


    @PatchMapping("/test/{id}") // 하드코딩으로 수정할 id의 name이 "수정해봅시다"이면 아이디 변경
    public ResponseEntity<?> updateTest(@RequestBody TestEntity testEntity){
        return ResponseEntity.ok().body(testService.updateTest(testEntity));
    }

    @DeleteMapping("/test/{id}")    // id에 해당되는 데이터 삭제. 없는 id일 경유 500 에러 발생
    public ResponseEntity<?> deleteTest(@PathVariable Long id) {
        return ResponseEntity.ok().body(testService.deleteTest(id));
    }

    @GetMapping("/test/name")
    public ResponseEntity<?> getTestByName(@RequestParam String name){
        return ResponseEntity.ok().body(testService.findByName(name));
    }

    @GetMapping("/test/name2")
    public ResponseEntity<?> getTestByName2(@RequestParam String name){
        return ResponseEntity.ok().body(testService.findByName2(name));
    }

}
